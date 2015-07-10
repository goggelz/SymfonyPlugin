package de.fhflensburg.pd.group002.helper;

import org.eclipse.swt.widgets.Display;

public class AppConsoleKiller implements Runnable {

	AppConsole cnl;
	public AppConsoleKiller(AppConsole cnl) {
		this.cnl = cnl;
	}

	@Override
	public void run() {
		Display.getDefault().asyncExec(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					cnl.killProcess();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
						
		});

	}

}
