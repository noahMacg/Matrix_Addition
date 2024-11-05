/*
Creates ThreadOperation objects
Incomplete...
General sources: 
CGPT "can you fill in a predefined int[][] declared in main.java after sending 
a reference to it to another class which fills in the info"
 */

public class ThreadOperation extends Thread {

    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int quadrant;
    int rows;
    int columns;

    public ThreadOperation(int[][] A, int[][] B, int[][] C, int quadrant) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.quadrant = quadrant;
        rows = A.length;
        columns = A[0].length;
    }

    public void run() {

    }

    public static int[] getQuadrantIndexes(int rows, int columns, int quadrant) {
        int[] quadrantIndexes = new int[4];
        if (quadrant == 1) {

        }
        return quadrantIndexes;
    }
}
