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

	//main関数の実行(FreeLife)
	public static void main(String[] args) {
		new FreeLife().setVisible(true);
	}

	public FreeLife(){
		//windowの大きさ等設定
		this.setSize(530, 500);
		setBackground(Color.pink);
		flg = false;
		kissOfDeath = false;
		setLayout(null);
		//↓ボタンの設定
		button1 = new JButton();
		button1.setText("Strat");
		button1.setBounds(10, 420, 70, 20);
		getContentPane().add(button1);
		button2 = new JButton();
		button2.setText("Clear");
		button2.setBounds(440, 420, 70, 20);
		getContentPane().add(button2);
		//↑ボタンの設定
		life1 = new LifePanel(100, 80);
		life1.setBounds(10, 10, 500, 400);
		life1.setBackground(Color.black);
		getContentPane().add(life1);
		button1.addActionListener(this);
		button2.addActionListener(this);
		//クローズ処理として終了
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ev){
		//startが押された際の処理
		if(ev.getSource() == button1){
			if(!flg){
				//start→stop lifegame実行
				button1.setText("Stop");
				kissOfDeath = true;
				t = new Thread(this);
				t.start();
			} else {
				//stop→start lifegame停止
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
			//エラー処理
			try {
				Thread.sleep(100L);
			}
			catch(Exception _ex){}
		} while(kissOfDeath);
	}
}
