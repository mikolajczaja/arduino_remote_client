package tisipiajpi;

import tisipiajpi.Message;
import tisipiajpi.TCPClient;

public class PositionSender {

	private String serverName;
	private int port;


	PositionSender(String serverName,int port){
		this.serverName=serverName;
		this.port=port;
	}

	void setServerName(String serverName){
		this.serverName=serverName;
	}
	void setPort(int port){
		this.port=port;
	}	
	String getServerName(){
		return this.serverName;
	}
	int getPort(){
		return this.port;
	}



	public static void main(String[] args) throws InterruptedException {

		PositionSender positionSender= new PositionSender("192.168.4.1",333);
		Message message= new Message();
		TCPClient tcpClient= new TCPClient();
		Joystick joystick= new Joystick();


		joystick.show(150,50,200); //:TODO settery window param nie dzia³aj¹
		joystick.setOutPositionX(100);
		joystick.setOutPositionY(100);
		//INFO main message sending loop

		
		while(joystick.getStatus()){

			
			//:INFO inverty teraz dzialaja poprawnie
			int invertedX=joystick.invertPositionX(joystick.getPositionX());
			int invertedY=joystick.invertPositionY(joystick.getPositionY());

			message.setMotorPosition(joystick.scalePositionY(joystick.getPositionY(),150,30));
			message.setServoPosition(joystick.scalePositionX(joystick.getPositionX(),180,0));


			int testX=message.getServoPosition();
			if(testX>99){
				testX=testX+100;
				message.setServoPosition(testX);
			}

			int testY=message.getMotorPosition();
			if(testY>99){
				testY=testY+100;
				message.setMotorPosition(testY);
			}

			tcpClient.send(positionSender.getServerName(),positionSender.getPort(),message);

			System.out.println(message);
			Thread.sleep(100); //INFO Sleep here
		}	
	}

}