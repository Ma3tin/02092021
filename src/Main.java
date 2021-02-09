import java.io.*;

public class Main {

    public static String firmys = "BlueNet, s.r.o.:05159822\n" +
            "Epic Internet a.s.:06230932\n" +
            "Big žirafa s.r.o.:18539217\n" +
            "Martin Nový:02838109\n" +
            "GreenStyles, a.s.:01846359\n" +
            "Valza, a.s.:9831829\n" +
            "ZCZ, s.r.o.:33828139";


    public static void main(String[] args) {
        String[] firmy = firmys.split("\n");
        String[] name = new String[7];
        String[] ico = new String[7];
        for (int i = 0; i < firmy.length; i++) {
            int doubledot = firmy[i].indexOf(":");
            name[i] = firmy[i].substring(0, doubledot);
            ico[i] = firmy[i].substring(doubledot + 1);
        }


        String line = "";
        try {
            for (int i = 2015; i <= 2020; i++) {
                String fileName = "VF_" + i + ".dat";
                String fileNameDone = "VF_" + i + "-done.dat";
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameDone));
                line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    String[] row = line.split(",");
                    bw.write(row[0] + ";");
                    int orderNumber = Integer.parseInt(row[1]);
                    int j = 0;
                    for (j = 0; j < 6; j++) {
                        int icos = Integer.parseInt(ico[j]);
                        if (icos == orderNumber) {
                            break;
                        }
                    }
                    bw.write(name[j] + ";");
                    bw.write(row[2]);
                    bw.newLine();
                    line = br.readLine();
                }
                bw.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

