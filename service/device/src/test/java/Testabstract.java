import com.example.demo.constant.AlarmType;
import com.example.demo.constant.BleRssi;
import com.example.demo.constant.HelloRequestDto;
import com.example.demo.entity.TestPersonInfoEntity;
import com.example.demo.service.impl.Abshello;
import java.sql.Date;
import java.sql.Timestamp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenrunzheng
 * @since 2020/4/2 10:14
 */
public class Testabstract {

    @Autowired
    private Abshello abshello;

    @Test
    public void Testabs(){

      //  TestPersonInfoEntity entity = abshello.computePay("aaa");
        System.out.println(AlarmType.getType(1));


        HelloRequestDto req  = HelloRequestDto.builder().name("22").build();


        HelloRequestDto q = HelloRequestDto.builder().age(req.getAge()).name(req.getName()).build();

     //   System.out.println(Integer.valueOf(req.getAge()));
        System.out.println(q.getClass());

       /* Timestamp t = new Timestamp(System.currentTimeMillis());
        System.out.println(new Date(t.getTime()));
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date( t.getTime()));
        System.out.println(date);

        BleRssi bleRssi = BleRssi.builder().rssi(-61).build();
        Integer v = -63;
        if (bleRssi.getRssi() - v  > 0) {
            System.out.println("fffffffffffffffffffffffff");
        }*/


    }

}
