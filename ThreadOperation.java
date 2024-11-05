/*
Creates ThreadOperation objects
Incomplete...
 */

public class ThreadOperation extends Thread {

    private int[][] A;
    private int[][] B;
    private int[][] C;
    int quadrant;

    public ThreadOperation(int[][] A, int[][] B, int[][] C, int quadrant) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.quadrant = quadrant;
    }

    public void run() {

    }
}
