import java.net.Socket;
import java.util.*;

public class Server_Controller {

	private Connector connector;
	private Request_Handler handler;
    public NodeComposite node_composite;
    Server_View server_view;
    private int cid;
    
    final int MAX = 1000000;
    public Server_Controller() {
    	cid = 0;
    }
    
    public void startUp(){
    	server_view = new Server_View(this);
    	connector = new Connector(this);
    	connector.start();
    	System.out.println("connect ok");
    	handler = new Request_Handler();
    	System.out.println("RH ok");
    	
    	node_composite = new NodeComposite();
    	//노드 반복체크
    	chkNode();
    	
    }

    public void addNode(Socket socket) {
        // TODO implement here
    	Node node = new Node(socket, cid);
    	server_view.addNodeView(cid);
    	node_composite.add(node);
    	cid++;
    }
    
    /**
     * @return
     */
 
    public void deleteNode(int cid){
    	Node dltNode = null;
    	for(Node n : node_composite.NodeList){
			if(n.cid == cid){
				dltNode = n;
			}
		}
    	if(dltNode != null){
    		dltNode.dieNode();
        	node_composite.remove(dltNode);
    	}
    }
    public void chkNode() {
    	while(true) {
    		Node dead_node=null;
    		//여기가 노드의 데이터를 view에 찍을 수 있는 형태로 가공
    		for(Node n : node_composite.NodeList){
    			//노드가 살아있는경우
    			if(n.alive_node){
    				server_view.getNodeView(n.cid).printProcessList(n.StringList);
    			}
    			else{
    				System.out.println("클라이언트 종료 확인");
    				server_view.getNodeView(n.cid).textArea.setText("클라이언트가 종료되었습니다.");
    				dead_node=n;
    			}
    		}
    		if(dead_node != null){
    			deleteNode(dead_node.cid);
    		}
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }


    public void inputCmd(String type, String cmd, int nodeNum) {
        // TODO implement here
    	if (nodeNum == MAX) {
    		//전체 메시지
    		node_composite.transCmd(type, cmd);
    	} else {
    		//nodenum으로 날린다
    		if(node_composite.getNode(nodeNum) != null){
        		node_composite.getNode(nodeNum).transCmd(type, cmd);    			
    		}
    		else{
    			server_view.textArea.append("전송할 수 없습니다.");
    		}
    	}
    	
    }
    
    public static void main(String[] args){
    	Server_Controller sc = new Server_Controller();
    	sc.startUp();
    }

}