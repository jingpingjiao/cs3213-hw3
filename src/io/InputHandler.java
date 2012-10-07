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

public class InputHandler implements Runnable{
	private String filePath;
	private Pipe outPipe;
	private InputStreamReader inStream;

	public InputHandler(Pipe pipe) {
		// TODO Auto-generated constructor stub
//		this constructor is to return an InputHanler which takes input from terminal
		this.outPipe = pipe;
		this.filePath = null;
	}
	
	public InputHandler(Pipe pipe, String filePath) {
//		This constructs an InputHandler read a file specified by filePath as input
		this.outPipe = pipe;
		this.filePath = filePath;
	}

	public InputHandler(Pipe pipe, InputStreamReader inStream) {
		this.outPipe = pipe;
		this.inStream = inStream;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		read();
	}

	private void read() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(inStream);
		String line;
		while (!(line = sc.nextLine()).isEmpty()) {
			outPipe.write(new Message(line));
		}
		outPipe.write(new EofMessage());
		sc.close();
	}

	@SuppressWarnings("unused")
	private void readingCommandLine() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String line;
		while(!(line=sc.nextLine()).isEmpty()){
			outPipe.write(new Message(line));
		}
		sc.close();
	}

	@SuppressWarnings("unused")
	private void readingFile() {
		FileReader fr;
		try {
			fr = new FileReader(this.filePath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while ((currentLine = br.readLine()) != null){
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

}
