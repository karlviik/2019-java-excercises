package ee.taltech.iti0202.geometry.shapes;

public class Circle extends Shape {
    private static final int CORNERS = 0;
    private static final Shape.Type type = Type.CIRCLE;

    public Circle(String name, Shape.Color color, int size) {
        super(name, color, size);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int getCorners() {
        return CORNERS;
    }

    @Override
    public double getArea() {
        return Math.PI * size * size;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * size;
    }

}
