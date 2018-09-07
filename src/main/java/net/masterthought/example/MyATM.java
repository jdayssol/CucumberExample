package net.masterthought.example;

public class MyATM {

	private int money;

	public MyATM(int money) {
		this.money = money;
	}

	public int requestMoney(CreditCard creditCard, int amount) {
		if (!creditCard.isValid() || amount > money) {
			return 0;
		}
		
		amount = creditCard.getAccount().getMoney(amount);
		
		money = money - amount;

		return amount;
	}

}
