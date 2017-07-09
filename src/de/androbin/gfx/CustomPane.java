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
    delayNano = (int) ( 1000000000L / fps - 1000000L * delayMilli );
    
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
    
    long deltaMilli = delayMilli;
    int deltaNano = delayNano;
    
    while ( running ) {
      if ( active ) {
        final long before = System.currentTimeMillis();
        
        update( deltaMilli / 1000f + deltaNano / 1000000000f );
        render();
        
        final long after = System.currentTimeMillis();
        final long diff = after - before;
        
        final long sleepMilli;
        final int sleepNano;
        
        if ( diff <= delayMilli ) {
          sleepMilli = delayMilli - diff;
          sleepNano = delayNano;
        } else {
          sleepMilli = 0L;
          sleepNano = 0;
        }
        
        sleeper.sleep( sleepMilli, sleepNano );
        
        deltaMilli = sleepMilli + diff;
        deltaNano = sleepNano;
      } else {
        sleeper.sleep( delayMilli, delayNano );
        
        deltaMilli = delayMilli;
        deltaNano = delayNano;
      }
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