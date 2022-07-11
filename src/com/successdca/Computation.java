package com.successdca;

class Computation {
    static String[] romeNumeric = {"IX", "X", "IV", "VIII", "VII", "VI", "III", "II", "I", "V", "", "L", "C"};
    static int[] arabicNumeric = {9, 10, 4, 8, 7, 6, 3, 2, 1, 5, 0, 50, 100};

    public static String CalcArab(int firstNumber, int secondNumber, String operator) {

        if (operator.equals("*")) {
            return (firstNumber * secondNumber) + "";
        }
        if (operator.equals("/")) {
            return (firstNumber / secondNumber) + "";
        }
        if (operator.equals("+")) {
            return (firstNumber + secondNumber) + "";
        }
        if (operator.equals("-")) {
            return (firstNumber - secondNumber) + "";
        }
        return " Ошибка вычесленний ";
    }

    public static String convertArabInRome(int arabValue) {
        String tmp = "";
        while (arabValue > 0) {
            if (arabValue == 100)  return "C";
            if (arabValue > 89) {
                tmp += "XC";
                arabValue -= 90;
            }
            if ((arabValue < 11) & (arabValue > 0))
                for (int i = 0; i < arabicNumeric.length; i++) {
                    if (arabValue == arabicNumeric[i]) return tmp +=romeNumeric[i];
            }
            if ((arabValue > 39) & (arabValue < 50)) {
                tmp += "XL";
                arabValue -= 40;
            }
            if (arabValue > 49) {
                tmp += "L";
                arabValue -= 50;
            }
            if ((arabValue < 40)& (arabValue > 0)) {
                tmp += "X";
                arabValue -= 10;
            }
        }
        return tmp ;
    }
}
