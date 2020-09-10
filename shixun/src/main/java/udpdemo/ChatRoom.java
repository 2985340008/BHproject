package udpdemo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.DatagramSocket;
import java.net.SocketException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class ChatRoom {
    public static void main(String[] args) throws SocketException {
        DatagramSocket dsSend = new DatagramSocket();
        DatagramSocket dsReceive = new DatagramSocket(12306);

        SendThread st = new SendThread(dsSend);
        ReceiveThread rt = new ReceiveThread(dsReceive);

        Thread t1 = new Thread(st);
        Thread t2 = new Thread(rt);
        t1.start();
        t2.start();
    }
}
