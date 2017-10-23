package cs131.pa1.filter.concurrent;

import cs131.pa1.filter.Message;

public class PrintFilter extends ConcurrentFilter {

	public PrintFilter() {
		super();
	}
	
	public void process() {
		while(!isDone()) {
			String a;
			try {
				a = input.take();
				if (a.equals(Message.POISON_PILL.toString())) {
					break;
				} else {
					processLine(a);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String processLine(String line) {
		System.out.println(line);
		return null;
	}

	@Override
	public void run() {
		process();
	}
}
