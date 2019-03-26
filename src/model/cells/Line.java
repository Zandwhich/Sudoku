package model.cells;

import com.sun.istack.internal.NotNull;
import errors.CellOutOfBoundsException;
import errors.CellOutOfLineRangeException;
import errors.ComputerResetsCellException;
import errors.NumOutOfCellRangeException;

public class Line extends CellCollection {

    private Cell[] line;

    public Line(@NotNull Cell[] line, boolean isComputer) {
        super(line.length, line.length, Line.initialNumUnfinished(line), isComputer);
        this.line = line;
    }

    private static int initialNumUnfinished(@NotNull Cell[] line) {
        int counter = line.length;
        for (Cell cell : line) {
            if (cell != null && cell.isFilled()) counter--;
        }
        return counter;
    }

    private Cell getCell(int cell) throws CellOutOfLineRangeException {
        if (cell < 1 || cell > super.getSize()) throw new CellOutOfLineRangeException(super.getSize(), cell);
        return this.line[cell-1];
    }

    public boolean isCellFilled(int cell) throws CellOutOfLineRangeException {
        try {
            return this.getCell(cell).isFilled();
        } catch (CellOutOfLineRangeException e) {
            throw e;
        }
    }

    public int getCellNum(int cell) throws CellOutOfLineRangeException {
        try {
            return this.getCell(cell).getNum();
        } catch (CellOutOfLineRangeException e) {
            throw e;
        }
    }

    public boolean setCellNum(int cell, int num) throws CellOutOfLineRangeException, ComputerResetsCellException,
            NumOutOfCellRangeException {
        try {
            if (this.getCell(cell).setNum(num)) {
                super.decNumUnFilled();
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean clearCellNum(int cell) throws CellOutOfLineRangeException {
        try {
            if (this.getCell(cell).clearNum()) {
                super.incNumUnFilled();
                return true;
            }
            return false;
        } catch (CellOutOfLineRangeException e) {
            throw e;
        }
    }

    public boolean addCellNote(int cell, int num) throws CellOutOfLineRangeException, NumOutOfCellRangeException {
        try {
            return this.getCell(cell).addNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean eraseCellNote(int cell, int num) throws CellOutOfLineRangeException, NumOutOfCellRangeException {
        try {
            return this.getCell(cell).eraseNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean getCellNote(int cell, int num) throws CellOutOfLineRangeException, NumOutOfCellRangeException {
        try {
            return this.getCell(cell).getNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean changeCellNote(int cell, int num) throws CellOutOfLineRangeException, NumOutOfCellRangeException {
        try {
            return this.getCell(cell).changeNote(num);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean eraseNote(int num) throws NumOutOfCellRangeException, CellOutOfBoundsException {
        try {
            boolean temp = false;
            for (int i = 1; i <= super.getSize(); i++) {
                temp = temp || eraseCellNote(i, num);
            }
            return temp;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean hasNum(int num) throws NumOutOfCellRangeException {
        // This line is just to throw the exception
        this.line[0].getNote(num);

        for (Cell cell: this.line) {
            if (cell.isFilled() && cell.getNum() == num) return true;
        }
        return false;
    }

    public Cell[] getCells() {
        return this.line;
    }

    @Override
    public Cell hasOnlyOneNote(int num) throws NumOutOfCellRangeException {
        if (this.hasNum(num)) return null;

        Cell temp = null;
        for (Cell cell : line) {
            if (cell.getNote(num) && temp == null) temp = cell;
            else if (cell.getNote(num) && temp != null) return null;
        }
        return temp;
    }
}
