package bank;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testAddAccount() {
		Person p = new Person("Dave", "dwadaw", "123213223213212132");
		SavingAccount ac = new SavingAccount(100.0);
		p.addAccount(ac);
		assertEquals(ac, p.getAccount(ac.getType()));
	}

	@Test
	public void testRemoveAccount() {

		Person p = new Person("Mark", "dwaded", "2321323123232132132");
		SpendingAccount ac = new SpendingAccount(100.0);
		p.addAccount(ac);
		p.removeAccount(ac);
		assertEquals(0, p.getAccounts().size());
	}

	@Test
	public void testGetAccounts() {
		Person p = new Person("Javi", "blabla", "214241421441242114");
		SpendingAccount ac = new SpendingAccount(100.0);
		SavingAccount ac1 = new SavingAccount(100.0);
		p.addAccount(ac);
		p.addAccount(ac1);
		assertEquals(2, p.getAccounts().size());
	}

}
