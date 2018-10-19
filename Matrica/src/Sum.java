public class Sum {
    public Matrix sum(Matrix p, Matrix q) throws Exception {

        if(p.getVerticalSize() != q.getVerticalSize()||p.getHorizontalSqze()!=q.getHorizontalSqze()){
            System.out.println("lenght and hight must be same");
            throw new Exception();


        }
        else{
            Matrix tmpMatrix = new Matrix(p.getVerticalSize(), q.getHorizontalSqze());
            for(int i = 0; i<p.getHorizontalSqze();i++) {
                for (int j = 0; j < tmpMatrix.getVerticalSize(); j++) {
                    tmpMatrix.setElements(i, j, p.getElement(i, j) + q.getElement(i, j));
                }
            }return tmpMatrix;}}}
