package practice2016;

public class Table {//стол, размещаемый в столовой
	public static int countTables = 0;//сколько сейчас создано столов
	private boolean[] seats;//true-место занято
	public koord posit;//положение стола в столовой(в клеточках)
	private boolean isLast;//является ли последним из столов
	public Table() {
		posit = new koord();
		seats = new boolean[App.NumberOfSeats];
			posit.x = App.arrOFTablePositkox[countTables];
		posit.y = App.arrOFTablePositkoy[countTables];
		countTables++;
		}
	public koord getPosOfSeat(int i) {
		koord k = new koord();
		k.x = posit.x;
		k.y = posit.y;
		switch(i) {
		default : {
			k.x = posit.x;
			k.y = posit.y;
			break;
		}
		case(0) : {
			k.x = posit.x;
			k.y = posit.y;
			break;
		}
		case(1) : {
			k.x = posit.x;
			k.y = posit.y;
			k.x+=2;
			break;
		}
		case(2) : {
			k.x = posit.x;
			k.y = posit.y;
			k.y+=3;
			//k.x-=2;
			break;
		}
		case(3) : {
			k.x = posit.x;
			k.y = posit.y;
			k.x+=2;
			k.y+=3;
			break;
		}
		};
		if(seats[0]==false)System.out.println(true);
		else System.out.println(false);
		if(seats[1]==false)System.out.println(true);
		else System.out.println(false);
		if(seats[2]==false)System.out.println(true);
		else System.out.println(false);
		if(seats[3]==false)System.out.println(true);
		else System.out.println(false);
		return k;
	}
	public boolean canISeatOnI(int i ) {//можно ли сесть на i-ое место за столом(в порядке слева направо,сверху вниз)
		boolean b = false;
		if(seats[i]==false)b = true;
		return b;
	}
	public void sitThere(int i) {//сесть на i-ое место
		if(i<=App.NumberOfSeats)seats[i] = true;
	}
	public void goAway(int i) {//сесть на i-ое место
		seats[i] = false;
	}
public koord getPos() {
	return posit;
}

}
