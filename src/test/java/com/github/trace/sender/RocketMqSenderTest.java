package com.github.trace.sender;

import com.alibaba.rocketmq.common.message.Message;
import org.junit.Test;

/**
 * 测试MQ
 * Created by lirui on 2015-10-28 14:33.
 */
public class RocketMqSenderTest {
  @Test
  public void testSend() throws Exception {
    RocketMqSender.getInstance().doSend(new Message("test", "test".getBytes()));
  }
}
