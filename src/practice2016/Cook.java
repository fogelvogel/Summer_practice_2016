package practice2016;

import java.util.Random;

public class Cook implements Worker {
private int indexOfFood;//��� � ����� �������� ������� ������ �����
public Cook() {
	
}
	@Override
	public int work() {//�������� ������� ������ ����
		int c;
		Random r = new Random();
		c= r.nextInt()%App.numberOfFood;
		if(c<0) c*=(-1);
		indexOfFood = c;
		Kitchen.arrPorcii[indexOfFood]++;
		return c;
	}

}
