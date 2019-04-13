package com.yychatclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychatclient.view.FriendChat1;
import com.yychatclient.view.FriendList;

public class ClientReceiverThread extends Thread{

	private Socket s;
	
	public ClientReceiverThread(Socket s){
		this.s=s;
	}
	public void run(){
		ObjectInputStream ois;
		while(true){
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message mess=(Message)ois.readObject();//����������Ϣ,�߳�����
				String showMessage=mess.getSender()+"��"+mess.getReceiver()+"˵��"+mess.getContent();
				System.out.println(showMessage);
				//jta.append(showMessage+"\r\n");
				//�ں��ѽ���FriendChat1����ʾ������Ϣ
				//1������õ�����������棬˼·���������������棬
				FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());//���ǽ�����+��������
								
				//2������ʾ��Ϣ
				friendChat1.appendJta(showMessage);
				
				//��3�����ͻ��˽��շ����������������ߺ�����Ϣ��Ȼ�����ø���Ϣ�������ߺ��ѵ�ͼ��
				if(mess.getMessageType().equals(Message.message_OnlineFriend)){
					
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
	}
}
