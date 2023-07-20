package utils;

import java.util.Calendar;
import java.util.Random;


public class CNPGenerator {
    private static final Random random = new Random();

    public static String generateCNP() {
        StringBuilder cnpBuilder = new StringBuilder();

        // Gender
        int gender = random.nextInt(9) + 1;
        cnpBuilder.append(gender);

        // Year of Birth
        int year = generateYearOfBirth(gender);
        cnpBuilder.append(String.format("%02d", year % 100));

        // Month of Birth
        int month = random.nextInt(12) + 1;
        cnpBuilder.append(String.format("%02d", month));

        // Date of Birth
        int maxDays = getMaxDaysInMonth(month, year);
        int day = random.nextInt(maxDays) + 1;
        cnpBuilder.append(String.format("%02d", day));

        // Area Code
        int areaCode = random.nextInt(52) + 1;
        cnpBuilder.append(String.format("%02d", areaCode));

        // Order Number
        int orderNumber = random.nextInt(1000);
        cnpBuilder.append(String.format("%03d", orderNumber));

        // Control Number
        int controlNumber = calculateControlNumber(cnpBuilder.toString());
        cnpBuilder.append(controlNumber);

        return cnpBuilder.toString();
    }

    private static int getMaxDaysInMonth(int month, int year) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    private static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }

    private static int generateYearOfBirth(int gender) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int startYear, endYear;

        switch (gender) {
            case 1:
            case 2:
                startYear = 1900;
                endYear = 1999;
                break;
            case 3:
            case 4:
                startYear = 1800;
                endYear = 1899;
                break;
            case 5:
            case 6:
                startYear = 2000;
                endYear = currentYear;
                break;
            case 7:
            case 8:
            case 9:
                startYear = 1800;
                endYear = currentYear;
                break;
            default:
                throw new IllegalArgumentException("Invalid gender: " + gender);
        }

        return random.nextInt(endYear - startYear + 1) + startYear;
    }

    private static int calculateControlNumber(String cnp) {
        String digits = cnp.substring(0, 12);
        String positionNumbers = "279146358279";
        int sum = 0;

        for (int i = 0; i < digits.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(digits.charAt(i)));
            int position = Integer.parseInt(String.valueOf(positionNumbers.charAt(i)));
            sum += digit * position;
        }

        int remainder = sum % 11;
        return remainder == 10 ? 1 : remainder;
    }
}
