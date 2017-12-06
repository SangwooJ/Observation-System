
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
		System.out.println("��� ����");
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
//Ŭ���̾�Ʈ�κ��� ���μ��� ����Ʈ�� �����ϴ� ������
    public void run() {
       // TODO implement here
    	try {
	    	while(true){
	    		System.out.println("Node GateWay");
	    		String str;
	    		
	    			List<String> processList = new ArrayList<String>();
	    			//Ŭ���̾�Ʈ�� ���ۿ�û
	    			str=dis.readUTF();
	    			//Ŭ���̾�Ʈ ���μ�������Ʈ�� �������� �о�´�.    		
	    			int n = getLineNum(str);
	    			
	    			for(int i =0 ;i<n;i++){
	    				String s =parsePL(dis.readUTF());
	    				processList.add(s);
	    			}
	    			//��忡 ����
	    			node.savePL(processList);
	    		
	    	}
    	} catch (IOException e) {
    		System.out.println("�����߻�");
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
    	//�Ľ��� �մϴ�.
    	n=Integer.parseInt(str);
    	return n;
    }
    public String parseCmd(String type,String cmd) {
        // TODO implement here
    	return type+"+"+cmd;
    }

}