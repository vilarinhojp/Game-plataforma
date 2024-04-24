package com.uncry.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import com.uncry.entities.Enemy;
import com.uncry.entities.Entity;
import com.uncry.entities.Player;
import com.uncry.graphics.Spritesheetcerca;
import com.uncry.graphics.Spritesheetenemy;
import com.uncry.graphics.Spritesheetplayer;
import com.uncry.graphics.Spritesheetworld;
import com.uncry.graphics.Ui;
import com.uncry.world.Camera;
import com.uncry.world.World;

public class Game extends Canvas implements Runnable, KeyListener{
	
	public static JFrame frame;
	public static final int WIDTH=320;
	public static final int HEIGHT=192;
	public static int SCALE=3;
	
	public Thread thread;
	public boolean isRunning;
	
	public BufferedImage image;
	
	public static Spritesheetplayer sheetPlayer;
	public static Spritesheetworld sheetWorld;
	public static Spritesheetcerca sheetCerca;
	public static Spritesheetenemy sheetEnemy;
	
	public static Player player;
	public static Ui ui;
	public static ArrayList<Entity> entities;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Entity>life_pack;
	
	public static World world;

	public static Random rand;
	public void initFrame() {
		frame=new JFrame("PIG RUN");
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		
	}
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		isRunning=true;
	}
	public synchronized void stop() {
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		isRunning=false;
	}
	public void tick() {
		for(int i=0;i<entities.size();i++) {
			Entity e=entities.get(i);
			e.tick();
		}
	}
	public void render() {
		BufferStrategy bs=getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g=image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		world.render(g);
		for(int i=0;i<entities.size();i++) {
			Entity e=entities.get(i);
			e.render(g);
		}
		g.setColor(new Color(135,206,235,35));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0,0,0,8));
		g.fillRect(0-Camera.x, 0-Camera.y, WIDTH*SCALE, HEIGHT/3);
		g.setColor(new Color(0,0,0,8));
		g.fillRect(0-Camera.x, 0-Camera.y, WIDTH*SCALE, HEIGHT/4);
		ui.render(g);
		g.dispose();
		g=bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	public void run() {
		requestFocus();
		double lastTime=System.nanoTime();
		double amountOfTicks=60;
		double ns=1000000000/amountOfTicks;
		double timer=System.currentTimeMillis();
		double delta=0;
		int frames=0;
		
		while(isRunning) {
			double now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
				
			}
			if(System.currentTimeMillis()-timer>=1000) {
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames=0;
			}
		}
	}
	public void keyTyped(KeyEvent e) {
		 
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			Game.player.right=true;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			Game.player.left=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			Game.player.up=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			Game.player.down=true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			Game.player.right=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			Game.player.left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			Game.player.up=false;
		}
		/*else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			Game.player.down=false;
		}
		*/
	}
	public Game() {
		
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		rand=new Random();
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities=new ArrayList<Entity>();
		enemies=new ArrayList<Enemy>();
		life_pack=new ArrayList<Entity>();
		sheetPlayer=new Spritesheetplayer("/spritesheetplayer.png");
		sheetEnemy=new Spritesheetenemy("/spritesheetenemy.png");
		sheetWorld=new Spritesheetworld("/spritesheetworld.png");
		world=new World("/map.png");
		ui=new Ui();
		player=new Player(0,0,32,32,sheetPlayer.getSprite(0, 0,32,32));
		entities.add(player);

		
	}
	public static void main(String[]args) {
		Game game=new Game();
		game.start();
		
	}
	
}
