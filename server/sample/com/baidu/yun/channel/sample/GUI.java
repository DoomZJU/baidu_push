package com.baidu.yun.channel.sample;

import java.awt.*;
import java.awt.event.*;

import  javax.swing.*;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class GUI extends JFrame {

		private JLabel apiKeyLabel;
		private JLabel secretKeyLabel;
		private JLabel userIDLabel;
		private JLabel channelIDLabel;
		private JLabel result;
		private JLabel messageLabel;
		private JLabel modLabel;
		private JLabel userGroupLabel;
		private JTextField apiKey;
		private JTextField secretKey;
		private JTextField userID;
		private JTextField channelID;
		private JTextField message;
		private JTextField tag;
		private JButton send;
		private JComboBox<String> selectMod;
		private static int flag = 0,more = 0;
		
		SimpleListener sendListener = new SimpleListener(); 
		SimpleListener1 selectListener = new SimpleListener1(); 
		
		private String[] mod = {"广播通知","标签消息","广播消息","单播消息"}; 
		
		public GUI(){
			super();
			this.setSize(500, 400);
			this.getContentPane().setLayout(null);
			this.add(getJLabel(), null);
			this.add(getJLabel1(), null);
			this.add(getJLabel2(), null);
			this.add(getJLabel3(), null);
			this.add(getJLabel4(), null);
			this.add(getJLabel5(), null);
			this.add(getJLabel6(), null);
			this.add(getJLabel7(), null);
			this.add(getJTextField(), null);
			this.add(getJTextField1(), null);
			this.add(getJTextField2(), null);
			this.add(getJTextField3(), null);
			this.add(getJTextField4(), null);
			this.add(getJTextField5(), null);
			this.add(getJButton(), null);
			this.add(getJComboBox(),null);
			
			JRadioButton broadCast = new JRadioButton("所有人");
			broadCast.setBounds(150,187,160,20);
			broadCast.setSelected(true);
			JRadioButton tag = new JRadioButton("特定标签");
			tag.setBounds(150,210,80,20);
			ButtonGroup bg = new ButtonGroup();
			bg.add(broadCast);
			bg.add(tag);
			broadCast.addItemListener(new ItemListener(){

				  public void itemStateChanged(ItemEvent e) {
				   // TODO Auto-generated method stub
				 
				   JRadioButton jop=(JRadioButton) e.getSource();
				   if (jop.isSelected()){
					   more = 0;
				   }
				   else {
					   more = 1;
				   }
				   	 
				  }
			});
			
			this.add(broadCast);
			this.add(tag);
			
			send.addActionListener(sendListener);
			selectMod.addActionListener(selectListener);
			this.setTitle("CPS系统移动终端数据推送演示程序");
			
	}
		
		private class SimpleListener implements ActionListener 
		{ 
		/* 
		 * 利用该内部类来监听所有事件源产生的事件 
		 * 便于处理事件代码模块化 
		 */ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		    	switch(flag){
		    		case 0:		{
		    			// 1. 设置developer平台的ApiKey/SecretKey
		    			ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
		    			
		    			// 2. 创建BaiduChannelClient对象实例
		    			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		    			
		    			// 3. 若要了解交互细节，请注册YunLogHandler类
		    			channelClient.setChannelLogHandler(new YunLogHandler() {
		    				@Override
		    				public void onHandle(YunLogEvent event) {
		    					System.out.println(event.getMessage());
		    				}
		    			});
		    			
		    			try {
		    				
		    				// 4. 创建请求类对象
		    				//		手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
		    				PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		    				request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
		    				request.setChannelId(Long.parseLong(channelID.getText()));	
		    				request.setUserId(userID.getText());	 
		    				
		    				request.setMessageType(1);
		    				request.setMessage("{\"title\":\"李东轩的test\",\"description\":\"收到这条信息则代表调试正常\"}");
		    				
		    				// 5. 调用pushMessage接口
		    				PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
		    					
		    				// 6. 认证推送成功
		    				System.out.println("push amount : " + response.getSuccessAmount()); 
		    				
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
		    			break;
		    		}
		    		case 1:		{
		    			
		    			// 1. 设置developer平台的ApiKey/SecretKey
		    			ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
		    			
		    			// 2. 创建BaiduChannelClient对象实例
		    			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		    			
		    			// 3. 若要了解交互细节，请注册YunLogHandler类
		    			channelClient.setChannelLogHandler(new YunLogHandler() {
		    				@Override
		    				public void onHandle(YunLogEvent event) {
		    					System.out.println(event.getMessage());
		    				}
		    			});
		    			
		    			try {
		    				
		    				// 4. 创建请求类对象
		    				//		手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
		    				PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		    				request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
		    				request.setChannelId( Long.parseLong(channelID.getText()));	
		    				request.setUserId(userID.getText());	 
		    				
		    				request.setMessageType(1);
		    				request.setMessage("{\"title\":\"李东轩的test\",\"description\":\"收到这条信息则代表调试正常\"}");
		    				
		    				// 5. 调用pushMessage接口
		    				PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
		    					
		    				// 6. 认证推送成功
		    				System.out.println("push amount : " + response.getSuccessAmount()); 
		    				
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
		    			break;
		    		}
		    		
		    		case 2:		{
		    			// 1. 设置developer平台的ApiKey/SecretKey
		    			ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
		    			
		    			// 2. 创建BaiduChannelClient对象实例
		    			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		    			
		    			// 3. 若要了解交互细节，请注册YunLogHandler类
		    			channelClient.setChannelLogHandler(new YunLogHandler() {
		    				@Override
		    				public void onHandle(YunLogEvent event) {
		    					System.out.println(event.getMessage());
		    				}
		    			});
		    			
		    			try {
		    				
		    				// 4. 创建请求类对象
		    				PushTagMessageRequest request = new PushTagMessageRequest();
		    				request.setDeviceType(3); 	// device_type => 1: web 2: pc 3:android 4:ios 5:wp	
		    				request.setTagName(tag.getText());
		    				request.setMessage(message.getText());
		    				// 若要通知，
		    				//			request.setMessageType(1);
		    				//			request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
		    				
		    				// 5. 调用pushMessage接口
		    				PushTagMessageResponse response = channelClient.pushTagMessage(request);
		    					
		    				// 6. 认证推送成功
		    				System.out.println("push amount : " + response.getSuccessAmount()); 
		    				
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
			    		break;
		    		}
		    		case 3:		{
		    			// 1. 设置developer平台的ApiKey/SecretKey
		    			ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
		    			
		    			// 2. 创建BaiduChannelClient对象实例
		    			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		    			
		    			// 3. 若要了解交互细节，请注册YunLogHandler类
		    			channelClient.setChannelLogHandler(new YunLogHandler() {
		    				@Override
		    				public void onHandle(YunLogEvent event) {
		    					System.out.println(event.getMessage());
		    				}
		    			});
		    			
		    			try {
		    				
		    				// 4. 创建请求类对象
		    				PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
		    				request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp	
		    				
		    				request.setMessage(message.getText());
		    				// 若要通知，
		    				//			request.setMessageType(1);
		    				//			request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
		    	 			
		    				// 5. 调用pushMessage接口
		    				PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
		    					
		    				// 6. 认证推送成功
		    				System.out.println("push amount : " + response.getSuccessAmount()); 
		    				
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
		    			break;
		    		}
		    		case 4:		{
		    			// 1. 设置developer平台的ApiKey/SecretKey
		    			ChannelKeyPair pair = new ChannelKeyPair(apiKey.getText(), secretKey.getText());
		    			
		    			// 2. 创建BaiduChannelClient对象实例
		    			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		    			
		    			// 3. 若要了解交互细节，请注册YunLogHandler类
		    			channelClient.setChannelLogHandler(new YunLogHandler() {
		    				@Override
		    				public void onHandle(YunLogEvent event) {
		    					System.out.println(event.getMessage());
		    				}
		    			});
		    			
		    			try {
		    				
		    				// 4. 创建请求类对象
		    				//		手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
		    				PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		    				request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
		    				request.setChannelId(Long.parseLong(channelID.getText()));	
		    				request.setUserId(userID.getText());	 
		    				
		    				request.setMessage(message.getText());
		    				
		    				// 5. 调用pushMessage接口
		    				PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
		    					
		    				// 6. 认证推送成功
		    				System.out.println("push amount : " + response.getSuccessAmount()); 
		    				
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
		    			break;
		    		}
		    		
		    	}
		    }
		}
	
		private class SimpleListener1 implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
		    { 
		    	int i = selectMod.getSelectedIndex()+1;
		    	switch(i){
				    	case 1:		flag = 1;
				    					break;
				    	case 2:		flag = 2;
				    					break;
				    	case 3:		flag = 3;
				    					break;
				    	case 4:		flag = 4;
				    					break;
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
			   if(channelIDLabel == null) {
				   channelIDLabel = new javax.swing.JLabel();
				   channelIDLabel.setBounds(34, 118, 100, 18);
				   channelIDLabel.setText("输入ChannelID:");
			   }
			   return channelIDLabel;
			}
		
		private javax.swing.JLabel getJLabel4() {
			   if(messageLabel == null) {
				   messageLabel = new javax.swing.JLabel();
				   messageLabel.setBounds(34, 141, 100, 18);
				   messageLabel.setText("输入信息:");
			   }
			   return messageLabel;
			}
		
		private javax.swing.JLabel getJLabel5() {
			   if(result == null) {
				   result = new javax.swing.JLabel();
				   result.setBounds(300, 100, 400, 20);
			   }
			   return result;
			}
		
		private javax.swing.JLabel getJLabel6() {
			   if(modLabel == null) {
				   modLabel = new javax.swing.JLabel();
				   modLabel.setBounds(34, 164, 100, 18);
				   modLabel.setText("选择消息类型:");
			   }
			   return modLabel;
			}
		
		private javax.swing.JLabel getJLabel7() {
			   if(userGroupLabel == null) {
				   userGroupLabel = new javax.swing.JLabel();
				   userGroupLabel.setBounds(34, 187, 100, 18);
				   userGroupLabel.setText("选择用户群组:");
			   }
			   return userGroupLabel;
			}
		
		private javax.swing.JTextField getJTextField() {
			   if(apiKey == null) {
				   apiKey = new javax.swing.JTextField();
				   apiKey.setBounds(150, 49, 160, 20);
			   }
			   return apiKey;
			}
		
		private javax.swing.JTextField getJTextField1() {
			   if(secretKey == null) {
				   secretKey = new javax.swing.JTextField();
				   secretKey.setBounds(150, 72, 160, 20);
			   }
			   return secretKey;
			}
		
		private javax.swing.JTextField getJTextField2() {
			   if(userID == null) {
				   userID = new javax.swing.JTextField();
				   userID.setBounds(150, 95, 160, 20);
			   }
			   return userID;
			}
		
		private javax.swing.JTextField getJTextField3() {
			   if(channelID == null) {
				   channelID = new javax.swing.JTextField();
				   channelID.setBounds(150, 118, 160, 20);
			   }
			   return channelID;
			}
		
		private javax.swing.JTextField getJTextField4() {
			   if(message == null) {
				   message = new javax.swing.JTextField();
				   message.setBounds(150, 141, 160, 20);
			   }
			   return message;
			}
		
		private javax.swing.JTextField getJTextField5() {
			   if(tag == null) {
				   tag = new javax.swing.JTextField();
				   tag.setBounds(235, 210, 160, 20);
			   }
			   return tag;
			}
		
		private javax.swing.JButton getJButton() {
			   if(send == null) {
				   send = new javax.swing.JButton();
				   send.setBounds(125, 240, 71, 27);
				   send.setText("发送");
			   }
			   return send;
			}
		
		private javax.swing.JComboBox<String> getJComboBox() {
			   if(selectMod == null) {
				   selectMod = new JComboBox(mod);
				   selectMod.setBounds(150,164,160,20);
				   selectMod.setSelectedIndex(3);
			   }
			   return selectMod;
			}
		
	public static void main(String[] args) {
		GUI w = new GUI();
		 w.setVisible(true);

	}

}
