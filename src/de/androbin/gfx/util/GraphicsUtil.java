package de.androbin.gfx.util;

import java.awt.*;
import java.awt.geom.*;

public final class GraphicsUtil
{
	private GraphicsUtil()
	{
	}
	
	public static void drawCircle( final Graphics g, final float x, final float y, final float r )
	{
		drawOval( g, x, y, r, r );
	}
	
	public static void drawImage( final Graphics g, final Image image, final float x, final float y )
	{
		final int px = Math.round( x );
		final int py = Math.round( y );
		
		g.drawImage( image, px, py, null );
	}
	
	public static void drawImage( final Graphics g, final Image image, final float x, final float y, final float w, final float h )
	{
		final int px = Math.round( x );
		final int py = Math.round( y );
		
		g.drawImage( image, px, py, Math.round( x + w ) - px, Math.round( y + h ) - py, null );
	}
	
	public static void drawImage( final Graphics g, final Image image, final Rectangle2D.Float bounds )
	{
		drawImage( g, image, bounds.x, bounds.y, bounds.width, bounds.height );
	}
	
	public static void drawLine( final Graphics g, final float x1, final float y1, final float x2, final float y2 )
	{
		g.drawLine( Math.round( x1 ), Math.round( y1 ), Math.round( x2 ), Math.round( y2 ) );
	}
	
	public static void drawRect( final Graphics g, final float x, final float y, final float w, final float h )
	{
		final int px = Math.round( x );
		final int py = Math.round( y );
		
		g.drawRect( px, py, Math.round( x + w ) - px, Math.round( y + h ) - py );
	}
	
	public static void drawOval( final Graphics g, final float x, final float y, final float w, final float h )
	{
		g.drawOval( Math.round( x - w ), Math.round( y - h ), Math.round( w * 2f ), Math.round( h * 2f ) );
	}
	
	public static void fill3DRect( final Graphics g, final float x, final float y, final float w, final float h, final boolean raised, final int thickness )
	{
		final Color c = g.getColor();
		
		final Color brighter = c.brighter();
		final Color darker = c.darker();
		
		g.setColor( raised ? c : darker );
		
		fillRect( g, x + thickness, y + thickness, w - 2 * thickness, h - 2 * thickness );
		fillDiagonal( g, x + w - thickness, y, thickness );
		fillDiagonal( g, x, y + h - thickness, thickness );
		
		g.setColor( raised ? brighter : darker );
		fillRect( g, x, y, thickness, h - thickness );
		fillRect( g, x + thickness, y, w - 2 * thickness, thickness );
		
		g.setColor( raised ? darker : brighter );
		fillRect( g, x + thickness, y + h - thickness, w - thickness, thickness );
		fillRect( g, x + w - thickness, y + thickness, thickness, h - 2 * thickness );
	}
	
	public static void fillCircle( final Graphics g, final float x, final float y, final float r )
	{
		fillOval( g, x, y, r, r );
	}
	
	private static void fillDiagonal( final Graphics g, final float x, final float y, final float size )
	{
		final Color color = g.getColor();
		
		final Color brighter = color.brighter();
		final Color darker = color.darker();
		
		for ( int i = 0; i < size; i++ )
		{
			g.setColor( brighter );
			fillRect( g, x, y + i, size - i - 1, 1 );
			
			g.setColor( color );
			fillRect( g, x + size - i - 1, y + i, 1, 1 );
			
			g.setColor( darker );
			fillRect( g, x + size - i, y + i, i, 1 );
		}
		
		g.setColor( color );
	}
	
	public static void fillRect( final Graphics g, final float x, final float y, final float w, final float h )
	{
		final int px = Math.round( x );
		final int py = Math.round( y );
		
		g.fillRect( px, py, Math.round( x + w ) - px, Math.round( y + h ) - py );
	}
	
	public static void fillRoundRect( final Graphics g, final float x, final float y, final float w, final float h, final float aw, final float ah )
	{
		final int px = Math.round( x );
		final int py = Math.round( y );
		
		g.fillRoundRect( px, py, Math.round( x + w ) - px, Math.round( y + h ) - py, Math.round( aw ), Math.round( ah ) );
	}
	
	public static void fillOval( final Graphics g, final float x, final float y, final float w, final float h )
	{
		g.fillOval( Math.round( x - w ), Math.round( y - h ), Math.round( w * 2f ), Math.round( h * 2f ) );
	}
}