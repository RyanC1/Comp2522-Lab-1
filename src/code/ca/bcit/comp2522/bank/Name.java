package ca.bcit.comp2522.bank;

/**
 * Represents a person's full name with first and last names.
 * Includes methods to retrieve full name, initials and reversed name.
 *
 * @author Mohammad Sadeghi
 * @author Ryan Chu
 * @version 1.0
 */
class Name
{
    private static final int MAX_FIRST_LAST_NAME_LEN = 44;
    private static final int FIRST_CHAR_OF_NAME  = 0;
    private static final int SECOND_CHAR_OF_NAME = 1;

    private final String firstName;
    private final String lastName;

    /**
     * Constructs a Name object with provided first and last name.
     * Validates the first name and the last name to make sure they meet the requirements.
     *
     * @param firstName the first name to be set
     * @param lastName  the last name to be set
     */
    public Name(final String firstName,
                final String lastName)
    {
        validateName(firstName);
        validateName(lastName);

        this.firstName = firstName;
        this.lastName  = lastName;
    }

    /**
     * Accessor method for first name.
     *
     * @return first name.
     */
    public String getFirst()
    {
        return firstName;
    }

    /**
     * Accessor method for last name.
     *
     * @return last name.
     */
    public String getLast()
    {
        return lastName;
    }

    /**
     * Validates the given name based on specific criteria:
     * 1. Cannot be null.
     * 2. Cannot be blank.
     * 3. Cannot contain the word "admin".
     * 4. Cannot be longer than MAX_FIRST_LAST_NAME_LEN characters.
     *
     * @param name the name being validated.
     */
    private static void validateName(final String name)
    {

        if(name == null ||
           name.isBlank() ||
           name.contains("admin") ||
           name.length() > MAX_FIRST_LAST_NAME_LEN)

        {
            throw new IllegalArgumentException("Bad name: " + name);
        }
    }

    /**
     * Gets the initials of the full name in the form of "F.L.".
     *
     * @return Full name initials in the form "F.L.".
     */
    public String getInitials()
    {
        final String initials;

        initials = Character.toUpperCase(firstName.charAt(FIRST_CHAR_OF_NAME)) + "." +
                   Character.toUpperCase(lastName.charAt(FIRST_CHAR_OF_NAME)) + ".";
        return initials;
    }

    /**
     * Returns the full name with the first letters of the first and last name's being capitalized
     * and the rest of the letters being lowercased.
     *
     * @return Full name in the form of "First Last".
     */
    public String getFullName()
    {
        final String fullName;

        fullName =
                Character.toUpperCase(firstName.charAt(FIRST_CHAR_OF_NAME)) +
                firstName.substring(SECOND_CHAR_OF_NAME).toLowerCase() + " " +
                Character.toUpperCase(lastName.charAt(FIRST_CHAR_OF_NAME)) +
                lastName.substring(SECOND_CHAR_OF_NAME).toLowerCase();

        return fullName;
    }

    /**
     * Returns The full name in reverse and returns it as a string.
     * Every character will be reversed including spaces.
     *
     * @return Full name reversed in format of "last name first name" with every character reversed.
     */
    public String getReverseName()
    {
        final StringBuilder reversedName;
        final String        str;

        reversedName = new StringBuilder();

        reversedName.append(firstName);
        reversedName.append(" ");
        reversedName.append(lastName);
        reversedName.reverse();

        str = reversedName.toString();

        return str;
    }
}