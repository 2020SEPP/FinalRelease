//package com.pclogo.demo.socket;
//
//import com.geniuses.sewage_zero_straight.bean.User;
//import com.geniuses.sewage_zero_straight.service.UserService;
//import com.geniuses.sewage_zero_straight.util.JSONUtil;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import static com.geniuses.sewage_zero_straight.net.socket.SocketHandler.*;
//
//@Slf4j
//@Data
//@Component
//@PropertySource("classpath:socket.properties")
//@NoArgsConstructor
//public class SocketServer {
//
//    @Value("${port}")
//    private Integer port;
//    private boolean started;
//    private ServerSocket serverSocket;
//    private ExecutorService executorService = Executors.newCachedThreadPool();
//
//    public static void main(String[] args){
//        new SocketServer().start(8068);
//    }
//
//    public void start(){
//        start(null);
//    }
//    @Autowired
//    private UserService userService;//测试使用
//
//
//    public void start(Integer port){
////        log.info("port: {}, {}", this.port, port);
//        System.out.println("参数port");
//        System.out.println(port);
//        System.out.println("成员port");
//        System.out.println(this.port);
//        try {
//            serverSocket =  new ServerSocket(port == null ? this.port : port);
//            started = true;
//            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
//        } catch (IOException e) {
//            log.error("端口冲突,异常信息：{}", e);
//            System.exit(0);
//        }
//
//        while (started){
//            try {
//                Socket socket = serverSocket.accept();
//                socket.setKeepAlive(true);
//                ClientSocket register = register(socket);
//                log.info("客户端已连接，其Key值为：{}", register.getKey());
//                List<User> list = userService.queryEntityListAll();
//                SocketHandler.sendMessage(register, JSONUtil.toJson(list));
//                if (register != null){
//                    executorService.submit(register);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
