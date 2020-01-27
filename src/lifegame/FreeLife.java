package lifegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FreeLife extends JFrame
implements ActionListener, Runnable{
	private static final long serialVersionUID = 1L;
	private JButton button1,button2;
	private LifePanel life1;
	private Thread t;
	private boolean flg,kissOfDeath;

	//main�֐��̎��s(FreeLife)
	public static void main(String[] args) {
		new FreeLife().setVisible(true);
	}

	public FreeLife(){
		//window�̑傫�����ݒ�
		this.setSize(530, 500);
		setBackground(Color.pink);
		flg = false;
		kissOfDeath = false;
		setLayout(null);
		//���{�^���̐ݒ�
		button1 = new JButton();
		button1.setText("Strat");
		button1.setBounds(10, 420, 70, 20);
		getContentPane().add(button1);
		button2 = new JButton();
		button2.setText("Clear");
		button2.setBounds(440, 420, 70, 20);
		getContentPane().add(button2);
		//���{�^���̐ݒ�
		life1 = new LifePanel(100, 80);
		life1.setBounds(10, 10, 500, 400);
		life1.setBackground(Color.black);
		getContentPane().add(life1);
		button1.addActionListener(this);
		button2.addActionListener(this);
		//�N���[�Y�����Ƃ��ďI��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ev){
		//start�������ꂽ�ۂ̏���
		if(ev.getSource() == button1){
			if(!flg){
				//start��stop lifegame���s
				button1.setText("Stop");
				kissOfDeath = true;
				t = new Thread(this);
				t.start();
			} else {
				//stop��start lifegame��~
				kissOfDeath = false;
				button1.setText("Start");
			}
			flg = !flg;
			return;
		} else {
			life1.initialData();
			life1.repaint();
			return;
		}
	}

	public void run(){
		do {
			life1.checkAllLife();
			life1.repaint();
			//�G���[����
			try {
				Thread.sleep(100L);
			}
			catch(Exception _ex){}
		} while(kissOfDeath);
	}
}
