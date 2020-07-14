package assessment_mothusi;

/**
 *
 * @author lifehackher (Mothusi Molorane)
 *
 */
public class ProbemA001f2 {

	public static int maxWholeNumber;
	public static int wholeNumberQ;
	public static int count;
	public static int batch;
	public static int nextbatch;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		maxWholeNumber = 1000;
		wholeNumberQ = 100;
		count = 1;
		nextbatch = 1;
		solve();
	}

	public static void solve() {

		batch = maxWholeNumber / wholeNumberQ;
		for (int i = 1; i <= batch; i++) {
			print(nextbatch, (nextbatch + wholeNumberQ)-1);
			nextbatch += wholeNumberQ;
		}

	}

	public static void print(int start, int end) {
		System.out.println("Sum of ALL numbers from " + start + " to " + end + ":" + sum("Natural", start, end));
		System.out.println("Sum of EVEN numbers from " + start + " to " + end + ":" + sum("Even", start, end));
		System.out.println("Sum of ODD numbers from " + start + " to " + end + ":" + sum("Odd", start, end));
		System.out.println("Sum of ALL numbers from " + 1 + " to " + end + ":" + sum("Natural", 1, end));
		System.out.println();
	}

	public static int sum(String type, int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			if (type.equalsIgnoreCase("Natural")) {
				sum += i;
			} else if (type.equalsIgnoreCase("Even")) {
				if (i % 2 == 0)
					sum += i;
			} else if (type.equalsIgnoreCase("Odd")) {
				if (i % 2 == 1)
					sum += i;
			}
		}
		return sum;
	}

}
