package errors;

public class ComputerResetsCellException extends Exception {

    private int prevNum, newNum;

    public ComputerResetsCellException(int prevNum, int newNum) {
        this.prevNum = prevNum;
        this.newNum = newNum;
    }

    public int getPrevNum() {
        return this.prevNum;
    }

    public int getNewNum() {
        return this.newNum;
    }

    @Override
    public void printStackTrace() {
        System.err.println("PrevNum:\t" + this.prevNum + "\nNewNum:\t" + this.newNum);
        super.printStackTrace();
    }
}
