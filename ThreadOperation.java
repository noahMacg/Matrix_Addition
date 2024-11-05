/*
Creates ThreadOperation objects
Incomplete...
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

    int rowLength;
    int columnLength;

    int startRow;
    int stopRow;
    int startCol;
    int stopCol;

    public ThreadOperation(int[][] A, int[][] B, int[][] C, int quadrant) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.quadrant = quadrant;
        rowLength = A.length;
        columnLength = A[0].length;
    }

    public void run() {
        updateQuadrantIndexes(rowLength, columnLength, quadrant);

        for (int i = startRow; i < stopRow; i++) {
            for (int j = startCol; j < stopCol; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public void updateQuadrantIndexes(int rowLength, int columnLength, int quadrant) {
        /* Holds the start / stop indexes.
        [0] = row start
        [1] = row end
        [2] = column start
        [3] = column end
        */

        if (quadrant == 1) {
            startRow = 0;
            stopRow = (rowLength / 2);
            startCol = 0;
            stopCol = columnLength / 2;

        } else if (quadrant == 2) {
            startRow = 0;
            stopRow = rowLength / 2;
            startCol = (columnLength / 2);
            stopCol = columnLength;
        } else if (quadrant == 3) {
            startRow = (rowLength / 2);
            stopRow = rowLength;
            startCol = 0;
            stopCol = (columnLength / 2);
        } else {
            startRow = (rowLength / 2);
            stopRow = rowLength;
            startCol = (columnLength / 2);
            stopCol = columnLength;
        }

    }

}
