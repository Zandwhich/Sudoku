package model.cells;

import errors.ComputerResetsCellException;
import errors.NumOutOfCellRangeException;

import java.awt.Point;

public class Cell {
    
    private int max;
    private int num = -1;
    private boolean isComputer;
    private boolean[] notes;

    /**
     * Overall position in the whole board
     */
    private Point position;

    public Cell(int max, int num, boolean isComputer, Point position) {
        this.max = max;
        this.num = num;
        this.isComputer = isComputer;
        this.notes = new boolean[this.max];
        this.position = position;
    }
    
    public Cell(int max, boolean isComputer, Point position) {
        this.max = max;
        this.isComputer = isComputer;
        this.notes = new boolean[this.max];
        this.position = position;
    }

    public int getMax() {
        return max;
    }

    public boolean isFilled() {
        return this.num != -1;
    }

    public int getNum() {
        return this.num;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getX() {
        return this.position.x;
    }

    public int getY() {
        return this.position.y;
    }

    /**
     * Sets the num of the cell to the num passed in.
     * @param num The new num of the cell
     * @return <code>true</code> if the cell's num has changed, <code>false</code> otherwise
     * @throws ComputerResetsCellException If the computer is resetting the cell's num, that means the algorithm is
     * faulty
     * @throws NumOutOfCellRangeException If the new num is larger than the max size of the nums, or smaller than 1
     */
    public boolean setNum(int num) throws ComputerResetsCellException, NumOutOfCellRangeException {
        // Throw the appropriate exceptions
        if (this.isComputer && this.isFilled()) throw new ComputerResetsCellException(this.num, num);
        if (num > this.max || num < 1) throw new NumOutOfCellRangeException(this.max, num, this.position);

        // Temporarily remember old number for check if changed
        int oldNum = this.num;
        this.num = num;

        // Clear the notes from this cell
        this.notes = new boolean[this.max];

        // Check if the cell's num has changed
        return !this.isFilled() || oldNum != this.num;
    }

    /**
     * Sets the cell's num to -1 (it clears it).
     * Returns true if it has turned a non--1 num to -1.
     * @return Returns true if it has turned a non--1 num to -1.
     */
    public boolean clearNum() {
        if (this.num != -1) {
            this.num = -1;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of the cell's <code>num</code>th note.
     * @param num The <code>num</code>th note to check.
     * @return The value of the cell's <code>num</code>th note.
     * @throws NumOutOfCellRangeException If the <code>num</code> is larger than the max size of the nums, or smaller
     * than 1
     */
    public boolean getNote(int num) throws NumOutOfCellRangeException {
        if (num > this.max || num < 1) throw new NumOutOfCellRangeException(this.max, num, this.position);
        return this.notes[num-1];
    }

    /**
     * Sets the cell's 'num'th note to <code>true</code>.
     * @param num The 'num'th note to be set to <code>true</code>.
     * @return <code>true</code> if note changed from <code>false</code> to <code>true</code>,
     * <code>false</code> otherwise
     * @throws NumOutOfCellRangeException If the note is larger than the max size of the nums, or smaller than 1
     */
    public boolean addNote(int num) throws NumOutOfCellRangeException {
        if (num > this.max || num < 1) throw new NumOutOfCellRangeException(this.max, num, this.position);
        if (!this.notes[num-1]) {
            this.notes[num-1] = true;
            return true;
        }
        return false;
    }

    /**
     * Sets the cell's 'num'th note to <code>false</code>.
     * @param num The 'num'th note to be set to <code>false</code>.
     * @return <code>true</code> if note changed from <code>true</code> to <code>false</code>,
     * <code>false</code> otherwise
     * @throws NumOutOfCellRangeException If the <code>num</code> is larger than the max size of the nums, or smaller
     * than 1
     */
    public boolean eraseNote(int num) throws NumOutOfCellRangeException {
        if (num > this.max || num < 1) throw new NumOutOfCellRangeException(this.max, num, this.position);
        if (this.notes[num-1]) {
            this.notes[num-1] = false;
            return true;
        }
        return false;
    }

    /**
     * Switches the value of the <code>num</code>th note.
     * @param num The <code>num</code>th note
     * @return <code>true</code> if no exceptions
     * @throws NumOutOfCellRangeException If the <code>num</code> is larger than the max size of the nums, or smaller
     * than 1
     */
    public boolean changeNote(int num) throws NumOutOfCellRangeException {
        if (num > this.max || num < 1) throw new NumOutOfCellRangeException(this.max, num, this.position);
        this.notes[num-1] = !this.notes[num-1];
        return true;
    }

    public int getOnlyNote() {
        if (this.isFilled()) return -1;
        int temp = -1;
        for (int i = 0; i < this.max; i++) {
            if (this.notes[i] && temp != -1) return -1;
            else if (this.notes[i]) temp = i;
        }
        return temp+1;
    }

    public boolean hasOneNote() {
        return this.getOnlyNote() != -1;
    }
}
