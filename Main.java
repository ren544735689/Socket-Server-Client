package socket;

public class Main {
	
	public static final int PORT=1111;
	
	public static void main(String[] args) {
		UI ui= new UI();
		ui.drawUI();
		
		//new Thread(new SocketClientWorker("127.0.0.1", 6666,"ClientA",5000,"A窟僕��連。。")).start();	
		SocketClient client=new SocketClient("127.0.0.1", PORT, ui);
		client.init();
		
		ui.setsocket(client);
		
	}
}
