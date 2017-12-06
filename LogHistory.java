import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogHistory {
    private static volatile LogHistory INSTANCE = null;
    public List<Log> logList;

    public LogHistory() {
        logList = new ArrayList<>();
    }


    public void LogHistory() {
        // TODO implement here
    }

    public static LogHistory getInstance() {
        if (INSTANCE == null) {
            synchronized (LogHistory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LogHistory();
                }
            }
        }
        return INSTANCE;
    }

    public void addLog(List list, int cid) {
        // TODO implement here
        String time = "[" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()) + "] ";
        Log log = new Log(list, time, Integer.toString(cid));
        logList.add(log);
    }
}