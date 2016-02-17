/* 
 *  Class: GramSchmidt
 *  Usage: Contains the algorithms for classical gram schmidt and modified gram
 *         schmidt. Comment out one of the lines of code in main to run either
 *         one of the algorithms. 
 *  M2AA3 Coursework 1 : Manivannan Solan
 *                       JMC Year 2
 *                       CID: 00929139
 */

public class GramSchmidt {

	static final double K = 0.0000000001;
	// a column vectors
	final static double[] a1 = { 1, 0, 0, 0, K };
	final static double[] a2 = { 1, 0, 0, K, 0 };
	final static double[] a3 = { 1, 0, K, 0, 0 };
	final static double[] a4 = { 1, K, 0, 0, 0 };
	final static double[][] aMatrix = { a1, a2, a3, a4 };

	static double[][] qMatrixTranspose = { null, null, null, null };
	static double[][] vMatrix1 = { a1, a2, a3, a4 };
	static double[][] vMatrix2 = { null, null, null, null };
	static double[][] vMatrix3 = { null, null, null, null };
	static double[][] vMatrix4 = { null, null, null, null };
	static double[][] vMatrixCGS = { null, null, null, null };
	static double[][][] vMatrixMGS = { vMatrix1, vMatrix2, vMatrix3, vMatrix4 };

	public static void main(String[] args) {

		//cgsAlgorithm();
		mgsAlgorithm();
		printQMatrix();
		
		System.out.println("\nPrinting part b\n");
		partBPrint();
	}
	
	public static void printQMatrix() {
		for (int i = 0; i < qMatrixTranspose.length; i++) {
			System.out.print("q" + i + ": [ ");
			for (int j = 0; j < qMatrixTranspose[0].length; j++) {
				System.out.print(qMatrixTranspose[i][j]);
				if (j != qMatrixTranspose[0].length - 1) {
					System.out.print(" , ");
				} else {
					System.out.print(" ]");
				}
			}
			System.out.println("");
		}
	}
	
	public static void partBPrint() {
		int n = qMatrixTranspose[0].length - 1;
		double[] v1 = new double[n];
		double[] v2 = new double[n];
		double[] v3 = new double[n];
		double[] v4 = new double[n];
		double[][] result = { v1, v2, v3, v4 };
		for (int i = 0; i < result.length; i++) {
			System.out.print("[ ");
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] = VectorUtils.innerProduct(qMatrixTranspose[i], qMatrixTranspose[j]);
				if (i == j) 
					result[i][j]--;
				System.out.print(result[i][j]);
				if (j != result[0].length - 1) {
					 System.out.print(" , ");
				} else {
					System.out.print(" ]");
				}
			}
			System.out.println("");
		}
		
	}

	public static void mgsAlgorithm() {

		// vMatrixMGS has already been initialised.

		qMatrixTranspose[0] = VectorUtils.scaleVector(vMatrixMGS[0][0], 1 / VectorUtils.norm(vMatrixMGS[0][0]));

		for (int i = 1; i < aMatrix.length; i++) {
			for (int j = i; j < aMatrix.length; j++) {
				double scale = VectorUtils.innerProduct(vMatrixMGS[i - 1][j], qMatrixTranspose[i - 1]);
				double[] scaled = VectorUtils.scaleVector(qMatrixTranspose[i - 1], scale);
				vMatrixMGS[i][j] = VectorUtils.vectorSubtraction(vMatrixMGS[i - 1][j], scaled);
			}
			qMatrixTranspose[i] = VectorUtils.scaleVector(vMatrixMGS[i][i], 1 / VectorUtils.norm(vMatrixMGS[i][i]));
		}

	}

	public static void cgsAlgorithm() {
		vMatrixCGS[0] = aMatrix[0];
		qMatrixTranspose[0] = VectorUtils.scaleVector(vMatrixCGS[0], 1 / VectorUtils.norm(vMatrixCGS[0]));

		for (int i = 1; i < vMatrixCGS.length; i++) {

			vMatrixCGS[i] = vCalcCGS(i);
			qMatrixTranspose[i] = VectorUtils.scaleVector(vMatrixCGS[i], 1 / VectorUtils.norm(vMatrixCGS[i]));

		}
	}

	public static double[] vCalcCGS(int index) {
		double[] aVector = aMatrix[index];
		double[] sumVector = new double[5];
		for (int j = 0; j < index; j++) {
			double innerProductCalc = VectorUtils.innerProduct(aVector, qMatrixTranspose[j]); 
			double[] scaledVector = VectorUtils.scaleVector(qMatrixTranspose[j], innerProductCalc); 
			sumVector = VectorUtils.vectorAddition(sumVector, scaledVector); 
		}
		return VectorUtils.vectorSubtraction(aVector, sumVector); 
	}

}
