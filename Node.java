
import java.net.Socket;
import java.util.*;

/**
 * 
 */
public class Node implements NodeInterface{


    public Gateway gate;
    public List<String> StringList;
    public int cid;
    public boolean alive_node;
    public Node(Socket socket, int cid) {
    	gate = new Gateway(socket,this);
    	StringList= new ArrayList<>();
    	this.cid = cid;
    	alive_node = true;
    }

	public void dieNode(){
		
    	System.out.println("�ݾƾߵȴ�.");
    }
   
    public void transCmd(String type, String cmd) {
        // TODO implement here
    	gate.sendCmd(type, cmd);
    }


    public void savePL(List<String> processList) {
        // TODO implement here
    	StringList = processList;
    	LogHistory logHis = LogHistory.getInstance();
    	logHis.addLog(processList, cid);
    	
    }

}