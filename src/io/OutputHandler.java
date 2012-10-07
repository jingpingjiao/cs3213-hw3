package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!((MyPipe) inPipe).empty()) {
			Message msg = inPipe.read();
			if (fstream != null) {
				BufferedWriter out = new BufferedWriter(fstream);
				try {
					out.write(msg.getContent());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println(msg.getContent());
			}
		}

	}

}
