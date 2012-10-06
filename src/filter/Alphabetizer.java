package filter;

import java.util.PriorityQueue;

import msg.Message;
import pipe.Pipe;

public class Alphabetizer extends InputFilter{
	PriorityQueue<String > sortedTitles = new PriorityQueue<String>();
	
	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);		
	}
	public void run() {
		while (true) {
			assert (sortedTitles != null);
			assert (sortedTitles.isEmpty());
			
			while(true){
				Message val = inPipe.read();
				//TODO fix this!
				boolean isStreamEnded = true;
				if (!isStreamEnded){
					sortedTitles.add(val.getContent());				
				}else{
					break;
				}				
			}			
			while(!sortedTitles.isEmpty()){
				outPipe.write(new Message(sortedTitles.poll()));
			}			
		}
	}
}
