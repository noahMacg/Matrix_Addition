/*
Creates ThreadOperation objects
General sources: 
CGPT "can you fill in a predefined int[][] declared in main.java after sending 
a reference to it to another class which fills in the info"
Office hours
 */

public class ThreadOperation extends Thread {

    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int quadrant;

    int rowLength; // Total row length
    int columnLength; // Total column length 

    // Values of where to start / stop based on this quadrant
    int startRow;
    int stopRow;
    int startCol;
    int stopCol;

    // Constructor 
    public ThreadOperation(int[][] A, int[][] B, int[][] C, int quadrant) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.quadrant = quadrant;
        rowLength = A.length;
        columnLength = A[0].length;
    }

    // Updates the specific matrix indexes for quadrant and does the addition
    public void run() {
        /* This method runs before the addition loop below to
        update the variables keeping track of the correct 
        index based on which quadrant it is in. */
        updateQuadrantIndexes(rowLength, columnLength, quadrant);

        // Loops through the correct indexes of the matrix quadrant this thread 
        // object is adding
        for (int i = startRow; i < stopRow; i++) {
            for (int j = startCol; j < stopCol; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    // Calculates the correct matrix index based on the specified quadrant
    public void updateQuadrantIndexes(int rowLength, int columnLength, int quadrant) {
        // Top left
        if (quadrant == 1) {
            startRow = 0;
            stopRow = (rowLength / 2);
            startCol = 0;
            stopCol = columnLength / 2;
            // Top right 
        } else if (quadrant == 2) {
            startRow = 0;
            stopRow = rowLength / 2;
            startCol = (columnLength / 2);
            stopCol = columnLength;
            // Bottom Left 
        } else if (quadrant == 3) {
            startRow = (rowLength / 2);
            stopRow = rowLength;
            startCol = 0;
            stopCol = (columnLength / 2);
            // Bottom right 
        } else {
            startRow = (rowLength / 2);
            stopRow = rowLength;
            startCol = (columnLength / 2);
            stopCol = columnLength;
        }
    }
}
