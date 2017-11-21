package de.androbin.gfx;

import java.awt.*;
import java.awt.image.*;
import javafx.util.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent {
  private Pair<BufferedImage, Graphics2D> buffer;
  
  public CustomPane() {
    setFocusable( true );
    setIgnoreRepaint( true );
  }
  
  @ Override
  public final void paintComponent( final Graphics g ) {
    super.paintComponent( g );
    render( (Graphics2D) g );
  }
  
  public final boolean render() {
    return ( buffer = render( this, buffer ) ) != null;
  }
  
  public abstract void render( final Graphics2D g );
  
  private Pair<BufferedImage, Graphics2D> render( final JComponent comp,
      final Pair<BufferedImage, Graphics2D> buffer0 ) {
    if ( GraphicsEnvironment.isHeadless() || comp.getWidth() <= 0 || comp.getHeight() <= 0 ) {
      return null;
    }
    
    Pair<BufferedImage, Graphics2D> buffer = buffer0;
    
    if ( buffer != null && !suitable( buffer.getKey(), comp ) ) {
      buffer.getValue().dispose();
      buffer = null;
    }
    
    if ( buffer == null ) {
      final BufferedImage image = new BufferedImage(
          comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB );
      buffer = new Pair<>( image, image.createGraphics() );
    }
    
    render( buffer.getValue() );
    
    final Graphics g = comp.getGraphics();
    
    if ( g != null ) {
      g.drawImage( buffer.getKey(), 0, 0, comp );
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }
    
    return buffer;
  }
  
  private static boolean suitable( final BufferedImage buffer, final JComponent comp ) {
    return buffer.getWidth() == comp.getWidth()
        && buffer.getHeight() == comp.getHeight();
  }
}