package errors;

public class CellOutOfLineRangeException extends CellOutOfBoundsException {

    private int num;

    public CellOutOfLineRangeException(int size, int num) {
        super(size);
        this.num = num;
    }

    public int getCell() {
        return num;
    }

    @Override
    public void printStackTrace() {
        System.err.println("Size of line:\t" + super.getSize() + "\nAttempted access:\t" + this.num);
        super.printStackTrace();
    }
}
