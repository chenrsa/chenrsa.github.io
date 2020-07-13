import com.example.demo.constant.AesUtil;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/7/10 13:59
 */
public class TestAes {


    @Test
    public void dosome() throws UnsupportedEncodingException {
        String en = AesUtil.encodeAes("18700000001"+"1594361585"+"b7d6f853-2190-4848-ab50-e029c4a309fc10","7dsnHXYfaTbzeffePRJc6jc48KwbabCs");
        System.out.println(en);
    }
}
