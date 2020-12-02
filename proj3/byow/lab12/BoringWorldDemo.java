package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 35;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    /* bad
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t,int k) {
        //world = new TETile[2 * s][3 * s - 2];
        for (int i = 0; i < s; i++) {
            for (int j = s - 1 - i; j < 2 * s - 1 + i; j++) {
                world[i][j] = t;
            }
        }
        for (int i = s; i < 2 * s; i++) {
            for (int j = i - s; j < 4 * s - 2 - i; j++) {
                world[i][j] = t;
            }
        }
    }
    */
    
    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    /**
     * Computesrelative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     *   xxx
     *  xxxxx
     * xxxxxxx
     * xxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxx
     *   xxx
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            //world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
            world[xCoord][yCoord] = t;
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int z) {
        Position p1 = p;
        for (int i = 0; i < z; i++) {
            p1 = new Position(p1.x, p1.y - 2 * s);
            addHexagon(world, p1, s, randomTile());
        }
    }
    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.GRASS;
            default: return Tileset.AVATAR;
        }
    }

    private static Position toprightposition(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y + s);
    }

    private static Position downrightposition(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y - s);
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        //for (int x = 20; x < 35; x += 1) {
        //    for (int y = 5; y < 10; y += 1) {
        //        world[x][y] = Tileset.WALL;
        //    }
        //}
        Position p = new Position(8, 27);
        // draws the world to the screen
        drawRandomVerticalHexes(world, p, 3, 3);
        Position p2 = toprightposition(p, 3);
        drawRandomVerticalHexes(world, p2, 3, 4);
        Position p3 = toprightposition(p2, 3);
        drawRandomVerticalHexes(world, p3, 3, 5);
        Position p4 = downrightposition(p3, 3);
        drawRandomVerticalHexes(world, p4, 3, 4);
        Position p5 = downrightposition(p4, 3);
        drawRandomVerticalHexes(world, p5, 3, 3);
        ter.renderFrame(world);
    }
}
