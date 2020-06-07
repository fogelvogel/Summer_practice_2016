package practice2016;

public class Kitchen { //�����-�� ��������
	public static Cook cook1; //�����
	public static Cook cook2;
	public static Washer washer1; //������
	public static Washer washer2;
	public static boolean c1IsWorking,c2IsWorking,w1IsWorking,w2IsWorking,k1IsWorking,k2IsWorking;//�������� �� ���� � ������ �����
	public static Kassa kassa1,kassa2;
	public static Client[] clients;
	public Table[] tables;
	public static int[] arrPorcii; //������ � ����������� ������
	public static int countPeople; //����� ���������� �����
	private static Kitchen instance;
	public static Kitchen getInstance() {
		if(instance==null) instance = new Kitchen();
		return instance;
	}
	private Kitchen() {
		arrPorcii = new int[App.numberOfFood];
		for(int i=0;i<App.numberOfFood;i++) {
			System.out.println("��� �������");
			arrPorcii[i] = App.startPorcions;
		}
		tables = new Table[App.numberOfTables];
		for(int i = 0;i<App.numberOfTables;i++) {
			tables[i] = new Table();
			System.out.println("���� ������");
		}
		c1IsWorking = c2IsWorking = w1IsWorking = w2IsWorking = k1IsWorking = k2IsWorking = true;
		cook1 = new Cook();
		cook2 = new Cook();
		washer1 = new Washer();
		washer2 = new Washer();
		kassa1 = new Kassa();
		kassa2 = new Kassa();
		clients = new Client[App.MaxPeople];
		countPeople++;
	}
}
