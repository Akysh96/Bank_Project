package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.*;

import bank.*;

public class AddWithDrawMoney extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private GetTable t = new GetTable();
	private Bank bank = new Bank();
	private JLabel label, label1, label2;
	private JFormattedTextField deposit = new JFormattedTextField(new Double(0));
	private JScrollPane pane, pane1;
	private JTextPane details = new JTextPane();
	private JButton add, withdraw, pay;
	private NumberFormat f = NumberFormat.getInstance();
	private JTextField time = new JTextField("0");
	private Font font = new Font("Times Roman", Font.BOLD, 15);

	public AddWithDrawMoney() {
		try {
			bank.readAccounts();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FocusListener highlighter = new FocusListener() {

			public void focusGained(FocusEvent e) {
				e.getComponent().setBackground(Color.GREEN);
			}

			public void focusLost(FocusEvent e) {
				e.getComponent().setBackground(UIManager.getColor("TextField.background"));
			}
		};
		setLayout(null);
		details.setFont(font);
		details.setEditable(false);
		table = t.getTable(bank.getAccounts());
		table.setFillsViewportHeight(true);
		pane = new JScrollPane(table);
		pane1 = new JScrollPane(details);
		pane.setBounds(30, 50, 500, 200);
		deposit.setBounds(200, 300, 100, 30);
		deposit.addFocusListener(highlighter);
		time.setBounds(200, 350, 80, 30);
		time.addFocusListener(highlighter);
		pane1.setBounds(550, 50, 400, 200);
		label = new JLabel("Sum:");
		label.setFont(font);
		label.setBounds(150, 300, 100, 30);
		label1 = new JLabel("Time:");
		label1.setFont(font);
		label1.setBounds(150, 350, 100, 30);
		label2 = new JLabel("per year (for Savings Account only)");
		label2.setFont(font);
		label2.setBounds(290, 350, 250, 30);
		add = new JButton("Add");
		add.setBounds(150, 400, 100, 30);
		withdraw = new JButton("Withdraw");
		withdraw.setBounds(255, 400, 100, 30);
		pay = new JButton("Pay Interest");
		pay.setBounds(700, 300, 100, 30);
		add(pane);
		add(deposit);
		add(time);
		add(pane1);
		add(label);
		add(label1);
		add(label2);
		add(add);
		add(withdraw);
		add(pay);
		setPreferredSize(new Dimension(980, 500));

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				String type = (String) table.getValueAt(table.getSelectedRow(), 4);
				details.setText(bank.getAccounts().get(id).getDetails(type));

			}
		});
		add.addActionListener(new Handler());
		withdraw.addActionListener(new Handler());
		pay.addActionListener(new Handler());
	}

	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(add)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					String type = (String) table.getValueAt(table.getSelectedRow(), 4);
					bank.getAccounts().get(id).getAccount(type).addMoney(f.parse(deposit.getText()).doubleValue());
					bank.writeAccounts();
					details.setText(bank.getAccounts().get(id).getDetails(type));
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Please select an id from the Table!");
				}
			} else if (e.getSource().equals(withdraw)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					String type = (String) table.getValueAt(table.getSelectedRow(), 4);
					if (type.equals("Saving Account")) {
						bank.getAccounts().get(id).getAccount(type).setTime(Integer.valueOf(time.getText()));
					}
					bank.getAccounts().get(id).getAccount(type).takeMoney(f.parse(deposit.getText()).doubleValue());
					bank.writeAccounts();
					details.setText(bank.getAccounts().get(id).getDetails(type));
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Please select an id from the Table!");
				}
			} else if (e.getSource().equals(pay)) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					String type = (String) table.getValueAt(table.getSelectedRow(), 4);
					Account ac = bank.getAccounts().get(id).getAccount(type);
					if (ac.getMoney() >= ac.getInterest()) {
						bank.getAccounts().get(id).getAccount(type).setMoney((ac.getMoney() - ac.getInterest()));
						bank.getAccounts().get(id).getAccount(type).setInterest(0.0);
					} else {
						new CustomOptionPane().displayGUI("Insufficient Funds!");
						return;
					}
					bank.writeAccounts();
					details.setText(bank.getAccounts().get(id).getDetails(type));
				} catch (Exception e1) {
					new CustomOptionPane().displayGUI("Error while Trying to pay Loan!");
				}
			}

		}

	}

}
