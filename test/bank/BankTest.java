package bank;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BankTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testAddPerson() {
		Bank bank = new Bank();
		Person p = new Person("Nate", "blabla", "11233214221456686");
		SavingAccount ac = new SavingAccount(100.0);
		p.addAccount(ac);
		bank.addPerson(p);
		assertEquals(true, bank.isWellFormed());
	}

	@Test
	public void testGetAccounts() {
		Bank bank = new Bank();
		Person p = new Person("Felix", "goowill", "3213213231234");
		SpendingAccount ac = new SpendingAccount(50.0);
		Person p1 = new Person("Ken", "lawler", "3213343433434343");
		SavingAccount ac1 = new SavingAccount(50.0);
		p1.addAccount(ac1);
		p.addAccount(ac);
		bank.addPerson(p);
		bank.addPerson(p1);
		assertEquals(2, bank.getAccounts().size());
	}

	@Test
	public void testRemovePerson() {
		Bank bank = new Bank();
		Person p = new Person("Mark", "Pettis", "13232342456564542");
		SavingAccount ac = new SavingAccount(10.0);
		p.addAccount(ac);
		bank.addPerson(p);
		assertEquals(1, bank.getAccounts().size());
		bank.removePerson(p.getId());
		assertEquals(0, bank.getAccounts().size());
	}

	@Test
	public void testAddAcounts() {
		Bank bank = new Bank();
		Person p = new Person("Blake", "wrwafa", "121312323122121312");
		SpendingAccount ac = new SpendingAccount(105.0);
		p.addAccount(ac);
		bank.addPerson(p);
		SavingAccount ac1 = new SavingAccount(100.0);
		bank.addAcounts(ac1, p.getId());
		assertEquals(2, p.getAccounts().size());
	}

	@Test
	public void testRemoveAccounts() {
		Bank bank = new Bank();
		Person p = new Person("Bill", "Parker", "12321232312321321");
		SavingAccount ac = new SavingAccount(100.0);
		SpendingAccount ac1 = new SpendingAccount(100.0);
		p.addAccount(ac);
		p.addAccount(ac1);
		bank.addPerson(p);
		bank.removeAccounts(ac, p.getId());
		assertEquals(1, p.getAccounts().size());
	}

}
