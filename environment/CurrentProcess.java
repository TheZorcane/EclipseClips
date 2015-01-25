package environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurrentProcess implements Runnable {

	private String currentProcesses;
	private boolean get;
	private Thread processStream;

	public CurrentProcess() {
		get = true;
		processStream = new Thread(this);
		processStream.start();
	}

	@Override
	public void run() {
		while (get) {
			try {
				currentProcesses = "";
				Process p = OS.NAME == OSName.Windows ? Runtime.getRuntime()
						.exec(System.getenv("windir") + "\\system32\\"
								+ "tasklist.exe") : Runtime.getRuntime().exec(
						"ps -fle");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = input.readLine()) != null) {
					if (line.contains(" R ") && !line.endsWith("]")
							&& !line.contains("ps -fle"))
						currentProcesses += line + "\n";
				}
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getCurrentProcesses() {
		return currentProcesses;
	}

	public void stopProcessStream() {
		get = false;
	}

	public void restartProcessStream() {
		get = true;
		processStream = new Thread(this);
		processStream.start();
	}

}
