package de.androbin.gfx;

import static java.awt.event.KeyEvent.*;
import de.androbin.thread.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javafx.util.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent implements Renderable, Runnable {
  private Pair<BufferedImage, Graphics2D> buffer;
  
  private final ThreadSleeper sleeper;
  
  protected int delayMilli;
  protected int delayMikro;
  
  protected volatile boolean active;
  protected volatile boolean running;
  
  protected boolean deadly;
  
  public CustomPane() {
    this( 60 );
  }
  
  public CustomPane( final int fps ) {
    sleeper = new ThreadSleeper();
    
    setFPS( fps );
    
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
    
    int deltaMilli = delayMilli;
    int deltaMikro = delayMikro;
    
    while ( running ) {
      if ( active ) {
        final long before = System.currentTimeMillis();
        
        update( deltaMilli / 1000f + deltaMikro / 1000000f );
        render();
        
        final long after = System.currentTimeMillis();
        final int diff = (int) ( after - before );
        
        final int sleepMilli;
        final int sleepMikro;
        
        if ( diff <= delayMilli ) {
          sleepMilli = delayMilli - diff;
          sleepMikro = delayMikro;
        } else {
          sleepMilli = 0;
          sleepMikro = 0;
        }
        
        sleeper.sleepMikro( sleepMilli, sleepMikro );
        
        deltaMilli = sleepMilli + diff;
        deltaMikro = sleepMikro;
      } else {
        sleeper.sleepMikro( delayMilli, delayMikro );
        
        deltaMilli = delayMilli;
        deltaMikro = delayMikro;
      }
    }
    
    destroy();
    
    if ( deadly ) {
      System.exit( 0 );
    } else {
      sleeper.reset();
    }
  }
  
  private final boolean render() {
    return ( buffer = render( this, buffer ) ) != null;
  }
  
  public final void setFPS( final int fps ) {
    delayMilli = 1000 / fps;
    delayMikro = ( 1000000 / fps ) % 1000;
  }
  
  public final void start( final String name ) {
    new Thread( this, name ).start();
  }
  
  protected abstract void update( float delta );
}