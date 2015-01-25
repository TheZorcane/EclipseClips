package clipse;

import graphics.ClipseGraphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ai.PersonalDataFileHandler;

/**
 * Main Clip class
 * 
 * @author Dvir
 *
 */
public class Clipse implements Runnable, MouseListener {

	private final ClipseGraphics graphics;
	public static PersonalDataFileHandler personalData;

	private boolean running;

	private static int mouseClicks = 0;

	public Clipse() {
		running = true;
		personalData = new PersonalDataFileHandler();
		this.graphics = new ClipseGraphics(Status.Normal);

		graphics.addMouseListener(this);
		Thread t = new Thread(this);
		t.start();
	}

	public Clipse(Status status) {

		this.graphics = new ClipseGraphics(status);
	}

	public void say(String message) {
		graphics.say(message);
	}

	public void changeClipStatus(Status status) {
		graphics.setStatus(status);

	}

	public static void main(String[] args) {
		Clipse clip = new Clipse();
		clip.welcomeMessage();
	}

	private void welcomeMessage() {
		// say("Hey " + personalData.getName() + ", How are you?");

	}

	@Override
	public void run() {

		while (running) {
			if (mouseClicks > 10) {
				say("You seems to left-clicking alot. Can I assist?");
				mouseClicks = 0;
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClicks++;
		System.out.println(mouseClicks);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
