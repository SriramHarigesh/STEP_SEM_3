public class BasicDrawingCanvas{
    public static void main(String[] args){
        CircleShape c=new CircleShape(5.0);
        SquareShape sq=new SquareShape(4.0);
        System.out.println(c.calculateArea());
        System.out.println(sq.calculateArea());
        sq.scale(2.0);
        System.out.println(sq.calculateArea());
        printArea(c);
        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());
    }
    static void printArea(Shape s){
        System.out.println(s.calculateArea());
    }
}
abstract class Shape{
    private static int counter=1000;
    private final String shapeId;
    public Shape(){
        shapeId="SH-"+(++counter);
    }
    public abstract double calculateArea();
    void scale(double factor){
    }
    void scale(double xFactor,double yFactor){
        scale(xFactor);
        scale(yFactor);
    }
    String getShapeId(){
        return shapeId;
    }
}
class CircleShape extends Shape{
    private double radius;
    public CircleShape(double radius){
        this.radius=radius;
    }
    @Override
    public double calculateArea(){
        return Math.PI*radius*radius;
    }
    @Override
    void scale(double factor){
        radius*=factor;
    }
    @Override
    void scale(double xFactor,double yFactor){
        radius*=Math.sqrt(xFactor*yFactor);
    }
}
class SquareShape extends Shape{
    private double side;
    public SquareShape(double side){
        this.side=side;
    }
    @Override
    public double calculateArea(){
        return side*side;
    }
    @Override
    void scale(double factor){
        side*=factor;
    }
    @Override
    void scale(double xFactor,double yFactor){
        side*=Math.sqrt(xFactor*yFactor);
    }
}