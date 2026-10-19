import socket
import sys

def run_client(server_ip):
    PORT = 8080

    # Criação do socket TCP
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    print(f"[*] Conectando ao servidor {server_ip}:{PORT}...")
    try:
        client_socket.connect((server_ip, PORT))
    except ConnectionRefusedError:
        print("[-] Erro: Não foi possível conectar ao servidor.")
        sys.exit(1)

    # Credenciais em texto claro para testar o protocolo
    username = "admin"
    password = "senhaerrada"
    message = f"{username}:{password}"

    print(f"[>] Enviando credenciais: {message}")
    client_socket.sendall(message.encode('utf-8'))

    # Recebe a resposta do servidor
    response = client_socket.recv(1024).decode('utf-8').strip()
    print(f"[<] Resposta do servidor: {response}")

    client_socket.close()

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Uso: python3 client.py ")
        sys.exit(1)
    
    server_ip = sys.argv[1]
    run_client(server_ip)