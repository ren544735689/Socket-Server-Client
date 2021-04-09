package socket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	//A server socket waits for requests to come in over the network.
	public static final int PORT=1111;
	private ServerSocket serverSocket;
	
	public static void main(String []args) {
		SocketServer server= new SocketServer(PORT);
		server.listenClientConnect();
	}
 
    public SocketServer(int port){
        try{
            serverSocket = new ServerSocket(port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
    /**
     * Listens for a connection to be made to this socket and accepts it.6
     * The method blocks until a connection is made.
     */
    public void listenClientConnect(){
        while(true){
            try {
                System.out.println("start listening... PORT: "+PORT);
                Socket clientConnectSocket = serverSocket.accept();
                InetAddress ip = clientConnectSocket.getInetAddress();
                int port=clientConnectSocket.getPort();
                System.out.println("socket accept! from ip: " + ip + " port: " + port);
                new SocketServerHandler(clientConnectSocket).start();
                // add socket to vector
                
            } catch (Exception e) {
                System.out.println("Server Error: PORT " + PORT + " is using!");
            }
        }
    }
}
