import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    private String notifyMessage = "Default Message"; // Initialize notifyMessage
    Timer timer = new Timer();

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println(notifyMessage);
        }
    };

    public void startTimer() {
        // Example: Set a custom future date
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2024); // Future year
        date.set(Calendar.MONTH, Calendar.NOVEMBER);
        date.set(Calendar.DAY_OF_MONTH, 29);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 3);
        date.set(Calendar.SECOND, 30);
        date.set(Calendar.MILLISECOND, 0);

        // Schedule the task to start at the given date and repeat every second
        timer.scheduleAtFixedRate(timerTask, date.getTime(), 1000);
    }

//    public static void main(String[] args) {
//        Reminder reminder = new Reminder();
//        reminder.setNotifyMessage("New Year's Eve Reminder!");
//        reminder.startTimer();
//    }
}
