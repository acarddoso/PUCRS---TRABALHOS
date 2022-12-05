import java.util.Scanner;
import java.io.PrintStream;

/**
 * Biblioteca de metodos para processamento de vetores de inteiros.
 *
 * @author Roland Teodorowitsch
 * @version 19 out. 2022
 */

public class Vetor {
    
    /**
     * Realiza a leitura de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>le</code> para um vetor parcial.
     * @param in Objeto da classe <code>Scanner</code> que ser&aacute; usado para realizar a leitura.
     * @param v Vetor a ser lido.
     */
    public static void le(Scanner in, int [] v) {
        le(in,v,v.length);
    }
    
    /**
     * Realiza a leitura de um vetor parcial (parcialmente preenchido) de inteiros.
     * @param in Objeto da classe <code>Scanner</code> que ser&aacute; usado para realizar a leitura.
     * @param v Vetor parcial a ser lido.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o lidos).
     */
    public static void le(Scanner in, int [] v, int t) {
        for (int i=0; i<t; ++i)
            v[i] = in.nextInt();
    }
    
    /**
     * Realiza a impress&atilde;o de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>imprime</code> para um vetor parcial.
     * @param out Objeto da classe <code>PrintStream</code> que ser&aacute; usado para realizar a impress&atilde;o.
     * @param v Vetor a ser impresso.
     */
    public static void imprime(PrintStream out,int[] v) {
        imprime(out,v,v.length);
    }

    /**
     * Realiza a impress&atilde;o de um vetor parcial (parcialmente preenchido) de inteiros.
     * @param out Objeto da classe <code>PrintStream</code> que ser&aacute; usado para realizar a impress&atilde;o.
     * @param v Vetor a ser impresso.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o impressos).
     */
    public static void imprime(PrintStream out,int[] v, int t) {
        for (int i=0; i<t; ++i) {
            if (i > 0)
                out.print(" ");
            out.print(v[i]);
        }
    }
    
    /**
     * Preenche todas as posi&ccedil;&otilde;es de um vetor (completamente preenchido) de inteiros com determinado valor.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>preenche</code> para um vetor parcial.
     * @param v Vetor a ser preenchido.
     * @param n Valor que ser&aacute; atribuido a cada posi&ccedil;&atilde;o do vetor.
     */
    public static void preenche(int[] v, int n) {
        preenche(v,v.length,n);
    }
    
    /**
     * Preenche as posi&ccedil;&otilde;es de um vetor parcial (parcialmente preenchido) de inteiros com determinado valor.
     * @param v Vetor a ser preenchido.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o atualizados).
     * @param n Valor que ser&aacute; atribuido a cada posi&ccedil;&atilde;o do vetor.
     */
    public static void preenche(int[] v, int t, int n) {
        for(int i = 0; i < t; i++){
            v[i] = n;
        }
    }
    
    /**
     * Preenche todas as posi&ccedil;oes de um vetor (completamente preenchido) de inteiros com valores sequenciais a partir de 1.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>preencheSequencialmente</code> para um vetor parcial.
     * @param v Vetor a ser preenchido.
     */
    public static void preencheSequencialmente(int[] v) {
        preencheSequencialmente(v,v.length);
    }
    
    /**
     * Preenche as posi&ccedil;oes de um vetor parcialmente preenchido de inteiros com valores sequenciais a partir de 1.
     * @param v Vetor a ser preenchido.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o atualizados).
     */
    public static void preencheSequencialmente(int[] v, int t) {
        for(int i = 0; i < t; i++){
            v[i] = i+1;
        }
    }
    
    /**
     * Soma todos os elementos de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>soma</code> para um vetor parcial.
     * @param v Vetor cujos elementos devem ser somados.
     * @return Somat&oacute;rio dos elementos do vetor (<code>long</code>).
     */
    public static long soma(int[] v) {
        return soma(v,v.length);
    }
    
    /**
     * Soma os elementos de um vetor parcialmente preenchido de inteiros.
     * @param v Vetor cujos elementos devem ser somados.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o somados).
     * @return Somat&oacute;rio dos elementos do vetor parcial (<code>long</code>).
     */
    public static long soma(int[] v, int t) {
        long somatorio = 0;
        for(int i = 0; i < t; i++){
            somatorio += v[i];
        }
        return somatorio; 
    }
    
    /**
     * M&eacute;dia de todos os elementos de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>media</code> para um vetor parcial.
     * @param v Vetor de cujos elementos a m&eacute;dia ser&aacute; calculada.
     * @return M&eacute;dia dos elementos do vetor (<code>double</code>).
     */
    public static double media(int[] v) {
        return (double)soma(v,v.length)/v.length;
    }
    
    /**
     * M&eacute;dia dos elementos de um vetor parcial (parcialmente preenchido) de inteiros.
     * @param v Vetor de cujos elementos a m&eacute;dia ser&aacute; calculada.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @return M&eacute;dia dos elementos do vetor parcial (<code>double</code>).
     */
    public static double media(int[] v,int t) {
        double media = 0;
        for(int i = 0; i < t; i++){
            media += v[i];
        }

        return media / t; 
    }
    
    /**
     * Determina o menor valor de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>menor</code> para um vetor parcial.
     * @param v Vetor para o qual ser&aacute; determinado o menor valor.
     * @return Menor valor dos elementos do vetor.
     */
    public static int menor(int[] v) {
            return menor(v,v.length);
    }
    
    /**
     * Determina o menor valor de um vetor parcial (parcialmente preenchido) de inteiros.
     * @param v Vetor para o qual ser&aacute; determinado o menor valor.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @return Menor valor dos elementos do vetor parcial.
     */
    public static int menor(int[] v, int t) {
        int menor = v[0];
        for(int i = 0; i < t; i++){
            if(v[i] < menor){
                menor = v[i];
            }
        }
        return menor; 
    }
    
    /**
     * Determina o maior valor de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>maior</code> para um vetor parcial.
     * @param v Vetor para o qual ser&aacute; determinado o maior valor.
     * @return Maior valor dos elementos do vetor.
     */
    public static int maior(int[] v) {
        return maior(v,v.length);
    }
    
    /**
     * Determina o maior valor de um vetor parcial (parcialmente preenchido) de inteiros.
     * @param v Vetor para o qual ser&aacute; determinado o maior valor.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @return Maior valor dos elementos do vetor parcial.
     */
    public static int maior(int[] v, int t) {
        int maior = 0;
        for(int i = 0; i < t; i++){
            if(v[i] > maior){
                maior = v[i];
            }
            }
        return maior; 
    }

    /**
     * Conta quantas vezes determinado valor aparece em um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>conta</code> para um vetor parcial.
     * @param v Vetor no qual ser&aacute; feita a verifica&ccedil;&atilde;o.
     * @param n Valor inteiro que ser&aacute; contado no vetor.
     * @return N&uacute;mero de vezes que o valor inteiro foi localizado no vetor.
     */
    public static int conta(int[] v, int n) {
        return conta(v,v.length,n);
    }
    
    /**
     * Conta quantas vezes determinado valor aparece em um vetor parcial (parcialmente preenchido) de inteiros.
     * @param v Vetor no qual ser&aacute; feita a verifica&ccedil;&atilde;o.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o testados).
     * @param n Valor inteiro que ser&aacute; contado no vetor.
     * @return N&uacute;mero de vezes que o valor inteiro foi localizado no vetor.
     */
    public static int conta(int[] v, int t, int n) {
        int cont = 0;
        for(int i = 0; i < t; i++){
            if(v[i] == n){
                cont++;
            }
        }
        return cont; 
    }
    
    /**
     * Inverte os elementos de um vetor (completamente preenchido) de inteiros, de forma que o primeiro elemento
     * seja trocado com o &uacute;ltimo, o segundo com o pen&uacute;ltimo, e assim por diante.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>inverte</code> para um vetor parcial.
     * @param v Vetor que ser&aacute; invertido.
     */
    public static void inverte(int[] v) {
        inverte(v,v.length);
    }
    
    /**
     * Inverte os elementos de um vetor parcial (parcialmente preenchido) de inteiros, de forma que o primeiro elemento
     * seja trocado com o &uacute;ltimo, o segundo com o pen&uacute;ltimo, e assim por diante.
     * @param v Vetor que ser&aacute; invertido.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     */
    public static void inverte(int[] v, int t) {
        int i = 0; int j = t -1;
        while(i < j){
            int aux = v[i];
            v[i] = v[j];
            v[j] = aux;
            i++;
            j--;
        }
    }
    
    /**
     * Realiza a busca de um determinado valor, linearmente, em um vetor (completamente
     * preenchido) de inteiros, do primeiro at&aacute; o &uacute;ltimo elemento, retornando
     * o &iacutue;ndice da primeira posi&ccedil;&atilde;o em que o elemento foi encontrado; ou
     * <code>-1</code> caso o elemento n&atilde;o seja encontrado.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>buscaLinear</code> para um vetor parcial.
     * @param v Vetor no qual a busca linear ser&aacute; feita.
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice da primeira ocorr&ecirc;ncia do elemento procurado no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaLinear(int[] v, int n) {
        return buscaLinear(v,v.length,n);
    }
    
    /**
     * Realiza a busca de um determinado valor, linearmente, em um vetor parcial
     * (parcialmente preenchido) de inteiros, do primeiro at&aacute; o &uacute;ltimo
     * elemento, retornando o &iacutue;ndice da primeira posi&ccedil;&atilde;o em que
     * o elemento foi encontrado; ou <code>-1</code> caso o elemento n&atilde;o seja
     * encontrado.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>buscaLinear</code> para um vetor parcial.
     * @param v Vetor no qual a busca linear ser&aacute; feita.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice da primeira ocorr&ecirc;ncia do elemento procurado no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaLinear(int[] v, int t, int n) {
        for(int i = 0; i < t; i++){
            if(v[i] == n){
                return i;
            }
        }

        return -1; 
    }
    
    /**
     * Realiza a busca de um determinado valor em um vetor (completamente preenchido) de
     * inteiros ordenado, usando o algoritmo de busca bin&acute;ria e retornando
     * o &iacutue;ndice da posi&ccedil;&atilde;o em que o elemento foi encontrado; ou
     * <code>-1</code> caso o elemento n&atilde;o seja encontrado.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>buscaBinaria</code> para um vetor parcial.
     * @param v Vetor no qual a busca bin&aacute;ria ser&aacute; feita.
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice do elemento no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaBinaria(int[] v, int n) {
        return buscaBinaria(v,v.length,n);
    }
    
    /**
     * Realiza a busca de um determinado valor em um vetor parcial (parcialmente
     * preenchido) de inteiros ordenado, usando o algoritmo de busca bin&acute;ria
     * e retornando o &iacutue;ndice da posi&ccedil;&atilde;o em que o elemento
     * foi encontrado; ou <code>-1</code> caso o elemento n&atilde;o seja encontrado.
     * @param v Vetor no qual a busca bin&aacute;ria ser&aacute; feita.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice do elemento no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaBinaria(int[] v, int t, int n) {
        int inicio = 0; int fim = t -1;
        while(inicio <= fim){
            int meio = (inicio + fim) / 2;
            if(n == v[meio]){
                return meio;
            }
            if(n < v[meio]){
                fim = meio -1;
            } else inicio = meio +1;
        }
        return -1; 
    }
    
    /**
     * Realiza a busca de um determinado valor em um vetor (completamente preenchido) de
     * inteiros ordenado, usando o algoritmo de busca bin&acute;ria recursiva e retornando
     * o &iacutue;ndice da posi&ccedil;&atilde;o em que o elemento foi encontrado; ou
     * <code>-1</code> caso o elemento n&atilde;o seja encontrado.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>buscaBinariaRec</code> para um vetor parcial ou completo.
     * @param v Vetor no qual a busca bin&aacute;ria ser&aacute; feita.
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice do elemento no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaBinariaRec(int[] v, int n) {
        return buscaBinariaRec(v,0,v.length-1,n);
    }
    
    /**
     * Realiza a busca de um determinado valor em um vetor parcial (parcialmente
     * preenchido) de inteiros ordenado, usando o algoritmo de busca bin&acute;ria recursiva
     * e retornando o &iacutue;ndice da posi&ccedil;&atilde;o em que o elemento
     * foi encontrado; ou <code>-1</code> caso o elemento n&atilde;o seja encontrado.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>buscaBinariaRec</code> para um vetor parcial ou completo.
     * @param v Vetor no qual a busca bin&aacute;ria ser&aacute; feita.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice do elemento no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaBinariaRec(int[] v, int t, int n) {
        return buscaBinariaRec(v,0,t-1,n);
    }
    
    /**
     * Realiza a busca de um determinado valor em um vetor parcial (parcialmente
     * preenchido) ou completamente preenchido de inteiros ordenado, usando o
     * algoritmo de busca bin&acute;ria recursiva e retornando o &iacutue;ndice
     * da posi&ccedil;&atilde;o em que o elemento foi encontrado; ou <code>-1</code>
     * caso o elemento n&atilde;o seja encontrado.
     * @param v Vetor no qual a busca bin&aacute;ria recursiva ser&aacute; feita.
     * @param ini Posi&ccedil;&atilde;o inicial dentro do vetor onde a busca ser&aacute; feita.
     * @param fim Posi&ccedil;&atilde;o final dentro do vetor onde a busca ser&aacute; feita.
     * @param n Valor inteiro que ser&aacute; pesquisado no vetor.
     * @return &Iacute;ndice do elemento no vetor, ou
     * <code>-1</code>, caso o valor n&atilde;o tenha sido encontrado.
     */
    public static int buscaBinariaRec(int[] v, int ini, int fim, int n) {

        return 1;
    }
    
    /**
     * Realiza a ordena&ccedil;&atilde;o de um vetor (completamente preenchido) de
     * inteiros, usando o algoritmo <i>Bubble Sort</i>.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>bubbleSort</code> para um vetor parcial.
     * @param v Vetor que ser&aacute; ordenado.
     */
    public static void bubbleSort(int[] v) {
        bubbleSort(v,v.length);
    }
    
    /**
     * Realiza a ordena&ccedil;&atilde;o de um vetor parcial (parcialmente
     * preenchido) de inteiros, usando o algoritmo <i>Bubble Sort</i>.
     * @param v Vetor que ser&aacute; ordenado.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     */
    public static void bubbleSort(int[] v, int t) {
        int aux = 0; int i = 0;
        for(i = 0; i<t; i++){
            for(int j = 0; j<t -1; j++){
                if(v[j] > v[j + 1]){
                    aux = v[j];
                    v[j] = v[j+1];
                    v[j+1] = aux;
                }
            }
        }
    }

    /**
     * Realiza a ordena&ccedil;&atilde;o de um vetor (completamente preenchido) de
     * inteiros, usando o algoritmo <i>Selection Sort</i>.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>selectionSort</code> para um vetor parcial.
     * @param v Vetor que ser&aacute; ordenado.
     */
    public static void selectionSort(int[] v) {
        bubbleSort(v,v.length);
    }
    
    /**
     * Realiza a ordena&ccedil;&atilde;o de um vetor parcial (parcialmente
     * preenchido) de inteiros, usando o algoritmo <i>Selection Sort</i>.
     * @param v Vetor que ser&aacute; ordenado.
     * @param t Tamanho do vetor parcial (ou seja, n&uacute;mero de elementos que ser&atilde;o considerados).
     */
    public static void selectionSort(int[] v, int t) {
        int posmenor; int aux;
        for (int i = 0; i < t; i++){
            posmenor = i;
            for (int j = i + 1; j < t; j++){
                if(v[j] < v[posmenor]){
                    posmenor = j;
                }
            }
            aux = v[posmenor];
            v[posmenor] = v[i];
            v[i] = aux;
        }
      
    }
    
    /**
     * Realiza a remo&ccedil;&atilde;o de um elemento de um vetor parcial (parcialmente
     * preenchido) de inteiros, copiando todos os elementos que est&atilde;o depois do elemento a ser removido,
     * uma posi&ccedil;&atilde;o em dire&ccedil;&atilde;o ao in&iacute;cio do vetor.
     * @param v Vetor do qual o elemento ser&aacute; removido.
     * @param t Tamanho atual do vetor parcial.
     * @param pos Posi&acedil;&atilde;o (ou &iacute;ndice) do elemento que ser&aacute; removido.
     * @return Retorna o novo tamanho do vetor parcial (ou seja, o tamanho anterior menos um), ou ou mesmo tamanho
     * que foi recebido, caso n&atilde;o seja poss&iacute;vel fazer a remo&ccedil;&atilde;o.
     */
    public static int removeElemento(int[] v, int tam, int pos) {

        return -1; 
    }
    
    /**
     * Realiza a inser&ccedil;&atilde;o de um elemento no final um vetor parcial (parcialmente
     * preenchido) de inteiros.
     * @param v Vetor no qual o elemento ser&aacute; inserido.
     * @param t Tamanho atual do vetor parcial.
     * @param n Valor inteiro a ser inserido no vetor.
     * @return Retorna o novo tamanho do vetor parcial (ou seja, o tamanho anterior mais um), ou mesmo tamanho
     * que foi recebido, caso n&atilde;o seja poss&iacute;vel fazer a inser&ccedil;&atilde;o.
     */
    public static int insereNoFinal(int[] v, int tam, int n) {
        int [] v2 = new int[tam+1];
        for(int i  = 0; i < tam; i++){
            v2[i] = i;
        }
        v2[v2.length-1] = n;
        tam++;
        return tam;
    }

    /**
     * Realiza a inser&ccedil;&atilde;o de um elemento em uma posi&ccedil;&atilde;o espec&iacute;fica
     * em um vetor parcial (parcialmente preenchido) de inteiros. A partir da posi&ccedil;&atilde;o
     * especificada, at&eacute; o final do vetor, todos os elementos s&atilde;o deslocados uma posi&ccedil;o
     * em dire&ccedil;&atilde;o ao final do vetor, para que o novo valor seja inserido nessa posi&ccedil;&atilde;o.
     * @param v Vetor no qual o elemento ser&aacute; inserido.
     * @param t Tamanho atual do vetor parcial.
     * @param pos Posi&ccedil;&atilde;o (ou &iacute;ndice) do vetor onde o valor dever&aacute; ser inserido.
     * @param n Valor inteiro a ser inserido no vetor.
     * @return Retorna o novo tamanho do vetor parcial (ou seja, o tamanho anterior mais um), ou mesmo tamanho
     * que foi recebido, caso n&atilde;o seja poss&iacute;vel fazer a inser&ccedil;&atilde;o.
     */
    public static int insere(int[] v, int tam, int pos, int n) {
        for(int i = 0; i < tam; i++){
            if(i == pos){
                v[i] = n;
                return tam +1; 
            }
        }
        return tam; 
    }
}
