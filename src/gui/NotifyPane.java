package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class NotifyPane {

	private String message;

	public void displayGUI(String message) {
		this.message = message;
		UIManager.put("OptionPane.background", Color.white);
		JOptionPane.showConfirmDialog(null, getPanel(), "JOptionPane Example : ", JOptionPane.CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	private JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.WHITE);
		ImageIcon image = new ImageIcon(getClass().getResource("transparent.gif"));
		JLabel label = new JLabel(image);
		JTextArea text = new JTextArea(message);
		text.setFont(new Font("Times Roman", Font.PLAIN, 15));
		label.setFont(new Font("Times Roman", Font.BOLD, 12));
		panel.add(label, BorderLayout.WEST);
		panel.add(text, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(350, 100));

		return panel;
	}

}
