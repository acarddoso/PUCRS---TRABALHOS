import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LeituraArquivo {

    private static ListaLogradouro lista;

    public static void main(String[] args) throws ParseException {


        String linhas[] = new String[100000];
        int numLinhas = 0;

        Path filePath = Paths.get("dataEditado.csv");


        // Ler o arquivo
        try ( BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                linhas[numLinhas] = line;
                numLinhas++;
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo: ", e.getMessage());
        }
        lista = new ListaLogradouro();
        // Mude numLinhas para algum numero pequeno para executar testes mais rapidamente.
        // Ex:
        // for (int i = 0; i < 50; i++) {
        for (int i = 0; i < numLinhas; i++) {
            String[] campos = linhas[i].split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(campos[0], formatter);
            int anoDataExtracao = dateTime.getYear();
            int mesDataExtracao = dateTime.getMonthValue();
            int diaDataExtracao = dateTime.getDayOfMonth();
            int horaDataExtracao = dateTime.getHour();
            int minDataExtracao = dateTime.getMinute();

            System.out.println("Data e hora extracao: " + diaDataExtracao + "/" + mesDataExtracao + "/" + anoDataExtracao + ", " + horaDataExtracao + ":" + minDataExtracao);

            String descricao = campos[1];
            String estado = campos[2];
            String complemento = campos[3];

            System.out.println("Descricao: " + descricao);
            System.out.println("Estado: " + estado + ", " + complemento);

            int anoImplantacao = 0;
            int mesImplantacao = 0;
            int diaImplantacao = 0;            
            if(!campos[4].equals("")) {
                if(campos[4].contains("-"))
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                else
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(campos[4], formatter);
                anoImplantacao = date.getYear();
                mesImplantacao = date.getMonthValue();
                diaImplantacao = date.getDayOfMonth();
            }
            
            System.out.println("Data implantacao: " + diaImplantacao + "/" + mesImplantacao + "/" + anoImplantacao);

            String logradouro = campos[5].split(" ", 2)[0];
            String nomeLog = campos[5].split(" ", 2)[1];
            System.out.println("Logradouro: " + logradouro + " " + nomeLog);

            double numInicial;
            if(campos[6].equals(""))
                numInicial = 0;
            else
                numInicial = Double.parseDouble(campos[6]);
            
            double numFinal;
            if(campos[7].equals(""))
                numFinal = 0;
            else
                numFinal = Double.parseDouble(campos[7]);
            
            String defronte = campos[8];
            String cruzamento = campos[9];
            String lado = campos[10];
            String fluxo = "";
            if(campos.length>=12)
                fluxo = campos[11];
            String localInstalacao = "";
            if(campos.length>=13)
                localInstalacao = campos[12];

            System.out.println("Num inicial e final: " + numInicial + ", " + numFinal + "; "
                    + defronte + "; " + cruzamento + "; " + lado + "; " + fluxo + "; " + localInstalacao);
            System.out.println("---------------------------------------> " + i);

            Logradouro novoLog = new Logradouro(logradouro, nomeLog);

            if (lista.add(novoLog)){

                String dataString = anoImplantacao + "-" + mesImplantacao + "-" + diaImplantacao;
                LocalDate data = null;
                if (!dataString.equals("0-0-0")) {
                    data = parseData(dataString);
                }
                Sinalizacao sinalizacao = new Sinalizacao(localInstalacao, data);
                novoLog.adicionarSinalizacao(sinalizacao);
            }
            else {
                String dataString = anoImplantacao + "-" + mesImplantacao + "-" + diaImplantacao;
                LocalDate data = null;
                if (!dataString.equals("0-0-0")) {
                    data = parseData(dataString);
                }
                Sinalizacao sinalizacao = new Sinalizacao(localInstalacao, data);
                lista.consultarLogradouroPorNome(novoLog.getNome()).adicionarSinalizacao(sinalizacao);
            }
        }
        System.out.println(lista.toString());

        System.out.println("-------------------------------------------------------------");
        System.out.println("Escolha a opcao desejada: ");
        System.out.println("1. Apresentar o nome da rua/av/trav que tem mais sinalizacoes registradas.");
        System.out.println("2. Apresentar o mes em que mais foram implantadas mais sinalizacoes em uma rua/av/trav.");
        System.out.println("3. Entrar em modo de navegacao entre logradouros.");
        System.out.println("4. Sair do sistema.");
        System.out.println("-------------------------------------------------------------");

        Scanner in = new Scanner(System.in);

        String opcaoString = in.nextLine();

        while(!verificarNumerico(opcaoString)) {
            System.out.println("Insira um valor inteiro como opcao.");
            opcaoString = in.nextLine();
        }

        int opcao = Integer.parseInt(opcaoString);

        while (opcao != 4) {

            switch (opcao) {
                case 1:
                    Logradouro maiorLog = lista.getMaisSinalizacoes();
                    System.out.println("O logradouro com mais sinalizacoes registradas eh o: " + maiorLog.getNomeCompleto());
                    System.out.println("Com o total de " + maiorLog.getQtdSinalizacoes());
                    break;
                case 2:
                    System.out.println("O mes em que foram implementadas mais sinalizacoes em uma rua eh o: " + lista.mesComMaisSinalizacoes());
                    break;
                case 3:
                    menuNavegacao();
                    break;
            }
            System.out.println("-------------------------------------------------------------");
            System.out.println("Escolha a opcao desejada: ");
            System.out.println("1. Apresentar o nome da rua/av/trav que tem mais sinalizacooes registradas.");
            System.out.println("2. Apresentar o mes em que mais foram implantadas mais sinalizacoes em uma rua/av/trav.");
            System.out.println("3. Entrar em modo de navegacao entre logradouros.");
            System.out.println("4. Sair do sistema.");
            System.out.println("-------------------------------------------------------------");

            opcaoString = in.nextLine();

            while(!verificarNumerico(opcaoString)) {
                System.out.println("Insira um valor inteiro como opcao.");
                opcaoString = in.nextLine();
            }

            opcao = Integer.parseInt(opcaoString);

        }

    }

    public static void menuNavegacao(){

        Logradouro primeirolog = lista.getInicio().getProximo();

        System.out.println("O primeiro logradouro da lista eh o: " + primeirolog.getNome());
        System.out.println("Contem um total de " + primeirolog.getQtdSinalizacoes() + " sinalizacoes.");
        System.out.println("A sinalizacao mais antiga tem a data de " + primeirolog.obterSinalizacaoMenorData().getData());
        System.out.println("A sinalizacao mais nova tem a data de " + primeirolog.obterSinalizacaoMaiorData().getData());
        System.out.println("-------------------------------------------------------------");
        System.out.println("Escolha a opcao desejada de navegacao: ");
        System.out.println("1. Apresentar o proximo logradouro.");
        System.out.println("2. Apresentar o logradouro anterior.");
        System.out.println("3. Sair do modo navegacao.");
        System.out.println("-------------------------------------------------------------");

        Scanner in = new Scanner(System.in);
        String opcaoString = in.nextLine();
        while(!verificarNumerico(opcaoString)) {
            System.out.println("Insira um valor inteiro como opcao.");
            opcaoString = in.nextLine();
        }
        int opcao = Integer.parseInt(opcaoString);

        Logradouro actualLog = primeirolog;

        while (opcao != 3) {

            switch (opcao) {
                case 1:
                    if (actualLog.getProximo().getNome() == null) {
                        System.out.println("Voce chegou ao final da lista.");
                        break;
                    }
                    else {
                        actualLog = actualLog.getProximo();
                    }

                    break;
                case 2:
                    if (actualLog.getAnterior().getNome() == null) {
                        System.out.println("O logradouro atual não tem valor anterior.");
                        break;
                    }
                    else {
                        actualLog = actualLog.getAnterior();
                    }

                    break;
            }
            System.out.println("-------------------------------------------------------------");
            System.out.println("O logradouro atual eh o: " + actualLog.getNome());
            System.out.println("Contehm um total de " + actualLog.getQtdSinalizacoes() + " sinalizacoes.");
            System.out.println("A sinalizacao mais antiga tem a data de " + actualLog.obterSinalizacaoMenorData().getData());
            System.out.println("A sinalizacao mais nova tem a data de " + actualLog.obterSinalizacaoMaiorData().getData());
            System.out.println("-------------------------------------------------------------");
            System.out.println("Escolha a opcao desejada de navegacao: ");
            System.out.println("1. Apresentar o proximo logradouro.");
            System.out.println("2. Apresentar o logradouro anterior.");
            System.out.println("3. Sair do modo navegacao.");

            opcaoString = in.nextLine();
            while(!verificarNumerico(opcaoString)) {
                System.out.println("Insira um valor inteiro como opcao.");
                opcaoString = in.nextLine();
            }
            opcao = Integer.parseInt(opcaoString);

        }



    }


    public static LocalDate parseData(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(dataString, formatter);
    }

    public static boolean verificarNumerico(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
