package controlStructures.controlStructures2;

public class ControlStructuresArray {




    public void switchArray(int [] arr) {
        int  zero=0, one=0, two=0, three=0, four=0, five=0, six=0, seven=0, eight=0, nine=0, ten=0;
        for(int i=0; i<arr.length;i++) {
            int val = arr[i];
            switch (val) {
                case 0:
                    System.out.print("0 ");
                    zero++;
                    break;
                case 1:
                    System.out.print("1 ");
                    one++;
                    break;
                case 2:
                    System.out.print ("2 ");
                    two++;
                    break;
                case 3:
                    System.out.print("3 ");
                    three++;
                    break;
                case 4:
                    System.out.print("4 ");
                    four++;
                    break;
                case 5:
                    System.out.print("5 ");
                    five++;
                    break;
                case 6:
                    System.out.print("6 ");
                    six++;
                    break;
                case 7:
                    System.out.print("7 ");
                    seven++;
                    break;
                case 8:
                    System.out.print("8 ");
                    eight++;
                    break;
                case 9:
                    System.out.print("9 ");
                    nine++;
                    break;
                case 10:
                    System.out.print("10 ");
                    ten ++;
                    break;

            }

        }
        System.out.println();
        System.out.println("zero-->" + zero + " one-->" + one + " two-->" + two + " three --> "  + three + " four -->" + four + " five-->" + five + " six--> " + six + " seven--> " + seven + " eight--> " + eight + " nine--> " + nine + " ten--> " +ten);


    }


    public void ifElseIf(int[] arr) {
       int  zero=0, one=0, two=0, three=0, four=0, five=0, six=0, seven=0, eight=0, nine=0, ten=0;

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (val == 0) {
                System.out.print("0 ");
                zero++;
            } else if (val == 1) {
                System.out.print("1 ");
                one++;
            }
            else if(val == 2){
                System.out.print("2 ");
                two++;
            }
            else if(val == 3){
                System.out.print("3 ");
                three++;
            }

            else if(val == 4){
                System.out.print("4 ");
                four++;
            }

            else if(val == 5){
                System.out.print("5 ");
                five++;
            }

            else if(val == 6){
                System.out.print("6 ");
                six++;
            }

            else if(val == 7){
                System.out.print("7 ");
                seven++;
            }

            else if(val == 8){
                System.out.print("8 ");
                eight++;
            }

            else if(val == 9){
                System.out.print("9 ");
                nine++;

            }

            else if(val == 10){
                System.out.print("10 ");
                ten++;
            }


        }
        System.out.println();
        System.out.println("zero-->" + zero + " one-->" + one + " two-->" + two + " three --> "  + three + " four -->" + four + " five-->" + five + " six--> " + six + " seven--> " + seven + " eight--> " + eight + " nine--> " + nine + " ten--> " +ten);

    }


}