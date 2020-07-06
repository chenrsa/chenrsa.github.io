import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.HelloRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/5/26 10:19
 */
public class TestJsonObject {



    @Test
    public void TestJson() throws IOException {
        String jsonString = "{\"password\":\"123456\",\"username\":\"张三\"}";

        JSONObject jsonObject =JSONObject.parseObject(jsonString);
        String name  = jsonObject.getString("username");
        System.out.println(name);
        ObjectMapper objectMapper = new ObjectMapper();
        HelloRequestDto requestDto = HelloRequestDto.builder().age("12").name("chen").build();
        String dto =  objectMapper.writeValueAsString(requestDto);
        HelloRequestDto dd = objectMapper.readValue(dto,HelloRequestDto.class);
        System.out.println(dd);
    }

}
