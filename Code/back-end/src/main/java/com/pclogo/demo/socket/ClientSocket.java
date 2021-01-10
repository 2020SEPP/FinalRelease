//package com.pclogo.demo.socket;
//
//import com.geniuses.sewage_zero_straight.bean.LZP_DATASERVER_TC_PROHIBSOURCE;
//import com.geniuses.sewage_zero_straight.service.LZP_DATASERVER_TC_PROHIBSOURCEService;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.net.Socket;
//import java.util.concurrent.TimeUnit;
//
//import static com.geniuses.sewage_zero_straight.net.socket.SocketHandler.*;
//
///**
// * @author zhoujian
// * 自定义封装的连接的客户端
// */
//@Slf4j
//@Data
//public class ClientSocket implements Runnable{
//
//    private Socket socket;
//    private DataInputStream inputStream;
//    private DataOutputStream outputStream;
//    private String key;
//    private String message;
//
//    @Override
//    public void run() {
//        //每5秒进行一次客户端连接，判断是否需要释放资源
//        while (true){
//            try {
//                TimeUnit.SECONDS.sleep(5);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (isSocketClosed(this)){
//                log.info("客户端已关闭,其Key值为：{}", this.getKey());
//                //关闭对应的服务端资源
//                close(this);
//                break;
//            }
//        }
//    }
//}
