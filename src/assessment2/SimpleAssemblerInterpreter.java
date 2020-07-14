package assessment2;


/*
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.util.HashMap;
import java.util.Map;

public class SolutionTest {
  @Test
  public void simple_1() {
    String[] program = new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"}; 
    Map<String, Integer> out = new HashMap<String, Integer>();
    out.put("a", 1);
    assertEquals(out, SimpleAssembler.interpret(program));
  }
  
  @Test
  public void simple_2() {
    String[] program = new String[]{"mov a -10","mov b a","inc a","dec b","jnz a -2"};
    Map<String, Integer> out = new HashMap<String, Integer>();
    out.put("a", 0);
    out.put("b", -20);
    assertEquals(out, SimpleAssembler.interpret(program));
  }
}
*/

/*Simple assembler interpreter
1575195% of 282218 of 1,045ShinuToki
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (2)
    Discourse (90)

    Add to Collection
    |
    Share this kata:

This is the first part of this kata series. Second part is here.

We want to create a simple interpreter of assembler which will support the following instructions:

    mov x y - copies y (either a constant value or the content of a register) into register x
    inc x - increases the content of register x by one
    dec x - decreases the content of register x by one
    jnz x y - jumps to an instruction y steps away (positive means forward, negative means backward), but only if x (a constant or a register) is not zero

Register names are alphabetical (letters only). Constants are always integers (positive or negative).

Note: the jnz instruction moves relative to itself. For example, an offset of -1 would continue at the previous instruction, while an offset of 2 would skip over the next instruction.

The function will take an input list with the sequence of the program instructions and will return a dictionary with the contents of the registers.

Also, every inc/dec/jnz on a register will always be followed by a mov on the register first, so you don't need to worry about uninitialized registers.
Example

SimpleAssebmler.interpret(new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"});

''' visualized:
mov a 5;
inc a;
dec a;
dec a;
jnz a -1;
inc a;
'''';

The above code will:

    set register a to 5,
    increase its value by 1,
    decrease its value by 2,
    then decrease its value until it is zero (jnz a -1 jumps to the previous instruction if a is not zero)
    and then increase its value by 1, leaving register a at 1

So, the function should return

{a=1}

This kata is based on the Advent of Code 2016 - day 12
*/

public class SimpleAssemblerInterpreter{
	//- store regex patterns of instruction
	//- each dictionary key associates with at most one value
	//  - Dictionary has format: Dictionary<Key, Value>
	private static final java.util.Dictionary<String, String> instructionSet;
	
	//- the reference to the Nashorn scripting engine
	//  - Nashorn is the German equivalent for the English-word Rhino
	//  - Rhino is an open-source JavaScript engine written  in Java and managed by the Mozilla Foundation
	private static javax.script.ScriptEngine Nashorn;
	

	//- set variables at class load time
	static{
		//- initial capacity is 4 dictionary entries + 12 blank leaflets
		instructionSet = new java.util.Hashtable<>(16);
		instructionSet.put(
			"mov",
			"mov\\s+(\\p{Alpha})\\s+(\\p{Alpha}|-?\\d+)"
		);
		instructionSet.put("inc", "inc\\s+(\\p{Alpha})");
		instructionSet.put("dec", "dec\\s+(\\p{Alpha})");
		instructionSet.put(
			"jnz",
			"jnz\\s+(\\p{Alpha}|-?\\d+)\\s+(-?\\d+)"
		);
		
		//- Nashorn
		Nashorn =
			new javax.script.ScriptEngineManager()
			.getEngineByName("Nashorn")
		;
	}


	private static String getInstructionFormat(final int instructionNumber, final String instruction){
		return (
			"[lbl] _"+instructionNumber+":"+
			instruction
		);
	}
	
	
	private static String getGotoFormat(final int instructionNumber){
		return (
			"goto _"+
			instructionNumber+
			"; "
		);
	}
	
	
	//- formats instruction list to Alex Form
	//  - https://web.archive.org/web/20160507105653/http://summerofgoto.com/js/goto.js
	//  - AlexSexton@gmail.com
	//- javax.script.ScriptException: <eval>:1:488 Undefined Label "_6" error ensues on input following
	//  - [lbl] _0:a = 1; [lbl] _1:b = 1; [lbl] _2:c = 0;
	//  - [lbl] _3:d = 26; [lbl] _4:if(0!=c) goto _6;
	//  - [lbl] _5:if(0!=1) goto _10; [lbl] _6:c = 7; [lbl] _7:++d;
	//  - [lbl] _8:--c; [lbl] _9:if(0!=c) goto _7; [lbl] _10:c = a;
	//  - [lbl] _11:++a; [lbl] _12:--b; [lbl] _13:if(0!=b) goto _11;
	//  - [lbl] _14:b = c; [lbl] _15:--d; [lbl] _16:if(0!=d) goto _10;
	//  - [lbl] _17:c = 18; [lbl] _18:d = 11; [lbl] _19:++a;
	//  - [lbl] _20:--d; [lbl] _21:if(0!=d) goto _19; [lbl] _22:--c;
	//  - [lbl] _23:if(0!=c) goto _18;
	private static String translateInstructionListToAlexGotoDotJSFormat(String[] instructionList) throws Exception{
		String translation = "";
		String paragraph = "";
		int instructionCount = 0;
		
		//- translation
		//  - format instructions with non-grammatical tokens
		//    - the token set {[lbl], :, goto, var, if, (, ), !, _, --, ++, ;} is not in source-code grammar
		//- only the source-code grammatical empty set {} persists in translation
		for(String instruction: instructionList){
			switch(instruction.substring(0, instruction.indexOf(" "))){
				case "mov":
					translation =
						instruction.replaceFirst(
							instructionSet.get("mov"),
							"$1"
						)+
						" = "+
						instruction.replaceFirst(
							instructionSet.get("mov"),
							"$2"
						)+
						"; "
					;
				break;
				
				case "inc":
					translation =
						"++"+
						instruction.replaceFirst(
							instructionSet.get("inc"),
							"$1"
						)+
						"; "
					;
				break;
				
				case "dec":
					translation =
						"--"+
						instruction.replaceFirst(
							instructionSet.get("dec"),
							"$1"
						)+
						"; "
					;
				break;
				
				case "jnz":
					translation =
						"if(0!="+
						instruction.replaceFirst(
							instructionSet.get("jnz"),
							"$1"
						)+
						") "+
						getGotoFormat(
							Integer.parseInt(
								instruction.replaceFirst(
									instructionSet.get("jnz"),
									"$2"
								)
							)
						)
					;
				break;
				
				default:
					throw new Exception(
						"INVALID OPCODE: "+
						instruction
					)
				;
			}
		
		paragraph+=
			getInstructionFormat(instructionCount, translation)
		;
		++instructionCount;
			
		}
		
		//- translate jnz instructions
		
		//- all jnz instructions
		Object[] jnzSet = java.util.regex.Pattern.compile("; ")
			.splitAsStream(paragraph)
			.filter(w->w.matches("\\[lbl\\] _\\d+:if.*goto _-?\\d+"))
			.toArray()
		;
		
		//- associate each jnz instruction with a goto
		int instructionNumber = 0;
		int jnzDirection = 0;
		for(Object instructionString: jnzSet){

			//- get relative number in instruction sequence
			instructionNumber =
				Integer.parseInt(
					((String)instructionString)
					.replaceFirst("\\[lbl\\] _(\\d+):if.*goto _-?\\d+", "$1")
				)
			;

			//- get jump number relative to self
			jnzDirection =
				Integer.parseInt(
					((String)instructionString)
					.replaceFirst("\\[lbl\\] _\\d+:if.*goto _(-?\\d+)", "$1")
				)
			;

			//- evaluate sequence instruction of goto
			
			if(instructionNumber+jnzDirection<0){
				throw new Exception(
					"JNZ: NULL ["+
					(instructionNumber+jnzDirection)+
					"] INSTRUCTION"
				);
			}
			else if(instructionCount<instructionNumber+jnzDirection){
				paragraph =
					paragraph.substring(
						0,
						paragraph.indexOf((String)instructionString)
					)
				;
				System.out.println(paragraph);
				break;
			}
			else{
				//- jump to past history:		jnzDirection<0
				//- jump to future history:		0<jnzDirection
				paragraph =
					paragraph.replaceFirst(
						((String)instructionString)
						.replace("[","\\[")
						.replace("]","\\]")
						.replace("(","\\(")
						.replace(")","\\)"),
						((String)instructionString)
						.substring(
							0,
							((String)instructionString).indexOf("goto _")+6
						)+
						(instructionNumber+jnzDirection)
					)
				;
			}
			
		}
		
		return paragraph;
	}

	
	//- translates Alex Form to form of one while loop and one switch
	private static String convertAlexFormatToWhileSwitch(String AlexForm) throws Exception{
		String token = "";
		String program = "";
		String translation = "";
		java.util.StringTokenizer instructionSet =
			new java.util.StringTokenizer(AlexForm, ";")
		;
		//- assume initial capacity of 16
		java.util.HashSet<Character> variablesSet =
			new java.util.HashSet<>(16)
		;
		
		//- set while-switch loop format
		translation+=
			"var jnz = 0; loop:while(-1<jnz){ "+
			"switch(jnz){ "+
			"[PLACEHOLDER]"+
			" } "+
			"jnz = -1;"+
			" }"
		;
		
		while(instructionSet.hasMoreTokens()){

			//- do not process "\\s*"
			//  - gives StringIndexOutOfBoundsException
			if(!(token = instructionSet.nextToken()).trim().equals("")){
				
				//- token is fourfold polymorphic
				//  - 1) " ?[lbl] _\d+:\+\+\p{Alpha}"
				//  - 2) " ?[lbl] _\d+:--p{Alpha}"
				//  - 3) " ?[lbl] _\d+:\p{Alpha} = \d+"
				//  - 4) " ?[lbl] _\d+:if(0!=(\\p{Alpha}|-?\\d+)) goto _\d+"
				
				//- register Latin-Alphabet variable name
				//	- it is 3) that introduces names
				if(token.matches(".*:.+\\s+\\=\\s+.*"))
					variablesSet.add(
						token.replaceFirst(".*:(.+)\\s+\\=\\s+.*", "$1")
						.charAt(0)
					)
				;
				
				//- set case constant equal to label number
				//- token is fourfold polymorphic
				//  - 1) " ?[lbl] _\d+:\+\+\p{Alpha}"
				//  - 2) " ?[lbl] _\d+:--p{Alpha}"
				//  - 3) " ?[lbl] _\d+:\p{Alpha} = \d+"
				//  - 4) " ?[lbl] _\d+:if(0!=(\\p{Alpha}|-?\\d+)) goto _\d+"
				program+=
					"case "+
					token.substring(
						token.indexOf("_")+1,
						token.indexOf(":")
					)+
					": "
				;
				
				//- token is fourfold polymorphic
				//  - 1) " ?[lbl] _\d+:\+\+\p{Alpha}"
				//  - 2) " ?[lbl] _\d+:--p{Alpha}"
				//  - 3) " ?[lbl] _\d+:\p{Alpha} = \d+"
				//  - 4) " ?[lbl] _\d+:if(0!=(\\p{Alpha}|-?\\d+)) goto _\d+"
				
				//- do not match polymorph 4)
				//	- token is valid JavaScript syntax
				if(!token.substring(token.indexOf(":")+1).contains("goto")){
					program+=
						token.substring(token.indexOf(":")+1)+"; "
					;
				}
				
				//- match polymorph 4)
				//  - token has JavaScript ungrammatical goto
				//  - 4) " ?[lbl] _\d+:if(0!=(\\p{Alpha}|-?\\d+)) goto _\d+"
				else{
					program+=
					
						//- get "if(0!=(\\p{Alpha}|-?\\d+)) "
						token.substring(
							token.indexOf(":")+1,
							token.indexOf("goto _")
						)+
						
						"{ jnz = "+
						//- get group(1) match in ".*goto _(\d+)"
						//  - equivalent to token.substring(token.lastIndexOf("_")+1)
						token.substring(token.indexOf("goto _")+6)+
						"; "+
						"continue loop; } "
						
					;
				}
				
			}
		}
		
		String variables = "";
		for(Character variable: variablesSet){
			variables+=
				//- initialise all known variables at program begin
				"var "+variable+" = 0; "
			;
		}
		return (
			//- variable initialisations precede all program statements
			variables+
			
			translation.replace("[PLACEHOLDER]", program)
		);
	}
	
	
	public static java.util.Map<String, Integer> interpret(final String[] program){
		
		try{
			//- throws javax.script.ScriptException
			Nashorn.eval(
				//- re-throws Exception
				convertAlexFormatToWhileSwitch(
					//- throws Exception
					translateInstructionListToAlexGotoDotJSFormat(program)
				)
			);

			//- persist engine environment to output Map
			java.util.Map<String, Integer> environment =
				new java.util.HashMap<>()
			;
			for(
			String key:
			Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE)
			.keySet()
			)
				//- jnz variable name is not grammatical to source code
				//	- it is an interpretive token in goto realisations
				if(!key.contains("jnz")){
					environment.put(
						key,
						new Integer(
							Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE)
							.get(key)
							.toString()
							
							//- engine interprets arithmetic as float
							//  - var a = 0;	//a is int (0)
							//  - ++a;			//a now is float (1.0)
							//- replace decimal expansion with ""
							.replaceFirst("\\..*", "")
						)
					);
				}

			//- reset engine history to empty set
			Nashorn =
				new javax.script.ScriptEngineManager()
				.getEngineByName("Nashorn")
			;
				
			return environment;
			
		}catch(javax.script.ScriptException e){

			//- interpret invalid script as error
			//return null;

		}catch(Exception e){

			//- interpret invalid opcode as error
			//return null;

		}
		
		return null;
		
	}
	
	
	public static void main(String ... args) throws Exception{

		java.util.Map<String, Integer> environment = interpret(
			new String[]{"mov a 1", "mov b 1", "mov c 0", "mov d 26", "jnz c 2", "jnz 1 5", "mov c 7", "inc d", "dec c", "jnz c -2", "mov c a", "inc a", "dec b", "jnz b -2", "mov b c", "dec d", "jnz d -6", "mov c 18", "mov d 11", "inc a", "dec d", "jnz d -2", "dec c", "jnz c -5"}
		);
		
		for(String key: environment.keySet())
			System.out.println(key+"= "+environment.get(key))
		;
		
		environment = interpret(
			new String[]{"mov d 100", "dec d", "mov b d", "jnz b -2", "inc d", "mov a d", "jnz 5 10", "mov c a"}
		);
		
		for(String key: environment.keySet())
			System.out.println(key+"= "+environment.get(key))
		;
	
	}
	
}

