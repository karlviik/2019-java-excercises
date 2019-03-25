package ee.taltech.iti0202.geometry.shapes;

public abstract class Shape {
    String name;
    int size;
    Color color;

    // should write it in caps, but I just don't want to mess with string formatting right now.
    public enum Color {
        Blue,
        Green,
        Red,
        Yellow,
        Black
    }

    protected enum Type {
        CIRCLE,
        SQUARE,
        TRIANGLE
    }

    Shape() {}

    Shape(String name, Color color, int size) {
        this.name = name;
        this.color = color;
        this.size = size;
        drawMessage();
    }

    public String drawMessage() {
        return String.format("Shape: %s (%s), Number of angles: %d, Color: %s, Size: %d", name, getType(), getCorners(), color, size);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public abstract Type getType();
    public abstract int getCorners();
    public abstract double getArea();
    public abstract double getPerimeter();


}
