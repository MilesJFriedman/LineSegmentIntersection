package recitation8BirdmanOfWaikiki;

public class DoTheLinesIntersect {
	
	public static void main(String[] Args) {
		
	}
	
	public static boolean kramersRuleBool(int ax, int by, int dx, int ey) {
		//create and fill a 2 by 2 matrix with the delta(x)/delta(y) and mu(x)/mu(y) values
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
