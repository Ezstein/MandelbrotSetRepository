package mandelbrotSet;

import java.math.BigDecimal;

/**
 * 
 * This class performs the basic testing of a point, z, to see if it is in the Mandelbrot or Julia set.
 * The seed of the  MandelbrotSet is always zero.
 * This test a point in either double precision or in arbitrary precision using the BigDecimal and ComplexBigDecimal classes.
 * The value returned is zero if it is in the Mandelbrot set,
 * or the number of iterations required before z leaves the closed disk of radius 2.
 * 
 * @author Ezra Stein
 * @version 1.0
 * @since 2015
 */
public class MandelbrotFunction
{
	/**
	 * Tests to see whether a number z, is in the Mandelbrot or Julia set for a given seed.
	 * To produce the Mandelbrot set, the calculator iterates over different values of z
	 * while the seed remains zero.
	 * To produce a Julia set, the calculator iterates over different seeds for
	 * a given value of z which is referred to in other classes as the "seedOfJuliaX/Y"
	 * 
	 * @param z				The number which is added to the seed,
	 * 						and added to each subsequent term to produce the next term.
	 * @param seed			The seed which is used to start the iteration.
	 * @param maxIterations	The maximum number of iterations used before determining the point to be in the set.
	 * @return				0 if the point is in the set, or the number of iterations (from 1-maxIterations)
	 * 						for the orbit of the tested point to escape past a disk of radius 2.
	 */
	public static int testPoint(Complex z, Complex seed, int maxIterations)
	{
		Complex zNew;
		Complex zOld = seed;
		int i = 1;
		while(i<= maxIterations)
		{
			zNew = z.add(zOld.square());
			if(zNew.ABS()> 2)
			{
				return i;
			}
			zOld = zNew;
			i++;
		}
		return 0;
	}
	
	/**
	 * This function does the same thing as {@code testPoint(Complex z, Complex seed, int maxIterations)} except it
	 * uses the arbitrary precision of BigDecimal values.
	 * Tests to see whether a number z, is in the Mandelbrot or Julia set for a given seed.
	 * To produce the Mandelbrot set, the calculator iterates over different values of z
	 * while the seed remains zero.
	 * To produce a Julia set, the calculator iterates over different seeds for
	 * a given value of z which is referred to in other classes as the "seedOfJuliaX/Y"
	 * 
	 * @param z				The number which is added to the seed,
	 * 						and added to each subsequent term to produce the next term.
	 * @param seed			The seed which is used to start the iteration.
	 * @param maxIterations	The maximum number of iterations used before determining the point to be in the set.
	 * @return				0 if the point is in the set, or the number of iterations (from 1-maxIterations)
	 * 						for the orbit of the tested point to escape past a disk of radius 2.
	 */
	public static int testPointBigDecimal(ComplexBigDecimal z, ComplexBigDecimal seed, int maxIterations)
	{
		ComplexBigDecimal zNew;
		ComplexBigDecimal zOld = seed;
		int i = 1;
		while(i<= maxIterations)
		{
			zNew = z.add(zOld.square());
			if(zNew.ABSSquared().compareTo(new BigDecimal("4")) == 1)
			{
				return i;
			}
			zOld = zNew;
			i++;
		}
		return 0;
	}
	
}
