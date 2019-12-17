package com.calculator;

/**
 * class for converting Arab values in Rome digits
 */
public class RomeConverter {

    /**
     * @param arabDigitValue
     *
     * @return rome value
     */
    int analyzingValue;

    public String convertRomeDigitsResult(int arabDigitValue) {
       try {
           analyzingValue = arabDigitValue;
           StringBuffer additionalString = new StringBuffer("");
           String simpleResult = analyzeSimpleValues(arabDigitValue);
           if (simpleResult != ""){
               return simpleResult;
           }
           /*max possible value is 100*/
           int convertingValue1 = arabDigitValue / 100;
           additionalString.append(convertC(convertingValue1));
           int remain1 = arabDigitValue % 100;

           int convertingValue2 = remain1 / 50;
           additionalString.append(convertL(convertingValue2));
           int remain2 = remain1 % 50;

           int convertingValue3 = remain2 / 10;
           additionalString.append(convertX(convertingValue3));
           int remain3 = remain2 % 10;

           additionalString.append(basic(remain3));
           return additionalString.toString();
       }
       catch (Exception ex){
           throw new ArithmeticException("convertRomeDigitsResult error");
       }
    }

    private String analyzeSimpleValues(int arabDigitValue) {
      switch (arabDigitValue){
          case 100:
              return "C";
          case 50:
              return "L";
          case 10:
              return "X";
      }
      return "";
    }

    // int 100 converter
    private String convertC(int inputValue) {
        if (inputValue == 4) return "CD";
        if ((inputValue != 0) && (inputValue < 4)) {
            StringBuffer additionalString = new StringBuffer("");
            int i = 0;
            while (i < inputValue) {
                additionalString.append("C");
                i++;
            }
            return additionalString.toString();
        }
        else return "";
    }

    private String convertX(int inputValue) {
        if (((int)Math.floor(analyzingValue / 10)) == 4){
            return "XL";
        }
        if (inputValue == 4) return "";
        if ((inputValue != 0) && (inputValue < 4)) {
            StringBuffer additionalString = new StringBuffer("");
            int i = 0;
            while (i < inputValue) {
                additionalString.append("X");
                i++;
            }
            return additionalString.toString();
        }
        else return "";
    }


    private String convertL(int inputValue) {
        if (inputValue == 0) {
            return "";
        }
        if ((inputValue == 4) || (inputValue == 1)) {
            return "XC";
        }
        return "L";
    }
    // От 1 до 9, то что осталось
    private String basic(int inputValue) {
        String[] romeArray = {
                "",
                "I",
                "II",
                "III",
                "IV",
                "V",
                "VI",
                "VII",
                "VIII",
                "IX"
        };
        return romeArray[inputValue];
    }

}
