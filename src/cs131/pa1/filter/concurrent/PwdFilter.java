package cs131.pa1.filter.concurrent;

import cs131.pa1.filter.Message;

public class PwdFilter extends ConcurrentFilter {
	
	public PwdFilter() {
		super();
	}
	
	public void process() {
		output.add(processLine(""));
		output.add(Message.POISON_PILL.toString());
		finish = true;
	}
	
	public String processLine(String line) {
		return ConcurrentREPL.currentWorkingDirectory;
	}
}