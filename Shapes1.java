// Importing necessary Java libraries
import java.lang.Math;
import java.io.*;
import java.util.*;

// Definition of the Point class
class Point {
    // Private instance variables to represent the coordinates of the point
    private int x;
    private int y;

    // Constructor to initialize the Point object with specified coordinates
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

    // Method to print the coordinates of the Point in a human-readable format
    public void print() {
        System.out.println("{" + getX() + "," + getY() + "}");
    }

    // Method to calculate the distance between two points using the Euclidean distance formula
    public double distance(Point p) {
        // Calculate the differences in x and y coordinates
        int a = x - p.getX();
        int b = y - p.getY();

        // Use the Euclidean distance formula: sqrt((a^2) + (b^2))
        double d = Math.sqrt((a * a) + (b * b));

        // Return the calculated distance
        return d;
    }
}

// Definition of the Triangle class
class Triangle {
    // Private instance variables to represent the three vertices of the triangle
    private Point p1;
    private Point p2;
    private Point p3;

    // Constructor to initialize the Triangle object with three specified points
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Method to calculate and return the area of the triangle using the shoelace formula
    public double getArea() {
        // Extracting coordinates of each point
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
        // Calculating the lengths of each side using the distance method of the Point class
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        
        // Return the sum of all three sides as the perimeter
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
        // Calculating the lengths of each side using the distance method of the Point class
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);

        // Checking Pythagorean theorem for all three combinations of sides
        if ((side1 * side1 + side2 * side2) == side3 * side3)
            return true;
        if ((side2 * side2 + side3 * side3) == side1 * side1)
            return true;
        if ((side3 * side3 + side1 * side1) == side2 * side2)
            return true;

        // If none of the conditions are met, it's not a right-angled triangle
        return false;
    }
}

// Definition of the Rectangle class
class Rectangle {
    // Private instance variables to represent the four corners of the rectangle
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    // Constructor to initialize the Rectangle object with four specified points
    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    // Method to calculate and return the area of the rectangle
    public double getArea() {
        // Calculate the length and width of the rectangle using distance method of Point class
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        
        // Return the product of length and width as the area
        return length * width;
    }

    // Method to calculate and return the perimeter of the rectangle
    public double getPerimeter() {
        // Calculate the length and width of the rectangle using distance method of Point class
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        
        // Return twice the sum of length and width as the perimeter
        return 2 * (length + width);
    }

    // Method to print the coordinates of all four points of the rectangle
    public void print() {
        p1.print();
        p2.print();
        p3.print();
        p4.print();
    }

    // Method to check if the rectangle is a square by comparing the lengths of opposite sides
    public boolean isSquare() {
        double length = p1.distance(p2);
        double width = p1.distance(p4);
        
        // Check if the length and width are equal
        if (length == width)
            return true;
        
        // If not equal, it's not a square
        return false;
    }
}

// Definition of the Shapes class
public class Shapes1 {
    // Main method where the execution of the program begins
    public static void main(String[] args) {

        // Array containing coordinates for rectangles
        int[][][] rectanglesArray = {
            {{1,3},{1,10},{5,10},{5,3}},
            {{2,3},{2,7},{6,7},{6,3}},
            {{3,3},{5,1},{5,4},{6,7}},
            {{1,10},{5,10},{5,3},{1,3}},
            {{3,4},{3,6},{1,6},{1,4}}
        };

        // Array containing coordinates for triangles
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

    // Method to print squares from an array of rectangles
    public static void printSquares(Rectangle[] rectangles) {
        System.out.println("These are the squares:");
        for(int i = 0; i < rectangles.length; i++) {
            if(rectangles[i].isSquare()) {
                rectangles[i].print();
            }
        }
    }

    // Method to print right-angled triangles from an array of triangles
    public static void printRightAngledTriangles(Triangle[] triangles) {
        System.out.println("These are the right-angled triangles:");
        for(int i = 0; i < triangles.length; i++) {
            if(triangles[i].isRightAngled()) {
                triangles[i].print();
            }
        }
    }

    // Method to convert a 3D array of rectangle coordinates into an array of Rectangle objects
    public static Rectangle[] getRectangles(int[][][] rectanglesArray) {
        Rectangle[] rectangles = new Rectangle[5];
        for(int i = 0; i < rectangles.length; i++) {
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

    // Method to convert a 3D array of triangle coordinates into an array of Triangle objects
    public static Triangle[] getTriangles(int[][][] trianglesArray) {
        Triangle[] triangles = new Triangle[4];
        for(int i = 0; i < triangles.length; i++) {
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
