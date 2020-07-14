package assessment2;
import java.io.*;

public class Prog{
	public static void main(String ... args){
		FileOutputStream myOut = null;
		int binary = 0b10000000000000000000000000000000;
		try {
			myOut = new FileOutputStream("text.txt");
			Writer myWriter = new OutputStreamWriter(myOut,"UTF-8");
			myWriter.write("Hallo World!");
			//myWriter.close();
			System.out.println(binary);
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				//myWriter.close();
			}catch(Exception e){
				//System.out.println(e);
			}
		}
	}
}
