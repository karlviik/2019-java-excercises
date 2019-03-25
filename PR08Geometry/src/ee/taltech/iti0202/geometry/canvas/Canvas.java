package ee.taltech.iti0202.geometry.canvas;

import ee.taltech.iti0202.geometry.shapes.Shape;

import java.util.ArrayList;

public class Canvas {
    private String name;
    private ArrayList<Shape> shapes = new ArrayList<>();

    public Canvas(String name) {
        this.name = name;
    }

    // returns true if shape was added, false if it already existed
    public boolean draw(Shape shape) {
        if (!shapes.contains(shape)) {
            shapes.add(shape);
            System.out.println(String.format("Shape %s was drawn successfully!", shape.getName()));
            return true;
        } else {
            System.out.println(String.format("Shape %s was not drawn because it already exists on the canvas! " +
                    "Yeah, no copypasta in this neighbourhood!", shape.getName()));
            return false;
        }
    }

    public int clear() {
        int removeCount = shapes.size();
        shapes = new ArrayList<>();
        System.out.println(String.format("Removed %d shapes from the canvas!", removeCount));
        return removeCount;
    }

    public boolean eraseShape (Shape shape) {
        if (shapes.contains(shape)) {
            shapes.remove(shape);
            System.out.println(String.format("Successfully erased %s from the canvas!", shape.getName()));
            return true;
        } else {
            System.out.println(String.format("Couldn't erase %s from the canvas because it's not on there!",
                    shape.getName()));
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList getShapes() {
        return shapes;
    }

    public void printShapes() {
        for (Shape shape : shapes) {
            System.out.println(shape.drawMessage());
        }
    }
}
