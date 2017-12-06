
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 */
public class WindowReader implements Reader {

    /**
     * Default constructor
     */
	List<String> process_list;
	Client_Controller cc;
    public WindowReader(Client_Controller cc) {
    	this.cc = cc;
    	while(true){
    		readPL();
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }


	@Override
	public void readPL() {
		// TODO Auto-generated method stub
		try{ 
			String s;
			Process oProcess = new ProcessBuilder("cmd", "/c", "tasklist").start();

		    // �ܺ� ���α׷� ��� �б�
		    BufferedReader stdOut   = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
		    BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));
		    process_list = new ArrayList<String>();
		    // "ǥ�� ���"�� "ǥ�� ���� ���"�� ���
		    while ((s =   stdOut.readLine()) != null){
		    	process_list.add(s);
		    }
		    while ((s = stdError.readLine()) != null) System.err.println(s);
		    process_list.remove(0);
		    process_list.remove(0);
		    process_list.remove(0);
		    
		    cc.transGateway(process_list);
		}catch(Exception e){ 
			System.out.println(e);
		}
	}



}