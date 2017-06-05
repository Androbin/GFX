package de.androbin.gfx.transition;

import java.awt.*;
import java.awt.image.*;

public interface Transition {
  default float getCrossingTime() {
    return 0.5f;
  }
  
  void render( Graphics2D g, BufferedImage image1, BufferedImage image2, float progress );
  
  public enum Type {
    CALL,
    CLOSE,
    SWITCH;
  }
}