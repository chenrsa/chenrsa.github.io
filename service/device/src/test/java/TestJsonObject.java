import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/5/26 10:19
 */
public class TestJsonObject {


    @Test
    public void TestJson(){
        String jsonString = "{\"password\":\"123456\",\"username\":\"张三\"}";

        JSONObject jsonObject =JSONObject.parseObject(jsonString);
        String name  = jsonObject.getString("username");
        System.out.println(name);
    }

}
