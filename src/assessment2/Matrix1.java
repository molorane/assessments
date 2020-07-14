package assessment2;

public class Matrix1{
	public static void enter(){
		//throw (
			//the compiler interprets Neo as unchecked Runtime
			//and creates the semantical equivalent
			//- (Runtime)(Erasure.getError(new Neo()))
			Erasure.<RuntimeException>
			
			//Throwable n = new Neo() is returned
			//n.getClass() is in java.lang.Object; thus, polymorphic
			getError(new Neo())
		;//);
	}
    public static void main(String[] args) {
        enter();
    }
}

/*
At compile time, the class has the following semantical equivalence.
The return signature section is generalised in toto.
Thus, getError() may be passed Throwable subtypes.
	class Erasure{
		static void Throwable get(Throwable object){
			return object;
		}
	}
*/
class Erasure{
	static <T extends Throwable> void getError(
		//convert new Neo() to Throwable
		Throwable throwable
	){
		//convert subtype to Throwable
		T throwable;
	}
}
