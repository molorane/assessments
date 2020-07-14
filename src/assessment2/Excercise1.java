package assessment2;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Excercise1 {
    public static void main(String[] args) {
		try {
			 Path path = FileSystems.getDefault().getPath("","C:\\Users\\brand\\eclipse-workspace\\Bowling\\src\\r.txt");
			
			byte [] b = Files.readAllBytes(path);
			for (byte i: b) {
				System.out.println(new String(new byte[] {i} ) +  "=" +Integer.toHexString(i) + " = " + Integer.toBinaryString(i));
			}
			byte c = -128;
			 System.out.println(0b010101);
			 System.out.println(c);
		}
		catch(IOException e){
			System.out.println(e);
		}	
	}

}
