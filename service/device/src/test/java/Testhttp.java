import com.example.demo.http.Httpclient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * @author chenrunzheng
 * @since 2020/3/12 16:08
 */
@Slf4j
public class Testhttp {


    @Autowired
    private Httpclient httpclient;

    @Test
    public void testHttp() throws Exception {


        String baiDuUrl_host = "http://api.map.baidu.com/geocoder?location=118.77429317814139,31.84367984958835&output=json&src=HYJ";
        String urlt = "http://api.map.baidu.com/geoconv/v1/?coords=118.74030998364739,31.94113537097132&ak=hSunH3oQzdI7pvRm7UGtffpt&output=json&from=1&to=5";
        WebClient webClient = WebClient.create();
        String aaa = webClient
                .get()
                .uri(baiDuUrl_host)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(aaa);

        String a = "aaaaa";
        Integer b = 1;
        b = 1 | b;

    }
}
