package practice2016;

public class Kassa {
	private int cash; //наличные
	public void setCash(int a) {
		cash = a;
	}
	public int getCash() {
		return cash;
	}
public void addMoney(int m) { //добавить наличные
	cash+=m;
}
}
