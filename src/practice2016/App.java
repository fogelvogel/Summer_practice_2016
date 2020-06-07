package practice2016;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class App {
	Graphics g;
	private int count;
	private JFrame frame; //�������� ����
	public static JTextArea textArea; //��� ������ ���� �������� �������� �� �����
	private JScrollPane scrollPane;
	private JFrame firstFrame; //�������������� ����� ���������
	private JFrame legendFrame; //�������� �����������
	private JFrame statsFrame; //���� ����������
	private JFrame pigeonFrame; //���� � �������
	private BackgroundPanel legendPanel;
	private BackgroundPanel pigeonPanel;
	public static JFrame settingsFrame; //���� � �����������
	private JComboBox cb1,cb2; //��� ������ ���������� ������ � ����
	private JLabel people,client; //������ ��� ���� ��������
	private JLabel stats1,stats2,stats3,stats4; //������ ��� ���� ����������
	public static JLabel good,bad,goodBad,fly,kas1Label,kas2Label,k1,k2;  //������ ��� ���� ����������
	public static int countGood,countBad,countFly; //������ ��� ���� ����������
	public static double countGoodBad; // ��� �������� ��������� ������� �������� � �������
public static JRadioButton rb1,rb2; //��� �������� ���������� �����
private JButton pigeonButton,legendButton,statsButton,slowButton;//������ ��� ������ ����
	public static Canvas panel; //�������� ������
	private BackgroundPanel bgp; //������ ��� ������� ������
	public static JCheckBox cbc1,cbc2,cbw1,cbw2,cbk1,cbk2; //��� ��������� ���������� �����
	public static koord roachPosit[]; // �����, ��� ����� ������ ��������
	private int tmp;
	private JLabel client2;
	private JLabel client3;
	public static int forEating = 16; //�������������� ����� ����� �������
	public static int numberOfTables; //����� ������ � ��������
	public static int numberOfFood; //����� ����
	public static int N = 30; //����� ������ �� ��������� � �����������
	public static int NumberOfSeats = 4; //����� ������� �� ����� ������
	public static int maxOchered = 10; //�������, ������� ����������, ���� ������ ��������
	public static int startPorcions = 3;//�����, � ������� �������� ��������
	public static int minMoney = 200; // ����� ��������� �����, ����������� ��� �����������
	public static int NumberOfFood = 3;  //������� ��� ������ ����� �����
	public static int GoToTheToilet = 100;  //��������, ��� ������� ������� ���� � ������
	public static int NumberOfNames = 50; //����� ��������� ����
	public static int MaxMoney = 1500;// ������������ ����� ����� � ��������
	public static int MaxPeople = 100;// ������������ ����� �����
	public static int MaxCallories = 1000;// ������������ ����� ������� � ���
	public static int timeBetweenPeople = 5; //����� �����, ����� ������� ���������� ����
	public static koord toilet; //���������� �������
	public static koord window; //���������� ���� ������ ������
	public static koord checkMoney; //���������� �����, ��� ���� ��������� ���� ������
	public static koord kas1; //���������� �����
	public static koord kas2; //���������� ����� 2
	public static koord[] ochered; //������ ��������� �������
	public static boolean isMorePeople; //���� �� ������� ��������
	public static int[] arrOFTablePositkox = {3,9,15,3,9,15};//������� ������ � ���������
	public static int[] arrOFTablePositkoy = {12,12,12,17,17,17};
	public static koord[] arrOFFoodPosit ;//������� ����
	public static int[]  arrOfActionPlaceskox = {
			N-2,N-6,19,18,17,16,15,14,13,12,11,10,9,8,5,4,0,3,5,3,5,9,11,9,11,15,17,15,17,3,5,3,5,9,11,9,11,15,17,15,17
			
	}; //�����, ��� ����� ����������� ��������
	public static int[]  arrOfActionPlaceskoy = {
			5,N-4,5,5,5,5,5,5,5,5,5,5,5,5,4,5,N-2,12,12,15,15,12,12,15,15,12,12,15,15, 17,17,20,20,17,17,20,20,17,17,20,20
	}; //�����, ��� ����� ����������� ��������
	public static Kitchen k;
	public static String[] names = {    //��������� �����: NumberOfNames ����
			
			"����","���","����","����","����",
			"����","����","�����","�����","����",
			"������","����","����","����","������",
			"����","����","����","����","�����",
			"������","������","����","����","����",
			"���","�����","��������","������","����",
			"�������","�����","����","����","������",
			"����","�����","����","�������","�����",
			"������","�����","�����","������","����",
			"����","��������","�����","�����","����"};
	public static String[] reasons = {       //��������� ������� ����, ��� ������� ������
			"������� ����� �����",
			"������������ ����� �� ���",
			"� ��� ��������� ����",
			"����"
	};
	public static String[]	namesOfFood = {
			"����","����","������",
			"�������","��������","���",
			"��� ����������","����� � �������","���",
			"������ ���","�����","���������� ����"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.firstFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		initialize();
	}
	/**
	 * ������������ ����������, ��������� �� � ������ ������ �������� ����
	 * �����-������ ������� (����, ����������, �����) ��� ��� ��������.
	 * ��������� ������� - ����� ������ � ����� �������, ������� � ����,  � ����� �������� ����, ��������������.
	 * ������������ �������� - �������� �� ������ ��������� (true) ��� ������� (false)
	 * @param a ����� ������, � ������� ��������� ������, ������� � ���� �� 30
	 * @param b ����� �������, � ������� ��������� ������, ������� � ���� �� 30
	 * @return boolean �������� �� ������ ���������
	*/
public boolean searchForThings(int a,int b) { //���������, �������� �� ������
	int tmpx = a/panel.getsw();
	int tmpy = b/panel.getsw();
	if(panel.getThings(tmpy*N+tmpx)) return true;
	return false;
}
	
	private void initialize() {
		roachPosit = new koord[10];
		roachPosit[0] = new koord(5,10);
		roachPosit[1] = new koord(2,15);
		roachPosit[2] = new koord(13,8);
		roachPosit[3] = new koord(18,11);
		roachPosit[4] = new koord(28,7);
		roachPosit[5] = new koord(25,27);
		roachPosit[6] = new koord(1,1);
		roachPosit[7] = new koord(20,10);
		roachPosit[8] = new koord(15,24);
		roachPosit[9] = new koord(2,18);
		textArea = new JTextArea();
		textArea.setText("");
        textArea.setCaretPosition(0);
		textArea.setEditable(false);
		textArea.setBounds(230, 20, 200, 340);
		 scrollPane = new JScrollPane(textArea);
		 scrollPane.setBounds(230, 20, 200, 340);
		scrollPane .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ochered = new koord[maxOchered];
		for(int i = 0;i<maxOchered;i++) {
			ochered[i] = new koord();
			ochered[i].x = N-4;
			ochered[i].y = i+8;
		}
		toilet = new koord();
		toilet.x = 0;
		toilet.y = N-2;
		window = new koord();
		window.x = N-2;
		window.y = 5;
		checkMoney = new koord();
		checkMoney.x = N-6;
		checkMoney.y = N-4;
		kas1 = new koord();
		kas2 = new koord();
		kas1.x = 5;
		kas2.x = 4;
		kas1.y = 4;
		kas2.y = 5;
		count =0;
		String[] answ = {"1","2","3","4","5","6"}; // �������� ���������� ������
		String[] answ2 = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		for(int i = 0;i<numberOfFood;i++) {
			arrOFFoodPosit[i] = new koord();
		}
		numberOfFood = 1;
		numberOfTables = 1;
		cb1 = new JComboBox(answ);
		cb2 = new JComboBox(answ2);
		cb1.setSelectedIndex(0);
		cb2.setSelectedIndex(0);
		frame = new JFrame();
		firstFrame = new JFrame();
		firstFrame.setUndecorated(true);
		frame.setResizable(false);
		legendFrame = new JFrame();
		legendFrame.setUndecorated(true);
		legendFrame.setResizable(false);
		statsFrame = new JFrame();
		statsFrame.setUndecorated(true);
		statsFrame.setResizable(false);
		pigeonFrame = new JFrame();
		pigeonFrame.setUndecorated(true);
		pigeonFrame.setResizable(false);
		settingsFrame = new JFrame();
		settingsFrame.setUndecorated(true);
		settingsFrame.setResizable(false);
		firstFrame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) screenSize.getWidth();
		int h = (int) screenSize.getHeight();
		firstFrame.setBounds(w/2 - 200, h/2 - 200, 400, 400);
		settingsFrame.setBounds(w/2 -800, h/2 - 200, 450, 450);
		legendFrame.setBounds(w/2 +400, 10, 300, 300);
		statsFrame.setBounds(w/2 +400, 320, 300, 200);
		pigeonFrame.setBounds(w/2 +400, 530, 300, 300);
		frame.setBounds(w/2 - 300, h/2 - 350, 630, 655);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		legendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		legendFrame.getContentPane().setLayout(null);
		statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statsFrame.getContentPane().setLayout(null);
		pigeonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pigeonFrame.getContentPane().setLayout(null);
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.getContentPane().setLayout(null);
		firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstFrame.getContentPane().setLayout(null);
		panel = new Canvas();
		pigeonPanel = new BackgroundPanel(1);
		legendPanel = new BackgroundPanel(2);
		good = new JLabel();
		bad = new JLabel();
		goodBad = new JLabel();
		fly = new JLabel();
		people = new JLabel();
		client = new JLabel();
		client2= new JLabel();
		client3= new JLabel();
		stats1 = new JLabel();
		stats2 = new JLabel();
		stats3 = new JLabel();
		stats4 = new JLabel();
		kas1Label = new JLabel();
		kas2Label= new JLabel();
		k1 = new JLabel();
		k2 = new JLabel();
		people.setText("���������� �����:");
		people.setBounds(10, 100, 150, 30);
		kas1Label.setText("� ������ �����:");
		kas1Label.setBounds(10, 360, 100, 30);
		kas2Label.setText("� ������ �����:");
		kas2Label.setBounds(10, 390, 100, 30);
		k1.setText("0");
		k1.setBounds(110, 360, 100, 30);
		k2.setText("0");
		k2.setBounds(110, 390, 100, 30);
		good.setText("0");
		good.setBounds(170, 10, 150, 30);
		bad.setText("0");
		bad.setBounds(170, 50, 150, 30);
		goodBad.setText("0");
		goodBad.setBounds(170, 90, 150, 30);
		fly.setText("0");
		fly.setBounds(170, 130, 150, 30);
		stats1.setText("�������� ����������:");
		stats1.setBounds(20, 10, 150, 30);
		stats2.setText("�������� ����:");
		stats2.setBounds(20, 50, 150, 30);
		stats3.setText("����������/����:");
		stats3.setBounds(20, 90, 150, 30);
		stats4.setText("��� �������:");
		stats4.setBounds(20, 130, 150, 30);
		client.setText("� ������ ������ �����");
		client.setBounds(10, 170, 180, 30);
		client2.setText("���������� ��������");
		client2.setBounds(10, 190, 180, 30);
		client3.setText("�������� ��������");
		client3.setBounds(10, 210, 180, 30);
		statsFrame.getContentPane().add(good,1,0);
		statsFrame.getContentPane().add(bad,1,0);
		statsFrame.getContentPane().add(goodBad,1,0);
		statsFrame.getContentPane().add(fly,1,0);
		statsFrame.getContentPane().add(stats1,0);
		statsFrame.getContentPane().add(stats2,1,0);
		statsFrame.getContentPane().add(stats3,1,0);
		statsFrame.getContentPane().add(stats4,1,0);
		settingsFrame.getContentPane().add(people,1,0);
		settingsFrame.getContentPane().add(kas1Label,1,0);
		settingsFrame.getContentPane().add(kas2Label,1,0);
		settingsFrame.getContentPane().add(k1,1,0);
		settingsFrame.getContentPane().add(k2,1,0);
		settingsFrame.getContentPane().add(client,1,0);
		settingsFrame.getContentPane().add(client2,1,0);
		settingsFrame.getContentPane().add(client3,1,0);
		settingsFrame.getContentPane().add(scrollPane,1,0);
		settingsFrame.getContentPane().add(textArea,1,0);
		scrollPane.setViewportView(textArea);
		scrollPane.setVisible(true);
		rb1 = new JRadioButton();
		rb1.setBounds(10,130,100,20);
		rb1.setText("�����");
		rb1.setSelected(true);
		rb2 = new JRadioButton();
		rb2.setBounds(10,150,150,20);
		rb2.setText("������� ��������");
		settingsFrame.getContentPane().add(rb1,1,0);
		settingsFrame.getContentPane().add(rb2,0);
		Random r = new Random();
		boolean bg = r.nextBoolean();
		rb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { //����� �� ������� �����������
				rb2.setSelected(false);
				rb1.setSelected(true);
				timeBetweenPeople = 5;
			}
		});
		rb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {//����� �� ������� �����������
				rb1.setSelected(false);
				rb2.setSelected(true);
				timeBetweenPeople = 1;
			}
		});
		bgp = new BackgroundPanel(bg);
		bgp.setBounds(0, 0, 400, 400);
		panel.setBounds(10, 11, 600, 600);
		legendPanel.setBounds(0, 0, 300, 300);
		pigeonPanel.setBounds(0, 0, 300, 300);
		panel.setsw(panel.getWidth()/N);
		panel.setx(	panel.getsw()*(N-1));
		panel.sety(	panel.getsw()*(N-1));
		frame.getContentPane().add(panel);
		pigeonFrame.getContentPane().add(pigeonPanel);
		legendFrame.getContentPane().add(legendPanel);
		firstFrame.getContentPane().add(bgp,1,0);
		cbc1 = new JCheckBox();
		cbc1.setBounds(10,80,70,20);
		cbc1.setText("�����1");
		cbc1.setSelected(true);
		settingsFrame.getContentPane().add(cbc1);
		cbc2 = new JCheckBox();
		cbc2.setBounds(100,80,70,20);
		cbc2.setText("�����2");
		cbc2.setSelected(true);
		settingsFrame.getContentPane().add(cbc2);
		cbw1 = new JCheckBox();
		cbw1.setBounds(10,20,80,20);
		cbw1.setText("������1");
		cbw1.setSelected(true);
		settingsFrame.getContentPane().add(cbw1);
		cbw2 = new JCheckBox();
		cbw2.setBounds(100,20,80,20);
		cbw2.setText("������2");
		cbw2.setSelected(true);
		settingsFrame.getContentPane().add(cbw2);
		cbk1 = new JCheckBox();
		cbk1.setBounds(10,50,80,20);
		cbk1.setText("�����1");
		cbk1.setSelected(true);
		settingsFrame.getContentPane().add(cbk1);
		cbk2 = new JCheckBox();
		cbk2.setBounds(100,50,80,20);
		cbk2.setText("�����2");
		cbk2.setSelected(true);
		settingsFrame.getContentPane().add(cbk2);
	if(bg){ //��������� ����������� � ����������� �� ���������� ����������
		cb1.setBounds(210, 208, 50, 20);
		cb2.setBounds(210, 257, 50, 20);
	}
	else {
		cb1.setBounds(120, 260, 50, 20);
		cb2.setBounds(120, 282, 50, 20);
	}
		cb1.setVisible(true);
		cb1.setFocusable(false);
		cb2.setFocusable(false);
		cb2.setVisible(true);
		firstFrame.getContentPane().add(cb1,2,0);
		firstFrame.getContentPane().add(cb2,2,0);
		KeyAdapter ka = new KeyAdapter(){
			@Override    //���������� �������
			public void keyReleased(KeyEvent arg0) {   //������� �� ���������� ������
				if (arg0.getKeyChar() == '\n') {//���� ������� enter
					numberOfTables = cb1.getSelectedIndex()+1;//��������� ���-�� ������
					numberOfFood = cb2.getSelectedIndex()+1;//��������� ���-�� ������������ ���
					settingsFrame.setVisible(true);
					frame.setVisible(true);   //���������� ����� ������� �����
					firstFrame.setVisible(false);  //���������� ��������� ������ �����
					k = Kitchen.getInstance(); //�������� ��������
					 arrOFFoodPosit  = new koord[numberOfFood];
						for(int i = 0;i<numberOfFood;i++) {
							arrOFFoodPosit[i] = new koord();
						}
						arrOFFoodPosit[0].x =19;
						arrOFFoodPosit[0].y = 4;
						for(int i = 0;i<numberOfFood-1;i++) {
							arrOFFoodPosit[i+1].x = arrOFFoodPosit[i].x-1;
							if(i%2==0)arrOFFoodPosit[i+1].y = arrOFFoodPosit[i].y-1;
							else arrOFFoodPosit[i+1].y = arrOFFoodPosit[i].y+1;
						} 
					
					
				}
			}
		};
	
legendButton = new JButton();
legendButton.setBounds(10,250,200,20);
legendButton.setText("�������� �����������");
legendButton.addActionListener(		new ActionListener() {
	@Override

		public void actionPerformed(ActionEvent arg0) {
legendFrame.setVisible(true);
	}
});
settingsFrame.getContentPane().add(legendButton,2,0);
		
	
statsButton = new JButton();
statsButton.setBounds(10,280,200,20);
statsButton.setText("�������� ����������");
statsButton.addActionListener(		new ActionListener() {
	@Override

		public void actionPerformed(ActionEvent arg0) {
statsFrame.setVisible(true);
	}
});
slowButton = new JButton();
slowButton.setBounds(10,340,200,20);
slowButton.setText("��������� ����� ����");
slowButton.addActionListener(		new ActionListener() {
	@Override

		public void actionPerformed(ActionEvent arg0) {
forEating = 32;
	}
});
settingsFrame.getContentPane().add(statsButton,2,0);
settingsFrame.getContentPane().add(slowButton,2,0);
pigeonButton = new JButton();
pigeonButton.setBounds(10,310,200,20);
pigeonButton.setText("�������� ������");
pigeonButton.addActionListener(		new ActionListener() {
	@Override

		public void actionPerformed(ActionEvent arg0) {
pigeonFrame.setVisible(true);
	}
});

settingsFrame.getContentPane().add(pigeonButton,2,0);
		
		
		firstFrame.addKeyListener(ka);
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (Kitchen.countPeople <= App.MaxPeople) {
					if (count % App.timeBetweenPeople == 0) {
						Kitchen.clients[Kitchen.countPeople - 1] = new Client();
						System.out.println(Kitchen.clients[Kitchen.countPeople - 1].getName());
						Kitchen.countPeople++;
					}
					count++;
				}
				if (arg0.getKeyChar() == 'd' && panel.getx() != panel.getWidth() - panel.getsw()) {// ���� ������ d � �������� ��������
					if (searchForThings(panel.getx() + panel.getsw(), panel.gety()))
						tmp = panel.getx() + panel.getsw();
					else
						tmp = panel.getx();
					panel.setx(tmp);
				}
				if (arg0.getKeyChar() == 'a' && panel.getx() != 0) {// ���� ������ a � �������� ��������
					if (searchForThings(panel.getx() - panel.getsw(), panel.gety()))
						tmp = panel.getx() - panel.getsw();
					else
						tmp = panel.getx();
					panel.setx(tmp);
				}
				if (arg0.getKeyChar() == 's' && panel.gety() != panel.getHeight() - panel.getsw()) {// ���� ������ s � �������� ��������
					if (searchForThings(panel.getx(), panel.gety() + panel.getsw()))
						tmp = panel.gety() + panel.getsw();
					else
						tmp = panel.gety();
					panel.sety(tmp);
				}
				if (arg0.getKeyChar() == 'w' && panel.gety() != 0) {// ���� ������ w � �������� ��������
					if (searchForThings(panel.getx(), panel.gety() - panel.getsw()))
						tmp = panel.gety() - panel.getsw();
					else
						tmp = panel.gety();
					panel.sety(tmp);
				}
				panel.repaint();
			}
		});
	}
}
