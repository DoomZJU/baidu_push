package com.baidu.yun.channel.sample;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class AndroidPushNotificationSample {

	public static void main(String[] args) {
		
		/*
		 * @brief	���͵���֪ͨ(Android Push SDK���ز�����)
		 * 			message_type = 1 (Ĭ��Ϊ0)
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "Cdt9aRwEn30XE7SetYnRW5Wk";
		String secretKey = "oNfPZy5yTH2VOWz834ObEN58Sphea5ZL";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			//		�ֻ��˵�ChannelId�� �ֻ��˵�UserId�� ����1111111111111���棬�û����滻Ϊ�Լ���
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
			request.setChannelId(4526054960620081363L);	
			request.setUserId("762603576330996665");	 
			
			request.setMessageType(1);
			request.setMessage("{\"title\":\"�����test\",\"description\":\"�յ�������Ϣ�������������\"}");
			
			// 5. ����pushMessage�ӿ�
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. ��֤���ͳɹ�
			System.out.println("push amount : " + response.getSuccessAmount()); 
			
		} catch (ChannelClientException e) {
			// �����ͻ��˴����쳣
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// ��������˴����쳣
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
}