package assessment2;


public class Seconds{
	private static String format(final long seconds){
		//java.tim.Duration does not take leap years into account; it is java.util.Calendar that does
		//get days() wherein 365 days = 1 year
		//leap years (multiples of 365.25 days) are not approximated
		long
			years = java.time.Duration.ofSeconds(seconds).toDays()/365,
			days = java.time.Duration.ofSeconds(seconds).toDays()%365,
			hours = java.time.Duration.ofSeconds(seconds).toHours()%24,
			minutes = java.time.Duration.ofSeconds(seconds).toMinutes()%60
		;
		
		 return seconds==0?"now":
            (
                (years!=0?years+" year"+(years==1?"":"s")+", ":"")+
				(days!=0?days+" day"+(days==1?"":"s")+", ":"")+
				(hours!=0?hours+" hour"+(hours==1?"":"s")+", ":"")+
				(minutes!=0?minutes+" minute"+(minutes==1?"":"s")+", ":"")+
                (seconds%60!=0?seconds%60+" second"+(seconds%60==1?"":"s")+", ":"")
            )
			//replace the ending ", " character string with nonentity ("")
			.replaceFirst("\\, $", "")
			
			//replace the ending ", <number> <word>" with " and <number> <word>"
            .replaceFirst("(\\, (\\d+) (\\w+))$", " and $2 $3")
        ;
	}
	
	public static void main(String ... args){
		System.out.println(format(1234));
	}
}