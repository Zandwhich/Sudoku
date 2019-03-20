package errors;

public class NumOutOfCellRangeException extends Exception {

    private int max, num;

    public NumOutOfCellRangeException(int max, int num) {
        this.max = max;
        this.num = num;
    }

    public int getMax() {
        return max;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void printStackTrace() {
        System.err.println("Max of cell:\t" + this.max + "\nAttempted access:\t" + this.num);
        super.printStackTrace();
    }
}
