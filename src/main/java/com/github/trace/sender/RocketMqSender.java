package com.github.trace.sender;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.message.Message;
import com.github.autoconf.ConfigFactory;
import com.github.autoconf.api.IChangeListener;
import com.github.autoconf.api.IConfig;
import com.github.trace.NamedThreadFactory;
import com.google.common.base.Strings;
import com.google.common.collect.Queues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发送消息到RocketMQ
 * Created by lirui on 2015-10-14 18:52.
 */
public class RocketMqSender implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(RocketMqSender.class);
  private static final RocketMqSender INSTANCE = new RocketMqSender();
  private final BlockingQueue<Message> queue = Queues.newArrayBlockingQueue(5000);
  private ExecutorService executor;
  private DefaultMQProducer sender;
  private boolean running;

  private RocketMqSender() {
    NamedThreadFactory factory = new NamedThreadFactory("rocketmq-sender", true);
    executor = Executors.newSingleThreadExecutor(factory);
    executor.submit(this);
    ConfigFactory.getInstance().getConfig("rocketmq-sender", new IChangeListener() {
      @Override
      public void changed(IConfig config) {
        reload(config);
      }
    });

    //增加退出回调功能
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        running = false;
        executor.shutdown();
        if (sender != null) {
          sender.shutdown();
        }
      }
    }));
  }

  public static RocketMqSender getInstance() {
    return INSTANCE;
  }

  private void reload(IConfig config) {
    String nameSrv = config.get("NAMESRV_ADDR");
    if (Strings.isNullOrEmpty(nameSrv)) {
      LOG.error("cannot find NAMESRV_ADDR in {}", config.getName());
      return;
    }
    System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, nameSrv);
    if (sender == null) {
      sender = new DefaultMQProducer("OssProducer");
      sender.setNamesrvAddr(nameSrv);
      try {
        sender.start();
      } catch (MQClientException e) {
        LOG.error("cannot start trace-producer", e);
      }
    } else {
      sender.setNamesrvAddr(nameSrv);
      sender.getDefaultMQProducerImpl().getmQClientFactory().getDefaultMQProducer().setNamesrvAddr(nameSrv);
      sender.getDefaultMQProducerImpl().getmQClientFactory().getMQClientAPIImpl().updateNameServerAddressList(nameSrv);
    }
  }

  void doSend(Message msg) {
    SendResult sendResult = null;
    try {
      sendResult = sender.send(msg);
    } catch (Exception e) {
      LOG.error("Send error, {}", msg, e);
      e.printStackTrace();
    }
    if (sendResult == null) {
      LOG.error("sendResult=null");
    } else {
      SendStatus status = sendResult.getSendStatus();
      if (status.equals(SendStatus.SEND_OK)) {
        LOG.debug("msgId={}, status={}", sendResult.getMsgId(), status);
      } else {
        LOG.error("msgId={}, status={}", sendResult.getMsgId(), status);
      }
    }
  }

  @Override
  public void run() {
    LOG.info("{} started", Thread.currentThread().getName());
    running = true;
    while (running) {
      Message msg;
      try {
        msg = queue.take();
      } catch (InterruptedException e) {
        LOG.error("interrupted, now exit");
        return;
      }
      if (msg == null || sender == null) {
        continue;
      }
      doSend(msg);
    }
  }

  public void asyncSend(Message m) {
    if (sender != null) {
      queue.offer(m);
    }
  }
}
