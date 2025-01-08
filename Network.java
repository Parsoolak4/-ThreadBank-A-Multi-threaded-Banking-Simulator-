package Assignment_1;

public class Network extends Thread {

    //Attributes of the Network class
    private static int maxNbPackets;                           /* Maximum number of simultaneous transactions handled by the network buffer */
    private static int inputIndexClient, inputIndexServer, outputIndexServer, outputIndexClient;   /* Network buffer indices for accessing the input buffer (inputIndexClient, outputIndexServer) and output buffer (inputIndexServer, outputIndexClient) */
    private static String clientIP;                            /* IP number of the client application*/
    private static String serverIP;                            /* IP number of the server application */
    private static int portID;                                 /* Port ID of the client application */
    private static String clientConnectionStatus;              /* Client connection status - connected, disconnected, idle */
    private static String serverConnectionStatus;              /* Server connection status - connected, disconnected, idle */
    private static Transactions inComingPacket[];              /* Incoming network buffer */
    private static Transactions outGoingPacket[];              /* Outgoing network buffer */
    private static String inBufferStatus, outBufferStatus;     /* Current status of the network buffers - normal, full, empty */
    private static String networkStatus;                       /* Network status - active, inactive */

    //Constructor of the Network class
    Network(String context)
    {
        int i;

        /* Initialization of the network components */
        if (context.equals("network"))
        {
            System.out.println("\n Activating the network ...");
            clientIP = "192.168.2.0";
            serverIP = "216.120.40.10";
            clientConnectionStatus = "idle";
            serverConnectionStatus = "idle";
            portID = 0;
            maxNbPackets = 10;
            inComingPacket = new Transactions[maxNbPackets];
            outGoingPacket = new Transactions[maxNbPackets];
            for (i=0; i < maxNbPackets; i++)
            {   inComingPacket[i] = new Transactions();
                outGoingPacket[i] = new Transactions();
            }
            inBufferStatus = "empty";
            outBufferStatus = "empty";
            inputIndexClient = 0;
            inputIndexServer = 0;
            outputIndexServer = 0;
            outputIndexClient = 0;
            networkStatus = "active";
        }
        else /* Activate network components for client or server */
            System.out.println("\n Activating network components for " + context + "...");
    }

    //Getter and setter methods for the clientIP
    public String getClientIP()
    {
        return clientIP;
    }
    public void setClientIP(String cip)
    {
        clientIP = cip;
    }

    //Getter and setter for the serverIP
    public String getServerIP()
    {
        return serverIP;
    }
    public void setServerIP(String sip)
    {
        serverIP = sip;
    }

    //Getter and setter methods for the clientConnectionStatus
    public String getClientConnectionStatus()
    {
        return clientConnectionStatus;
    }
    public void setClientConnectionStatus(String connectStatus)
    {
        clientConnectionStatus = connectStatus;
    }

    //Getter and setter methods for the serverConnectionStatus
    public String getServerConnectionStatus()
    {
        return serverConnectionStatus;
    }
    public void setServerConnectionStatus(String connectStatus)
    {
        serverConnectionStatus = connectStatus;
    }

    //Getter and setter methods for the portID
    public int getPortID()
    {
        return portID;
    }
    public void setPortID(int pid)
    {
        portID = pid;
    }

    //Getter and setter for the inBufferStatus
    public String getInBufferStatus()
    {
        return inBufferStatus;
    }
    public void setInBufferStatus(String inBufStatus)
    {
        inBufferStatus = inBufStatus;
    }

    //Getter and setter for the outBufferStatus
    public String getOutBufferStatus()
    {
        return outBufferStatus;
    }
    public void setOutBufferStatus(String outBufStatus)
    {
        outBufferStatus = outBufStatus;
    }

    //Getter and setter methods for the networkStatus
    public String getNetworkStatus()
    {
        return networkStatus;
    }
    public void setNetworkStatus(String netStatus)
    {
        networkStatus = netStatus;
    }

    //Getter and setter methods for the inputIndexClient
    public int getInputIndexClient()
    {
        return inputIndexClient;
    }
    public void setInputIndexClient(int i1)
    {
        inputIndexClient = i1;
    }

    //Getter and setter methods for the inputIndexServer
    public int getInputIndexServer()
    {
        return inputIndexServer;
    }
    public void setInputIndexServer(int i2)
    {
        inputIndexServer = i2;
    }

    //Getter and setter methods for the outputIndexServer
    public int getOutputIndexServer()
    {
        return outputIndexServer;
    }
    public void setOutputIndexServer(int o1)
    {
        outputIndexServer = o1;
    }

    //Getter and setter for the outputIndexClient
    public int getOutputIndexClient()
    {
        return outputIndexClient;
    }
    public void setOutputIndexClient(int o2)
    {
        outputIndexClient = o2;
    }

    //Getter and setter methods for the maxNbPackets
    public int getMaxNbPackets()
    {
        return maxNbPackets;
    }
    public void setMaxNbPackets(int maxPackets)
    {
        maxNbPackets = maxPackets;
    }

    //Transmitting the transactions from the client to the server through the network
    public boolean send(Transactions inPacket)
    {
        inComingPacket[inputIndexClient].setAccountNumber(inPacket.getAccountNumber());
        inComingPacket[inputIndexClient].setOperationType(inPacket.getOperationType());
        inComingPacket[inputIndexClient].setTransactionAmount(inPacket.getTransactionAmount());
        inComingPacket[inputIndexClient].setTransactionBalance(inPacket.getTransactionBalance());
        inComingPacket[inputIndexClient].setTransactionError(inPacket.getTransactionError());
        inComingPacket[inputIndexClient].setTransactionStatus("transferred");

        System.out.println("\n DEBUG : Network.send() - index inputIndexClient " + inputIndexClient);
        System.out.println("\n DEBUG : Network.send() - account number " + inComingPacket[inputIndexClient].getAccountNumber());


        setInputIndexClient(((getInputIndexClient( ) + 1) % getMaxNbPackets ()));	/* Increment the input buffer index  for the client */
        /* Check if input buffer is full */
        if (getInputIndexClient() == getOutputIndexServer())
        {
            setInBufferStatus("full");

            System.out.println("\n DEBUG : Network.send() - inComingBuffer status " + getInBufferStatus());
        }
        else
            setInBufferStatus("normal");

        return true;
    }

    //Transmitting the transactions from the server to the client through the network
    public boolean receive(Transactions outPacket)
    {
        outPacket.setAccountNumber(outGoingPacket[outputIndexClient].getAccountNumber());
        outPacket.setOperationType(outGoingPacket[outputIndexClient].getOperationType());
        outPacket.setTransactionAmount(outGoingPacket[outputIndexClient].getTransactionAmount());
        outPacket.setTransactionBalance(outGoingPacket[outputIndexClient].getTransactionBalance());
        outPacket.setTransactionError(outGoingPacket[outputIndexClient].getTransactionError());
        outPacket.setTransactionStatus("done");

        System.out.println("\n DEBUG : Network.receive() - index outputIndexClient " + outputIndexClient);
        System.out.println("\n DEBUG : Network.receive() - account number " + outPacket.getAccountNumber());

        setOutputIndexClient(((getOutputIndexClient( ) + 1) % getMaxNbPackets( ))); /* Increment the output buffer index for the client */
        /* Check if output buffer is empty */
        if ( getOutputIndexClient( ) == getInputIndexServer( ))
        {
            setOutBufferStatus("empty");

            System.out.println("\n DEBUG : Network.receive() - outGoingBuffer status " + getOutBufferStatus());
        }
        else
            setOutBufferStatus("normal");

        return true;
    }

    //Transferring the completed transactions from the server to the network buffer
    public boolean transferOut(Transactions outPacket)
    {
        outGoingPacket[inputIndexServer].setAccountNumber(outPacket.getAccountNumber());
        outGoingPacket[inputIndexServer].setOperationType(outPacket.getOperationType());
        outGoingPacket[inputIndexServer].setTransactionAmount(outPacket.getTransactionAmount());
        outGoingPacket[inputIndexServer].setTransactionBalance(outPacket.getTransactionBalance());
        outGoingPacket[inputIndexServer].setTransactionError(outPacket.getTransactionError());
        outGoingPacket[inputIndexServer].setTransactionStatus("transferred");

        System.out.println("\n DEBUG : Network.transferOut() - index inputIndexServer " + inputIndexServer);
        System.out.println("\n DEBUG : Network.transferOut() - account number " + outGoingPacket[inputIndexServer].getAccountNumber());

        setInputIndexServer(((getInputIndexServer() + 1) % getMaxNbPackets())); /* Increment the output buffer index for the server */
        /* Check if output buffer is full */
        if ( getInputIndexServer( ) == getOutputIndexClient( ))
        {
            setOutBufferStatus("full");

            System.out.println("\n DEBUG : Network.transferOut() - outGoingBuffer status " + getOutBufferStatus());
        }
        else
            setOutBufferStatus("normal");

        return true;
    }
    //Transferring the transactions from the network buffer to the server
    public boolean transferIn(Transactions inPacket)
    {
        System.out.println("\n DEBUG : Network.transferIn - account number " + inComingPacket[outputIndexServer].getAccountNumber());
        inPacket.setAccountNumber(inComingPacket[outputIndexServer].getAccountNumber());
        inPacket.setOperationType(inComingPacket[outputIndexServer].getOperationType());
        inPacket.setTransactionAmount(inComingPacket[outputIndexServer].getTransactionAmount());
        inPacket.setTransactionBalance(inComingPacket[outputIndexServer].getTransactionBalance());
        inPacket.setTransactionError(inComingPacket[outputIndexServer].getTransactionError());
        inPacket.setTransactionStatus("received");

        System.out.println("\n DEBUG : Network.transferIn() - index outputIndexServer " + outputIndexServer);
        System.out.println("\n DEBUG : Network.transferIn() - account number " + inPacket.getAccountNumber());

        setOutputIndexServer(((getOutputIndexServer() + 1) % getMaxNbPackets()));	/* Increment the input buffer index for the server */
        /* Check if input buffer is empty */
        if ( getOutputIndexServer( ) == getInputIndexClient( ))
        {
            setInBufferStatus("empty");

            System.out.println("\n DEBUG : Network.transferIn() - inComingBuffer status " + getInBufferStatus());
        }
        else
            setInBufferStatus("normal");

        return true;
    }
    //Handling of connection requests through the network
    public boolean connect(String IP)
    {
        if (getNetworkStatus().equals("active"))
        {
            if (getClientIP().equals(IP))
            {
                setClientConnectionStatus("connected");
                setPortID(0);
            }
            else
            if (getServerIP().equals(IP))
            {
                setServerConnectionStatus("connected");
            }
            return true;
        }
        else
            return false;
    }
    //Handling of disconnection requests through the network
    public boolean disconnect(String IP)
    {
        if (getNetworkStatus( ).equals("active"))
        {
            if (getClientIP().equals(IP))
            {
                setClientConnectionStatus("disconnected");
            }
            else
            if (getServerIP().equals(IP))
            {
                setServerConnectionStatus("disconnected");
            }
            return true;
        }
        else
            return false;
    }
    //toString method for the Network class
    public String toString()
    {
        return ("\n Network status " + getNetworkStatus() + "Input buffer " + getInBufferStatus() + "Output buffer " + getOutBufferStatus());
    }
    //implement the method Run() to execute the server thread
    public void run()
    {


        //System.out.println("\n DEBUG : Network.run() - starting network thread");


        while (true)
        {


            Thread.yield();


            if(getServerConnectionStatus().equals("disconnected") && getClientConnectionStatus().equals("disconnected"))
            {

                System.out.println("\n Terminating network thread - Client disconnected Server disconnected");
                System.exit(0);

            }

        }
    }

}
