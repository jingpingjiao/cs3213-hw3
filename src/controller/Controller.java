package controller;

import filter.Alphabetizer;
import filter.CircularShifter;
import filter.InputFilter;
import filter.OutputFilter;
import io.InputHandler;
import io.OutputHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pipe.MyPipe;
import pipe.Pipe;

/**
 * Controller class acts as both UI and controller in this small scale program
 * 
 * @author JJP
 * 
 */
public class Controller {
	private BufferedReader br;
	private ArrayList<Object> runables = new ArrayList<Object>();
	private InputHandler inHandler;
	private OutputHandler outHandler;

	/**
	 * Constructor for Controller
	 */
	public Controller() {
		this.setBr(new BufferedReader(new InputStreamReader(System.in)));
		initialize();
	}

	/**
	 * Initialize pipes and filters
	 */
	private void initialize() {
		Pipe pipe1 = new MyPipe();

		InputHandler inHandler = new InputHandler(pipe1);

		Pipe pipe2 = new MyPipe();
		InputFilter infilter = new InputFilter(pipe1, pipe2);

		Pipe pipe3 = new MyPipe();
		CircularShifter circularShifter = new CircularShifter(pipe2, pipe3);

		Pipe pipe4 = new MyPipe();
		Alphabetizer alphabetizer = new Alphabetizer(pipe3, pipe4);

		Pipe pipe5 = new MyPipe();
		OutputFilter outfilter = new OutputFilter(pipe4, pipe5);

		OutputHandler outHandler = new OutputHandler(pipe5);

		this.setInHandler(inHandler);
		this.runables.add(infilter);
		this.runables.add(circularShifter);
		this.runables.add(alphabetizer);
		this.runables.add(outfilter);
		this.setOutHandler(outHandler);
	}

	/**
	 * Main function
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		Controller c = new Controller();
		c.run();
	}

	private void run() throws IOException {
		// default input
		String input = "exit";
		String[] args;

		// welcome routine
		welcome();
		help();

		// read input and process
		do {
			System.out.print(">");
			input = br.readLine().trim();
			args = input.split(" ");
			if (args.length < 1) {
				continue;
			}

			String command = args[0];
			if (command.equalsIgnoreCase("exit")) {
				this.exit();
			} else if (command.equalsIgnoreCase("help")) {
				this.help();
			} else if (command.equalsIgnoreCase("process")) {
				this.process(input);
			} else {
				this.help();
			}

		} while (true);

	}

	/**
	 * Welcome routine
	 */
	private static void welcome() {
		String s = "=======================================\n"
				+ "          WELCOME TO KWIC MINI\n"
				+ "=======================================\n";
		System.out.println(s);

	}

	/**
	 * Exit routine
	 */
	private void exit() {
		System.out.println("Exiting from KIWIC MINI...");
		System.exit(0);
	}

	/**
	 * Print help content
	 */
	private void help() {
		String helpMsg = "help : display help content";
		String exitMsg = "exit : exit from KIWIC MINI";
		String processMsg = "process : process input, default input from stdin,output to stdout  \n"
				+ "-i	[filename] specify input file, \n"
				+ "-o	[filename] specify output file.";
		String help = "Avaible commands are: \n" + helpMsg + "\n" + exitMsg
				+ "\n" + processMsg + "\n";
		System.out.println(help);
	}

	/**
	 * This method process userinput
	 * 
	 * @param input
	 */
	private void process(String input) {
		initialize();
		// validate user input against regex
		String regex1 = "process +-[io] +[a-z0-9.-_]* *";
		String regex2 = "process +-[io] +[a-z0-9.-_]* +-[io] +[a-z0-9.-_]* *";
		String[] args = input.split(" ");

		if (args.length == 1) {// if only command is passed
			startAllThread();
		} else {
			if (input.matches(regex1) || input.matches(regex2)) {
				String inputFileName = null;
				String outputFileName = null;

				// check for parameters
				for (int i = 1; i < args.length; i++) {
					if (args[i].trim().equalsIgnoreCase("-i"))
						inputFileName = args[i + 1].trim();
					if (args[i].trim().equalsIgnoreCase("-o"))
						outputFileName = args[i + 1].trim();
				}

				if (inputFileName != null) {
					try {
						// if user specify input filename, change the
						// InputHandler
						this.inHandler = new InputHandler(
								this.inHandler.getOutPipe(),
								new InputStreamReader(new FileInputStream(
										inputFileName)));
					} catch (FileNotFoundException e) {
						System.out.println("File '" + inputFileName
								+ "' not found");
						return;
					}
				}
				if (outputFileName != null) {
					try {
						// if user specify output filename, change the
						// outputHandler
						this.outHandler = new OutputHandler(
								this.outHandler.getInPipe(), new FileWriter(
										outputFileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				startAllThread();
			} else {
				System.out.println("Please check you input");
			}
		}

	}

	private void startAllThread() {
		// start running all thread
		this.inHandler.run();
		for (Object obj : runables) {
			((Runnable) obj).run();
		}
		this.outHandler.run();
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public InputHandler getInHandler() {
		return inHandler;
	}

	public void setInHandler(InputHandler inHandler) {
		this.inHandler = inHandler;
	}

	public OutputHandler getOutHandler() {
		return outHandler;
	}

	public void setOutHandler(OutputHandler outHandler) {
		this.outHandler = outHandler;
	}
}
