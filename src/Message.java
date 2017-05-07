public class Message {

	private int motorPosition;
	private int servoPosition;
	private char action='x';
	
	public Message(){
	}

	public Message(int motorPosition,int servoPosition){
		this.motorPosition=motorPosition;
		this.servoPosition=servoPosition;
	}


	void setMotorPosition(int motorPosition){
		this.motorPosition=motorPosition;
	}
	void setServoPosition(int servoPosition){
		this.servoPosition=servoPosition;
	}
	void setAction(char action){
		this.action=action;
	}
	int getMotorPosition(){
		return this.motorPosition;
	}
	int getServoPosition(){
		return this.servoPosition;
	}
	char getAction(){
		return this.action;
	}
	
	void actionHeadlights(){
		if(this.action!='H')this.action='H';
		else this.action='x';
	}

	@Override
	public String toString(){
		return "#"+String.format("%03d",this.servoPosition)+"$"+String.format("%03d",this.motorPosition)+this.action+"\n";
	} //:TODO zmiany linii

}
