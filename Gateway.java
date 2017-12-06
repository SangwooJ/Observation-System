
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 
 */
public class Gateway extends Thread {

	Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	public Client_Controller contrl;
	
    public Gateway(Client_Controller c) {
    	//3.connection
    	String _ip = "127.0.0.1";
    	int _port = 9999;
    	contrl = c;
    	try {
			socket = new Socket(_ip,_port);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.exit(0);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("접속실패");
			System.exit(0);
			e.printStackTrace();
		}
    			
    }

    public void sendPL(List<String> list ) {
        // TODO implement here
    	try {
    		//보낼 프로세스리스트의 라인 수
    		dos.writeUTF(Integer.toString(list.size()));
	    	for(int i =0 ; i<list.size(); i++){
	    		//프로토콜 생성 추가
				dos.writeUTF(list.get(i));
	    	}
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			
    		try {
				dos.close();
				dis.close();
				socket.close();
				System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    }

    public void run() {
        // TODO implement here
    	while(true){
    		receive();
    	}
    }
    public void receive(){
    	try {
			String cmd = dis.readUTF();
			System.out.println(cmd);
			
			StringTokenizer s = new StringTokenizer(cmd,"+");
			
			String cmd_type = s.nextToken();
			String cmd_msg  = s.nextToken();
			
			contrl.transCmd(cmd_type, cmd_msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				dos.close();
				dis.close();
				socket.close();
				System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("error");
			}
		}
    }
}