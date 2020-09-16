import com.example.demo.constant.ApiRequest;
import com.example.demo.constant.HelloRequestDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/5/25 9:41
 */

public class Testfor {


    @Test
    public void test(){
        ApiRequest<HelloRequestDto> requestDtoApiRequest = new ApiRequest<>();
        List<HelloRequestDto> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            HelloRequestDto dto = new HelloRequestDto();
            if (i ==  0) {
                dto.setName("aa");
            } else {
                dto.setName("bbbb");
            }
            list.add(dto);
        }
        System.out.println(list);


        Calendar calendar = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        calendar.setTimeInMillis(timestamp.getTime());
        System.out.println(calendar.get(Calendar.MONTH));

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        String s  = "您好，【3楼门禁】门禁出现人员【chenrunz】的体温过高告警，请立刻核实！";

        String msg = s.substring(s.indexOf("人员【") + 3,s.indexOf("】的体温"));
        System.out.println(msg);
    }


}
