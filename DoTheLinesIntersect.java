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
		
		for (int i = 0; i < testCases; i++) {
			
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
			//not a system of two parametric equations have a solution.
			boolean kramersBoolean = kramersRuleBool(lineAChangeInX, lineBChangeInX, lineAChangeInY, lineBChangeInY, startingPointBx-startingPointAx, startingPointBy-startingPointAy);
			
			//Create a flag variable that will be used during the coincidental line test.
			boolean doTheyIntersect = false;
			
			//System.out.println(kramersBoolean);
			
			//It is not enough to just test kramer's rule because the lines could be collinear,
			//therefore this must be tested to see whether or not the line segments actually
			//intersect: if kramersRuleBool gives a true value then the two line segments do 
			//intersect.
			if (kramersBoolean == true)
				System.out.println("Move to the left or right!");
			//Otherwise, if both starting points have the same x or y values, the lines could be
			//collinear and need to be tested.
			else /*if (startingPointAx == startingPointBx || startingPointAy == startingPointBy)*/ {
				if (areTheLinesCollinear(startingPointAx, startingPointAy, endingPointAx, endingPointAy, endingPointBx, endingPointBy) == true) {
					//even if the line segments are collinear and are part of the same linear
					//plane, they may not intersect at any point, therefore this must be tested.
					doTheyIntersect = areTheLinesCoincidental(startingPointAx, startingPointAy, endingPointAx, endingPointAy, startingPointBx, startingPointBy, endingPointBx, endingPointBy);
					if (doTheyIntersect == true)
						System.out.println("Move to the left or right!");
					else
						System.out.println("Good picture, Birdman!");
				} else
					System.out.println("Good picture, Birdman!");
			} /*else
				System.out.println("Good picture, Birdman!");*/
			
		}
	}
	
	//Tests two collinear line segments to see if they are coincidental.
	public static boolean areTheLinesCoincidental(int startAx, int startAy, int endAx, int endAy, int startBx, int startBy, int endBx, int endBy) {
		//Since we know that the two line segments are located on the same linear plane, they
		//must have the same slope, therefore using the parametric equations of the first line,
		//we can test both end points of the other line against those equations to see if the
		//segments intersect at any point. 
		int changeInAx = endAx - startAx;
		int changeInAy = endAy - startAy;
		
		//Equations: X = startAx + changeInAx(lambda) and Y = startAy + changeInAy(lambda)
		//Solve for lambda in the above equations by plugging in the x and y values of the end
		//points of the second line segment.
		double lambdaX = ((double)startBx - (double)startAx) / changeInAx;
		double lambdaY = ((double)startBy - (double)startAy) / changeInAy;
		
		//If both of the lambda values are less than 0 or greater than 1, there is no
		//intersection points, otherwise there is at least one intersection point.
		if ((lambdaX < 0 && lambdaY < 0) || (lambdaX > 1 && lambdaY > 1))
			return false;
		else
			return true;
	}
	
	//Tests if two line segments are collinear: Takes both the starting point and ending point
	//of one line along with the ending point of the other and creates two new line segments,
	//then uses the cross product to determine whether or not the points are colinear or not.
	public static boolean areTheLinesCollinear(int X1, int Y1, int X2, int Y2, int X3, int Y3) {
		//create two vectors off of the starting point and find their i and j values.
		int vectorAi = X2 - X1;
		int vectorAj = Y2 - Y1;
		int vectorBi = X3 - X1;
		int vectorBj = Y3 - Y1;
		
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
	
	//Equations in the form of: UxLambda - VxMu = X2 - X1 (ax + by = c)
	//							UyLambda - VyMu = Y2 - Y1 (dx + ey = f)
	//(where Ux = ax, Vx = by, X2-X1 = c, Uy = dx, Vy = ey, and Y2-Y1 = f)
	public static boolean kramersRuleBool(int ax, int by, int dx, int ey, int c, int f) {
		/*System.out.println("ax=" + ax + " by=" + by + " c=" + c);
		System.out.println("dx=" + dx + " ey=" + ey + " f=" + f);
		System.out.println();*/
		
		//create and fill a 2 by 2 matrix with the lambda(x)/lambda(y) and mu(x)/mu(y) values
		//found in a parametric equations of two lines.
		int[][] denom = new int[2][2];
		denom[0][0] = ax;
		denom[0][1] = by*-1;
		denom[1][0] = dx;
		denom[1][1] = ey*-1;
		
		//test what would be the denominator of the Kramer's equation for x/y and see whether or
		//not the absolute value of the determinant of the denominator is 0. If it is, there are
		//no unique solutions to the system of equations. If the denominator is not 0, there is at
		//least one unique solution to the system.
		if (determinant2x2(denom) == 0 /*&& (c/f) != (ax/dx)*/)
			return false;
		else {
			//create numerator matricies and actually solve for lambda and mu using kramers:
			int [][] numerator1 = new int[2][2];
			int [][] numerator2 = new int[2][2];
			numerator1[0][0] = c;
			numerator1[0][1] = by*-1;
			numerator1[1][0] = f;
			numerator1[1][1] = ey*-1;
			numerator2[0][0] = ax;
			numerator2[0][1] = c;
			numerator2[1][0] = dx;
			numerator2[1][1] = f;
			double lambda = (double)determinant2x2(numerator1) / (double)determinant2x2(denom);
			double mu = (double)determinant2x2(numerator2) / (double)determinant2x2(denom);
			
			/*System.out.println("l: " + lambda);
			System.out.println("m: " + mu);
			System.out.println();*/
			
			/*for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					System.out.print(numerator2[i][j] + " ");
				}
				System.out.println();
			}*/
			
			//lambda and mu must both have values in between 0 and 1 inclusive for the line
			//to have an intersection.
			if ((lambda >= 0 && lambda <= 1) && (mu >= 0 && mu <= 1))
				return true;
			else
				return false;
		}
	}
	
	public static int determinant2x2(int[][] matrix) {
		return (matrix[0][0]*matrix[1][1]) - (matrix[0][1]*matrix[1][0]);
	}
}
