package ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * This class storage user's personal data
 * 
 * @author ido
 * 
 */
public class PersonalDataFileHandler {
	public static final String fileName = "personalData.dat";
	public static final String charFormat = "UTF-8";
	private String name;
	private int newbie; // from 0-10, 0 = newbie, 10 experienced

	public PersonalDataFileHandler() {
		// defult settings
		name = "";
		newbie = 1;

		File f = new File(fileName);
		if (f.exists() && !f.isDirectory()) {
			getDataFromFile();
			System.out.println("a");
		} else {
			System.out.println("b");
			setNewData();
			getDataFromFile();
		}
	}

	private void getDataFromFile() {
		try {
			Scanner in = new Scanner(new File(fileName));
			if (in.hasNext())
				name = in.nextLine();
			else {
				System.out
						.println("bad file. creating new file. can't find name");
				setNewData();
			}
			if (in.hasNextInt())
				newbie = in.nextInt();
			else {
				System.out
						.println("bad file. creating new file.Can't find prog level");
				setNewData();
			}

		} catch (FileNotFoundException e) {
			System.out
					.println("error, file not found. trying to get new data from user");
			setNewData();
			getDataFromFile();
		}

	}

	public String getName() {
		return name;
	}

	public int isNewbie() {
		return newbie;
	}

	/**
	 * 
	 * @return true if new data has been inputed successfully
	 */
	public boolean setNewData() {

		try {
			PrintWriter writer;
			writer = new PrintWriter(fileName, charFormat);

			String name = null;
			while (name == null) {
				Scanner key = new Scanner(System.in);
				System.out.print("enter name:");
				name = key.next();
				// name =
				// JOptionPane.showInputDialog("Hey, What is your name?");

				if (name != null) {

					writer.println(name);

				}
			}
			System.out.println("AA");
			int Filenewbie = -1;
			while (Filenewbie < 0 || Filenewbie > 10) {
				Scanner key = new Scanner(System.in);
				System.out.print("enter level:");
				Filenewbie = key.nextInt();

				writer.println(Filenewbie);
			}

			writer.close();
			return true;
		} catch (FileNotFoundException e) {

			return false;
		} catch (UnsupportedEncodingException e) {

			return false;
		}

	}
}
