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
 * 
 *         This is a teaser app that won't allow you to click on the button.
 *         Only way to stop the app is to press enter while the app is in focus.
 *         You can change the code of 'clickMeActionPerformed()' function to change the way the app
 *         behaves when enter is pressed.
 *         Remove the code from the 'clickMeActionPerformed()' function and then app will only be
 *         closed from the
 *         task-manager
 */
public class ClickMe {

	private JFrame f;
	private JButton clickMe = new JButton("Click Me");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dim = this.toolkit.getScreenSize();
	private Random randomGenerator = new Random();
	private int x;
	private int y;

	/**
	 * This can create some issues on Linux systems (but for me its ok)
	 */
	private int ht = (this.dim.height - GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height) + 22;

	ClickMe() {
		this.f = new JFrame("");
		this.f.setSize(70, 22);
		this.f.setLayout(null);

		/**
		 * App will remains on top no matter what
		 */
		this.f.setAlwaysOnTop(true);

		/**
		 * Position the app window at the center of the screen
		 */
		this.f.setLocationRelativeTo(null);

		/**
		 * Window frame will not be visible
		 */
		this.f.setUndecorated(true);
		this.clickMe.setBounds(0, 0, 70, 22);
		this.clickMe.setBorder(null);
		this.f.add(this.clickMe);

		/**
		 * Window will not close on hitting 'x' on the frame (This is of no use since the window is
		 * undecorated)
		 */
		this.f.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.f.setVisible(true);

		/**
		 * This method is invoked when the mouse hover over the button
		 */
		this.clickMe.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ClickMe.this.mouseEntered();
			}
		});

		/**
		 * This method checks that the app remains in foreground (in focus)
		 */
		this.clickMe.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent evt) {
				ClickMe.this.focusLost();
			}
		});

		/**
		 * Only way to close the app
		 */
		this.clickMe.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ClickMe.this.clickMeActionPerformed();
			}
		});

		/**
		 * This stops the app from getting minimize
		 */
		this.f.addWindowStateListener(new java.awt.event.WindowStateListener() {
			@Override
			public void windowStateChanged(java.awt.event.WindowEvent evt) {
				ClickMe.this.stateChanged();
			}
		});
	}

	/**
	 * Method to generate the random location on the screen when mouse enter the app window
	 */
	void mouseEntered() {

		this.x = this.randomGenerator.nextInt(this.dim.width - 70);
		this.y = this.randomGenerator.nextInt(this.dim.height - this.ht);
		if ((this.x < this.ht) || (this.x > (this.dim.width - this.ht)) || (this.y < this.ht) || (this.y > (this.dim.height - this.ht))) {
			this.mouseEntered();
		}
		this.f.setLocation(this.x, this.y);
	}

	/**
	 * App window will never loss focus
	 */
	void focusLost() {
		this.f.setState(Frame.NORMAL);
	}

	/**
	 * When user press enter this method is invoked provided that app is in focus
	 */
	void clickMeActionPerformed() {
		JOptionPane.showMessageDialog(this.clickMe, "Thanks For Playing", "Bye Bye", JOptionPane.INFORMATION_MESSAGE);

		/**
		 * To close the app.
		 * You can replace 'System.exit(0);' with 'JFrame.dispose();'
		 */
		System.exit(0);
	}

	void stateChanged() {
		/**
		 * Frame.NORMAL means the app window is in normal state
		 */
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