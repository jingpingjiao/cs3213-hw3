package filter;

import msg.EofMessage;
import msg.Message;
import pipe.MyPipe;
import pipe.Pipe;

public class InputFilter extends Filter {

	public InputFilter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!((MyPipe) inPipe).empty()) {
			Message msg = inPipe.read();
			if (msg instanceof EofMessage) {
				sendMessage(msg);
			} else {
				Message outMsg = transform(msg);
				sendMessage(outMsg);
			}
		}

	}

//	private void sendMessage(Message outMsg) {
//		// TODO Auto-generated method stub
//		outPipe.write(outMsg);
//	}

	private Message transform(Message msg) {
		// TODO Auto-generated method stub
		String str = msg.getContent().trim();
		str = str.replace("\n", "").replace("\r", "");
		return new Message(str);
	}

}
