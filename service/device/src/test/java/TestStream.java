import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;
/**
 * @author chenrunzheng
 * @since 2020/3/26 18:42
 */

@Slf4j
public class TestStream {

    @Test
    public  void ddd() throws Exception {

        List<String> strings = Arrays.asList("abc", "c", "bc", "efg", "abcd","", "jkl");

        List<String> res = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        Map<String,String> resss = strings.stream().collect(Collectors.toMap(this::handle,s -> s));
        ImmutablePair<String,String> pair = new ImmutablePair<>("ss","aa");

        log.info(pair.getLeft());


       /* Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        System.out.println("map==" + map);

        Set<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        System.out.println("通过Map.keySet遍历key和value：" + set);

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：" + map.keySet());
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：" + map.entrySet());
        Set<Map.Entry<String, String>> it = map.entrySet();
        System.out.println("通过Map.entrySet使用iterator遍历key和value：" + it.iterator().next());

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }*/

        Person xioaming = new Person();
        xioaming.setAddr("aa");
        xioaming.setAge("12");
        xioaming.setName("cc");

        System.out.println(xioaming);
        System.out.println(xioaming.getAddr());

        String v = "100";

        handle(v);
        System.out.println("a==" + v);


        Map<String, String> map = new HashMap<String, String>();
        map.put("age", "44");
        map.put("name", "chen");
        map.put("addr", "nanjing");
        fds(map);
        System.out.println("map==" + map);

        Set<String> a= map.keySet();
        for (String b : a) {
            System.out.println("set==" + map.get(b));
        }



    }
    private void fds(Map<String,String> a){

        a.put("aaaa","fdsfd");
    }

    private String handle(String str){

        return str+'s';
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Person{
        String name;
        String age;
        String addr;
    }
}
