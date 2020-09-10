package com.example.demo.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class ReceiveDome {

    /*
    UDP协议接收数据
    1.创建接收端Socket对象
    2.创建一个数据包（接收数据）
    3.调用Socket对象的接收方法接收数据
    4.解析数据包，并显示在控制台
    5.释放资源
     */
    @org.junit.Test
    public void dome1() throws IOException {

        //创建接收端Socket对象
        DatagramSocket ds = new DatagramSocket(10086);

        //创建一个数据包（接收容器）
        byte[] bys = new byte[1024];
        int length = bys.length;
        DatagramPacket dp = new DatagramPacket(bys, length);

        //调用Socket对象的接受方法接收数据
        ds.receive(dp);

        //解析数据包，并显示在控制台
        int len = dp.getLength();
        String s = new String(bys, 0, len);
        InetAddress address = dp.getAddress();
        String ip = address.getHostAddress();
        String name = address.getHostName();
        System.out.println(ip + "传递的数据是：" + s + name);

        //释放资源
        ds.close();
    }

    /*
    UDP协议发送数据
    1.创建发送端Socket对象
    2.创建数据，并把数据打包
    3.调用Socket对象的发送方法发送数据包
    4.释放资源
     */
    @org.junit.Test
    public void test2() throws SecurityException, IOException {
        //创建发送端Socket对象
        DatagramSocket ds = new DatagramSocket();

        //创建数据，并把数据打包
        byte[] bys = "hello,洋小伙".getBytes();
        int length = bys.length;
        InetAddress address = InetAddress.getByName("192.168.1.132");

        //端口号
        int port = 8888;
        DatagramPacket dp = new DatagramPacket(bys, length, address, port);

        //调用Socket对象的发送方法发送数据包
        ds.send(dp);
        ds.close();

    }


    /*
    UDP程序简化版
     */
    //接收端
    @org.junit.Test
    public void test3() throws IOException {
        //创建接收端的Socket对象
        DatagramSocket ds = new DatagramSocket(12345);

        //创建一个包裹
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);

        //接收数据
        ds.receive(dp);

        //解析数据
        String ip = dp.getAddress().getHostAddress();
        String s = new String(dp.getData(), 0, dp.getLength());
        System.out.println(ip + "data is:" + s);

        //释放资源
        ds.close();
    }

    //发送端
    @org.junit.Test
    public void test4() throws IOException {
        //创建发送端的Socket对象
        DatagramSocket ds = new DatagramSocket();

        //创建数据并打包
        byte[] bys = "现在解决问题了，丷协议可用！！！".getBytes();
        DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("127.0.0.1"), 12345);

        //发送数据
        ds.send(dp);

        //释放资源
        ds.close();
    }

    /*
    数据来自于键盘录入
    键盘录入数据要自己看着录入结束
     */
    //发送端
    @org.junit.Test
    public void test5() throws IOException {

        //创建发送端的Socket对象
        DatagramSocket ds = new DatagramSocket();

        //封装键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            if ("100".equals(line)) {
                break;
            }
            //创建数据并打包
            byte[] bys = line.getBytes();
            DatagramPacket dp = new DatagramPacket(bys, bys.length,
                    InetAddress.getByName("127.0.0.1"), 12346);

            //发送数据
            ds.send(dp);
        }
        //释放资源
        ds.close();
    }

    //接收
    @Test
    public void test6() throws IOException {

        //创建接收端的Socket
        DatagramSocket ds = new DatagramSocket(12346);
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

            //释放资源（可以有，但是没必要）
            ds.close();
        }
    }
}
