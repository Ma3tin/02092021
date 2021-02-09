import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

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
            HashSet<String> noNameIco = new HashSet<>();
            ArrayList<String> orderLine = new ArrayList<>();
            BufferedWriter nf = new BufferedWriter(new FileWriter("nezname-faktury"));
            long total = 0;
            for (int i = 2015; i <= 2020; i++) {
                String fileName = "VF_" + i + ".dat";
                String fileNameDone = "VF_" + i + "-done.dat";
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameDone));
                BufferedWriter ns = new BufferedWriter(new FileWriter("nezname-spolecnosti"));
                line = br.readLine();
                line = br.readLine();
                int last = -1;
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
                    for (int k = 0; k < fileName.length(); k++) {
                        int column = line.indexOf(",");
                        line.substring(0, column);

                    }
                    bw.newLine();
                    line = br.readLine();
                    int actual = Integer.parseInt(row[0]);
                    if (last != -1) {
                        int rozdil = actual - last;
                        if (rozdil > 1) {
                            //System.out.println(last + 1);
                            int missing = (last + 1);
                            nf.write(String.valueOf(missing));
                            nf.newLine();
                        }
                    }
                    last = actual;

                    int money = Integer.parseInt(row[2]);
                    total += money;
                }
                bw.close();
                ArrayList<String> listIco = new ArrayList<>(noNameIco);
                for (int j = 0; j < listIco.size(); j++) {
                    ns.write(listIco.get(j));
                    ns.newLine();
                }
                ns.close();
            }
            nf.close();
            System.out.println(total);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNotInArray(String rowIco, String[] ico) {
        for (int i = 0; i < ico.length; i++) {
            if (ico[i].equals(rowIco)) return false;

        }
        return true;
    }

}

