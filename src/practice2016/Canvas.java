package practice2016;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	public static int swForAll = 1; //���������� �������� � ����� ������
	private static final long serialVersionUID = 1L;
	private int x,y; //���������� ������
	public static int sw; //���������� �������� ������ ������ ��� ������� ������
	private boolean drawLine; //�������� �� �� ���� ���� ���������
	private String name; //��� ��������� ��������
	private String reason; //������� �����
	private int roaches; //���������� ���������
	public static boolean[] thingsAtField; //������ �����������/����������� �������
	public void drawString(String n,String r) { //����������, �������� �� ��������� �� ����� �������
		name = n;
		reason = r;
		drawLine = true;
		return;
	}
	
	public boolean getThings(int a) { //��������� ����������, �������� �� ������, �� ������� ������ ���������� ���������
		return thingsAtField[a];
	}
	public void setx(int a) {
		x = a;
	}
	public void sety(int b) {
		y = b;
	}
	public void setsw(int c) {
		sw = c;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public int getsw() {
		return sw;
	}
	public void drawFood(int a,int b,int c,int d,Graphics g) { //��� ������ ���� � ����������, �������� �������������
		Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.yellow);
		g2d.fillRect(a*sw,b*sw,c*sw,d*sw);
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
	public static void drawThings(int a,int b,int c,int d,Graphics g) {//��� ��������� ���� �������� ����� � ������ thingsAtField
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(a*sw,b*sw,c*sw,d*sw);
		for(int i = b; i<b+d;i++)
		{
			for(int j = a;j<a+c;j++)
			{
				thingsAtField[i*App.N + j] = false;
			}
		}
	}

	private void doDrawing(Graphics g) { //���������� ��������� ����� �� �����
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.lightGray); //����� �����
		sw = this.getWidth() / App.N; //������������ ���������� �������� � ������
		for(int i=0;i<App.numberOfTables*4+17;i++) { //��������� ����, ��� ����� ����������� ��������
			g2d.fillRect(App.arrOfActionPlaceskox[i]*sw,App.arrOfActionPlaceskoy[i]*sw, sw, sw);
		}
		for(int i = 0;i<App.maxOchered;i++) { //��������� �������
			g2d.fillRect(App.ochered[i].x*sw,App.ochered[i].y*sw, sw, sw);
		}
		thingsAtField = new boolean[App.N * App.N];
		for (int i = 0; i < App.N * App.N; i++) {
			thingsAtField[i] = true;
		}
		for (int i = 0; i <= this.getWidth() / sw; i++) { //��� ��������� �����
			int m = Math.max(this.getHeight(), this.getWidth());
			g2d.drawLine(i * sw, 0, i * sw, this.getHeight());
			g2d.drawLine(0, i * sw, this.getWidth(), i * sw);
		}
		//��������
		g2d.setColor(Color.black);
		drawThings(3, 3, App.N - 6, 1, g);// ��������
		drawThings(7, 4, App.N - 10, 1, g);// �������� ����
		drawThings(7, 7, App.N - 12, 1, g);// ����������
		drawThings(0, 7, 4, 1, g);// ����� ��������.
		// drawThings(7,17,3,2,g,true);//���� 1
		drawThings(App.N - 5, App.N - 5, 5, 1, g);// ����
		drawThings(App.N - 9, App.N - 2, 3, 1, g);// ����
		drawThings(App.N - 5, App.N - 2, 1, 2, g);// ����
		drawThings(3, 4, 1, 3, g);// ����� ����.
		drawThings(0, App.N - 5, 8, 1, g);// ��������� �����
		drawThings(7, App.N - 4, 1, 2, g);// ����������� � ����������
		drawThings(3, App.N - 4, 1, 2, g);// ����������� � ������
		drawThings(App.N - 1, 3, 1, 2, g);// �����

		g.drawString("�����2", 1 * sw / 2, 4 * sw);// �������
		g.drawString("�����1", 7 * sw / 2, 2 * sw / 2);// �������
		g.drawString("���", 27 * sw / 2, 4 * sw / 2);// �������

		g.drawString("�����1,2", 21 * sw, 2 * sw / 2);// �������
		g.drawString("������1,2", 25 * sw, 2 * sw / 2);// �������
		g.drawString("���������2", 8 * sw, 2 * sw / 2);// �������
		g.drawString("���������1", 17 * sw, 2 * sw / 2);// �������

		g2d.setColor(Color.gray);
		drawThings(App.N - 3, 3, 2, 2, g);// ���� ������

		g2d.setColor(Color.WHITE);
		g.drawString("����������", 27 * sw / 2, 15 * sw / 2 + 5);// �������
		g.drawString("������", 1 * sw / 2, 26 * sw - 5);// �������
		g.drawString("����", 26 * sw, 26 * sw - 5);// �������
		g.drawString("����", 43 * sw/2, 29 * sw - 5);// �������
		g.drawString("����", 27 * sw, 4 * sw - 5);// �������
		g.drawString("������", 27 * sw, 5 * sw - 5);// �������
		
		drawThings(App.N - 1, 0, 1, 3, g);// ���� ��������
		int tmp = 3;
		if (App.numberOfTables <= 3)
			tmp = App.numberOfTables;

		for (int i = 0; i < tmp; i++) { //��������� ������
			drawThings(i * 6 + 3, 13, 3, 2, g);
		}
		for (int i = 2; i < App.numberOfTables - 1; i++) {
			drawThings((i - 2) * 6 + 3, 18, 3, 2, g); //��������� ������� ���� ������
		}
		
		g2d.setColor(Color.black);
	
		if (App.numberOfTables <= 3)
			tmp = App.numberOfTables;

		for (int i = 0; i < tmp; i++) {
			g.drawString("����", (i * 6 + 4) * sw - 5, 14 * sw + 3); //��������� �������� ��� ������
		}
		for (int i = 2; i < App.numberOfTables - 1; i++) { //��������� �������� ��� ������
			g.drawString("����", sw * ((i - 2) * 6 + 4) - 5, 19 * sw + 3);
		}
		g2d.setColor(Color.magenta);
		if(App.cbk1.isSelected())drawThings(5, 2, 1, 1, g);// ����
		if(App.cbk2.isSelected())drawThings(2, 5, 1, 1, g);// ����
		drawThings(9, 2, 1, 1, g);// ����
		drawThings(18, 2, 1, 1, g);// ����
		if(App.cbc1.isSelected())drawThings(21, 2, 1, 1, g);// ����
		if(App.cbc2.isSelected())drawThings(23, 2, 1, 1, g);// ����
		if(App.cbw1.isSelected())drawThings(25, 2, 1, 1, g);// ����
		if(App.cbw2.isSelected())drawThings(27, 2, 1, 1, g);// ����
		g2d.setColor(Color.red);
		g2d.fillRect(x, y, sw, sw); //��������� ������
		g2d.setColor(Color.blue);
		for (int i = 0; i < App.k.countPeople - 1; i++) { //��������� ��������
			App.k.clients[i].moving(g2d);
		}
		App.k.c1IsWorking = App.cbc1.isSelected();
		App.k.c2IsWorking = App.cbc2.isSelected();
		App.k.w1IsWorking = App.cbw1.isSelected();
		App.k.w2IsWorking = App.cbw2.isSelected();
		App.k.k1IsWorking = App.cbk1.isSelected();
		App.k.k2IsWorking = App.cbk2.isSelected();
		if (App.rb2.isSelected()) {
			App.rb1.setSelected(false);
			App.isMorePeople = true;
		}
		for (int i = 0; i < App.numberOfFood; i++) {
			this.drawFood(App.arrOFFoodPosit[i].x, App.arrOFFoodPosit[i].y, 1, 1, g);
		}
		
		if(drawLine == true) { //��� ������ ��������� �� ����� �������
			drawLine = false;
			App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ������");
			if(reason == App.reasons[3]) {g2d.setColor(Color.green);App.countGood++;}
			else 	{g2d.setColor(Color.red);App.countBad++;}
			if(reason == App.reasons[2]) App.countFly++;
			g.drawString(name, 9 * sw , 10 * sw -5 );// �������
			g.drawString("����.", 12 * sw , 10 * sw  - 5);// �������
			g.drawString("�������:", 14 * sw , 10 * sw  - 5);// �������
			g.drawString(reason, 17 * sw , 10 * sw  - 5);// �������
			
		}
		if(roaches>=0)roaches--;
		App.good.setText(String.valueOf(App.countGood)); //��� ������ ����������
		App.bad.setText(String.valueOf(App.countBad));
		App.k1.setText(String.valueOf(App.k.kassa1.getCash()));
		App.k2.setText(String.valueOf(App.k.kassa2.getCash()));
		if(App.countBad!= 0)App.countGoodBad = App.countGood/App.countBad;
		else App.countGoodBad = App.countGood;
		App.goodBad.setText(String.valueOf(App.countGoodBad));
		App.fly.setText(String.valueOf(App.countFly));
		if(App.cbc1.isSelected()==false && App.cbc2.isSelected()==false) {
			g2d.setColor(Color.red);
			g.drawString("������ ��������� �� �����!", 9 * sw , 15 * sw -5 );// �������
		}
			if(App.cbw1.isSelected()==false && App.cbw2.isSelected()==false) {
				g2d.setColor(Color.red);
				g.drawString("����� ��������� ��������!", 9 * sw , 16 * sw -5 );// �������
			
				if(roaches<=10)roaches+=2;
				
			}
			g2d.setColor(Color.orange);
			for(int i = 0;i<9;i++) {
				if(roaches>=i+2)drawThings(App.roachPosit[i].x,App.roachPosit[i].y,1,1,g);
			}
			if(App.cbk1.isSelected()==false && App.cbk2.isSelected()==false) {
				g2d.setColor(Color.red);
				g.drawString("����� �� ��������!", 9 * sw , 17 * sw -5 );// �������
			}
	}
}
