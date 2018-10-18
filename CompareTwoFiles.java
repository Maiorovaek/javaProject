import javax.naming.Context;
import java.io.*;
import java.util.*;

public class CompareTwoFiles {
    public static void main(String[] args) throws IOException {
        String first = "", second = "";
        String firstName = "", secondName = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a first file name: ");
        firstName = input.nextLine();
        System.out.print("Enter a second file name: ");
        secondName = input.nextLine();
        Scanner input1 = new Scanner(new File(firstName));
        Scanner input2 = new Scanner(new File(secondName));
        File dir1 = new File("Different1.csv");

        if (!dir1.exists()) {
            while (input1.hasNextLine() && input2.hasNextLine()) {
                first = input1.nextLine();
                second = input2.nextLine();
                if (!first.equals(second)) {
                    System.out.println("Differences found: " + "\n" + first + '\n' + second);
                    PrintWriter writer;
                    writer = new PrintWriter(new FileWriter(dir1, true));
                    String dif = new String(first);
                    String dif2 = new String(second);
                    StringBuffer sb = new StringBuffer();
                    sb.append(dif + ";" + dif2);
                    writer.println(sb);
                    writer.flush();
                    writer.close();
                }
            }
        } else {
            System.out.println("File Different1.csv  exists");
        }
        System.out.println("done!");
    }
}