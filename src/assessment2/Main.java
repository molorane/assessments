public class Main{
	public static void main(String ... args){
		try{
			javax.script.ScriptEngine Nashorn =
				new javax.script.ScriptEngineManager()
				.getEngineByName("Nashorn")
			;
				Nashorn.eval(
"var a = 0; var b = 0; var c = 0; var d = 0; var jnz = 0; loop:while(-1<jnz){ switch(jnz){ case 0: a = 1; case 1: b = 1; case 2: c = 0; case 3: d = 26; case 4: if(0!=c) { jnz = 6; continue loop; } case 5: if(0!=1) { jnz = 10; continue loop; } case 6: c = 7; case 7: ++d; case 8: --c; case 9: if(0!=c) { jnz = 7; continue loop; } case 10: c = a; case 11: ++a; case 12: --b; case 13: if(0!=b) { jnz = 11; continue loop; } case 14: b = c; case 15: --d; case 16: if(0!=d) { jnz = 10; continue loop; } case 17: c = 18; case 18: d = 11; case 19: ++a; case 20: --d; case 21: if(0!=d) { jnz = 19; continue loop; } case 22: --c; case 23: if(0!=c) { jnz = 18; continue loop; }  } jnz = -1; }"
				);
				System.out.println(
					Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE).size()
				)
				;
				for(String key: Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE).keySet())
				System.out.println(
					key+"= "+Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE).get(key)
				)
				;
			}catch(Exception e){
				System.out.println(e);
			}
	}
}
