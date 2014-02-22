package com.baidu.channel.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.ChannelMessage;
import com.baidu.yun.channel.model.FetchMessageRequest;
import com.baidu.yun.channel.model.FetchMessageResponse;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.VerifyBindRequest;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class ChannelClientTest {

	// n2WOyGXVgnBV31TIRYGdt11s/rno1pacvXpSjjf28EG39IEx4FweClLrm
//	private static MiniWebServer webServer = MiniWebServer.getInstance();

	@Test
	public void testQueryBindList() {
		
		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "NUEpCHhYNfTdbcFowp8KrNEC";
		String secretKey = "qCUoFWYf8L6TOGQpFLoDQuF9Xz2G69Nc";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			QueryBindListRequest request = new QueryBindListRequest();
			request.setUserId("1042317216157291926");
			
			// 5. 锟斤拷锟斤拷queryBindList锟接匡拷
			QueryBindListResponse response = channelClient.queryBindList(request);
			
			// 6. 锟皆凤拷锟截的斤拷锟斤拷锟斤拷锟斤拷胁锟斤拷锟�
			List<BindInfo> bindInfos = response.getBinds();
			for ( BindInfo bindInfo : bindInfos ) {
				long channelId = bindInfo.getChannelId();
				String userId = bindInfo.getUserId();
				int status = bindInfo.getBindStatus();
				System.out.println("channel_id:" + channelId + ", user_id: " + userId + ", status: " + status);
				
				String bindName = bindInfo.getBindName();
				long bindTime = bindInfo.getBindTime();
				String deviceId = bindInfo.getDeviceId();
				int deviceType = bindInfo.getDeviceType();
				long timestamp = bindInfo.getOnlineTimestamp();
				long expire = bindInfo.getOnlineExpires();
				
				System.out.println("bind_name:" + bindName + "\t" + "bind_time:" + bindTime);
				System.out.println("device_type:" + deviceType + "\tdeviceId" + deviceId);
				System.out.println(String.format("timestamp: %d, expire: %d", timestamp, expire));
				
			}
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
	@Test
	public void testPushUnicastMessage() {

		/*
		 * @brief	锟斤拷锟酵碉拷锟斤拷锟斤拷息(锟斤拷息锟斤拷锟斤拷为透锟斤拷锟斤拷锟缴匡拷锟斤拷锟斤拷应锟斤拷锟皆硷拷4锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷)
		 * 			message_type = 0 (默锟斤拷为0)
		 */
		
		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);
			request.setChannelId(4035698885061886729L);
			request.setUserId("1144280722819924199");
			
			request.setMessage("hello channel");
			
			// 5. 锟斤拷锟斤拷pushMessage锟接匡拷
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. 锟斤拷证锟斤拷锟酵成癸拷
			Assert.assertEquals(1, response.getSuccessAmount());
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}

	}

	@Test
	public void testPushUnicastAndroidNotification() {

		/*
		 * @brief	锟斤拷锟酵碉拷锟斤拷锟斤拷息(锟斤拷息锟斤拷锟斤拷为通知锟斤拷Android sdk service9锟斤拷锟斤拷息锟斤拷锟斤拷通知锟斤拷锟斤拷锟斤拷应锟矫ｏ拷 锟斤拷锟斤拷IOS锟斤拷锟斤拷)
		 * 			message_type = 1, device_type = 3
		 */
		
		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);
			request.setChannelId(4035698885061886729L);
			request.setUserId("814432199857187466");
			
			request.setMessageType(1);
			request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
			
			// 5. 锟斤拷锟斤拷pushMessage锟接匡拷
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. 锟斤拷证锟斤拷锟酵成癸拷
			Assert.assertEquals(1, response.getSuccessAmount());
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
	@Test
	public void testPushUnicastIosNotification() {

		/*
		 * @brief	锟斤拷锟酵碉拷锟斤拷锟斤拷息(锟斤拷息锟斤拷锟斤拷为通知锟斤拷IOS通锟斤拷APNS锟秸碉拷通知)
		 * 			message_type = 1, device_type = 4
		 */
		
		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(4);
//			request.setChannelId(4035698885061886729L);
			request.setUserId("814432199857187466");
			
			request.setMessageType(1);
			request.setMessage("{aps}");
			
			// 5. 锟斤拷锟斤拷pushMessage锟接匡拷
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. 锟斤拷证锟斤拷锟酵成癸拷
			Assert.assertEquals(1, response.getSuccessAmount());
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
	@Test
	public void testPushTagMessage() {

		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			PushTagMessageRequest request = new PushTagMessageRequest();
			request.setMessageType(0);
			request.setDeviceType(3);
			request.setTagName("锟斤拷锟斤拷锟斤拷要锟斤拷");
			request.setMessage("{\"title\":\"锟斤拷通知\",\"description\":\"锟斤拷锟斤拷要锟斤拷锟斤拷ddd锟斤拷锟斤拷锟劫度回碉拷锟斤拷锟角★拷\"}");
			
			// 5. 锟斤拷锟斤拷pushTagMessage锟接匡拷
			PushTagMessageResponse response = channelClient.pushTagMessage(request);
			if ( response.getSuccessAmount() == 1 ) {
				// TODO
			}
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}

	}
	
	@Test
	public void testPushBroadcastMessage() {

		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setMessageType(0);
			request.setDeviceType(3);
			request.setMessage("{\"title\":\"锟斤拷通知\",\"description\":\"锟斤拷锟斤拷要锟斤拷锟斤拷ddd锟斤拷锟斤拷锟劫度回碉拷锟斤拷锟角★拷\"}");
			
			// 5. 锟斤拷锟斤拷pushTagMessage锟接匡拷
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
			if ( response.getSuccessAmount() == 1 ) {
				// TODO
			}
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
	}
	
	@Test
	public void testBindVerify() {

		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			VerifyBindRequest request = new VerifyBindRequest();
			request.setChannelId(4035698885061886729L);
			request.setUserId("1144280722819924199");

			// 5. 锟斤拷锟斤拷verifyBind锟接匡拷
			channelClient.verifyBind(request);
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}

	}	
	
	// ----------------------------------------------------------------------------
	@Test
	public void testFetchMessage() {

		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			FetchMessageRequest request = new FetchMessageRequest();
			request.setUserId("1144280722819924199");
			
			// 5. 锟斤拷锟斤拷fetchMessage锟接匡拷
			FetchMessageResponse resp = channelClient.fetchMessage(request);
			List<ChannelMessage> messages = resp.getMessages();
			for ( ChannelMessage msg : messages ) {
				System.out.println(msg.getData());
			}
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}

	}
	
	
	@Test
	public void testInitIosCert() {

		/*
		 */
		
		// 1. 锟斤拷锟斤拷developer平台锟斤拷ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. 锟斤拷锟斤拷BaiduChannelClient锟斤拷锟斤拷实锟斤拷
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. 锟斤拷要锟剿解交锟斤拷细锟节ｏ拷锟斤拷注锟斤拷YunLogHandler锟斤拷
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			InitAppIoscertRequest request = new InitAppIoscertRequest();
			request.setName("name");
			request.setDescription("description");
			
			request.setDevCert("");
			request.setReleaseCert("");
			
			// 5. 锟斤拷锟斤拷initAppIoscert锟接匡拷
			channelClient.initAppIoscert(request);
				
			
		} catch (ChannelClientException e) {
			// 锟斤拷锟斤拷突锟斤拷舜锟斤拷锟斤拷斐�
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟届常
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
	
	
}
