package ee.taltech.iti0202.idcode;

public class IDCode {
    private enum Gender {
        MALE, FEMALE
    }

    public static boolean isIDCodeCorrect(String idCode) {
        if (idCode.length() == 11 && isNumeric(idCode)) {
            if (isGenderNumberCorrect(idCode) && isDayNumberCorrect(idCode) && isQueueNumberCorrect(idCode)) {
                if (isControlNumberCorrect(idCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isGenderNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(0, 1))) {
            int gender = Integer.parseInt(idCode.substring(0, 1));
            return 0 < gender && gender < 7;
        }
        return false;
    }

    private static boolean isYearNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(1, 3))) {
            int year = Integer.parseInt(idCode.substring(1, 3));
            return 0 <= year && year < 100;
        }
        return false;
    }

    private static boolean isMonthNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(3, 5))) {
            int month = Integer.parseInt(idCode.substring(3, 5));
            return 0 < month && month < 13;
        }
        return false;
    }

    private static boolean isDayNumberCorrect(String idCode) {
        if (isYearNumberCorrect(idCode) && isMonthNumberCorrect(idCode) && isNumeric(idCode.substring(5, 7))) {
            int febAdd = isLeapYear(getFullYear(idCode)) ? 1 : 0;
            int[] days = {31, 28 + febAdd, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int month = Integer.parseInt(idCode.substring(3, 5));
            int monthDayCount = days[month - 1];
            int day = Integer.parseInt(idCode.substring(5, 7));
            return 0 < day && day <= monthDayCount;
        }
        return false;
    }

    private static boolean isQueueNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(7, 10))) {
            int queueNumber = Integer.parseInt(idCode.substring(7, 10));
            return 0 < queueNumber && queueNumber <= 999;
        }
        return false;
    }

    private static boolean isNumeric(String inString) {
        for (char c : inString.toCharArray()){
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isControlNumberCorrect(String idCode) {
        int loopCount = 0;
        int multiplier = 1;
        while (loopCount < 2) {
            loopCount += 1;
            int numCount = 0;
            int checkSum = 0;
            for (char c : idCode.toCharArray()) {
                int num = Character.getNumericValue(c);
                numCount += 1;
                if (numCount == 11) {
                    if (checkSum % 11 == 10) {
                        if (num == 0 && loopCount == 2) {
                            return true;
                        }
                    } else {
                        return checkSum % 11 == num;
                    }
                } else {
                    checkSum += Character.getNumericValue(c) * multiplier;
                    multiplier = multiplier == 9 ? 1 : multiplier + 1;

                }
            }
            multiplier += 1;
        }
        return false;
    }

    private static boolean isLeapYear(int fullYear) {
        return fullYear % 4 == 0 && (fullYear % 100 != 0 || fullYear % 400 == 0);
    }

    public static String getInformationFromIDCode(String idCode) {
        if (isIDCodeCorrect(idCode)) {
            String birthDate = idCode.substring(5, 7) + "." + idCode.substring(3, 5) + "." + getFullYear(idCode);
            return "This is a " + getGender(idCode) + " born on " + birthDate;
        } else {
            return "Given invalid ID code!";
        }
    }

    public static Gender getGender(String idCode) {
        if (isIDCodeCorrect(idCode) && isGenderNumberCorrect(idCode)) {
            if (Integer.parseInt(idCode.substring(0, 1)) % 2 == 0) {
                return Gender.FEMALE;
            } else {
                return Gender.MALE;
            }
        }
        return null;
    }

    public static int getFullYear(String idCode) {
        if (isGenderNumberCorrect(idCode) && isYearNumberCorrect(idCode)) {
            int centuryAdd = (int) Math.ceil(Integer.parseInt(idCode.substring(0, 1)) / 2.0);
            return 1700 + 100 * centuryAdd  + Integer.parseInt(idCode.substring(1, 3));
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getGender("39811060019"));
        System.out.println(getInformationFromIDCode("39811060019"));
        System.out.println(isControlNumberCorrect("39811060019"));
        System.out.println(isIDCodeCorrect("39811060019"));

    }
}