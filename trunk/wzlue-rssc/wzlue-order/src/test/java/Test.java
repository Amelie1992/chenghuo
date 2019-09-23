import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_WEEK, 10);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out.println(year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒");
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        System.out.println(dateStr);
    }
}
