package ca.bcit.comp2522.bank;


/**
 * Represents a bank account.
 * Keeps track of the owner, their pin, the account number, and the date opened/closed.
 * Has the functionality to deposit or withdraw (w or w/o pin verification) money in usd.
 *
 * @author Mohammad Sadeghi
 * @author Ryan Chu
 * @version 1.0
 */
class BankAccount
{
    private static final int NOTHING                   = 0;
    private static final int ACCOUNT_NUMBER_MIN_LENGTH = 6;
    private static final int ACCOUNT_NUMBER_MAX_LENGTH = 7;
    private static final int SAME_DATE                 = 0;


    private final BankClient client;
    private final int        pin;
    private final String     accountNumber;
    private final Date       accountOpened;
    private final Date       accountClosed;

    private double balanceUsd;

    /**
     * Constructs a BankAccount.
     *
     * @param client        the bank accounts owner
     * @param pin           the PIN of the account
     * @param accountNumber the unique account number
     * @param accountOpened when the account was opened
     * @param accountClosed when the account was closed
     * @param balanceUsd    the current balance of the account in USD
     */
    public BankAccount(final BankClient client,
                       final int pin,
                       final String accountNumber,
                       final Date accountOpened,
                       final Date accountClosed,
                       final double balanceUsd)
    {

        validateClient(client);
        validatePin(pin);
        validateAccountNumber(accountNumber);
        validateDate(accountOpened);
        validateClientDateConsistency(client,
                                      accountOpened,
                                      accountClosed);
        validateBalanceUsd(balanceUsd);

        this.client = client;
        this.pin    = pin;
        //String length 6 or 7
        this.accountNumber = accountNumber;
        this.accountOpened = accountOpened;
        this.accountClosed = accountClosed;
        this.balanceUsd    = balanceUsd;
    }

    /**
     * Constructs a BankAccount, using the clients signup date as the account creation date.
     *
     * @param client        the bank accounts owner
     * @param pin           the PIN of the account
     * @param accountNumber the unique account number
     * @param accountClosed when the account was closed
     * @param balanceUsd    the current balance of the account in USD
     */
    public BankAccount(final BankClient client,
                       final int pin,
                       final String accountNumber,
                       final Date accountClosed,
                       final double balanceUsd)
    {
        this(client, pin, accountNumber, client.getSignupDate(), accountClosed, balanceUsd);
    }

    /**
     * Validates the given Client is not null.
     *
     * @param client the Client to check
     */
    private void validateClient(final BankClient client)
    {
        if(client == null)
        {
            throw new NullPointerException("Bank client is null");
        }
    }

    /**
     * Validates if the given pin is greater than NOTHING
     * @param pin the pin to check
     */
    private void validatePin(final int pin)
    {
        if(pin < NOTHING)
        {
            throw new IllegalArgumentException("Pin number out of range");
        }
    }

    /**
     * Validates the account number is between ACCOUNT_NUMBER_MAX_LENGTH and ACCOUNT_NUMBER_MIN_LENGTH and not null.
     *
     * @param accountNumber the account number to check
     */
    private void validateAccountNumber(final String accountNumber)
    {
        if(accountNumber == null || accountNumber.length() < ACCOUNT_NUMBER_MIN_LENGTH ||
           accountNumber.length() > ACCOUNT_NUMBER_MAX_LENGTH)
        {
            throw new IllegalArgumentException(
                    "Invalid account number length, must be between " + ACCOUNT_NUMBER_MIN_LENGTH + " and " +
                    ACCOUNT_NUMBER_MAX_LENGTH);
        }
    }

    /**
     * Checks if the given Date is null.
     *
     * @param date the Date to check
     */
    private void validateDate(final Date date)
    {
        if(date == null)
        {
            throw new NullPointerException("Given date is null");
        }
    }

    /**
     * Validates the given balance in USD is over $0.
     *
     * @param number the balance in USD to check
     */
    private void validateBalanceUsd(final double number)
    {
        if(number <= NOTHING)
        {
            throw new IllegalArgumentException("Balance given must be positive, got: " + number);
        }
    }

    /**
     * Checks:
     * 1: if the accounts creation date is after is closure date
     * 2: if the accounts creation date is before the signup date
     * 3: if the accounts creation date is after the clients death date
     *
     * @param client        the client to check
     * @param accountOpened the Date the account opened
     * @param accountClosed the Date the account closed (can be null)
     */
    private void validateClientDateConsistency(final BankClient client,
                                               final Date accountOpened,
                                               final Date accountClosed)
    {
        if((accountNumber != null && accountOpened.compareTo(accountClosed) > SAME_DATE) ||
           accountOpened.compareTo(client.getSignupDate()) < SAME_DATE ||
           (!client.isAlive() && accountOpened.compareTo(client.getDeathDate()) > SAME_DATE))
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Retrieves the client associated with the bank account.
     *
     * @return the BankClient object representing the client associated with the account.
     */
    public BankClient getClient()
    {
        return client;
    }

    /**
     * Retrieves the PIN code associated with the account.
     *
     * @return the PIN code for the account.
     */
    public int getPin()
    {
        return pin;
    }

    /**
     * Retrieves the account number.
     *
     * @return the account number as a String.
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Retrieves the date when the account was opened.
     *
     * @return the Date when the account was opened.
     */
    public Date getAccountOpened()
    {
        return accountOpened;
    }

    /**
     * Retrieves the date when the account was closed.
     * If the account is still open, this value may be null.
     *
     * @return the Date when the account was closed, or null if the account is still open.
     */
    public Date getAccountClosed()
    {
        return accountClosed;
    }

    /**
     * Retrieves the balance of the account in USD.
     *
     * @return the current balance in USD.
     */
    public double getBalanceUsd()
    {
        return balanceUsd;
    }


    /**
     * Adds the specified value to this.balanceUSD.
     *
     * @param amountUsd the value to add (greater than NOTHING)
     */
    public void deposit(final double amountUsd)
    {
        validateBalanceUsd(amountUsd);
        this.balanceUsd += amountUsd;
    }

    /**
     * Removes the specified value to this.balanceUSD.
     * Fails if value would put this.balanceUSD negative.
     *
     * @param amountUsd the value to remove (greater than NOTHING)
     * @return the value subtracted (if successful)
     */
    public double withdraw(final double amountUsd)
    {
        validateBalanceUsd(amountUsd);
        if (this.balanceUsd >= amountUsd) {
            this.balanceUsd -= amountUsd;
            return amountUsd;
        }
        else
        {
            System.out.println("transaction failed");
            return NOTHING;
        }
    }

    /**
     * Removes the specified value to this.balanceUSD if the given pin matches the pin of the account.
     * Fails if value would put this.balanceUSD negative or the pin is inaccurate.
     *
     * @param amountUsd the value to remove (greater than NOTHING)
     * @param pinToMatch the pin to match
     * @return the value subtracted (if successful)
     */
    public double withdraw(final double amountUsd,
                           final int pinToMatch)
    {
        validateBalanceUsd(amountUsd);

        if(pinToMatch == this.pin && this.balanceUsd >= amountUsd)
        {
            this.balanceUsd -= amountUsd;
            return amountUsd;
        }
        else
        {
            System.out.println("Invalid pin");
            return NOTHING;
        }

    }

    /**
     * Generates a string containing the accounts details the exact format of
     * “Full_Name Balance_USD Account_Number Date_Opened (Date_Closed if applicable)"
     * such as
     * “Albert Einstein had $900 USD in account #abc123 which he opened on Monday, January 1, 1900 and closed Saturday, October 14, 1950."
     *
     * @return A string included the details of the account.
     */
    public String getDetails()
    {
        final StringBuilder details;

        details = new StringBuilder();

        details.append(this.client.getName().getFullName());
        details.append(" had $");
        details.append(this.balanceUsd);
        details.append(" USD in account #");
        details.append(this.accountNumber);
        details.append(" which he opened on ");
        details.append(this.accountOpened.getDayOfTheWeek());
        details.append(", ");
        details.append(this.accountOpened.getYyyyMmDd());
        if (this.accountClosed != null) {
            details.append(" and closed ");
            details.append(this.accountClosed.getDayOfTheWeek());
            details.append(", ");
            details.append(this.accountClosed.getYyyyMmDd());
        }
        details.append(".");

        return details.toString();
    }
}
