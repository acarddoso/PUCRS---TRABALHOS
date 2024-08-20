import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListaSinalizacao {
    private int quantidade;
    private Sinalizacao inicio;
    private Sinalizacao fim;


    public ListaSinalizacao(){
        inicio = new Sinalizacao(null, null);
        fim = new Sinalizacao(null, null);
        this.quantidade = 0;
    }

    public Sinalizacao getInicio() {
        return inicio;
    }

    public void setInicio(Sinalizacao inicio) {
        this.inicio = inicio;
    }

    public Sinalizacao getFim() {
        return fim;
    }

    public void setFim(Sinalizacao fim) {
        this.fim = fim;
    }

    /**public int obterMesComMaisSinalizacoes() {
        int[] contadorMeses = new int[13];

        Sinalizacao atual = inicio;

        while (atual != fim && atual != null) {
            LocalDate data = atual.getData();
            int mes = 12;
            if (data != null) {
                mes = data.getMonthValue();
            }
            contadorMeses[mes - 1]++;
            atual = atual.proximo;
        }

        int indiceMesMaisSinalizacoes = 0;
        int maiorQuantidade = contadorMeses[0];

        for (int i = 1; i < contadorMeses.length; i++) {
            if (contadorMeses[i] > maiorQuantidade) {
                maiorQuantidade = contadorMeses[i];
                indiceMesMaisSinalizacoes = i;
            }
        }

        Month.of(indiceMesMaisSinalizacoes + 1)

        return ;
    }
     * @return*/

    public Map<Month, Integer> obterMesComMaisSinalizacoes() {
        Map<Month, Integer> contadorMeses = new HashMap<>();

        Sinalizacao atual = inicio.proximo;
        while (atual != null && atual != fim) {
            LocalDate data = atual.getData();
            if (data != null) {
                Month mes = data.getMonth();
                contadorMeses.put(mes, contadorMeses.getOrDefault(mes, 0) + 1);
            }

            atual = atual.proximo;
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

        return contadorMeses;
    }

    public void adicionarSinalizacao(Sinalizacao novaSinalizacao) {

        if (this.estaVazia()) {
            this.inicio = novaSinalizacao;
            this.fim = novaSinalizacao;

        }else {
            this.fim.proximo = novaSinalizacao;
            this.fim = novaSinalizacao;
        }
        quantidade++;
    }

    public boolean estaVazia() {
        return (this.quantidade==0);
    }

    public void esvaziar(){
        this.inicio=null;
        this.fim = null;
        this.quantidade=0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void removerSinalizacao(int posicao){
        if (posicao==0){
            this.inicio = inicio.proximo;
        }
        else {
            Sinalizacao aux = this.inicio;
            Sinalizacao anterior= aux;
            for (int i=0; i< posicao;i++){
                anterior=aux;
                aux = aux.proximo;
            }
            anterior.proximo = aux.proximo;
            if (aux.proximo!=null){
                this.fim = anterior;
            }
        }

    }
}
