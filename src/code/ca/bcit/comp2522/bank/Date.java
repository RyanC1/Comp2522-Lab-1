package ca.bcit.comp2522.bank;

/**
 * Represents a Date made of a year, month, and day.
 * Has functionality to find the weekday and compare dates.
 *
 * @author Mohammad Sadeghi
 * @author Ryan Chu
 * @version 1.0
 */
class Date
{
    private final static int MIN_YEAR  = 1800;
    private final static int MAX_YEAR  = 2024;
    private final static int MIN_MONTH = 1;
    private final static int MAX_MONTH = 12;
    private final static int MIN_DAY   = 1;

    private final static int LONG_MONTH_MAX_DAY  = 31;
    private final static int SHORT_MONTH_MAX_DAY = 30;
    private final static int LEAP_FEB_MAX_DAY    = 29;
    private final static int NO_LEAP_FEB_MAX_DAY = 28;

    private final static int JANUARY   = 1;
    private final static int FEBRUARY  = 2;
    private final static int MARCH     = 3;
    private final static int APRIL     = 4;
    private final static int MAY       = 5;
    private final static int JUNE      = 6;
    private final static int JULY      = 7;
    private final static int AUGUST    = 8;
    private final static int SEPTEMBER = 9;
    private final static int OCTOBER   = 10;
    private final static int NOVEMBER  = 11;
    private final static int DECEMBER  = 12;

    private final static int LEAP_YEAR_CYCLE       = 4;
    private final static int CENTURY_INTERVAL      = 100;
    private final static int LEAP_CENTURY_INTERVAL = 400;
    private final static int NO_REMAINDER          = 0;

    private final static int    NINETEENTH_CENTURY_START    = 1800;
    private final static int    TWENTIETH_CENTURY_START     = 1900;
    private final static int    TWENTY_FIRST_CENTURY_START  = 2000;
    private final static int    NINETEENTH_CENTURY_OFFSET   = 2;
    private final static int    TWENTIETH_CENTURY_OFFSET    = 0;
    private final static int    TWENTY_FIRST_CENTURY_OFFSET = 6;
    private final static int    LEAP_YEAR_OFFSET            = 6;
    private final static int    NOTHING                     = 0;
    private final static int    STEP_1_CONSTANT             = 12;
    private final static int    STEP_3_CONSTANT             = 4;
    private final static String STEP_5_CONSTANT             = "144025036146";
    private final static int    STEP_5_CONSTANT_OFFSET      = 1;
    private final static int    STEP_6_CONSTANT             = 7;

    private final static int SATURDAY  = 0;
    private final static int SUNDAY    = 1;
    private final static int MONDAY    = 2;
    private final static int TUESDAY   = 3;
    private final static int WEDNESDAY = 4;
    private final static int THURSDAY  = 5;
    private final static int FRIDAY    = 6;

    private final static int NO_DIFFERENCE = 0;


    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a Date object
     *
     * @param year  the year to set (MIN_YEAR - MAX_YEAR)
     * @param month the month to set (MIN_MONTH - MAX-MONTH)
     * @param day   the day to set (MIN_DAY - _MAX_DAY dependent on month)
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateInt(year,
                    MIN_YEAR,
                    MAX_YEAR);
        validateInt(month,
                    MIN_MONTH,
                    MAX_MONTH);
        validateDay(day,
                    month,
                    year);

        this.year  = year;
        this.month = month;
        this.day   = day;
    }

    /**
     * Validates a given int is withing a specified range.
     *
     * @param number the number to check
     * @param min    the lower bound to check
     * @param max    the upper bound to check
     */
    private static void validateInt(final int number,
                                    final int min,
                                    final int max)
    {
        if(number < min || number > max)
        {
            throw new IllegalArgumentException("Out of range: " + min + " - " + max + " got: " + number);
        }
    }

    /**
     * Validates whether a day is valid given the month and year.
     *
     * @param day   the day to check
     * @param month the month to check
     * @param year  the year to check
     */
    private static void validateDay(final int day,
                                    final int month,
                                    final int year)
    {

        switch(month)
        {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> validateInt(day,
                                                                                     MIN_DAY,
                                                                                     LONG_MONTH_MAX_DAY);

            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> validateInt(day,
                                                                 MIN_DAY,
                                                                 SHORT_MONTH_MAX_DAY);
            case FEBRUARY ->
            {
                if(isLeapYear(year))
                {
                    validateInt(day,
                                MIN_DAY,
                                LEAP_FEB_MAX_DAY);
                }
                else
                {
                    validateInt(day,
                                MIN_DAY,
                                NO_LEAP_FEB_MAX_DAY);
                }
            }

            default -> throw new IllegalStateException("Invalid month: " + month);
        }


    }

    /**
     * Checks if the given year is a leap year.
     *
     * @param year the year to check
     * @return if the year is a leap year as a boolean
     */
    private static boolean isLeapYear(final int year)
    {
        return (year % LEAP_YEAR_CYCLE == NO_REMAINDER && year % CENTURY_INTERVAL != NO_REMAINDER) ||
               (year % (LEAP_CENTURY_INTERVAL) == NO_REMAINDER);
    }


    /**
     * Returns the day of the month for this date.
     *
     * @return the day of the month
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     * Returns the month of the date.
     *
     * @return the month (1 = January, 2 = February, ..., 12 = December)
     */
    public int getMonth()
    {
        return this.month;
    }

    /**
     * Returns the year of the date.
     *
     * @return the year of the date
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Returns the date in "Month name day, year" format such as "May 2, 2020".
     *
     * @return a string representation of the date in "Month name day, year" format
     */
    public String getYyyyMmDd()
    {
        final String monthName;
        monthName = switch(month) {
            case JANUARY -> "January";
            case FEBRUARY -> "February";
            case MARCH -> "March";
            case APRIL -> "April";
            case MAY -> "May";
            case JUNE -> "June";
            case JULY -> "July";
            case AUGUST -> "August";
            case SEPTEMBER -> "September";
            case OCTOBER -> "October";
            case NOVEMBER -> "November";
            case DECEMBER -> "December";
            default -> throw new IllegalStateException("Invalid month: " + month);
        };


        return monthName + " " + this.day + ", " + year;
    }

    /**
     * Returns the day of the week as a String
     * <p>
     * To get the day of the week, does the following seven steps for dates in the 1900s:
     * e.g. October 31, 1977:
     * step 1: calculate the number of twelves in 77: 6
     * step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 = 5
     * step 3: calculate the number of fours in step 2: 5/4 = 1.25, so 1
     * step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 = 43
     * step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 = 44
     * step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
     * step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
     * <p>
     * Extra notes:
     * a) for January/February dates in leap years, add 6 at the start
     * b) for all dates in the 2000s, add 6 at the start
     * c) for all dates in the 1800s, add 2 at the start
     *
     * @return String of the day of the week
     */
    public String getDayOfTheWeek()
    {
        final int initialFormulaValue;

        final int step1result;
        final int step2result;
        final int step3result;
        final int step4result;
        final int step5result;
        final int step6result;

        initialFormulaValue = determineInitialValue();

        step1result = initialFormulaValue / STEP_1_CONSTANT;

        step2result = initialFormulaValue - step1result * STEP_1_CONSTANT;

        step3result = step2result / STEP_3_CONSTANT;

        step4result = this.day + step1result + step2result + step3result;

        step5result =
                step4result + Character.getNumericValue(STEP_5_CONSTANT.charAt(this.month - STEP_5_CONSTANT_OFFSET));

        step6result = step5result % STEP_6_CONSTANT;

        return switch(step6result)
        {
            case SATURDAY -> "saturday";
            case SUNDAY -> "sunday";
            case MONDAY -> "monday";
            case TUESDAY -> "tuesday";
            case WEDNESDAY -> "wednesday";
            case THURSDAY -> "thursday";
            case FRIDAY -> "friday";
            default -> throw new IllegalStateException("Unexpected when finding weekday: " + step6result);
        };
    }

    /**
     * Used to find the initial value at the start of the weekday finding formula
     * @return the initial value of the formula
     */
    private int determineInitialValue()
    {
        final int currentYear;
        final int leapOffset;
        final int offset;

        currentYear = this.year % CENTURY_INTERVAL;
        leapOffset  = (isLeapYear(this.year)) && (this.month == JANUARY || this.month == FEBRUARY) ? LEAP_YEAR_OFFSET : NOTHING;

        offset = switch(this.year - currentYear)
        {
            case NINETEENTH_CENTURY_START -> NINETEENTH_CENTURY_OFFSET;
            case TWENTIETH_CENTURY_START -> TWENTIETH_CENTURY_OFFSET;
            case TWENTY_FIRST_CENTURY_START -> TWENTY_FIRST_CENTURY_OFFSET;
            default -> throw new IllegalStateException("Unsupported century: " + (this.year - currentYear));
        };

        return currentYear + offset + leapOffset;
    }

    /**
     * Compares this date with the specified date to determine their relative order.
     * Errors if the given date is null
     *
     * <p>
     * returns the difference between the calling date's year and the given date's year
     * or the difference in months if they are the same year
     * or the difference in days if they are the same month
     * </p>
     *
     * @param dateToCompare the Date object to compare this date against
     * @return a negative int (if the given date is later than this date), positive int (if the given date is earlier than this date), or zero (if they are the same date)
     */
    public int compareTo(Date dateToCompare)
    {
        if (dateToCompare == null) {
            throw new IllegalArgumentException("dateToCompare is null");
        }

        int comparison;

        comparison = this.year - dateToCompare.getYear();

        if(comparison == NO_DIFFERENCE)
        {
            comparison = this.month - dateToCompare.getMonth();
            if(comparison == NO_DIFFERENCE)
            {
                comparison = this.day - dateToCompare.getDay();
            }
        }

        return comparison;
    }
}

