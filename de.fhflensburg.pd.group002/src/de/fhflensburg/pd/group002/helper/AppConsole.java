package de.fhflensburg.pd.group002.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

public class AppConsole implements Runnable{
	private String cmd;
	private Process p;
	private Boolean processKilled = false;
	public AppConsole(String cmd){
		this.cmd = cmd;
	}
	
		@Override
		public void run() {
			Display.getDefault().asyncExec(new Runnable(){

				@Override
				public void run() {
	    			try {
	    		        ProcessBuilder builder = new ProcessBuilder(
	    		                "cmd.exe", "/c", "cd C:\\xampp\\htdocs\\Symfony && php app/Console "+cmd);
	    		        builder.redirectErrorStream(true);
	    	            p = builder.start();
	    	            
	    	            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	            String line = null;
	    	            while (true && !processKilled) {
	    	                line = r.readLine();
	    	                if (cmd.contains("server:run")) {
	    	                	
	    	                	System.out.println(line);
	    	                	if(line != null)
	    	                		break; 
	    	                }
	    	                if (line == null) { break; }
	    	                System.out.println(line);
	    	           
	    	            }
	    			} catch (Exception e) {
	    				System.err.println(e.getMessage());
	    			}
				}
							
			});			
			
			Display.getDefault().asyncExec(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						killProcess();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
							
			});


		}
		
		public void killProcess(){
			processKilled = true;
			p.destroy();
		}
		



}