package bank;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AccountTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testAddMoney() {
		Person pe = new Person("pat", "dwdw", "13124213514224");
		Account ac = new SavingAccount();
		ac.addObserver(pe);
		ac.addMoney(100.0);
		assertEquals(true, ac.isWellFormed());
	}

	@Test
	public void testAddObserver() {
		Person pe = new Person("Dave", "daawd", "12321321321321231");
		Account ac = new SpendingAccount();
		ac.addObserver(pe);
		ac.notifyObserver("\n  Observer added!");
		assertEquals(pe, ac.getObserver());
	}

	@Test
	public void testTakeMoney() {
		Person pe = new Person("Robert", "dawdwad", "14144142422355124");
		Account ac = new SavingAccount(200.0);
		ac.addObserver(pe);
		ac.takeMoney(100.0);
		String current = String.valueOf(100.0);
		assertEquals(current, String.valueOf(ac.getMoney()));

	}

}
