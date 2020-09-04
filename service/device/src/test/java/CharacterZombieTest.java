import com.example.demo.constant.NewsPaperOfficeJava;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/9/3 9:54
 */
public class CharacterZombieTest {



    @Test
    public void test(){
     //   RedZombie redZombie = new RedZombie(new SlowSpeed(),new normalAttack());
      /*  Character character = new RedZombie(new SlowSpeed(),new normalAttack());
        character.attack();*/

        NewsPaperOfficeJava paperOfficeJava = new NewsPaperOfficeJava();
        paperOfficeJava.setMessage("fdsafsa");
        paperOfficeJava.sendMessage();
    }

}
