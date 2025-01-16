package ca.bcit.comp2522.bank;

/**
 *
 *
 * @author Mohammad Sadeghi
 * @version 1.0
 */
public class BankClient
{

    private final Name name;
    private final Date birthDate;
    private final Date deathDate ;
    private final Date signupDate;
    private final String clientID;


    /**
     * Constructs a BankClient object with the given name, birthdate,
     * deathDate, clientID, and signup date.
     *
     * @param name Name of the bank client.
     * @param birthDate birthDate of the bank client.
     * @param deathDate deathDate of the bank client.
     * @param signupDate signupDate of the bank client.
     * @param clientID unique client ID of the bank client.
     */
    BankClient(final Name name,
               final Date birthDate,
               final Date deathDate,
               final Date signupDate,
               final String clientID )
    {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.signupDate = signupDate;
        this.clientID = clientID;
    }

    /**
     *
     *
     * @return A string included the details of the client.
     */
    public String getDetails()
    {
        final StringBuilder details;
        final String str;

        details = new StringBuilder();

        if(deathDate == null)
        {
            details.append(name.getFullName());
            details.append(" client #");
            details.append(clientID);
            details.append(" (alive) joined the bank on ");
            details.append(signupDate.getDatOfTheWeek());
            details.append(", ");
            details.append(signupDate.getYyyyMmDd());
            details.append("and was born on ");
            details.append(birthDate.getDatOfTheWeek());
            details.append(", ");
            details.append(birthDate.getYyyyMmDd());
        }
        else
        {
            details.append(name.getFullName());
            details.append(" client #");
            details.append(clientID);
            details.append(" (not alive) joined the bank on ");
            details.append(signupDate.getDatOfTheWeek());
            details.append(", ");
            details.append(signupDate.getYyyyMmDd());
            details.append("and was born on ");
            details.append(birthDate.getDatOfTheWeek());
            details.append(", ");
            details.append(birthDate.getYyyyMmDd());
            details.append(" (died ");
            details.append(deathDate.getDatOfTheWeek());
            details.append(", ");
            details.append(deathDate.getYyyyMmDd());
            details.append(")");
        }

        str = details.toString();
        return str;
    }
}