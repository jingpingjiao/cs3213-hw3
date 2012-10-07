package controller;

import io.InputHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	private BufferedReader br;

	public Controller() {
		this.setBr(new BufferedReader(new InputStreamReader(System.in)));
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
		InputStreamReader in;
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

		// prepare inputstream
		if (inputFileName == null) {
			in = new InputStreamReader(System.in);
		} else {
			try {
				in = new InputStreamReader(new FileInputStream(inputFileName));
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				return;
			}
		}
		
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
}
