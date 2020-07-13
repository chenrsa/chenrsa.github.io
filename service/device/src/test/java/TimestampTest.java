import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/4/29 9:19
 */
public class TimestampTest {


    @Test
    public void TestTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime()/1000);
        System.out.println(timestamp.getHours());
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(timestamp.getTime());
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
        System.out.println(myFmt1.format(date));

        /*Random random = new Random();
        Integer s = random.nextInt(899999) + 100000;
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        System.out.println(code);
        System.out.println(s);*/
        abc:


        System.out.println(RandomStringUtils.random(6,"0123456789"));
        System.out.println("ccccccccccc");
        new Thread(() ->{
            System.out.println("ddddddddddd");
        }).start();

        System.out.println("aaaaaaaaaaa");


    }
}
