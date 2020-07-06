import com.example.demo.constant.AlarmType;
import com.example.demo.constant.HelloRequestDto;
import com.example.demo.service.impl.Abshello;
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
        handle(req);

        HelloRequestDto q = HelloRequestDto.builder().age(req.getAge()).name(req.getName()).build();

     //   System.out.println(Integer.valueOf(req.getAge()));
        System.out.println(q.getClass());


    }

    private void handle(HelloRequestDto requestDto){
        requestDto.setName("namee");
    }

}
