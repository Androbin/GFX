package de.androbin.gfx;

import static java.awt.event.KeyEvent.*;
import de.androbin.thread.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent implements Renderable, Runnable {
  private BufferedImage buffer;
  
  private final ThreadSleeper sleeper;
  
  protected long delayMilli;
  protected int delayNano;
  
  protected volatile boolean active;
  protected volatile boolean running;
  
  protected boolean deadly;
  
  public CustomPane() {
    this( 60 );
  }
  
  public CustomPane( final int fps ) {
    sleeper = new ThreadSleeper();
    
    delayMilli = 1000L / fps;
    delayNano = (int) ( 1000000000L / fps - delayMilli * 1000000L );
    
    active = true;
    deadly = true;
    
    setFocusable( true );
    setIgnoreRepaint( true );
  }
  
  protected abstract void destroy();
  
  public final void exitOnEscape() {
    addKeyListener( new KeyAdapter() {
      @ Override
      public void keyPressed( final KeyEvent event ) {
        if ( event.getKeyCode() == VK_ESCAPE ) {
          running = false;
        }
      }
    } );
  }
  
  @ Override
  public final void paintComponent( final Graphics g ) {
    super.paintComponent( g );
    render( (Graphics2D) g );
  }
  
  @ Override
  public final void run() {
    if ( running ) {
      return;
    }
    
    running = true;
    
    long lastFrame = System.currentTimeMillis();
    
    while ( running ) {
      sleeper.sleep( delayMilli, delayNano );
      final long thisFrame = System.currentTimeMillis();
      
      if ( active ) {
        update( ( thisFrame - lastFrame ) / 1000f );
        render();
      }
      
      lastFrame = thisFrame;
    }
    
    destroy();
    
    if ( deadly ) {
      System.exit( 0 );
    } else {
      sleeper.reset();
    }
  }
  
  public final boolean render() {
    return ( buffer = render( this, buffer ) ) != null;
  }
  
  protected final void start( final String name ) {
    new Thread( this, name ).start();
  }
  
  protected abstract void update( float delta );
}