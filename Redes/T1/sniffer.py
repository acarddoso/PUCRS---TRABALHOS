import socket
import struct

def parse_packet(packet):
    # O cabeçalho Ethernet tem 14 bytes
    eth_length = 14
    eth_header = packet[:eth_length]
    eth = struct.unpack('!6s6sH', eth_header)
    
    # EtherType para IPv4 é 0x0800
    eth_protocol = socket.ntohs(eth[2])
    if eth_protocol != 8:
        return

    # Extrai o cabeçalho IP (começa após o Ethernet)
    ip_header = packet[eth_length:eth_length+20]
    iph = struct.unpack('!BBHHHBBH4s4s', ip_header)

    version_ihl = iph[0]
    ihl = version_ihl & 0xF
    ip_header_length = ihl * 4

    protocol = iph[6]
    
    # Protocolo TCP é o número 6
    if protocol != 6:
        return

    # Extrai o cabeçalho TCP
    tcp_header_start = eth_length + ip_header_length
    tcp_header = packet[tcp_header_start:tcp_header_start+20]
    tcph = struct.unpack('!HHLLBBHHH', tcp_header)

    source_port = tcph[0]
    dest_port = tcph[1]
    
    # Calcula o tamanho do cabeçalho TCP
    doff_reserved = tcph[4]
    tcph_length = (doff_reserved >> 4) * 4

    # O payload (dados da aplicação) começa após o cabeçalho Ethernet + IP + TCP
    payload_start = eth_length + ip_header_length + tcph_length
    payload = packet[payload_start:]

    if not payload:
        return

    # Filtra apenas pacotes direcionados ou originados na porta 8080 (nosso alvo)
    if dest_port == 8080 or source_port == 8080:
        try:
            # Tenta decodificar o payload como texto claro
            data_str = payload.decode('utf-8', errors='ignore').strip()
            if data_str:
                print(f"[INTERCEPTADO] Porta Destino: {dest_port} | Payload: {data_str}")
        except Exception:
            pass

def main():
    # Cria um socket raw para capturar pacotes na camada de enlace (Ethernet)
    # ATENÇÃO: Funciona em sistemas Linux (ambiente padrão das VMs)
    try:
        raw_socket = socket.socket(socket.AF_PACKET, socket.SOCK_RAW, socket.ntohs(3))
    except PermissionError:
        print("[-] Erro: Você precisa executar este script como root (sudo python3 sniffer.py).")
        return

    print("[*] Sniffer passivo rodando... Aguardando tráfego do protocolo de login na porta 8080...")

    while True:
        # Recebe o pacote bruto da rede
        packet, _ = raw_socket.recvfrom(65535)
        parse_packet(packet)

if __name__ == "__main__":
    main()