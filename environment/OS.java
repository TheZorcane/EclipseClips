package environment;

public class OS {

	private static String name = System.getProperty("os.name").toLowerCase();

	public static final OSName NAME = name.contains("win") ? OSName.Windows
			: name.contains("linux") ? OSName.Linux
					: name.contains("unix") ? OSName.Unix : name
							.contains("mac") ? OSName.Mac : OSName.Solaris;

	public static void main(String[] args) {
		System.out.println(name);
	}

}