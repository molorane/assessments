/*
public class Matrix {
    public static void enter() {
        try {
            NeoHandler.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class NeoHandler {
    public NeoHandler() throws Neo {
        throw new Neo();
    }
}
*/


public class Matrix {
	private static <T extends java.lang.Throwable> void exit(java.lang.Throwable exception) throws T{
		throw (T) exception;
	}

	
	///////
	public class Matrix {
	Matrix(){}
	
	Matrix(java.lang.String message) throws Neo{
		Neo neo = new Neo();
		//neo.setMessage(message);
		throw neo;
	}
	
    public static void main(String[] args) {
        enter();
    }
    
    public void exit() throws Neo{
      throw new Neo();
    }

    public static void enter(){
        /*try{
            new Matrix().getClass().getMethod("exit", new java.lang.Class[0]).invoke(new Matrix(), new java.lang.Object[0]);
        }catch(java.lang.NoSuchMethodException e){}
          catch(java.lang.IllegalAccessException e){}
          catch(java.lang.reflect.InvocationTargetException e){System.out.println("InvocationTargetException");}
          catch(java.lang.ExceptionInInitializerError e){
              System.out.println("ExceptionInInitializerError");
        }
		
		try{
			new Matrix().getClass().getConstructor(new java.lang.Class[]{new Matrix().getClass()}).newInstance(new java.lang.Object[]{"Exit: Matrix"});
		}catch(java.lang.NoSuchMethodException | java.lang.InstantiationException | java.lang.IllegalAccessException e){}*/
		
		IllegalArgumentException r = new IllegalArgumentException();
		r.initCause(new Neo());
		try{throw r.getCause();}catch(Throwable e){}
		
		//try{java.lang.Class.forName("java.io.FileInputStream").getConstructor(new java.lang.Class[]{java.lang.Class.forName("java.io.File")}).newInstance(new java.lang.Object[0]);}catch(java.lang.ClassNotFoundException | java.lang.NoSuchMethodException | java.lang.InstantiationException |java.lang.IllegalAccessException | java.lang.reflect.InvocationTargetException e){}
    }
}

class Neo extends Exception {
    
}
	///////
	
	
	
    public static void enter(){
        /*try{
            new Matrix().getClass().getMethod("exit", new java.lang.Class[0]).invoke(new Matrix(), new java.lang.Object[0]);
        }catch(java.lang.NoSuchMethodException e){}
          catch(java.lang.IllegalAccessException e){}
          catch(java.lang.reflect.InvocationTargetException e){System.out.println("InvocationTargetException");}
          catch(java.lang.ExceptionInInitializerError e){
              System.out.println("ExceptionInInitializerError");
        }
		
		try{
			new Matrix().getClass().getConstructor(new java.lang.Class[]{new Matrix().getClass()}).newInstance(new java.lang.Object[]{"Exit: Matrix"});
		}catch(java.lang.NoSuchMethodException | java.lang.InstantiationException | java.lang.IllegalAccessException e){}*/
		
		/*IllegalArgumentException r = new IllegalArgumentException();
		r.initCause(new Neo());
		try{throw r.getCause();}catch(Throwable e){}*/
		
		exit(new Neo());
		
		//try{java.lang.Class.forName("java.io.FileInputStream").getConstructor(new java.lang.Class[]{java.lang.Class.forName("java.io.File")}).newInstance(new java.lang.Object[0]);}catch(java.lang.ClassNotFoundException | java.lang.NoSuchMethodException | java.lang.InstantiationException |java.lang.IllegalAccessException | java.lang.reflect.InvocationTargetException e){}
    }
}

class Neo extends Exception {
    
}