package de.androbin.gfx;

import java.awt.*;
import javax.swing.*;

public final class SystemGraphics {
  public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCREEN_RES = Toolkit.getDefaultToolkit().getScreenResolution();
  
  public static final float PHY_WIDTH = SCREEN_SIZE.width * 0.0254f / SCREEN_RES;
  public static final float PHY_HEIGHT = SCREEN_SIZE.height * 0.0254f / SCREEN_RES;
  
  private SystemGraphics() {
  }
  
  public static Dimension getDesktopSize() {
    final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final GraphicsDevice device = environment.getDefaultScreenDevice();
    final GraphicsConfiguration configuration = device.getDefaultConfiguration();
    return configuration.getBounds().getSize();
  }
  
  private static boolean setLookAndFeel( final String className ) {
    try {
      UIManager.setLookAndFeel( className );
      return true;
    } catch ( final ReflectiveOperationException | UnsupportedLookAndFeelException ignore ) {
      return false;
    }
  }
  
  public static boolean setNimbusLookAndFeel() {
    return setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
  }
  
  public static boolean setSystemLookAndFeel() {
    return setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
  }
}