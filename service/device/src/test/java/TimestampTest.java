import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/4/29 9:19
 */
public class TimestampTest {


    @Test
    public void TestTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        Date date = new Date(timestamp.getTime());
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        System.out.println(code);

        String phone = "19986700306";

    }
}
