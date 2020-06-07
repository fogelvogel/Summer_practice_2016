package practice2016;

import java.util.Random;

public class Food {
	public static int countFood;//���������� ����� ���
	private koord posit; // ������� ���
	private String type; //��� ���
	private String name; //�������� ���
	private int cost; //���� ���
	private int callories; //���������� �������
	private boolean presenseOfAFly; //������� ����
	public static boolean isLast;//�������� �� ��������� ����� ����
	
	public Food() {
	if(countFood==App.numberOfFood) isLast = true;
	posit = App.arrOFFoodPosit[countFood];
		name = App.namesOfFood[countFood];
		if(countFood%2==0)
			if(countFood%3==0) type = "�������";
			else type = "������";
		else type = "������";
		if(countFood%3==0) type = "�������";
		Random r = new Random();
		boolean fly = r.nextBoolean();
		if(fly) fly = r.nextBoolean();
		if(fly) fly = r.nextBoolean();
		if(fly) presenseOfAFly = r.nextBoolean();//����������� ���� = 1/16
		int c = r.nextInt();
		if(c<0) c*=(-1);
		callories = c;
		c= r.nextInt()%App.MaxCallories;
		if(c<0) c*=(-1);
		cost = c;
		countFood++;
	}
	public Food(int number) {
		if(number==App.numberOfFood) isLast = true;
		posit = App.arrOFFoodPosit[number];
			name = App.namesOfFood[number];
			if(number%2==0)
				if(number%3==0) type = "�������";
				else type = "������";
			else type = "������";
			if(number%3==0) type = "�������";
			Random r = new Random();
			boolean fly = r.nextBoolean();
			if(fly) fly = r.nextBoolean();
			if(fly) fly = r.nextBoolean();
			if(fly) presenseOfAFly = r.nextBoolean();//����������� ���� = 1/16
			int c = r.nextInt();
			if(c<0) c*=(-1);
			callories = c;
			c= r.nextInt()%App.MaxCallories;
			if(c<0) c*=(-1);
			cost = c;
	}
	public Food(String t,String n,int co,int ca,boolean fly) {
		type = t;
		name = n;
		cost = co;
		callories = ca;
		presenseOfAFly = fly;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}
	public int getCallories() {
		return callories;
	}
	public boolean getFly() {
		return presenseOfAFly;
	}
	public koord getPos() {
		return posit;
	}
}
