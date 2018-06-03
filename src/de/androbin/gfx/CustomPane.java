package de.androbin.gfx;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent {
  private BufferedImage buffer;
  
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
  
  public abstract void render( Graphics2D g );
  
  private BufferedImage render( final JComponent comp, final BufferedImage buffer0 ) {
    if ( GraphicsEnvironment.isHeadless() || comp.getWidth() <= 0 || comp.getHeight() <= 0 ) {
      return null;
    }
    
    BufferedImage buffer = buffer0;
    
    if ( buffer == null || !suitable( buffer, comp ) ) {
      buffer = new BufferedImage( comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB );
    }
    
    final Graphics2D g2 = buffer.createGraphics();
    render( g2 );
    g2.dispose();
    
    final Graphics g = comp.getGraphics();
    
    if ( g != null ) {
      g.drawImage( buffer, 0, 0, null );
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