package model.cells;

import errors.CellOutOfBoundsException;
import errors.NumOutOfCellRangeException;

public abstract class CellCollection {
    
    private int numUnFilled, totalCells, size;
    private boolean isComputer;
    
    public CellCollection(int totalCells, int size, int numUnFilled, boolean isComputer) {
        this.numUnFilled = numUnFilled;
        this.totalCells = totalCells;
        this.size = size;
        this.isComputer = isComputer;
    }

    public boolean isComputer() {
        return this.isComputer;
    }

    public int getNumUnfilled() {
        return this.numUnFilled;
    }

    protected void setNumUnFilled(int numUnFilled) {
        this.numUnFilled = numUnFilled;
    }

    protected void decNumUnFilled() {
        this.numUnFilled--;
    }

    protected void incNumUnFilled() {
        this.numUnFilled++;
    }

    public int getNumFilled() {
        return this.totalCells - this.numUnFilled;
    }

    public boolean isFilled() {
        return this.numUnFilled == 0;
    }

    public int getTotalCells() {
        return this.totalCells;
    }

    public int getSize() {
        return this.size;
    }

    public abstract boolean eraseNote(int num) throws NumOutOfCellRangeException, CellOutOfBoundsException;

    public abstract boolean hasNum(int num) throws NumOutOfCellRangeException;

    public abstract Cell hasOnlyOneNote(int num) throws NumOutOfCellRangeException;
}
