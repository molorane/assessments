/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment2;

/**
 *
 * @author GIANNI
 */
public class Problem023a {

    public static void main(String[] args) {
        loop(400);

    }

    public static void loop(int num) {
        for (int n = 1; n <= num; n++) {
            numberSystemFact(n);
        }
    }

    public static void numberSystemFact(int N) {

        int highestFact = 0;

        for (int countUpFact = 1; N >= findFact(countUpFact); countUpFact++) {
            highestFact = countUpFact;
        }

        String add = "";
        String fact = "";
        int remainder = N;

        for (int count = highestFact; count > 0; count--) {

            if (remainder / findFact(highestFact) >= 1) {
                fact += remainder / findFact(highestFact) + " X " + highestFact + "!" + " + ";
                add += (remainder / findFact(highestFact));
                remainder = (remainder % findFact(highestFact));
                highestFact--;

            } else {
                fact += "0 X " + highestFact + "!" + " + ";
                highestFact--;
                add += "0";
            }
        }
        System.out.println(N + " = " + add + " (which is " + fact.substring(0, fact.length()-2) + ")");

    }

    public static int findFact(int num) {
        int fact = 1;
        for (int i = 1; i <= num; ++i) {
            fact *= i;
        }
        return fact;
    }
}
