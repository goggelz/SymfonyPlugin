package de.fhflensburg.pd.group002.input;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import de.fhflensburg.pd.group002.helper.*;
public class Modal extends AbstractHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    // Create a label to display what the user typed in
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), 
				"app/Console", 
				"your command", 
				"", 
				new Validator());
		if(dlg.open() == Window.OK){
			AppConsole cnl = new AppConsole(dlg.getValue());
			AppConsoleKiller kill = new AppConsoleKiller(cnl);
			Thread t1 = new Thread(cnl);
			Thread t2 = new Thread(kill);
			
			Display.getCurrent().asyncExec(new Runnable(){
				public void run(){
					t1.start();
				//	t2.start();
				}
			});

		}
		return null;
	}

}
