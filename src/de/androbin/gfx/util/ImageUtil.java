package de.androbin.gfx.util;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

public final class ImageUtil
{
	private ImageUtil()
	{
	}
	
	public static BufferedImage loadImage( final File file )
	{
		if ( file == null )
		{
			return null;
		}
		
		try
		{
			return ImageIO.read( file );
		}
		catch ( final IOException e )
		{
			return null;
		}
	}
	
	public static BufferedImage loadImage( final String path )
	{
		final URL res = ClassLoader.getSystemResource( "gfx/" + path );
		
		if ( res == null )
		{
			return null;
		}
		
		try
		{
			return ImageIO.read( res );
		}
		catch ( final IOException e )
		{
			return null;
		}
	}
	
	public static BufferedImage rotateImage( final BufferedImage src, final float theta )
	{
		if ( src == null )
		{
			return null;
		}
		
		return rotateImage( src, theta, src.getWidth() * 0.5f, src.getHeight() * 0.5f );
	}
	
	public static BufferedImage rotateImage( final BufferedImage src, final float theta, final float anchorx, final float anchory )
	{
		if ( src == null )
		{
			return null;
		}
		
		final BufferedImage rotated = new BufferedImage( src.getWidth(), src.getHeight(), src.getType() );
		final Graphics2D g = rotated.createGraphics();
		
		g.setTransform( AffineTransform.getRotateInstance( theta, anchorx, anchory ) );
		g.drawImage( src, 0, 0, null );
		
		return rotated;
	}
	
	public static boolean saveImage( final RenderedImage image, final File file )
	{
		return saveImage( image, "PNG", file );
	}
	
	public static boolean saveImage( final RenderedImage image, final String format, final File file )
	{
		try
		{
			return ImageIO.write( image, format, file );
		}
		catch ( final IOException e )
		{
			return false;
		}
	}
}