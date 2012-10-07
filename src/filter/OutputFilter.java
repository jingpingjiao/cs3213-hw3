package filter;

import msg.EofMessage;
import msg.Message;
import pipe.MyPipe;
import pipe.Pipe;

public class OutputFilter extends Filter {

	public OutputFilter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while (!((MyPipe) inPipe).empty()) {
			Message msg = inPipe.read();
			if (msg instanceof EofMessage) {
				sendMessage(msg);
				break;
			}
			Message outMsg = transform(msg);
			sendMessage(outMsg);
		}
	}

//	private void sendMessage(Message outMsg) {
//		outPipe.write(outMsg);
//	}

	private Message transform(Message msg) {
		return msg;
	}
}
