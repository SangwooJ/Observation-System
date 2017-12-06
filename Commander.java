
import java.util.*;

/**
 * 
 */
public class Commander {

	Command command;
    public Commander() {
    }

    /**
     * @param cmd
     */
    public void execCmd(String cmd_type, String cmd_msg) {
        // TODO implement here
    	
    	switch(cmd_type){
    	case "ALERT" :
    		command = new CmdAlert(cmd_msg);
    		command.start();
    		break;
    	case "PROCESS_END":
    		command = new CmdProcessEnd();
    		command.start();
    	}
    }

    /**
     * @param cmd
     */

}