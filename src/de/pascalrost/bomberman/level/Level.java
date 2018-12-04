package de.pascalrost.bomberman.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import de.pascalrost.bomberman.entity.bomb.Bomb;
import de.pascalrost.bomberman.entity.creep.Creep;
import de.pascalrost.bomberman.entity.flame.Flame;
import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.level.tile.BrickTile;
import de.pascalrost.bomberman.level.tile.Tile;

public class Level {

	private String path;
	protected int[] tiles;
	protected int width, height;
	public int creeps;
	public static List<Bomb> bomb;
	public static List<Flame> flame;
	public static List<Creep> creep;
	public static List<Integer> brickX;
	public static List<Integer> brickY;
	private Random random;
	private boolean complete;
	
	//public static Level level1 = new Level("/textures/levels/Level" + Level.levelCounter + ".png", Level.levelCounter);
	//public static Level level2 = new Level("/textures/levels/Level2.png", 2);

	public Level(String path, int creeps) {
		bomb = new ArrayList<Bomb>();
		flame = new ArrayList<Flame>();
		creep = new ArrayList<Creep>();
		brickX = new ArrayList<Integer>();
		brickY = new ArrayList<Integer>();
		this.creeps = creeps;
		this.path = path;
		random = new Random();
		complete = false;
		generateLevel();
		loadLevel();
	}

	public void generateLevel() {

	}
	
	public void update() {
		if(creep.size() == 0 && flame.size() == 0){
			complete = true;
		}
	}

	public void loadLevel() {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Screen screen) {		
		int x1 = (screen.width + 64) >> 6;
		int y1 = (screen.height + 64) >> 6;
		for (int y = 0; y < y1; y++) {
			for (int x = 0; x < x1; x++) {
				if (x >= 15 || y > 8)break;
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < bomb.size(); i++) {
			bomb.get(i).update();
			bomb.get(i).render(screen);
		}
		
		for (int i = 0; i < Level.flame.size(); i++) {
			Level.flame.get(i).update();
			Level.flame.get(i).render(screen);
		}

		for (int i = 0; i < creep.size(); i++) {
			creep.get(i).update();
			creep.get(i).render(screen);
			if (creep.get(i).removed)
				creep.remove(i);
		}
	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.floor_tile;
		if (x >= width || x < 0 || y < 0 || y >= height) t = Tile.border_tile;
		else if (tiles[x + y * width] == 0xFF000000) t = Tile.border_tile;
		else if (tiles[x + y * width] == 0xFF00FF00) t = Tile.brick_tile;
		else if (tiles[x + y * width] == 0xFFFFFFFF) t = Tile.floor_tile;
	
		for(int yy = 0; yy < brickY.size(); yy++) {
			if(brickX.get(yy) == x && brickY.get(yy) == y) {
				return Tile.floor_tile;
			}
		}
		
		return t;
	}

	public void addBomb(Bomb bomb) {
		this.bomb.add(bomb);
	}

	public void addFlame(Flame flame) {
		this.flame.add(flame);
	}

	public void addCreeps() {
		for (int i = 0; i < creeps; i++) {
			int xx = 0;
			int yy = 0;

			while (getTile(xx, yy).solid() || (xx == 1 && yy == 1) || (xx == 2 && yy == 1) || (xx == 1 && yy == 2)) {
				xx = getRandomX();
				yy = getRandomY();
			}

			creep.add(new Creep(xx, yy, Sprite.creep_forward_1, this));
		}
	}

	private int getRandomX() {
		int num = 0;

		while (num % 2 == 0) {
			num = random.nextInt(13) + 1;
		}

		return num;
	}

	private int getRandomY() {
		int num = 0;

		while (num % 2 == 0) {
			num = random.nextInt(7) + 1;
		}

		return num;
	}
	
	public void addBrokenBricks(int x, int y) {
		brickX.add(x);
		brickY.add(y);
	}
	
	public boolean isComplete() {
		return complete;
	}

}
