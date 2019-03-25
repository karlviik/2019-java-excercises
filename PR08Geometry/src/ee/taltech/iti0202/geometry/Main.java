package ee.taltech.iti0202.geometry;

import ee.taltech.iti0202.geometry.canvas.Canvas;
import ee.taltech.iti0202.geometry.shapes.Circle;
import ee.taltech.iti0202.geometry.shapes.Shape;
import ee.taltech.iti0202.geometry.shapes.Square;
import ee.taltech.iti0202.geometry.shapes.Triangle;

public class Main {

    public static void main(String[] args) {
        Canvas canvas = new Canvas("Peeter");
        Square square1 = new Square("Toomas", Shape.Color.Black, 10);
        Square square2 = new Square("Mati", Shape.Color.Blue, 50);
        Triangle triangle1 = new Triangle("Kati", Shape.Color.Green, 90);
        Circle circle1 = new Circle("Evelin Ilves", Shape.Color.Red, 90000);

        canvas.draw(square1);
        canvas.draw(square1);
        canvas.draw(square2);
        canvas.draw(triangle1);
        canvas.draw(circle1);
        canvas.printShapes();
        canvas.eraseShape(circle1);
        canvas.eraseShape(circle1);

        canvas.printShapes();
        System.out.println(circle1.getArea());
        System.out.println(square1.getArea());
        System.out.println(square2.getArea());
        System.out.println(square2.getPerimeter());
        square1.changeSize(10);
        square1.changeSize(-1000000);
    }

}
