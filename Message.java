package tisipiajpi;

public class Message {

	private int motorPosition;
	private int servoPosition;

	public Message(){
	}

	public Message(int motorPosition,int servoPosition){
		this.motorPosition=motorPosition;
		this.servoPosition=servoPosition;
	}


	public void setMotorPosition(int motorPosition){
		this.motorPosition=motorPosition;
	}
	public void setServoPosition(int servoPosition){
		this.servoPosition=servoPosition;
	}
	public int getMotorPosition(){
		return this.motorPosition;
	}
	public int getServoPosition(){
		return this.servoPosition;
	}

	@Override
	public String toString(){
		return "#"+String.format("%03d",this.servoPosition)+",$"+String.format("%03d",this.motorPosition)+"\n";
	}

}
