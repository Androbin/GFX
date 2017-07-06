package de.androbin.gfx.util;

import java.awt.*;
import java.awt.geom.*;

public final class GraphicsUtil {
  private GraphicsUtil() {
  }
  
  public static void drawCircle( final Graphics g, final Point2D.Float p, final float r ) {
    drawCircle( g, p.x, p.y, r );
  }
  
  public static void drawCircle( final Graphics g, final float x, final float y, final float r ) {
    drawOval( g, x, y, r, r );
  }
  
  public static void drawImage( final Graphics g, final Image image, final Point2D.Float p ) {
    drawImage( g, image, p.x, p.y );
  }
  
  public static void drawImage( final Graphics g, final Image image,
      final float x, final float y ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    g.drawImage( image, px, py, null );
  }
  
  public static void drawImage( final Graphics g, final Image image,
      final Rectangle2D.Float bounds ) {
    drawImage( g, image, bounds.x, bounds.y, bounds.width, bounds.height );
  }
  
  public static void drawImage( final Graphics g, final Image image, final Point2D.Float p,
      final Point2D.Float size ) {
    drawImage( g, image, p.x, p.y, size.x, size.y );
  }
  
  public static void drawImage( final Graphics g, final Image image, final Point2D.Float p,
      final float width, final float height ) {
    drawImage( g, image, p.x, p.y, width, height );
  }
  
  public static void drawImage( final Graphics g, final Image image, final float x, final float y,
      final Point2D.Float size ) {
    drawImage( g, image, x, y, size.x, size.y );
  }
  
  public static void drawImage( final Graphics g, final Image image, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.drawImage( image, px, py, pw, ph, null );
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
  
  public static void drawRect( final Graphics g, final Point2D.Float p, final Point2D.Float size ) {
    drawRect( g, p.x, p.y, size.x, size.y );
  }
  
  public static void drawRect( final Graphics g, final Point2D.Float p,
      final float width, final float height ) {
    drawRect( g, p.x, p.y, width, height );
  }
  
  public static void drawRect( final Graphics g, final float x, final float y,
      final Point2D.Float size ) {
    drawRect( g, x, y, size.x, size.y );
  }
  
  public static void drawRect( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.drawRect( px, py, pw, ph );
  }
  
  public static void drawRoundRect( final Graphics g, final RoundRectangle2D.Float rect ) {
    drawRoundRect( g, rect.x, rect.y, rect.width, rect.height, rect.arcwidth, rect.archeight );
  }
  
  public static void drawRoundRect( final Graphics g, final float x, final float y,
      final float width, final float height, final float arcWidth, final float arcHeight ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.drawRoundRect( px, py, pw, ph, Math.round( arcWidth ), Math.round( arcHeight ) );
  }
  
  public static void drawOval( final Graphics g, final Rectangle2D.Float rect ) {
    drawOval( g, rect.x, rect.y, rect.width, rect.height );
  }
  
  public static void drawOval( final Graphics g, final Point2D.Float p, final Point2D.Float size ) {
    drawOval( g, p.x, p.y, size.x, size.y );
  }
  
  public static void drawOval( final Graphics g, final Point2D.Float p,
      final float width, final float height ) {
    drawOval( g, p.x, p.y, width, height );
  }
  
  public static void drawOval( final Graphics g, final float x, final float y,
      final Point2D.Float size ) {
    drawOval( g, x, y, size.x, size.y );
  }
  
  public static void drawOval( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x - width );
    final int py = Math.round( y - height );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.drawOval( px, py, pw, ph );
  }
  
  public static void fill3DRect( final Graphics g, final Rectangle2D.Float rect,
      final boolean raised, final int thickness ) {
    fill3DRect( g, rect.x, rect.y, rect.width, rect.height, raised, thickness );
  }
  
  public static void fill3DRect( final Graphics g, final float x, final float y,
      final float width, final float height, final boolean raised, final int thickness ) {
    final Color c = g.getColor();
    
    final Color brighter = c.brighter();
    final Color darker = c.darker();
    
    g.setColor( raised ? c : darker );
    
    fillRect( g, x + thickness, y + thickness, width - thickness * 2f, height - thickness * 2f );
    fillDiagonal( g, x + width - thickness, y, thickness );
    fillDiagonal( g, x, y + height - thickness, thickness );
    
    g.setColor( raised ? brighter : darker );
    fillRect( g, x, y, thickness, height - thickness );
    fillRect( g, x + thickness, y, width - thickness * 2f, thickness );
    
    g.setColor( raised ? darker : brighter );
    fillRect( g, x + thickness, y + height - thickness, width - thickness, thickness );
    fillRect( g, x + width - thickness, y + thickness, thickness, height - thickness * 2f );
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
  
  public static void fillRect( final Graphics g, final Point2D.Float p, final Point2D.Float size ) {
    fillRect( g, p.x, p.y, size.x, size.y );
  }
  
  public static void fillRect( final Graphics g, final Point2D.Float p,
      final float width, final float height ) {
    fillRect( g, p.x, p.y, width, height );
  }
  
  public static void fillRect( final Graphics g, final float x, final float y,
      final Point2D.Float size ) {
    fillRect( g, x, y, size.x, size.y );
  }
  
  public static void fillRect( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.fillRect( px, py, pw, ph );
  }
  
  public static void fillRoundRect( final Graphics g, final RoundRectangle2D.Float rect ) {
    fillRoundRect( g, rect.x, rect.y, rect.width, rect.height, rect.arcwidth, rect.archeight );
  }
  
  public static void fillRoundRect( final Graphics g, final float x, final float y,
      final float width, final float height, final float arcWidth, final float arcHeight ) {
    final int px = Math.round( x );
    final int py = Math.round( y );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.fillRoundRect( px, py, pw, ph, Math.round( arcWidth ), Math.round( arcHeight ) );
  }
  
  public static void fillOval( final Graphics g, final Rectangle2D.Float rect ) {
    fillOval( g, rect.x, rect.y, rect.width, rect.height );
  }
  
  public static void fillOval( final Graphics g, final Point2D.Float p, final Point2D.Float size ) {
    fillOval( g, p.x, p.y, size.x, size.y );
  }
  
  public static void fillOval( final Graphics g, final Point2D.Float p,
      final float width, final float height ) {
    fillOval( g, p.x, p.y, width, height );
  }
  
  public static void fillOval( final Graphics g, final float x, final float y,
      final Point2D.Float size ) {
    fillOval( g, x, y, size.x, size.y );
  }
  
  public static void fillOval( final Graphics g, final float x, final float y,
      final float width, final float height ) {
    final int px = Math.round( x - width );
    final int py = Math.round( y - height );
    
    final int pw = Math.round( x + width ) - px;
    final int ph = Math.round( y + height ) - py;
    
    g.fillOval( px, py, pw, ph );
  }
}