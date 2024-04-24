package com.uncry.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;

public class Ui {
	int xD;
	int yD;
	int maxLife=60;
	int boxMax=32*3;
	public static BufferedImage lifeBox;
	public Ui() {
		lifeBox=Game.sheetWorld.getSprite(64, 3*32, 32*3, 32);

	}
	public void render(Graphics g) {
		xD=2;
		yD=2;
		g.setColor(new Color(0,255,127));
		g.fillRect(0+xD, 0+yD, boxMax-(maxLife-Game.player.life),32-6);
		g.setColor(new Color(0,0,0,50));
		g.fillRect(0+xD,0+yD,boxMax-(maxLife-Game.player.life),32-6);
		g.drawImage(lifeBox, 0+xD, 0+yD, null);
		
	}
}
