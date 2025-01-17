package ca.bcit.comp2522.bank;


/**
 * @author Ryan Chu
 * @version 1.0
 */
class Date
{
    //first of month/last of month
    private final static int MIN_YEAR  = 1800;
    private final static int MAX_YEAR  = 2024;
    private final static int MIN_MONTH = 1;
    private final static int MAX_MONTH = 12;
    private final static int MIN_DAY   = 1;

    //short month long month max
    private final static int MAX_DAY_LONG_MONTH  = 31;
    private final static int MAX_DAY_SHORT_MONTH = 30;
    private final static int MAX_LEAP_FEB_DAY    = 29;
    private final static int MAX_NO_LEAP_FEB_DAY = 28;

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

    //year per leap/per century/years between leap centuroes
    private final static int LEAP_YEAR_CYCLE           = 4;
    private final static int LEAP_YEAR_CYCLE_EXCEPTION = 100;
    private final static int LEAP_YEAR_VALID_CHECK     = 0;


    private final static int NINETEENTH_CENTURY   = 1800;
    private final static int TWENTIETH_CENTURY    = 1900;
    private final static int TWENTY_FIRST_CENTURY = 2000;
    private final static int SATURDAY             = 0;
    private final static int SUNDAY               = 1;
    private final static int MONDAY               = 2;
    private final static int TUESDAY              = 3;
    private final static int WEDNESDAY            = 4;
    private final static int THURSDAY             = 5;
    private final static int FRIDAY               = 6;

    private final static int NO_DIFFERENCE       = 0;


    private final int year;
    private final int month;
    private final int day;


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

    private static void validateInt(final int number,
                                    final int min,
                                    final int max)
    {
        if(number < min || number > max)
        {
            StringBuilder intRangeErrorMessage;

            intRangeErrorMessage = new StringBuilder();

            intRangeErrorMessage.append(number);
            intRangeErrorMessage.append(" out of range of ");
            intRangeErrorMessage.append(min);
            intRangeErrorMessage.append('-');
            intRangeErrorMessage.append(max);

            throw new IllegalStateException(intRangeErrorMessage.toString());
        }
    }

    private static void validateDay(final int day,
                                    final int month,
                                    final int year)
    {

        switch(month)
        {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                validateInt(day,
                            MIN_DAY,
                            MAX_DAY_LONG_MONTH);
                break;

            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                validateInt(day,
                            MIN_DAY,
                            MAX_DAY_SHORT_MONTH);
                break;

            case FEBRUARY:
                if(isLeapYear(month,
                              year))
                {
                    validateInt(day,
                                MIN_DAY,
                                MAX_LEAP_FEB_DAY);
                }
                else
                {
                    validateInt(day,
                                MIN_DAY,
                                MAX_NO_LEAP_FEB_DAY);
                }

            default:
                StringBuilder monthRangeErrorMessage;
                monthRangeErrorMessage = new StringBuilder();

                monthRangeErrorMessage.append(month);
                monthRangeErrorMessage.append(" out of range of ");
                monthRangeErrorMessage.append(MIN_MONTH);
                monthRangeErrorMessage.append('-');
                monthRangeErrorMessage.append(MAX_MONTH);

                throw new IllegalStateException(monthRangeErrorMessage.toString());
        }


    }

    private static boolean isLeapYear(final int month,
                                      final int year)
    {
        return (year % LEAP_YEAR_CYCLE == LEAP_YEAR_VALID_CHECK &&
                year % LEAP_YEAR_CYCLE_EXCEPTION != LEAP_YEAR_VALID_CHECK) ||
               (year % (LEAP_YEAR_CYCLE * LEAP_YEAR_CYCLE_EXCEPTION) == LEAP_YEAR_VALID_CHECK);
    }

    /**
     * Returns the day associated with the date object.
     *
     * @return int representing the day (1-31)
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     * Returns the month associated with the date object.
     *
     * @return int representing the month (1-12)
     */
    public int getMonth()
    {
        return this.month;
    }

    /**
     * Returns the year associated with the date object.
     *
     * @return int representing the year (1800-2024)
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Returns the Date associcated with the date object in YYYY-MM-DD format
     *
     * @return String the YYYY-MM-DD formated Date
     */
    public String getYyyyMmDd()
    {
        StringBuilder yyyyMmDd = new StringBuilder();
        yyyyMmDd.append(this.year);
        yyyyMmDd.append("-");
        yyyyMmDd.append(this.month);
        yyyyMmDd.append("-");
        yyyyMmDd.append(this.day);
        return yyyyMmDd.toString();
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
    public String getDatOfTheWeek()
    {
        int initialFormulaValue;

        final int    step1result;
        final int    step2result;
        final int    step3result;
        final int    step4result;
        final int    step5result;
        final int    step6result;
        final String step7result;

        final int    step1Constant;
        final int    step3Constant;
        final String step5Constant;
        final int    step5Offset;
        final int    step6Constant;
        final int    nineteenthCenturyOffset;
        final int    twentiethCenturyOffset;
        final int    twentyFirstCenturyOffset;
        final int    leapYearOffset;

        step1Constant            = 12;
        step3Constant            = 4;
        step5Constant            = "144025036146";
        step5Offset              = 1;
        step6Constant            = 7;
        nineteenthCenturyOffset  = 2;
        twentiethCenturyOffset   = 0;
        twentyFirstCenturyOffset = 6;
        leapYearOffset           = 6;

        if(year >= NINETEENTH_CENTURY && year < TWENTIETH_CENTURY)
        {
            initialFormulaValue = nineteenthCenturyOffset + this.year - NINETEENTH_CENTURY;
        }
        else if(year >= TWENTIETH_CENTURY && year < TWENTY_FIRST_CENTURY)
        {
            initialFormulaValue = twentiethCenturyOffset + this.year - TWENTIETH_CENTURY;
        }
        else if(year >= TWENTY_FIRST_CENTURY && year < MAX_YEAR)
        {
            initialFormulaValue = twentyFirstCenturyOffset + this.year - TWENTY_FIRST_CENTURY;
        }
        else
        {
            throw new IllegalStateException("Invalid year: " + year);
        }

        if((this.month == JANUARY || this.month == FEBRUARY) && isLeapYear(this.month,
                                                                           this.year))
        {
            initialFormulaValue += leapYearOffset;
        }


        step1result = initialFormulaValue / step1Constant;
        step2result = initialFormulaValue - step1result;
        step3result = step2result % step3Constant;
        step4result = this.day + step1result + step2result + step3result;
        step5result = step4result + Character.getNumericValue(step5Constant.charAt(this.month - step5Offset));
        step6result = step5result % step6Constant;

        switch(step6result)
        {
            case SATURDAY:
                step7result = "Saturday";
                break;
            case SUNDAY:
                step7result = "Sunday";
                break;
            case MONDAY:
                step7result = "Monday";
                break;
            case TUESDAY:
                step7result = "Tuesday";
                break;
            case WEDNESDAY:
                step7result = "Wednesday";
                break;
            case THURSDAY:
                step7result = "Thursday";
                break;
            case FRIDAY:
                step7result = "Friday";
                break;
            default:
                StringBuilder weekdayFormatErrorMessage = new StringBuilder();
                weekdayFormatErrorMessage.append("Unexpected value of ");
                weekdayFormatErrorMessage.append(step6result);
                weekdayFormatErrorMessage.append(". Expected 0-6");
                throw new IllegalStateException(weekdayFormatErrorMessage.toString());
        }

        return step7result;
    }

    /**
     * Compares this date with the specified date to determine their relative order.
     *
     * <p>
     * returns the difference of the calling date's year with the given date's year
     * or the difference in months if they are the same year
     * or the difference in days if they are the same month
     * </p>
     *
     * @param dateToCompare the Date object to compare this date against
     * @return a negative int, positive int, or zero
     */
    public int compareTo(Date dateToCompare)
    {
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

