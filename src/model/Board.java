package model;

import errors.CellOutOfBoundsException;
import errors.ComputerResetsCellException;
import errors.NumOutOfCellRangeException;
import model.cells.Cell;
import model.cells.Grid;
import model.cells.Line;

import java.awt.Point;

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
        for (int x = 0; x < this.cells.length; x++) {
            for (int y = 0; y < this.cells[0].length; y++) {
                cells[y][x] = new Cell(this.size * this.size, isComputer, new Point(x, y));
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

    public Line[] getRows() {
        return this.rows;
    }

    public Line[] getColumns() {
        return this.columns;
    }

    public Grid[][] getGrids() {
        return this.grids;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Line getRow(Point position) {
        return this.rows[position.y];
    }

    public Line getParentRow(Cell cell) {
        return this.getRow(cell.getPosition());
    }

    public Line getColumn(Point position) {
        return this.columns[position.x];
    }

    public Line getParentColumn(Cell cell) {
        return this.getColumn(cell.getPosition());
    }

    public Grid getGrid(Point position) {
        return this.grids[position.y/this.size][position.x/this.size];
    }

    public Grid getParentGrid(Cell cell) {
        return this.getGrid(cell.getPosition());
    }

    public int getSize() {
        return size;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setCellNum(Cell cell, int num) throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException {
        cell.setNum(num);
        this.getParentRow(cell).eraseNote(num);
        this.getParentColumn(cell).eraseNote(num);
        this.getParentGrid(cell).eraseNote(num);
    }

    public void setCellNum(int x, int y, int num) throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException {
        this.setCellNum(this.cells[y][x], num);
    }

    public void print() {
        for (Cell[] cells : this.cells) {
            for (Cell cell : cells) {
                if (cell.getNum() != -1) System.out.print("[" + cell.getNum() + "]");
                else System.out.print("[ ]");
            }
            System.out.println();
        }
    }
}
