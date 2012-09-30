package filter;

import msg.Message;
import pipe.Pipe;

public abstract class Filter implements Runnable {
	Pipe inPipe;
	Pipe outPipe;

	public Filter(Pipe inPipe, Pipe outPipe) {
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}

	public void run() {
		while (true) {
			Message val = inPipe.read();
			try {
				val = transform(val);
			} catch (Exception e) {
				e.printStackTrace();
			}
			outPipe.write(val);
		}
	}

	private Message transform(Message val) throws Exception {
		// TODO Auto-generated method stub
		throw (new Exception("transform has to be overwritten"));
	}

}
