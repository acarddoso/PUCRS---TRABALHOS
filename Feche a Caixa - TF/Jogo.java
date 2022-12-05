import java.io.FileNotFoundException;
import java.io.IOException;
public class Jogo {
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        FechaCaixa jogo = new FechaCaixa(FechaCaixa.obtem_nome());
            Placar ranking = new Placar("ranking.csv",10);
                if (ranking.tem()) ranking.mostrar();
                        jogo.jogar(ranking);
    
 
    }
}

