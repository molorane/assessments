package assessment2;

public class NumberOverFlow{
	public static void main(String ... args){
		//- implicitly the compiler asserts (long)(int)(short)(byte)107
		byte a = 107+2;

		byte b = 7;

		byte c = (byte)(a+b);

		System.out.println(c);

		a = b = c;
		(a = b) = c;
		a = (b = c);
		
	}
}