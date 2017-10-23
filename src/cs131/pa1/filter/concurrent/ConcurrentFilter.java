package cs131.pa1.filter.concurrent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;


public abstract class ConcurrentFilter extends Filter implements Runnable{
	
	protected LinkedBlockingQueue<String> input;
	protected LinkedBlockingQueue<String> output;
	protected boolean finish = false;
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter sequentialNext = (ConcurrentFilter) nextFilter;
			this.next = sequentialNext;
			sequentialNext.prev = this;
			if (this.output == null){
				this.output = new LinkedBlockingQueue<String>();
			}
			sequentialNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	
	public Filter getNext() {
		return next;
	}
	
	public void process(){
		while (!isDone()){
			String line;
			try {
				line = input.take();
				if(line.equals(Message.POISON_PILL.toString())) {
					finish = true;
					break;
				}
				String processedLine = processLine(line);
				if (processedLine != null){
					output.add(processedLine);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//
		output.add(Message.POISON_PILL.toString());
		finish = true;
	}
	
	@Override
	public boolean isDone() {
		return finish;
	}
	public void run() {
		process();
	}
	
	protected abstract String processLine(String line);
	
}
