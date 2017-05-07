import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JTextField joystickWindowSizeField;
	private JTextField joystickWindowPositionX;
	private JTextField joystickWindowPositionY;
	private JTextField ipField;
	private JTextField portField;
	private JButton applyButton;
	private Joystick joystick;
	private PositionSender positionSender;

	Joystick getJoystick() {
		return this.joystick;
	}

	void setJoystick(Joystick joystick) {
		this.joystick = joystick;
	}

	PositionSender getPositionSender() {
		return this.positionSender;
	}

	void setPositionSender(PositionSender positionSender) {
		this.positionSender = positionSender;
	}

	public Settings(Joystick joystick, PositionSender positionSender) {

		this.joystick = joystick;
		this.positionSender = positionSender;

		frame = new JFrame("Settings");
		frame.setSize(300, 300);
		frame.setLocation(400, 50);
		frame.getContentPane().setLayout(null);

		joystickWindowSizeField = new JTextField();
		joystickWindowSizeField.setBounds(95, 71, 86, 20);
		frame.getContentPane().add(joystickWindowSizeField);
		joystickWindowSizeField.setColumns(10);
		joystickWindowSizeField
				.setText(String.valueOf(joystick.getJoystickWindowSize()));

		joystickWindowPositionX = new JTextField();
		joystickWindowPositionX.setBounds(95, 102, 86, 20);
		frame.getContentPane().add(joystickWindowPositionX);
		joystickWindowPositionX.setColumns(10);
		joystickWindowPositionX
				.setText(String.valueOf(joystick.getJoystickWindowPositionX()));

		joystickWindowPositionY = new JTextField();
		joystickWindowPositionY.setBounds(95, 133, 86, 20);
		frame.getContentPane().add(joystickWindowPositionY);
		joystickWindowPositionY.setColumns(10);
		joystickWindowPositionY
				.setText(String.valueOf(joystick.getJoystickWindowPositionY()));

		ipField = new JTextField();
		ipField.setBounds(95, 164, 86, 20);
		frame.getContentPane().add(ipField);
		ipField.setColumns(10);
		ipField.setText(positionSender.getServerName());

		portField = new JTextField();
		portField.setBounds(95, 195, 86, 20);
		frame.getContentPane().add(portField);
		portField.setColumns(10);
		portField.setText(String.valueOf(positionSender.getPort()));

		JLabel lblNewLabel = new JLabel("Joystick size");
		lblNewLabel.setBounds(10, 74, 74, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblJoystickX = new JLabel("Joystick X");
		lblJoystickX.setBounds(10, 105, 75, 14);
		frame.getContentPane().add(lblJoystickX);

		JLabel lblJoystickY = new JLabel("Joystick Y");
		lblJoystickY.setBounds(10, 136, 75, 14);
		frame.getContentPane().add(lblJoystickY);

		JLabel lblCos = new JLabel("IP");
		lblCos.setBounds(10, 167, 75, 14);
		frame.getContentPane().add(lblCos);

		JLabel lblCos_1 = new JLabel("Port");
		lblCos_1.setBounds(10, 198, 75, 14);
		frame.getContentPane().add(lblCos_1);

		applyButton = new JButton("Apply");
		applyButton.setBounds(95, 239, 86, 23);
		frame.getContentPane().add(applyButton);
		applyButton.addMouseListener(new ApplyButtonMouseAdapter());

		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				frame.dispose();
			}
		});

	}

	class ApplyButtonMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			String getValue1 = joystickWindowSizeField.getText();
			String getValue2 = joystickWindowPositionX.getText();
			String getValue3 = joystickWindowPositionY.getText();
			String getValue4 = ipField.getText();
			String getValue5 = portField.getText();
			Joystick joystick = getJoystick();
			PositionSender positionSender = getPositionSender();

			joystick.setJoystickWindowParameters(Integer.parseInt(getValue1),
					Integer.parseInt(getValue2), Integer.parseInt(getValue3));
			joystick.setJoystickWindowUpperLimitX();
			joystick.setJoystickWindowUpperLimitY();
			setJoystickMiddle(joystick);

			positionSender.setServerName(getValue4);
			positionSender.setPort(Integer.parseInt(getValue5));
		}

		void setJoystickMiddle(Joystick joystick) {
			int middleX = Math.abs(joystick.getJoystickWindowUpperLimitX()
					+ joystick.getJoystickWindowPositionX()) / 2;
			int middleY = Math.abs(joystick.getJoystickWindowUpperLimitY()
					+ joystick.getJoystickWindowPositionY()) / 2;
			joystick.setPositionX(middleX);
			joystick.setPositionY(middleY);

			joystick.setOutPositionX(Math.abs(joystick.getPositionX()
					- joystick.getJoystickWindowPositionX()));
			joystick.setOutPositionY(Math.abs(joystick.getPositionY()
					- joystick.getJoystickWindowPositionY()));
			
		}

	}

}