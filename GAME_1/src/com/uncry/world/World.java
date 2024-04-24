package com.uncry.world;

import java.awt.Graphics;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.uncry.entities.Enemy;
import com.uncry.entities.Entity;
import com.uncry.entities.Lifepack;
import com.uncry.main.Game;

public class World {
	
	public static int WIDTH;
	public static int HEIGHT;
	public static Tile[]tile;
	
	public World(String path) {
		try{
			BufferedImage map=ImageIO.read(getClass().getResource(path));
			WIDTH=map.getWidth();
			HEIGHT=map.getHeight();
			int[]pixels=new int[WIDTH*HEIGHT];
			tile=new Tile[WIDTH*HEIGHT];
			map.getRGB(0,0,WIDTH,HEIGHT,pixels,0,WIDTH);
			
			for(int xx=0;xx<WIDTH;xx++) {
				for(int yy=0;yy<HEIGHT;yy++) {
					int pixelAtual=pixels[xx+(yy*WIDTH)];
					int indice=xx+(yy*WIDTH);
					switch(pixelAtual) {
					case(0xFF8999FF):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.SKY_TILE);
					break;
					case(0xFF96FFA6):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_1);
					break;
					case(0xFF87E5AF):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_2);
					break;
					case(0xFF2A472E):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_3);
					break; 
					case(0xFF134419):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_5);
					break;
					//plataforma:
					case(0xFF57935F):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_4);
					break;
					case(0xFF66AD70):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_S_1);
					break;
					case(0xFF7CD389):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.GRASS_TILE_S_2);
					break;
					//feno
					case(0xFFDDE5AF):
						tile[indice]=new WallTile(xx*32,yy*32,32,32,Tile.FENO_FLOOR_TILE);
					break;
					//arbusto:
					case(0xFF64AA8D):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.LITTLE_TREE_TILE);
					break;
					//árvore:
					case(0xFF809926):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_1);
					break;
					case(0xFF758C23):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_2);
					break;
					case(0xFF6A7F1F):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_3);
					break;
					case(0xFF60721C):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_4);
					break;
					case(0xFF556619):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_5);
					break;
					case(0xFF4A5916):
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.TREE_TILE_6);
					break;
					//inimigos
					case(0xFFFF9998):
					//enemy
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.SKY_TILE);
				        Enemy en=new Enemy(xx*32,yy*32,32,32,Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
						
					break;
					case(0xFFD44200):
						//life_pack
							tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.SKY_TILE);
							/*Entity life=new Entity(xx*32,yy*32,32,32,Enemy.LIFE);
							life.setX(xx*32);
							life.setY(yy*32);
							Game.entities.add(life);
							Game.life_pack.add(life);
							*/
							Game.entities.add(new Lifepack(xx*32,yy*32,32,32,Entity.LIFE));
						break;
					default:
						tile[indice]=new FloorTile(xx*32,yy*32,32,32,Tile.SKY_TILE);
					break;
					
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int nextX, int nextY) {
		int size=32;
		int x1= nextX/size;
		int y1= nextY/size;
		
		int x2= (nextX+size-1)/size;
		int y2= nextY/size;

		int x3= nextX/size;
		int y3=(nextY+size-1)/size;
		
		int x4=(nextX+size-1)/size;
		int y4=(nextY+size-1)/size;
		
		return !(tile[x1+(y1*WIDTH)] instanceof WallTile || tile[x2+(y2*WIDTH)] instanceof WallTile
				|| tile[x3+(y3*WIDTH)] instanceof WallTile || tile[x4+(y4*WIDTH)] instanceof WallTile);
		

	}
	
	public void render(Graphics g) {
		int startx=Camera.x/32;
		int starty=Camera.y/32;
		int finalx=startx+(Game.WIDTH/32);
		int finaly=starty+(Game.HEIGHT/32);
		
		for(int xx=startx;xx<finalx+1;xx++) {
			for(int yy=starty;yy<finaly+1;yy++) {
				if(xx<0||yy<0||xx>=WIDTH||yy>=HEIGHT)
					continue;
				Tile e=tile[xx+(yy*WIDTH)];
				e.render(g);
			}
		}
	}
}
