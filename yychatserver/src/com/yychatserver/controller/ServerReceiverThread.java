package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{//���븴��
	Socket s;
	public ServerReceiverThread(Socket s){//s���뷢�������Ӧ�ķ�����Socket����
		this.s=s;
	}
	
	public void run(){
		ObjectInputStream ois;
		ObjectOutputStream oos;
		Message mess;
		while (true) {
			try {
				ois = new ObjectInputStream(s.getInputStream());
				mess=(Message)ois.readObject();//����������Ϣ������
				System.out.println(mess.getSender()+"��"+mess.getReceiver()+"˵��"+mess.getContent());
				
				if(mess.getMessageType().equals(Message.message_Common)){
					Socket s1=(Socket)StartServer.hmSocket.get(mess.getReceiver());//�õ�������������Ӧ�ķ�����Socket����
					oos=new ObjectOutputStream(s1.getOutputStream());
					oos.writeObject(mess);//ת��������Ϣ
				}
				
				//��2�������������յ�������������ߺ�����Ϣ(���ͣ�message_OnlineFriend)
				if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
					
				}
				
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}
