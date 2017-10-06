package bank;

import gui.CustomOptionPane;

public class SpendingAccount extends Account {

	private static final long serialVersionUID = -4478989318409251375L;

	public SpendingAccount() {

	}

	public SpendingAccount(Double money) {
		this.money = money;
	}

	public void takeMoney(Double money) {
		try {
			assert money >= 10.0 && money % 10 == 0;
			assert this.money >= money;
			assert isWellFormed();
			double pre = this.money;
			this.money -= money;
			assert this.money < pre;
			assert isWellFormed();
			notifyObserver(
					"Money withdrawn: " + money + "\nFrom account: " + getType() + "\nCurrent Amount: " + this.money);
		} catch (AssertionError e) {
			new CustomOptionPane()
					.displayGUI("Please insert a valid amount of money (Sum has to be divideable by 10!)");
		}
	}

	public boolean isWellFormed() {
		if (money.equals(null) && money < 0.0) {
			return false;
		}
		return true;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return "\nMinimum amount to withdraw: 10";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Spending Account";
	}

}
