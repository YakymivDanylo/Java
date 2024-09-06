import java.util.List;

public class Ex6 {
    abstract static class Shape{
        public abstract double getArea();
    }

    static class Circle extends Shape{
        private double radius;

        public Circle(double radius){this.radius=radius;}

        @Override
        public double getArea(){return Math.PI*radius*radius;}
    }

    static class Rectangle extends Shape{
        private double width;
        private double height;

        public Rectangle(double width, double height){
            this.width=width;
            this.height=height;
        }

        @Override
        public double getArea(){return width*height;}
    }

    public static double calculateTotalArea(List<? extends Shape> shapes){
        double totalArea=0;
        for(Shape shape:shapes){
            totalArea+=shape.getArea();
        }
        return totalArea;
    }

    public static void main(String[] args){
        List<Shape> shapes= List.of(new Circle(5), new Rectangle(4,6),new Circle(2.5));

        double totalArea = calculateTotalArea(shapes);
        System.out.println("Total area: " + totalArea);
    }

}
