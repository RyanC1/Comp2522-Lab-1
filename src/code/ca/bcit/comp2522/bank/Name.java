package ca.bcit.comp2522.bank;

/**
 * 
 *
 * @author Mohammad Sadeghi
 * @version 1.0
 */
public class Name
{
    private static final int MAX_FIRST_AND_LAST_CHARS = 45;

    private final String firstName;
    private final String lastName;

    /**
     * Validates and initializes first name and last name.
     * @param firstName the first name
     * @param lastName the last name
     */
    public Name(final String firstName, final String lastName)
    {
        validateFirstName(firstName);
        validateLastName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirst()
    {
        return firstName;
    }

    public String getLast()
    {
        return lastName;
    }

    private static void validateFirstName(final String firstName)
    {
        if(firstName != null && firstName.equalsIgnoreCase("admin"))
        {
            throw new IllegalArgumentException("bad last name: " + firstName);
        }

        if(firstName == null || firstName.isBlank() || firstName.length() < MAX_FIRST_AND_LAST_CHARS)
        {
            throw new IllegalArgumentException("bad first name: " + firstName);
        }
    }

    private static void validateLastName(final String lastName)
    {
        if(lastName != null && lastName.equalsIgnoreCase("admin"))
        {
            throw new IllegalArgumentException("bad last name: " + lastName);
        }

        if(lastName == null || lastName.isBlank() || lastName.length() < MAX_FIRST_AND_LAST_CHARS)
        {
            throw new IllegalArgumentException("bad last name: " + lastName);
        }
    }

    public String getInitials()
    {
        return Character.toUpperCase(firstName.charAt(0)) + "." + Character.toUpperCase(lastName.charAt(0)) + ".";
    }

    public String getFullName()
    {
        return Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1).toLowerCase() + " " +
                Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1).toLowerCase();
    }

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
