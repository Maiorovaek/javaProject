package factorial;

public class Factorial {
    int n = 0;

    public Factorial(int n) {
        this.n = n;
    }

    public int mulFactor() {
        int result = 1;
        for (int i = 0; i < n+1; i++) {
            if (i == 0) {
                result = 1;
            } else {
                result = result * i;
            }
        }
        return result;
    }

    public int rekursFactor(int n){
        int result ;

        if(n<=1)
            return 1;
        else
        result = n*rekursFactor(n-1);
        return result;
    }


}
