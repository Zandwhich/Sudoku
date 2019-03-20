package errors;

public abstract class CellOutOfBoundsException extends Exception {

    private int size;

    public CellOutOfBoundsException(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
