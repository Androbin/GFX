package de.androbin.gfx;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public interface Renderable
{
	default void render( final Graphics2D g )
	{
	}
	
	default boolean render( final JComponent comp )
	{
		return render( comp, null ) != null;
	}
	
	default BufferedImage render( final JComponent comp, final BufferedImage buffer )
	{
		if ( GraphicsEnvironment.isHeadless() || comp.getWidth() <= 0 || comp.getHeight() <= 0 )
		{
			return null;
		}
		
		final BufferedImage buffer_ = buffer != null && buffer.getWidth() == comp.getWidth() && buffer.getHeight() == comp.getHeight()
				? buffer : new BufferedImage( comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB );
		
		render( buffer_.createGraphics() );
		
		final Graphics g = comp.getGraphics();
		
		if ( g != null )
		{
			g.drawImage( buffer_, 0, 0, comp );
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		
		return buffer_;
	}
}