package com.uncry.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheetplayer {
	
	public BufferedImage sprite;
	
	public Spritesheetplayer(String path) {
		try {
			sprite=ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x,int y,int w,int h) {
		return sprite.getSubimage(x, y, w, h);
	}
}
