package assessment2;


/*Snail Sort

Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],		[1,2,3]
         [4,5,6],		[6,9,8]
         [7,8,9]]		[7,4,5]
snail(array) #=> [1,2,3,6,9,8,7,4,5]

For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]

This image will illustrate things more clearly:

NOTE: The idea is not sort the elements from the lowest value to the highest; the idea is to traverse the 2-d array in a clockwise snailshell pattern.

NOTE 2: The 0x0 (empty matrix) is represented as [[]]
*/


/*******
 *******
 
 The solution is the following chronology.
 1) Abstract the input matrix to a different matrix by one-to-one mapping
 2) Snail sort the matrix of abstraction to obtain sortedAbstraction
 3) From sortedAbstraction, get the transformed input matrix by inverse of the one-to-one mapping
 
 The following is a table of empirical data and postulation thereon
 
 =======================
 TABLE OF EMPIRICAL DATA
 =======================
 
 -------
 Let all input matrices of dimension NxN map to the abstraction
	[0, 0, ..., 0]		//N 0's
	[0, 0, ..., 0]		//N 0's
	...				----|			
	...					|--- //N-3 rows		
	...				----|
	[0, 0, ..., 0]		//N 0's

 Then, postulations of sorting are one the abstraction.
 All numbers in the abstraction uniquely are indexed and may be mapped to the unique indices of the input matrix.
 -------
 
 3x3		sorted		postulation (indices contains transformation; N=3 is constant)
 -------------------------------------------------------------------------------------
 
						[FIRST ITERATION IS IN QUESTION: INDEX=0]
						{row1Property=1; row2Property=N; row3Property=N*N}
						{row1PropertyMultiplicand=1; row2PropertyLimit=N*(N-1); row3PropertySubtrahend=N-1}
						for(int index=0; index<N*N-1; ){
 [0, 0, 0]	[1, 2, 3]		// for(int i=row1Property; i<row2Property; ++i, ++index){indices[index]=i;}
							// creates the sequence indices = [1, 2]
							// sets: index = 2 as run is for i=1, i=2 (i=3 halts whence ++index ensues)
	 
 [0, 0, 0]	[6, 9, 8]		{row2Property=3, row2PropertyLimit=3*(3-1)=6, index=2}
							// for(int i=row2Property; i<=row2PropertyLimit; i+=N, ++index){indices[index]=i;}
							// runs for: i=3, i=3+3=6 (halts at: i=6+3=9 whence ++index ensues)
							// creates the sequence indices = [1, 2]|[3, 6]
							// sets: index = 2+2 = 4;
							
 [0, 0, 0]	[7, 4, 5]		{row3Property=3*3=9, row3PropertySubtrahend=(3-1)=2, index=4}
							{row3Property-row3PropertySubtrahend = 7}
							// for(int i=row3Property; row3Property-row3PropertySubtrahend<i; --i, ++index){indices[index]=i;}
							// runs for: i=9, --i=8 (halts at: --i=7 whence ++index ensues)
							// creates the sequence indices = [1, 2]|[3, 6]|[9, 8]
							// sets: index =  4+2 = 6;
 
							{row3Property=3*3=9, row3PropertySubtrahend=(3-1)=2, index=6}
							{row3Property-row3PropertySubtrahend = 7, row2Property+N+1 = 3+3+1 = 7}
							// for(int i=row3Property-row3PropertySubtrahend; row2Property+N+1<=i; i-=N, ++index){indices[index]=i;}
							// runs for: i=7, i-=3 = 4 (halts at: i-=3 = -3 whence ++index ensues)
							// creates the sequence indices = [1, 2]|[3, 6]|[9, 8]|[7, 4]
							// sets: index =  6+2 = 8;
							// index=8 halts enclosing loop
							
							// set variables for the next iteration
							
							row1Property = (N+1)*row1PropertyMultiplicand;
							++row1PropertyMultiplicand;
							
							row2Property += (N-1);
							row2PropertyLimit -= (N+1);
							
							row3Property -= (N+1);
							row3PropertySubtrahend -= 2;
							
						}
						
						indices[N*N-1] = indices[N*N-2] - (N%2==0?1:-1);
						// set the length-1 element
						// creates the sequence indices = [1, 2]|[3, 6]|[9, 8]|[7, 4]|[5]
						// algorithm is correct for N=3
						
---------------------------------------------------------------------------------------------------------------------------------

		
 4x4		sorted		postulation (indices contains transformation; N=3 is constant)
 -------------------------------------------------------------------------------------
						
						[FIRST ITERATION IS IN QUESTION: INDEX=0]
						{row1Property=1; row2Property=N; row3Property=N*N}
						{row1PropertyMultiplicand=1; row2PropertyLimit=N*(N-1); row3PropertySubtrahend=N-1}
						for(int index=0; index<N*N-1; ){
 [0,0,0,0][1,2,3,4]			// for(int i=row1Property; i<row2Property; ++i, ++index){indices[index]=i;}
							// creates the sequence indices = [1, 2, 3]
							// sets: index = 3 as run is for i=1, i=2, i=3 (i=4 halts whence ++index ensues)
	 
 [0,0,0,0][8,12,16,15]		{row2Property=4, row2PropertyLimit=4*(4-1)=4*3=12, index=3}
							// for(int i=row2Property; i<=row2PropertyLimit; i+=N, ++index){indices[index]=i;}
							// runs for: i=4, i=4+4=8, i=8+4=12 (halts at: i=12+4=16 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]
							// sets: index = 3+3 = 6;
							
 [0,0,0,0][14,13,9,5]		{row3Property=4*4=16, row3PropertySubtrahend=(4-1)=3, index=6}
							{row3Property-row3PropertySubtrahend = 16-3 = 13}
							// for(int i=row3Property; row3Property-row3PropertySubtrahend<i; --i, ++index){indices[index]=i;}
							// runs for: i=16, --i=15, --i=14 (halts at: , --i=13 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]
							// sets: index =  6+3 = 9;
 
 [0,0,0,0][6,7,11,10]		{row3Property=4*4=16, row3PropertySubtrahend=(4-1)=3, index=9}
							{row3Property-row3PropertySubtrahend = 16-3 = 13, row2Property+N+1 = 4+4+1 = 9}
							// for(int i=row3Property-row3PropertySubtrahend; row2Property+N+1<=i; i-=N, ++index){indices[index]=i;}
							// runs for: i=13, i-=4 = 9 (halts at: i-=4 = 5 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]
							// sets: index =  9+2 = 11;
							
							// set variables for the next iteration
							
							row1Property = (N+1)*row1PropertyMultiplicand;
							++row1PropertyMultiplicand;
							
							row2Property += (N-1);
							row2PropertyLimit -= (N+1);
							
							row3Property -= (N+1);
							row3PropertySubtrahend -= 2;
							
						}
						
						
						[SECOND ITERATION IS IN QUESTION: INDEX=11]
						{row1Property = (4+1)*1 = 5; row2Property = N+(N-1) = 4+3 = 7; row3Property = N*N-(N+1) = 16-5 = 11}
						{
							row1PropertyMultiplicand = 2;
							row2PropertyLimit = N*(N-1)-(N+1) = 4*3-5 = 7;
							row3PropertySubtrahend = N-1-2 = 4-1-2 = 1
						}
						{indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]}
						{N*N-1 = 4*4-1 = 15}
						for(int index=11; index<N*N-1; ){
 [0,0,0,0][1,2,3,4]			// for(int i=row1Property; i<row2Property; ++i, ++index){indices[index]=i;}
							// runs for: i=5, i=5+1=6 (halts at: i=6+1=7 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]|[5, 6]
							// sets: index = 11+2 = 13
	 
 [0,0,0,0][8,12,16,15]		{row2Property=7, row2PropertyLimit=7, index=13}
							// for(int i=row2Property; i<=row2PropertyLimit; i+=N, ++index){indices[index]=i;}
							// runs for: i=7 (halts at: i=7+4=11 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]|[5, 6]|[7]
							// sets: index = 13+1 = 14
							
 [0,0,0,0][14,13,9,5]		{row3Property=11, row3PropertySubtrahend=1, index=14}
							{row3Property-row3PropertySubtrahend = 11-1 = 10}
							// for(int i=row3Property; row3Property-row3PropertySubtrahend<i; --i, ++index){indices[index]=i;}
							// runs for: i=11 (halts at: , --i=10 whence ++index ensues)
							// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]|[5, 6]|[7]|[11]
							// sets: index =  14+1 = 15;
 
 [0,0,0,0][6,7,11,10]		{row3Property=11, row3PropertySubtrahend=1, index=15}
							{row3Property-row3PropertySubtrahend = 11-1 = 10, row2Property+N+1 = 7+4+1=12}
							// for(int i=row3Property-row3PropertySubtrahend; row2Property+N+1<=i; i-=N, ++index){indices[index]=i;}
							// runs for: (halts at: i=10 whence 0 iteration ensues)
							// index=15 halts enclosing loop
							// ensuingly, is the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]|[5, 6]|[7]|[11]|[0]
							
							// set variables for the next iteration
							
							row1Property = (N+1)*row1PropertyMultiplicand;
							++row1PropertyMultiplicand;
							
							row2Property += (N-1);
							row2PropertyLimit -= (N+1);
							
							row3Property -= (N+1);
							row3PropertySubtrahend -= 2;
							
						}
						
						indices[N*N-1] = indices[N*N-2] - (N%2==0?1:-1);
						// set the length-1 element
						// creates the sequence indices = [1, 2, 3]|[4, 8, 12]|[16, 15, 14]|[13, 9]|[5, 6]|[7]|[11]|[10]
						// algorithm is correct for N=4
						
 *******
 *******/

public class SnailSort{
	private static void transformAbstraction(int[] abstractionMatrix, final int[][] inputMatrix){
		for(int i=0; i<abstractionMatrix.length; ++i)
			abstractionMatrix[i] =
				inputMatrix[
					abstractionMatrix[i]/inputMatrix.length-(abstractionMatrix[i]%inputMatrix.length!=0?0:1)
				][
					(abstractionMatrix[i]-inputMatrix.length*(
						abstractionMatrix[i]/inputMatrix.length-(abstractionMatrix[i]%inputMatrix.length!=0?0:1)
						)
					)-1
				]
			;
	}
	
	public static int[] snail(final int[][] matrix){
		// exceptions of postulation
		if(matrix.length == 0 || matrix[0].length == 0) return new int[0];
		if(matrix.length == 1) return new int[]{matrix[0][0]};
		
		final int N = matrix.length;
		int[] indices = new int[N*N];
		
		// {row1Property=1; row2Property=N; row3Property=N*N}
		// {row1PropertyMultiplicand=1; row2PropertyLimit=N*(N-1); row3PropertySubtrahend=N-1}
		for(
			int index=0, row1Property=1, row2Property=N, row3Property=N*N,
				row1PropertyMultiplicand=1, row2PropertyLimit=N*(N-1), row3PropertySubtrahend=N-1
			;
			index <
				N*N-1
			;
		){
			for(int i=row1Property; i<row2Property; ++i, ++index){indices[index]=i;}

			for(int i=row2Property; i<=row2PropertyLimit; i+=N, ++index){indices[index]=i;}

			for(int i=row3Property; row3Property-row3PropertySubtrahend<i; --i, ++index){indices[index]=i;}

			for(int i=row3Property-row3PropertySubtrahend; row2Property+N+1<=i; i-=N, ++index){indices[index]=i;}


			// set variables for the next iteration
			
			row1Property = (N+1)*row1PropertyMultiplicand;
			++row1PropertyMultiplicand;
			
			row2Property += (N-1);
			row2PropertyLimit -= (N+1);
			
			row3Property -= (N+1);
			row3PropertySubtrahend -= 2;

		}

		indices[N*N-1] = indices[N*N-2] - (N%2==0?1:-1);
		
		
		// modifies indices; does not modify 
		transformAbstraction(indices, matrix);
		
		return indices;
		
	}
	
	public static void main(String ... args){
		System.out.println(java.util.Arrays.toString(snail(new int[][]{{1, 2, 3, 9}, {4, 5, 6, 4}, {7, 8, 9, 1}, {1, 2, 3, 4}})));
	}

}

/*
[[1, 2, 3, 9]
[4, 5, 6, 4]
[7, 8, 9, 1]
[1, 2, 3, 4]] should be sorted to
[1, 2, 3, 9, 4, 1, 4, 3, 2, 1, 7, 4, 5, 6, 9, 8]
[1, 2, 3, 9, 4, 1, 4, 3, 2, 1, 7, 4, 5, 6, 9, 8]

[[]] should be sorted to []
*/