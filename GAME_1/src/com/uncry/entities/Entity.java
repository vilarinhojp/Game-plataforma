package com.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class Entity {
	
	protected int x;
	public int y;
	protected int w,h;
	public BufferedImage sprite;
	
	public static BufferedImage LIFE=Game.sheetWorld.getSprite(128, 64, 32,32);
	public static BufferedImage ENEMY_EN=Game.sheetEnemy.getSprite(0, 0, 32, 32);
	
	public int getX() {
		return (int)this.x;
		
	}
	public int getY() {
		return (int)this.y;
		
	}
	public int getW() {
		return this.w;
		
	}
	public int getH() {
		return this.h;
		
	}
	
	public Entity(int x,int y,int w,int h, BufferedImage sprite) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sprite=sprite;		
	}
	public void setX(int newX) {
		this.x=newX;
	}
	public void setY(int newY) {
		this.y=newY;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite,getX()-Camera.x,getY()-Camera.y,getW(),getH(),null);
	}
}
