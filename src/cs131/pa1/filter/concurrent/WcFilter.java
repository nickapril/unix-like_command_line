package cs131.pa1.filter.concurrent;

import cs131.pa1.filter.Message;

public class WcFilter extends ConcurrentFilter {
	private int linecount;
	private int wordcount;
	private int charcount;
	
	public WcFilter() {
		super();
	}
	
	public void process() {		
		String temp;
		while(!isDone()) {
			try {
				temp = input.take();
				if(temp.equals(Message.POISON_PILL.toString())) {
					output.add(processLine(null));
					output.add(Message.POISON_PILL.toString());
					finish = true;
				}else {
					String processedLine = processLine(temp);
					if (processedLine != null){
						output.add(processedLine);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public String processLine(String line) {
		//prints current result if ever passed a null
		if(line == null) {
			return linecount + " " + wordcount + " " + charcount;
		}
		
		if(isDone()) {
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return ++linecount + " " + wordcount + " " + charcount;
		} else {
			linecount++;
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return null;
		}
	}
}
