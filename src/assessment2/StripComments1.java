
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StripComments1 {

	 public static String stripComments(String text, String[] commentSymbols) {
   
        String [] split = text.split("\n");

      String commentSymb ="";
      for (int i=0; i< commentSymbols.length; i++)
                    commentSymb += commentSymbols[i];
                    
        String pattern = "([\\s ]*[" + commentSymb + "]+[^" + commentSymb+"]*.*[\\s ]*)";
        Pattern r = Pattern.compile(pattern);
        
        for(int i =0; i< split.length; i++) {
            Matcher m = r.matcher(split[i]);
            if (m.find( )) split[i] = m.replaceFirst("");
           split[i] = split[i].replaceFirst("\\s++$", "");
        }
        
        String output = String.join("\n", split);

        return  output;
    }
	
}