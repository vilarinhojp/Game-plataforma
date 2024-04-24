package com.uncry.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;
import com.uncry.world.World;

public class Enemy extends Entity {
	
	int speed=1;
	public static boolean right,left,staticR,staticL,moved;
	public static BufferedImage[]staticRightEnemy;
	public static BufferedImage[]staticLeftEnemy;
	public static BufferedImage[]rightEnemy;
	public static BufferedImage[]leftEnemy;
	
	public static int frames=0;
	public static int maxFrames=10;
	public static int index=0;
	public static int maxIndex=6;
	
	public static int framesS=0;
	public static int maxFramesS=5;
	public static int indexS=0;
	public static int maxIndexS=6;
	
	int dir=0;
	int piscar=0;
	int cont=0;
	
	int contadorContato=0;
	int resistencia=0;
	public void tick() {
		right=false;
		left=false;
		staticR=false;
		staticL=false;
		moved=false;
		if(this.isCollidingRightPlayer()==false) {
		if(this.getX()<Game.player.getX()+16&&World.isFree((int)(this.x+speed),this.getY()) && !isColliding((int)(this.x+speed),this.getY())
			&&	this.getY()-Game.player.getY()<=96) {
			dir=0;
			moved=true;
			x+=speed;
			right=true;
			left=false;
			staticR=false;
			staticL=false;
		}else if(this.getX()>Game.player.getX()+16&&World.isFree((int)(this.x-speed), this.getY()) && !isColliding((int)(this.x-speed), this.getY())
			&&	this.getY()-Game.player.getY()<=96) {
			dir=1;
			x-=speed;
			moved=true;
			left=true;
			right=false;
			staticR=false;
			staticL=false;
		}
		}else {
			Game.player.life-=1;
			System.out.println(Game.player.life);
		}
		if(getX()<Game.player.getX()&&!World.isFree((int)(x+speed), getY())) {
			staticR=true;
			staticL=false;
			moved=false;
		}else if(getX()>Game.player.getX()&&!World.isFree((int)(x-speed),getY())) {
			staticL=true;
			staticR=false;
			moved=false;
		}
		
		if(moved) {
			frames++;
			if(frames==maxFrames) {
				frames=0;
				index++;
				if(index>maxIndex) {
					index=0;
				}
			}
		}
		if(!right&&!left) {
			if(piscar<10) {
			framesS++;
			if(framesS==maxFramesS) {
				framesS=0;
				indexS++;
				if(indexS>maxIndexS) {
					indexS=0;
					piscar=Game.rand.nextInt(100);
				}
			}
			}else {
				cont++;
				if(cont>=30) {
					piscar=Game.rand.nextInt(100);
					cont=0;
				}
			}
		}
		
	}
	public boolean isCollidingRightPlayer() {
		Rectangle player=new Rectangle(Game.player.getX(),Game.player.getY(),Game.player.getW(),Game.player.getH());
		Rectangle enemy=new Rectangle(this.getX(),this.getY(),this.getW(),this.getH());
		
		return enemy.intersects(player);
	}
	public boolean isColliding(int nextX,int nextY) {
		Rectangle enemyCurrent=new Rectangle(nextX,nextY,32,32);
		for(int i=0;i<Game.enemies.size();i++) {
			Enemy e=Game.enemies.get(i);
			if(e==this) 
				continue;
			
			Rectangle enemyAtual=new Rectangle(e.getX(),e.getY(),32,32);
			if(enemyAtual.intersects(enemyCurrent)) {
				return true;
			}
		}
		return false;
	}
	public void render(Graphics g) {
		if(right) {
				g.drawImage(rightEnemy[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
				
		}else if(left) {
				g.drawImage(leftEnemy[index], this.getX()-Camera.x,this.getY()-Camera.y,null);
				
		}else if(dir==0){
				g.drawImage(staticRightEnemy[indexS], this.getX()-Camera.x,this.getY()-Camera.y,null);
			
		}else if(dir==1) {
			g.drawImage(staticLeftEnemy[indexS], this.getX()-Camera.x,this.getY()-Camera.y,null);

		}
	}
	public Enemy(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
		rightEnemy=new BufferedImage[7];
		leftEnemy=new BufferedImage[7];
		staticRightEnemy=new BufferedImage[7];
		staticLeftEnemy=new BufferedImage[7];
		
		for(int i=0;i<rightEnemy.length;i++) {
			staticLeftEnemy[i]=Game.sheetEnemy.getSprite(i*32, 0, 32,32);
			staticRightEnemy[i]=Game.sheetEnemy.getSprite(i*32, 32, 32,32);
			leftEnemy[i]=Game.sheetEnemy.getSprite(i*32, 64, 32,32);
			rightEnemy[i]=Game.sheetEnemy.getSprite(i*32, 96, 32,32);

		}
	}

}
