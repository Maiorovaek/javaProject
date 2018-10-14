import java.io.IOException;
import java.util.Scanner;

public class MyClass {


    public static void main(String[] args) throws IOException {
        int lengthOfArr = 5;
        int[] arr = new int[lengthOfArr];

        System.out.println("Please, enter numbers for array: ");
        Scanner scanner = new Scanner(System.in);


        String input = scanner.nextLine();
        int count = 0;
        if (input.length() != 0) {
            count++;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    count++;
                }
            }
        }
        System.out.println("Entered " + count + " numbers");


        if (lengthOfArr < count) {
            do {
                lengthOfArr = lengthOfArr * 2;
            } while (lengthOfArr < count);

            arr = new int[lengthOfArr];
        }

        System.out.print("Length of Array " + lengthOfArr);
        System.out.println();
        String[] arrOfInput = input.split(" ");


        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(arrOfInput[i]);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }


        System.out.println("Please, enter simbol for delete: ");

        Scanner s= new Scanner(System.in);
        int x = s.nextInt();
        for(int i=0;i<arr.length; i++){
           if(arr[i] == x){
              arr[i] = 0;
           }
            System.out.print(arr[i] + " ");
        }
    }
}
