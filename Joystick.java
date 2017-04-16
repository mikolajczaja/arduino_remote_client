package tisipiajpi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Canvas;

public class Joystick extends JPanel{

	private static final long serialVersionUID = 1L;

	//TODO nazwij ta klase jakos sensownie

	private JFrame frame;
	private JTextField statusField;
	private JButton btnNewButton;

	private int outPositionX;
	private int outPositionY;

	private int positionX=0;
	private int positionY=0;
	private int joystickWindowSize;
	private int joystickWindowPositionX;
	private int joystickWindowPositionY;

	//:TODO to przenies do konstruktora okna (samo przypisanie, bez deklaracji)
	private int joystickWindowUpperLimitX=joystickWindowPositionX+joystickWindowSize;
	private int joystickWindowUpperLimitY=joystickWindowPositionY+joystickWindowSize;


	private boolean status=true; //INFO: true-okno otwarte

	public int getJoystickWindowSize(){
		return this.joystickWindowSize;
	}
	public int getJoystickWindowPositionX(){
		return this.joystickWindowPositionX;
	}
	public int getJoystickWindowPositionY(){
		return this.joystickWindowPositionY;
	}
	public int getJoystickWindowUpperLimitX(){
		return this.joystickWindowUpperLimitX;
	}
	public int getJoystickWindowUpperLimitY(){
		return this.joystickWindowUpperLimitY;
	}

	public void setJoystickWindowSize(int joystickWindowSize){
		this.joystickWindowSize=joystickWindowSize;
	}
	public void setJoystickWindowPositionY(int joystickWindowPositionY){
		this.joystickWindowPositionY=joystickWindowPositionY;
	}
	public void setJoystickWindowPositionX(int joystickWindowPositionX){
		this.joystickWindowPositionX=joystickWindowPositionX;
	}
	public void setJoystickWindowUpperLimitX(){
		this.joystickWindowUpperLimitX=
				this.joystickWindowPositionX+this.joystickWindowSize;
	}
	public void setJoystickWindowUpperLimitY(){
		this.joystickWindowUpperLimitY=
				this.joystickWindowPositionY+this.joystickWindowSize;
	}

	public void setJoystickWindowParameters(int joystickWindowSize,
			int joystickWindowPositionX,int joystickWindowPositionY){

		setJoystickWindowSize(joystickWindowSize);
		setJoystickWindowPositionX(joystickWindowPositionX);
		setJoystickWindowPositionY(joystickWindowPositionY);
		setJoystickWindowUpperLimitX();
		setJoystickWindowUpperLimitY();
	}


	public int getPositionX(){
		return this.positionX;
	}
	public int getPositionY(){
		return this.positionY;
	}
	public int getOutPositionX(){
		return this.outPositionX;
	}
	public int getOutPositionY(){
		return this.outPositionY;
	}
	boolean getStatus(){
		return this.status;
	}

	void setPositionX(int positionX){
		this.positionX=positionX;
	}
	void setPositionY(int positionY){
		this.positionY=positionY;
	}
	void setOutPositionX(int outPositionX){
		this.outPositionX=outPositionX;
	}
	void setOutPositionY(int outPositionY){
		this.outPositionY=outPositionY;
	}
	void setStatus(boolean status){
		this.status=status;
	}

	void printAllParameters(){
		System.out.println("x:"+this.joystickWindowPositionX+" , "+this.joystickWindowUpperLimitX+
				" ;y: "+this.joystickWindowPositionY+" , "+this.joystickWindowUpperLimitY+" ;size: "+
				this.joystickWindowSize);
	}

	//INFO default Testmouse constructor
	public Joystick(){

		//Show(250,200);
	}


	//INFO main graphic interface method
	void show(int joystickWindowPositionX,int joystickWindowPositionY,
			int joystickWindowSize) throws InterruptedException{

		setJoystickWindowParameters(joystickWindowSize,
				joystickWindowPositionX,joystickWindowPositionY);

		printAllParameters();

		frame= new JFrame("bla");
		frame.setSize(500,300);
		frame.getContentPane().setLayout(null);

		statusField = new JTextField();
		statusField.setBounds(10, 237, 134, 25);
		statusField.setEditable(false);
		frame.getContentPane().add(statusField);
		statusField.setColumns(10);

		statusField.setText(positionX+" , "+positionY);
		JLabel lblMousePosition = new JLabel("x,y");
		lblMousePosition.setBounds(10, 221, 110, 14);
		frame.getContentPane().add(lblMousePosition);

		btnNewButton = new JButton("Settings");
		btnNewButton.setBounds(10, 20, 120, 20);
		frame.getContentPane().add(btnNewButton);

		MyCanvas canvas = new MyCanvas();
		frame.add(canvas);
		canvas.addMouseListener(new MyMouseAdapter());
		canvas.addMouseMotionListener(new MyMouseAdapter());



		//btnNewButton.setVisible(true);
		frame.setVisible(true);
		//repaint();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				setStatus(false);
				System.exit(0);
			}
		});



	}


	int invertPositionX(int positionX){
		int invertedPositionX=getJoystickWindowUpperLimitX()-(positionX-getJoystickWindowPositionX());
		return invertedPositionX;
	}

	int invertPositionY(int positionY){
		int invertedPositionY=getJoystickWindowUpperLimitY()-(positionY-getJoystickWindowPositionY());
		return invertedPositionY;
	}



	int scalePositionX(int position,int upperLimit,int lowerLimit){
		
		float scaledPosition=0;
		float diffUpDown=upperLimit-lowerLimit;
		float diffLimits=getJoystickWindowUpperLimitX()-getJoystickWindowPositionX();
		float scale=diffUpDown/diffLimits;
		position=position-getJoystickWindowPositionX();

		scaledPosition=lowerLimit+scale*position;

		return (int)scaledPosition;
	}

	int scalePositionY(int position,int upperLimit,int lowerLimit){
		
		float scaledPosition=0;
		float diffUpDown=upperLimit-lowerLimit;	
		float diffLimits=getJoystickWindowUpperLimitY()-getJoystickWindowPositionY();
		float scale=diffUpDown/diffLimits;
		position=position-getJoystickWindowPositionY();

		scaledPosition=lowerLimit+scale*position;

		return (int)scaledPosition;
	}





	public class MyCanvas extends Canvas {

		private static final long serialVersionUID = 1L;

		public MyCanvas () {
			setSize(500,300);
		}


		//INFO line and joystick thumb drawing method
		public void paint (Graphics g) {
			int radius=20;

			Graphics2D g2d = (Graphics2D) g;

			int joystickWindowPositionX2=joystickWindowPositionX-radius;
			int joystickWindowPositionY2=joystickWindowPositionY-radius;
			int joystickWindowUpperLimitX2=joystickWindowUpperLimitX+radius;
			int joystickWindowUpperLimitY2=joystickWindowUpperLimitY+radius;

			g2d.drawLine(joystickWindowPositionX2,joystickWindowPositionY2,
					joystickWindowPositionX2,joystickWindowUpperLimitY2);
			g2d.drawLine(joystickWindowPositionX2,joystickWindowUpperLimitY2,
					joystickWindowUpperLimitX2,joystickWindowUpperLimitY2);
			g2d.drawLine(joystickWindowUpperLimitX2,joystickWindowUpperLimitY2,
					joystickWindowUpperLimitX2,joystickWindowPositionY2);
			g2d.drawLine(joystickWindowUpperLimitX2,joystickWindowPositionY2,
					joystickWindowPositionX2,joystickWindowPositionY2);

			g2d.drawLine(joystickWindowPositionX2,joystickWindowPositionY2,
					joystickWindowUpperLimitX2,joystickWindowUpperLimitY2);
			g2d.drawLine(joystickWindowUpperLimitX2,joystickWindowPositionY2,
					joystickWindowPositionX2,joystickWindowUpperLimitY2);

			//  	g2d.drawLine(lowerLimit2+half,lowerLimit2,lowerLimit2+half,upperLimit2);
			//   	g2d.drawLine(lowerLimit2,upperLimit2-half,upperLimit2,upperLimit2-half);


			//	g2d.drawLine(upperLimit+radius,lowerLimit-radius,lowerLimit-radius,upperLimit+radius);


			g2d.setColor(Color.RED);
			g2d.fillOval(positionX-radius,positionY-radius,radius*2,radius*2);

			try {
				Thread.sleep(10);  //INFO sleep in line drawing method
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint();

		}
	}


	class MyMouseAdapter extends MouseAdapter{

		public void mouseDragged(MouseEvent e) {

			positionX=e.getX();
			positionY=e.getY();
			if(e.getX()>joystickWindowUpperLimitX)positionX=joystickWindowUpperLimitX;
			if(e.getY()>joystickWindowUpperLimitY)positionY=joystickWindowUpperLimitY;
			if(e.getX()<joystickWindowPositionX)positionX=joystickWindowPositionX;
			if(e.getY()<joystickWindowPositionY)positionY=joystickWindowPositionY;
			outPositionX=invertPositionX(positionX);
			outPositionY=invertPositionY(positionY);
			statusField.setText(outPositionX+" , "+outPositionY);
		}     

		public void mousePressed(MouseEvent e) {

			positionX=e.getX();
			positionY=e.getY();
			if(e.getX()>joystickWindowUpperLimitX)positionX=joystickWindowUpperLimitX;
			if(e.getY()>joystickWindowUpperLimitY)positionY=joystickWindowUpperLimitY;
			if(e.getX()<joystickWindowPositionX)positionX=joystickWindowPositionX;
			if(e.getY()<joystickWindowPositionY)positionY=joystickWindowPositionY;
			outPositionX=invertPositionX(positionX);
			outPositionY=invertPositionY(positionY);
			statusField.setText(outPositionX+" , "+outPositionY);
		}           

		public void mouseReleased(MouseEvent e) {
			int middleX=Math.abs(joystickWindowUpperLimitX+joystickWindowPositionX)/2;
			int middleY=Math.abs(joystickWindowUpperLimitY+joystickWindowPositionY)/2;

			positionX=middleX;
			positionY=middleY;

			outPositionX=Math.abs(positionX-joystickWindowPositionX);
			outPositionY=(positionY-joystickWindowPositionY);
			statusField.setText(outPositionX+" , "+outPositionY);
		}

		public void mouseMoved(MouseEvent e) {
			int middleX=Math.abs(joystickWindowUpperLimitX+joystickWindowPositionX)/2;
			int middleY=Math.abs(joystickWindowUpperLimitY+joystickWindowPositionY)/2;

			positionX=middleX;
			positionY=middleY;

			outPositionX=Math.abs(positionX-joystickWindowPositionX);
			outPositionY=(positionY-joystickWindowPositionY);
			statusField.setText(outPositionX+" , "+outPositionY);
		}

	}

}