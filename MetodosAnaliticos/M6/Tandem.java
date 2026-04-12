package MetodosAnaliticos.M6;
import java.util.PriorityQueue;

enum Tipo {
    CHEGADA1, PASSAGEM, SAIDA2
}

class Evento implements Comparable<Evento> {
    double tempo;
    Tipo tipo;

    Evento(double tempo, Tipo tipo) {
        this.tempo = tempo;
        this.tipo = tipo;
    }

    @Override
    public int compareTo(Evento e2) {
        return Double.compare(this.tempo, e2.tempo);
    }
}

class Gerador {
    static long semente = 12345; 
    static long a = 1103515245;
    static long c = 12345;
    static long M = (long) Math.pow(2, 31);
    static int cont = 0;

    static double nextRand() {
        semente = (a * semente + c) % M;
        cont++;
        return (double) semente / M;
    }

    static double calc(double min, double max) {
        return min + (nextRand() * (max - min));
    }
}

public class Tandem {
    public static void main(String[] args) {
        int f1 = 0;
        int cap1 = 3;
        int serv1 = 2;
        int loss1 = 0;
        double[] est1 = new double[4];

        int f2 = 0;
        int cap2 = 5;
        int serv2 = 1;
        int loss2 = 0;
        double[] est2 = new double[6];

        double agora = 0;
        double ultimoTempo = 0;
        PriorityQueue<Evento> lista = new PriorityQueue<>();

        lista.add(new Evento(1.5, Tipo.CHEGADA1));

        while (Gerador.cont < 100000 && !lista.isEmpty()) {
            Evento e = lista.poll();
            
            double delta = e.tempo - ultimoTempo;
            est1[f1] += delta;
            est2[f2] += delta;
            
            agora = e.tempo;
            ultimoTempo = agora;

            if (e.tipo == Tipo.CHEGADA1) {
                if (f1 < cap1) {
                    f1++;
                    if (f1 <= serv1) {
                        lista.add(new Evento(agora + Gerador.calc(3.0, 4.0), Tipo.PASSAGEM));
                    }
                } else {
                    loss1++;
                }
                lista.add(new Evento(agora + Gerador.calc(1.0, 4.0), Tipo.CHEGADA1));
            } 
            else if (e.tipo == Tipo.PASSAGEM) {
                f1--;
                if (f1 >= serv1) {
                    lista.add(new Evento(agora + Gerador.calc(3.0, 4.0), Tipo.PASSAGEM));
                }

                if (f2 < cap2) {
                    f2++;
                    if (f2 <= serv2) {
                        lista.add(new Evento(agora + Gerador.calc(2.0, 3.0), Tipo.SAIDA2));
                    }
                } else {
                    loss2++;
                }
            } 
            else if (e.tipo == Tipo.SAIDA2) {
                f2--;
                if (f2 >= serv2) {
                    lista.add(new Evento(agora + Gerador.calc(2.0, 3.0), Tipo.SAIDA2));
                }
            }
        }

        System.out.println("Tempo global: " + agora);
        System.out.println("RESULTADOS FILA 1");
        System.out.println("Perdas F1: " + loss1);
        for (int i = 0; i < est1.length; i++) {
            System.out.printf("Estado %d: %.4f (Prob: %.2f%%)\n", i, est1[i], (est1[i]/agora)*100);
        }

        System.out.println("\nRESULTADOS FILA 2");
        System.out.println("Perdas F2: " + loss2);
        for (int i = 0; i < est2.length; i++) {
            System.out.printf("Estado %d: %.4f (Prob: %.2f%%)\n", i, est2[i], (est2[i]/agora)*100);
        }
    }
}