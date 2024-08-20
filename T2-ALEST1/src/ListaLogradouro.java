import java.time.Month;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListaLogradouro {
    private Logradouro inicio;
    private Logradouro fim;
    private int quantidade;

    public ListaLogradouro() {
        inicio=new Logradouro(null, null);
        fim= new Logradouro(null, null);
        quantidade=0;
    }

    public boolean add(Logradouro logradouro){
        if (!existe(logradouro.getNome())){
            adicionarLogradouroOrdenado(logradouro);
            return true;
        }
        return false;
    }

    public Logradouro getMaisSinalizacoes(){
        Logradouro atual = inicio.getProximo();
        Logradouro logradouroComMaiorQtdSinalizacoes  = null;
        int maiorQtdSinalizacoes = 0;
        while (atual != fim) {
            if (atual.getQtdSinalizacoes() > maiorQtdSinalizacoes) {
                maiorQtdSinalizacoes = atual.getQtdSinalizacoes();
                logradouroComMaiorQtdSinalizacoes = atual;
            }
            atual = atual.getProximo();
        }
        return logradouroComMaiorQtdSinalizacoes;
    }

    public int mesComMaisSinalizacoes(){
        Logradouro logradouroAtual = inicio;

        if (quantidade>0) {
            logradouroAtual = inicio.getProximo();
        }

        int mesComMaisTotalInt = 0;
        Map<Month, Integer> contadorMeses = new HashMap<>();
        contadorMeses.put(Month.JANUARY, 0);
        contadorMeses.put(Month.FEBRUARY, 0);
        contadorMeses.put(Month.MARCH, 0);
        contadorMeses.put(Month.APRIL, 0);
        contadorMeses.put(Month.JUNE, 0);
        contadorMeses.put(Month.JULY, 0);
        contadorMeses.put(Month.AUGUST, 0);
        contadorMeses.put(Month.SEPTEMBER, 0);
        contadorMeses.put(Month.OCTOBER, 0);
        contadorMeses.put(Month.NOVEMBER, 0);
        contadorMeses.put(Month.DECEMBER, 0);

        while (logradouroAtual != null) {
            Map<Month, Integer> mesesAtual = logradouroAtual.mesComMaisSinalizacoes();
            Iterator<Month> iterator = mesesAtual.keySet().iterator();
            while(iterator.hasNext()) {
                Month mes = iterator.next();
                Integer quantidade = mesesAtual.get(mes);
                contadorMeses.merge(mes, quantidade, Integer::sum);
            }
            logradouroAtual = logradouroAtual.getProximo();
        }

        int maiorQuantidade = 0;
        int mesComMaisSinalizacoes = 0;

        Iterator<Month> iterator = contadorMeses.keySet().iterator();

        while(iterator.hasNext()) {
            Month mes = iterator.next();
            Integer quantidade = contadorMeses.get(mes);

            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                mesComMaisSinalizacoes = mes.getValue();
            }
        }

        return mesComMaisSinalizacoes;
    }

    public Logradouro consultarLogradouroPorNome(String nome) {
        Logradouro logradouroAtual = inicio;

        if (quantidade>0) {
            logradouroAtual = inicio.getProximo();
        }

        while (logradouroAtual != null) {
            if (logradouroAtual.getNome().equals(nome)) {
                return logradouroAtual;
            }

            logradouroAtual = logradouroAtual.getProximo();
        }

        return null;
    }

    public void adicionarLogradouroOrdenado(Logradouro novoLogradouro) {

        if (quantidade == 0) {
            // A lista está vazia, portanto o novo logradouro será o único na lista
            inicio.proximo = novoLogradouro;
            novoLogradouro.anterior = inicio;
            novoLogradouro.proximo = fim;
            fim.anterior = novoLogradouro;
        } else {
            Logradouro atual = inicio.proximo;

            while (atual != fim && atual.getNome().compareTo(novoLogradouro.getNome()) < 0) {
                atual = atual.proximo;
            }

            novoLogradouro.proximo = atual;
            novoLogradouro.anterior = atual.anterior;
            atual.anterior.proximo = novoLogradouro;
            atual.anterior = novoLogradouro;
        }

        quantidade++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Logradouro atual = inicio;

        while (atual != null) {
            sb.append(atual.getNome());
            sb.append(" (");
            sb.append(atual.getTipo());
            sb.append(")");
            sb.append(System.lineSeparator());

            atual = atual.proximo;
        }

        return sb.toString();
    }

    private void adicionarLogradouro(Logradouro novoLogradouro) {
         if(quantidade==0) {
            inicio.proximo = novoLogradouro;
            fim.anterior = novoLogradouro;
            novoLogradouro.anterior = inicio;
            novoLogradouro.proximo = fim;
            quantidade++;
            return;
        }

        novoLogradouro.proximo = fim;
        novoLogradouro.anterior = fim.anterior;
        fim.anterior = novoLogradouro;
        novoLogradouro.anterior.proximo = novoLogradouro;
        quantidade++;
    }

    public boolean existe(String nome) {
        Logradouro logradouroAtual = inicio;

        if (quantidade>0) {
            logradouroAtual = inicio.proximo;
        }

        while (logradouroAtual.getNome() != null) {
            if (logradouroAtual.getNome().equals(nome)) {
                return true;
            }

            logradouroAtual = logradouroAtual.proximo;
        }
        return false;
    }

    private void trocarLogradouros(Logradouro logradouro1, Logradouro logradouro2) {
        // Troca a posição de dois logradouros adjacentes na lista
        if (logradouro1.anterior != null) {
            logradouro1.anterior.proximo = logradouro2;
        } else {
            inicio = logradouro2;
        }

        if (logradouro2.proximo != null) {
            logradouro2.proximo.anterior = logradouro1;
        } else {
            fim = logradouro1;
        }

        Logradouro temp = logradouro1.proximo;
        logradouro1.proximo = logradouro2.proximo;
        logradouro2.proximo = logradouro1;
        logradouro2.anterior = logradouro1.anterior;
        logradouro1.anterior = logradouro2;

        if (logradouro1.proximo != null) {
            logradouro1.proximo.anterior = logradouro1;
        }

        if (logradouro2.anterior != null) {
            logradouro2.anterior.proximo = logradouro2;
        }
    }

    public void ordenar() {
        if (quantidade <= 1) {
            return;
        }

        boolean trocaRealizada;
        do {
            trocaRealizada = false;
            Logradouro atual = inicio;

            while (atual.proximo != null) {
                if (atual.getNome().compareTo(atual.proximo.getNome()) > 0) {
                    trocarLogradouros(atual, atual.proximo);
                    trocaRealizada = true;
                }

                atual = atual.proximo;
            }
        } while (trocaRealizada);
    }

    public Logradouro getInicio() {
        return inicio;
    }

    public void setInicio(Logradouro inicio) {
        this.inicio = inicio;
    }

    public Logradouro getFim() {
        return fim;
    }

    public void setFim(Logradouro fim) {
        this.fim = fim;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
