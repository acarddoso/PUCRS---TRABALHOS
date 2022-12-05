import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Placar {
    private String[] n;
    private int[] pontos; private int num_linhas; private int tamanho_placar;
 
    
    public Placar(String arquivo, int tam) throws FileNotFoundException {
        num_linhas = 0;
        int nPlacar = 10;
        tamanho_placar = tam;
        n = new String[tamanho_placar];
        pontos = new int[tamanho_placar];

        File arquivo_p = new File(arquivo);
        if(arquivo_p.exists()) {
            Scanner sc_file = new Scanner(arquivo_p);
            while(sc_file.hasNextLine()) {
                String linha = sc_file.nextLine();
                String[] campos = linha.split(";");
                if(campos.length != 2) {
                    System.out.println("> Placar: arquivo " + arquivo + " invalido...");
                    sc_file.close();
                    return;
                } else {
                    n[num_linhas] = campos[0].trim();
                    pontos[num_linhas] = Integer.parseInt(campos[1].trim());
                    num_linhas++;
                }
            }
            sc_file.close();
        }
    }
    
    public void salvar_arquivo() throws FileNotFoundException, IOException {
        FileWriter arquivo = new FileWriter("ranking.csv");
        PrintWriter n_arquivo = new PrintWriter(arquivo);
        for(int i = 0; i < num_linhas; i++) {
            n_arquivo.printf("%s;%d",n[i],pontos[i]);
            if(i != num_linhas-1){ 
                n_arquivo.println();
            }
        }
        n_arquivo.close();
    }

    public void mostrar() throws FileNotFoundException {
        System.out.println("\n----------Ranking----------");
        System.out.printf("%-18s%s","Jogador:","Pontos:\n");
        for(int i = 0; i < num_linhas; i++) {
            System.out.printf("%2d. %-15s- %3d\n", i+1, n[i], pontos[i]);
        }
        System.out.println("--------------------------\n");
    }

    public boolean tem() {
        if(num_linhas != 0){
            return true;
        } else return false;
    }

    public boolean adiciona_pontuacao(String nome, int pontuacao_nova) {
        if(tem()) {
            for(int i = 0; i < tamanho_placar && i < num_linhas+1; i++) {
                if(pontuacao_nova < pontos[i]) {
                    for(int j = num_linhas; j > i; j--) {
                        if(j != tamanho_placar) {
                            n[j] = n[j-1];
                            pontos[j] = pontos[j-1];
                        }
                    }
                    pontos[i] = pontuacao_nova;
                    n[i] = nome;
                    
                    return true;
                }
            }
        }
        else {
            n[num_linhas] = nome;
            pontos[num_linhas] = pontuacao_nova;
            System.out.printf("%s - %d", n[0], pontos[0]);
            num_linhas++;
            return true;
        }
        return false;
    }

}