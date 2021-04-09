package socket;

import java.io.InputStream;
import java.net.Socket;
 
public class SocketServerHandler extends Thread{
	static Socket [] vec=new Socket[10];
	static int servernum=0;
	
    private Socket clientConnectSocket;
    public SocketServerHandler(Socket clientConnectSocket){
        this.clientConnectSocket = clientConnectSocket;
        vec[servernum++]=clientConnectSocket;
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
                    System.out.println("receive message from ip: "+clientConnectSocket.getInetAddress()+" port: "+clientConnectSocket.getPort() +" message: \n" + message);
                    clientConnectSocket.getOutputStream().write(data);
                    //System.out.println("DEBUG: message:"+message);
                    
                    
                    
                    // processing input message:
                	String toip="";
                	int ipint = message.indexOf(':');
                	if(ipint==-1) {
                		System.out.println("Unvalid input ip!");
                	}
                	else {
                       	toip=message.substring(0,ipint);
                	}
                	message=message.substring(ipint+1);
                	//System.out.println("DEBUG: message:"+message+" ipint:"+ipint);
                	String toport="";
                	int ipport = message.indexOf('\n');
                	if(ipport==-1) {
                		System.out.println("Unvalid input port!");
                	}
                	else {
                       	toport=message.substring(0,ipport);
                	}
                	message=message.substring(ipport+1);
                	//System.out.println("DEBUG: message:"+message+" portint:"+ipport);
                	//System.out.println("DEBUG::repackage and send... toip:"+toip+" toport:"+toport);
                	
                	// check in accept socket lists
                	boolean findflag=true;
                	for(int i=0;i<servernum;i++) {
                		Socket ss;
                    	String sip;
                    	int sport;
                		ss=vec[i];
                		sip=ss.getInetAddress().toString();
                		sip=sip.substring(1,sip.length());
                		sport=ss.getPort();
                		if(sip.equals(toip) && String.valueOf(sport).equals(toport)) {
                			System.out.println("Find target! starting sending...");
                			// System.out.println("DEBUG::sending to ip:"+toip+" port:"+toport+" message:"+message);
                			message="from "+clientConnectSocket.getInetAddress()+":"+clientConnectSocket.getPort() +"\n"+message+"\n";
                			ss.getOutputStream().write(message.getBytes());
                			findflag=true;
                		}
                	}
                	if(findflag==false) {
                		String errormessage="Sorry, invalid ip or port!";
                		clientConnectSocket.getOutputStream().write(errormessage.getBytes());
                	}
                	
                	
                	
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}