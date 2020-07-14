package assessment2;

public class ByteToDecimalConversion{
	public static void main(String ... args) throws Exception{
		final byte b = -65;
		float f = (float)0x1D;
		System.out.println(0xff&b);
		System.out.println(new java.io.DataInputStream(new java.io.ByteArrayInputStream(new byte[]{b})).readUnsignedByte());
	}
}
