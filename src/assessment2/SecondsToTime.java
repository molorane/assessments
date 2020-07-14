
//Human readable duration format
public class SecondsToTime {

    public static void main(String[] args) {
        
       int seconds = 7817*1234;
       String answer = "";
       if(seconds == 0){
           answer = "now";
       }
      

        int year = seconds / (365 * 24 * 3600);

        seconds = seconds % (365 * 24 * 3600);
        int day = seconds / (24 * 3600);

        seconds = seconds % (24 * 3600);
        int hour = seconds / 3600;

        seconds %= 3600;
        int minute = seconds / 60;

        seconds %= 60;
        int second = seconds;

        if (year != 0) {
            if (year > 1) {
                answer += year + " years, ";
            } else {
                answer += year + " year, ";
            }
        }
        if (day != 0) {
            if (day > 1) {
                answer += day + " days, ";
            } else {
                answer += day + " day, ";
            }
        }
        if (hour != 0) {
            if (hour > 1) {
                answer += hour + " hours, ";
            } else {
                answer += hour + " hour, ";
            }
        }
        if (minute != 0) {
            if (minute > 1) {
                answer += minute + " minutes, ";
            } else {
                answer += minute + " minute, ";
            }
        }
        if (second != 0) {
            if (second > 1) {
                answer += second + " seconds, ";
            } else {
                answer += second + " second, ";
            }
        }
        if(answer.endsWith(", ")) answer = answer.substring(0, answer.length() -2);
        if(answer.contains(",")) answer = answer.substring(0, answer.lastIndexOf(",")) + " and" + answer.substring(answer.lastIndexOf(",") + 1);
        /*if(answer.length()>2 && seconds != 0)
            answer = answer.substring(0, answer.length() - 2);
       
        if(answer.lastIndexOf(",") != -1 && seconds != 0)
            answer = answer.substring(0, answer.lastIndexOf(",")) + " and" + answer.substring(answer.lastIndexOf(",") + 1); */
       /*
      if(seconds > 60){
       answer = answer.substring(0, answer.length() - 2);
       
       answer = answer.substring(0, answer.lastIndexOf(",")) + " and" + answer.substring(answer.lastIndexOf(",") + 1);
      } else{
          if(seconds != 0) answer = answer.substring(0, answer.length() - 2);
      } */
         
       /* StringBuilder b = new StringBuilder(answer);
       
         b.replace(answer.lastIndexOf(" "), answer.lastIndexOf(" ") + 1, "");
        answer = b.toString();
        b.replace(answer.lastIndexOf(","), answer.lastIndexOf(",") + 1, "");
        answer = b.toString(); 
        b.replace(answer.lastIndexOf(","), answer.lastIndexOf(",") + 1, " and");
        answer = b.toString();*/
        System.out.println(answer);

		}
		}