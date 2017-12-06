
import java.util.*;

import javax.swing.JOptionPane;

/**
 * 
 */
public class Client_Controller {

    public Client_Controller() {
    }

    public Gateway gateway;

    public Reader reader;

    public Commander commander;

    public void startUp() {
        // TODO implement here
    	commander = new Commander();
    	
    	gateway = new Gateway(this);
    	gateway.start();
    	
    	reader = new WindowReader(this);
    }

    /**
     * @param process_list
     */
    public void transGateway(List<String> process_list) {
        // TODO implement here
    	gateway.sendPL(process_list);
    }

    /**
     * @param cmd
     */
    public void transCmd(String type, String cmd) {
        // TODO implement here
    	System.out.println("transCmd");
    	commander.execCmd(type,cmd);
    }
    
    public static void main(String[] args) {
    	Client_Controller cc = new Client_Controller();
    	cc.startUp();
    }

}