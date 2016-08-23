package de.androbin.gfx.util;

import static de.androbin.math.util.floats.FloatMathUtil.*;
import java.awt.*;

public final class ColorUtil
{
	private ColorUtil()
	{
	}
	
	public static Color getColorByHue( final float hue )
	{
		int r = 0;
		int g = 0;
		int b = 0;
		
		final float h = ( hue - (int) hue ) * 6f;
		final float f = h - (int) h;
		
		final float q = 1f - f;
		
		switch ( (int) h )
		{
			case 0 :
				r = 255;
				g = (int) ( f * 255f + 0.5f );
				break;
			
			case 1 :
				r = (int) ( q * 255f + 0.5f );
				g = 255;
				break;
			
			case 2 :
				g = 255;
				b = (int) ( f * 255f + 0.5f );
				break;
			
			case 3 :
				g = (int) ( q * 255f + 0.5f );
				b = 255;
				break;
			
			case 4 :
				r = (int) ( f * 255f + 0.5f );
				b = 255;
				break;
			
			case 5 :
				r = 255;
				b = (int) ( q * 255f + 0.5f );
				break;
		}
		
		return new Color( 0xFF000000 | ( r << 16 ) | ( g << 8 ) | ( b << 0 ) );
	}
	
	public static Color interpolateColor( final Color l, final float p, final Color r )
	{
		final int red = Math.round( interpolate( l.getRed(), p, r.getRed() ) );
		final int green = Math.round( interpolate( l.getGreen(), p, r.getGreen() ) );
		final int blue = Math.round( interpolate( l.getBlue(), p, r.getBlue() ) );
		
		return new Color( red, green, blue );
	}
}