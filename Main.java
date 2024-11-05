/*
 Noah MacGillivray
 Assignment:  
 10/31/2024
 CSCI 2251 U01
 Purpose: Reads in matrix from file, divides 
 based on specified dimension in first row and 
 prints the matrix to consul. 
 
This code is provided to give you a
starting place. It should be modified.
No further imports are needed.
To earn full credit, you must also
answer the following question:

Q1: One of the goals of multi-threading
is to minimize the resource usage, such
as memory and processor cycles. In three
sentences, explain how multi-threaded
code accomplishes this goal. Consider
writing about blocking on I/O, multicore 
machines, how sluggish humans are,
threads compared to processes, etcetera,
and connect these issues to 
multi-threading.

Multithreading reduces system resource usage
in several ways including improved CPU utilization,
reducing context switching and sharing memory space.
If a system has a multicore processor it can run many
different threads at the same time (something humans are
unable to do efficiently; our biological neural network's
working memory is overwhelmed with quickly).Aside from 
the multithreading, the CPU can be better used by allowing
I/O operations to complete in the background while the
system is doing other things. Memory efficiency is improved
because threads share memory space which decreases compared 
to using multiple processes to complete the same task.Improved 
response time by not blocking operations, reducing idle times
and better responsiveness when interacting with the system. 
*/

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Matrix rows being read in
        int rows = 0;
        // Matrix columns being read in
        int columns = 0;
        // Quadrants for dividing the matrix addition 
        int quadrantOne = 1;
        int quadrantTwo = 2;
        int quadrantThree = 3;
        int quadrantFour = 4;

        // First matrix being read in from file
        int[][] A = null;
        // Second matrix being read in from file
        int[][] B = null;

        // Read in file from command prompt
        if (args.length < 1) {
            System.out.println("Please provide the file.");
        }
        String filePath = args[0];

        // Testing
        //String filePath = "C:\\Users\\pilga\\code\\CS2251_code\\Matrix_Addition\\matrix1.txt";
        //String filePath = "C:\\Users\\pilga\\code\\CS2251_code\\Matrix_Addition\\matrix2.txt";
        //String filePath = "C:\\Users\\pilga\\code\\CS2251_code\\Matrix_Addition\\matrix3.txt";
        // int[][] test = fillMatrixRandom(5, 5, 10);
        // System.out.println("\nTest matrix");
        // print2dArray(test);

        try {
            Scanner fileReader = new Scanner(new File(filePath));

            // Read in first row indicating rows / columns
            if (fileReader.hasNextLine()) {
                rows = fileReader.nextInt();
                columns = fileReader.nextInt();
            }
            A = matrixFromFile(rows, columns, fileReader);
            B = matrixFromFile(rows, columns, fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);
        }

        // Matrix to hold the added numbers
        int[][] C = new int[rows][columns];

        // Source module30_concurrency\035_example_with_join
        // Instantiates ThreadOperation objects
        ThreadOperation T1 = new ThreadOperation(A, B, C, quadrantOne);
        ThreadOperation T2 = new ThreadOperation(A, B, C, quadrantTwo);
        ThreadOperation T3 = new ThreadOperation(A, B, C, quadrantThree);
        ThreadOperation T4 = new ThreadOperation(A, B, C, quadrantFour);

        // Source module30_concurrency\035_example_with_join
        //Starts the threads
        T1.start();
        T2.start();
        T3.start();
        T4.start();

        // Source module30_concurrency\035_example_with_join
        //Wait on threads to finish.
        try {
            T1.join();
            T2.join();
            T3.join();
            T4.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println();
        System.out.println("Matrix A from file");
        print2dArray(A);
        System.out.println();
        System.out.println("Matrix B from file");
        print2dArray(B);
        System.out.println("\nSingle thread testing\nreference for matrix A+B");
        print2dArray(singleThreadAddArrays(A, B, rows, columns));

    } // End Main

    // Source: Claude "how to read in a matrix in java"
    public static int[][] matrixFromFile(int rows, int columns, Scanner fileReader) {
        int[][] tempMatrix = new int[rows][columns];
        if (fileReader.hasNext()) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    tempMatrix[i][j] = fileReader.nextInt();
                }
            }
        }
        return tempMatrix;
    }

    // Fill array with random numbers
    // Source: Claude "how do I fill a matrix with a loop java"
    public static int[][] fillMatrixRandom(int rows, int cols, int value) {
        int[][] matrix = new int[rows][cols];
        Random randomNumber = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = randomNumber.nextInt(value);
            }
        }
        return matrix;
    }

    // Prints matrix
    public static void print2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%4d", array[i][j]);
            }
            System.out.println();
        }
    }

    // Adds matrices on a single thread for testing comparison
    public static int[][] singleThreadAddArrays(int[][] A, int[][] B, int rows, int cols) {
        int[][] temp = new int[rows][cols];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                temp[i][j] = A[i][j] + B[i][j];
            }
        }
        return temp;
    }
} // End class