package ca.bcit.comp2522.bank;

/**
 * 
 *
 * @author Mohammad Sadeghi
 * @version 1.0
 */
public class Name
{
    private static final int FIRST_LAST_NAME_LEN = 45;

    private final String firstName;
    private final String lastName;

    /**
     * Validates and initializes first name and last name.
     * @param firstName the first name
     * @param lastName the last name
     * @throws IllegalArgumentException if first name and last name are not valid
     */
    public Name(final String firstName, final String lastName)
    {
        validateFirstName(firstName);
        validateLastName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Accessor method for first name.
     * @return first name.
     */
    public String getFirst()
    {
        return firstName;
    }

    /**
     * Accessor method for last name.
     * @return last name.
     */
    public String getLast()
    {
        return lastName;
    }

    /*
     * Validates the first name to make sure it's not null,
     * is not blank, is not 'admin' and that it is in the range
     * of maximum allowed number of characters.
     */
    private static void validateFirstName(final String firstName)
    {
        //Checks if first name is admin, if so throws an exception
        if(firstName != null && firstName.equalsIgnoreCase("admin"))
        {
            throw new IllegalArgumentException("bad last name: " + firstName);
        }

        //Checks if first name null, blank or is longer than max number of characters allowed
        if(firstName == null || firstName.isBlank() || firstName.length() < FIRST_LAST_NAME_LEN)
        {
            throw new IllegalArgumentException("bad first name: " + firstName);
        }
    }

    /*
     * Validates the last name to make sure it's not null,
     * is not blank, is not 'admin' and that it is in the range
     * of maximum allowed number of characters.
     */
    private static void validateLastName(final String lastName)
    {
        //Checks if last name is admin, if so throws an exception
        if(lastName != null && lastName.equalsIgnoreCase("admin"))
        {
            throw new IllegalArgumentException("bad last name: " + lastName);
        }

        //Checks if last name null, blank or is longer than max number of characters allowed
        if(lastName == null || lastName.isBlank() || lastName.length() < FIRST_LAST_NAME_LEN)
        {
            throw new IllegalArgumentException("bad last name: " + lastName);
        }
    }

    /**
     * Returns the initials of the full name.
     * @return Full name initials.
     */
    public String getInitials()
    {
        return Character.toUpperCase(firstName.charAt(0)) + "." + Character.toUpperCase(lastName.charAt(0)) + ".";
    }

    /**
     * Returns the full name.
     * @return Full name.
     */
    public String getFullName()
    {
        return Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1).toLowerCase() + " " +
                Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1).toLowerCase();
    }

    /**
     * Returns The full name in reverse.
     * @return Full name reversed.
     */
    public String getReverseName()
    {
        final String fullName;

        fullName = getFullName();
        StringBuilder reversedName = new StringBuilder();

        for(int i = fullName.length() - 1; i >= 0; i--)
        {
            reversedName.append((fullName).charAt(i));
        }

        return reversedName.toString();
    }
}