package socket;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClient implements Runnable{
 
    //通信socket
    private Socket clientSocket;
    private int port;
    private InetAddress ip;
    //发送的消息内容
    public String message;
    UI myui;
    
    
    public SocketClient(String host,int port,UI ui) {
    	try{
            this.clientSocket = new Socket(host, port);
            this.myui=ui;
            this.init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public SocketClient(String host,int port,String message){
        try{
            this.clientSocket = new Socket(host, port);
            this.message =  message;
            this.init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init() {
    	ip=clientSocket.getLocalAddress();
    	port=clientSocket.getLocalPort();
    	this.listen();
    }
    public void setMessage(String str) {
    	this.message=str;
    }
    public void run() {
        {
            try {
                clientSocket.getOutputStream().write(message.getBytes());
                //System.out.println("DEBUG: " + "send message: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void listen()
    {
    	new SocketClientHandler(clientSocket, myui).start();
    }
}