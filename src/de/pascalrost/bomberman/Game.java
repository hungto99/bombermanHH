package de.pascalrost.bomberman;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import de.pascalrost.bomberman.entity.mob.Player;
import de.pascalrost.bomberman.font.Font;
import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.input.Keyboard;
import de.pascalrost.bomberman.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private int width = 960, height = 540;
	private int scale = 1;
	private Dimension size;
	private String title = "0x50's Bomberman";
	private Thread thread;
	private boolean running = false;
	private int levelCounter = 0;
	
	private Screen screen;
	private Keyboard input;
	private Player player;
	private Level level;
	private Font font;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		frame = new JFrame();
		screen = new Screen(width, height);
		input = new Keyboard();
		setLevel();
		font = Font.completed;
		
		addKeyListener(input);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if((System.currentTimeMillis() - timer) > 1000) {
				//frame.setTitle(title + " Alpha - " + updates + " ups , " + frames + " fps");
				frames = 0;
				updates = 0;
				timer += 1000;
			}
			
			// LIMIT FRAMES WORKAROUND!
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {	
		if(player.isAlive){
			input.update();
			level.update();
			player.update();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		if(level.isComplete()){
			screen.clear();
			level.render(screen);
			player.render(screen);
			font = Font.completed;
			font.render(screen);
			setLevel();
		}
		else if(player.isAlive) {
			screen.clear();
			level.render(screen);
			player.render(screen);
		}
		else {
			screen.clear();
			level.render(screen);
			player.render(screen);
			font = Font.gameover;
			font.render(screen);
		}
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void setLevel() {
		levelCounter++;
		level = new Level("/textures/levels/Level" + levelCounter + ".png", levelCounter);
		level.addCreeps();
		player = new Player(1, 1, input, level);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title + " - Alpha");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}
