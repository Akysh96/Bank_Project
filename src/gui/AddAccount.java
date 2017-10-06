package gui;

import bank.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

public class AddAccount extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField name, surName, cnp;
	private JLabel label, dd, mm, yy, sum;
	private JButton add = new JButton("add");
	private JButton add1 = new JButton("add 2nd account");
	private JButton removePerson = new JButton("Remove Person");
	private JButton removeAccount = new JButton("Remove Account");
	private ArrayList<String> age = new ArrayList<String>();
	private JComboBox<Object> month, day, year;
	private JComboBox<String> type = new JComboBox<String>();
	private JComboBox<String> type1 = new JComboBox<String>();
	private String[] months = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
	private JFormattedTextField deposit = new JFormattedTextField(new Double(0));
	private JFormattedTextField deposit1 = new JFormattedTextField(new Double(0));
	private NumberFormat f = NumberFormat.getInstance();
	private Bank bank = new Bank();
	private GetTable table1 = new GetTable();
	private JTable table;
	private JScrollPane pane = new JScrollPane();

	public AddAccount() {
		setLayout(null);

		FocusListener highlighter = new FocusListener() {

			public void focusGained(FocusEvent e) {
				e.getComponent().setBackground(Color.GREEN);
			}

			public void focusLost(FocusEvent e) {
				e.getComponent().setBackground(UIManager.getColor("TextField.background"));
			}
		};
		name = new JTextField();
		surName = new JTextField();
		cnp = new JTextField();
		readAccounts();
		reFresh();
		initCombobox();
		name.addFocusListener(highlighter);
		surName.addFocusListener(highlighter);
		cnp.addFocusListener(highlighter);
		deposit.addFocusListener(highlighter);
		deposit1.addFocusListener(highlighter);
		name.setBounds(170, 70, 200, 20);
		surName.setBounds(170, 140, 200, 20);
		cnp.setBounds(170, 210, 200, 20);
		deposit.setBounds(170, 360, 100, 20);
		deposit1.setBounds(720, 360, 100, 20);
		type.addItem("Saving Account");
		type.addItem("Spending Account");
		type1.addItem("Saving Account");
		type1.addItem("Spending Account");
		label = new JLabel("<html>Name:<br><br><br>Surname:<br><br><br>CNP:<br><br><br>Age:<br><br><br>Deposit:");
		mm = new JLabel("mm");
		dd = new JLabel("dd");
		yy = new JLabel("yy");
		sum = new JLabel("Money Amount:");
		label.setFont(new Font("Times Roman", Font.ITALIC + Font.BOLD, 18));
		label.setBounds(50, 50, 500, 350);
		dd.setBounds(170, 285, 35, 20);
		day.setBounds(190, 285, 35, 20);
		mm.setBounds(230, 285, 35, 20);
		month.setBounds(250, 285, 50, 20);
		yy.setBounds(305, 285, 35, 20);
		year.setBounds(320, 285, 50, 20);
		add.setBounds(200, 400, 80, 30);
		add1.setBounds(610, 400, 150, 30);
		removePerson.setBounds(610, 440, 150, 30);
		removeAccount.setBounds(780, 440, 150, 30);
		sum.setFont(new Font("Times Roman", Font.ITALIC + Font.BOLD, 15));
		sum.setBounds(580, 355, 150, 30);
		type.setBounds(300, 400, 150, 30);
		type1.setBounds(780, 400, 150, 30);
		pane.setBounds(480, 100, 500, 200);
		add(pane);
		add(label);
		add(name);
		add(surName);
		add(cnp);
		add(deposit);
		add(deposit1);
		add(sum);
		add(dd);
		add(day);
		add(mm);
		add(month);
		add(yy);
		add(year);
		add(add);
		add(add1);
		add(removePerson);
		add(removeAccount);
		add(type);
		add(type1);
		setPreferredSize(new Dimension(1000, 500));
		add.addActionListener(new Handler());
		add1.addActionListener(new Handler());
		removePerson.addActionListener(new Handler());
		removeAccount.addActionListener(new Handler());
	}

	private class Handler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(add)) {
				try {
					if (Integer.valueOf((String) year.getSelectedItem()) > 1996) {
						new CustomOptionPane().displayGUI("Age is not respected!");
					} else {
						Account ac = null;
						if (type.getSelectedItem().equals("Saving Account")) {
							ac = new SavingAccount(f.parse(deposit.getText()).doubleValue());
						} else if (type.getSelectedItem().equals("Spending Account")) {
							ac = new SpendingAccount(f.parse(deposit.getText()).doubleValue());
						}
						Person person = new Person(name.getText(), surName.getText(), cnp.getText());
						ac.addObserver(person);
						person.addAccount(ac);
						bank.addPerson(person);
						reFresh();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(add1)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					Account ac = null;
					if (type1.getSelectedItem().equals("Saving Account")) {
						ac = new SavingAccount(f.parse(deposit1.getText()).doubleValue());
					} else if (type1.getSelectedItem().equals("Spending Account")) {
						ac = new SpendingAccount(f.parse(deposit1.getText()).doubleValue());
					}
					ac.addObserver(bank.getAccounts().get(id));
					bank.addAcounts(ac, id);
					reFresh();
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Please select an id from the Table!");
				}
			} else if (e.getSource().equals(removePerson)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					bank.removePerson(id);
					reFresh();
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Please select an id from the Table!");
				}

			} else if (e.getSource().equals(removeAccount)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					Account ac = null;
					if (table.getValueAt(table.getSelectedRow(), 4).equals("Saving Account")) {
						ac = new SavingAccount();
					} else if (table.getValueAt(table.getSelectedRow(), 4).equals("Spending Account")) {
						ac = new SpendingAccount();
					}
					bank.removeAccounts(ac, id);
					if (bank.getAccounts().get(id).getAccounts().size() == 0) {
						bank.removePerson(id);
					}
					reFresh();
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Please select an id from the Table!");
				}
			}

		}

	}

	public void reFresh() {
		try {
			name.setText("");
			surName.setText("");
			cnp.setText("");
			deposit.setText("0");
			bank.writeAccounts();
			table = table1.getTable(bank.getAccounts());
			pane.getViewport().add(table);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readAccounts() {
		try {
			bank.readAccounts();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void initCombobox() {
		for (int years = 1950; years <= Calendar.getInstance().get(Calendar.YEAR); years++) {
			age.add(String.valueOf(years));
		}
		year = new JComboBox<Object>(age.toArray());
		age.clear();
		for (int i = 0; i < 12; i++) {
			age.add(months[i]);
		}
		month = new JComboBox<Object>(age.toArray());
		age.clear();
		for (int days = 1; days <= 31; days++) {
			age.add(String.valueOf(days));
		}
		day = new JComboBox<Object>(age.toArray());
	}

}
