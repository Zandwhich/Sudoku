package model.cells;

import com.sun.istack.internal.NotNull;
import errors.CellOutOfGridBoundsException;
import errors.ComputerResetsCellException;
import errors.NumOutOfCellRangeException;

public class Grid extends CellCollection {

    private Cell[][] grid;

    public Grid(@NotNull Cell[][] grid, boolean isComputer) {
        super(grid.length * grid.length, grid.length, Grid.initialNumUnFinished(grid), isComputer);
        this.grid = grid;
    }

    private static int initialNumUnFinished(Cell[][] grid) {
        int counter = grid.length * grid.length;
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell != null && cell.isFilled()) counter--;
            }
        }
        return counter;
    }

    private Cell getCell(int x, int y) throws CellOutOfGridBoundsException {
        if (x < 1 || x > super.getSize() || y < 1 || y > super.getSize())
            throw new CellOutOfGridBoundsException(x, y, super.getSize());
        return this.grid[x-1][y-1];
    }



    public boolean isCellFilled(int x, int y) throws CellOutOfGridBoundsException {
        try {
            return this.getCell(x, y).isFilled();
        } catch (CellOutOfGridBoundsException e) {
            throw e;
        }
    }

    public int getCellNum(int x, int y) throws CellOutOfGridBoundsException {
        try {
            return this.getCell(x, y).getNum();
        } catch (CellOutOfGridBoundsException e) {
            throw e;
        }
    }

    public boolean setCellNum(int x, int y, int num) throws CellOutOfGridBoundsException, NumOutOfCellRangeException,
            ComputerResetsCellException {
        try {
            if (this.getCell(x, y).setNum(num)) {
                super.decNumUnFilled();
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean clearCellNum(int x, int y) throws CellOutOfGridBoundsException {
        try {
            if (this.getCell(x, y).clearNum()) {
                super.incNumUnFilled();
                return true;
            }
            return false;
        } catch (CellOutOfGridBoundsException e) {
            throw e;
        }
    }

    public boolean addCellNote(int x, int y, int num) throws CellOutOfGridBoundsException, NumOutOfCellRangeException {
        try {
            return this.getCell(x, y).addNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean eraseCellNote(int x, int y, int num) throws CellOutOfGridBoundsException,
            NumOutOfCellRangeException {
        try {
            return this.getCell(x, y).eraseNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean getCellNote(int x, int y, int num) throws CellOutOfGridBoundsException, NumOutOfCellRangeException {
        try {
            return this.getCell(x, y).getNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean changeCellNote(int x, int y, int num) throws CellOutOfGridBoundsException,
            NumOutOfCellRangeException {
        try {
            return this.getCell(x, y).changeNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean eraseNote(int num) throws NumOutOfCellRangeException, CellOutOfGridBoundsException {
        try {
            boolean temp = false;
            for (int i = 1; i <= super.getSize(); i++) {
                for (int j = 1; j <= super.getSize(); j++) {
                    temp = temp || this.eraseCellNote(i, j, num);
                }
            }
            return temp;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean hasNum(int num) throws NumOutOfCellRangeException {
        // This line is just to throw the exception
        this.grid[0][0].getNote(num);

        for (Cell[] cells : this.grid) {
            for (Cell cell : cells) {
                if (cell.isFilled() && cell.getNum() == num) return true;
            }
        }

        return false;
    }

    @Override
    public Cell hasOnlyOneNote(int num) throws NumOutOfCellRangeException {
        // This line is just to throw the exception
        this.grid[0][0].getNote(num);

        if (this.hasNum(num)) return null;

        Cell temp = null;
        for (Cell[] cells : this.grid) {
            for (Cell cell : cells) {
                if (cell.getNote(num) && temp == null) temp = cell;
                else if (cell.getNote(num) && temp != null) return null;
            }
        }
        return temp;
    }
}
