package filter;

import java.util.Comparator;
import java.util.PriorityQueue;

import msg.Message;
import pipe.Pipe;
import util.StringComparator;

<<<<<<< HEAD
public class Alphabetizer extends InputFilter {
	Comparator<String> comparator = new StringComparator();

	PriorityQueue<String> sortedTitles = new PriorityQueue<String>(0,
			comparator);

=======
	PriorityQueue<String > sortedTitles = new PriorityQueue<String>(1,comparator);
	
>>>>>>> 5f98fc8759467a80d31429a92b635a349b5d6ee7
	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	public void run() {
		while (true) {
			assert (sortedTitles != null);
			assert (sortedTitles.isEmpty());

			while (true) {
				Message val = inPipe.read();
				// TODO fix this!

				if (val.getClass() != msg.EofMessage.class) {
					sortedTitles.add(val.getContent());
				} else {
					break;
				}
			}
			while (!sortedTitles.isEmpty()) {
				outPipe.write(new Message(sortedTitles.poll()));
			}
		}
	}
}
