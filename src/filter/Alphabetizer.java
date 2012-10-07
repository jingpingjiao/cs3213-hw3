package filter;

import java.util.Comparator;
import java.util.PriorityQueue;

import msg.EofMessage;
import msg.Message;
import pipe.Pipe;
import util.StringComparator;

public class Alphabetizer extends Filter {
	Comparator<String> comparator = new StringComparator();

	PriorityQueue<String> sortedTitles = new PriorityQueue<String>(1,
			comparator);

	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	public void run() {
		assert (sortedTitles != null);
		assert (sortedTitles.isEmpty());
		while (true) {
			Message val = inPipe.read();
			// TODO fix this!
			if (!(val instanceof EofMessage)) {
				sortedTitles.add(val.getContent());
			} else {
				break;
			}
		}

		while (!sortedTitles.isEmpty()) {
			sendMessage(new Message(sortedTitles.poll()));
		}
		sendMessage(new msg.EofMessage());
	}

}
