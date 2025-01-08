package Assignment_1;

public class Accounts {

    //Attributes of the Accounts class

    private String accountNumber;       /* Unique account number */
    private String accountType;         /* chequing, saving, credit */
    private String firstName;           /* First name of account holder */
    private String lastName;            /* Last name of account holder */
    private double balance;             /* Account balance */

    //Constructor of the Account class

    Accounts(){
        accountNumber = " ";
        accountType = " ";
        firstName = " ";
        lastName = " ";
        balance = 0.0;
    }
    //Getter and Setter method for the accountNumber
    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setAccountNumber(String accNumber)
    {
        accountNumber = accNumber;
    }

    //Getter and Setter for the accountType
    public String getAccountType()
    {
        return accountType;
    }
    public void setAccountType(String accType)
    {
        accountType = accType;
    }

    //Getter and Setter method for the firstName
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String fName)
    {
        firstName = fName;
    }

    //Getter method for the lastName
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lName)
    {
        lastName = lName;
    }

    //Getter and setter method for the balance
    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double bal)
    {
        balance = bal;
    }

    //toString method for the Account class
    public String toString()
    {
        return ("\n Account number " + getAccountNumber() + "Account type " + getAccountType() + "First name " + getFirstName() + "Last Name " + getLastName() + "Balance " + getBalance());
    }
}
