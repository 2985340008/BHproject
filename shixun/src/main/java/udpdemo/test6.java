package udpdemo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class test6 {
    public static void main(String[] args) throws IOException {

        //创建接收端的Socket
        DatagramSocket ds = new DatagramSocket(12347);
        while (true) {
            //创建一个包
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);

            //接收数据
            ds.receive(dp);

            //解析数据
            String ip = dp.getAddress().getHostAddress();
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println(ip + "dta is:" + s);

            //不能自动关闭资源
        }
    }
}
