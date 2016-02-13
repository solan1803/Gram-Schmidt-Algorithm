public class CGSAlgorithm {

	static final double K = 0.1;
	// a column vectors
	final static double[] a1 = { 1, 0, 0, 0, K };
	final static double[] a2 = { 1, 0, 0, K, 0 };
	final static double[] a3 = { 1, 0, K, 0, 0 };
	final static double[] a4 = { 1, K, 0, 0, 0 };
	final static double[][] aMatrix = { a1, a2, a3, a4 };
	
	static double[][] qMatrix = { null, null, null, null };
	static double[][] vMatrixCGS = { null, null, null, null };
	static double[][][] vMatrixMGS = { null, null, null, null };
	static double[][] vMatrix1 = { null, null, null, null };
	static double[][] vMatrix2 = { null, null, null, null };
	static double[][] vMatrix3 = { null, null, null, null };
	static double[][] vMatrix4 = { null, null, null, null };

	public static void main(String[] args) {

		//classicalGramSchmidtAlgorithm();
		modifiedGramSchmidtAlgorithm();
		// PRINT Q MATRIX
		for (int i = 0; i < qMatrix.length; i++) {
			System.out.print("q" + i + ": ");
			for (int j = 0; j < qMatrix[0].length; j++) {
				System.out.print(qMatrix[i][j]);
				System.out.print(", ");
			}
			System.out.println("");
		}
	}
	
	public static void modifiedGramSchmidtAlgorithm() {
		
		vMatrixMGS[0] = vMatrix1;
		vMatrixMGS[1] = vMatrix2;
		vMatrixMGS[2] = vMatrix3;
		vMatrixMGS[3] = vMatrix4;
		
		vMatrix1[0] = a1;
		vMatrix1[1] = a2;
		vMatrix1[2] = a3;
		vMatrix1[3] = a4;
		
		
		
		qMatrix[0] = scaleVector(vMatrixMGS[0][0], 1 / norm(vMatrixMGS[0][0]));
		
		for (int i = 1; i < aMatrix.length; i++) {
			for (int j = i; j < aMatrix.length; j++) {
				double scale = innerProduct(vMatrixMGS[i-1][j], qMatrix[i-1]);
				double[] scaled = scaleVector(qMatrix[i-1], scale);
				vMatrixMGS[i][j] = vectorOperation(vMatrixMGS[i-1][j], scaled, "-");
			}
			qMatrix[i] = scaleVector(vMatrixMGS[i][i], 1/norm(vMatrixMGS[i][i]));
 		}
		
		
		
	}

	public static void classicalGramSchmidtAlgorithm() {
		vMatrixCGS[0] = aMatrix[0];
		qMatrix[0] = scaleVector(vMatrixCGS[0], 1 / norm(vMatrixCGS[0]));

		for (int i = 1; i < vMatrixCGS.length; i++) {

			vMatrixCGS[i] = calculateV(i);
			qMatrix[i] = scaleVector(vMatrixCGS[i],
					1 / norm(vMatrixCGS[i]));

		}
		
	}

	public static double[] calculateV(int index) {

		double[] aVector = aMatrix[index];

		double[] resultOfSummation = { 0, 0, 0, 0, 0 };
		for (int j = 0; j < index ; j++) {

			double inner = innerProduct(aVector, qMatrix[j]); // < ai, qj>
			
			double[] scaled = scaleVector(qMatrix[j], inner); // <ai, qj>qj
			
			resultOfSummation = vectorOperation(resultOfSummation, scaled, "+"); // total summation over <ai, qj>qj
		}
		

		return vectorOperation(aVector, resultOfSummation, "-"); // ai - summation
	}

	public static double innerProduct(final double[] a, final double[] b) {
		assert (a.length == b.length);
		double total = 0;
		for (int i = 0; i < a.length; i++) {
			total += a[i] * b[i];
		}
		return total;

	}

	public static double[] scaleVector(final double[] a, final double scale) {
		
		double[] copy = new double[a.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = a[i] * scale;
		}

		return copy;

	}

	public static double[] vectorOperation(final double[] a, final double[] b, String op) {
		
		assert(a.length == b.length);
		double[] copy = new double[a.length];
		switch (op) {

		case "+":
			for (int i = 0; i < a.length; i++) {
				copy[i] = a[i] + b[i];
			}
			break;
		case "-":
			for (int i = 0; i < a.length; i++) {
				copy[i] = a[i] - b[i];
			}
			break;
		}

		return copy;
	}

	public static double norm(double[] v) {
		double result = 0;
		for (int i = 0; i < v.length; i++) {
			result += v[i] * v[i];
		}
		return Math.sqrt(result);
	}

}
