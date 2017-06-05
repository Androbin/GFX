package de.androbin.gfx.util;

import java.awt.*;
import java.awt.geom.*;

public final class GraphicsUtil {
  private GraphicsUtil() {
  }
  
  public static void drawCircle( final Graphics g, final float x, final float y, final float r ) {
    drawOval( g, x, y, r, r );
  }
  
  public static void drawImage( final Graphics g, final Image image,
      final Rectangle2D.Float bounds ) {
    drawImage( g, image, bounds.x, bounds.y, bounds.width, bounds.height );
  }
  
  public static void drawImage( final Graphics g, final Image image,
      final float x, final float y ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.drawImage( image, px, py, null );
  }
  
  public static void drawImage( final Graphics g, final Image image, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.drawImage( image, px, py, Math.round( x + width ) - px, Math.round( y + height ) - py, null );
  }
  
  public static void drawLine( final Graphics g, final Line2D.Float line ) {
    drawLine( g, line.x1, line.y1, line.x2, line.y2 );
  }
  
  public static void drawLine( final Graphics g, final Point2D.Float p1, final Point2D.Float p2 ) {
    drawLine( g, p1.x, p1.y, p2.x, p2.y );
  }
  
  public static void drawLine( final Graphics g, final float x1, final float y1,
      final float x2, final float y2 ) {
    g.drawLine( Math.round( x1 ), Math.round( y1 ), Math.round( x2 ), Math.round( y2 ) );
  }
  
  public static void drawRect( final Graphics g, final Rectangle2D.Float rect ) {
    drawRect( g, rect.x, rect.y, rect.width, rect.height );
  }
  
  public static void drawRect( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.drawRect( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py );
  }
  
  public static void drawRoundRect( final Graphics g, final RoundRectangle2D.Float rect ) {
    drawRoundRect( g, rect.x, rect.y, rect.width, rect.height, rect.arcwidth, rect.archeight );
  }
  
  public static void drawRoundRect( final Graphics g, final float x, final float y,
      final float width, final float height, final float arcWidth, final float arcHeight ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.drawRoundRect( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py,
        Math.round( arcWidth ), Math.round( arcHeight ) );
  }
  
  public static void drawOval( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x - width );
    final int py = Math.round( y - height );
    
    g.drawOval( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py );
  }
  
  public static void fill3DRect( final Graphics g, final float x, final float y,
      final float width, final float height, final boolean raised, final int thickness ) {
    final Color c = g.getColor();
    
    final Color brighter = c.brighter();
    final Color darker = c.darker();
    
    g.setColor( raised ? c : darker );
    
    fillRect( g, x + thickness, y + thickness, width - 2 * thickness, height - 2 * thickness );
    fillDiagonal( g, x + width - thickness, y, thickness );
    fillDiagonal( g, x, y + height - thickness, thickness );
    
    g.setColor( raised ? brighter : darker );
    fillRect( g, x, y, thickness, height - thickness );
    fillRect( g, x + thickness, y, width - 2 * thickness, thickness );
    
    g.setColor( raised ? darker : brighter );
    fillRect( g, x + thickness, y + height - thickness, width - thickness, thickness );
    fillRect( g, x + width - thickness, y + thickness, thickness, height - 2 * thickness );
  }
  
  public static void fillCircle( final Graphics g, final Point2D.Float p, final float r ) {
    fillCircle( g, p.x, p.y, r );
  }
  
  public static void fillCircle( final Graphics g, final float x, final float y, final float r ) {
    fillOval( g, x, y, r, r );
  }
  
  private static void fillDiagonal( final Graphics g, final float x, final float y,
      final float size ) {
    final Color color = g.getColor();
    
    final Color brighter = color.brighter();
    final Color darker = color.darker();
    
    for ( int i = 0; i < size; i++ ) {
      g.setColor( brighter );
      fillRect( g, x, y + i, size - i - 1, 1 );
      
      g.setColor( color );
      fillRect( g, x + size - i - 1, y + i, 1, 1 );
      
      g.setColor( darker );
      fillRect( g, x + size - i, y + i, i, 1 );
    }
    
    g.setColor( color );
  }
  
  public static void fillRect( final Graphics g, final Rectangle2D.Float rect ) {
    fillRect( g, rect.x, rect.y, rect.width, rect.height );
  }
  
  public static void fillRect( final Graphics g, final Point2D.Float p, final Point2D.Float s ) {
    fillRect( g, p.x, p.y, s.x, s.y );
  }
  
  public static void fillRect( final Graphics g, final float x, final float y, final float width,
      final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.fillRect( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py );
  }
  
  public static void fillRoundRect( final Graphics g, final RoundRectangle2D.Float rect ) {
    fillRoundRect( g, rect.x, rect.y, rect.width, rect.height, rect.arcwidth, rect.archeight );
  }
  
  public static void fillRoundRect( final Graphics g, final float x, final float y,
      final float width, final float height, final float arcWidth, final float arcHeight ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.fillRoundRect( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py,
        Math.round( arcWidth ), Math.round( arcHeight ) );
  }
  
  public static void fillOval( final Graphics g, final Point2D.Float p,
      final float width, final float height ) {
    fillOval( g, p.x, p.y, width, height );
  }
  
  public static void fillOval( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x - width );
    final int py = Math.round( y - height );
    
    g.fillOval( px, py, Math.round( x + width ) - px, Math.round( y + height ) - py );
  }
}