package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clipse.Status;

/**
 * 
 * ClipseGraphics responsible for the Clips Graphics
 * 
 * @author ido
 *
 */
public class ClipseGraphics extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;
	public static final int CLIPSE_SIZE_WITDH = 120;
	public static final int CLIPSE_SIZE_HEIGHT = 150;
	Dimension screen;

	private final JFrame frame;
	private JFrame message;
	private int x;
	private int y;
	private Status status;
	private Image currentImg;
	private final boolean saysStuff;
	private final String say;
	private Point mousedowncoor;

	public ClipseGraphics(Status status) {
		screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		this.status = status;
		saysStuff = false;
		say = "";
		// main frame
		frame = new JFrame();
		frame.add(this);
		frame.setSize(CLIPSE_SIZE_WITDH, CLIPSE_SIZE_HEIGHT);
		frame.setLocation((int) (screen.getWidth() - CLIPSE_SIZE_WITDH),
				(int) (screen.getHeight() - CLIPSE_SIZE_HEIGHT));
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(screen);
		frame.setUndecorated(true);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.setBackground(new Color(0, 0, 0, 0));
		setImageByStatus();
		setOpaque(false);

		frame.setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(currentImg, frame.getWidth() - CLIPSE_SIZE_WITDH,
				frame.getHeight() - CLIPSE_SIZE_HEIGHT, CLIPSE_SIZE_WITDH,
				CLIPSE_SIZE_WITDH, null);
		if (saysStuff)
			g.drawString(say, 50, 50);
	}

	public void setImageByStatus() {
		try {
			switch (status) {
			case Normal:
				currentImg = ImageIO.read(new File("Images/clip.png"));
				break;
			case Mad:
				currentImg = ImageIO.read(new File("Images/madClip.gif"));
				break;
			case Builder:
				currentImg = ImageIO.read(new File("Images/BuilderClip.gif"));
				break;
			default:
				this.status = Status.Normal;

				currentImg = ImageIO.read(new File("Images/clip.png"));

				break;
			}
		} catch (IOException e) {
		}

		repaint();
	}

	public void say(String text) {
		System.out.println(text);
		message = new JFrame();
		JPanel panel = new JPanel();
		message.add(panel);

		message.setLocation(
				(int) (screen.getWidth() - CLIPSE_SIZE_WITDH - 100),
				(int) (screen.getHeight() - CLIPSE_SIZE_HEIGHT) - 100);
		// message.setAlwaysOnTop(true);
		// message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(new JLabel(text));
		message.setSize(panel.getPreferredSize());

		message.setUndecorated(true);
		// frame.addMouseListener(this);
		// frame.addMouseMotionListener(this);
		message.setBackground(new Color(0, 0, 0, 0));

		panel.setOpaque(false);

		message.setVisible(true);

		// Thread.sleep(3000);

	}

	@Deprecated
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Deprecated
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Deprecated
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousedowncoor = e.getPoint();

	}

	@Deprecated
	@Override
	public void mouseReleased(MouseEvent e) {
		// mousedowncoor = new Point(0, 0);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		frame.setLocation(e.getXOnScreen() - mousedowncoor.x, e.getYOnScreen()
				- mousedowncoor.y);

	}

	@Deprecated
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
		repaint();
	}

}
