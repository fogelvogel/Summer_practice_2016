package practice2016;

public class Table {//����, ����������� � ��������
	public static int countTables = 0;//������� ������ ������� ������
	private boolean[] seats;//true-����� ������
	public koord posit;//��������� ����� � ��������(� ���������)
	private boolean isLast;//�������� �� ��������� �� ������
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
	public boolean canISeatOnI(int i ) {//����� �� ����� �� i-�� ����� �� ������(� ������� ����� �������,������ ����)
		boolean b = false;
		if(seats[i]==false)b = true;
		return b;
	}
	public void sitThere(int i) {//����� �� i-�� �����
		if(i<=App.NumberOfSeats)seats[i] = true;
	}
	public void goAway(int i) {//����� �� i-�� �����
		seats[i] = false;
	}
public koord getPos() {
	return posit;
}

}
