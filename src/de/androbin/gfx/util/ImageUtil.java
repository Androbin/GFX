package de.androbin.gfx.util;

import de.androbin.io.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import javax.imageio.*;
import javax.swing.*;

public final class ImageUtil {
  private ImageUtil() {
  }
  
  private static int getType( final BufferedImage src ) {
    return src.getTransparency() == Transparency.OPAQUE
        ? BufferedImage.TYPE_INT_RGB
        : BufferedImage.TYPE_INT_ARGB;
  }
  
  public static ImageIcon loadIcon( final String path, final int size ) {
    return new ImageIcon( scaleImage( loadImage( path ), size, size ) );
  }
  
  public static BufferedImage loadImage( final String path ) {
    return loadImage( DynamicClassLoader.getPath( "gfx/" + path ) );
  }
  
  public static BufferedImage loadImage( final Path file ) {
    if ( file == null ) {
      return null;
    }
    
    try {
      return ImageIO.read( Files.newInputStream( file ) );
    } catch ( final IOException e ) {
      return null;
    }
  }
  
  public static BufferedImage rotateImage( final BufferedImage src, final float theta ) {
    if ( src == null ) {
      return null;
    }
    
    return rotateImage( src, theta, src.getWidth() * 0.5f, src.getHeight() * 0.5f );
  }
  
  public static BufferedImage rotateImage( final BufferedImage src, final float theta,
      final float anchorx, final float anchory ) {
    if ( src == null ) {
      return null;
    }
    
    final BufferedImage rotated = new BufferedImage(
        src.getWidth(), src.getHeight(), getType( src ) );
    final Graphics2D g = rotated.createGraphics();
    
    g.rotate( theta, anchorx, anchory );
    g.drawImage( src, 0, 0, null );
    g.dispose();
    
    return rotated;
  }
  
  public static boolean saveImage( final RenderedImage image, final File file ) {
    return saveImage( image, "PNG", file );
  }
  
  public static boolean saveImage( final RenderedImage image, final String format,
      final File file ) {
    try {
      return ImageIO.write( image, format, file );
    } catch ( final IOException e ) {
      return false;
    }
  }
  
  public static BufferedImage scaleImage( final BufferedImage src, final Dimension size ) {
    return scaleImage( src, size.width, size.height );
  }
  
  public static BufferedImage scaleImage( final BufferedImage src,
      final int width, final int height ) {
    final BufferedImage scaled = new BufferedImage( width, height, getType( src ) );
    final Graphics2D g = scaled.createGraphics();
    
    g.drawImage( src, 0, 0, width, height, null );
    g.dispose();
    
    return scaled;
  }
}