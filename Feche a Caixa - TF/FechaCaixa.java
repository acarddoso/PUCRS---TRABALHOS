import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.io.IOException;

public class FechaCaixa{
    private String nome; private String acao;
    private int[] dados; private int pontos; private int lados; private int soma_dados;
    private boolean[] tabuleiro; private boolean[] marcadores;
    private boolean jogo;
    /**
     * Construtor do objeto FechaCaixa;
    */
    public FechaCaixa(String n){
     nome = n;
     acao = "";
     pontos = 0;
     dados = new int[2];
     soma_dados = 0;
     lados = 6;
     jogo = true;
     tabuleiro = new boolean[9]; marcadores = new boolean[9];
    }
           
    /**
     * O metodo central onde o jogo e executado
     * @param Placar pontuacao recebe o objeto de placar
     */

    public void jogar(Placar ranking) throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("\n\n\n------------Feche a Caixa------------\n");
        Scanner sc = new Scanner(System.in);
        System.out.println();

        while(jogo) {
            tabuleiro();
            
            acao(sc);

            if(!verifica_acao()) { 
            } else { 
                switch(acao) {
                    case "R": 
                        if (soma_dados == 0) {
                            gera_dado();
                            soma_dados = dados[0] + dados[1];
                        } else {
                            System.out.println("\n\nVoce ja rolou os dados.\n");
                        }
                        if(jogadas()) {
                                break;
                            } else {
                            if(dados[1] == 0){
                                System.out.printf("Voce obteve o dado %d. ",dados[0]);
                            } else{
                                System.out.printf("Voce obteve os dados [%d] e [%d] = [%d]  ", dados[0], dados[1], soma_dados);
                            System.out.println("\nVoce nao tem jogadas.");
                        }
                        } 
                    case "P": 
                        if (soma_dados != 0) {
                            System.out.println("O valor dos dados foi somado a sua pontuação.");
                            System.out.println();
                            pontos = pontos + soma_dados;
                            soma_dados = 0;
                            dados[0] = 0; dados[1] = 0;
                        }
                        else {System.out.println("\n\nRole os dados antes.\n");}
                        break;
                    case "F": 
                        if(soma_dados != 0) {
                            if(fecha_casa()) {
                                System.out.println("\n\nCasa fechada com sucesso!");
                                soma_dados = 0;
                                dados[0] = 0; dados[1] = 0;
                                for(int i = 0; i < marcadores.length; i++) {
                                    marcadores[i] = false;
                                }
                            } else {System.out.println("\n\nA soma das casas nao e igual a soma dos dados.");}
                        } else {System.out.println("\n\nRole os dados antes.\n");};
                        break;
                    case "S": 
                        System.out.println("Finalizando partida...");
                        jogo = false;
                        break;
                    default: 
                        
                    int casa = (Integer.parseInt(acao)) - 1;
                        if(marcadores[casa]) marcadores[casa] = false;
                        else marcadores[casa] = true;
                        break;
                }
                if(testa_fim()) {
                    System.out.print("Jogo finalizado.");
                    System.out.printf("\nSua pontuacao: %d\n\n", pontos);
                    jogo = false;
                    if(ranking.adiciona_pontuacao(nome, pontos)) {
                        System.out.println("\nPontuação adicionada ao ranking.");
                        ranking.salvar_arquivo();
                    }
                    else {
                        System.out.println("\nInfelizmente sua pontuação não entrou no ranking.");
                    }
                    ranking.mostrar();
                }
            }
        }
        sc.close();
        System.out.println();
        System.out.print("FIM...");
    }




    /**
     * Metodo para gerar os valores de um dado
     * @param (int lados) para verificar se tem um ou dois lados
     * @return valores dos dados
     */
    public int rola_dado(int lados) {
        return (int)(Math.random() * lados) + 1;
    }

    /**
     * Metodo para verificar se o dado vai ter 2 lados
     * * @return (boolean) se tem dois lados
     */
    public boolean doisDados() {
        for (int i = lados; i < tabuleiro.length; i++) {
            if (!tabuleiro[i]){
            return true;
            }
        }
        return false;
    }

    /**
     * Metodo atribuir os valores dos dados em vetor[2] ou um vetor[1]
     */
    public void gera_dado(){
        
            dados[0] = rola_dado(lados);
            if(doisDados()){
                dados[1] = rola_dado(lados);
            } else {
                dados[1] = 0;
            }
    }

    
    /**
     * Metodo para verificar se o jogador ainda tem jogadas possiveis
     * @return (boolean = false) se nao tiver nehuma combinação de casas possiveis com a soma dos dado que ele tem
     */
    public boolean jogadas() {
        if(soma_dados <= 9 && !tabuleiro[soma_dados -1]) {
            return true;
        } else {
            for(int i = 1; i < 5; i++) {
                for(int j = i+1; j <= 9 && i+j <= 12; j++) {
                    if(j + i == soma_dados && !tabuleiro[j-1] && !tabuleiro[i-1]){ 
                    return true;
                    }
                    for(int k = j + 1; k <= 9 && i + j + k <= 12; k++) {
                        if(j + i + k == soma_dados && !tabuleiro[j-1] && !tabuleiro[i-1] && !tabuleiro[k-1]){ 
                        return true;
                        }
                        for(int l = k+1; l <= 6 && i+j+k+l <= 12; l++) {
                            if(i + j + k + l == soma_dados && !tabuleiro[j-1] && !tabuleiro[i-1] && !tabuleiro[k-1] && !tabuleiro[l-1]){ 
                            return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * Metodo para somar o valor das casas que os marcoderes estao e comparar com a soma dos dados
     * @return (boolean= true) se os valores forem iguais
     */
    public boolean fecha_casa() {
    int soma_marcadores = 0;
    for(int i = 0; i < tabuleiro.length; i++) {
        if(marcadores[i]){ 
            soma_marcadores = soma_marcadores + i + 1; 
        }
    }
    if(soma_marcadores == soma_dados) { 
        for(int i = 0; i < tabuleiro.length; i++) {
            if(marcadores[i]){ 
                tabuleiro[i] = true;
            } 
        } return true;
    } else {
        return false;
      }

    }
    
    public boolean testa_fim() {
        for(int i = 0; i < tabuleiro.length; i++) { 
            if(!tabuleiro[i]) { 
                return false;
             } 
        }
        return true;
    }

    /**
     * Metodo que recebe a acao do usario e tranforma em int
     * @return (boolean) caso a acao seja invalida
     */
    public boolean verifica_acao() throws InterruptedException {
        if(testa_num(acao)) {
            int casa = Integer.parseInt(acao);
            System.out.println("");
            if(casa >= 1 && casa <= 9) {
                if(tabuleiro[casa-1]) {
                    System.out.println("Esta casa ja foi fechada!\n");
                    return false;
                } else {return true;}
            } else {
                System.out.println("Digite uma casa valida.\n");
                return false;}
        } else {
            switch(acao) {
                case "R": return true;
                case "P": return true;
                case "F": return true;
                case "S": return true;
                default:
                    System.out.println("Digite uma ação válida.\n");
                    return false;
            }
        }
    }

    /**
     * Metodo para imprimir o tabuleiro e informacoes do jogo
     */
    public void tabuleiro() {
        System.out.printf("Jogador:   %s\n", nome);
        System.out.printf("Pontuação:    %d\n", pontos);
        for(int i = 0; i < tabuleiro.length; i++) {
            System.out.print("[");
            if(!tabuleiro[i]) 
                System.out.print(i+1);
             else 
                System.out.print("X");
                System.out.print("]");
        }
        System.out.println("");
        for(int i = 0; i < marcadores.length; i++) {
            if(marcadores[i]){

             System.out.print(" ^ ");
            } else System.out.print(" . ");
        }
        System.out.println("\n-------------------------------------");
        if(dados[0] != 0) {
            System.out.printf("Dados: [%d]", dados[0]);
            if(dados[1] != 0){
                System.out.printf("[%d]", dados[1]);
            System.out.println("");
            }
        }
        System.out.printf("\nSoma dos dados [%d]\n\n", soma_dados);
    }

    /**
     * Metodo para ler a acao do usuario
     * @param (int) via scanner
     */
    public void acao(Scanner in) {
        System.out.print("R = rolar dados, P = passar a vez, F = fechar casas, S = sair, ou digite o numero de uma casa para marcar/desmarcar: \n");
        acao = in.nextLine().toUpperCase().trim();
        //System.out.println("\n\n");
        switch(acao) {
            case "ROLAR": acao = "R";
                break;
            case "PASSAR": acao = "P";
                break;
            case "FECHAR": acao = "F";
                break;
            case "SAIR": acao = "S";
                break;
            default:
                break;
        }
    }

    /**
    * metodo para obter o nome do usuario;
    */
    public static String obtem_nome(){
        System.out.println("Digite seu nome: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
        } 

    public boolean testa_num(String terminal) {
        if(terminal == null) { 
            return false;
        } try {
            int a = Integer.parseInt(terminal);
        } catch(NumberFormatException e){ 
            return false; 
        }
    return true;
    }
}
