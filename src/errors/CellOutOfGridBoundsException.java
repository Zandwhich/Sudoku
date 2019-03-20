package errors;

public class CellOutOfGridBoundsException extends CellOutOfBoundsException {

    private int x, y;

    public CellOutOfGridBoundsException(int x, int y, int size) {
        super(size);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void printStackTrace() {
        System.err.println("Size of grid:\t" + super.getSize() + "\nAttempted access x:\t" + this.x + "\nAttempted " +
                "access y:\t" + this.y);
        super.printStackTrace();
    }
}
