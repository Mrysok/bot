
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


class ChatBot extends JFrame implements ActionListener{
	
	final String TITLE_OF_PROGRAM = "Chatbot";
	final int START_LOCATION = 200;
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 450;
	
	JTextArea dialogue;  	//���� ����������
	JCheckBox ai;		// ���/���� ��
	JTextField message;		
	ClassBot sbot;		//����� ��� ����
	
	public static void main(String[] args){
		new ChatBot();	
	}
	
	ChatBot(){
		setTitle(TITLE_OF_PROGRAM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(START_LOCATION,START_LOCATION,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		// ���� ��� �������
		dialogue = new JTextArea();
		dialogue.setLineWrap(true);
		JScrollPane	scrollBar = new JScrollPane(dialogue);
		
		// ������ ��� ������
		JPanel dp = new JPanel();
		dp.setLayout(new BoxLayout(dp, BoxLayout.X_AXIS));
		ai = new JCheckBox("AI");
		ai.doClick();
		//ai.
		message = new JTextField();
		message.addActionListener(this);
		JButton enter = new JButton("Enter");
		enter.addActionListener(this);
		
		//��������� �������� �� ������
		dp.add(ai);
		dp.add(message);
		dp.add(enter);
		
		add(BorderLayout.CENTER, scrollBar);
		add(BorderLayout.SOUTH,dp);
		setVisible(true);
		sbot = new ClassBot();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event){
		
	if(!ai.isSelected()){
		
	
		if(sbot.getflag()){//��������� ���� �� ��������
		if (message.getText().trim().length()>0){//��������� �� ������������ ���� ��� �����
			dialogue.append(message.getText()+"\n");//����� ���������� ������ � ����
			try {
				dialogue.append("������: " + 
						sbot.sayInReturn(message.getText())+"\n");//����� ���� �� ClassBot.java
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		message.setText("");}//������������ ��� �����
		
		if(!sbot.getflag()){//���� false ���������� ���������
			if (message.getText().trim().length()>0){
				dialogue.append(message.getText()+"\n");
				try {
					dialogue.append("������: " + 
							sbot.IDontKnow(message.getText())+"\n");//����� ���� �� ��������
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		message.setText("");
		message.requestFocusInWindow();
	}}else{
		if (message.getText().trim().length()>0){//��������� �� ������������ ���� ��� �����
			dialogue.append(message.getText()+"\n");//����� ���������� ������ � ����
			try {
				dialogue.append("������: " + 
						sbot.Simantika(message.getText())+"\n");//����� ���� �� ClassBot.java
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
	}}