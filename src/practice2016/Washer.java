package practice2016;

import java.util.Random;

public class Washer  implements Worker{

	@Override
	public int work() {
		int c;
		Random r = new Random();
		c= r.nextInt()%2;
		if(c<0) c*=(-1);
		return c;
	}

}
