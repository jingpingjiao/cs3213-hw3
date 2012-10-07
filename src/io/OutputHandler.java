package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import msg.EofMessage;
import msg.Message;
import pipe.MyPipe;
import pipe.Pipe;

public class OutputHandler implements Runnable {
	FileWriter fstream;
	private Pipe inPipe;

	public OutputHandler(Pipe pipe, FileWriter fstream) {
		this.inPipe = pipe;
		this.fstream = fstream;
	}

	public OutputHandler(Pipe pipe) {
		this.inPipe = pipe;
		this.fstream = null;
	}

	public Pipe getInPipe() {
		return this.inPipe;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!((MyPipe) inPipe).empty()) {
			Message msg = inPipe.read();
			if (msg instanceof EofMessage) {
				break;
			} else {
				if (fstream != null) {
					BufferedWriter out = new BufferedWriter(fstream);
					try {
						out.write(msg.getContent());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(msg.getContent());
				}
			}
		}

	}

}
