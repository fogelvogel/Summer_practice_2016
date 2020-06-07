package practice2016;

public class FoodDispenser implements Worker{

	@Override
	public int work() {
		System.out.println("Еда выдана");
		return 0;
	}

}
