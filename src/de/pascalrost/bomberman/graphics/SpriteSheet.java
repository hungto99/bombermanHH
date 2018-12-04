package de.pascalrost.bomberman.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public final int SIZE;
	private String path;
	public int[] pixels;
	
	public static SpriteSheet text = new SpriteSheet(864, "/textures/text/font.png");
	public static SpriteSheet tiles = new SpriteSheet(576, "/textures/sheets/SpriteSheet.png");
	
	public SpriteSheet(int size, String path) {
		this.SIZE = size;
		this.path = path;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
