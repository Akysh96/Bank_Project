package bank;

import java.io.Serializable;
import gui.CustomOptionPane;

public abstract class Account implements Serializable, Observable {

	private static final long serialVersionUID = 1L;

	protected Double money = 0.0;
	protected Observer obs;
	protected CustomOptionPane optionPane = new CustomOptionPane();
	protected Double interest = 0.0;
	protected final Double rate = 0.7;
	protected Integer time;

	public void addMoney(Double money) {
		try {
			assert money > 0.0 && isWellFormed();
			Double pre = this.money;
			this.money += money;
			assert this.money > pre;
			assert isWellFormed();
			notifyObserver("Added Money: " + money + "\nCurrent Amount: " + this.money);
		} catch (AssertionError e) {
			if (money <= 0) {
				optionPane.displayGUI("Please insert a valid amount of money to add!");
			}
			optionPane.displayGUI("Failure at pre or post condition in adding money Class: SavingAccount");
		}
	};

	@Override
	public void addObserver(Observer obs) {
		try {
			assert !obs.equals(null);
			this.obs = obs;
			assert !this.obs.equals(null);
		} catch (AssertionError e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyObserver(String changes) {
		obs.update(changes);

	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getRate() {
		return rate;
	}

	public Observer getObserver() {
		return obs;
	}

	public abstract void takeMoney(Double money);

	public abstract String getDetails();

	public abstract String getType();

	public abstract boolean isWellFormed();

}