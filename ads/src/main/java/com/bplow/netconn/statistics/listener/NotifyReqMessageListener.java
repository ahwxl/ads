package com.bplow.netconn.statistics.listener;

/*import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bplow.netconn.statistics.domain.CustomerReq;
import com.bplow.netconn.statistics.service.StatisDayService;

/**
 * 处理消息
 * @author qian
 *
 */
public class NotifyReqMessageListener /*implements MessageListener*/{

	private static Logger logger = LoggerFactory.getLogger(NotifyReqMessageListener.class);
	
	private StatisDayService statisDayService;//消息处理
	
	/*@Override
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String cnidx  =  mapMessage.getString("extNo");
			String mediaId = mapMessage.getString("mediaId");//媒体id
			String adNo   =  mapMessage.getString("adNo");; //广告id
			String sessionId =  mapMessage.getString("sessionId");
			String refUrl     = mapMessage.getString("refUrl");;
			String reqUrl    =  mapMessage.getString("reqUrl");
			CustomerReq cusreq = new CustomerReq(mediaId,  adNo, reqUrl,
					 refUrl, cnidx,  sessionId);
			
			statisDayService.statictis(cusreq);

			// 打印消息详情
			logger.info("UserName:{}, Email:{}, ObjectType:{}", mapMessage.getString("userName"),
					mapMessage.getString("email"), mapMessage.getStringProperty("objectType"));
		} catch (Exception e) {
			logger.error("处理消息时发生异常.", e);
		}
		
	}*/

	public void setStatisDayService(StatisDayService statisDayService) {
		this.statisDayService = statisDayService;
	}

	
}
