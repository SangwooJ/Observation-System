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
    	//��� �ݺ�üũ
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
    		//���Ⱑ ����� �����͸� view�� ���� �� �ִ� ���·� ����
    		for(Node n : node_composite.NodeList){
    			//��尡 ����ִ°��
    			if(n.alive_node){
    				server_view.getNodeView(n.cid).printProcessList(n.StringList);
    			}
    			else{
    				System.out.println("Ŭ���̾�Ʈ ���� Ȯ��");
    				server_view.getNodeView(n.cid).textArea.setText("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
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
    		//��ü �޽���
    		node_composite.transCmd(type, cmd);
    	} else {
    		//nodenum���� ������
    		if(node_composite.getNode(nodeNum) != null){
        		node_composite.getNode(nodeNum).transCmd(type, cmd);    			
    		}
    		else{
    			server_view.textArea.append("������ �� �����ϴ�.");
    		}
    	}
    	
    }
    
    public static void main(String[] args){
    	Server_Controller sc = new Server_Controller();
    	sc.startUp();
    }

}