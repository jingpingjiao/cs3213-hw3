package controller;

import java.io.BufferedReader;
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
		
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
}
