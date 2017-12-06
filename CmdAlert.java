
import java.util.*;

import javax.swing.JOptionPane;

/**
 * 
 */
public class CmdAlert implements Command{

	String alert_msg;
    public CmdAlert(String msg) {
    	this.alert_msg = msg;
    }


    public void start() {
        // TODO implement here
    	System.out.println(alert_msg);
    	JOptionPane.showMessageDialog(null, alert_msg, "°æ°í", JOptionPane.ERROR_MESSAGE);
		
    }
}