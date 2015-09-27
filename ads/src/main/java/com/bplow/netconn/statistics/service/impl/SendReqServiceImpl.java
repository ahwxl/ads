package com.bplow.netconn.statistics.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.bplow.netconn.statistics.domain.CustomerReq;
import com.bplow.netconn.statistics.service.SendReqService;

/**
 * 发送消息 服务
 * @author qian
 *
 */
@Service
public class SendReqServiceImpl implements SendReqService{

	@Autowired
	@Qualifier("advancedJmsTemplate")
	private JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier("advancedNotifyQueue")
	private Destination notifyQueue;
	
	@Override
	public void sendMessage(final CustomerReq req) {
		jmsTemplate.send(notifyQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {

				MapMessage message = session.createMapMessage();
				message.setString("mediaId", req.getMediaId());
				message.setString("adNo", req.getAdNo());
				message.setString("reqUrl", req.getReqUrl());
				message.setString("refUrl", req.getRefUrl());
				message.setString("extNo", req.getExtNo());
				message.setString("sessionId", req.getSessionId());

				return message;
			}
		});

	}

}
