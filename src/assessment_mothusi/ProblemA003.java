package assessment_mothusi;

/**
 * 
 * 
 * @author lifehackher (Mothusi Molorane)
 *
 */

public class ProblemA003 {

	public static int sizeOfX;
	public static int columns;
	public static int rows;
	public static int rowCenter;
	public static int columnCenter;
	public static int upperBoundX;
	public static int lowerBoundX;
	public static int currentWeightX;
	public static int count;
	public static boolean $Printed;
	public static int countForLowerBound;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		columns = 79;
		rows = 25;
		count = 0;
		countForLowerBound = 0;
		$Printed = false;
		solve(5);
	}

	private static void solve(int sizeOfx) {

		sizeOfX = sizeOfx;

		if (sizeOfx >= 1 && sizeOfx <= 9) {

			rowCenter = (rows / 2) + 1;
			columnCenter = (columns / 2) + 1;

			upperBoundX = rowCenter - sizeOfX;
			lowerBoundX = rowCenter + sizeOfX;

			for (int i = 1; i <= rows; i++) {

				if (i > rowCenter)
					countForLowerBound++;

				for (int j = 1; j <= columns; j++) {

					if (j == 1 && (i % 10 == 0)) {
						System.out.print(i / 10);
					} else {

						if (i >= upperBoundX && i <= lowerBoundX) {

							if (i <= rowCenter) {
								if (j + (sizeOfX - count) == columnCenter) {
									System.out.print("$");
									$Printed = true;
								} else if (j - (sizeOfX - count) == columnCenter) {
									System.out.print("$");
									$Printed = true;
									count++;
								}
							} else {
								if (j + countForLowerBound == columnCenter) {
									System.out.print("$");
									$Printed = true;
								} else if (j - countForLowerBound == columnCenter) {
									System.out.print("$");
									$Printed = true;
								}
							}
						}

						if (!$Printed) {
							if ((j % 10) == 0) {
								System.out.print(j / 10);
							} else {
								System.out.print("=");
							}
						}
					}
					$Printed = false;
				}
				System.out.println();
			}
		} else {
			System.out.print("Number out of range. Must be in the range [1,9].");
		}
	}

}
