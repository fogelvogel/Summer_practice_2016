package practice2016;

import java.awt.Graphics2D;
import java.util.Random;

public class Client { //клиент столовой

private Food food; //еда, которую купил человек
private String name;     //имя клиента
private koord posit;     //позиция в данный момент
private int steps; //сколько времени он сидит за столом
private int table; //номер стола, за которым сидит человек
private int seatNumber; //номер сиденья за столом
private int money;    //карманные деньги
private boolean hasAFly;     //есть ли у него муха
private int wantsToToilet;//значение поля может быть от 0 до GoToTheToilet. При максимуме челове идет в туалет, когда доедает еду
private boolean wantsToExit;    //сейчас человек пойдет к выходу
private boolean isSitting;   //сидит ли за столом
private boolean hasAFood;   //сидит ли за столом
private int eatingTime;   //время, необходимое человеку, чтобы поесть (в шагах)
private String reasonOfExit;   //причина, по которой человек уходит
private int posX;   //позиция по х
private int posY;    //позиция по y
private boolean forLabel; //для вывода сообщения об уходе

public int checkForMoney() {
	
	if(money>App.minMoney)return 1; //для каждого человека есть минимальное значение, которое определяет, пойдет он дальше или уйдет
	return 0;
}
public boolean checkForASeat() { //проверяет, можно ли сесть на данное конкретное место 
	boolean tmp = false; // изначальное значение на всякий случай false
	for(int i = 0;i<App.NumberOfSeats;i++) { //цикл по количеству сидений за одним столом
		for(int j = 0;j<App.numberOfTables;j++) { //цикл по количеству столов
			if(App.k.tables[j].canISeatOnI(i)) return true; //используем подпрограмму класса table
		}
	}
	return tmp; //возвращение функцией значения
}
public koord goEat() { //клиент идет есть за стол (если есть место)
	koord tmp = new koord(); //для передачи новых координат в вызывающую подпрограмму
	tmp.x = posit.x; //временной переменной присваиваются координаты человека
	tmp.y = posit.y; //временной переменной присваиваются координаты человека
	for(int i = 0;i<App.NumberOfSeats;i++) {//цикл по количеству сидений за одним столом
		for(int j = 0;j<App.numberOfTables;j++) {//цикл по количеству столов
			if(App.k.tables[j].canISeatOnI(i)&&!isSitting) { //если место свободно
				App.k.tables[j].sitThere(i); //используем подпрограмму класса table, которая отмечает место как занятое
				table = j; //номер стола запоминается
				App.textArea.setText(App.textArea.getText()+ "\n"+ name + " садится за стол");//вывод сообщения на форму
				isSitting = true; //отмечается, что человек сидит
				seatNumber = i; //номер сиденья запоминается
				tmp.x = App.k.tables[j].getPosOfSeat(i).x; //позиция сиденья запоминается и передается временной переменной
				tmp.y = App.k.tables[j].getPosOfSeat(i).y;//позиция сиденья запоминается и передается временной переменной
				return tmp; //значение возвращается в вызывающую подпрограмму
			}
		}
	}
	return tmp; //значение возвращается в вызывающую подпрограмму в другом случае
}
public koord goPay() { //клиент идет платить в кассу
	koord tmp = new koord(); //создается временная переменная
	tmp.x = posit.x; //временной переменной присваиваются координаты человека
	tmp.y = posit.y; //временной переменной присваиваются координаты человека
	if(App.panel.thingsAtField[App.kas1.y*App.N+App.kas1.x]&&App.k.k1IsWorking) { // проверяется, работает ли касса
		
		tmp.x = App.kas1.x; //временной переменной присваивается значение кассы
		tmp.y = App.kas1.y;//временной переменной присваивается значение кассы
		if(!checkForASeat()) wantsToExit = true; //если места за столом для человека нет, он хочет к выходу
		else {
			
			App.k.kassa1.addMoney(money); //в другом случае платит в кассу все свои деньги
			money = 0; //деньги человека обнуляются
		}
		
	}
	else {
		if(App.panel.thingsAtField[App.kas2.y*App.N+App.kas2.x]&&App.k.k2IsWorking) { // проверяется, работает ли касса 2
			tmp.x = App.kas2.x; //временной переменной присваивается значение кассы 2
			tmp.y = App.kas2.y; //временной переменной присваивается значение кассы 2
			if(!checkForASeat()) wantsToExit = true; //если места за столом для человека нет, он хочет к выходу
			else {
				wantsToExit = false; // человек не хочет к выходу
				App.k.kassa2.addMoney(money);//в другом случае платит в кассу все свои деньги
				money = 0; //деньги человека обнуляются
			}
			
		}
	}
	return tmp; //значение временной переменной возвращается в вызывающую подпрограмму
}
public koord goBuy() { //клиент идет к прилавку покупать еду
	koord tmp = new koord(); //создается временная переменная
	Random r = new Random(); //для генерации случайных чисел
	int c = r.nextInt()%App.numberOfFood; //с - случайный номер блюда, не больше, чем количество блюд, которое возьмет клиент
	if(c<0)c*=(-1); // если с меньше нуля, то домножаем на -1
	if(App.panel.thingsAtField[5*App.N+App.arrOFFoodPosit[c].x]) { //если место не занято другим клиентом
		tmp.x = App.arrOFFoodPosit[c].x; // присваивание временной переменной позиции еды
		tmp.y = 5; // по игреку всегда фиксированое значение
	}
	else {
		if(App.panel.thingsAtField[App.ochered[App.maxOchered-1].y*App.N+App.ochered[App.maxOchered-1].x]== false) { //встает в очередь
			tmp.x = 100; //чтобы человек исчез с экрана
		reasonOfExit  = App.reasons[0]; //причина ухода - слишком много людей
		if(forLabel==true)App.panel.drawString(this.name,this.reasonOfExit); //рисуется строка о том, что клиент ушел и по какой причине
		forLabel = false; //чтобы надпись не появилась больше одного раза
		}
		for(int i = 0;i<App.maxOchered;i++) { //цикл по размеру очереди
			if(App.panel.thingsAtField[App.ochered[App.maxOchered-i-1].y*App.N+App.ochered[App.maxOchered-i-1].x]== true) tmp = App.ochered[App.maxOchered-i-1];
		// движение человека по очереди
		}
	}
	
	return tmp; //возвращение значения функцией
}
public koord selectPosit() { //выбор позиции клиента для следующего шага
	wantsToToilet++; //чтобы человек захотел в туалет
	koord k = new koord(); //временная переменная для перемещения человека
	k.x = posit.x; //присваивается текущее значение человека
	k.y = posit.y; //присваивается текущее значение человека
	if(posit.x == (App.N-1)) { //если у человека начальное значение
		k.x = App.checkMoney.x; //человек встает на точку проверки денег
		k.y = App.checkMoney.y;//человек встает на точку проверки денег
	}
	if(posit.y==App.checkMoney.y) { //если человек на позиции проверки денег
		if(checkForMoney()==1) { //вызывается подпрограмма проверки денег
			App.textArea.setText(App.textArea.getText()+ "\n"+ name + " идет покупать"); //вывод на форму
			k.x = goBuy().x; //вызывается подпрограмма покупки еды
			k.y = goBuy().y;//вызывается подпрограмма покупки еды
			
		return k; //работа подпрограммы прерывается
		}
		else {
			reasonOfExit = App.reasons[1]; //причина ухода меняется на "недостаточно денег на еду"
			k.x = 100; //человек уходит с поля
			if(forLabel==true)App.panel.drawString(this.name,this.reasonOfExit); //рисуется строка, информирующая об уходе человека
			forLabel = false; //чтобы сообщение больше не появлялось
			return k; //работа подпрограммы прерывается
		}
	}
	for(int i = 0;i<App.numberOfFood;i++) { //цикл по количеству блюд
		if(posit.x == App.arrOFFoodPosit[i].x && posit.y==5) { //если человек стоит рядом с данным блюдом
		if(!hasAFood)	App.textArea.setText(App.textArea.getText()+ "\n"+ name + " берет"+App.namesOfFood[i]); //сообщенние в форму выводится один раз
			food = new Food(i); //еда с необходимым номером и i-ым названием создается
			hasAFood = true; //у клиента есть еда
			if(food.getFly()) hasAFly = true; //если в еде есть муха, у клиента тоже есть муха
			if(!hasAFood)App.textArea.setText(App.textArea.getText()+ "\n"+ name + " платит"); //сообщенние в форму выводится один раз
			k.x = goPay().x; //вызывается подпрограмма оплаты
			k.y = goPay().y; //вызывается подпрограмма оплаты
			}
	}
	for(int i = 0;i<App.maxOchered;i++) {//для выхода из очереди
		if(posit.x == App.ochered[i].x) k = goBuy();//если есть место, человек идет покупать, иначе двигается по очереди
	}
	if(!wantsToExit&&((posit.x ==App.kas1.x&&posit.y ==App.kas1.y)||(posit.x ==App.kas2.x&&posit.y ==App.kas2.y))) { //если человек на любой кассе и не хочет уходить
		koord asd = new koord(); //создается новая временная переменная
		asd = goEat(); //вызывается подпрограмма поиска стола для съедения еды
		k.x= asd.x; //координаты нового места присваиваются
		k.y= asd.y;//координаты нового места присваиваются
	}
	for(int i = 0;i<App.NumberOfSeats;i++) { //цикл по количеству сидений
		for(int j = 0;j<App.numberOfTables;j++) { //цикл по количеству столов
			if(posit.x==App.k.tables[j].getPosOfSeat(i).x&&posit.y==App.k.tables[j].getPosOfSeat(i).y) { //если позиция человека соответствует позиции сиденья
				table = j;//стол запоминается
				seatNumber = i; //сиденье запоминается
				if(steps >= App.forEating) { //если человек просидел за столом достаточное количество времени
				App.k.tables[table].goAway(seatNumber); //клиент освобождает занятое сиденье
				k = App.window;//координаты меняются на координаты окна приема грязной посуды
				System.out.println(name + " уходит");//вывод собщения в форму
				return k;//возвращение новых координат клиенту
				}
				steps++;//счетчик сидения за столом увеличивается
				}
		}
	}
	if(((posit.x==App.toilet.x)&&(posit.y == App.toilet.y))) { //если клиент находится в туалете
		k.x =100; //человек исчезает с формы
		if(hasAFly)App.panel.drawString(name, App.reasons[2]);//выводится строка с причиной ухода
		else App.panel.drawString(name, App.reasons[3]); //выводится строка с причиной ухода
	}
	if((posit.x==App.window.x&&posit.y == App.window.y)) { //если человек находится у окна приема посуды
		App.textArea.setText(App.textArea.getText()+ "\n"+ name + "  сдал(а) посуду");//вывод сообщения в форму
		if(wantsToToilet ==  App.GoToTheToilet) {k = App.toilet;App.textArea.setText(App.textArea.getText()+ "\n"+ name + " идет в туалет");}//если человек хочет в туалет
		else {
			k.x =100; //исчезает с формы
			if(hasAFly)App.panel.drawString(name, App.reasons[2]); //выводится строка с причиной ухода
			else App.panel.drawString(name, App.reasons[3]); //выводится строка с причиной ухода
		}
	}
	wantsToToilet++; //чтобы человек захотел в туалет
	wantsToToilet++; //чтобы человек захотел в туалет
if(wantsToExit) {//если человек хочет к выходу
	k.x = 100; //исчезает сформы
	
	App.panel.drawString(name, App.reasons[0]); //вывод строки с причиной ухода
	
}
	return k;//возвращение значения функцией
}
public int moving(Graphics2D g) { //подпрограмма вычисления движения в нужную точку
	koord ko = new koord(); //создание новой временной переменной
	ko = selectPosit(); //вызывается подпрограмма выбора новой позиции клиента
	posit.x = ko.x; //новая позиция присваивается позиции клиента
	posit.y= ko.y; //новая позиция присваивается позиции клиента
	if(posit.x!=100)App.panel.thingsAtField[ko.y*App.N+ko.x] = false;//проверяется, можно ли идти в выбранную точку
	Random r = new Random(); //для генерации случайных чисел
	int c = r.nextInt()%App.numberOfFood; //случайное число, меньше, чем число блюд
	Canvas.drawThings(posit.x, posit.y, 1, 1, g); //отрисовка человека на форме
	return 0; //возвращение нулевого значения
}
public Client() { //конструктор без параметров
	forLabel = true;//для вывода сообщения об уходе
	steps = 0; //шаги обнуляются
	Random r = new Random(); //для генерации случайных чисел
	int c = r.nextInt()%App.NumberOfNames; //имя выбирается случайным образом
	if(c<0) c*=(-1); //число не должно быть отрицательным
	name = App.names[c]; //имя присваивается
	c = r.nextInt()%100; //следующее случайное число
	if(c<0) c*=(-1);//число не должно быть отрицательным
	wantsToToilet = c; //насколько человек хочет в туалет
	c = r.nextInt()%App.MaxMoney; //генерируется случайное число
	if(c<0) c*=(-1);//число не должно быть отрицательным
	money = c; //деньги присваиваются от переменной с
	c = r.nextInt()%5+5; //создается новая переменная
	if(c<0) c*=(-1);//число не должно быть отрицательным
	eatingTime = c; // присваивается время, необходимое для еды
	reasonOfExit = App.reasons[0]; // ставится стандартная причина ухода
	posX = posY = (App.N-1);// ставится стандартная позиция
	posit = new koord(); //создается переменная для позиции
	posit.x = posX; //позиция присваивается от интовых переменных
	posit.y = posY;//позиция присваивается от интовых переменных
	App.textArea.setText(App.textArea.getText()+ "\n"+ name + " пришел(а)"); //вывод сообщения в форму
	
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
