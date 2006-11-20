package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstrate JOptionPane
 * @author Ian Darwin
 */
@SuppressWarnings("serial")
public class JOptionDemo extends JFrame {

	// Constructor
	JOptionDemo(String s) {
		super(s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		JButton b = new JButton("Informational");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionDemo.this,
					"This is your information: etaoin shrdlu", "Coded Message",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Warning");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionDemo.this,
					"This is your warning: etaoin shrdlu", "Coded Message",
					JOptionPane.WARNING_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Error");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionDemo.this,
					"This is your error message: etaoin shrdlu", "Coded Message",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		cp.add(b);

		b = new JButton("Goodbye!");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cp.add(b);

		// size the main window
		pack();
	}

	public static void main(String[] arg) {
		JOptionDemo x = new JOptionDemo("Testing 1 2 3...");
		x.setVisible(true);
	}
}
