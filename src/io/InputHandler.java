package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import msg.EofMessage;
import msg.Message;

import pipe.Pipe;

public class InputHandler implements Runnable {
	private String filePath;
	private Pipe outPipe;
	private Scanner sc;
	private BufferedReader br;

	public InputHandler(Pipe pipe) {
		// TODO Auto-generated constructor stub
		// this constructor is to return an InputHanler which takes input from
		// terminal
		assert (pipe != null);
		this.outPipe = pipe;
		this.filePath = null;
		this.sc = new Scanner(System.in);
		this.br=null;
	}

	public InputHandler(Pipe pipe, InputStreamReader inStream) {
		// This constructs an InputHandler read a file specified
		assert (pipe != null);
		assert (inStream != null);
		this.outPipe = pipe;
		this.br = new BufferedReader(inStream);
		this.sc=null;
	}

	@Deprecated
	public InputHandler(Pipe pipe, String filePath) {

		assert (pipe != null);
		assert (filePath != null);
		this.outPipe = pipe;
		this.filePath = filePath;
		this.sc = null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.sc == null) {
			readFile();
		} else {
			readCommandLine();
		}
	}

	private void readFile() {
		String currentLine;
		try {
			while ((currentLine = br.readLine()) != null) {
				outPipe.write(new Message(currentLine));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Processing....\n");
		outPipe.write(new EofMessage());
	}

	private void readCommandLine() {
		String line;
		while (!(line = sc.nextLine()).isEmpty()) {
			outPipe.write(new Message(line));
		}
		System.out.println("Processing....\n");
		outPipe.write(new EofMessage());
	}

	@SuppressWarnings("unused")
	private void readingFile() {
		FileReader fr;
		try {
			fr = new FileReader(this.filePath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				outPipe.write(new Message(currentLine));
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pipe getOutPipe() {
		return this.outPipe;
	}
}
