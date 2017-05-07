public class PositionSender {

	private String serverName;
	private int port;

	PositionSender(String serverName, int port) {
		this.serverName = serverName;
		this.port = port;
	}

	void setServerName(String serverName) {
		this.serverName = serverName;
	}

	void setPort(int port) {
		this.port = port;
	}

	String getServerName() {
		return this.serverName;
	}

	int getPort() {
		return this.port;
	}

	public static void main(String[] args) throws InterruptedException {

		PositionSender positionSender = new PositionSender("192.168.4.1", 333);
		Message message = new Message();
		TCPClient tcpClient = new TCPClient();
		Joystick joystick = new Joystick();

		joystick.show(150, 50, 200);

		// INFO main message sending loop
		while (joystick.getStatus()) {

			if (message.getAction() == 'H')
				message.setAction('x');

			if (joystick.getLightsChange() == true) {
				message.setAction('H');
				joystick.setLightsChange(false);
			}

			if (joystick.getSettingsChange() == true) {
				Settings settings = new Settings(joystick, positionSender);
				joystick.setSettingsChange(false);
			}

			int invertedX = joystick.invertPositionX(joystick.getPositionX());
			int invertedY = joystick.invertPositionY(joystick.getPositionY());

			message.setMotorPosition(
					joystick.scalePositionY(joystick.getPositionY(), 0, 180));
			message.setServoPosition(joystick.scalePositionX(invertedX, 140, 40));

			// :TODO czy ponizsze jest na pewno konieczne?
			int testX = message.getServoPosition();
			if (testX > 99) {
				testX = testX + 100;
				message.setServoPosition(testX);
			}

			int testY = message.getMotorPosition();
			if (testY > 99) {
				testY = testY + 100;
				message.setMotorPosition(testY);
			}

			//tcpClient.send(positionSender.getServerName(), positionSender.getPort(),message);

			System.out.println(message);
			Thread.sleep(50); // INFO Sleep here
		}
	}

}