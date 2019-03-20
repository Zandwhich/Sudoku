package model;

import model.cells.Cell;
import model.cells.Grid;
import model.cells.Line;

public class Board {

    public static final int DEFAULT_SIZE = 3;

    private int size = Board.DEFAULT_SIZE;
    private boolean isComputer;
    private Cell[][] cells;
    private Grid[][] grids;
    private Line[] rows;
    private Line[] columns;

    public Board(boolean isComputer) {
        this.setup(isComputer);
    }

    public Board (int size, boolean isComputer) {
        this.size = size;
        this.setup(isComputer);
    }

    private void setup(boolean isComputer) {
        // Set if the computer is running the program
        this.isComputer = isComputer;

        // Create all of the cells in a size^2 x size^2 grid
        this.cells = new Cell[this.size * this.size][this.size * this.size];
        for (Cell[] row : this.cells) {
            for (Cell cell : row) {
                cell = new Cell(this.size * this.size, isComputer);
            }
        }

        // Create the columns from the grid
        this.columns = new Line[this.size * this.size];
        for (int i = 0; i < this.size * this.size; i++) {
            this.columns[i] = new Line(this.cells[i], this.isComputer);
        }

        // Create the rows from the grid
        this.rows = new Line[this.size * this.size];
        Cell[] tempRow;
        for (int i = 0; i < this.size * this.size; i++) {
            tempRow = new Cell[this.size * this.size];
            for (int j = 0; j < this.size * this.size; j++) {
                tempRow[j] = cells[j][i];
            }
            this.rows[i] = new Line(tempRow, this.isComputer);
        }

        // Create the sub-grids from the grid
        Cell[][] tempGrid;
        this.grids = new Grid[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                tempGrid = new Cell[this.size][this.size];
                for (int k = 0; k < this.size; k++) {
                    for (int l = 0; l < this.size; l++) {
                        tempGrid[k][l] = this.cells[i * this.size + k][j * this.size + l];
                    }
                }
                this.grids[i][j] = new Grid(tempGrid, this.isComputer);
            }
        }
    }

    private Line getParentRow(int y) {
        return this.rows[y];
    }

    private Line getParentColumn(int x) {
        return this.columns[x];
    }

    private Grid getParentGrid(int x, int y) {
        x /= 3;
        y /= 3;
        return this.grids[x][y];
    }

    public int getSize() {
        return size;
    }

    public boolean isComputer() {
        return isComputer;
    }
}
