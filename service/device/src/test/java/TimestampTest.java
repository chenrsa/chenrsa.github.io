
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import java.sql.Timestamp;
import java.util.Date;
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
        Long t = 1596590552000L;
        System.out.println(new Timestamp(t));
        System.out.println(new Timestamp(1597014304000L));
        System.out.println(timestamp.getTime());
        System.out.println(timestamp.getTime()/1000);
        System.out.println(timestamp.getHours());

        /*Date date = new Date(timestamp.getTime());
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(myFmt1.format(date));*/

        /*Random random = new Random();
        Integer s = random.nextInt(899999) + 100000;
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        System.out.println(code);
        System.out.println(s);*/


       Date d = new Date();
       System.out.println(d);


         String cardNum = "11122222";
            ByteBuf buf = Unpooled.buffer(4);
            byte[] tag = ByteBufUtil.decodeHexDump(cardNum);
            buf.writeBytes(tag);
            long value = buf.readUnsignedIntLE();
            StringBuilder sb = new StringBuilder(String.valueOf(value));
            while (sb.length() < 10) {
                sb.insert(0, "0");
            }
            System.out.println(sb);

    Long time1 = 1600843258L;
    Long time2 = 1600573258L;
    Integer days = Math.toIntExact((time1 - time2) / (3600 * 24));
    System.out.println(days);

    }
}
