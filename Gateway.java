
import java.io.*;
import java.net.Socket;
import java.util.*;

public class Gateway extends Thread{

	public Socket socket;
	public Node node;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Gateway(Socket socket,Node node) {
	     this.socket = socket;
	     this.node = node; 
	     try {
	    	 is = socket.getInputStream();
			 os = socket.getOutputStream();
		     dis = new DataInputStream(is);
		     dos = new DataOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    start();
	}

	public void closeGateWay(){
		System.out.println("노드 종료");
		node.alive_node = false;
		try {
			dos.close();
			dis.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public void sendCmd(String type, String cmd) {
        // TODO implement here
    	try {
			dos.writeUTF(parseCmd(type,cmd));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//클라이언트로부터 프로세스 리스트를 전송하는 스레드
    public void run() {
       // TODO implement here
    	try {
	    	while(true){
	    		System.out.println("Node GateWay");
	    		String str;
	    		
	    			List<String> processList = new ArrayList<String>();
	    			//클라이언트의 전송요청
	    			str=dis.readUTF();
	    			//클라이언트 프로세스리스트가 몇줄인지 읽어온다.    		
	    			int n = getLineNum(str);
	    			
	    			for(int i =0 ;i<n;i++){
	    				String s =parsePL(dis.readUTF());
	    				processList.add(s);
	    			}
	    			//노드에 저장
	    			node.savePL(processList);
	    		
	    	}
    	} catch (IOException e) {
    		System.out.println("에러발생");
			closeGateWay();
		}
    }
    
    public String parsePL(String str) {
    	String data[] = str.split(" +");
    	String result = "Process Name: " + data[0] + " Memory: " + data[4] + " K";
		return result;
    }

    public int getLineNum(String str) {
    	int n = 0;
    	//파싱을 합니다.
    	n=Integer.parseInt(str);
    	return n;
    }
    public String parseCmd(String type,String cmd) {
        // TODO implement here
    	return type+"+"+cmd;
    }

}