package message;

import msg.Message;

public class EofMessage extends Message {
	public static final String EOF = "-1";
	public EofMessage() {
		super(EOF);
	}

}
