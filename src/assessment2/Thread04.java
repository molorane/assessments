package assessment2;

public class Thread04 
{
	private static long prime;
	public static void main(String[] args) 
	{
		Thread th;
		(th = new Thread(() -> nextPrime(10000000))).start();
		System.out.println(prime);
	}
	private static void nextPrime(long t)
	{
		if (t < 2) prime = 2;
		else for (prime = t % 2 == 0 ? t + 1 : t; !isPrime(prime); prime += 2);
	}
	private static boolean isPrime(long t)
	{
		if (t == 2 || t == 3 || t == 5 || t == 7) return true;
		if (t < 11 || t % 2 == 0) return false;
		for (long k = 3, m = (long)Math.sqrt(t) + 1; k <= m; k += 2)
		if (t % k == 0) return false;
		return true;
	}
}