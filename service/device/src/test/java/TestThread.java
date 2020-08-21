import com.example.demo.constant.CallableThread;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/7/7 19:28
 */
public class TestThread{

    private volatile int a = 10;

    @Test
    public void test() throws InterruptedException {

       /* System.out.println("ffffffffffffffffff");
        ThreadTest threadTest = new ThreadTest("Thread-1");
        threadTest.start();

        ThreadCon threadTest1 = new ThreadCon("Thread-2");
        threadTest1.start();

        Thread.sleep(500);
        System.out.println("aaaaaaaaaaaaaaa");


        MyThread t = new MyThread();
        Thread a = new Thread(t,"a");
        Thread b = new Thread(t,"b");
        Thread c = new Thread(t,"c");
        Thread d = new Thread(t,"d");
        Thread e = new Thread(t,"e");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        Thread.sleep(500);*/



        CallableThread callableThread = new CallableThread();

        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        //参数里面放一个Callable的对象
        FutureTask<Integer> result = new FutureTask<>(callableThread);

        new Thread(result).start();

        try {
            Integer sum = result.get();
            System.out.println("sum == " + sum);
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }


    }


}
