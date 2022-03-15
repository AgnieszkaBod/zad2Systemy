import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Zad2Systemy extends JFrame {

    public Zad2Systemy() {
        setSize(1500, 500);
        setTitle("Zad2Systemy");
    }

    public static final String filePath = "src/katalog.txt";

    public static String[] headers = new String[15];
    public static Object[][] data = new Object[24][15];

    public static JButton bExport = new JButton("Eksportuj dane");

    public static void importData() throws IOException {
        BufferedReader fileReader = null;
        String line = null;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));

        } catch (
                FileNotFoundException e) {
            System.out.println("Blad przy otwieraniu pliku!");

        }
        String[] words;
        line = fileReader.readLine();

        for (int i = 0; i < 24; i++) {
            line = fileReader.readLine();
            words = line.split(";", -1);
            for (int j = 0; j <= 14; j++) {
                data[i][j] = words[j];
                if (data[i][j] == null || data[i][j].equals("")) {
                    data[i][j] = "Brak informacji";
                }
            }
        }
    }

    public static void getHeaders() throws IOException {
        BufferedReader fileReader = null;
        String line;

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
        }
    }

    public static void main(String[] args) throws IOException {

        Zad2Systemy window = new Zad2Systemy();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getHeaders();

        JTable table = new JTable(data, headers) {
            @Override  //edycja danych w cell
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                data[row][column] = aValue;
                System.out.println(aValue + " | " + row + " | " + column);
            }
        };

       JButton bImport = new JButton("Importuj dane");

        bExport.setBounds(10, 450, 150, 50);
        bExport.setBackground(Color.ORANGE);
        bExport.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bImport.setBounds(170, 450, 150, 50);
        bImport.setBackground(Color.BLUE);
        bImport.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        table.setBounds(30, 40, 400, 500);
        JScrollPane sp = new JScrollPane(table);
        window.add(sp);
        window.add(bImport);
        window.setVisible(true);

        bImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                window.repaint();
            }
        });
    }
}
