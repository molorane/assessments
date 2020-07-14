/*
Phone Directory
2423876% of 315258 of 1,656g964
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (1)
    Discourse (127)

    Add to Collection
    |
    Share this kata:

John keeps a backup of his old personal phone book as a text file. On each line of the file he can find the phone number (formated as +X-abc-def-ghij where X stands for one or two digits), the corresponding name between < and > and the address.

Unfortunately everything is mixed, things are not always in the same order, lines are cluttered with non-alpha-numeric characters.

Examples of John's phone book lines:

"/+1-541-754-3010 156 Alphand_St. <J Steeve>\n"

" 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!\n"

"<Anastasia> +48-421-674-8974 Via Quirinal Roma\n"

Could you help John with a program that, given the lines of his phone book and a phone number returns a string for this number : "Phone => num, Name => name, Address => adress"

Examples:

s = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!\n"

phone(s, "1-541-754-3010") should return "Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand St."

It can happen that, for a few phone numbers, there are many people for a phone number -say nb- , then

return : "Error => Too many people: nb"

or it can happen that the number nb is not in the phone book, in that case

return: "Error => Not found: nb"

You can see other examples in the test cases.

JavaScript random tests completed by @matt c
Note

Codewars stdout doesn't print part of a string when between < and >

*/

/*
expected:<[Phone => 8-421-674-8974, Name => Elizabeth Corber, Address => Via Papa Roma]> but was:<[Error => Too many people: 8-421-674-8974]>
*/

public class PhoneNumber{
	
	private static String phone(String contactList, final String phoneNumber){

		//reformat meta characters for coherency with regex interpretations
		//% choice by assumption it is not a regex meta character
		contactList = ("\n"+contactList).replace("\n", "%");	
	
		//check for existence
		if(!contactList.matches(".*[^\\d]"+phoneNumber+"[^\\d].*")){
			return "Error => Not found: "+phoneNumber;
		}
		
		//check for uniqueness
		else if(contactList.replaceFirst("[^\\d]"+phoneNumber+"[^\\d]","").matches(".*[^\\d]"+phoneNumber+"[^\\d].*")){
			return "Error => Too many people: "+phoneNumber;
		}
		
		//extrapolate unique contact string
		else{
			
			//replace the contactList with the unique contact 
			contactList = contactList.replaceFirst(".*%(.*)"+phoneNumber+"([^%]*)%.*", "$1$2");
			
			return (
				//the phone number is given
				"Phone => "+phoneNumber+
				
				//the name is in <> delimiters
				", Name => "+contactList.replaceFirst(".*<(.*)>.*", "$1")+
				
				//the address is contactList - {<...>, phoneNumber}
				", Address => "+
					//subtract the phoneNumber from the contactList
					contactList.replace(phoneNumber, "")
					
					//subtract the name section from the contactList
					.replaceFirst("(.*)<.*>(.*)", "$1$2")
					
					//replace non-address characters with the space-bar character
					.replaceAll("[^\\p{Alnum}\\.\\s-]", " ")
					
					.trim()
					.replaceAll(" +", " ")
			);
			
		}
		
	}
	
	public static void main(String ... args){
		String s = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
    + "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n"
    + "+1-741-984-3090 <Peter Reedgrave> _Chicago\n :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n"
	+ "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
    + "+1-111-544-8973 <Peter Pan> LA\n +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n"
    + "<Peter Gone> LA ?+1-121-544-8974 \n <R Steell> Quora Street AB-47209 +1-481-512-2222\n"
    + "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n"
    + "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n"
    + "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n"
    + "+1-099-500-8000 <Peter Crush> Labrador Bd.\n +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n"
    ;
		System.out.println(phone(s, "48-421-674-8974"));
		System.out.println("Phone => 1-921-512-2222, Name => Wilfrid Stevens, Address => Wild Street AA-67209".equals(phone(s, "1-921-512-2222")));
		System.out.println("Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand St.".equals(phone(s, "1-541-754-3010")));
		System.out.println("Phone => 1-121-504-8974, Name => Arthur Clarke, Address => San Antonio TT-45120".equals(phone(s, "1-121-504-8974")));
		System.out.println("Phone => 1-498-512-2222, Name => Bernard Deltheil, Address => Mount Av. Eldorado".equals(phone(s, "1-498-512-2222")));
		System.out.println(phone(s, "1-098-512-2222"));
		System.out.println("Error => Not found: 5-555-555-5555".equals(phone(s, "5-555-555-5555")));
		System.out.println(phone(s, "1-931-512-4855"));
		System.out.println(phone(s, "8-421-674-8974"));
		System.out.println(phone(s, "8-421-674-8974"));
	}
}

/*  
    @Test
    public void test1() {
        System.out.println("Fixed Tests: phone");        
        testing(PhoneDir.phone(dr, "48-421-674-8974"), "Phone => 48-421-674-8974, Name => Anastasia, Address => Via Quirinal Roma");
        testing(PhoneDir.phone(dr, "1-921-512-2222"), "Phone => 1-921-512-2222, Name => Wilfrid Stevens, Address => Wild Street AA-67209");
        testing(PhoneDir.phone(dr, "1-908-512-2222"), "Phone => 1-908-512-2222, Name => Peter O'Brien, Address => High Street CC-47209");
        testing(PhoneDir.phone(dr, "1-541-754-3010"), "Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand St.");
        testing(PhoneDir.phone(dr, "1-121-504-8974"), "Phone => 1-121-504-8974, Name => Arthur Clarke, Address => San Antonio TT-45120");
        testing(PhoneDir.phone(dr, "1-498-512-2222"), "Phone => 1-498-512-2222, Name => Bernard Deltheil, Address => Mount Av. Eldorado");
        testing(PhoneDir.phone(dr, "1-098-512-2222"), "Error => Too many people: 1-098-512-2222");
        testing(PhoneDir.phone(dr, "5-555-555-5555"), "Error => Not found: 5-555-555-5555");
    }
}

*/