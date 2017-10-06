package bank;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import gui.CustomOptionPane;
import gui.NotifyPane;

public class Person implements Serializable, Observer {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name, surName, cnp, date;
	private ArrayList<Account> accounts;
	private DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private Date dateobj = new Date();

	public Person(String name, String surName, String cnp) {
		this.name = name;
		this.surName = surName;
		this.cnp = cnp;
		this.date = df.format(dateobj).toString();
		accounts = new ArrayList<Account>();
	}

	public void addAccount(Account ac) {
		try {
			assert !ac.equals(null) && ac.isWellFormed();
			assert contains(ac);
			int preSize = accounts.size();
			accounts.add(ac);
			assert accounts.size() > preSize;
			assert isWellFormed();
		} catch (AssertionError e) {
			new CustomOptionPane().displayGUI("Error adding Account at pre or post condition class 'Person'");
		}
	}

	public void removeAccount(Account ac) {
		try {
			assert !ac.equals(null) && ac.isWellFormed();
			int preSize = accounts.size();
			for (Iterator<Account> iterator = accounts.iterator(); iterator.hasNext();) {
				Account ac1 = iterator.next();
				if (ac1.getType().equals(ac.getType())) {
					iterator.remove();
				}
			}
			assert accounts.size() < preSize;
			update("\nYour Account: '" + ac.getType() + "' has \nbeen Removed!");
		} catch (AssertionError e) {
			new CustomOptionPane().displayGUI("Error removing Account at pre or post condition class 'Person'");
		}

	}

	public boolean isWellFormed() {
		if (name.length() == 0 || surName.length() == 0 || cnp.length() == 0 || accounts.size() == 0) {
			new CustomOptionPane().displayGUI("Please fill in the fields!");
			return false;
		}
		if (cnp.length() < 10) {
			new CustomOptionPane().displayGUI("CNP has to be atleast 10 numerical characters!");
			return false;
		}
		return true;
	}

	public String getDetails(String type) {
		Account ac = getAccount(type);
		return "Details:\n" + "Date of Register: " + getDate() + "\nId: " + getId() + "\nOwner: " + getName() + " "
				+ getSurName() + "\nCNP: " + getCnp() + "\nAccount type: " + ac.getType() + "\nAvailable Amount: "
				+ ac.getMoney() + ac.getDetails();
	}

	public Account getAccount(String type) {
		for (Account ac : accounts) {
			if (ac.getType().equals(type)) {
				return ac;
			}
		}
		return null;
	}

	public boolean contains(Account ac) {
		for (Account ac1 : accounts) {
			if (ac1.getType().equals(ac.getType())) {
				new CustomOptionPane().displayGUI("Account type alread exist!");
				return false;
			}
		}
		return true;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public String getDate() {
		return this.date;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurName() {
		return surName;
	}

	public String getCnp() {
		return cnp;
	}

	@Override
	public void update(String changes) {
		new NotifyPane().displayGUI("Date: " + df.format(dateobj).toString() + "\nOwner: " + getName() + " "
				+ getSurName() + "\n" + changes);
	}

}
