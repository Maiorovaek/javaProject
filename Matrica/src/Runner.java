public class Runner {
    public static void main(String[] args) throws Exception {
        Matrix a = new Matrix(3,3);
        MatrixCreate.fillRandomized(a,2,8);
        System.out.println("Matrix first is" +  a);
        Matrix q = new Matrix(3,3);
        MatrixCreate.fillRandomized(q,2,7);
        System.out.println("Matrix second is" + q);
        Multiplicator mult = new Multiplicator();
        System.out.println("Matrix product is" + mult.multiply(a,q));


Sum s = new Sum();
        System.out.println("Summ" + s.sum(a, q));


    }
}
