import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Zad2Systemy extends JFrame {

    public Zad2Systemy() {
        setSize(1500, 700);
        setTitle("Zad2Systemy");
    }

    public static final String filePath = "src/katalog.txt";

    public static String[] headers = new String[15];
    public static Object[][] data = new Object[24][15];

    public static void importData() throws IOException {
        BufferedReader fileReader = null;
        String line;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));

        } catch (
                FileNotFoundException e) {
            System.out.println("Blad przy otwieraniu pliku!");

        }
        String[] words;
        assert fileReader != null;
        fileReader.readLine();

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

    public static void exportToFile() {
        PrintWriter zapis = null;
        try {
            zapis = new PrintWriter("src/wynik.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 15; j++) {
                assert zapis != null;
                zapis.print(data[i][j] + ";");
            }
            if (i < 23) {
                zapis.print("\n");
            }
        }
        zapis.close();
    }

    public static void main(String[] args) throws IOException {

        Zad2Systemy window = new Zad2Systemy();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getHeaders();

        JButton bExport = new JButton("Eksportuj dane");
        bExport.setBounds(10, 450, 150, 50);
        bExport.setBackground(Color.ORANGE);
        bExport.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton bImport = new JButton("Importuj dane");
        bImport.setBounds(170, 450, 150, 50);
        bImport.setBackground(Color.BLUE);
        bImport.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JTable table = new JTable(data, headers) {
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                data[row][column] = aValue;
                System.out.println(aValue + " | " + row + " | " + column);
            }
        };

        table.setBounds(30, 40, 400, 500);
        JScrollPane sp = new JScrollPane(table);
        window.add(bExport);
        window.add(bImport);
        window.add(sp);
        window.setVisible(true);

        bImport.addActionListener(e -> {
            try {
                importData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            window.repaint();
        });
        bExport.addActionListener(e -> exportToFile());
    }
}
