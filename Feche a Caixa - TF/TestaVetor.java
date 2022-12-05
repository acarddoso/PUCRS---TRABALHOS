import java.util.Scanner;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * TestaVetor.java
 *
 * @author Roland Teodorowitsch
 * @version 31 out. 2022
 */
public class TestaVetor {

    private final static boolean advertencias = true;
    
    private static int[] v16 = {10,16,4,11,1,14,6,15,3,13,7,9,2,8,12,5};
    private static int[] v16b = {10,16,4,11,1,14,6,15,3,13,7,0,0,0,0,0};
    private static int[] v20 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private static int[] v20b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0,0,0,0,0};
    private static int[] v25 = {25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private static int[] v25b = {25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,0,0,0,0,0};
    private static int[] v21 = {0xaa55,1,0x55aa,0xaa55,2,0x55aa,0xaa55,3,0x55aa,0xaa55,4,0x55aa,0xaa55,5,0x55aa,0xaa55,6,0x55aa,0xaa55,7,0x55aa};
    private static int[][] invV16 = {
        {5,12,8,2,9,7,13,3,15,6,14,1,11,4,16,10},
        {12,8,2,9,7,13,3,15,6,14,1,11,4,16,10},
        {8,2,9,7,13,3,15,6,14,1,11,4,16,10},
        {2,9,7,13,3,15,6,14,1,11,4,16,10},
        {9,7,13,3,15,6,14,1,11,4,16,10},
        {7,13,3,15,6,14,1,11,4,16,10},
        {13,3,15,6,14,1,11,4,16,10},
        {3,15,6,14,1,11,4,16,10},
        {15,6,14,1,11,4,16,10},
        {6,14,1,11,4,16,10},
        {14,1,11,4,16,10},
        {1,11,4,16,10},
        {11,4,16,10},
        {4,16,10},
        {16,10},
        {10}
    };
    private static int[] v16Ord = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private static int[] v16bOrd = {1,3,4,6,7,10,11,13,14,15,16,0,0,0,0,0};
    private static int[] v20Ord = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private static int[] v20bOrd = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0,0,0,0,0};
    private static int[] v25Ord = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    private static int[] v25bOrd = {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,0,0,0,0,0};
    private static int[][] result16 = {
        {},
        {7},
        {11,7},
        {11,14,7},
        {11,14,7,2},
        {11,14,13,7,2},
        {16,11,14,13,7,2},
        {16,11,14,13,7,2,12},
        {16,11,14,13,7,9,2,12},
        {16,11,14,15,13,7,9,2,12},
        {16,11,14,15,13,7,9,2,8,12},
        {16,11,1,14,15,13,7,9,2,8,12},
        {16,11,1,14,6,15,13,7,9,2,8,12},
        {16,4,11,1,14,6,15,13,7,9,2,8,12},
        {16,4,11,1,14,6,15,3,13,7,9,2,8,12},
        {16,4,11,1,14,6,15,3,13,7,9,2,8,12,5},
        {10,16,4,11,1,14,6,15,3,13,7,9,2,8,12,5}
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
    

    private static boolean ehIgual(int[] v1, int[] v2) {
        return ehIgual(v1,v1.length,v2,v2.length);
    }

    private static boolean ehIgual(int[] v1,int t1, int[] v2,int t2) {
        if (t1 != t2) return false;
        for (int i=0; i<t1; ++i) {
            if (v1[i] != v2[i])
                return false;
        }
        return true;
    }
   
    public static boolean testaLe() {
        boolean res = true, teste;
        String leitura16 = "10 16 4 11 1 14 6 15 3 13 7 9 2 8 12 5";
        String leitura20 = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20";
        String leitura25 = "25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1";
        int[] vet16 = new int[16];
        Vetor.le(new Scanner(leitura16),vet16);
        teste = ehIgual(vet16,v16);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet16) falhou...");
        res = res && teste;
        for (int i=0; i<vet16.length; ++i) vet16[i] = 0;
        Vetor.le(new Scanner(leitura16),vet16,11);
        teste = ehIgual(vet16,v16b);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet16,11) falhou...");
        res = res && teste;
        int[] vet20 = new int[20];
        Vetor.le(new Scanner(leitura20),vet20);
        teste = ehIgual(vet20,v20);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet20) falhou...");
        res = res && teste;
        for (int i=0; i<vet20.length; ++i) vet20[i] = 0;
        Vetor.le(new Scanner(leitura20),vet20,15);
        teste = ehIgual(vet20,v20b);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet20,15) falhou...");
        res = res && teste;
        int[] vet25 = new int[25];
        Vetor.le(new Scanner(leitura25),vet25);
        teste = ehIgual(vet25,v25);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet25) falhou...");
        res = res && teste;
        for (int i=0; i<vet25.length; ++i) vet25[i] = 0;
        Vetor.le(new Scanner(leitura25),vet25,20);
        teste = ehIgual(vet25,v25b);
        if (!teste && advertencias) System.out.println("> Vetor.le(new Scanner(leitura),vet25,20) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaImprime() {
        boolean res = true, teste;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        baos.reset();
        Vetor.imprime(out,v16);
        teste = contemMesmosValores(baos.toString(),"10 16 4 11 1 14 6 15 3 13 7 9 2 8 12 5");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v16) falhou...");
        res = res && teste;
        baos.reset();
        Vetor.imprime(out,v16,10);
        teste = contemMesmosValores(baos.toString(),"10 16 4 11 1 14 6 15 3 13");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v16,10) falhou...");
        res = res && teste;
        baos.reset();
        Vetor.imprime(out,v20);
        teste = contemMesmosValores(baos.toString(),"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v20) falhou...");
        res = res && teste;
        baos.reset();
        Vetor.imprime(out,v20,12);
        teste = contemMesmosValores(baos.toString(),"1 2 3 4 5 6 7 8 9 10 11 12");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v20,12) falhou...");
        res = res && teste;
        baos.reset();
        Vetor.imprime(out,v25);
        teste = contemMesmosValores(baos.toString(),"25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v25) falhou...");
        res = res && teste;
        baos.reset();
        Vetor.imprime(out,v25,14);
        teste = contemMesmosValores(baos.toString(),"25 24 23 22 21 20 19 18 17 16 15 14 13 12");
        if (!teste && advertencias) System.out.println("> Vetor.imprime(out,v25,14) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaPreenche() { 
        boolean res = true, teste;
        final int TAM = 20;
        final int TAM2 = 12;
        final int PADRAO1 = 0xaa55;
        final int PADRAO2 = 0x55aa;
        int[] vet = new int[TAM];
        Vetor.preenche(vet,PADRAO1);
        teste = true;
        for (int i=0; i<TAM; ++i)
            teste = teste && vet[i] == PADRAO1;
        if (!teste && advertencias) System.out.println("> Vetor.preenche(vet,PADRAO1) falhou...");
        res = res && teste;
        Vetor.preenche(vet,TAM2,PADRAO2);
        teste = true;
        for (int i=0; i<TAM2; ++i)
            teste = teste && vet[i] == PADRAO2;
        for (int i=TAM2; i<TAM; ++i)
            teste = teste && vet[i] == PADRAO1;
        if (!teste && advertencias) System.out.println("> Vetor.preenche(vet,TAM2,PADRAO2) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaPreencheSequencialmente() {
        boolean res = true, teste;
        int[] v20s = new int[20];
        Vetor.preencheSequencialmente(v20s);
        teste = ehIgual(v20,v20s);
        if (!teste && advertencias) System.out.println("> Vetor.preencheSequencialmente(v20s) falhou...");
        res = res && teste;
        int[] v20bs = new int[20];
        Vetor.preencheSequencialmente(v20bs,15);
        teste = ehIgual(v20b,v20bs);
        if (!teste && advertencias) System.out.println("> Vetor.preencheSequencialmente(v20bs,15) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaSoma() {
        boolean res = true, teste;
        teste = Vetor.soma(v16)==136;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v16) falhou...");
        res = res && teste;
        teste =  Vetor.soma(v16,10)==93;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v16,10) falhou...");
        res = res && teste;
        teste = Vetor.soma(v20)==210;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v20) falhou...");
        res = res && teste;
        teste =  Vetor.soma(v20,15)==120;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v20,15) falhou...");
        res = res && teste;
        teste = Vetor.soma(v25)==325;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v25) falhou...");
        res = res && teste;
        teste =  Vetor.soma(v25,20)==310;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v25,20) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaMedia() {
        boolean res = true, teste;
        teste = Vetor.media(v16)==8.5;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v16) falhou...");
        res = res && teste;
        teste =  Vetor.media(v16,10)==9.3;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v16,10) falhou...");
        res = res && teste;
        teste = Vetor.media(v20)==10.5;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v20) falhou...");
        res = res && teste;
        teste =  Vetor.media(v20,15)==8.0;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v20,15) falhou...");
        res = res && teste;
        teste = Vetor.media(v25)==13.0;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v25) falhou...");
        res = res && teste;
        teste =  Vetor.media(v25,20)==15.5;
        if (!teste && advertencias) System.out.println("> Vetor.soma(v25,20) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaMenor() {
        boolean res = true, teste;
        teste = Vetor.menor(v16)==1;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v16) falhou...");
        res = res && teste;
        teste = Vetor.menor(v16,4)==4;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v16,4) falhou...");
        res = res && teste;
        teste = Vetor.menor(v20)==1;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v20) falhou...");
        res = res && teste;
        teste = Vetor.menor(v20,4)==1;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v20,4) falhou...");
        res = res && teste;
        teste = Vetor.menor(v25)==1;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v25) falhou...");
        res = res && teste;
        teste = Vetor.menor(v25,5)==21;
        if (!teste && advertencias) System.out.println("> Vetor.menor(v20,4) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaMaior() {
        boolean res = true, teste;
        teste = Vetor.maior(v16)==16;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v16) falhou...");
        res = res && teste;
        teste = Vetor.maior(v16,4)==16;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v16,4) falhou...");
        res = res && teste;
        teste = Vetor.maior(v20)==20;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v20) falhou...");
        res = res && teste;
        teste = Vetor.maior(v20,4)==4;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v20,4) falhou...");
        res = res && teste;
        teste = Vetor.maior(v25)==25;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v25) falhou...");
        res = res && teste;
        teste = Vetor.maior(v25,5)==25;
        if (!teste && advertencias) System.out.println("> Vetor.maior(v20,4) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaConta() {
        boolean res = true, teste;
        teste = Vetor.conta(v21,0xaa55)==7;
        if (!teste && advertencias) System.out.println("> Vetor.conta(v21,0xaa55) falhou...");
        res = res && teste;
        teste = Vetor.conta(v21,0x55aa)==7;
        if (!teste && advertencias) System.out.println("> Vetor.conta(v21,0x55aa) falhou...");
        res = res && teste;
        for (int i=1; i<=7; ++i) {
            teste = Vetor.conta(v21,i*3,0xaa55)==i;
            if (!teste && advertencias) System.out.println("> Vetor.conta(v21,"+i*3+",0xaa55) falhou...");
            res = res && teste;
            teste = Vetor.conta(v21,i*3,0x55aa)==i;
            if (!teste && advertencias) System.out.println("> Vetor.conta(v21,"+i*3+",0x55aa) falhou...");
            res = res && teste;
        }
        return res;
    }
    
    public static boolean testaInverte() {
        boolean res = true, teste;
        int[] vTest = new int[v16.length];
        for (int i=0; i<v16.length; ++i) vTest[i] = v16[i];
        Vetor.inverte(vTest);
        teste = ehIgual(vTest,invV16[0]); 
        if (!teste && advertencias) System.out.println("> Vetor.inverte(vTest) falhou...");
        res = res && teste;
        for (int t=v16.length-1, l=1; t>=1; --t, l++) {
            for (int i=0; i<t; ++i) vTest[i] = v16[i];
            Vetor.inverte(vTest,t);
            teste = ehIgual(vTest,t,invV16[l],t); 
            if (!teste && advertencias) System.out.println("> Vetor.inverte(vTest,"+t+") falhou...");
            res = res && teste;
        }
        return res;
    }
    
    public static boolean testaBuscaLinear() {
        int[] v18 = {13,1,2,3,14,5,6,7,15,0,9,2,10,4,11,6,12,8};
        int[] vResp18 = {9,1,2,3,13,5,6,7,17,10,12,14,16,0,4,8,-1,-1};
        int[] vResp18Par = {-1,1,2,3,-1,5,6,7,-1,-1,-1,-1,-1,0,4,8,-1,-1};
        boolean res = true, teste;
        for (int i=0; i<vResp18.length; ++i) {
            teste = Vetor.buscaLinear(v18,i) == vResp18[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaLinear(v18,"+i+") falhou...");
           res = res && teste;
        }
        for (int i=0; i<vResp18Par.length; ++i) {
            teste = Vetor.buscaLinear(v18,9,i) == vResp18Par[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaLinear(v18,9,"+i+") falhou...");
           res = res && teste;
        }
        return res;
    }
    
    public static boolean testaBuscaBinaria() {
        int[] v18 = {1,3,3,4,5,5,6,8,9,11,12,13,15,19,20,20,24,25};
        int[] resBuscas = {-1,0,-1,1,3,5,6,-1,7,8,-1,9,10,11,-1,12,-1,-1,-1,13,15,-1,-1,-1,16,17,-1};
        int[] resBuscasPar = {-1,0,-1,1,3,4,6,-1,7,8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        boolean res = true, teste;
        for (int i=0; i<=26; ++i) {
            teste = Vetor.buscaBinaria(v18,i) == resBuscas[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaBinaria(v18,"+i+") falhou...");
           res = res && teste;
        }
        for (int i=0; i<=26; ++i) {
            teste = Vetor.buscaBinaria(v18,9,i) == resBuscasPar[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaBinaria(v18,9,"+i+") falhou...");
           res = res && teste;
        }
        return res;
    }
    
    public static boolean testaBuscaBinariaRec() {
        int[] v18 = {1,3,3,4,5,5,6,8,9,11,12,13,15,19,20,20,24,25};
        int[] resBuscas = {-1,0,-1,1,3,5,6,-1,7,8,-1,9,10,11,-1,12,-1,-1,-1,13,15,-1,-1,-1,16,17,-1};
        int[] resBuscasPar = {-1,0,-1,1,3,4,6,-1,7,8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        boolean res = true, teste;
        for (int i=0; i<=26; ++i) {
            teste = Vetor.buscaBinariaRec(v18,i) == resBuscas[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaBinariaRec(v18,"+i+") falhou...");
           res = res && teste;
        }
        for (int i=0; i<=26; ++i) {
            teste = Vetor.buscaBinariaRec(v18,9,i) == resBuscasPar[i]; 
            if (!teste && advertencias) System.out.println("> Vetor.buscaBinariaRec(v18,9,"+i+") falhou...");
           res = res && teste;
        }
        return res;
    }
        
    public static boolean testaBubbleSort() {
        int[] v;
        boolean res = true, teste;
        
        v = new int[v16.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v16[i];
        Vetor.bubbleSort(v);
        teste = ehIgual(v,v16Ord);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v16) falhou...");
        res = res && teste;
        v = new int[v16b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v16b[i];
        Vetor.bubbleSort(v,11);
        teste = ehIgual(v,v16bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v16b,11) falhou...");
        res = res && teste;
        
        v = new int[v20.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v20[i];
        Vetor.bubbleSort(v);
        teste = ehIgual(v,v20Ord);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v20) falhou...");
        res = res && teste;
        v = new int[v20b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v20b[i];
        Vetor.bubbleSort(v,15);
        teste = ehIgual(v,v20bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v20b,15) falhou...");
        res = res && teste;

        v = new int[v25.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v25[i];
        Vetor.bubbleSort(v);
        teste = ehIgual(v,v25Ord);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v25) falhou...");
        res = res && teste;
        v = new int[v25b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v25b[i];
        Vetor.bubbleSort(v,20);
        teste = ehIgual(v,v25bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.bubbleSort(v25b,20) falhou...");
        res = res && teste;

        return res;
    }

    public static boolean testaSelectionSort() {
        int[] v;
        boolean res = true, teste;
        
        v = new int[v16.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v16[i];
        Vetor.selectionSort(v);
        teste = ehIgual(v,v16Ord);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v16) falhou...");
        res = res && teste;
        v = new int[v16b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v16b[i];
        Vetor.selectionSort(v,11);
        teste = ehIgual(v,v16bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v16b,11) falhou...");
        res = res && teste;
        
        v = new int[v20.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v20[i];
        Vetor.selectionSort(v);
        teste = ehIgual(v,v20Ord);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v20) falhou...");
        res = res && teste;
        v = new int[v20b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v20b[i];
        Vetor.selectionSort(v,15);
        teste = ehIgual(v,v20bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v20b,15) falhou...");
        res = res && teste;

        v = new int[v25.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v25[i];
        Vetor.selectionSort(v);
        teste = ehIgual(v,v25Ord);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v25) falhou...");
        res = res && teste;
        v = new int[v25b.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v25b[i];
        Vetor.selectionSort(v,20);
        teste = ehIgual(v,v25bOrd);
        if (!teste && advertencias) System.out.println("> Vetor.selectionSort(v25b,20) falhou...");
        res = res && teste;

        return res;
    }
    
    public static boolean testaRemoveElemento() {
        boolean res = true, teste;
        int[] rem16  = {16, 0,14,14,-1, 7, 1, 4, 2, 8, 3, 5, 6, 7, 0, 2, 3, 1, 0, 0};
        int[] resp16 = {16,15,14,14,14,13,12,11,10, 9, 8, 7, 6, 6, 5, 4, 3, 2, 1, 0};
        int[] v = new int[v16.length];
        for (int i=0; i<v.length; ++i)
            v[i] = v16[i];
        int t = 16;
        for (int i=0; i<rem16.length; ++i) {
            int nt = Vetor.removeElemento(v,t,rem16[i]);
            teste = (nt == resp16[i]) && ehIgual(v,nt,result16[nt],result16[nt].length);
            t = nt;
            if (!teste && advertencias) System.out.println("> Vetor.removeElemento(v,"+t+","+rem16[i]+") falhou...");
            res = res && teste;
            
        }
        return res;
    }
    
    public static boolean testaInsereNoFinal() {
        boolean res = true, teste;
        int[] v1 = new int[v16.length];
        int[] v2 = new int[v16.length];
        for (int i=0; i<v2.length; ++i)
            v2[i] = 0;
        int t = 0;
        for (int i=0; i<v16.length; ++i) {
            int n = v16[i];
            v2[i] = n;
            int nt = Vetor.insereNoFinal(v1,t,n);
            teste = (nt == t + 1) && ehIgual(v1,v2);
            t = nt;
            if (!teste && advertencias) System.out.println("> Vetor.insereNoFinal(v1,"+t+","+n+") falhou...");
            res = res && teste;        
        }
        int nt = Vetor.insereNoFinal(v1,t,99);
        teste = (nt == t) && ehIgual(v1,v2);
        if (!teste && advertencias) System.out.println("> Vetor.insereNoFinal(v1,"+t+",99) falhou...");
        res = res && teste;
        return res;
    }
    
    public static boolean testaInsere() {
        boolean res = true, teste;
        int[] pos16  = { -1, 1, 0, 0, 1, 3, 2, 0, 6, 5, 3, 8, 2, 4, 1, 7,-1,15,14, 0,-1,16};
        int[] val16  = {  7, 7, 7,11,14, 2,13,16,12, 9,15, 8, 1, 6, 4, 3, 5, 5, 5,10,99,99};
        int[] resp16 = {  0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,14,14,15,16,16,16};
        int[] v = new int[v16.length];
        int t = 0;
        for (int i=0; i<pos16.length; ++i) {
            int nt = Vetor.insere(v,t,pos16[i],val16[i]);
            teste = (nt == resp16[i]) && ehIgual(v,nt,result16[nt],result16[nt].length);
            t = nt;
            if (!teste && advertencias) System.out.println("> Vetor.insere(v,"+t+","+pos16[i]+","+val16+") falhou...");
            res = res && teste;
            
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Teste para Vetor.le():                      " + (testaLe()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.imprime():                 " + (testaImprime()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.preenche():                " + (testaPreenche()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.preencheSequencialmente(): " + (testaPreencheSequencialmente()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.soma():                    " + (testaSoma()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.media():                   " + (testaMedia()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.menor():                   " + (testaMenor()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.maior():                   " + (testaMaior()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.conta():                   " + (testaConta()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.inverte():                 " + (testaInverte()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.buscaLinear():             " + (testaBuscaLinear()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.buscaBinaria():            " + (testaBuscaBinaria()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.buscaBinariaRec():         " + (testaBuscaBinariaRec()?"OK":"ERRO"));    
        System.out.println("Teste para Vetor.bubbleSort():              " + (testaBubbleSort()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.selectionSort():           " + (testaSelectionSort()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.removeElemento():          " + (testaRemoveElemento()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.insereNoFinal():           " + (testaInsereNoFinal()?"OK":"ERRO"));
        System.out.println("Teste para Vetor.insere():                  " + (testaInsere()?"OK":"ERRO"));
    }
}
