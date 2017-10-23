package cs131.pa1.filter.concurrent;

import cs131.pa1.filter.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class ConcurrentREPL {

	static String currentWorkingDirectory;
	static boolean hasError = false;
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		HashMap<String, Thread> processes = new HashMap<String, Thread>();

		while(true) {
			//obtain command from user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
						
			if(command.equals("exit")) {
				break;
			} else if(command.equals("repl_jobs")) {		//	Print all background processes currently running
				int counter = 1;
		        ArrayList<String> keys = new ArrayList<String>(processes.keySet());	// ArrayList is used to order the output correctly
		        
		        for(int i=keys.size()-1; i>=0;i--){
					if(processes.get(keys.get(i)) != null) {
						System.out.println("\t" + counter + ". " + keys.get(i));
						counter++;
						try {
							processes.get(keys.get(i)).join();
							processes.remove(keys.get(i));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						// continue?
					}
		        }

			} else if(!command.trim().equals("")) {
				
				String truncCommand = command;
				if(command.charAt(command.length()-1) == '&') {		// Check for a backgrond process
					truncCommand = command.substring(0, command.length()-2);		
				}
				ConcurrentFilter filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(truncCommand);
				
				if(hasError) {
					// skip processing 	
				}else {
					// Create a new thread for each filter
					Thread t = null;
					while(filterlist != null) {
						t = new Thread(filterlist);
						t.start();
						filterlist = (ConcurrentFilter) filterlist.getNext();
					}
					
					if(command.charAt(command.length()-1) == '&') {
						processes.put(command, t);
						// Command is a background process, skip join for now
					}else {
						try {
							t.join();
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			hasError = false;
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}
}
