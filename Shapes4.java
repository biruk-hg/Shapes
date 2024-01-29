import java.lang.Math;
import java.io.*;
import java.util.*;

// Define an interface with methods for x, y, printing, and distance
interface Interface {
    public int getX();
    public int getY();
    public void print();
    public double distance(Interface p1);
}

// Define an abstract class with three points and abstract methods for area and perimeter
abstract class Abstract {
    public Interface p1;
    public Interface p2;
    public Interface p3;

    // Abstract methods to be implemented by subclasses
    public abstract double getArea();
    public abstract double getPerimeter();

    // Print method to print the three points
    public void print() {
        p1.print();
        p2.print();
        p3.print();
    }
}

// Implementation of the Interface for a 2D point
class Point implements Interface {
    private int x;
    private int y;

    // Constructor to initialize the point with x and y coordinates
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter methods for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Print method to display the point's coordinates
    public void print() {
        System.out.println("{" + getX() + "," + getY() + "}");
    }

    // Method to calculate distance between two points
    public double distance(Interface p) {
        int a = x - p.getX();
        int b = y - p.getY();
        double d = Math.sqrt((a * a) + (b * b));
        return d;
    }
}

// Triangle class extending the Abstract class
class Triangle extends Abstract {

    // Constructor to initialize three points of the triangle
    public Triangle(Interface p1, Interface p2, Interface p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Implementation of abstract method to calculate area of the triangle
    public double getArea() {
        int x1 = p1.getX();
        int y1 = p1.getY();

        int x2 = p2.getX();
        int y2 = p2.getY();

        int x3 = p3.getX();
        int y3 = p3.getY();

        int side1 = x1 * (y2 - y3);
        int side2 = x2 * (y3 - y1);
        int side3 = x3 * (y1 - y2);

        return Math.abs((1.0 / 2) * (side1 + side2 + side3));
    }

    // Implementation of abstract method to calculate perimeter of the triangle
    public double getPerimeter() {
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        return (side1 + side2 + side3);
    }

    // Print method to print the three points
    public void print() {
        super.print(); // Call the print method of the superclass
    }

    // Method to check if the triangle is right-angled
    public boolean isRightAngled() {
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        if ((side1 * side1 + side2 * side2) == side3 * side3)
            return true;
        if ((side2 * side2 + side3 * side3) == side1 * side1)
            return true;
        if ((side3 * side3 + side1 * side1) == side2 * side2)
            return true;
        return false;
    }
}

// Rectangle class extending the Triangle class
class Rectangle extends Triangle {
    private Interface p4;

    // Constructor to initialize four points of the rectangle
    public Rectangle(Interface p1, Interface p2, Interface p3, Interface p4) {
        super(p1, p2, p3); // Call the constructor of the Triangle (the 'super' class)
        this.p4 = p4;
    }

    // Implementation of abstract method to calculate area of the rectangle
    public double getArea() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        return length * width;
    }

    // Implementation of abstract method to calculate perimeter of the rectangle
    public double getPerimeter() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        return 2 * (length + width);
    }

    // Print method to print the four points
    public void print() {
        super.print(); // Call the print method of the superclass
        p4.print();
    }

    // Method to check if the rectangle is a square
    public boolean isSquare() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        if (length == width)
            return true;
        return false;
    }
}

// Main class for testing the shapes
public class Shapes4 {
    public static void main(String[] args) {

        // Sample data for rectangles and triangles
        int[][][] rectanglesArray = {
                {{1, 3}, {1, 10}, {5, 10}, {5, 3}},
                {{2, 3}, {2, 7}, {6, 7}, {6, 3}},
                {{3, 3}, {5, 1}, {5, 4}, {6, 7}},
                {{1, 10}, {5, 10}, {5, 3}, {1, 3}},
                {{3, 4}, {3, 6}, {1, 6}, {1, 4}}
        };
        int[][][] trianglesArray = {
                {{3, 7}, {1, 5}, {1, 9}},
                {{5, 1}, {1, 1}, {1, 4}},
                {{1, 3}, {1, 9}, {1, 11}},
                {{5, 7}, {1, 5}, {1, 9}}
        };

        // Creating arrays of rectangles and triangles using the sample data
        Rectangle[] rectangles = getRectangles(rectanglesArray);
        Triangle[] triangles = getTriangles(trianglesArray);

        // Printing squares and right-angled triangles
        printSquares(rectangles);
        printRightAngledTriangles(triangles);
    }

    // Method to print squares from an array of rectangles
    public static void printSquares(Rectangle[] rectangles) {
        System.out.println("These are the squares:");
        for (int i = 0; i < rectangles.length; i++) {
            if (rectangles[i].isSquare()) {
                rectangles[i].print();
            }
        }
    }

    // Method to print right-angled triangles from an array of triangles
    public static void printRightAngledTriangles(Triangle[] triangles) {
        System.out.println("These are the right-angled triangles:");
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].isRightAngled()) {
                triangles[i].print();
            }
        }
    }

    // Method to create an array of rectangles from a 3D array of rectangle coordinates
    public static Rectangle[] getRectangles(int[][][] rectanglesArray) {
        Rectangle[] rectangles = new Rectangle[5];
        for (int i = 0; i < rectangles.length; i++) {
            Point p1 = new Point(rectanglesArray[i][0][0], rectanglesArray[i][0][1]);
            Point p2 = new Point(rectanglesArray[i][1][0], rectanglesArray[i][1][1]);
            Point p3 = new Point(rectanglesArray[i][2][0], rectanglesArray[i][2][1]);
            Point p4 = new Point(rectanglesArray[i][3][0], rectanglesArray[i][3][1]);
            Rectangle r = new Rectangle(p1, p2, p3, p4);
            rectangles[i] = r;
        }
        return rectangles;
    }

    // Method to create an array of triangles from a 3D array of triangle coordinates
    public static Triangle[] getTriangles(int[][][] trianglesArray) {
        Triangle[] triangles = new Triangle[4];
        for (int i = 0; i < triangles.length; i++) {
            Point p1 = new Point(trianglesArray[i][0][0], trianglesArray[i][0][1]);
            Point p2 = new Point(trianglesArray[i][1][0], trianglesArray[i][1][1]);
            Point p3 = new Point(trianglesArray[i][2][0], trianglesArray[i][2][1]);
            Triangle t = new Triangle(p1, p2, p3);
            triangles[i] = t;
        }
        return triangles;
    }
}
