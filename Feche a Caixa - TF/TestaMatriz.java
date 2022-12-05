import java.util.Scanner;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * TestaMatriz.java
 *
 * @author Roland Teodorowitsch
 * @version 28 out. 2022
 */
public class TestaMatriz {

    private final static boolean advertencias = true;
    
    private static int[][] m1 = {
        { 1, 2, 3, 4, 5},
        { 6, 7, 8, 9,10},
        {11,12,13,14,15},
        {16,17,18,19,20},
        {21,22,23,24,25}
    };
        
    private static int[][] m2 = {
        { 1, 2, 3, 4},
        { 5, 6, 7, 8},
        { 9,10,11,12},
        {13,14,15,16}
    };
        
    private static int[][] m3 = {
        { 1, 2, 3, 4, 5},
        { 6, 7, 8, 9,10},
        {11,12,13,14,15},
        {16,17,18,19,20}
    };
        
    private static int[][] m4 = {
        { 1, 2, 3, 4},
        { 5, 6, 7, 8},
        { 9,10,11,12},
        {13,14,15,16},
        {17,18,19,20}
    };

    private static int[][] m3t = {
        { 1, 6,11,16},
        { 2, 7,12,17},
        { 3, 8,13,18},
        { 4, 9,14,19},
        { 5,10,15,20}
    };
        
    private static int[][] m4t = {
        { 1, 5, 9,13,17},
        { 2, 6,10,14,18},
        { 3, 7,11,15,19},
        { 4, 8,12,16,20}
    };
    
    private static int[][] m5 = {
      { 1, 6, 7, 8, 9},
      { 6, 2,10,11,12},
      { 7,10, 3,13,14},
      { 8,11,13, 4,15},
      { 9,12,14,15, 5}
    };

    private static int[][] m6 = {
      { 1, 5, 6, 7},
      { 5, 2, 8, 9},
      { 6, 8, 3,10},
      { 7, 9,10, 4}
    };

    private static boolean contemMesmosValores(String s1, String s2) {
        Scanner sc1 = new Scanner(s1);
        Scanner sc2 = new Scanner(s2);
        while (sc1.hasNextInt()) {
                if (!sc2.hasNextInt())
                    return false;
                int i1 = sc1.nextInt();
                int i2 = sc2.nextInt();
                if (i1 != i2)
                    return false;
        }
        if (sc2.hasNextInt())
            return false;
        return true;
    }
    
    private static boolean ehIgual(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) return false;
        for (int i=0; i<m1.length; ++i) {
            if (m1[i].length != m2[i].length) return false;
            for (int j=0; j<m1[i].length; ++j)
                if (m1[i][j] != m2[i][j])
                    return false;
        }
        return true;
    }

    public static boolean testaLe() {
        boolean res = true, teste;
        String leitura = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20";
        int[][] mat3 = new int[4][5];
        Matriz.le(new Scanner(leitura),mat3);
        teste = ehIgual(mat3,m3);
        if (!teste && advertencias) System.out.println("> Matriz.le(new Scanner(leitura),mat3) falhou...");
        res = res && teste;
        int[][] mat4 = new int[5][4];
        Matriz.le(new Scanner(leitura),mat4);
        teste = ehIgual(mat4,m4);
        if (!teste && advertencias) System.out.println("> Matriz.le(new Scanner(leitura),mat4) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaImprime() {
        boolean res = true, teste;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        
        baos.reset();
        Matriz.imprime(out,m1);
        teste = contemMesmosValores(baos.toString(),"1 2 3 4 5\n6 7 8 9 10\n11 12 13 14 15\n16 17 18 19 20\n21 22 23 24 25\n");
        if (!teste && advertencias) System.out.println("> Matriz.imprime(out,m1) falhou...");
        res = res && teste;
        baos.reset();
        Matriz.imprime(out,m2);
        teste = contemMesmosValores(baos.toString(),"1 2 3 4\n5 6 7 8\n9 10 11 12\n13 14 15 16\n");
        if (!teste && advertencias) System.out.println("> Matriz.imprime(out,m2) falhou...");
        res = res && teste;
        baos.reset();
        Matriz.imprime(out,m3);
        teste = contemMesmosValores(baos.toString(),"1 2 3 4 5\n6 7 8 9 10\n11 12 13 14 15\n16 17 18 19 20\n");
        if (!teste && advertencias) System.out.println("> Matriz.imprime(out,m3) falhou...");
        res = res && teste;
        baos.reset();
        Matriz.imprime(out,m4);
        teste = contemMesmosValores(baos.toString(),    "1 2 3 4\n5 6 7 8\n9 10 11 12\n13 14 15 16\n17 18 19 20\n");
        if (!teste && advertencias) System.out.println("> Matriz.imprime(out,m4) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaTrocaLinhas() {
        boolean res = true, teste;
        int lin;

        int[][] g3 = {
            { 1, 2, 3, 4, 5},
            { 6, 7, 8, 9,10},
            {11,12,13,14,15},
            {16,17,18,19,20}
        };
        lin = 1;
        for (int i=-1; i<=m3.length; ++i) {
            if (i!=m3.length && i!=-1) {
                for (int j=0; j<m3[i].length; ++j) {
                    int aux = m3[i][j];
                    m3[i][j] = m3[lin][j];
                    m3[lin][j] = aux;
                }
            }
            teste = Matriz.trocaLinhas(m3,lin,i);
            if (i==m3.length || i==-1) teste = !teste;
            teste = teste && ehIgual(m3,g3);
            if (!teste && advertencias) System.out.println("> Matriz.trocaLinhas(m3,"+lin+","+i+") falhou...");
            res = res && teste;
        }
        
        int[][] g4 = {
            { 1, 2, 3, 4},
            { 5, 6, 7, 8},
            { 9,10,11,12},
            {13,14,15,16},
            {17,18,19,20}
        };
        lin = 2;
        for (int i=-1; i<=m4.length; ++i) {
            if (i!=m4.length && i!=-1) {
                for (int j=0; j<m4[i].length; ++j) {
                    int aux = m4[i][j];
                    m4[i][j] = m4[lin][j];
                    m4[lin][j] = aux;
                }
            }
            teste = Matriz.trocaLinhas(m4,i,lin);
            if (i==m4.length|| i==-1) teste = !teste;
            teste = teste && ehIgual(m4,g4);
            if (!teste && advertencias) System.out.println("> Matriz.trocaLinhas(m4,"+i+","+lin+") falhou...");
            res = res && teste;
        }
        
        return res;
    }
    
    public static boolean testaTrocaColunas() {
        boolean res = true, teste;
        int col;

        int[][] g3 = {
            { 1, 2, 3, 4, 5},
            { 6, 7, 8, 9,10},
            {11,12,13,14,15},
            {16,17,18,19,20}
        };
        col = 1;
        for (int j=-1; j<=m3[0].length; ++j) {
            if (j!=m3[0].length && j!=-1) {
                for (int i=0; i<m3.length; ++i) {
                    int aux = m3[i][j];
                    m3[i][j] = m3[i][col];
                    m3[i][col] = aux;
                }
            }
            teste = Matriz.trocaColunas(m3,col,j);
            if (j==m3[0].length || j==-1) teste = !teste;
            teste = teste && ehIgual(m3,g3);
            if (!teste && advertencias) System.out.println("> Matriz.trocaLinhas(m3,"+col+","+j+") falhou...");
            res = res && teste;
        }
        
        int[][] g4 = {
            { 1, 2, 3, 4},
            { 5, 6, 7, 8},
            { 9,10,11,12},
            {13,14,15,16},
            {17,18,19,20}
        };
        col = 2;
        for (int j=-1; j<=m4[0].length; ++j) {
            if (j!=m4[0].length && j!=-1) {
                for (int i=0; i<m4.length; ++i) {
                    int aux = m4[i][j];
                    m4[i][j] = m4[i][col];
                    m4[i][col] = aux;
                }
            }
            teste = Matriz.trocaColunas(m4,j,col);
            if (j==m4[0].length || j==-1) teste = !teste;
            teste = teste && ehIgual(m4,g4);
            if (!teste && advertencias) System.out.println("> Matriz.trocaLinhas(m4,"+j+","+col+") falhou...");
            res = res && teste;
        }
        
        return res;
    }
    
    public static boolean testaPreencheSequencialmente() {
        boolean res = true, teste;
        int[][] m1s = new int[5][5];
        Matriz.preencheSequencialmente(m1s);
        teste = ehIgual(m1,m1s);
        if (!teste && advertencias) System.out.println("> Matriz.preencheSequencialmente(m1s) falhou...");
        res = res && teste;
        int[][] m2s = new int[4][4];
        Matriz.preencheSequencialmente(m2s);
        teste = ehIgual(m2,m2s);
        if (!teste && advertencias) System.out.println("> Matriz.preencheSequencialmente(m2s) falhou...");
        res = res && teste;
        int[][] m3s = new int[4][5];
        Matriz.preencheSequencialmente(m3s);
        teste = ehIgual(m3,m3s);
        if (!teste && advertencias) System.out.println("> Matriz.preencheSequencialmente(m3s) falhou...");
        res = res && teste;
        int[][] m4s = new int[5][4];
        Matriz.preencheSequencialmente(m4s);
        teste = ehIgual(m4,m4s);
        if (!teste && advertencias) System.out.println("> Matriz.preencheSequencialmente(m4s) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaPreenche() {
        boolean res = true, teste;
        final int LIN = 12;
        final int COL = 34;
        final int PADRAO1 = 0xaa55;
        final int PADRAO2 = 0x55aa;
        int[][] mat = new int[LIN][COL];
        Matriz.preenche(mat,PADRAO1);
        teste = true;
        for (int i=0; i<LIN; ++i) {
            for (int j=0; j<COL; ++j) {
                teste = teste && mat[i][j] == PADRAO1;
            }
        }
        if (!teste && advertencias) System.out.println("> Matriz.preenche(mat,PADRAO1) falhou...");
        res = res && teste;
        Matriz.preenche(mat,PADRAO2);
        teste = true;
        for (int i=0; i<LIN; ++i) {
            for (int j=0; j<COL; ++j) {
                teste = teste && mat[i][j] == PADRAO2;
            }
        }
        if (!teste && advertencias) System.out.println("> Matriz.preenche(mat,PADRAO2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSoma() {
        boolean res = true, teste;
        teste = Matriz.soma(m1)==325;
        if (!teste && advertencias) System.out.println("> Matriz.soma(m1) falhou...");
        res = res && teste;
        teste =  Matriz.soma(m2)==136;
        if (!teste && advertencias) System.out.println("> Matriz.soma(m2) falhou...");
        res = res && teste;
        return res;
    }

    public static boolean testaSomaLinha() {
        boolean res = true, teste;
        teste = Matriz.somaLinha(m1,0)==15;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m1,0) falhou...");
        res = res && teste;
        teste = Matriz.somaLinha(m1,1)==40;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m1,1) falhou...");
        res = res && teste;
        teste = Matriz.somaLinha(m1,2)==65;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m1,2) falhou...");
        res = res && teste;
        teste = Matriz.somaLinha(m1,3)==90;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m1,3) falhou...");
        res = res && teste;
        teste = Matriz.somaLinha(m1,4)==115;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m1,4) falhou...");
        res = res && teste;
        teste =  Matriz.somaLinha(m2,0)==10;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m2,0) falhou...");
        res = res && teste;
        teste =  Matriz.somaLinha(m2,1)==26;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m2,1) falhou...");
        res = res && teste;
        teste =  Matriz.somaLinha(m2,2)==42;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m2,2) falhou...");
        res = res && teste;
        teste =  Matriz.somaLinha(m2,3)==58;
        if (!teste && advertencias) System.out.println("> Matriz.somaLinha(m2,3) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSomaColuna() {
        boolean res = true, teste;
        teste = Matriz.somaColuna(m1,0)==55;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m1,0) falhou...");
        res = res && teste;
        teste = Matriz.somaColuna(m1,1)==60;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m1,1) falhou...");
        res = res && teste;
        teste = Matriz.somaColuna(m1,2)==65;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m1,2) falhou...");
        res = res && teste;
        teste = Matriz.somaColuna(m1,3)==70;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m1,3) falhou...");
        res = res && teste;
        teste = Matriz.somaColuna(m1,4)==75;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m1,4) falhou...");
        res = res && teste;
        teste =  Matriz.somaColuna(m2,0)==28;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m2,0) falhou...");
        res = res && teste;
        teste =  Matriz.somaColuna(m2,1)==32;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m2,1) falhou...");
        res = res && teste;
        teste =  Matriz.somaColuna(m2,2)==36;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m2,2) falhou...");
        res = res && teste;
        teste =  Matriz.somaColuna(m2,3)==40;
        if (!teste && advertencias) System.out.println("> Matriz.somaColuna(m2,3) falhou...");
        res = res && teste;
        return res;
    }

    public static boolean testaSomaDiagonalPrincipal() {
        boolean res = true, teste;
        teste = Matriz.somaDiagonalPrincipal(m1)==65;
        if (!teste && advertencias) System.out.println("> Matriz.somaDiagonalPrincipal(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaDiagonalPrincipal(m2)==34;
        if (!teste && advertencias) System.out.println("> Matriz.somaDiagonalPrincipal(m2) falhou...");
        res = res && teste;
        return res;
    }
    
    
    public static boolean testaSomaAcimaDiagonalPrincipal() {
        boolean res = true, teste;
        teste = Matriz.somaAcimaDiagonalPrincipal(m1)==90;
        if (!teste && advertencias) System.out.println("> Matriz.somaAcimaDiagonalPrincipal(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaAcimaDiagonalPrincipal(m2)==36;
        if (!teste && advertencias) System.out.println("> Matriz.somaAcimaDiagonalPrincipal(m2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSomaAbaixoDiagonalPrincipal() {
        boolean res = true, teste;
        teste = Matriz.somaAbaixoDiagonalPrincipal(m1)==170;
        if (!teste && advertencias) System.out.println("> Matriz.somaAbaixoDiagonalPrincipal(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaAbaixoDiagonalPrincipal(m2)==66;
        if (!teste && advertencias) System.out.println("> Matriz.somaAbaixoDiagonalPrincipal(m2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSomaDiagonalSecundaria() {
        boolean res = true, teste;
        teste = Matriz.somaDiagonalSecundaria(m1)==65;
        if (!teste && advertencias) System.out.println("> Matriz.somaDiagonalSecundaria(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaDiagonalSecundaria(m2)==34;
        if (!teste && advertencias) System.out.println("> Matriz.somaDiagonalSecundaria(m2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSomaAcimaDiagonalSecundaria() {
        boolean res = true, teste;
        teste = Matriz.somaAcimaDiagonalSecundaria(m1)==70;
        if (!teste && advertencias) System.out.println("> Matriz.somaAcimaDiagonalSecundaria(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaAcimaDiagonalSecundaria(m2)==26;
        if (!teste && advertencias) System.out.println("> Matriz.somaAcimaDiagonalSecundaria(m2) falhou...");
        res = res && teste;
        return res;
    }

    public static boolean testaSomaAbaixoDiagonalSecundaria() {
        boolean res = true, teste;
        teste = Matriz.somaAbaixoDiagonalSecundaria(m1)==190;
        if (!teste && advertencias) System.out.println("> Matriz.somaAbaixoDiagonalSecundaria(m1) falhou...");
        res = res && teste;
        teste =  Matriz.somaAbaixoDiagonalSecundaria(m2)==76;
        if (!teste && advertencias) System.out.println("> Matriz.somaAbaixoDiagonalSecundaria(m2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaMaiorValor() {
        final int DIM1 = 10;
        final int DIM2 = 20;
        final int LIM1 = 1000;
        final int LIM2 = 10000;
        java.util.Random r = new java.util.Random();
        int[][] mat1 = new int[DIM1][DIM2];
        int[][] mat2 = new int[DIM2][DIM1];
        for (int i=0; i<DIM1; ++i) {
            for (int j=0; j<DIM2; ++j) {
                mat1[i][j] = r.nextInt(LIM1);
                mat2[j][i] = LIM2/2 - r.nextInt(LIM2);
            }
        }
        int maior1 = mat1[0][0];
        int maior2 = mat2[0][0];
        for (int i=0; i<DIM1; ++i) {
            for (int j=0; j<DIM2; ++j) {
                if (mat1[i][j] > maior1) maior1 = mat1[i][j];
                if (mat2[j][i] > maior2) maior2 = mat2[j][i];
            }
        }
        boolean res = true, teste;
        teste = Matriz.maiorValor(mat1)==maior1;
        if (!teste && advertencias) System.out.println("> Matriz.maiorValor(mat1) falhou...");
        res = res && teste;
        teste =  Matriz.maiorValor(mat2)==maior2;
        if (!teste && advertencias) System.out.println("> Matriz.maiorValor(mat2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaMenorValor() {
        final int DIM1 = 10;
        final int DIM2 = 20;
        final int LIM1 = 1000;
        final int LIM2 = 10000;
        java.util.Random r = new java.util.Random();
        int[][] mat1 = new int[DIM1][DIM2];
        int[][] mat2 = new int[DIM2][DIM1];
        for (int i=0; i<DIM1; ++i) {
            for (int j=0; j<DIM2; ++j) {
                mat1[i][j] = r.nextInt(LIM1);
                mat2[j][i] = LIM2/2 - r.nextInt(LIM2);
            }
        }
        int menor1 = mat1[0][0];
        int menor2 = mat2[0][0];
        for (int i=0; i<DIM1; ++i) {
            for (int j=0; j<DIM2; ++j) {
                if (mat1[i][j] < menor1) menor1 = mat1[i][j];
                if (mat2[j][i] < menor2) menor2 = mat2[j][i];
            }
        }
        boolean res = true, teste;
        teste = Matriz.menorValor(mat1)==menor1;
        if (!teste && advertencias) System.out.println("> Matriz.menorValor(mat1) falhou...");
        res = res && teste;
        teste =  Matriz.menorValor(mat2)==menor2;
        if (!teste && advertencias) System.out.println("> Matriz.menorValor(mat2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaTransposta() {
        boolean res = true, teste;
        int[][] transp;
        transp = Matriz.transposta(m3);
        teste = ehIgual(transp,m3t);
        if (!teste && advertencias) System.out.println("> Matriz.transposta(m3) falhou...");
        res = res && teste;
        transp =  Matriz.transposta(m4);
        teste = ehIgual(transp,m4t);
        if (!teste && advertencias) System.out.println("> Matriz.transposta(m4) falhou...");
        res = res && teste;
        return res;
    }

    public static boolean testaEhSimetrica() {
        boolean res = true, teste;
        teste = Matriz.ehSimetrica(m5);
        if (!teste && advertencias) System.out.println("> Matriz.ehSimetrica(m5) falhou...");
        res = res && teste;
        for (int i=0; i<m5.length-1; ++i) {
            for (int j=i+1; j<m5.length; ++j) {
                int aux = m5[i][j];
                m5[i][j] = 100;
                teste = !Matriz.ehSimetrica(m5);
                if (!teste && advertencias) System.out.println("> Matriz.ehSimetrica(m5) falhou...");
                res = res && teste;
                m5[i][j] = aux;
            }
        }
        teste = Matriz.ehSimetrica(m6);
        if (!teste && advertencias) System.out.println("> Matriz.ehSimetrica(m6) falhou...");
        res = res && teste;
        for (int i=0; i<m6.length-1; ++i) {
            for (int j=i+1; j<m6.length; ++j) {
                int aux = m6[i][j];
                m6[i][j] = 100;
                teste = !Matriz.ehSimetrica(m6);
                if (!teste && advertencias) System.out.println("> Matriz.ehSimetrica(m6) falhou...");
                res = res && teste;
                m6[i][j] = aux;
            }
        }
        return res;
    }
    
    public static boolean testaMedia() {
        boolean res = true, teste;
        teste = Matriz.media(m1)==13.0;
        if (!teste && advertencias) System.out.println("> Matriz.media(m1) falhou...");
        res = res && teste;
        teste = Matriz.media(m2)==8.5;
        if (!teste && advertencias) System.out.println("> Matriz.media(m2) falhou...");
        res = res && teste;
        teste = Matriz.media(m3)==10.5;
        if (!teste && advertencias) System.out.println("> Matriz.media(m3) falhou...");
        res = res && teste;
        teste = Matriz.media(m4)==10.5;
        if (!teste && advertencias) System.out.println("> Matriz.media(m4) falhou...");
        res = res && teste;
        return res;
    }
        
    public static void main(String[] args) {
        System.out.println("Teste para Matriz.le():                           " + (testaLe()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.imprime():                      " + (testaImprime()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.trocaLinhas():                  " + (testaTrocaLinhas()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.trocaColunas():                 " + (testaTrocaColunas()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.preencheSequencialmente():      " + (testaPreencheSequencialmente()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.preenche():                     " + (testaPreenche()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.soma():                         " + (testaSoma()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaLinha():                    " + (testaSomaLinha()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaColuna():                   " + (testaSomaColuna()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaDiagonalPrincipal():        " + (testaSomaDiagonalPrincipal()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaAcimaDiagonalPrincipal():   " + (testaSomaAcimaDiagonalPrincipal()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaAbaixoDiagonalPrincipal():  " + (testaSomaAbaixoDiagonalPrincipal()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaDiagonalSecundaria():       " + (testaSomaDiagonalSecundaria()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaAcimaDiagonalSecundaria():  " + (testaSomaAcimaDiagonalSecundaria()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.somaAbaixoDiagonalSecundaria(): " + (testaSomaAbaixoDiagonalSecundaria()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.maiorValor():                   " + (testaMaiorValor()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.menorValor():                   " + (testaMenorValor()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.transposta():                   " + (testaTransposta()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.ehSimetrica():                  " + (testaEhSimetrica()?"OK":"ERRO"));
        System.out.println("Teste para Matriz.media():                        " + (testaMedia()?"OK":"ERRO"));
    }
}
