package recitation8BirdmanOfWaikiki;

import java.util.Scanner;

/**
 * DoTheLinesIntersect.java
 * 
 * @author Miles
 * @version 7/28/14
 */
public class DoTheLinesIntersect {
	
	public static void main(String[] Args) {
		Scanner input = new Scanner(System.in);
		
		int testCases = input.nextInt();
		
			//scan in the x and y locations of the birdman, which will be starting point A, and
			//the sunset which will be ending point A. Then scan in the x and y locations of the
			//starting and ending points of the person being photographed.
			int startingPointAx = input.nextInt();
			int startingPointAy = input.nextInt();
			int endingPointAx = input.nextInt();
			int endingPointAy = input.nextInt();
			int startingPointBx = input.nextInt();
			int startingPointBy = input.nextInt();
			int endingPointBx = input.nextInt();
			int endingPointBy = input.nextInt();
			
			//figure out the change in x/y values for each line segment.
			int lineAChangeInX = endingPointAx - startingPointAx;
			int lineAChangeInY = endingPointAy - startingPointAy;
			int lineBChangeInX = endingPointBx - startingPointBx;
			int lineBChangeInY = endingPointBy - startingPointBy;
			
			/*System.out.println("lineAChangeInX:" + lineAChangeInX);
			System.out.println("lineAChangeInY:" + lineAChangeInY);
			System.out.println("lineBChangeInX:" + lineBChangeInX);
			System.out.println("lineBChangeInY:" + lineBChangeInY);*/
			
			//Call kramersRuleBool which will output a true or false value stating whether or
			boolean kramersBoolean = kramersRuleBool(lineAChangeInX, lineBChangeInX, lineAChangeInY, lineBChangeInY);
			
			//System.out.println(kramersBoolean);
			
			//It is not enough to just test kramer's rule because the lines could be colinear,
			//therefore this must be tested to see whether or not the line segments actually
			//intersect.
			
	}
	
	//Tests if two line segments are collinear: Takes both the starting point and ending point
	//of one line along with the ending point of the other and creates two new line segments,
	//then uses the cross product to determine whether or not the points are colinear or not.
	public static boolean areTheLinesColinear(int X1, int Y1, int X2, int Y2, int X3, int Y3) {
		//create two vectors off of the starting point and find their i and j values.
		int vectorAi = X2 - X1;
		int vectorAj = Y2 - Y1;
		int vectorBi = X3 - X1;
		int vectorBj = X3 - X1;
		
		//create/fill a matrix for use with determinant2x2 method(the same as the cross-product).
		int[][] matrix = new int[2][2];
		matrix[0][0] = vectorAi;
		matrix[0][1] = vectorAj;
		matrix[1][0] = vectorBi;
		matrix[1][1] = vectorBj;
		
		int crossProduct = determinant2x2(matrix);
		
		//if the crossProduct of the two vectors yields 0 then the vectors are collinear.
		if(crossProduct == 0)
			return true;
		else
			return false;
	}
	
	//Equations in the form of: UxLambda - VxMu = X2 - X1
	//							UyLambda - VyMu = Y2 - Y1
	//(where Ux = ax, Vx = by, X2-X1 = c, Uy = dx, Vy = ey, and Y2-Y1 = f)
	public static boolean kramersRuleBool(int ax, int by, int dx, int ey) {
		//create and fill a 2 by 2 matrix with the lambda(x)/lambda(y) and mu(x)/mu(y) values
		//found in a parametric equations of two lines.
		int[][] matrix = new int[2][2];
		matrix[0][0] = ax;
		matrix[0][1] = by;
		matrix[1][0] = dx;
		matrix[1][1] = ey;
		
		//test what would be the denominator of the Kramer's equation for x/y and see whether or
		//not the absolute value of the determinant of the denominator is 0. If it is, there are
		//no unique solutions to the system of equations. If the denominator is not 0, there is at
		//least one unique solution to the system.
		if (determinant2x2(matrix) == 0)
			return false;
		else
			return true;
	}
	
	public static int determinant2x2(int[][] matrix) {
		return (matrix[0][0]*matrix[1][1]) - (matrix[0][1]*matrix[1][0]);
	}
}
