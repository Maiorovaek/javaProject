package nestedLoops;

public class LoopsRectangle {

    int n;
    int s;
   public void printRectangle(int n, int s){

    for(int i = 0; i<n; i++){
        for(int j=0;j<s; j++){
            System.out.print("#");
        }
        System.out.print("#");
        System.out.println();
    }

   }
}
