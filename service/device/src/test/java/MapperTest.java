import com.example.demo.constant.HelloRequestDtobac;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.swagger2.mappers.ModelMapper;

/**
 * @author chenrunzheng
 * @since 2020/10/10 15:27
 */
public class MapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public  void ddd() throws Exception {

        HelloRequestDtobac requestDtobac = HelloRequestDtobac.builder().age("11").name("fdsa").build();

    }
}
