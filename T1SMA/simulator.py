import yaml
import random
from collections import defaultdict

class QueueNode:
    def __init__(self, name, service_time_min, service_time_max, capacity):
        self.name = name
        self.service_time_min = service_time_min
        self.service_time_max = service_time_max 
        self.capacity = capacity 
        self.current_state = 0 
        self.transitions = {}
        self.state_times = defaultdict(float) 
        self.losses = 0 

    def add_transition(self, target_node, probability):
        self.transitions[target_node] = probability

    def generate_service_time(self):
        return random.uniform(self.service_time_min, self.service_time_max)

class QueueNetworkSimulator:
    def __init__(self, config_file):
        self.nodes = {}
        self.events = []
        self.current_time = 0.0
        self.total_randoms = 0
        self.max_randoms = 100000
        self.global_time = 0.0
        self.load_config(config_file)

    def load_config(self, config_file):
        with open(config_file, 'r') as file:
            config = yaml.safe_load(file)

        for node in config['nodes']:
            queue = QueueNode(node['name'], node['service_time_min'], node['service_time_max'], node['capacity'])
            self.nodes[node['name']] = queue

        for transition in config['transitions']:
            source = transition['source']
            for target, prob in transition['targets'].items():
                self.nodes[source].add_transition(target, prob)

        self.schedule_event(2.0, 'arrival', 'G/G/1', None)

    def schedule_event(self, time, event_type, node, customer_from):
        self.events.append((time, event_type, node, customer_from))
        self.events.sort()

    def run(self):
        while self.events and self.total_randoms < self.max_randoms:
            event = self.events.pop(0)
            self.current_time = event[0]
            event_type, node_name, customer_from = event[1], event[2], event[3]
            node = self.nodes[node_name]

            for n in self.nodes.values():
                n.state_times[n.current_state] += self.current_time - self.global_time
            self.global_time = self.current_time

            if event_type == 'arrival':
                self.handle_arrival(node, customer_from)
            elif event_type == 'departure':
                self.handle_departure(node)

    def handle_arrival(self, node, customer_from):
        self.total_randoms += 1
        if node.current_state < node.capacity:
            node.current_state += 1
            service_time = node.generate_service_time()
            self.schedule_event(self.current_time + service_time, 'departure', node.name, None)
        else:
            node.losses += 1

        if node.name == 'G/G/1':
            next_arrival_time = random.uniform(2.0, 4.0)
            self.schedule_event(self.current_time + next_arrival_time, 'arrival', 'G/G/1', None)

    def handle_departure(self, node):
        node.current_state -= 1
        self.total_randoms += 1

        if node.transitions:
            r = random.random()
            cumulative_prob = 0.0
            for target, prob in node.transitions.items():
                cumulative_prob += prob
                if r <= cumulative_prob:
                    if target == 'exit':
                        break
                    self.schedule_event(self.current_time, 'arrival', target, node.name)
                    break

    def report_statistics(self):
        print("=== Resultados da Simulação ===")
        print(f"Tempo Global da Simulação: {self.current_time} minutos")
        print("\nDistribuição de Probabilidades e Tempos Acumulados por Fila:")
        for node_name, node in self.nodes.items():
            print(f"\nFila {node_name}:")
            total_time = sum(node.state_times.values())
            for state, time in node.state_times.items():
                prob = time / total_time if total_time > 0 else 0
                print(f"Estado {state}: Probabilidade = {prob:.4f}, Tempo Acumulado = {time:.2f} minutos")
            print(f"Perda de Clientes: {node.losses}")
        print(f"\nTotal de Números Aleatórios Usados: {self.total_randoms}")

config_yaml = """
nodes:
  - name: G/G/1
    service_time_min: 1.0
    service_time_max: 2.0
    capacity: 1
  - name: G/G/2/5
    service_time_min: 4.0
    service_time_max: 8.0
    capacity: 5
  - name: G/G/2/10
    service_time_min: 5.0
    service_time_max: 15.0
    capacity: 10

transitions:
  - source: G/G/1
    targets:
      G/G/2/5: 0.8
      G/G/2/10: 0.2
  - source: G/G/2/5
    targets:
      G/G/1: 0.3
      exit: 0.2
  - source: G/G/2/10
    targets:
      exit: 0.3
"""

with open("network_config.yaml", "w") as f:
    f.write(config_yaml)

simulator = QueueNetworkSimulator("network_config.yaml")
simulator.run()
simulator.report_statistics()