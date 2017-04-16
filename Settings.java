package tisipiajpi;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings extends JPanel{

	private static final long serialVersionUID = 1L;

	private JFrame frame; //TODO setup tego frejma
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;

	//TODO ta klasa jako nowe okno z testmouse	
	public Settings(Joystick joystick) {
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(143, 145, 86, 20);
		add(textField);		
		//TODO setery do tego podpiac jak ponizej chyba
		//TODO przetestuj czy to dziala w ogole
		joystick.setJoystickWindowSize(Integer.parseInt(textField.getText()));
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(143, 176, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(143, 207, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(143, 238, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(143, 269, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel = new JLabel("Joystick size");
		lblNewLabel.setBounds(68, 148, 65, 14);
		add(lblNewLabel);

		JLabel lblJoystickX = new JLabel("Joystick X");
		lblJoystickX.setBounds(68, 179, 65, 14);
		add(lblJoystickX);

		JLabel lblJoystickY = new JLabel("Joystick Y");
		lblJoystickY.setBounds(68, 210, 65, 14);
		add(lblJoystickY);

		JLabel lblCos = new JLabel("IP");
		lblCos.setBounds(68, 241, 65, 14);
		add(lblCos);

		JLabel lblCos_1 = new JLabel("Port");
		lblCos_1.setBounds(68, 272, 65, 14);
		add(lblCos_1);

		btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				Joystick test=new Joystick();
				test.show();
			}

		});
		btnNewButton.setBounds(20, 20, 80, 40);
		add(btnNewButton);



		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				//setStatus(false);
				System.exit(0);
			}
		});

	}
	public static void main(String[] args) {
		Joystick test= new Joystick();
		Settings set= new Settings(test);
	}

}
