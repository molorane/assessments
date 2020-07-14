package assessment2;

/*(Range Extraction
3376288% of 972710 of 6,733jhoffner
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (5)
    Discourse (96)

    Add to Collection
    |
    Share this kata:

A format for expressing an ordered list of integers is to use a comma separated list of either

    individual integers
    or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example ("12, 13, 15-17")

Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

Solution.rangeExtraction(new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
# returns "-6,-3-1,3-5,7-11,14,15,17-20"

Courtesy of rosettacode.org
*/

public class RangeExtraction{
	public static String rangeExtraction(final int[] integers){
    //
    if(integers.length == 1) return ""+integers[0];
  
		//list to contain format tokens
		java.util.ArrayList<String> format = new java.util.ArrayList<>(integers.length);
		
		//iterate through entire array size - 1 elements
		for(int i=0; i<=integers.length-2; ++i)
			
			//check if two immediately adjacent elements are consecutive
			if(integers[i]+1 == integers[i+1]){
				
				//if empty list, add <number>- token
				//if non-empty list and if <number>- token exists, continue to for()
				if(format.isEmpty() || !format.get(format.size()-1).matches("-*\\d+-"))
					format.add(integers[i]+"-");
			}
			
			//the current element is not consecutive with the next
			else{
				if(
					//if empty list, short circuit condition
					!format.isEmpty() &&
					
					//check if current token is in <number>- format
					Integer.parseInt(format.get(format.size()-1).replaceFirst("(-*\\d+).*", "$1")) == integers[i]-1
				)
					//undo <number>- format if the case
					format.set(
						format.size()-1,
						format.get(format.size()-1).replaceFirst("-$", ", ")
					);
					
				//add current element to list of tokens
				format.add(integers[i]+", ");
			}
			
   //idiosyncrasies of format   
   if(format.get(format.size()-1).endsWith("-") && Integer.parseInt(format.get(format.size()-1).replaceFirst("(-*\\d+).*", "$1")) == integers[integers.length-1]-1)
     format.set(
       format.size()-1,
       format.get(format.size()-1)
       .substring(
         0,
         format.get(format.size()-1).length()-1
       )+", "
     );   
      
		return (
			//dependence on toString() idiosyncrasies
			format.toString()
			.replace("-, ", "-")
			.replace(", ,", ",")
			.replace("[", "")
			.replace("]", "")
      
      //idiosyncrasies of format
      .replaceAll("\\s", "")+
			
			//append last array element as for() is from 0 to array size - 2
			integers[integers.length-1]
		);
	}
	public static void main(String ... args){
		System.out.println(rangeExtraction(new int[] {-3,-2,-1,2,10,15,16,18,19,20,24,25,26}));
	}
}