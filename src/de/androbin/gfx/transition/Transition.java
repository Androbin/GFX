package de.androbin.gfx.transition;

import java.awt.*;
import java.awt.image.*;

public interface Transition
{
	default float getCrossingTime()
	{
		return 0.5f;
	}
	
	void render( final Graphics2D g, final BufferedImage image1, final BufferedImage image2, final float progress );
	
	public static enum Type
	{
		CALL,
		CLOSE,
		SWITCH;
	}
}