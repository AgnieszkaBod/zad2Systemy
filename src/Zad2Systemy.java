import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Zad2Systemy extends JFrame {
    public Zad2Systemy() {
        setSize(1500, 500);
        setTitle("Zad2Systemy");
    }

    public static void main(String[] args) throws IOException {
        final String filePath = "src/katalog.txt";
        BufferedReader fileReader = null;
        String line;
        final String[] headers = new String[15];
        final Object[][] data = new Object[24][15];

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
        Zad2Systemy window = new Zad2Systemy();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = new JTable(data, headers) {
            @Override  //edycja danych w cell
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                data[row][column] = aValue;
                System.out.println(aValue + " | " + row + " | " + column);
            }
        };
        table.setBounds(30, 40, 400, 500);
        JScrollPane sp = new JScrollPane(table);
        window.add(sp);
        window.setVisible(true);
    }
}
