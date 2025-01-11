package ca.bcit.comp2522.bank;

/**
 * @author Ryan Chu
 * @version 1.0
 */
class Date
{
    private final static int MAX_YEARS = 2024;
    private final static int MIN_YEARS = 1800;
    private final static int MAX_MONTHS = 12;
    private final static int MIN_MONTHS = 1;

    private final static int JANUARY = 1;
    private final static int FEBRUARY = 2;
    private final static int MARCH = 3;
    private final static int APRIL = 4;
    private final static int MAY = 5;
    private final static int JUNE = 6;
    private final static int JULY = 7;
    private final static int AUGUST = 8;
    private final static int SEPTEMBER = 9;
    private final static int OCTOBER = 10;
    private final static int NOVEMBER = 11;
    private final static int DECEMBER = 12;

    private final static int MAX_31_DAY_MONTH_DAYS = 31;
    private final static int MAX_30_DAY_MONTH_DAYS = 30;

    private final static int MAX_LEAP_FEB_DAYS = 29;
    private final static int MAX_NO_LEAP_FEB_DAYS = 28;
    private final static int LEAP_YEAR_CYCLE = 4;
    private final static int LEAP_YEAR_CYCLE_EXCEPTION = 100;
    private final static int LEAP_YEAR_VALID = 0;

    private final static int MIN_DAYS = 1;

    private final static int YEAR_1800_OFFSET = 2;
    private final static int YEAR_1900 = 1900;
    private final static int YEAR_2000_OFFSET = 6;
    private final static int YEAR_2000 = 2000;

    //rename step one constant, make them local
    private final static int WEEKDAY_FINDER_CONSTANT_12 = 12;
    private final static int WEEKDAY_FINDER_CONSTANT_4 = 4;

    private final int year;
    private final int month;
    private final int day;


    Date(final int year, final int month, final int day)
    {
        validateNumber(year, MIN_YEARS, MAX_YEARS);
        validateNumber(month, MIN_MONTHS, MAX_MONTHS);
        validateDay(day, month, year);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    private static void validateNumber(final int number, final int min, final int max)
    {
        if(number < min || number > max)
        {
            StringBuilder rangeErrorMessage;

            rangeErrorMessage = new StringBuilder();

            rangeErrorMessage.append(number);
            rangeErrorMessage.append(" out of range of ");
            rangeErrorMessage.append(min);
            rangeErrorMessage.append('-');
            rangeErrorMessage.append(max);

            throw new IllegalStateException(rangeErrorMessage.toString());
        }
    }

    private static void validateDay(final int day, final int month, final int year)
    {
        final int maxDays;

        switch (month)
        {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                maxDays = MAX_30_DAY_MONTH_DAYS;
                break;

            case FEBRUARY:
                maxDays = (isLeapYear(month, year)) ? MAX_LEAP_FEB_DAYS : MAX_NO_LEAP_FEB_DAYS;
                break;

            default:
                maxDays = MAX_31_DAY_MONTH_DAYS;
                break;
        }

        validateNumber(day, MIN_DAYS, maxDays);

    }

    private static boolean isLeapYear(final int month, final int year)
    {
        return (year % LEAP_YEAR_CYCLE == LEAP_YEAR_VALID && year % LEAP_YEAR_CYCLE_EXCEPTION != LEAP_YEAR_VALID) ||
                (year % (LEAP_YEAR_CYCLE * LEAP_YEAR_CYCLE_EXCEPTION) == LEAP_YEAR_VALID);
    }


    public int getDay()
    {
        return this.day;
    }

    public int getMonth()
    {
        return this.month;
    }

    public int getYear()
    {
        return this.year;
    }

    public int getDatOfTheWeek()
    {
        int dayOfTheWeek;

        if(year < YEAR_1900) {

        }
        else if(year > YEAR_1900 && year < YEAR_2000)
        {

        }
        else
        {

        }



        return 0;
    }

}

