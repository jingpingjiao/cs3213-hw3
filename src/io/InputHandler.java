package io;

import java.io.InputStreamReader;
import java.util.Scanner;

import message.EofMessage;
import msg.Message;

import pipe.Pipe;

public class InputHandler implements Runnable {
	private InputStreamReader inStream;
	private Pipe outPipe;

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
}
