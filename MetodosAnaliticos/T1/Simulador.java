package T1;
import java.util.*;

class Evento implements Comparable<Evento> {
    double tempo;
    int tipo;
    Fila origem;
    Fila destino;

    Evento(double t, int tp, Fila o, Fila d) {
        this.tempo = t;
        this.tipo = tp;
        this.origem = o;
        this.destino = d;
    }

    public int compareTo(Evento outro) {
        return Double.compare(this.tempo, outro.tempo);
    }
}

class Fila {
    String id;
    int k = 0; 
    int c = 0; 
    int atual = 0; 
    int perdas = 0;
    double minS, maxS;
    double[] tempos;
    Map<Fila, Double> rotas = new HashMap<>();
    double saidaCerta = 0;

    Fila(String id, int c, int k, double min, double max) {
        this.id = id;
        this.c = c; 
        this.k = k; 
        this.minS = min;
        this.maxS = max;
        this.tempos = new double[k + 1];
    }
}

public class Simulador {
    static long semente = 12345;
    static int randCont = 0;

    static double proximoRand() {
        semente = (1103515245 * semente + 12345) % 2147483648L;
        randCont++;
        return (double) Math.abs(semente) / 2147483648L;
    }

    static double geraTempo(double min, double max) {
        return min + (proximoRand() * (max - min));
    }

    public static void main(String[] args) {
        Fila f1 = new Fila("Fila 1", 1, 100, 1.0, 2.0);
        Fila f2 = new Fila("Fila 2", 2, 5, 4.0, 6.0);
        Fila f3 = new Fila("Fila 3", 2, 10, 5.0, 15.0);

        f1.rotas.put(f2, 0.8);
        f1.rotas.put(f3, 0.2);
        
        f2.rotas.put(f1, 0.3);
        f2.rotas.put(f3, 0.5);
        f2.saidaCerta = 0.2;

        f3.rotas.put(f2, 0.7);
        f3.saidaCerta = 0.3;

        PriorityQueue<Evento> lista = new PriorityQueue<>();
        
        lista.add(new Evento(2.0, 1, null, f1));

        double tempoTotal = 0;
        double uTempo = 0;

        while (randCont < 100000 && !lista.isEmpty()) {
            Evento e = lista.poll();
            tempoTotal = e.tempo;
            double diff = tempoTotal - uTempo;

            f1.tempos[f1.atual] += diff;
            f2.tempos[f2.atual] += diff;
            f3.tempos[f3.atual] += diff;
            
            uTempo = tempoTotal;

            if (e.tipo == 1 || e.tipo == 3) {
                Fila f = e.destino;
                if (e.tipo == 1) {
                    lista.add(new Evento(tempoTotal + geraTempo(2.0, 4.0), 1, null, f1));
                }

                if (f.atual < f.k) {
                    f.atual++;
                    if (f.atual <= f.c) {
                        lista.add(new Evento(tempoTotal + geraTempo(f.minS, f.maxS), 2, f, null));
                    }
                } else {
                    f.perdas++;
                }
            } else if (e.tipo == 2) {
                Fila f = e.origem;
                f.atual--;
                if (f.atual >= f.c) {
                    lista.add(new Evento(tempoTotal + geraTempo(f.minS, f.maxS), 2, f, null));
                }
                
                double r = proximoRand();
                double soma = 0;
                boolean foi = false;
                for (Fila destino : f.rotas.keySet()) {
                    soma += f.rotas.get(destino);
                    if (r < soma) {
                        lista.add(new Evento(tempoTotal, 3, f, destino));
                        foi = true;
                        break;
                    }
                }
            }
        }

        System.out.println("Resultado");
        System.out.println("Tempo global: " + tempoTotal);
        System.out.println("Aleatorios usados: " + randCont);
        
        exibir(f1, tempoTotal);
        exibir(f2, tempoTotal);
        exibir(f3, tempoTotal);
    }

    static void exibir(Fila f, double total) {
        System.out.println("\n--- " + f.id + " ---");
        System.out.println("Perdas: " + f.perdas);
        for (int i = 0; i < f.tempos.length; i++) {
            if (f.tempos[i] > 0) {
                double p = (f.tempos[i] / total) * 100;
                System.out.printf("Estado %d: %.2f seg (%.2f%%)\n", i, f.tempos[i], p);
            }
        }
    }
}