import java.util.PriorityQueue;

enum TipoEv {
    IN, OUT
}

class Evento implements Comparable<Evento> {
    double t;
    TipoEv tipo;

    Evento(double t, TipoEv tipo) {
        this.t = t;
        this.tipo = tipo;
    }

    @Override
    public int compareTo(Evento e2) {
        if (this.t < e2.t) return -1;
        if (this.t > e2.t) return 1;
        return 0;
    }
}

class Gerador {
    static long s = 42;
    static int cont = 0;

    static double nextRand() {
        s = (1664525 * s + 1013904223) % (long) Math.pow(2, 32);
        cont++;
        return (double) Math.abs(s) / Math.pow(2, 32);
    }

    static double calcIntervalo(double min, double max) {
        return min + (nextRand() * (max - min));
    }
}

public class SimuladorFila {
    PriorityQueue<Evento> lista;
    int cap, servs, f;
    double agora, uT;
    int loss;
    double[] estados;

    public SimuladorFila(int cap, int servs) {
        this.cap = cap;
        this.servs = servs;
        this.f = 0;
        this.agora = 0;
        this.uT = 0;
        this.loss = 0;
        this.estados = new double[cap + 1];
        this.lista = new PriorityQueue<>();
        Gerador.cont = 0; 
    }

    public void exec(double t1, int max) {
        lista.add(new Evento(t1, TipoEv.IN));

        while (Gerador.cont < max && !lista.isEmpty()) {
            Evento e = lista.poll();
            
            double d = e.t - uT;
            estados[f] += d;
            
            agora = e.t;
            uT = agora;

            if (e.tipo == TipoEv.IN) {
                if (f < cap) {
                    f++;
                    if (f <= servs) {
                        lista.add(new Evento(agora + Gerador.calcIntervalo(3.0, 5.0), TipoEv.OUT));
                    }
                } else {
                    loss++;
                }
                lista.add(new Evento(agora + Gerador.calcIntervalo(2.0, 5.0), TipoEv.IN));
            } else {
                f--;
                if (f >= servs) {
                    lista.add(new Evento(agora + Gerador.calcIntervalo(3.0, 5.0), TipoEv.OUT));
                }
            }
        }
        
        System.out.println("Tempo: " + agora);
        System.out.println("Perdas: " + loss);
        for (int i = 0; i < estados.length; i++) {
            System.out.println("Estado " + i + ": " + (estados[i]/agora)*100 + "%");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("--- G/G/1/5 ---");
        new SimuladorFila(5, 1).exec(2.0, 100000);

        System.out.println("--- G/G/2/5 ---");
        new SimuladorFila(5, 2).exec(2.0, 100000);
    }
}