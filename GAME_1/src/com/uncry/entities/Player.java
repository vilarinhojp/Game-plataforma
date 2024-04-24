package com.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;
import com.uncry.world.World;



public class Player extends Entity{
	
	public boolean right=false;
	public boolean left=false;
	public boolean up=false;
	public boolean down=false;
	public boolean jump=false;
	public boolean contatoEnemy=false;
	public boolean moved;
	
	public static BufferedImage[]rightPlayer;
	public static BufferedImage[]leftPlayer;
	public static BufferedImage[]staticPlayerRight;
	public static BufferedImage[]staticPlayerLeft;
	public static BufferedImage[]DownPlayer;
	public static BufferedImage[]JumpPlayer;

	public static int life=60;

	public int frames=0;
	public int maxFrames=5;
	public int index=0;
	public int maxIndex=3;
	
	public int framesSR=0;
	public int maxFramesSR=6;
	public int indexSR=0;
	public int maxIndexSR=5;
	
	public int framesS=0;
	public int maxFramesS=6;
	public int indexS=0;
	public int maxIndexS=5;
	
	public static int dir=1;
    int contador=0;
	
    private double gravity = 0.4;
	private double vspd = 0;
	
	public double speed=2;
	public static double gravidade=4;
	public static int clique;
	public static int i=1;
	public void tick() {
		moved=false;
		contatoEnemy=false;
		vspd+=gravity;
		//system jump
		/*if(!World.isFree((int)x,(int)(y+1)) && up)
		{
			vspd = -7;
			//up = false;
		}
		
		if(!World.isFree((int)x,(int)(y+vspd))) {
			
			//aperfeiçoamento da colisão
			int signVsp = 0;
			if(vspd >= 0)
			{
				signVsp = 1;
			}else  {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;
			}
		
			vspd = 0;
		}
		*/
		if(contatoEnemy) {
			this.x+=30;
			vspd=-3;
		}
		if(!World.isFree((int)(x),y+1)&& up){			
			vspd=-7;
			
		}
		if(!World.isFree((int)(x),(int)(y+vspd))) {
			
			int signVspd;
			if(vspd>=0) {
				signVspd=1;
			}else {
				signVspd=-1;
			}
			while(World.isFree((int)(x),(int)(y+signVspd))) {
				y=y+signVspd;
			}
			vspd=0;
		}
		y = (int) (y + vspd);
		//fin
		
		if(right && World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			dir = 1;
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = -1;
		}
		if(right && World.isFree((int)(x+speed), getY())) {
			dir=1;
			moved=true;
			x+=speed;
			
		}else if(left && World.isFree((int)(x-speed), getY())) {
			dir=2;
			moved=true;
			x-=speed;
			
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
		}else {
			contador++;
			if(contador>=100) {
			framesS++;
			if(framesS==maxFramesS) {
				framesS=0;
				indexS++;
				if(indexS>maxIndexS) {
					indexS=0;
					contador=0;
				}
			}
			}
		}
		
		Camera.x=Camera.Clamp(getX()-(Game.WIDTH/2-(getW()/2)),0,(World.WIDTH*32)-Game.WIDTH);
		Camera.y=Camera.Clamp(getY()-(Game.HEIGHT/2-(getH()/2)),0,(World.HEIGHT*32)-Game.HEIGHT);
		
		
	}
	public void render(Graphics g) {
		if(right&&up) {
			g.drawImage(JumpPlayer[0], getX()-Camera.x, getY()-Camera.y, null);
		}else if(left&&up) {
			g.drawImage(JumpPlayer[1], getX()-Camera.x, getY()-Camera.y, null);
		}else if(right && World.isFree((int)x,(int)(y+vspd)) && vspd>=1) {
			g.drawImage(DownPlayer[0], getX()-Camera.x, getY()-Camera.y, null);
		}else if(left && World.isFree((int)x,(int)(y+vspd)) && vspd>=1) {
			g.drawImage(DownPlayer[1], getX()-Camera.x, getY()-Camera.y, null);
		}
		else if(moved==false) {
			{
				if(dir==1) {
					g.drawImage(staticPlayerRight[indexS], getX()-Camera.x, getY()-Camera.y, null);
				}else if(dir==2) {
					g.drawImage(staticPlayerLeft[indexS], getX()-Camera.x, getY()-Camera.y, null);
				}
			}
		}
		else if(right) {
			g.drawImage(rightPlayer[index], getX()-Camera.x, getY()-Camera.y, null);
		}else if(left) {
			g.drawImage(leftPlayer[index],getX()-Camera.x, getY()-Camera.y, null);
		}
	}
	public Player(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
		rightPlayer=new BufferedImage[4];
		leftPlayer=new BufferedImage[4];
		staticPlayerRight=new BufferedImage[6];
		staticPlayerLeft=new BufferedImage[6];
		DownPlayer=new BufferedImage[2];
		JumpPlayer=new BufferedImage[2];
		
		for(int i=0;i<rightPlayer.length;i++) {		
			rightPlayer[i]=Game.sheetPlayer.getSprite(i*32, 64, 32, 32);
			leftPlayer[i]=Game.sheetPlayer.getSprite(i*32, 96, 32, 32);

		}
		for(int i=0;i<staticPlayerRight.length;i++) {		
			staticPlayerRight[i]=Game.sheetPlayer.getSprite(i*32,0, 32, 32);
			staticPlayerLeft[i]=Game.sheetPlayer.getSprite(i*32, 32, 32, 32);

		}
		
		DownPlayer[0]=Game.sheetPlayer.getSprite(64, 4*32, 32,32);
		DownPlayer[1]=Game.sheetPlayer.getSprite(96, 4*32, 32,32);

		JumpPlayer[0]=Game.sheetPlayer.getSprite(0, 4*32, 32,32);
		JumpPlayer[1]=Game.sheetPlayer.getSprite(32, 4*32, 32,32);
		
	}
}
