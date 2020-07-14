package assessment2;

public class Int32ToIPV4{
	private static String translate(final long addr){
		try{
			return
				//java.net provides for socket programming
				java.net.InetAddress
				
					//extrapolate IP address (IPV because a buffer of 4 bytes is in question) from byte array
					.getByAddress(
					
						//convert the int addr to a byte array
						//java.nio (initially understood as "native io"; now, as "new io") contains native methods for common denominator access to the i/o facilities of several operating systems
						java.nio.ByteBuffer
						
						//allocate a byte array of 4 elements where 4*8=32
						.allocate(4)
						
						//write the int addr to the buffer
						//the next read/write operation is 32 bits off from the buffer's first content
						.putInt(
								//get the int ration of long
								(int)(addr)
						)
						
						//get the buffer's backed array
						.array()
					)
					
					//convert the IP address to a string
					.toString()
					
					//format the IP address string
					.replaceFirst(".*/", "")
			;
		}
		catch(java.net.UnknownHostException e){
			//the exception is thrown only if a byte array of length not 4 or not 16 is passed getByAddress
			//not network connection takes place for the error to ensue
		}
		
		//compiler "omitted return statement" error
		return "";
	}
	
	public static void main(String ... args){
		System.out.println(translate(32));
	}
}