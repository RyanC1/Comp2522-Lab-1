package ca.bcit.comp2522.bank;

/**
 * Represents a bank client.
 * Has a name, birthdate, optional death date signup date and a client ID.
 * Includes a method to retrieve details of the client and check if they are alive or deceased.
 *
 * @author Mohammad Sadeghi
 * @author Ryan Chu
 * @version 1.0
 */
class BankClient
{
    private static final int CLIENT_ID_MAX_CHARS = 7;
    private static final int CLIENT_ID_MIN_CHARS = 6;
    private static final int SAME_DATE           = 0;

    private final Name   name;
    private final Date   birthDate;
    private final Date   deathDate;
    private final Date   signupDate;
    private final String clientID;


    /**
     * Constructs a BankClient object
     *
     * @param name       Name of the bank client (cannot be null).
     * @param birthDate  birthDate of the bank client (cannot be null).
     * @param deathDate  deathDate of the bank client (must be > birthDate).
     * @param signupDate signupDate of the bank client (must be => birthDate and =< deathDate).
     * @param clientID   unique client ID of the bank client ().
     */
    BankClient(final Name name,
               final Date birthDate,
               final Date deathDate,
               final Date signupDate,
               final String clientID)
    {
        validateName(name);
        validateDate(birthDate);
        validateDate(signupDate);
        validateAccountDateConsistancy(signupDate,
                                       birthDate,
                                       deathDate);
        validateClientId(clientID);

        this.name       = name;
        this.birthDate  = birthDate;
        this.deathDate  = deathDate;
        this.signupDate = signupDate;
        this.clientID   = clientID;
    }

    /**
     * validates the given Name isn't null.
     *
     * @param name the Name to check
     */
    private void validateName(final Name name)
    {
        if(name == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    /**
     * Validates the given Date isn't null.
     *
     * @param date the Date to check.
     */
    private void validateDate(final Date date)
    {
        if(date == null)
        {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }

    /**
     * Checks if the clients deathDate is before the birth or signup date
     * or the signup date is before the birthdate.
     *
     * @param signupDate the date the client signed up
     * @param birthDate  the date the client was born
     * @param deathDate  the date the client died
     */
    private void validateAccountDateConsistancy(final Date signupDate,
                                                final Date birthDate,
                                                final Date deathDate)
    {
        if((deathDate != null && birthDate.compareTo(deathDate) > SAME_DATE) ||
           birthDate.compareTo(signupDate) > SAME_DATE ||
           (deathDate != null && signupDate.compareTo(deathDate) > SAME_DATE))
        {
            throw new IllegalArgumentException("Date inconsistency for bank client");
        }
    }

    /**
     * Validates the clientID is between CLIENT_ID_MAX_CHARS and CLIENT_ID_MIN_CHARS and is not null.
     * @param clientID the ID to check
     */
    private void validateClientId(final String clientID)
    {
        if(clientID == null || clientID.length() > CLIENT_ID_MAX_CHARS || clientID.length() < CLIENT_ID_MIN_CHARS)
        {
            throw new IllegalArgumentException(
                    "Client ID must be between " + CLIENT_ID_MIN_CHARS + " and " + CLIENT_ID_MAX_CHARS);
        }
    }

    /**
     * Returns the name of the client.
     *
     * @return the Name object representing the client's name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the birthdate of the client.
     *
     * @return the Date object representing the client's birthdate
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Returns the death date of the client.
     *
     * @return the Date object representing the client's death date, or null if the client is still alive
     */
    public Date getDeathDate()
    {
        return deathDate;
    }

    /**
     * Returns the unique client ID associated with the client.
     *
     * @return the String representing the client's ID
     */
    public String getClientID()
    {
        return clientID;
    }

    /**
     * Returns the signup date of the client.
     *
     * @return the Date object representing the client's signup date
     */
    public Date getSignupDate()
    {
        return signupDate;
    }

    /**
     * Returns true if the client is alive.
     *
     * @return if the client is alive
     */
    public boolean isAlive()
    {
        return (deathDate == null);
    }

    /**
     * Generates a string containing the client's details the exact format of
     * “Full_Name Client_Id (alive/dead + death date) Full_Join_Date”
     * such as
     * “Tiger Woods client #12345 (alive) joined the bank on Thursday, September 3, 2020”
     *
     * @return A string included the details of the client.
     */
    public String getDetails()
    {
        final StringBuilder details;

        details = new StringBuilder();

        details.append(name.getFullName());
        details.append(" client #");
        details.append(clientID);

        if(this.isAlive())
        {
            details.append(" (alive)");
        }
        else
        {
            details.append(" (died ");
            details.append(deathDate.getDayOfTheWeek());
            details.append(", ");
            details.append(deathDate.getYyyyMmDd());
            details.append(")");
        }
        details.append(" joined the bank on ");
        details.append(signupDate.getDayOfTheWeek());
        details.append(", ");
        details.append(signupDate.getYyyyMmDd());

        return details.toString();
    }
}