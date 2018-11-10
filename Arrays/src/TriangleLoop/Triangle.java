package TriangleLoop;

public class Triangle {

    public void printTriangle(int n){
        for(int i =0; i<n; i++){
            for(int j=i; j<n; j++){
                System.out.print("#");
            }

            System.out.println();
        }
    }

    public void printTriangle2(int n){
        for(int i =0; i<n; i++){
            for(int j=0; j<i+1; j++){
                System.out.print("#");
            }
            System.out.println();
        }
    }
    public void printTriangle3(int n ){

        for (int i = 0; i < n; i++){

            for (int j = n; j > i+1; j--){
                System.out.print(" ");
            }
            for (int j = 0; j < i+1; j++){
                System.out.print("#");
            }


            System.out.println();
        }
    }}




