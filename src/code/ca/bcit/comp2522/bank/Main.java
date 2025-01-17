package ca.bcit.comp2522.bank;

/**
 * Tests the Name, Date, BankClient, and BankAccount classes by instantiating them and using their functions
 * <p>
 * * @author Mohammad Sadeghi
 * * @author Ryan Chu
 * * @version 1.0
 */
class Main
{
    /**
     * Drives the program
     *
     * @param args unused
     */
    public static void main(final String[] args)
    {

        BankClient einstein = new BankClient(new Name("Albert",
                                                      "Einstein"),
                                             new Date(1879,
                                                      3,
                                                      14),
                                             new Date(1955,
                                                      4,
                                                      18),
                                             new Date(1900,
                                                      1,
                                                      1),
                                             "123456");
        BankAccount einsteinAccount = new BankAccount(einstein,
                                                      3141,
                                                      "abc123",
                                                      new Date(1950,
                                                               10,
                                                               14),
                                                      1000);

        System.out.println("Initials: " + einstein.getName().getInitials());
        System.out.println("Full name: " + einstein.getName().getFullName());
        System.out.println("Reversed name: " + einstein.getName().getReverseName());
        System.out.println("Details: " + einstein.getDetails());
        einsteinAccount.withdraw(100);
        System.out.println("\n");

        BankClient mandela = new BankClient(new Name("Nelson",
                                                     "Mandela"),
                                            new Date(1918,
                                                     7,
                                                     18),
                                            new Date(2013,
                                                     12,
                                                     5),
                                            new Date(1994,
                                                     5,
                                                     10),
                                            "123457");
        BankAccount mandelaAccount = new BankAccount(mandela,
                                                     4664,
                                                     "654321",
                                                     null,
                                                     2000);

        System.out.println("Initials: " + mandela.getName().getInitials());
        System.out.println("Full name: " + mandela.getName().getFullName());
        System.out.println("Reversed name: " + mandela.getName().getReverseName());
        System.out.println("Details: " + mandela.getDetails());
        mandelaAccount.withdraw(200);
        System.out.println("\n");

        BankClient kahlo = new BankClient(new Name("Frida",
                                                   "Kalho"),
                                          new Date(1907,
                                                   7,
                                                   6),
                                          new Date(1954,
                                                   7,
                                                   13),
                                          new Date(1940,
                                                   1,
                                                   1),
                                          "123458");
        BankAccount kahloAccount = new BankAccount(kahlo,
                                                   1907,
                                                   "frd123",
                                                   new Date(1954,
                                                            7,
                                                            13),
                                                   500);

        System.out.println("Initials: " + kahlo.getName().getInitials());
        System.out.println("Full name: " + kahlo.getName().getFullName());
        System.out.println("Reversed name: " + kahlo.getName().getReverseName());
        System.out.println("Details: " + kahlo.getDetails());
        kahloAccount.withdraw(50);

        BankClient chan = new BankClient(new Name("Jackie",
                                                  "Chan"),
                                         new Date(1954,
                                                  4,
                                                  7),
                                         null,
                                         new Date(1980,
                                                  10,
                                                  1),
                                         "123459");
        BankAccount jackieAccount = new BankAccount(chan,
                                                    1954,
                                                    "chan789",
                                                    null,
                                                    3000);

        System.out.println("Initials: " + chan.getName().getInitials());
        System.out.println("Full name: " + chan.getName().getFullName());
        System.out.println("Reversed name: " + chan.getName().getReverseName());
        System.out.println("Details: " + chan.getDetails());
        jackieAccount.withdraw(500);
        System.out.println("\n");

    }
}
