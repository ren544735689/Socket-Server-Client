package socket;

import java.io.InputStream;
import java.net.Socket;
 
public class SocketClientHandler extends Thread{

    private Socket clientConnectSocket;
    public UI ui;
    public SocketClientHandler(Socket clientConnectSocket,UI ui){
        this.clientConnectSocket = clientConnectSocket;
        this.ui=ui;
    }
 
    @Override
    public void run(){
        try {
            InputStream inputStream = clientConnectSocket.getInputStream();
            while (true) {
                byte[] data = new byte[100];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("receive message from ip: "+clientConnectSocket.getInetAddress()+" port: "+clientConnectSocket.getPort() +" message: \n" + message+"\n");
                    
                    //System.out.println("DEBUG::ClientHandler: recive message:"+message);
                    
                    ui.txtappend(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}