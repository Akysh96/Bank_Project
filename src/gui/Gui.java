package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cards = new JPanel(new CardLayout());
	private JPanel panel = new JPanel();
	private JButton addAccount = new JButton("Add Account");
	private JButton addWithdraw = new JButton("Add or Withdraw Money     ");
	private JButton back = new JButton("Back");
	private AddAccount ac;
	private AddWithDrawMoney ac1;

	public Gui() {

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(350, 100));
		panel.add(addAccount, BorderLayout.CENTER);
		panel.add(addWithdraw, BorderLayout.EAST);
		back.setBounds(10, 10, 100, 25);
		cards.add(panel);
		add(cards);
		Handler handler = new Handler();
		addAccount.addActionListener(handler);
		addWithdraw.addActionListener(handler);
		back.addActionListener(handler);
		pack();
	}

	private class Handler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(addAccount)) {
				ac = new AddAccount();
				ac.add(back);
				cards.removeAll();
				cards.add(ac);
				pack();

			} else if (e.getSource().equals(addWithdraw)) {
				ac1 = new AddWithDrawMoney();
				ac1.add(back);
				cards.removeAll();
				cards.add(ac1);
				pack();
			} else if (e.getSource().equals(back)) {
				cards.removeAll();
				cards.add(panel);
				pack();
			}

		}

	}

}
