package de.androbin.gfx.transition;

import java.awt.*;
import java.awt.image.*;

public final class SlideTransition implements Transition
{
	public static final SlideTransition	LEFT  = new SlideTransition( -1, 0 );
	public static final SlideTransition	RIGHT = new SlideTransition( 1, 0 );
											  
	public static final SlideTransition	UP	  = new SlideTransition( 0, -1 );
	public static final SlideTransition	DOWN  = new SlideTransition( 0, 1 );
											  
	private final int					dirX;
	private final int					dirY;
										
	private SlideTransition( final int dirX, final int dirY )
	{
		this.dirX = dirX;
		this.dirY = dirY;
	}
	
	@ Override
	public void render( final Graphics2D g, final BufferedImage image1, final BufferedImage image2, final float progress )
	{
		final int x1 = (int) ( dirX * progress * image1.getWidth() );
		final int y1 = (int) ( dirY * progress * image1.getHeight() );
		
		final int x2 = x1 - dirX * image1.getWidth();
		final int y2 = y1 - dirY * image1.getHeight();
		
		g.drawImage( image1, x1, y1, null );
		g.drawImage( image2, x2, y2, null );
	}
}