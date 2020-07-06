import com.example.demo.DeviceServiceApplication;
import com.example.demo.rabbit.SendRabbitMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenrunzheng
 * @since 2020/6/3 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceServiceApplication.class)
public class TestRabbit {


    @Autowired
    private SendRabbitMsg sendRabbitMsg;

    @Test
    public void rabbitTest(){
        sendRabbitMsg.send("hello world！！！！！！！！！");
        System.out.println("casssssssssssssssd");
    }
}
