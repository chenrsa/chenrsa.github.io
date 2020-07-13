import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

/**
 * @author chenrunzheng
 * @since 2020/7/7 9:16
 */
public class WriteFileTest {

    @Test
    public void TestWriteFile() throws IOException {
        //创建并写一个文件
        /*try{
            BufferedWriter out = new BufferedWriter(new FileWriter("runoob.txt"));
            out.write("菜鸟教程");
            out.close();
            System.out.println("文件创建成功！");
        } catch(IOException e){
        }*/

        //从一个文件中读数据
 /*       try {
            BufferedReader in = new BufferedReader(new FileReader("runoob.txt"));
            String str;
            //readLine 读取一行数据以\t,\n结尾的
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
        }*/

        //文件删除
        try{
            File file = new File("D:\\Temp\\demo\\service\\device\\runoob.txt");
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        BufferedWriter out = new BufferedWriter(new FileWriter("runoob.txt"));
        out.write("菜鸟教程");
        out.close();
        System.out.println("文件创建成功！");
        InputStream in = new FileInputStream(new File("runoob.txt"));

    }
}
