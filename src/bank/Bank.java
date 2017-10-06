package bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Random;

import gui.CustomOptionPane;

public class Bank implements BankProc {

	private HashMap<Integer, Person> owner = new HashMap<Integer, Person>();
	private Random rand = new Random();
	private OutputStream fo;
	private ObjectOutputStream objecto;
	private InputStream fi;
	private ObjectInputStream objecti;

	public void addPerson(Person person) {
		try {
			assert !person.equals(null);
			assert contains(person);
			assert person.isWellFormed();
			Integer id = 0;
			do {
				id = rand.nextInt(1000);
			} while (owner.containsKey(id));
			int preSize = owner.size();
			owner.put(id, person);
			person.setId(id);
			assert owner.size() > preSize;
			assert isWellFormed();
		} catch (AssertionError e) {
			new CustomOptionPane()
					.displayGUI("Error adding owner at pre or post condition in class 'Bank: addPerson!'");
		}

	}

	public HashMap<Integer, Person> getAccounts() {
		return owner;
	}

	public void removePerson(Integer id) {
		try {
			assert id > 0 && id != null;
			int preSize = owner.size();
			owner.remove(id);
			assert owner.size() < preSize;
			assert isWellFormed();
		} catch (AssertionError e) {
			new CustomOptionPane().displayGUI("Error removing Person at pre or post condition class 'Bank");
		}

	}

	public void addAcounts(Account ac, Integer id) {
		try {
			assert !id.equals(null) || !ac.equals(null);
			assert ac.isWellFormed();
			int preSize = owner.get(id).getAccounts().size();
			owner.get(id).addAccount(ac);
			assert owner.get(id).getAccounts().size() > preSize;
			assert isWellFormed();
		} catch (AssertionError e) {
			new CustomOptionPane().displayGUI("Error in adding account pre o post condition at 'Bank: addAcounts'");
		}

	}

	public void removeAccounts(Account ac, Integer id) {
		try {
			assert id > 0 && !id.equals(null);
			assert !ac.equals(null) && ac.isWellFormed();
			int preSize = owner.get(id).getAccounts().size();
			owner.get(id).removeAccount(ac);
			assert owner.get(id).getAccounts().size() < preSize;
			assert isWellFormed();
		} catch (AssertionError e) {
			new CustomOptionPane()
					.displayGUI("Error in removing account pre o post condition at 'Bank: removeAcounts'");
		}
	}

	@Override
	public void writeAccounts() throws IOException {
		try {
			assert isWellFormed();
			fo = new FileOutputStream("Accounts.txt");
			objecto = new ObjectOutputStream(fo);
			objecto.writeObject(owner);
		} catch (Exception e) {
			new CustomOptionPane().displayGUI("pre or post Condition Error while writing to file!");
		} finally {
			fo.close();
			objecto.close();
		}

	}

	@SuppressWarnings("unchecked")

	public void readAccounts() throws IOException {
		try {
			assert owner.size() == 0;
			fi = new FileInputStream("Accounts.txt");
			objecti = new ObjectInputStream(fi);
			owner = (HashMap<Integer, Person>) objecti.readObject();
			assert isWellFormed();
		} catch (Exception e) {
			new CustomOptionPane().displayGUI("Pre or post Condition Error while reading from file!");
		} finally {
			fi.close();
			objecti.close();
		}

	}

	public boolean contains(Person person) {
		for (Person person1 : owner.values()) {
			if (person1.getCnp().equals(person.getCnp())) {
				new CustomOptionPane().displayGUI("Person with that identity already exist!");
				return false;
			}
		}
		return true;
	}

	public boolean isWellFormed() {
		assert !owner.equals(null);
		return true;
	}

}
