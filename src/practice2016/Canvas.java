package practice2016;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	public static int swForAll = 1; //количество пикселей в одной клетке
	private static final long serialVersionUID = 1L;
	private int x,y; //координаты игрока
	public static int sw; //количество пикселей внутри клетки для данного класса
	private boolean drawLine; //рисовать ли на этом шаге сообщение
	private String name; //имя уходящего человека
	private String reason; //причина ухода
	private int roaches; //количество тараканов
	public static boolean[] thingsAtField; //массив разрешенных/запрещенных позиций
	public void drawString(String n,String r) { //определяет, рисовать ли сообщение об уходе клиента
		name = n;
		reason = r;
		drawLine = true;
		return;
	}
	
	public boolean getThings(int a) { //позволяет определить, свободна ли клетка, на которую клиент собирается наступить
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
	public void drawFood(int a,int b,int c,int d,Graphics g) { //для вывода блюд в количестве, заданном пользователем
		Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.yellow);
		g2d.fillRect(a*sw,b*sw,c*sw,d*sw);
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
	public static void drawThings(int a,int b,int c,int d,Graphics g) {//для занесения всех обьектов сразу в массив thingsAtField
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

	private void doDrawing(Graphics g) { //собственно рисование всего на форме
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.lightGray); //выбор цвета
		sw = this.getWidth() / App.N; //высчитыается количество пикселей в клетке
		for(int i=0;i<App.numberOfTables*4+17;i++) { //рисование мест, где могут происходить действия
			g2d.fillRect(App.arrOfActionPlaceskox[i]*sw,App.arrOfActionPlaceskoy[i]*sw, sw, sw);
		}
		for(int i = 0;i<App.maxOchered;i++) { //рисование очереди
			g2d.fillRect(App.ochered[i].x*sw,App.ochered[i].y*sw, sw, sw);
		}
		thingsAtField = new boolean[App.N * App.N];
		for (int i = 0; i < App.N * App.N; i++) {
			thingsAtField[i] = true;
		}
		for (int i = 0; i <= this.getWidth() / sw; i++) { //для рисования линий
			int m = Math.max(this.getHeight(), this.getWidth());
			g2d.drawLine(i * sw, 0, i * sw, this.getHeight());
			g2d.drawLine(0, i * sw, this.getWidth(), i * sw);
		}
		//интерьер
		g2d.setColor(Color.black);
		drawThings(3, 3, App.N - 6, 1, g);// прилавок
		drawThings(7, 4, App.N - 10, 1, g);// прилавок тоже
		drawThings(7, 7, App.N - 12, 1, g);// ограждение
		drawThings(0, 7, 4, 1, g);// касса горизонт.
		// drawThings(7,17,3,2,g,true);//стол 1
		drawThings(App.N - 5, App.N - 5, 5, 1, g);// вход
		drawThings(App.N - 9, App.N - 2, 3, 1, g);// меню
		drawThings(App.N - 5, App.N - 2, 1, 2, g);// вход
		drawThings(3, 4, 1, 3, g);// касса верт.
		drawThings(0, App.N - 5, 8, 1, g);// туалетная стена
		drawThings(7, App.N - 4, 1, 2, g);// перегородка в рукомойник
		drawThings(3, App.N - 4, 1, 2, g);// перегородка в туалет
		drawThings(App.N - 1, 3, 1, 2, g);// стена

		g.drawString("Касса2", 1 * sw / 2, 4 * sw);// надписи
		g.drawString("Касса1", 7 * sw / 2, 2 * sw / 2);// надписи
		g.drawString("Еда", 27 * sw / 2, 4 * sw / 2);// надписи

		g.drawString("Повар1,2", 21 * sw, 2 * sw / 2);// надписи
		g.drawString("Мойщик1,2", 25 * sw, 2 * sw / 2);// надписи
		g.drawString("Раздатчик2", 8 * sw, 2 * sw / 2);// надписи
		g.drawString("Раздатчик1", 17 * sw, 2 * sw / 2);// надписи

		g2d.setColor(Color.gray);
		drawThings(App.N - 3, 3, 2, 2, g);// окно приема

		g2d.setColor(Color.WHITE);
		g.drawString("Ограждение", 27 * sw / 2, 15 * sw / 2 + 5);// надписи
		g.drawString("Туалет", 1 * sw / 2, 26 * sw - 5);// надписи
		g.drawString("Вход", 26 * sw, 26 * sw - 5);// надписи
		g.drawString("Цены", 43 * sw/2, 29 * sw - 5);// надписи
		g.drawString("Окно", 27 * sw, 4 * sw - 5);// надписи
		g.drawString("приема", 27 * sw, 5 * sw - 5);// надписи
		
		drawThings(App.N - 1, 0, 1, 3, g);// стол мойщиков
		int tmp = 3;
		if (App.numberOfTables <= 3)
			tmp = App.numberOfTables;

		for (int i = 0; i < tmp; i++) { //рисование столов
			drawThings(i * 6 + 3, 13, 3, 2, g);
		}
		for (int i = 2; i < App.numberOfTables - 1; i++) {
			drawThings((i - 2) * 6 + 3, 18, 3, 2, g); //рисование нижнего ряда столов
		}
		
		g2d.setColor(Color.black);
	
		if (App.numberOfTables <= 3)
			tmp = App.numberOfTables;

		for (int i = 0; i < tmp; i++) {
			g.drawString("Стол", (i * 6 + 4) * sw - 5, 14 * sw + 3); //рисование надписей для столов
		}
		for (int i = 2; i < App.numberOfTables - 1; i++) { //рисование надписей для столов
			g.drawString("Стол", sw * ((i - 2) * 6 + 4) - 5, 19 * sw + 3);
		}
		g2d.setColor(Color.magenta);
		if(App.cbk1.isSelected())drawThings(5, 2, 1, 1, g);// люди
		if(App.cbk2.isSelected())drawThings(2, 5, 1, 1, g);// люди
		drawThings(9, 2, 1, 1, g);// люди
		drawThings(18, 2, 1, 1, g);// люди
		if(App.cbc1.isSelected())drawThings(21, 2, 1, 1, g);// люди
		if(App.cbc2.isSelected())drawThings(23, 2, 1, 1, g);// люди
		if(App.cbw1.isSelected())drawThings(25, 2, 1, 1, g);// люди
		if(App.cbw2.isSelected())drawThings(27, 2, 1, 1, g);// люди
		g2d.setColor(Color.red);
		g2d.fillRect(x, y, sw, sw); //рисование игрока
		g2d.setColor(Color.blue);
		for (int i = 0; i < App.k.countPeople - 1; i++) { //рисование клиентов
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
		
		if(drawLine == true) { //для вывода сообщения об уходе клиента
			drawLine = false;
			App.textArea.setText(App.textArea.getText()+ "\n"+ name + " уходит");
			if(reason == App.reasons[3]) {g2d.setColor(Color.green);App.countGood++;}
			else 	{g2d.setColor(Color.red);App.countBad++;}
			if(reason == App.reasons[2]) App.countFly++;
			g.drawString(name, 9 * sw , 10 * sw -5 );// надписи
			g.drawString("Ушел.", 12 * sw , 10 * sw  - 5);// надписи
			g.drawString("Причина:", 14 * sw , 10 * sw  - 5);// надписи
			g.drawString(reason, 17 * sw , 10 * sw  - 5);// надписи
			
		}
		if(roaches>=0)roaches--;
		App.good.setText(String.valueOf(App.countGood)); //для вывода статистики
		App.bad.setText(String.valueOf(App.countBad));
		App.k1.setText(String.valueOf(App.k.kassa1.getCash()));
		App.k2.setText(String.valueOf(App.k.kassa2.getCash()));
		if(App.countBad!= 0)App.countGoodBad = App.countGood/App.countBad;
		else App.countGoodBad = App.countGood;
		App.goodBad.setText(String.valueOf(App.countGoodBad));
		App.fly.setText(String.valueOf(App.countFly));
		if(App.cbc1.isSelected()==false && App.cbc2.isSelected()==false) {
			g2d.setColor(Color.red);
			g.drawString("Повары разошлись по домам!", 9 * sw , 15 * sw -5 );// надписи
		}
			if(App.cbw1.isSelected()==false && App.cbw2.isSelected()==false) {
				g2d.setColor(Color.red);
				g.drawString("Скоро заведутся тараканы!", 9 * sw , 16 * sw -5 );// надписи
			
				if(roaches<=10)roaches+=2;
				
			}
			g2d.setColor(Color.orange);
			for(int i = 0;i<9;i++) {
				if(roaches>=i+2)drawThings(App.roachPosit[i].x,App.roachPosit[i].y,1,1,g);
			}
			if(App.cbk1.isSelected()==false && App.cbk2.isSelected()==false) {
				g2d.setColor(Color.red);
				g.drawString("Кассы не работают!", 9 * sw , 17 * sw -5 );// надписи
			}
	}
}
