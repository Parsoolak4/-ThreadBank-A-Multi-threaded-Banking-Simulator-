package Assignment_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends Thread {

    //Attributes of the Client class
    private static int numberOfTransactions;   		/* Number of transactions to process */
    private static int maxNbTransactions;      		/* Maximum number of transactions */
    private static Transactions [] transaction; 	/* Transactions to be processed */
    private static Network objNetwork;          	/* Client object to handle network operations */
    private String clientOperation;    				/* sending or receiving */

    //Constructor of the Client class
    Client(String operation)
    {
        if (operation.equals("sending"))
        {
            System.out.println("\n Initializing client sending application ...");
            numberOfTransactions = 0;
            maxNbTransactions = 100;
            transaction = new Transactions[maxNbTransactions];
            objNetwork = new Network("client");
            clientOperation = operation;
            System.out.println("\n Initializing the transactions ... ");
            readTransactions();
            System.out.println("\n Connecting client to network ...");
            String cip = objNetwork.getClientIP();
            if (!(objNetwork.connect(cip)))
            {   System.out.println("\n Terminating client application, network unavailable");
                System.exit(0);
            }
        }
        else
        if (operation.equals("receiving"))
        {
            System.out.println("\n Initializing client receiving application ...");
            clientOperation = operation;
        }
    }
    //Getter and setter for the numberOfTransactions
    public int getNumberOfTransactions()
    {
        return numberOfTransactions;
    }
    public void setNumberOfTransactions(int nbOfTrans)
    {
        numberOfTransactions = nbOfTrans;
    }

    //Getter and setter for the clientOperation
    public String getClientOperation()
    {
        return clientOperation;
    }
    public void setClientOperation(String operation)
    {
        clientOperation = operation;
    }

    //Reading of the transactions from an input file
    public void readTransactions()
    {
        Scanner inputStream = null;     /* Transactions input file stream */
        int i = 0;                      /* Index of transactions array */

        try
        {
            inputStream = new Scanner(new FileInputStream("/Users/pooya/eclipse-workspace/COMP346_Assignments/src/Assignment_1/transaction.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File transaction.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }
        while (inputStream.hasNextLine( ))
        {
            try
            {   transaction[i] = new Transactions();
                transaction[i].setAccountNumber(inputStream.next());            /* Read account number */
                transaction[i].setOperationType(inputStream.next());            /* Read transaction type */
                transaction[i].setTransactionAmount(inputStream.nextDouble());  /* Read transaction amount */
                transaction[i].setTransactionStatus("pending");                 /* Set current transaction status */
                i++;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Line " + i + "file transactions.txt invalid input");
                System.exit(0);
            }

        }
        setNumberOfTransactions(i);		/* Record the number of transactions processed */

        // System.out.println("\n DEBUG : Client.readTransactions() - " + getNumberOfTransactions() + " transactions processed");

        inputStream.close( );

    }
    //Sending the transactions to the server
    public void sendTransactions()
    {
        int i = 0;     /* index of transaction array */

        while (i < getNumberOfTransactions())
        {
            while( objNetwork.getInBufferStatus().equals("full") )
            {
                Thread.yield();
                /* Alternatively, busy-wait until the network input buffer is available */
            }

            transaction[i].setTransactionStatus("sent");   /* Set current transaction status */

            // System.out.println("\n DEBUG : Client.sendTransactions() - sending transaction on account " + transaction[i].getAccountNumber());

            objNetwork.send(transaction[i]);                            /* Transmit current transaction */
            i++;
        }

    }
    //Receiving the completed transactions from the server
    public void receiveTransactions(Transactions transact)
    {
        int i = 0;     /* Index of transaction array */

        while (i < getNumberOfTransactions())
        {
            while( objNetwork.getOutBufferStatus().equals("empty"))
            {
                Thread.yield();
            }  	/* Alternatively, busy-wait until the network output buffer is available */


            objNetwork.receive(transact);                               	/* Receive updated transaction from the network buffer */

            //   System.out.println("\n DEBUG : Client.receiveTransactions() - receiving updated transaction on account " + transact.getAccountNumber());

            System.out.println(transact);                               	/* Display updated transaction */
            i++;
        }
    }
    //toString method for the Client class
    public String toString()
    {
        return ("\n client IP " + objNetwork.getClientIP() + " Connection status" + objNetwork.getClientConnectionStatus() + "Number of transactions " + getNumberOfTransactions());
    }
    //Code for the run method
    public void run()
    {
        Transactions transact = new Transactions();
        long sendClientStartTime, sendClientEndTime, receiveClientStartTime, receiveClientEndTime;



        if(getClientOperation().equals("sending")) {

            // System.out.println("\n DEBUG : Client.run() - starting client sending thread connected");

            sendClientStartTime = System.currentTimeMillis();
            sendTransactions();
            sendClientEndTime = System.currentTimeMillis();
            System.out.println("\n Terminating client sending thread -  running time: "+(sendClientEndTime - sendClientStartTime) );


        } else if(getClientOperation().equals("receiving")) {

            //   System.out.println("\n DEBUG : Client.run() - starting client receiving thread connected");


            receiveClientStartTime = System.currentTimeMillis();
            receiveTransactions(transact);
            receiveClientEndTime = System.currentTimeMillis();
            System.out.println("\n Terminating client receiving thread -  running time: "+(receiveClientEndTime - receiveClientStartTime) );


            objNetwork.disconnect(objNetwork.getClientIP());
        }
    }
}
