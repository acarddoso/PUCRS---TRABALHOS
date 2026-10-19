import socket
import threading

PROXY_HOST = "127.0.0.1"
PROXY_PORT = 8080

SERVER_IP = "127.0.0.1"
SERVER_PORT = 9090


def relay(source, destination, direction):
    try:
        while True:
            data = source.recv(4096)

            if not data:
                break

            print(f"[{direction}] Original: {data.decode('utf-8', errors='replace')}")

            if direction == "CLIENTE -> SERVIDOR":
                original = b"admin:senhaerrada"
                modified = b"admin:secret123"

                if data == original:
                    data = modified
                    print("[MITM] Credenciais adulteradas!")

            print(f"[{direction}] Encaminhado: {data.decode('utf-8', errors='replace')}")

            destination.sendall(data)

    except OSError as error:
        print(f"[!] Conexão encerrada: {error}")

    finally:
        try:
            destination.shutdown(socket.SHUT_WR)
        except OSError:
            pass


def handle_client(client_socket, client_address):
    print(f"[+] Cliente conectado: {client_address}")

    try:
        with client_socket:
            with socket.create_connection(
                (SERVER_IP, SERVER_PORT),
                timeout=10
            ) as server_socket:

                server_socket.settimeout(None)

                client_to_server = threading.Thread(
                    target=relay,
                    args=(client_socket, server_socket, "CLIENTE -> SERVIDOR")
                )

                server_to_client = threading.Thread(
                    target=relay,
                    args=(server_socket, client_socket, "SERVIDOR -> CLIENTE")
                )

                client_to_server.start()
                server_to_client.start()

                client_to_server.join()
                server_to_client.join()

    except OSError as error:
        print(f"[-] Erro na sessão {client_address}: {error}")

    finally:
        print(f"[-] Sessão encerrada: {client_address}")


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as proxy_socket:
        proxy_socket.setsockopt(
            socket.SOL_SOCKET,
            socket.SO_REUSEADDR,
            1
        )

        proxy_socket.bind((PROXY_HOST, PROXY_PORT))
        proxy_socket.listen(20)

        print(f"[*] Proxy MITM escutando em {PROXY_HOST}:{PROXY_PORT}")
        print(f"[*] Encaminhando para {SERVER_IP}:{SERVER_PORT}")

        while True:
            client_socket, client_address = proxy_socket.accept()

            thread = threading.Thread(
                target=handle_client,
                args=(client_socket, client_address),
                daemon=True
            )

            thread.start()


if __name__ == "__main__":
    main()