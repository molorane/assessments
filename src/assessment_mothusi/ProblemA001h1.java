package assessment_mothusi;

/**
 *
 * @author lifehackher (Mothusi Molorane)
 *
 */
public class ProblemA001h1 {

	public static int param1;
	public static int maxNum;
	public static int row;
	public static boolean printedFirstTriangle;
	public static boolean isComplete;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		param1 = 20;
		printedFirstTriangle = false;
		isComplete = false;
		row = 1;
		solve();
	}

	public static void solve() {
		maxNum = param1;

		do {

			for (int i = (printedFirstTriangle) ? row : maxNum; i >= 1; i--) {
				System.out.print(row + " :");
				for (int j = row + 1; j <= maxNum; j++) {
					if (j == maxNum) {
						System.out.print(j);
					} else {
						System.out.print(j + ", ");
					}
				}
				System.out.println();
				if (printedFirstTriangle)
					row--;
				else
					row++;
			}

			if (printedFirstTriangle) {
				isComplete = true;
			}

			printedFirstTriangle = true;
			row--;

		} while (!isComplete);

	}

}
