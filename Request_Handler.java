
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Pipe;
import java.util.*;

public class Request_Handler extends Thread {
	
	public File f;
 
    public Request_Handler() {
    	start();
    }


    public void run() {
    	while(true) {
    		String request;
    		//Pipe로 읽는다
    		Scanner sc = new Scanner(System.in);

    		request = sc.nextLine();
    		//파일을 쓴다.
    		
    		LogHistory loghis = LogHistory.getInstance();
    		
    		BufferedWriter out = null;
			
    		for(Log log :loghis.logList){
    			int i =0;
    			try {
    				out = new BufferedWriter(new FileWriter("log/out" + log.getCId() + ".txt" , true));
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				//파일 생성 실패
    			}
    			
    		    try {
    		    	out.write("Time : " + log.getTime() + " //");
    		    	out.write("ID : " + log.getCId());
	    		    out.newLine();
    		    	String s = log.getLine(0);
    		    	while(s!=null){
    		    		
    		    		out.write(s); 
    	    		    out.newLine();
    	    		    
    		    		i++;
    		    		s=log.getLine(i);
    		    	}
	    		    out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//파일 쓰기 실패
				} 
    		}
    		
    	}
    }

}