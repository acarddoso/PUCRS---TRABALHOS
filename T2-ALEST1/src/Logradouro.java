import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class Logradouro {
    private String tipo;
    private String nome;
    private ListaSinalizacao sinalizacoes;
    private int qtdSinalizacoes;
    public Logradouro anterior;
    public Logradouro proximo;

    public Logradouro(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        this.sinalizacoes = new ListaSinalizacao();
    }

    public void setSinalizacoes(ListaSinalizacao sinalizacoes) {
        this.sinalizacoes = sinalizacoes;
    }

    public Logradouro getAnterior() {
        return anterior;
    }

    public void setAnterior(Logradouro anterior) {
        this.anterior = anterior;
    }

    public Logradouro getProximo() {
        return proximo;
    }

    public void setProximo(Logradouro proximo) {
        this.proximo = proximo;
    }

    public int getQtdSinalizacoes() {
        return this.qtdSinalizacoes;
    }

    public void setQtdSinalizacoes(int qtdSinalizacoes) {
        this.qtdSinalizacoes = qtdSinalizacoes;
    }



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ListaSinalizacao getSinalizacoes() {
        return sinalizacoes;
    }

    public Map<Month, Integer> mesComMaisSinalizacoes(){
        return sinalizacoes.obterMesComMaisSinalizacoes();
    }

    public void adicionarSinalizacao(Sinalizacao sinalizacao){
        this.sinalizacoes.adicionarSinalizacao(sinalizacao);
        this.qtdSinalizacoes++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sinalizacao obterSinalizacaoMaiorData() {
        Sinalizacao sinalizacaoMaiorData = null;
        LocalDate maiorData = null;

        Sinalizacao atual = sinalizacoes.getInicio();
        while (atual != null) {
            LocalDate data = atual.getData();
            if (data != null && (maiorData == null || data.compareTo(maiorData) > 0)) {
                maiorData = data;
                sinalizacaoMaiorData = atual;
            }

            atual = atual.getProximo();
        }

        return sinalizacaoMaiorData;
    }

    public Sinalizacao obterSinalizacaoMenorData() {
        Sinalizacao sinalizacaoMenorData = null;
        LocalDate menorData = null;

        Sinalizacao atual = sinalizacoes.getInicio();
        while (atual != null) {
            LocalDate data = atual.getData();
            if (data != null && (menorData == null || data.isBefore(menorData))) {
                menorData = data;
                sinalizacaoMenorData = atual;
            }

            atual = atual.getProximo();
        }

        return sinalizacaoMenorData;
    }

    public String getNomeCompleto(){
        return this.tipo + " " + this.nome;
    }

}
