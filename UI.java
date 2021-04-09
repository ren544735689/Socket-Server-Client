package socket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI {
	public static final int Width=400;
    public static final int Height=600;
    public SocketClient client;  
    JTextArea contactfield;
    
    UI(){}
    UI(SocketClient client){
    	this.client=client;
    }
    
    void setsocket(SocketClient client)
    {
    	this.client=client;
    }
    
    void drawUI() {
    	JFrame frame=new JFrame("Client");    	//create window
        frame.setSize(Width, Height);
        JPanel jp=new JPanel();    				//create JPanel object
        jp.setLayout(null);
        
        contactfield=new JTextArea();    	// input txt area
        contactfield.setLineWrap(true);    										// automatically \n
        JScrollPane cscroll=new JScrollPane(contactfield);    					// add scroll
        contactfield.setBounds(30, 30, 300, 320);
        cscroll.setBounds(30,30,300,320);
        jp.add(cscroll);
        
        JTextArea txtfield=new JTextArea("please input message here:\n  format:ip\':\' port\'\\n\' message\'\\n\'");    	// input txt area
        txtfield.setLineWrap(true);    										// automatically \n
        JScrollPane scroll=new JScrollPane(txtfield);    					// add scroll
        txtfield.setBounds(30, 400, 200, 120);
        scroll.setBounds(30,400,200,120);
        jp.add(scroll);
        
        JButton button_ok=new JButton ("OK");	// "OK" button
        button_ok.setBounds(260, 450, 80, 30);
        button_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//button_ok.setEnabled(false);
            	String txt=txtfield.getText();
            	//System.out.println("DEBUG: input txt: "+txt);
            	client.setMessage(txt);
            	txtfield.setText("");
            	client.run();
            }
        });
        jp.add(button_ok);
        
        frame.add(jp);
        frame.setBounds(400, 200, Width, Height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void txtappend(String str) {
    	contactfield.append(str);
    }
}