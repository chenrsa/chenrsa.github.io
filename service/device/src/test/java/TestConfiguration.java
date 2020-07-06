import com.example.demo.DeviceServiceApplication;
import com.example.demo.configuration.ConfigBean;
import com.example.demo.configuration.UserInfoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenrunzheng
 * @since 2020/7/2 14:10
 */
@SpringBootTest(classes = DeviceServiceApplication.class)
@RunWith(SpringRunner.class)
public class TestConfiguration {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserInfoConfig config;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ConfigBean helloBean;

    @Test
    public void testConfig(){
        System.out.println(config.getAddr());
        System.out.println(helloBean.HelloBean().getAddresss());

    }

}
