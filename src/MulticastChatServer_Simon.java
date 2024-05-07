import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatServer_Simon {
    public static void main(String[] args)
        throws Exception {
        // Default port number
        int portnumber = 8080;
        if(args.length >= 1)
        {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MulticastSocket
        MulticastSocket serverMulticastSocket =
                new MulticastSocket(portnumber);
        System.out.println("MulticastSocket is created at port " + portnumber);

        // Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("22.4.5.6");

        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method was called...");
        boolean infinite =true;

        // Continually recieves data and prints them
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String message = new String(data.getData()).trim();
            System.out.println("Message recieved from client = " + message);
        }
        serverMulticastSocket.close();
    }
}