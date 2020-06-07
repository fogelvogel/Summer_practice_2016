package practice2016;

import java.util.Random;

public class Cook implements Worker {
private int indexOfFood;//еду с каким индексом готовит сейчас повар
public Cook() {
	
}
	@Override
	public int work() {//готовить втечние одного шага
		int c;
		Random r = new Random();
		c= r.nextInt()%App.numberOfFood;
		if(c<0) c*=(-1);
		indexOfFood = c;
		Kitchen.arrPorcii[indexOfFood]++;
		return c;
	}

}
