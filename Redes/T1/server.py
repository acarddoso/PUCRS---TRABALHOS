import socket

HOST = "127.0.0.1"
PORT = 9090


def handle_client(client_socket, client_address):
    try:
        with client_socket:
            print(f"[+] Conexão aceita de {client_address}")

            data = client_socket.recv(1024)

            if not data:
                print("[-] Cliente desconectou sem enviar dados")
                return

            try:
                request = data.decode("utf-8").strip()

            except UnicodeDecodeError:
                print("[-] Mensagem com codificação UTF-8 inválida")
                client_socket.sendall(b"INVALID_ENCODING\n")
                return

            print(f"[>] Dados recebidos: {request}")

            try:
                username, password = request.split(":", 1)

                if username == "admin" and password == "secret123":
                    response = "LOGIN_SUCCESS\n"
                else:
                    response = "LOGIN_FAILURE\n"

            except ValueError:
                response = "INVALID_FORMAT\n"

            client_socket.sendall(response.encode("utf-8"))

            print(f"[<] Resposta enviada: {response.strip()}")

    except (ConnectionResetError, BrokenPipeError) as error:
        print(f"[-] Cliente desconectou: {error}")

    except OSError as error:
        print(f"[-] Erro de comunicação: {error}")

    finally:
        print(f"[-] Sessão encerrada: {client_address}")


def run_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:

        server_socket.setsockopt(
            socket.SOL_SOCKET,
            socket.SO_REUSEADDR,
            1
        )

        server_socket.bind((HOST, PORT))
        server_socket.listen(20)

        print(f"[*] Servidor escutando em {HOST}:{PORT}")

        while True:
            try:
                client_socket, client_address = server_socket.accept()

                handle_client(client_socket, client_address)

            except OSError as error:
                print(f"[-] Erro ao aceitar conexão: {error}")


if __name__ == "__main__":
    run_server()