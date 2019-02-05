package ee.taltech.iti0202.idcode;

public class IDCode {
    private static final int ID_CODE_LEN = 11;
    private static final int MIN_GENDER_INT = 1;
    private static final int MAX_GENDER_INT = 6;
    private static final int MIN_YEAR = 0;
    private static final int MAX_YEAR = 99;
    private static final int YEAR_START = 1;
    private static final int YEAR_END = 2;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MONTH_START = 3;
    private static final int MONTH_END = 4;
    private static final int MIN_QUEUE = 1;
    private static final int MAX_QUEUE = 999;
    private static final int QUEUE_START = 7;
    private static final int QUEUE_END = 9;
    private static final int BASE_YEAR = 1700;
    private static final int CENTURY = 100;
    private static final int FOUR_CENTURIES = 400;
    private static final int DAY_START = 5;
    private static final int DAY_END = 6;
    private static final int FEB_NORMAL = 28;
    private static final int MONTH_SMALL = 30;
    private static final int MONTH_BIG = 31;
    private static final int MAX_MULTIPLIER = 9;

    private enum Gender {
        MALE, FEMALE
    }

    public static boolean isIDCodeCorrect(String idCode) {
        if (idCode.length() == ID_CODE_LEN && isNumeric(idCode)) {
            if (isGenderNumberCorrect(idCode) && isDayNumberCorrect(idCode) && isQueueNumberCorrect(idCode)) {
                return isControlNumberCorrect(idCode);
            }
        }
        return false;
    }

    private static boolean isGenderNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(0, 1))) {
            int gender = Integer.parseInt(idCode.substring(0, 1));
            return MIN_GENDER_INT <= gender && gender <= MAX_GENDER_INT;
        }
        return false;
    }

    private static boolean isYearNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(YEAR_START, YEAR_END + 1))) {
            int year = Integer.parseInt(idCode.substring(YEAR_START, YEAR_END + 1));
            return MIN_YEAR <= year && year <= MAX_YEAR;
        }
        return false;
    }

    private static boolean isMonthNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(MONTH_START, MONTH_END + 1))) {
            int month = Integer.parseInt(idCode.substring(MONTH_START, MONTH_END + 1));
            return MIN_MONTH <= month && month <= MAX_MONTH;
        }
        return false;
    }

    private static boolean isDayNumberCorrect(String idCode) {
        if (isYearNumberCorrect(idCode) && isMonthNumberCorrect(idCode) && isNumeric(idCode.substring(DAY_START, DAY_END + 1))) {
            int febAdd = isLeapYear(getFullYear(idCode)) ? 1 : 0;
            int[] days = {MONTH_SMALL, FEB_NORMAL + febAdd, MONTH_BIG, MONTH_SMALL, MONTH_BIG, MONTH_SMALL, MONTH_BIG, MONTH_BIG, MONTH_SMALL, MONTH_BIG, MONTH_SMALL, MONTH_BIG};
            int month = Integer.parseInt(idCode.substring(MONTH_START, MONTH_END + 1));
            int monthDayCount = days[month - 1];
            int day = Integer.parseInt(idCode.substring(DAY_START, DAY_END + 1));
            return 0 < day && day <= monthDayCount;
        }
        return false;
    }

    private static boolean isQueueNumberCorrect(String idCode) {
        if (isNumeric(idCode.substring(QUEUE_START, QUEUE_END + 1))) {
            int queueNumber = Integer.parseInt(idCode.substring(QUEUE_START, QUEUE_END + 1));
            return MIN_QUEUE <= queueNumber && queueNumber <= MAX_QUEUE;
        }
        return false;
    }

    private static boolean isNumeric(String inString) {
        for (char c : inString.toCharArray()) {
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
                if (numCount == ID_CODE_LEN) {
                    if (checkSum % ID_CODE_LEN == 10) {
                        if (num == 0 && loopCount == 2) {
                            return true;
                        }
                    } else {
                        return checkSum % ID_CODE_LEN == num;
                    }
                } else {
                    checkSum += Character.getNumericValue(c) * multiplier;
                    multiplier = multiplier == MAX_MULTIPLIER ? 1 : multiplier + 1;

                }
            }
            multiplier += 1;
        }
        return false;
    }

    private static boolean isLeapYear(int fullYear) {
        return fullYear % 4 == 0 && (fullYear % CENTURY != 0 || fullYear % FOUR_CENTURIES == 0);
    }

    public static String getInformationFromIDCode(String idCode) {
        if (isIDCodeCorrect(idCode)) {
            String birthDate = idCode.substring(DAY_START, DAY_END + 1) + "." + idCode.substring(MONTH_START, MONTH_END + 1) + "." + getFullYear(idCode);
            return "This is a " + getGender(idCode).toString().toLowerCase() + " born on " + birthDate;
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
            return BASE_YEAR + CENTURY * centuryAdd + Integer.parseInt(idCode.substring(1, 3));
        }
        return 0;
    }

    /* public static void main(String[] args) {
        System.out.println(getGender("39811060019"));
        System.out.println(getInformationFromIDCode("39811060019"));
        System.out.println(isControlNumberCorrect("39811060019"));
        System.out.println(isIDCodeCorrect("39811060019"));

    } */
}
