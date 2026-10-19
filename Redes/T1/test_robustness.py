import socket
import threading
import time

PROXY_IP = "127.0.0.1"
PROXY_PORT = 8080


def send_message(message, test_name):
    try:
        with socket.create_connection(
            (PROXY_IP, PROXY_PORT),
            timeout=5
        ) as client:

            client.settimeout(5)
            client.sendall(message)

            response = client.recv(1024)

            print(
                f"[{test_name}] "
                f"Resposta: {response.decode('utf-8', errors='replace').strip()}"
            )

    except Exception as error:
        print(f"[{test_name}] Erro: {error}")


def disconnect_abruptly():
    try:
        with socket.create_connection(
            (PROXY_IP, PROXY_PORT),
            timeout=5
        ) as client:

            print("[DESCONEXAO] Cliente conectado")

            client.sendall(b"admin:")

            print("[DESCONEXAO] Encerrando sem esperar resposta")

    except Exception as error:
        print(f"[DESCONEXAO] Erro: {error}")


def main():
    print("===== TESTE DE ROBUSTEZ =====")

    tests = [
        (b"mensagem_invalida", "FORMATO INVALIDO"),
        (b"admin:senhaerrada", "LOGIN ADULTERADO"),
        (b"usuario:senha", "LOGIN INVALIDO"),
        (b"\xff\xfe\xfd", "BYTES INVALIDOS")
    ]

    threads = []

    for message, name in tests:
        thread = threading.Thread(
            target=send_message,
            args=(message, name)
        )

        threads.append(thread)

    disconnect_thread = threading.Thread(
        target=disconnect_abruptly
    )

    threads.append(disconnect_thread)

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()

    print("\n[*] Aguardando 1 segundo...")
    time.sleep(1)

    print("[*] Verificando se o proxy continua funcionando...")

    send_message(
        b"admin:senhaerrada",
        "TESTE FINAL"
    )

    print("\n===== FIM DOS TESTES =====")


if __name__ == "__main__":
    main()