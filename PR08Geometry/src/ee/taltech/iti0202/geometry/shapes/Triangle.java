package ee.taltech.iti0202.geometry.shapes;

public class Triangle extends Shape {
    private static final int CORNERS = 3;
    private static final Shape.Type TYPE = Type.TRIANGLE;

    public Triangle(String name, Shape.Color color, int size) {
        super(name, color, size);
    }

    public void changeSize(int change) {
        if (size + change <= 0) {
            System.out.println(String.format("NO! Change can't make the shape size to or below zero! "
                    + "Attempted to change size %d by %d.", size, change));
        } else {
            System.out.println(String.format("Shape %s size changed from %d to %d.", name, size, size + change));
            size += change;
        }
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    @Override
    public int getCorners() {
        return CORNERS;
    }

    @Override
    public double getArea() {
        return Math.sqrt(3) * size * size / 4;
    }

    @Override
    public double getPerimeter() {
        return 3 * size;
    }
}
