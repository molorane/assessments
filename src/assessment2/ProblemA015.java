package assessment2;

public class ProblemA015 {

    public static void main(String[] args) {
        loop(2000);
    }

    public static void loop(int num) {
        for (int n = 1; n <= num; n++) {
            System.out.print(n + " = ");
            romanNumerals(n);
        }
    }

    public static void romanNumerals(int n) {

        String ans = "";
        while (n != 0) {
            if (n >= 1000) {
                ans += "M";
                n += -1000;
            } else if (n >= 900) {
                ans += "CM";
                n += - 900;
            } else if (n >= 500) {
                ans += "D";
                n += - 500;
            } else if (n >= 400) {
                ans += "CD";
                n += - 400;
            } else if (n >= 100) {
                ans += "C";
                n += - 100;
            } else if (n >= 90) {
                ans += "XC";
                n += - 90;
            } else if (n >= 50) {
                ans = ans + "L";
                n = n - 50;
            } else if (n >= 40) {
                ans += "XL";
                n += - 40;
            } else if (n >= 10) {
                ans += "X";
                n += - 10;
            } else if (n >= 9) {
                ans += "IX";
                n += - 9;
            } else if (n >= 5) {
                ans += "V";
                n += - 5;
            } else if (n >= 4) {
                ans += "IV";
                n += - 4;
            } else if (n >= 3) {
                ans += "III";
                n += - 3;
            } else if (n >= 2) {
                ans += "II";
                n += -2;
            } else if (n >= 1) {
                ans += "I";
                n += -1;
            }
        }
        System.out.println(ans);
    }
}
