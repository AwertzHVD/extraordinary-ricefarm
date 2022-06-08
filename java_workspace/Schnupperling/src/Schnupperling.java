import java.util.Scanner;

public class Schnupperling {

	public static void main(String[] args) {
		forLoop();
		forLoopUpside();

		scan();
	}

	private static void scan() {
		Scanner scanner = new Scanner(System.in);
		String name = "";

		System.out.print("Geben Sie Ihren Namen ein: ");
		name = scanner.nextLine();

		System.out.println("Hallo " + name + " !");

	}

	private static void forLoopUpside() {
		String text = "* ";
		int row = 5;
		for (int i = row; i >= 0; i--) {
			System.out.println();
			for (int j = 1; j < i; j++) {
				System.out.print(text);
			}
		}
	}

	private static void forLoop() {
		String text = "* ";

		int row = 5;

		for (int i = 0; i < row; i++) {
			System.out.println();
			for (int j = 0; j <= i; j++) {
				System.out.print(text);
			}
		}
	}

	private static void forLoopSolution() {
		// i for rows and j for columns
		// row denotes the number of rows you want to print
		int i, j, row = 6;
		// outer loop for rows
		for (i = 0; i < row; i++) {
			// inner loop for columns
			for (j = 0; j <= i; j++) {
				// prints stars
				System.out.print("* ");
			}
			// throws the cursor in a new line after printing each line
			System.out.println();
		}

	}

}
