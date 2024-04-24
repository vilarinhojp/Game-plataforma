package com.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Lifepack extends Entity{
	
	int i=0;
	int contador=0;
	public void tick() {
		contador++;
		if(contador<=10) {
			y-=1;
		}else if(contador>10&&contador<=20) {
			y+=1;
		}else if(contador>20){
			contador=0;
		}
	}
	
	public Lifepack(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
	}

}
