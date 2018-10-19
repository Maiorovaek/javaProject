public class MatrixCreate {
    public static void fillRandomized(Matrix t, int start, int end){
        int v = t.getVerticalSize();
        int h = t.getHorizontalSqze();
        for(int i = 0; i<v;i++){
            for(int j=0; j<h;j++){

                try {
                    int value = (int)(Math.random()*(end-start) + start);
                    t.setElements(i,j,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
