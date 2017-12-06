import java.util.List;


public class Log {
    private List<String> log;
    private String time;
    private String cId;

    public Log(List<String> log, String time, String cId) {
        this.log = log;
        this.time = time;
        this.cId = cId;
    }
    
    public String getLine(int n){
    	if(n<log.size()){
    		return log.get(n);
    	}
    	return null;
    }
    public String getTime(){
    	return time;
    }
    public String getCId(){
    	return cId;
    }
}