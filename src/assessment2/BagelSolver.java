// -	The solution analogises the Help Neo Escape The Matrix Kata
//		-	Reflection is used to nullify linguistic semantics
// -	The solution contrasts the Help Neg Escape The Matrix Kata
//		-	Generics does not provide a solution
public class BagelSolver extends Bagel {

  public static Bagel getBagel() throws Exception{
    // Set the boolean value of static java.lang.Boolean.TRUE
	java.lang.reflect.Field field = Boolean.class.getField("TRUE");
	field.setAccessible(true);
	//Exception in thread "main" java.lang.IllegalArgumentException: Can not set static final java.lang.Boolean field java.lang.Boolean.TRUE to (boolean)true
	field.setBoolean(
		new Boolean(""),
		true
	);
    return new Bagel();
  }
  
  public static void main(String ... args) throws Exception{
	  int i = 1+new Integer(2);
	  Object[] a = new Object[]{"1"};
	  //for((String)(Object o): a)
	System.out.println(":0:a; :1:b; :2:c; ".replaceAll(":\\d+:", ""));
  }

}

class Bagel {
    public final int getValue() {
        return 3;
    }
}
