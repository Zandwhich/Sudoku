import controller.IController;
import errors.CellOutOfBoundsException;
import errors.ComputerResetsCellException;
import errors.NumOutOfCellRangeException;
import model.Board;
import model.cells.Cell;
import model.cells.CellCollection;
import model.cells.Grid;
import model.cells.Line;

public class Machine {

    private IController controller;
    private Board board;

    public Machine(IController controller, Board board) {
        this.controller = controller;
        this.board = board;
    }

    private void setupNotes() throws NumOutOfCellRangeException {

        /* Loop through all of the possible numbers
         * For every possible number, loop through all of the rows
         * If this row already contains this number, skip it
         * Otherwise, loop through all of the cells in the row
         * If the cell is filled, or its column contains the number, skip it
         * Otherwise, add a note with the number
         */
        for (int num = 1; num <= Math.pow(this.controller.getSize(), 2); num++) {
            for (Line row : board.getRows()) {
                System.out.println();
                System.out.println("About to do hasNum");
                System.out.print("Num:\t" + num + "\nRow: ");
                row.print();
                if (!row.hasNum(num)) {
                    for (Cell cell : row.getCells()) {
                        if (!cell.isFilled() && !board.getParentColumn(cell).hasNum(num) &&
                                !board.getParentGrid(cell).hasNum(num)) cell.addNote(num);
                    }
                }
            }
        }
    }

    private boolean onlyNotes() throws NumOutOfCellRangeException, ComputerResetsCellException, CellOutOfBoundsException
    {
        boolean temp = false;
        for (Cell[] cells : board.getCells()) {
            for (Cell cell : cells) {
                if (!cell.isFilled() && cell.hasOneNote()) {
                    temp = true;
                    this.board.setCellNum(cell, cell.getOnlyNote());
                }
            }
        }
        return temp;
    }

    private boolean onlyOccurrenceInCollection(CellCollection collection) throws NumOutOfCellRangeException,
            ComputerResetsCellException, CellOutOfBoundsException {
        boolean tempBool = false;
        Cell tempCell;
        for (int num = 1; num <= Math.pow(this.controller.getSize(), 2); num++) {
                tempCell = collection.hasOnlyOneNote(num);
                if (tempCell != null) {
                    tempBool = true;
                    this.board.setCellNum(tempCell, num);
            }
        }
        return tempBool;
    }

    private boolean onlyOccurrenceInColumns() throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException {
        boolean temp = false;
        for (Line column : this.board.getColumns()) {
            temp = temp || this.onlyOccurrenceInCollection(column);
        }
        return temp;
    }

    private boolean onlyOccurrenceInRows() throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException {
        boolean temp = false;
        for (Line row : this.board.getRows()) {
            temp = temp || this.onlyOccurrenceInCollection(row);
        }
        return temp;
    }

    private boolean onlyOccurrenceInGrids() throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException  {
        boolean temp = false;
        for (Grid[] grids : this.board.getGrids()) {
            for (Grid grid : grids) {
                temp = temp || this.onlyOccurrenceInCollection(grid);
            }
        }
        return temp;
    }

    private boolean onlyOccurrence() throws NumOutOfCellRangeException, ComputerResetsCellException,
            CellOutOfBoundsException {
        return this.onlyOccurrenceInColumns() || this.onlyOccurrenceInRows() || this.onlyOccurrenceInGrids();
    }

    public void runAI() {
        try {

            this.setupNotes();
            this.board.printNotes();
            System.out.println();

            boolean temp = true;
            while (temp) {
                temp = this.onlyNotes();
                if (temp) continue;
                temp = this.onlyOccurrence();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
