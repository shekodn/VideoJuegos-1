package tetris;

import java.awt.Color;

/**
 * The {@code PieceType} enum describes the properties of the various pieces
 * that can be used in the game.
 *
 * @author Brendan Jones
 *
 */
public enum TileType {

    /**
     * Piece TypeI. (Largo)
     */
    TypeI(new Color(BoardPanel.arrR[0], BoardPanel.arrG[0], BoardPanel.arrB[0]), 4, 4, 1, new boolean[][]{
        {
            false, false, false, false,
            true, true, true, true,
            false, false, false, false,
            false, false, false, false,},
        {
            false, false, true, false,
            false, false, true, false,
            false, false, true, false,
            false, false, true, false,},
        {
            false, false, false, false,
            false, false, false, false,
            true, true, true, true,
            false, false, false, false,},
        {
            false, true, false, false,
            false, true, false, false,
            false, true, false, false,
            false, true, false, false,}
    }, 0),
    /**
     * Piece TypeJ. (Ld)
     */
    TypeJ(new Color(BoardPanel.arrR[1], BoardPanel.arrG[1], BoardPanel.arrB[1]), 3, 3, 2, new boolean[][]{
        {
            true, false, false,
            true, true, true,
            false, false, false,},
        {
            false, true, true,
            false, true, false,
            false, true, false,},
        {
            false, false, false,
            true, true, true,
            false, false, true,},
        {
            false, true, false,
            false, true, false,
            true, true, false,}
    }, 1),
    /**
     * Piece TypeL. (Li)
     */
    TypeL(new Color(BoardPanel.arrR[2], BoardPanel.arrG[2], BoardPanel.arrB[2]), 3, 3, 2, new boolean[][]{
        {
            false, false, true,
            true, true, true,
            false, false, false,},
        {
            false, true, false,
            false, true, false,
            false, true, true,},
        {
            false, false, false,
            true, true, true,
            true, false, false,},
        {
            true, true, false,
            false, true, false,
            false, true, false,}
    }, 2),
    /**
     * Piece TypeO. (Cuadrado)
     */
    TypeO(new Color(BoardPanel.arrR[3], BoardPanel.arrG[3], BoardPanel.arrB[3]), 2, 2, 2, new boolean[][]{
        {
            true, true,
            true, true,},
        {
            true, true,
            true, true,},
        {
            true, true,
            true, true,},
        {
            true, true,
            true, true,}
    }, 3),
    /**
     * Piece TypeS.
     */
    TypeS(new Color(BoardPanel.arrR[4], BoardPanel.arrG[4], BoardPanel.arrB[4]), 3, 3, 2, new boolean[][]{
        {
            false, true, true,
            true, true, false,
            false, false, false,},
        {
            false, true, false,
            false, true, true,
            false, false, true,},
        {
            false, false, false,
            false, true, true,
            true, true, false,},
        {
            true, false, false,
            true, true, false,
            false, true, false,}
    }, 4),
    /**
     * Piece TypeT.
     */
    TypeT(new Color(BoardPanel.arrR[5], BoardPanel.arrG[5], BoardPanel.arrB[5]), 3, 3, 2, new boolean[][]{
        {
            false, true, false,
            true, true, true,
            false, false, false,},
        {
            false, true, false,
            false, true, true,
            false, true, false,},
        {
            false, false, false,
            true, true, true,
            false, true, false,},
        {
            false, true, false,
            true, true, false,
            false, true, false,}
    }, 5),
    /**
     * Piece TypeZ.
     */
    TypeZ(new Color(BoardPanel.arrR[6], BoardPanel.arrG[6], BoardPanel.arrB[6]), 3, 3, 2, new boolean[][]{
        {
            true, true, false,
            false, true, true,
            false, false, false,},
        {
            false, false, true,
            false, true, true,
            false, true, false,},
        {
            false, false, false,
            true, true, false,
            false, true, true,},
        {
            false, true, false,
            true, true, false,
            true, false, false,}
    }, 6);

    private int iTipo;
    /**
     * The base color of tiles of this type.
     */
    private Color baseColor;

    /**
     * The light shading color of tiles of this type.
     */
    private Color lightColor;

    /**
     * The dark shading color of tiles of this type.
     */
    private Color darkColor;

    /**
     * The column that this type spawns in.
     */
    private int spawnCol;

    /**
     * The row that this type spawns in.
     */
    private int spawnRow;

    /**
     * The dimensions of the BoardPanel.array for this piece.
     */
    private int dimension;

    /**
     * The number of rows in this piece. (Only valid when rotation is 0 or 2,
     * but it's fine since we're only using it for displaying the next piece
     * preview, which uses rotation 0).
     */
    private int rows;

    /**
     * The number of columns in this piece. (Only valid when rotation is 0 or 2,
     * but it's fine since we're only using it for displaying the next piece
     * preview, which uses rotation 0).
     */
    private int cols;

    /**
     * The tiles for this piece. Each piece has an BoardPanel.array of tiles for
     * each rotation.
     */
    private boolean[][] tiles;

    /**
     * Creates a new TileType.
     *
     * @param color The base color of the tile.
     * @param dimension The dimensions of the tiles BoardPanel.array.
     * @param cols The number of columns.
     * @param rows The number of rows.
     * @param tiles The tiles.
     */
    private TileType(Color color, int dimension, int cols, int rows, boolean[][] tiles, int iTipo) {
        this.iTipo = iTipo;
        this.baseColor = color;
        this.lightColor = color.brighter();
        this.darkColor = color.darker();
        this.dimension = dimension;
        this.tiles = tiles;
        this.cols = cols;
        this.rows = rows;

        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = getTopInset(0);
    }

    public int getTipo() {
        return iTipo;
    }

    /**
     * Gets the base color of this type.
     *
     * @return The base color.
     */
    public Color getBaseColor() {
        return baseColor;
    }

    /**
     * Gets the light shading color of this type.
     *
     * @return The light color.
     */
    public Color getLightColor() {
        return lightColor;
    }

    /**
     * Gets the dark shading color of this type.
     *
     * @return The dark color.
     */
    public Color getDarkColor() {
        return darkColor;
    }

    /**
     * Gets the dimension of this type.
     *
     * @return The dimension.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Gets the spawn column of this type.
     *
     * @return The spawn column.
     */
    public int getSpawnColumn() {
        return spawnCol;
    }

    /**
     * Gets the spawn row of this type.
     *
     * @return The spawn row.
     */
    public int getSpawnRow() {
        return spawnRow;
    }

    /**
     * Gets the number of rows in this piece. (Only valid when rotation is 0 or
     * 2, but it's fine since this is only used for the preview which uses
     * rotation 0).
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in this piece. (Only valid when rotation is 0
     * or 2, but it's fine since this is only used for the preview which uses
     * rotation 0).
     *
     * @return The number of columns.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Checks to see if the given coordinates and rotation contain a tile.
     *
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @param rotation The rotation to check in.
     * @return Whether or not a tile resides there.
     */
    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }

    /**
     * The left inset is represented by the number of empty columns on the left
     * side of the BoardPanel.array for the given rotation.
     *
     * @param rotation The rotation.
     * @return The left inset.
     */
    public int getLeftInset(int rotation) {
        /*
		 * Loop through from left to right until we find a tile then return
		 * the column.
         */
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (isTile(x, y, rotation)) {
                    return x;
                }
            }
        }
        return -1;
    }

    /**
     * The right inset is represented by the number of empty columns on the left
     * side of the BoardPanel.array for the given rotation.
     *
     * @param rotation The rotation.
     * @return The right inset.
     */
    public int getRightInset(int rotation) {
        /*
		 * Loop through from right to left until we find a tile then return
		 * the column.
         */
        for (int x = dimension - 1; x >= 0; x--) {
            for (int y = 0; y < dimension; y++) {
                if (isTile(x, y, rotation)) {
                    return dimension - x;
                }
            }
        }
        return -1;
    }

    /**
     * The left inset is represented by the number of empty rows on the top side
     * of the BoardPanel.array for the given rotation.
     *
     * @param rotation The rotation.
     * @return The top inset.
     */
    public int getTopInset(int rotation) {
        /*
		 * Loop through from top to bottom until we find a tile then return
		 * the row.
         */
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (isTile(x, y, rotation)) {
                    return y;
                }
            }
        }
        return -1;
    }

    /**
     * The botom inset is represented by the number of empty rows on the bottom
     * side of the BoardPanel.array for the given rotation.
     *
     * @param rotation The rotation.
     * @return The bottom inset.
     */
    public int getBottomInset(int rotation) {
        /*
		 * Loop through from bottom to top until we find a tile then return
		 * the row.
         */
        for (int y = dimension - 1; y >= 0; y--) {
            for (int x = 0; x < dimension; x++) {
                if (isTile(x, y, rotation)) {
                    return dimension - y;
                }
            }
        }
        return -1;
    }

}
