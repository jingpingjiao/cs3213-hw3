package filter;

import msg.EofMessage;
import msg.Message;
import pipe.Pipe;

public class CircularShifter extends Filter {

	private String DELIMS = "[ ]+";
	private String mesContent = null;
	private String[] tokens;
	private int nextBeginIndex = 0;

	// constructor
	public CircularShifter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	public void run() {
		while (true) {
			Message val = inPipe.read();
			if (val != null) {
				if (val instanceof EofMessage) {
					sendMessage(new msg.EofMessage());
					break;
				} else {
					mesContent = val.getContent();
					assert (mesContent != null);
					if (mesContent != null) {
						tokens = mesContent.split(DELIMS);
						Message processedMes = transform();
						while (processedMes != null) {
							sendMessage(processedMes);
							processedMes = transform();
						}
					} else
						reset();
				}
			}
		}
	}

	Message transform() {

		if ((mesContent == null) || (nextBeginIndex > tokens.length)
				|| (tokens == null)) {

			return null;
		}
		;

		String strResult = "";
		for (int j = nextBeginIndex; j < tokens.length + nextBeginIndex; j++) {
			strResult = strResult.concat(tokens[j % tokens.length]).concat(" ");
		}
		strResult = strResult.trim();
		nextBeginIndex++;
		if (nextBeginIndex >= tokens.length)
			reset();

		return new Message(strResult);
		// TODO remove this
		// throw (new Exception("transform has to be overwritten"));
	}

	private void reset() {
		mesContent = null;
		nextBeginIndex = 0;
		tokens = null;
	}

}
