package ProjBall;

public class TestBall {
    public static void main(String[] args) {
        Ball b1 = new Ball(15, -9, 2 , 0,50);
        Container c1 = new Container(0,0,50,20);

       System.out.println(c1.collides(b1));


    }
}
