// Importing necessary Java libraries
import java.lang.Math;
import java.io.*;
import java.util.*;

// Definition of the Point class representing a 2D point with x and y coordinates
class Point {
    private int x;
    private int y;

    // Constructor to initialize a Point with given x and y coordinates
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter method to retrieve the x-coordinate of the Point
    public int getX() {
        return x;
    }

    // Getter method to retrieve the y-coordinate of the Point
    public int getY() {
        return y;
    }

    // Method to print the coordinates of the Point
    public void print() {
        System.out.println("{" + getX() + "," + getY() + "}");
    }

    // Method to calculate and return the distance between two points
    public double distance(Point p) {
        int a = x - p.getX();
        int b = y - p.getY();
        // Using the Euclidean distance formula: sqrt((a^2) + (b^2))
        double d = Math.sqrt((a * a) + (b * b));
        return d;
    }
}

// Definition of the Triangle class representing a triangle using three points
class Triangle {
    public Point p1;
    public Point p2;
    public Point p3;

    // Constructor to initialize a Triangle with three specified points
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Method to calculate and return the area of the triangle using the shoelace formula
    public double getArea() {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int x3 = p3.getX();
        int y3 = p3.getY();

        // Applying the shoelace formula for area calculation
        int side1 = x1 * (y2 - y3);
        int side2 = x2 * (y3 - y1);
        int side3 = x3 * (y1 - y2);

        // Using the absolute value to ensure a positive area
        return Math.abs((1.0 / 2) * (side1 + side2 + side3));
    }

    // Method to calculate and return the perimeter of the triangle
    public double getPerimeter() {
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        return (side1 + side2 + side3);
    }

    // Method to print the coordinates of all three points of the triangle
    public void print() {
        p1.print();
        p2.print();
        p3.print();
    }

    // Method to check if the triangle is a right-angled triangle
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

// Definition of the Rectangle class, extending the Triangle class
class Rectangle extends Triangle {
    private Point p4;

    // Constructor to initialize a Rectangle with four specified points
    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3); // calls constructor of Triangle (the 'super' class)
        this.p4 = p4;
    }

    // Overridden method to calculate and return the area of the rectangle
    public double getArea() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        return length * width;
    }

    // Overridden method to calculate and return the perimeter of the rectangle
    public double getPerimeter() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        return 2 * (length + width);
    }

    // Overridden method to print the coordinates of all four points of the rectangle
    public void print() {
        super.print();
        p4.print();
    }

    // Method to check if the rectangle is a square by comparing the lengths of opposite sides
    public boolean isSquare() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        if (length == width)
            return true;
        return false;
    }
}

// Main class that contains the program's entry point and various functions
public class Shapes2 {
    public static void main(String[] args) {
        // Arrays containing coordinates for rectangles and triangles
        int[][][] rectanglesArray = {
            {{1,3},{1,10},{5,10},{5,3}},
            {{2,3},{2,7},{6,7},{6,3}},
            {{3,3},{5,1},{5,4},{6,7}},
            {{1,10},{5,10},{5,3},{1,3}},
            {{3,4},{3,6},{1,6},{1,4}}
        };
        int[][][] trianglesArray = {
            {{3,7},{1,5},{1,9}},
            {{5,1},{1,1},{1,4}},
            {{1,3},{1,9},{1,11}},
            {{5,7},{1,5},{1,9}}
        };

        // Create arrays of Rectangle and Triangle objects using the coordinates arrays
        Rectangle[] rectangles = getRectangles(rectanglesArray);
        Triangle[] triangles = getTriangles(trianglesArray);

        // Print squares and right-angled triangles based on certain conditions
        printSquares(rectangles);
        printRightAngledTriangles(triangles);
    }

    // Function to print squares from an array of rectangles
    public static void printSquares(Rectangle[] rectangles) {
        System.out.println("These are the squares:");
        for (int i = 0; i < rectangles.length; i++) {
            if (rectangles[i].isSquare()) {
                rectangles[i].print();
            }
        }
    }

    // Function to print right-angled triangles from an array of triangles
    public static void printRightAngledTriangles(Triangle[] triangles) {
        System.out.println("These are the right-angled triangles:");
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].isRightAngled()) {
                triangles[i].print();
            }
        }
    }

    // Function to convert a 3D array of rectangle coordinates into an array of Rectangle objects
    public static Rectangle[] getRectangles(int[][][] rectanglesArray) {
        Rectangle[] rectangles = new Rectangle[5];
        for (int i = 0; i < rectangles.length; i++) {
            // Extract coordinates for each corner of the rectangle
            Point p1 = new Point(rectanglesArray[i][0][0], rectanglesArray[i][0][1]);
            Point p2 = new Point(rectanglesArray[i][1][0], rectanglesArray[i][1][1]);
            Point p3 = new Point(rectanglesArray[i][2][0], rectanglesArray[i][2][1]);
            Point p4 = new Point(rectanglesArray[i][3][0], rectanglesArray[i][3][1]);

            // Create Rectangle object and add it to the array
            Rectangle r = new Rectangle(p1, p2, p3, p4);
            rectangles[i] = r;
        }
        return rectangles;
    }

    // Function to convert a 3D array of triangle coordinates into an array of Triangle objects
    public static Triangle[] getTriangles(int[][][] trianglesArray) {
        Triangle[] triangles = new Triangle[4];
        for (int i = 0; i < triangles.length; i++) {
            // Extract coordinates for each vertex of the triangle
            Point p1 = new Point(trianglesArray[i][0][0], trianglesArray[i][0][1]);
            Point p2 = new Point(trianglesArray[i][1][0], trianglesArray[i][1][1]);
            Point p3 = new Point(trianglesArray[i][2][0], trianglesArray[i][2][1]);

            // Create Triangle object and add it to the array
            Triangle t = new Triangle(p1, p2, p3);
            triangles[i] = t;
        }
        return triangles;
    }
}
