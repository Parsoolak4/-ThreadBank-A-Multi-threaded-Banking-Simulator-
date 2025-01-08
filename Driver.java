package Assignment_1;

public class Driver {
    public static void main(String[] args) {

        //implement all the operations of main class

        Network objNetwork = new Network("network");
        objNetwork.start();

        Server objServer = new Server();
        objServer.start();

        Client send = new Client("sending");
        Client receive = new Client("receiving");

        send.start();
        receive.start();


        /**
         * If I change the buffer size from 10 to 20 or to 30, my program will run faster, and the reason is because
         * in each time transferring the data, I can transfer more when I have larger buffer capacity.
         */

    }
}
