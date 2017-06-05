package de.androbin.gfx.transition;

import java.awt.*;
import java.awt.image.*;

public final class ColorCrossfade implements Transition {
  public static final ColorCrossfade BLACK = new ColorCrossfade( Color.BLACK );
  public static final ColorCrossfade WHITE = new ColorCrossfade( Color.WHITE );
  
  private final float red;
  private final float green;
  private final float blue;
  
  public ColorCrossfade( final Color color ) {
    this( color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f );
  }
  
  public ColorCrossfade( final float red, final float green, final float blue ) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  @ Override
  public void render( final Graphics2D g, final BufferedImage image1, final BufferedImage image2,
      final float progress ) {
    g.drawImage( progress < 0.5f ? image1 : image2, 0, 0, null );
    g.setColor(
        new Color( red, green, blue, progress < 0.5f ? progress * 2f : 2f * ( 1f - progress ) ) );
    g.fillRect( 0, 0, image1.getWidth(), image1.getHeight() );
  }
}