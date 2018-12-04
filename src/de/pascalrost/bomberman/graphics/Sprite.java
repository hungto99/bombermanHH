package de.pascalrost.bomberman.graphics;

public class Sprite {

	private int x, y;
	public final int SIZE;
	private SpriteSheet sheet;
	public int[] pixels;

	public static Sprite brick_on_fire_1 = new Sprite(64, 3, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_2 = new Sprite(64, 4, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_3 = new Sprite(64, 5, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_4 = new Sprite(64, 6, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_5 = new Sprite(64, 7, 0, SpriteSheet.tiles);

	public static Sprite border_sprite = new Sprite(64, 0, 0, SpriteSheet.tiles);
	public static Sprite brick_sprite = new Sprite(64, 1, 0, SpriteSheet.tiles);
	public static Sprite floor_sprite = new Sprite(64, 2, 0, SpriteSheet.tiles);

	public static Sprite player_forward_1 = new Sprite(64, 0, 1, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(64, 1, 1, SpriteSheet.tiles);
	public static Sprite player_forward_3 = new Sprite(64, 2, 1, SpriteSheet.tiles);
	public static Sprite player_forward_4 = new Sprite(64, 3, 1, SpriteSheet.tiles);
	public static Sprite player_forward_5 = new Sprite(64, 4, 1, SpriteSheet.tiles);
	public static Sprite player_forward_6 = new Sprite(64, 5, 1, SpriteSheet.tiles);
	public static Sprite player_forward_7 = new Sprite(64, 6, 1, SpriteSheet.tiles);
	public static Sprite player_forward_8 = new Sprite(64, 7, 1, SpriteSheet.tiles);

	public static Sprite player_back_1 = new Sprite(64, 0, 2, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(64, 1, 2, SpriteSheet.tiles);
	public static Sprite player_back_3 = new Sprite(64, 2, 2, SpriteSheet.tiles);
	public static Sprite player_back_4 = new Sprite(64, 3, 2, SpriteSheet.tiles);
	public static Sprite player_back_5 = new Sprite(64, 4, 2, SpriteSheet.tiles);
	public static Sprite player_back_6 = new Sprite(64, 5, 2, SpriteSheet.tiles);
	public static Sprite player_back_7 = new Sprite(64, 6, 2, SpriteSheet.tiles);
	public static Sprite player_back_8 = new Sprite(64, 7, 2, SpriteSheet.tiles);

	public static Sprite player_side_1 = new Sprite(64, 0, 3, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(64, 1, 3, SpriteSheet.tiles);
	public static Sprite player_side_3 = new Sprite(64, 2, 3, SpriteSheet.tiles);
	public static Sprite player_side_4 = new Sprite(64, 3, 3, SpriteSheet.tiles);
	public static Sprite player_side_5 = new Sprite(64, 4, 3, SpriteSheet.tiles);
	public static Sprite player_side_6 = new Sprite(64, 5, 3, SpriteSheet.tiles);
	public static Sprite player_side_7 = new Sprite(64, 6, 3, SpriteSheet.tiles);
	public static Sprite player_side_8 = new Sprite(64, 7, 3, SpriteSheet.tiles);

	public static Sprite bomb_1 = new Sprite(64, 8, 1, SpriteSheet.tiles);
	public static Sprite bomb_2 = new Sprite(64, 8, 2, SpriteSheet.tiles);
	public static Sprite bomb_3 = new Sprite(64, 8, 3, SpriteSheet.tiles);

	public static Sprite flame_1 = new Sprite(64, 0, 4, SpriteSheet.tiles);
	public static Sprite flame_2 = new Sprite(64, 1, 4, SpriteSheet.tiles);
	public static Sprite flame_3 = new Sprite(64, 2, 4, SpriteSheet.tiles);
	public static Sprite flame_4 = new Sprite(64, 3, 4, SpriteSheet.tiles);
	public static Sprite flame_5 = new Sprite(64, 4, 4, SpriteSheet.tiles);

	public static Sprite creep_forward_1 = new Sprite(64, 0, 5, SpriteSheet.tiles);
	public static Sprite creep_forward_2 = new Sprite(64, 1, 5, SpriteSheet.tiles);
	public static Sprite creep_forward_3 = new Sprite(64, 2, 5, SpriteSheet.tiles);
	public static Sprite creep_forward_4 = new Sprite(64, 3, 5, SpriteSheet.tiles);
	public static Sprite creep_forward_5 = new Sprite(64, 4, 5, SpriteSheet.tiles);
	public static Sprite creep_forward_6 = new Sprite(64, 5, 5, SpriteSheet.tiles);

	public static Sprite creep_back_1 = new Sprite(64, 0, 6, SpriteSheet.tiles);
	public static Sprite creep_back_2 = new Sprite(64, 1, 6, SpriteSheet.tiles);
	public static Sprite creep_back_3 = new Sprite(64, 2, 6, SpriteSheet.tiles);
	public static Sprite creep_back_4 = new Sprite(64, 3, 6, SpriteSheet.tiles);
	public static Sprite creep_back_5 = new Sprite(64, 4, 6, SpriteSheet.tiles);
	public static Sprite creep_back_6 = new Sprite(64, 5, 6, SpriteSheet.tiles);

	public static Sprite creep_side_1 = new Sprite(64, 0, 7, SpriteSheet.tiles);
	public static Sprite creep_side_2 = new Sprite(64, 1, 7, SpriteSheet.tiles);
	public static Sprite creep_side_3 = new Sprite(64, 2, 7, SpriteSheet.tiles);
	public static Sprite creep_side_4 = new Sprite(64, 3, 7, SpriteSheet.tiles);
	public static Sprite creep_side_5 = new Sprite(64, 4, 7, SpriteSheet.tiles);
	public static Sprite creep_side_6 = new Sprite(64, 5, 7, SpriteSheet.tiles);
	public static Sprite creep_side_7 = new Sprite(64, 6, 7, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
