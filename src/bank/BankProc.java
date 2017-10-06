package bank;

import java.io.IOException;

public interface BankProc{
	
	public void addPerson(Person person);
	
	public void removePerson(Integer id);
	
	public void addAcounts(Account ac, Integer id);
	
	public void removeAccounts(Account ac, Integer id);
	
	public void writeAccounts() throws IOException;
	
	public void readAccounts() throws IOException;


}
