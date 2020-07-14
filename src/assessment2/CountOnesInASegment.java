/*CountOnesInASegment
1733996% of 15140 of 529d0n14
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (4)
    Discourse (42)

    Add to Collection
    |
    Share this kata:

Given two numbers: 'left' and 'right' (1 <= 'left' <= 'right' <= 200000000000000) return sum of all '1' occurencies in binary representations of numbers between 'left' and 'right' (including both)

Example:
countOnes 4 7 should return 8, because:
4(dec) = 100(bin), which adds 1 to the result.
5(dec) = 101(bin), which adds 2 to the result.
6(dec) = 110(bin), which adds 2 to the result.
7(dec) = 111(bin), which adds 3 to the result.
So finally result equals 8.

1-3:		4
4-7:		8
8-15:		20

1:		001
2:		010
3:		011
4:		100
5:		101
6:		110
7:		111
8:		1000
9:		1001
10:		1010
11:		1011
12:		1100
13:		1101
14:		1110
15:		1111


WARNING: Segment may contain billion elements, to pass this kata, your solution cannot iterate through all numbers in the segment!
*/