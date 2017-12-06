
import java.util.*;

/**
 * 
 */
public class NodeComposite implements NodeInterface {

	public List<Node> NodeList;

	
    /**
     * Default constructor
     */
    public NodeComposite() {
    	NodeList = new ArrayList<Node>();
    }
    /**
     * 
     */
    public void add(Node n) {
        // TODO implement here
    	NodeList.add(n);
    }
    
    public Node getNode(int cid) {
    	for (Node n : NodeList) {
    		if (n.cid == cid)
    			return n;
    	}
    	return null;
    }

    /**
     * 
     */
    public void remove(Node n) {
        // TODO implement here
    	NodeList.remove(n);
    }

	@Override
	public void transCmd(String type, String cmd) {
		// TODO Auto-generated method stub
		for(Node n : NodeList){
			n.transCmd(type, cmd);
		}
		
	}
    
    

}