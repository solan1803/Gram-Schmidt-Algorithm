/*
 *   Class: VectorUtils
 *   Usage: Helper methods needed for calculations in classical gram schmidt and
 *          modified gram schmidt algorithms. 
 *   M2AA3 Coursework 1 : Manivannan Solan 
 *   					  JMC Year 2
 *   					  CID: 00929139 
 */
public class VectorUtils {

	/* 
	 *  Method: innerProduct
	 *  Usage: Calculates the dot product of two vectors, checks whether vectors 
	 *         are the same length before calculation. 
	 */
	public static final double innerProduct(final double[] a, final double[] b) {
		assert (a.length == b.length);
		double total = 0;
		for (int i = 0; i < a.length; i++) {
			total += a[i] * b[i];
		}
		return total;
	}

	/* 
	 *  Method: scaleVector
	 *  Usage: Multiplies each component of the vector by the scale value; does 
	 *         not change the original vector. 
	 */
	public static final double[] scaleVector(final double[] a, final double scale) {
		double[] result = new double[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i] * scale;
		}
		return result;
	}

	/* 
	 *  Method: vectorAddition 
	 *  Usage: Adds two vectors together without modifying them. Makes sure both
	 *         vectors are the same length before calculation. 
	 */
	public static final double[] vectorAddition(final double[] a, final double[] b) {
		assert (a.length == b.length);
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] + b[i];
		}
		return result;
	}
	
	/*
	 *  Method: vectorSubtraction
	 *  Usage: Subtracts two vectors without modifying them. Makes sure both
	 *         vectors are the same length before calculation. 
	 */
	public static final double[] vectorSubtraction(final double[] a, final double[] b) {
		assert (a.length == b.length);
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] - b[i];
		}
		return result;
	}

	/* 
	 *  Method: norm
	 *  Usage: Calculates the length of a vector. 
	 */
	public static double norm(double[] v) {
		double result = 0;
		for (int i = 0; i < v.length; i++) {
			result += v[i] * v[i];
		}
		return Math.sqrt(result);
	}
	
}
