package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;

public class Tile {
	
	public int x,y,w,h;
	public BufferedImage sheet;
	
	public static BufferedImage GRASS_TILE_1=Game.sheetWorld.getSprite(0, 0, 32, 32);
	public static BufferedImage GRASS_TILE_2=Game.sheetWorld.getSprite(4*32, 0, 32, 32);
	public static BufferedImage GRASS_TILE_3=Game.sheetWorld.getSprite(4*32, 32, 32, 32);	
	public static BufferedImage GRASS_TILE_4=Game.sheetWorld.getSprite(96, 32, 32, 32);
	public static BufferedImage GRASS_TILE_5=Game.sheetWorld.getSprite(5*32, 32, 32, 32);

	public static BufferedImage FENO_FLOOR_TILE=Game.sheetWorld.getSprite(5*32,0, 32, 32);

	public static BufferedImage GRASS_TILE_S_1=Game.sheetWorld.getSprite(64, 32, 32, 32);
	public static BufferedImage GRASS_TILE_S_2=Game.sheetWorld.getSprite(64, 64, 32, 32);
 

	public static BufferedImage SKY_TILE=Game.sheetWorld.getSprite(0, 32, 32, 32);
	
	public static BufferedImage TREE_TILE_1= Game.sheetWorld.getSprite(0, 64, 32, 32);
	public static BufferedImage TREE_TILE_2= Game.sheetWorld.getSprite(32, 64, 32, 32);
	public static BufferedImage TREE_TILE_3= Game.sheetWorld.getSprite(0, 96, 32, 32);
	public static BufferedImage TREE_TILE_4= Game.sheetWorld.getSprite(32, 96, 32, 32);
	public static BufferedImage TREE_TILE_5= Game.sheetWorld.getSprite(0, 128, 32, 32);
	public static BufferedImage TREE_TILE_6= Game.sheetWorld.getSprite(32, 128, 32, 32);

	public static BufferedImage LITTLE_TREE_TILE= Game.sheetWorld.getSprite(3*32, 64, 32, 32);


	public Tile(int x,int y,int w,int h,BufferedImage sprite) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sheet=sprite;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sheet, x-Camera.x, y-Camera.y,w,h,null);
	}

}
