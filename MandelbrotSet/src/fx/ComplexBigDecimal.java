package fx;
import java.io.Serializable;
import java.math.*;

/**
 * Defines an immutable complex number with real and imaginary components as well as some basic operations.
 * ComplexBigDecimal uses BigDecimal to achieve values up to any precision in all calculations.
 * This class is used when double precision becomes insufficient for deep zooms.
 * 
 * @author Ezra Stein
 * @version 1.0
 * @since 2015
 * @see BigDecimal
 */
public class ComplexBigDecimal implements Serializable
{
	/**
	 * The serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the real part of the complex number in a BigDecimal object
	 */
	BigDecimal real;
	/**
	 * Holds the imaginary part of the complex number in a BigDecimal object
	 */
	BigDecimal imaginary;
	/**
	 * Holds the precision or "scale" of the BigDecimal objects.
	 * It represents the number of digits in used before rounding.
	 */
	int scale;
	
	/**
	 * Constructs a Complex number with real and imaginary components at a certain precision.
	 * 
	 * @param realPart - Real component
	 * @param imaginaryPart - Imaginary component
	 * @param precision - Scale of the BigDecimal Objects
	 */
	public ComplexBigDecimal(BigDecimal realPart, BigDecimal imaginaryPart, int precision)
	{
		real = realPart;
		imaginary = imaginaryPart;
		scale = precision;
		real = real.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		imaginary = imaginary.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Constructs a complex number Constructs a Complex number with real and imaginary components at a certain precision.
	 * 
	 * @param realPart - String value of real component
	 * @param imaginaryPart - String value of imaginary component
	 * @param precision - Scale of the BigDecimal Objects
	 */
	public ComplexBigDecimal(String realPart, String imaginaryPart,int precision)
	{
		real = new BigDecimal(realPart);
		imaginary = new BigDecimal(imaginaryPart);
		scale = precision;
		real = real.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		imaginary = imaginary.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Returns the real part of the complex number.
	 * 
	 * @return The real part.
	 */
	public BigDecimal getRealPart()
	{
		return real;
	}
	
	/**
	 * Returns the imaginary part of the complex number.
	 * 
	 * @return The imaginary part.
	 */
	public BigDecimal getImaginaryPart()
	{
		return imaginary;
	}
	
	/**
	 * Returns the sum of two ComplexBigDecimal types.
	 * 
	 * @param addend
	 * @return The sum
	 */
	public ComplexBigDecimal add(ComplexBigDecimal addend)
	{
		return new ComplexBigDecimal(addend.getRealPart().add(real), addend.getImaginaryPart().add(imaginary), scale);
	}
	
	/**
	 * Returns the square of the complex number (z^2).
	 * @return The square
	 */
	public ComplexBigDecimal square()
	{
		return new ComplexBigDecimal((real.pow(2)).subtract(imaginary.pow(2)),(real.multiply(imaginary)).multiply(new BigDecimal("2")), scale);
	}
	
	/**
	 * Returns the square of the absolute value of this complex number (|z|^2).
	 * Finding the normal absolute value requires taking a square root which causes loss of precision.
	 * @return The square of the absolute value
	 */
	public BigDecimal ABSSquared()
	{
		return (real.pow(2)).add(imaginary.pow(2));
	}
	
	/**
	 * Returns the precision of the BigDecimal components
	 * @return The precision
	 */
	public int getScale()
	{
		return scale;
	}
	
	public String toString()
	{
		return real.stripTrailingZeros().toPlainString() + "+" + imaginary.stripTrailingZeros().toPlainString() + "i";
	}
	
	/**
	 * Converts this complexBigDecimal to a less precise complex.
	 * @return a complex representing this complexBigDecimal.
	 */
	public Complex toComplex()
	{
		return new Complex(real.doubleValue(), imaginary.doubleValue());
	}
	
	
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
		{
			return false;
		}
		if(o == this)
		{
			return true;
		}
		if(o instanceof ComplexBigDecimal)
		{
			ComplexBigDecimal other = (ComplexBigDecimal) o;
			if(other.getRealPart().equals(real) && other.getImaginaryPart().equals(imaginary) && other.getScale() == scale)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		return real.hashCode() + imaginary.hashCode() + scale;
	}
	
	
}