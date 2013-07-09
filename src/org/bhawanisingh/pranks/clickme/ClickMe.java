package org.bhawanisingh.pranks.clickme;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Bhawani Singh
 */
public class ClickMe {

	private JFrame f;
	private JButton clickMe = new JButton("Click Me");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dim = this.toolkit.getScreenSize();
	private Random randomGenerator = new Random();
	private int x;
	private int y;
	private int ht = (this.dim.height - GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height) + 22;

	ClickMe() {
		this.f = new JFrame("");
		this.f.setSize(70, 22);
		this.f.setLayout(null);
		this.f.setAlwaysOnTop(true);
		this.f.setLocationRelativeTo(null);
		this.f.setUndecorated(true);
		this.clickMe.setBounds(0, 0, 70, 22);
		this.clickMe.setBorder(null);
		this.f.add(this.clickMe);
		this.f.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.f.setVisible(true);
		this.clickMe.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ClickMe.this.mouseEntered();
			}
		});
		this.clickMe.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent evt) {
				ClickMe.this.focusLost();
			}
		});
		this.clickMe.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ClickMe.this.clickMeActionPerformed();
			}
		});
		this.f.addWindowStateListener(new java.awt.event.WindowStateListener() {
			@Override
			public void windowStateChanged(java.awt.event.WindowEvent evt) {
				ClickMe.this.stateChanged();
			}
		});
	}

	void mouseEntered() {

		this.x = this.randomGenerator.nextInt(this.dim.width - 70);
		this.y = this.randomGenerator.nextInt(this.dim.height - this.ht);
		if ((this.x < this.ht) || (this.x > (this.dim.width - this.ht)) || (this.y < this.ht) || (this.y > (this.dim.height - this.ht))) {
			this.mouseEntered();
		}
		this.f.setLocation(this.x, this.y);
	}

	void focusLost() {
		this.f.setState(Frame.NORMAL);
	}

	void clickMeActionPerformed() {
		JOptionPane.showMessageDialog(this.clickMe, "Thanks For Playing", "Bye Bye", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	void stateChanged() {
		this.f.setState(Frame.NORMAL);
	}

	public static void main(String... s) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		new ClickMe();
	}
}