/*
Creates ThreadOperation objects
Incomplete...
 */

public class ThreadOperation extends Thread {

    private int[][] A;
    private int[][] B;
    int quadrant;

    public ThreadOperation(int[][] A, int[][] B, int quadrant) {
        this.A = A;
        this.B = B;
        this.quadrant = quadrant;
    }

    public void run() {

    }
}
