package com.zilker.multithreading;

public class Multithreadsum {

	private static int arr1[] = new int[] { 1, 2, 3, 4, 5 };
	private static int arr2[] = new int[] { 2, 3, 4, 5, 6 };
	private static int sumSquare[] = new int[arr1.length];
	private static int sumCube[] = new int[arr1.length];
	private static int squareCube[] = new int[arr1.length];

	public static void main(String[] args) {

		Runnable run1 = new Square();
		Thread t1 = new Thread(run1);
		Runnable run2 = new Cube();
		Thread t2 = new Thread(run2);
		Runnable run3 = new SumSquareCube();
		Thread t3 = new Thread(run3);

		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			t3.start();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class Square implements Runnable {

		public void run() {

			int length1 = arr1.length;
			for (int i = 0; i < length1; i++) {
				sumSquare[i] = arr1[i] * arr1[i];
			}

		}
	}

	static class Cube implements Runnable {
		public void run() {

			int length2 = arr2.length;
			for (int i = 0; i < length2; i++) {
				sumCube[i] = arr2[i] * arr2[i] * arr2[i];
			}

		}
	}

	static class SumSquareCube implements Runnable {
		public void run() {
			int length1 = arr1.length;
			for (int i = 0; i < length1; i++) {
				squareCube[i] = sumSquare[i] + sumCube[i];
				System.out.print(squareCube[i] + " ");
			}
		}
	}
}