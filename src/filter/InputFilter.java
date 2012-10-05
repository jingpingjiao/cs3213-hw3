package filter;

import msg.Message;
import pipe.MyPipe;
import pipe.Pipe;

public class InputFilter extends Filter{

	public InputFilter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!((MyPipe)inPipe).empty()){
			Message msg = inPipe.read();
			Message outMsg = process(msg);
			sendMessage(outMsg);
		}
	}

	private void sendMessage(Message outMsg) {
		// TODO Auto-generated method stub
		outPipe.write(outMsg);
	}

	private Message process(Message msg) {
		// TODO Auto-generated method stub
		String str = msg.getContent().trim();
		str = str.replace("\n", "").replace("\r", "");
		return new Message(str);
	}

}
