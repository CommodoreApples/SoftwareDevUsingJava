import java.lang.Math;

public class PA4 {
    private int n;
    private double side;
    private double x;
    private double y;

    public PA4() {
        this.n = 3;
        this.side = 1.0;
        this.x = 0.0;
        this.y = 0.0;
    }

    public PA4(int n, double side) {
        this.n = n;
        this.side = side;
        this.x = 0.0;
        this.y = 0.0;
    }

    public PA4(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPerimeter() {
        return n * side;
    }

    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }

    public static void main(String[] args) {
        PA4 poly1 = new PA4();
        PA4 poly2 = new PA4(6, 4);
        PA4 poly3 = new PA4(10, 4, 5.6, 7.8);

        System.out.println("Polygon 1 - Perimeter: " + poly1.getPerimeter() + " Area: " + poly1.getArea());
        System.out.println("Polygon 2 - Perimeter: " + poly2.getPerimeter() + " Area: " + poly2.getArea());
        System.out.println("Polygon 3 - Perimeter: " + poly3.getPerimeter() + " Area: " + poly3.getArea());
    }
}
