package com.successdca;

class ParseString {
    static boolean isArabic, isRome;
    static String operator;
    static int firstNumber,secondNumber;

    public static String parse(String text) {

        //избавляемся от пробелов в строке
        text = text.replaceAll(" ", "");

        //возврат, некорректный математический оператор
        if (!parseOp(text)) {
            return "You are using the wrong math operator";
        }

        isArabic = isContainsArabicNumber(text);
        isRome = isContainsRomeNumber(text);
        if(isRome & isArabic) return "Error: you use both Arabic and Roman numerals";

        if(isArabic){
            char[] ch = text.toCharArray();
            String str1 = "", str2 = "", op = "";
            char charTmp ;
            int count = 0;
            int countFirsDigit = 0;
            int countSecondDigit = 0;

            while (count < ch.length - 1) {
                char c = ch[countFirsDigit];

                if (Character.isDigit(c)) {
                    str1 = str1.concat(Character.toString(c));
                    firstNumber = Integer.parseInt(str1); //первое число
                    countFirsDigit++;//счетчик первого числа
                    count++;//общий счетчик
                    countSecondDigit++;//счетчик второго числа

                } else {
                    countSecondDigit++;
                    count = countSecondDigit;
                    charTmp = c;
                    op = Character.toString(charTmp);
                    char c2 = ch[countSecondDigit];
                    if (Character.isDigit(c2)) {
                        str2 = str2.concat(Character.toString(c2));
                        secondNumber = Integer.parseInt(str2);
                    }
                }
            }
            if( (firstNumber>0) & (firstNumber<=10) &
                    (secondNumber>0) & (secondNumber<=10) )
            return Computation.CalcArab(firstNumber,secondNumber,op);

            return "Error. The numbers entered must be between 1 and 10";
        }

        if(isRome){
            char[] ch = text.toCharArray();
            int countFirsDigit = 0;
            int countSecondDigit = 0;
            int separator=0;
            String str1 = "", str2 = "";
            String[] romeNumeric = { "IX","X" ,"IV", "VIII", "VII", "VI","III","II","I", "V","","L","C" };
            int[] arabicNumeric = {   9  , 10 , 4  ,  8    ,  7   ,  6  ,  3  , 2  , 1 ,  5 , 0, 50,100 };
            for (int i=0; i < (ch.length - 1); i++){
                if((ch[i]=='+')||(ch[i]=='-')||(ch[i]=='*')||(ch[i]=='/'))  separator=i;
            }
            for(int i=0; i<separator; i++)
                str1+=ch[i]; //первое римское число

            for(int i=separator+1; i<(ch.length ); i++)
                str2+=ch[i]; //второе римское число

            for(int i=0; i<romeNumeric.length;i++){
                if (str1.equals(romeNumeric[i])) countFirsDigit=arabicNumeric[i];
                if (str2.equals(romeNumeric[i])) countSecondDigit=arabicNumeric[i];
            }
           if( !((countFirsDigit>0) & (countFirsDigit<=10) &
                    (countSecondDigit>0) & (countSecondDigit<=10)) )
                return "Error. The numbers entered must be between I and X";

            int romeToArab= Integer.parseInt(Computation.CalcArab(countFirsDigit,countSecondDigit,operator));
            return Computation.convertArabInRome(romeToArab);
        }

        return "Unknown error";
    }

    private static boolean parseOp(String str) {
        if (str.contains("-")) {
            operator = "-";
            return true;
        }
        if (str.contains("/")) {
            operator = "/";
            return true;
        }
        if (str.contains("+")) {
            operator = "+";
            return true;
        }
        if (str.contains("*")) {
            operator = "*";
            return true;
        }

        return false;
    }

    private static boolean isContainsArabicNumber(String text) {
        return text.contains("1") || text.contains("2") ||
                text.contains("3") || text.contains("4") || text.contains("5") ||
                text.contains("6") || text.contains("7") || text.contains("8") ||
                text.contains("9") || text.contains("0");
    }

    private static boolean isContainsRomeNumber(String text){
        return text.contains("X") || text.contains("V") || text.contains("I");
    }
}