import socket
import threading
import time

PROXY_IP = "127.0.0.1"
PROXY_PORT = 8080

NUM_CLIENTS = 10

results = []
lock = threading.Lock()


def simulate_client(client_id):
    try:
        start = time.perf_counter()

        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client:
            client.settimeout(10)
            client.connect((PROXY_IP, PROXY_PORT))

            message = "admin:senhaerrada"
            client.sendall(message.encode("utf-8"))

            response = client.recv(1024).decode("utf-8").strip()

        elapsed = time.perf_counter() - start

        with lock:
            results.append((client_id, response, elapsed))

        print(
            f"[Cliente {client_id}] "
            f"Resposta: {response} | "
            f"Tempo: {elapsed:.4f}s"
        )

    except Exception as error:
        with lock:
            results.append((client_id, "ERROR", 0))

        print(f"[Cliente {client_id}] Erro: {error}")


def main():
    threads = []

    print(f"[*] Iniciando teste com {NUM_CLIENTS} clientes...")

    start = time.perf_counter()

    for i in range(NUM_CLIENTS):
        thread = threading.Thread(
            target=simulate_client,
            args=(i + 1,)
        )

        threads.append(thread)

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()

    elapsed = time.perf_counter() - start

    success = sum(
        1 for _, response, _ in results
        if response == "LOGIN_SUCCESS"
    )

    failures = NUM_CLIENTS - success

    print("\n===== RESULTADO FINAL =====")
    print(f"Total de clientes: {NUM_CLIENTS}")
    print(f"Logins bem-sucedidos: {success}")
    print(f"Falhas: {failures}")
    print(f"Tempo total: {elapsed:.4f}s")


if __name__ == "__main__":
    main()