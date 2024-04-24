package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;

public class TileFront {
	
	public int x,y,w,h;
	public BufferedImage sprite;
	public static BufferedImage TILE_CERCA_1=Game.sheetWorld.getSprite(4*32, 0, 32, 32);
	public static BufferedImage TILE_CERCA_2=Game.sheetWorld.getSprite(4*32, 32, 32, 32);
	public static BufferedImage TILE_CERCA_V=Game.sheetWorld.getSprite(0, 32, 32, 32);

	public TileFront(int x,int y,int w,int h,BufferedImage sprite) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sprite=sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, w,h,null);
	}
}
