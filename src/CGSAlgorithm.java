public class CGSAlgorithm {

	public static void main(String[] args) {

		final double K = 0.1;
		double[] a1 = { 1, 0, 0, 0, K };
		double[] a2 = { 1, 0, 0, K, 0 };
		double[] a3 = { 1, 0, K, 0, 0 };
		double[] a4 = { 1, K, 0, 0, 0 };

		double[][] aMatrix = { a1, a2, a3, a4 };
		double[][] resultCGS = classicalGramSchmidtAlgorithm(aMatrix);
		for (int i = 0; i < resultCGS.length; i++) {
			for (int j = 0; j < resultCGS[0].length; j++) {
				System.out.println(resultCGS[i][j]);
			}
			System.out.println("");
		}
	}

	public static double[][] classicalGramSchmidtAlgorithm(double[][] aMatrix) {
		double[][] qMatrix = { null, null, null, null, null };
		double[][] vMatrix = { null, null, null, null, null };
		vMatrix[0] = aMatrix[0];
		qMatrix[0] = scaleVector(vMatrix[0], 1 / norm(vMatrix[0]));

		for (int i = 2; i < 5; i++) {

			vMatrix[i - 1] = calculateV(i, aMatrix, qMatrix);
			qMatrix[i - 1] = scaleVector(vMatrix[i - 1],
					1 / norm(vMatrix[i - 1]));

		}
		return qMatrix;
	}

	public static double[] calculateV(int index, double[][] aMatrix, double[][] qMatrix) {

		double[] aVector = aMatrix[index - 1];

		double[] resultOfSummation = { 0, 0, 0, 0, 0 };
		for (int j = 1; j <= index ; j++) {

			double inner = innerProduct(aVector, qMatrix[j - 1]);
			double[] scaled = scaleVector(qMatrix[j - 1], inner);
			resultOfSummation = vectorOperation(scaled, qMatrix[j - 1], "+");
		}

		return vectorOperation(aVector, resultOfSummation, "-");
	}

	public static double innerProduct(double[] a, double[] b) {
		assert (a.length == b.length);
		double total = 0;
		for (int i = 0; i < a.length; i++) {
			total += a[i] * b[i];
		}
		return total;

	}

	public static double[] scaleVector(double[] a, double scale) {

		for (int i = 0; i < a.length; i++) {
			a[i] *= scale;
		}

		return a;

	}

	public static double[] vectorOperation(double[] a, double[] b, String op) {

		switch (op) {

		case "+":
			for (int i = 0; i < a.length; i++) {
				a[i] = a[i] + b[i];
			}
			break;
		case "-":
			for (int i = 0; i < a.length; i++) {
				a[i] = a[i] - b[i];
			}
			break;
		}

		return a;
	}

	public static double norm(double[] v) {
		double result = 0;
		for (int i = 0; i < v.length; i++) {
			result += v[i] * v[i];
		}
		return Math.sqrt(result);
	}

}
