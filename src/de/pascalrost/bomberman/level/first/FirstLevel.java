package de.pascalrost.bomberman.level.first;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.pascalrost.bomberman.level.Level;

public class FirstLevel extends Level {

	public FirstLevel(String path, int creeps) {
		super(path, creeps);
	}
	
	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(FirstLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
