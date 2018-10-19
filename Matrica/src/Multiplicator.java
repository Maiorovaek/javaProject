public class Multiplicator {
    public Matrix multiply(Matrix p, Matrix q) throws Exception {
        int v = p.getVerticalSize();
        int h = q.getHorizontalSqze();
        int temp = p.getHorizontalSqze();
        if(temp != q.getVerticalSize()){
throw new Exception();        }
        Matrix result = new Matrix(v , h);

// умножение
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < h; j++) {
                    int value = 0;
                    for (int k = 0; k < temp; k++) {
                        value += p.getElement(i, k) * q.getElement(k, j);
                    }
                    result.setElements(i, j, value);
                }
            }

        return result;

    }
}
