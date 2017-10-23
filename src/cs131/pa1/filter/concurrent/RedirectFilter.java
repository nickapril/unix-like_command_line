package cs131.pa1.filter.concurrent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class RedirectFilter extends ConcurrentFilter {
	private FileWriter fw;
	
	public RedirectFilter(String line) throws Exception {
		super();
		String[] param = line.split(">");
		if(param.length > 1) {
			if(param[1].trim().equals("")) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
				ConcurrentREPL.hasError = true;
				throw new Exception();
			}
			try {
				fw = new FileWriter(new File(ConcurrentREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + param[1].trim()));
			} catch (IOException e) {
				System.out.printf(Message.FILE_NOT_FOUND.toString(), line);	//shouldn't really happen but just in case
				ConcurrentREPL.hasError = true;
				throw new Exception();
			}
		} else {
			System.out.printf(Message.REQUIRES_INPUT.toString(), line);
			ConcurrentREPL.hasError = true;
			throw new Exception();
		}
	}
	
	public void process() {
		while(!isDone()) {
			try {
				String temp = input.take();
				if(temp.equals(Message.POISON_PILL.toString())) {
					finish = true;
				}else {
					processLine(temp);
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String processLine(String line) {
		try {
			fw.append(line + "\n");
		} catch (IOException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
			ConcurrentREPL.hasError = true;
		}
		return null;
	}

	@Override
	public void run() {
		process();
		
	}
}
