package assessment2;

public class Reference{
	public static void main(String ... args){
		Integer integer = new Integer("0");
		Integer[] integerArray = new Integer[]{integer};
		
		++integerArray[0];
		System.out.println(integer);
	}
}