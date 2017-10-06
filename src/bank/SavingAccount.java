package bank;

import gui.CustomOptionPane;

public class SavingAccount extends Account {

	private static final long serialVersionUID = -1825883238680084591L;

	public SavingAccount() {
	}

	public SavingAccount(Double money) {
		this.money = money;
	}

	@Override
	public boolean isWellFormed() {
		if (money.equals(null) || money < 0.0) {
			return false;
		}
		return true;
	}

	public void takeMoney(Double money) {
		try {
			if (money < 100.0) {
				new CustomOptionPane().displayGUI("Minimum amount of money to withdraw is 100 euros!");
				return;
			}
			if (money > this.money) {
				if (time == 0) {
					new CustomOptionPane().displayGUI("Insert a time value!");
					return;
				}
				this.interest += money * (rate * time);
				this.money = 0.0;
			} else {
				this.money -= money;
			}
			assert isWellFormed();
			notifyObserver("\nMoney withdrawn: " + money + "\nFrom Account: " + getType());
		} catch (AssertionError e) {
			new CustomOptionPane().displayGUI("Error at pre or post condition class 'SavingAccount: takeMoney'");
		}
	}

	@Override
	public String getType() {
		return "Saving Account";
	}

	public String getDetails() {

		return "\nInterest: " + getInterest() + "\nRate: " + getRate();
	}

}
