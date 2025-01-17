package ca.bcit.comp2522.bank;


/**
 * Represents a bank account.
 * Keeps track of the owner, their pin, the account number, and the date opened/closed.
 * Has the functionality to deposit or withdraw (w or w/o pin verification) money in usd.
 *
 * @Author Ryan Chu
 * @Version 2024
 */
class BankAccount
{
    private static int NOTHING                   = 0;
    private static int ACCOUNT_NUMBER_MIN_LENGTH = 6;
    private static int ACCOUNT_NUMBER_MAX_LENGTH = 7;


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
        validateDate(accountClosed);
        validateClientDateConsistency(client,
                                      accountOpened,
                                      accountClosed);
        validateBalanceUsd(balanceUsd);

        this.client = new BankClient();
        this.pin    = 0;
        //String length 6 or 7
        this.accountNumber = "";
        this.accountOpened = null;
        this.accountClosed = null;
        this.balanceUsd    = 0;
    }

    private void validateClient(final BankClient client)
    {
        if(client == null)
        {
            throw new NullPointerException("Bank client is null");
        }
    }

    private void validatePin(final int pin)
    {
        if(pin < 0)
        {
            throw new IllegalArgumentException("Pin number out of range");
        }
    }

    private void validateAccountNumber(final String accountNumber)
    {
        if(accountNumber == null || accountNumber.length() < ACCOUNT_NUMBER_MIN_LENGTH ||
           accountNumber.length() > ACCOUNT_NUMBER_MAX_LENGTH)
        {
            throw new IllegalArgumentException(
                    "Invalid account number length, must be between " + ACCOUNT_NUMBER_MIN_LENGTH + " and " +
                    ACCOUNT_NUMBER_MAX_LENGTH + " got: " + accountNumber.length());
        }
    }

    private void validateDate(final Date date)
    {
        if(date == null)
        {
            throw new NullPointerException("Given date is null");
        }
    }

    private void validateBalanceUsd(final double number)
    {
        if(number <= NOTHING)
        {
            throw new IllegalArgumentException("Balance given must be positive, got: " + number);
        }
    }

    private void validateClientDateConsistency(final BankClient client,
                                               final Date accountOpened,
                                               final Date accountClosed)
    {

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
     * @param amountUsd
     */
    public void deposit(final double amountUsd)
    {
        validateBalanceUsd(amountUsd);
        this.balanceUsd += amountUsd;
    }


    public double withdraw(final double amountUsd)
    {
        validateBalanceUsd(amountUsd);
        this.balanceUsd -= amountUsd;
        return amountUsd;
    }


    public double withdraw(final double amountUsd,
                           final int pinToMatch)
    {
        validateBalanceUsd(amountUsd);

        if(pinToMatch == this.pin)
        {
            this.balanceUsd -= amountUsd;
        }
        else
        {
            System.out.println("Invalid pin");
        }
        return amountUsd;
    }


    public void getDetails()
    {

    }
}
