import socket

def run_server():
    HOST = '0.0.0.0'  # Escuta em todas as interfaces da VM
    PORT = 8080

    # Criação do socket TCP (SOCK_STREAM)
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Permite reutilizar o endereço para evitar erros de "Address already in use"
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    server_socket.bind((HOST, PORT))
    server_socket.listen(1)
    print(f"[*] Servidor de login escutando em {HOST}:{PORT}...")

    while True:
        client_socket, client_address = server_socket.accept()
        print(f"[+] Conexão aceita de {client_address[0]}:{client_address[1]}")

        # Recebe os dados em texto claro (máximo de 1024 bytes)
        data = client_socket.recv(1024)
        if not data:
            client_socket.close()
            continue

        request = data.decode('utf-8').strip()
        print(f"[>] Dados recebidos: {request}")

        # Protocolo simples: espera receber "usuario:senha"
        try:
            username, password = request.split(':', 1)
            # Validação fictícia simples
            if username == "admin" and password == "secret123":
                response = "LOGIN_SUCCESS\n"
            else:
                response = "LOGIN_FAILURE\n"
        except ValueError:
            response = "INVALID_FORMAT\n"

        # Envia a resposta de volta ao cliente
        client_socket.sendall(response.encode('utf-8'))
        client_socket.close()
        print(f"[<] Resposta enviada e conexão encerrada com {client_address[0]}")

if __name__ == "__main__":
    run_server()