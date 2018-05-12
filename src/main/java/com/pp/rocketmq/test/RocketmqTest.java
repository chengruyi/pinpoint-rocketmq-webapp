package com.pp.rocketmq.test;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by cry@meitu.com
 * Date: 2018/5/12
 * Description:
 */
public class RocketmqTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DefaultMQProducer defaultMQProducer = new
                DefaultMQProducer("please_rename_unique_group_name");
        //2.设置命名服务的地址,默认是去读取conf文件下的配置文件 rocketmq.namesrv.addr
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //Launch the instance.
        SendResult sendResult = null;
        try {
            defaultMQProducer.start();
            for (int i = 0; i < 100; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " +
                                i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                sendResult = defaultMQProducer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            System.out.printf("fail send message" + e);
        }
        resp.getOutputStream().write(sendResult.toString().getBytes());
    }
}
