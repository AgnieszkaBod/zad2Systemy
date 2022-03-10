import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Zad2Systemy extends JFrame {
    public Zad2Systemy() {
        setSize(800, 700);
        setTitle("Zad2Systemy");
    }

    void readFile() throws IOException {

        final String filePath = "src/katalog.txt";
        BufferedReader fileReader = null;
        String line;
        String[] headers = new String[15];
        Object[][] data = new Object[24][15];

        try {
            fileReader = new BufferedReader(new FileReader(filePath));

        } catch (
                FileNotFoundException e) {
            System.out.println("Blad przy otwieraniu pliku!");
        }

        if (fileReader != null) {
            line = fileReader.readLine();
            String[] words = line.split(";");
            int i = 0;
            for (String word : words) {
                headers[i] = word;
                i++;
            }

            for (i = 0; i < 24; i++) {
                line = fileReader.readLine();
                words = line.split(";");
                System.arraycopy(words, 0, data[i], 0, words.length);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Zad2Systemy zad2 = new Zad2Systemy();
        zad2.readFile();
    }
}
