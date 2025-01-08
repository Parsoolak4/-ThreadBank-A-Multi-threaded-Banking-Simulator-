package Assignment_1;

public class Transactions {

    //Attributes of the Transactions class
    private String accountNumber;       /* Account number */
    private String operationType;       /* Operation type : deposit, withdrawal, query */
    private double transactionAmount;   /* Amount to deposit or withdraw */
    private double transactionBalance;  /* Account balance after transaction */
    private String transactionError;    /* Transaction error : NSF, invalid amount, invalid account, none */
    private String transactionStatus;   /* Current transaction status : pending, sent, received, transferred, done */

    //Constructor of the Transactions class

    Transactions()
    {
        accountNumber = " ";
        operationType = " ";
        transactionAmount = 0.00;
        transactionBalance = 0.00;
        transactionError = "none";
        transactionStatus = " ";
    }

    //Getter and setter methods for the accountNumber
    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setAccountNumber(String accNumber)
    {
        accountNumber = accNumber;
    }

    //Getter and setter method for the operationType
    public String getOperationType()
    {
        return operationType;
    }
    public void setOperationType(String opType)
    {
        operationType = opType;
    }

    //Getter and setter methods for the transactionAmount
    public double getTransactionAmount()
    {
        return transactionAmount;
    }
    public void setTransactionAmount(double transAmount)
    {
        transactionAmount = transAmount;
    }

    //Getter and setter for the transactionBalance
    public double getTransactionBalance()
    {
        return transactionBalance;
    }
    public void setTransactionBalance(double transBalance)
    {
        transactionBalance = transBalance;
    }

    //Getter and setter for the transactionError
    public String getTransactionError()
    {
        return transactionError;
    }
    public void setTransactionError(String transError)
    {
        transactionError = transError;
    }

    //Getter and setter for the transactionStatus
    public String getTransactionStatus()
    {
        return transactionStatus;
    }
    public void setTransactionStatus(String transStatus)
    {
        transactionError = transStatus;
    }

    //toString method for the Transaction class
    public String toString()
    {
        return ("\n Account number " + getAccountNumber() + " Account Balance " + getTransactionBalance() + " Message " + getTransactionError());
    }
}
