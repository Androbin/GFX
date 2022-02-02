package de.androbin.gfx;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class CustomPane extends JComponent {
  private VolatileImage buffer;
  
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
    return ( buffer = render( buffer ) ) != null;
  }
  
  public abstract void render( Graphics2D g );
  
  private VolatileImage render( final VolatileImage buffer0 ) {
    if ( GraphicsEnvironment.isHeadless() || getWidth() <= 0 || getHeight() <= 0 ) {
      return null;
    }
    
    VolatileImage buffer = buffer0;
    
    if ( buffer == null || !suitable( buffer ) ) {
      buffer = createVolatileImage( getWidth(), getHeight() );
    }
    
    final Graphics2D g2 = buffer.createGraphics();
    render( g2 );
    g2.dispose();
    
    final Graphics g = getGraphics();
    
    if ( g != null ) {
      g.drawImage( buffer, 0, 0, null );
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }
    
    return buffer;
  }
  
  private boolean suitable( final VolatileImage buffer ) {
    return buffer.getWidth() == getWidth()
        && buffer.getHeight() == getHeight();
  }
}