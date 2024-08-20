import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DNAalien{
    public static String mutateDNA(String dna) {
        boolean mutated;
        do {
            mutated = false;
            StringBuilder newDNA = new StringBuilder();
            int i = 0;
            while (i < dna.length()) {
                if (i + 1 < dna.length() && dna.charAt(i) != dna.charAt(i + 1)) {
                    newDNA.append(getMutatedBase(dna.charAt(i), dna.charAt(i + 1)));
                    i += 2;
                    mutated = true;
                } else {
                    newDNA.append(dna.charAt(i));
                    i++;
                }
            }
            dna = newDNA.toString();
        } while (mutated);
        return dna;
    }

    public static char getMutatedBase(char base1, char base2) {
        if ((base1 == 'D' && base2 == 'N') || (base1 == 'N' && base2 == 'D')) {
            return 'A';
        } else if ((base1 == 'N' && base2 == 'A') || (base1 == 'A' && base2 == 'N')) {
            return 'D';
        } else {
            return 'N';
        }
    }

    public static void main(String[] args) {
        String inputFolderPath = "C:\\Users\\a_aca\\Downloads\\Casos de Teste-20230901";
        String outputFolderPath = "C:\\Users\\a_aca\\Downloads\\Casos de Teste-20230901\\output";

        File inputFolder = new File(inputFolderPath);
        File outputFolder = new File(outputFolderPath);

        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        File[] inputFiles = inputFolder.listFiles();

        if (inputFiles != null) {
            for (File inputFile : inputFiles) {
                if (inputFile.isFile()) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        String alienDNA = reader.readLine();
                        reader.close();

                        String mutatedDNA = mutateDNA(alienDNA);

                        File outputFile = new File(outputFolder, inputFile.getName().replace(".txt", "_output.txt"));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                        writer.write(mutatedDNA);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}