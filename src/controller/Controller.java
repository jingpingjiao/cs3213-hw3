package controller;

import filter.Alphabetizer;
import filter.CircularShifter;
import filter.Filter;
import filter.InputFilter;
import filter.OutputFilter;
import io.InputHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pipe.MyPipe;
import pipe.Pipe;

public class Controller {
	private BufferedReader br;
	private ArrayList<Filter> filters;

	public Controller() {
		this.setBr(new BufferedReader(new InputStreamReader(System.in)));
		initialize();
	}

	private void initialize() {
		Pipe pipe1 = new MyPipe();
		InputHandler inHandler = new InputHandler(pipe1, new InputStreamReader(
				System.in));
		Pipe pipe2 = new MyPipe();
		InputFilter infilter = new InputFilter(pipe1, pipe2);

		Pipe pipe3 = new MyPipe();
		CircularShifter cs = new CircularShifter(pipe2, pipe3);

		Pipe pipe4 = new MyPipe();
		Alphabetizer alphabetizer = new Alphabetizer(pipe3, pipe4);

		Pipe pipe5 = new MyPipe();
		OutputFilter outfilter = new OutputFilter(pipe4, pipe5);
		
		OutputHandler outHandler =new OutPutHandler(pipe5);
	}

	public static void main(String args[]) {
		Controller c = new Controller();
		c.run();
	}

	private void run() {
		// default input
		String input = "exit";
		String[] args;

		// welcome routine
		welcome();
		help();

		// read input and process
		do {
			try {
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
					this.process(args);
				} else {
					this.help();
				}
			} catch (IOException e) {
				System.out.println("Error occurs while reading input");
			}

		} while (true);

	}

	private static void welcome() {
		String s = "=======================================\n"
				+ "          WELCOME TO KWIC MINI\n"
				+ "=======================================\n";
		System.out.println(s);

	}

	private void exit() {
		System.out.println("Exiting from KIWIC MINI...");
		System.exit(0);
	}

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

	private void process(String[] args) {
		String inputFileName = null;
		String outputFileName = null;

		// parse parameter
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-i")) {
				if (args.length < i + 2 || args[i + 1].trim().equals("")) {
					System.out.println("Invalid input");
					return;
				} else {
					inputFileName = args[i + 1];
				}
			} else if (args[i].equalsIgnoreCase("-o")) {
				if (args.length < i + 2 || args[i + 1].trim().equals("")) {
					System.out.println("Invalid input");
					return;
				} else {
					outputFileName = args[i + 1];
				}
			}
		}

		// prepare inputsHandler
		if (inputFileName == null) {
			InputStreamReader in = new InputStreamReader(System.in);
		} else {
			try {
				InputStreamReader in = new InputStreamReader(
						new FileInputStream(inputFileName));
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				return;
			}
		}

		// prepare outputHandler,

	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
}
