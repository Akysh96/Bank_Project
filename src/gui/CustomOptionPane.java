package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class CustomOptionPane implements Serializable {

	private static final long serialVersionUID = 855257560007748718L;
	private String message;

	public void displayGUI(String message) {
		UIManager.put("OptionPane.background", Color.class);
		this.message = message;
		JOptionPane.showConfirmDialog(null, getPanel(), "JOptionPane Example : ", JOptionPane.CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	private JPanel getPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(message);
		label.setFont(new Font("Times Roman", Font.BOLD, 12));
		ImageIcon image = null;
		try {
			image = new ImageIcon(
					ImageIO.read(new URL("http://icons.iconarchive.com/icons/kyo-tux/aeon/48/Sign-Delete-icon.png")));
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		label.setIcon(image);
		label.setIconTextGap(20);
		panel.add(label);
		panel.setPreferredSize(new Dimension(500, 80));

		return panel;
	}

}
