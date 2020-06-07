package practice2016;

import java.awt.Graphics2D;
import java.util.Random;

public class Client { //������ ��������

private Food food; //���, ������� ����� �������
private String name;     //��� �������
private koord posit;     //������� � ������ ������
private int steps; //������� ������� �� ����� �� ������
private int table; //����� �����, �� ������� ����� �������
private int seatNumber; //����� ������� �� ������
private int money;    //��������� ������
private boolean hasAFly;     //���� �� � ���� ����
private int wantsToToilet;//�������� ���� ����� ���� �� 0 �� GoToTheToilet. ��� ��������� ������ ���� � ������, ����� ������� ���
private boolean wantsToExit;    //������ ������� ������ � ������
private boolean isSitting;   //����� �� �� ������
private boolean hasAFood;   //����� �� �� ������
private int eatingTime;   //�����, ����������� ��������, ����� ������ (� �����)
private String reasonOfExit;   //�������, �� ������� ������� ������
private int posX;   //������� �� �
private int posY;    //������� �� y
private boolean forLabel; //��� ������ ��������� �� �����

public int checkForMoney() {
	
	if(money>App.minMoney)return 1; //��� ������� �������� ���� ����������� ��������, ������� ����������, ������ �� ������ ��� �����
	return 0;
}
public boolean checkForASeat() { //���������, ����� �� ����� �� ������ ���������� ����� 
	boolean tmp = false; // ����������� �������� �� ������ ������ false
	for(int i = 0;i<App.NumberOfSeats;i++) { //���� �� ���������� ������� �� ����� ������
		for(int j = 0;j<App.numberOfTables;j++) { //���� �� ���������� ������
			if(App.k.tables[j].canISeatOnI(i)) return true; //���������� ������������ ������ table
		}
	}
	return tmp; //����������� �������� ��������
}
public koord goEat() { //������ ���� ���� �� ���� (���� ���� �����)
	koord tmp = new koord(); //��� �������� ����� ��������� � ���������� ������������
	tmp.x = posit.x; //��������� ���������� ������������� ���������� ��������
	tmp.y = posit.y; //��������� ���������� ������������� ���������� ��������
	for(int i = 0;i<App.NumberOfSeats;i++) {//���� �� ���������� ������� �� ����� ������
		for(int j = 0;j<App.numberOfTables;j++) {//���� �� ���������� ������
			if(App.k.tables[j].canISeatOnI(i)&&!isSitting) { //���� ����� ��������
				App.k.tables[j].sitThere(i); //���������� ������������ ������ table, ������� �������� ����� ��� �������
				table = j; //����� ����� ������������
				App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ������� �� ����");//����� ��������� �� �����
				isSitting = true; //����������, ��� ������� �����
				seatNumber = i; //����� ������� ������������
				tmp.x = App.k.tables[j].getPosOfSeat(i).x; //������� ������� ������������ � ���������� ��������� ����������
				tmp.y = App.k.tables[j].getPosOfSeat(i).y;//������� ������� ������������ � ���������� ��������� ����������
				return tmp; //�������� ������������ � ���������� ������������
			}
		}
	}
	return tmp; //�������� ������������ � ���������� ������������ � ������ ������
}
public koord goPay() { //������ ���� ������� � �����
	koord tmp = new koord(); //��������� ��������� ����������
	tmp.x = posit.x; //��������� ���������� ������������� ���������� ��������
	tmp.y = posit.y; //��������� ���������� ������������� ���������� ��������
	if(App.panel.thingsAtField[App.kas1.y*App.N+App.kas1.x]&&App.k.k1IsWorking) { // �����������, �������� �� �����
		
		tmp.x = App.kas1.x; //��������� ���������� ������������� �������� �����
		tmp.y = App.kas1.y;//��������� ���������� ������������� �������� �����
		if(!checkForASeat()) wantsToExit = true; //���� ����� �� ������ ��� �������� ���, �� ����� � ������
		else {
			
			App.k.kassa1.addMoney(money); //� ������ ������ ������ � ����� ��� ���� ������
			money = 0; //������ �������� ����������
		}
		
	}
	else {
		if(App.panel.thingsAtField[App.kas2.y*App.N+App.kas2.x]&&App.k.k2IsWorking) { // �����������, �������� �� ����� 2
			tmp.x = App.kas2.x; //��������� ���������� ������������� �������� ����� 2
			tmp.y = App.kas2.y; //��������� ���������� ������������� �������� ����� 2
			if(!checkForASeat()) wantsToExit = true; //���� ����� �� ������ ��� �������� ���, �� ����� � ������
			else {
				wantsToExit = false; // ������� �� ����� � ������
				App.k.kassa2.addMoney(money);//� ������ ������ ������ � ����� ��� ���� ������
				money = 0; //������ �������� ����������
			}
			
		}
	}
	return tmp; //�������� ��������� ���������� ������������ � ���������� ������������
}
public koord goBuy() { //������ ���� � �������� �������� ���
	koord tmp = new koord(); //��������� ��������� ����������
	Random r = new Random(); //��� ��������� ��������� �����
	int c = r.nextInt()%App.numberOfFood; //� - ��������� ����� �����, �� ������, ��� ���������� ����, ������� ������� ������
	if(c<0)c*=(-1); // ���� � ������ ����, �� ��������� �� -1
	if(App.panel.thingsAtField[5*App.N+App.arrOFFoodPosit[c].x]) { //���� ����� �� ������ ������ ��������
		tmp.x = App.arrOFFoodPosit[c].x; // ������������ ��������� ���������� ������� ���
		tmp.y = 5; // �� ������ ������ ������������ ��������
	}
	else {
		if(App.panel.thingsAtField[App.ochered[App.maxOchered-1].y*App.N+App.ochered[App.maxOchered-1].x]== false) { //������ � �������
			tmp.x = 100; //����� ������� ����� � ������
		reasonOfExit  = App.reasons[0]; //������� ����� - ������� ����� �����
		if(forLabel==true)App.panel.drawString(this.name,this.reasonOfExit); //�������� ������ � ���, ��� ������ ���� � �� ����� �������
		forLabel = false; //����� ������� �� ��������� ������ ������ ����
		}
		for(int i = 0;i<App.maxOchered;i++) { //���� �� ������� �������
			if(App.panel.thingsAtField[App.ochered[App.maxOchered-i-1].y*App.N+App.ochered[App.maxOchered-i-1].x]== true) tmp = App.ochered[App.maxOchered-i-1];
		// �������� �������� �� �������
		}
	}
	
	return tmp; //����������� �������� ��������
}
public koord selectPosit() { //����� ������� ������� ��� ���������� ����
	wantsToToilet++; //����� ������� ������� � ������
	koord k = new koord(); //��������� ���������� ��� ����������� ��������
	k.x = posit.x; //������������� ������� �������� ��������
	k.y = posit.y; //������������� ������� �������� ��������
	if(posit.x == (App.N-1)) { //���� � �������� ��������� ��������
		k.x = App.checkMoney.x; //������� ������ �� ����� �������� �����
		k.y = App.checkMoney.y;//������� ������ �� ����� �������� �����
	}
	if(posit.y==App.checkMoney.y) { //���� ������� �� ������� �������� �����
		if(checkForMoney()==1) { //���������� ������������ �������� �����
			App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ���� ��������"); //����� �� �����
			k.x = goBuy().x; //���������� ������������ ������� ���
			k.y = goBuy().y;//���������� ������������ ������� ���
			
		return k; //������ ������������ �����������
		}
		else {
			reasonOfExit = App.reasons[1]; //������� ����� �������� �� "������������ ����� �� ���"
			k.x = 100; //������� ������ � ����
			if(forLabel==true)App.panel.drawString(this.name,this.reasonOfExit); //�������� ������, ������������� �� ����� ��������
			forLabel = false; //����� ��������� ������ �� ����������
			return k; //������ ������������ �����������
		}
	}
	for(int i = 0;i<App.numberOfFood;i++) { //���� �� ���������� ����
		if(posit.x == App.arrOFFoodPosit[i].x && posit.y==5) { //���� ������� ����� ����� � ������ ������
		if(!hasAFood)	App.textArea.setText(App.textArea.getText()+ "\n"+ name + " �����"+App.namesOfFood[i]); //���������� � ����� ��������� ���� ���
			food = new Food(i); //��� � ����������� ������� � i-�� ��������� ���������
			hasAFood = true; //� ������� ���� ���
			if(food.getFly()) hasAFly = true; //���� � ��� ���� ����, � ������� ���� ���� ����
			if(!hasAFood)App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ������"); //���������� � ����� ��������� ���� ���
			k.x = goPay().x; //���������� ������������ ������
			k.y = goPay().y; //���������� ������������ ������
			}
	}
	for(int i = 0;i<App.maxOchered;i++) {//��� ������ �� �������
		if(posit.x == App.ochered[i].x) k = goBuy();//���� ���� �����, ������� ���� ��������, ����� ��������� �� �������
	}
	if(!wantsToExit&&((posit.x ==App.kas1.x&&posit.y ==App.kas1.y)||(posit.x ==App.kas2.x&&posit.y ==App.kas2.y))) { //���� ������� �� ����� ����� � �� ����� �������
		koord asd = new koord(); //��������� ����� ��������� ����������
		asd = goEat(); //���������� ������������ ������ ����� ��� �������� ���
		k.x= asd.x; //���������� ������ ����� �������������
		k.y= asd.y;//���������� ������ ����� �������������
	}
	for(int i = 0;i<App.NumberOfSeats;i++) { //���� �� ���������� �������
		for(int j = 0;j<App.numberOfTables;j++) { //���� �� ���������� ������
			if(posit.x==App.k.tables[j].getPosOfSeat(i).x&&posit.y==App.k.tables[j].getPosOfSeat(i).y) { //���� ������� �������� ������������� ������� �������
				table = j;//���� ������������
				seatNumber = i; //������� ������������
				if(steps >= App.forEating) { //���� ������� �������� �� ������ ����������� ���������� �������
				App.k.tables[table].goAway(seatNumber); //������ ����������� ������� �������
				k = App.window;//���������� �������� �� ���������� ���� ������ ������� ������
				System.out.println(name + " ������");//����� �������� � �����
				return k;//����������� ����� ��������� �������
				}
				steps++;//������� ������� �� ������ �������������
				}
		}
	}
	if(((posit.x==App.toilet.x)&&(posit.y == App.toilet.y))) { //���� ������ ��������� � �������
		k.x =100; //������� �������� � �����
		if(hasAFly)App.panel.drawString(name, App.reasons[2]);//��������� ������ � �������� �����
		else App.panel.drawString(name, App.reasons[3]); //��������� ������ � �������� �����
	}
	if((posit.x==App.window.x&&posit.y == App.window.y)) { //���� ������� ��������� � ���� ������ ������
		App.textArea.setText(App.textArea.getText()+ "\n"+ name + "  ����(�) ������");//����� ��������� � �����
		if(wantsToToilet ==  App.GoToTheToilet) {k = App.toilet;App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ���� � ������");}//���� ������� ����� � ������
		else {
			k.x =100; //�������� � �����
			if(hasAFly)App.panel.drawString(name, App.reasons[2]); //��������� ������ � �������� �����
			else App.panel.drawString(name, App.reasons[3]); //��������� ������ � �������� �����
		}
	}
	wantsToToilet++; //����� ������� ������� � ������
	wantsToToilet++; //����� ������� ������� � ������
if(wantsToExit) {//���� ������� ����� � ������
	k.x = 100; //�������� ������
	
	App.panel.drawString(name, App.reasons[0]); //����� ������ � �������� �����
	
}
	return k;//����������� �������� ��������
}
public int moving(Graphics2D g) { //������������ ���������� �������� � ������ �����
	koord ko = new koord(); //�������� ����� ��������� ����������
	ko = selectPosit(); //���������� ������������ ������ ����� ������� �������
	posit.x = ko.x; //����� ������� ������������� ������� �������
	posit.y= ko.y; //����� ������� ������������� ������� �������
	if(posit.x!=100)App.panel.thingsAtField[ko.y*App.N+ko.x] = false;//�����������, ����� �� ���� � ��������� �����
	Random r = new Random(); //��� ��������� ��������� �����
	int c = r.nextInt()%App.numberOfFood; //��������� �����, ������, ��� ����� ����
	Canvas.drawThings(posit.x, posit.y, 1, 1, g); //��������� �������� �� �����
	return 0; //����������� �������� ��������
}
public Client() { //����������� ��� ����������
	forLabel = true;//��� ������ ��������� �� �����
	steps = 0; //���� ����������
	Random r = new Random(); //��� ��������� ��������� �����
	int c = r.nextInt()%App.NumberOfNames; //��� ���������� ��������� �������
	if(c<0) c*=(-1); //����� �� ������ ���� �������������
	name = App.names[c]; //��� �������������
	c = r.nextInt()%100; //��������� ��������� �����
	if(c<0) c*=(-1);//����� �� ������ ���� �������������
	wantsToToilet = c; //��������� ������� ����� � ������
	c = r.nextInt()%App.MaxMoney; //������������ ��������� �����
	if(c<0) c*=(-1);//����� �� ������ ���� �������������
	money = c; //������ ������������� �� ���������� �
	c = r.nextInt()%5+5; //��������� ����� ����������
	if(c<0) c*=(-1);//����� �� ������ ���� �������������
	eatingTime = c; // ������������� �����, ����������� ��� ���
	reasonOfExit = App.reasons[0]; // �������� ����������� ������� �����
	posX = posY = (App.N-1);// �������� ����������� �������
	posit = new koord(); //��������� ���������� ��� �������
	posit.x = posX; //������� ������������� �� ������� ����������
	posit.y = posY;//������� ������������� �� ������� ����������
	App.textArea.setText(App.textArea.getText()+ "\n"+ name + " ������(�)"); //����� ��������� � �����
	
}

public boolean getIsSitting() {
	return isSitting;
}
public void setSitting(boolean b) {
	isSitting = b;
}
public int getMoney() {
	return money;
}
public void setMoney(int money) {
	this.money = money;
}
public boolean isHasAFly() {
	return hasAFly;
}
public void setHasAFly(boolean hasAFly) {
	this.hasAFly = hasAFly;
}
public int getWantsToToilet() {
	return wantsToToilet;
}
public void setWantsToToilet(int wantsToToilet) {
	this.wantsToToilet = wantsToToilet;
}
public boolean isWantsToExit() {
	return wantsToExit;
}
public void setWantsToExit(boolean wantsToExit) {
	this.wantsToExit = wantsToExit;
}
public String getReasonOfExit() {
	return reasonOfExit;
}
public void setReasonOfExit(String reasonOfExit) {
	this.reasonOfExit = reasonOfExit;
}
public int getPosX() {
	return posX;
}
public void setPosX(int posX) {
	this.posX = posX;
}
public int getPosY() {
	return posY;
}
public void setPosY(int posY) {
	this.posY = posY;
}
public String getName() {
	return name;
}

public int getEatingTime() {
	return eatingTime;
}
}
