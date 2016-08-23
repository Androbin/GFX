package de.androbin.gfx;

import static java.awt.event.KeyEvent.*;
import de.androbin.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent implements Renderable, Runnable, Serializable
{
	private BufferedImage		buffer;
	
	private final ThreadSleeper	threadSleeper;
	
	protected long				delayMilli;
	protected int				delayNano;
	
	protected boolean			active;
	protected boolean			running;
	
	protected boolean			deadly;
	
	public CustomPane()
	{
		threadSleeper = new ThreadSleeper();
		
		delayMilli = 20L;
		
		active = true;
		deadly = true;
		
		setFocusable( true );
		setIgnoreRepaint( true );
	}
	
	public CustomPane( final int fps )
	{
		this();
		
		delayMilli = 1000L / fps;
		delayNano = (int) ( 1000000000L / fps - delayMilli * 1000000L );
	}
	
	protected abstract void destroy();
	
	public final void exitOnEscape()
	{
		addKeyListener( new KeyAdapter()
		{
			@ Override
			public void keyPressed( final KeyEvent event )
			{
				if ( event.getKeyCode() == VK_ESCAPE )
				{
					running = false;
				}
			}
		} );
	}
	
	@ Override
	public final void paintComponent( final Graphics g )
	{
		super.paintComponent( g );
		render( (Graphics2D) g );
	}
	
	@ Override
	public final void run()
	{
		if ( running )
		{
			return;
		}
		
		running = true;
		
		long lastFrame = System.currentTimeMillis();
		
		while ( running )
		{
			threadSleeper.sleep( delayMilli, delayNano );
			final long thisFrame = System.currentTimeMillis();
			
			if ( active )
			{
				update( ( thisFrame - lastFrame ) / 1000f );
				render();
			}
			
			lastFrame = thisFrame;
		}
		
		destroy();
		
		if ( deadly )
		{
			System.exit( 0 );
		}
		else
		{
			threadSleeper.reset();
		}
	}
	
	public final boolean render()
	{
		return ( buffer = render( this, buffer ) ) != null;
	}
	
	protected final void start( final String name )
	{
		new Thread( this, name ).start();
	}
	
	protected abstract void update( final float delta );
}