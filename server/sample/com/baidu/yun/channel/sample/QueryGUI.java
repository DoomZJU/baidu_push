package com.baidu.yun.channel.sample;

import java.util.List;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

import java.awt.*;
import java.awt.event.*;
import  javax.swing.*;


public class QueryGUI extends JFrame{
	private JLabel apiKeyLabel;
	private JLabel secretKeyLabel;
	private JLabel userIDLabel;
	private JLabel result;
	private JLabel result2;
	private JLabel result3;
	private JLabel result4;
	private JTextField apiKey;
	private JTextField secretKey;
	private JTextField userID;
	private JButton query;
	
	SimpleListener queryListener = new SimpleListener(); 
	
	public QueryGUI(){
			super();
			this.setSize(800, 400);
			this.getContentPane().setLayout(null);
			this.add(getJLabel(), null);
			this.add(getJLabel1(), null);
			this.add(getJLabel2(), null);
			this.add(getJLabel3(), null);
			this.add(getJLabel4(), null);
			this.add(getJLabel5(), null);
			this.add(getJLabel6(), null);
			this.add(getJTextField(), null);
			this.add(getJTextField1(), null);
			this.add(getJTextField2(), null);
			this.add(getJButton(), null);
			query.addActionListener(queryListener);
			this.setTitle("查询终端绑定信息");
			
	}

	private class SimpleListener implements ActionListener 
	{ 
	/* 
	 * 利用该内部类来监听所有事件源产生的事件 
	 * 便于处理事件代码模块化 
	 */ 
	    public void actionPerformed(ActionEvent e) 
	    { 
	    	// 1. 设置developer平台的ApiKey/SecretKey
	    	ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
	    	
	    	// 2. 创建BaiduChannelClient对象实例
	    	BaiduChannelClient channelClient = new BaiduChannelClient(pair);
	    	
	    	// 3. 若要了解交互细节，请注册YunLogHandler类
			channelClient.setChannelLogHandler(new YunLogHandler() {
				@Override
				public void onHandle(YunLogEvent event) {
					// TODO Auto-generated method stub
					System.out.println(event.getMessage());
				}
			});
			
			try {
				// 4. 创建请求类对象
				//		手机端的UserId， 先用1111111111111代替，用户需替换为自己的
				QueryBindListRequest request = new QueryBindListRequest();
				request.setUserId(userID.getText());
				
				// 5. 调用queryBindList接口
				QueryBindListResponse response = channelClient.queryBindList(request);
				
				// 6. 对返回的结果对象进行操作
				List<BindInfo> bindInfos = response.getBinds();
				for ( BindInfo bindInfo : bindInfos ) {
					long channelId = bindInfo.getChannelId();
					String userId = bindInfo.getUserId();
					int status = bindInfo.getBindStatus();
					//System.out.println("channel_id:" + channelId + ", user_id: " + userId + ", status: " + status);
					result.setText("channel_id:" + channelId + ", user_id: " + userId + ", status: " + status);
					
					String bindName = bindInfo.getBindName();
					long bindTime = bindInfo.getBindTime();
					String deviceId = bindInfo.getDeviceId();
					int deviceType = bindInfo.getDeviceType();
					long timestamp = bindInfo.getOnlineTimestamp();
					long expire = bindInfo.getOnlineExpires();
					
					//System.out.println("bind_name:" + bindName + "\t" + "bind_time:" + bindTime);
					result2.setText("bind_name:" + bindName + "\t" + "bind_time:" + bindTime);
					//System.out.println("device_type:" + deviceType + "\tdeviceId" + deviceId);
					result3.setText("device_type:" + deviceType + "\tdeviceId" + deviceId);
					//System.out.println(String.format("timestamp: %d, expire: %d", timestamp, expire));
					result4.setText(String.format("timestamp: %d, expire: %d", timestamp, expire));
				}
				
			} catch (ChannelClientException e1) {
				// 处理客户端错误异常
				e1.printStackTrace();
			} catch (ChannelServerException e2) {
				// 处理服务端错误异常
				System.out.println(
						String.format("request_id: %d, error_code: %d, error_message: %s" , 
							e2.getRequestId(), e2.getErrorCode(), e2.getErrorMsg()
							)
						);
			}
	    	
	    } 
	} 
	
	private javax.swing.JLabel getJLabel() {
		   if(apiKeyLabel == null) {
			   apiKeyLabel = new javax.swing.JLabel();
			   apiKeyLabel.setBounds(34, 49, 100, 18);
			   apiKeyLabel.setText("输入ApiKey:");
		   }
		   return apiKeyLabel;
		}
		
		private javax.swing.JLabel getJLabel1() {
			   if(secretKeyLabel == null) {
				   secretKeyLabel = new javax.swing.JLabel();
				   secretKeyLabel.setBounds(34, 72, 100, 18);
				   secretKeyLabel.setText("输入SecretKey:");
			   }
			   return secretKeyLabel;
			}
		
		private javax.swing.JLabel getJLabel2() {
			   if(userIDLabel == null) {
				   userIDLabel = new javax.swing.JLabel();
				   userIDLabel.setBounds(34, 95, 100, 18);
				   userIDLabel.setText("输入UserID:");
			   }
			   return userIDLabel;
			}
		
		private javax.swing.JLabel getJLabel3() {
			   if(result == null) {
				   result = new javax.swing.JLabel();
				   result.setBounds(300, 50, 400, 20);
			   }
			   return result;
			}
		
		private javax.swing.JLabel getJLabel4() {
			   if(result2 == null) {
				   result2 = new javax.swing.JLabel();
				   result2.setBounds(300, 100, 400, 20);
			   }
			   return result2;
			}
		
		private javax.swing.JLabel getJLabel5() {
			   if(result3 == null) {
				   result3 = new javax.swing.JLabel();
				   result3.setBounds(300, 150, 400, 20);
			   }
			   return result3;
			}
		
		private javax.swing.JLabel getJLabel6() {
			   if(result4 == null) {
				   result4 = new javax.swing.JLabel();
				   result4.setBounds(300, 200, 250, 20);
			   }
			   return result4;
			}
		
		private javax.swing.JTextField getJTextField() {
			   if(apiKey == null) {
				   apiKey = new javax.swing.JTextField();
				   apiKey.setBounds(125, 49, 160, 20);
			   }
			   return apiKey;
			}

			private javax.swing.JTextField getJTextField1() {
				   if(secretKey == null) {
					   secretKey = new javax.swing.JTextField();
					   secretKey.setBounds(125, 72, 160, 20);
				   }
				   return secretKey;
				}
			
			private javax.swing.JTextField getJTextField2() {
				   if(userID == null) {
					   userID = new javax.swing.JTextField();
					   userID.setBounds(125, 95, 160, 20);
				   }
				   return userID;
				}
			
			private javax.swing.JButton getJButton() {
				   if(query == null) {
					   query = new javax.swing.JButton();
					   query.setBounds(125, 150, 71, 27);
					   query.setText("查询");
				   }
				   return query;
				}
	
	public static void main(String[] args) {
		 QueryGUI w = new QueryGUI();
		  w.setVisible(true);

	}

}
