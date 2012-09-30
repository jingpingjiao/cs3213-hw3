package pipe;

import msg.Message;

public interface Pipe {

	public Message read();

	public void write(Message val);

}
