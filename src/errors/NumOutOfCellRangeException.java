package errors;

import java.awt.Point;

public class NumOutOfCellRangeException extends Exception {

    private int max, num;
    private Point point;

    public NumOutOfCellRangeException(int max, int num, Point point) {
        this.max = max;
        this.num = num;
        this.point = point;
    }

    public int getMax() {
        return max;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void printStackTrace() {
        System.err.println("Point:\n\tx:\t" + point.x + "\n\ty:\t" + point.y + "\nMax of cell:\t\t" + this.max +
                "\nAttempted access:\t" + this.num + "\n");
        super.printStackTrace();
    }
}
