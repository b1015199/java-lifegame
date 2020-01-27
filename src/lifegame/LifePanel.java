package lifegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class LifePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean data[][];
	private int dataWidth;
	private int dataHeight;
	private Image offScreen;
	private Graphics offg;


	public LifePanel(){
		this(25, 25);
	}

	public LifePanel(int i, int j){
		dataWidth = i;
		dataHeight = j;
		//panel�̐ݒ�
		setPreferredSize(new Dimension(dataWidth * 5,
				dataHeight * 5));
		initialData();
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent mouseevent){
				doMouseDown(mouseevent);
			}
		});
	}

	public void initialData(){
		data = new boolean[dataWidth][dataHeight];
		for(int i = 0; i < dataWidth; i++){
			for(int j = 0; j < dataHeight; j++)
				data[i][j] = false;

		}
	}

	public void doMouseDown(MouseEvent ev){
		int i = ev.getX() / 5;
		int j = ev.getY() / 5;
		if(i >= dataWidth || j >= dataHeight){
			return;
		} else {
			data[i][j] = !data[i][j];
			repaint();
			return;
		}
	}

	//�X�V����֐�
	public void update(Graphics g){
		paint(g);
	}

	//�`��֐�
	public void paint(Graphics g){
		if(offScreen == null){
			offScreen = createImage(dataWidth * 5, dataHeight * 5);
			offg = offScreen.getGraphics();
		}
		//�h�b�g����������
		offg.setColor(Color.BLACK);
		offg.fillRect(0, 0, dataWidth * 5, dataHeight * 5);
		//�h�b�g���s���N�ɂ���
		offg.setColor(Color.PINK);
		for(int i = 0; i < dataWidth; i++){
			for(int j = 0; j < dataHeight; j++)
				if(data[i][j])
					offg.fillRect(i * 5, j * 5, 4, 4);

		}

		g.clearRect(0, 0, getSize().width, getSize().height);
		g.drawImage(offScreen, 0, 0, this);
	}

	//life(�h�b�g)�̊m�F(�����Ȃ�)
	public void checkAllLife(){
		boolean aflag[][] = new boolean[dataWidth][dataHeight];
		for(int j = 0; j < dataWidth; j++){
			for(int k = 0; k < dataHeight; k++){
				int i = checkLife(j, k);
				aflag[j][k] = false;
				if(i == 2 && data[j][k])
					aflag[j][k] = true;
				if(i == 3)
					aflag[j][k] = true;
				if(i < 2 || i > 3)
					aflag[j][k] = false;
			}

		}
		for(int l = 0; l < dataWidth; l++){
			for(int i1 = 0; i1 < dataHeight; i1++)
				data[l][i1] = aflag[l][i1];

		}
		repaint();
	}

	//life(�h�b�g)�̊m�F(�c���̒l(width*height��)�����炢�`�F�b�N)
	public int checkLife(int i, int j){
		int k = 0;
		int l = dataWidth;
		int i1 = dataHeight;
		//�����̏����͑��T�C�g�ȂǂƔ�ׂďȗ�������Ă��čׂ����l�̕ω����킩��܂���ł����B
		return k = (k = (k = (k = (k = (k = (k =
				(k += data[(i + l + -1) % l][(j + i1 + -1) % i1] ? 1 : 0)
				+ (data[(i + l) % l][(j + i1 + -1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1 + -1) % i1] ? 1 : 0))
				+ (data[(i + l + -1) % l][(j + i1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1) % i1] ? 1 : 0))
				+ (data[(i + l + -1) % l][(j + i1 + 1) % i1] ? 1 : 0))
				+ (data[(i + l) % l][(j + i1 + 1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1 + 1) % i1] ? 1 : 0);
	}
}

