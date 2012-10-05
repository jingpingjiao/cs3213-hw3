package pipe;

import java.util.ArrayList;

import msg.Message;

public class MyPipe implements Pipe{
	private ArrayList<Message> messages;
	
	public MyPipe(){
		this.messages = new ArrayList<Message>();
	}

	@Override
	public Message read(){
		// TODO Auto-generated method stub
		if(!messages.isEmpty()){
			return messages.remove(0);
		}
		return null;
	}

	@Override
	public void write(Message val) {
		// TODO Auto-generated method stub
		messages.add(val);
	}
	
	public boolean empty(){
		return messages.size() == 0;
	}

}
