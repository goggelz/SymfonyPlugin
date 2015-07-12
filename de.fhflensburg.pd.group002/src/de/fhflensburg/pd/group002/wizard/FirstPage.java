package de.fhflensburg.pd.group002.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FirstPage extends WizardPage {
  private Composite container;

  public FirstPage() {
    super("Symfony Project");
    setTitle("Symfony Project");
    setDescription("Fake Wizard: Download Symfony");
  }

  @Override
  public void createControl(Composite parent) {
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 1;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("This Wizard downloads and extracts the starter project for Symfony 2.3.10.\n"
    		+ "It's saved in the path: 'C:\\xampp\\htdocs\\' and will be there extracted aswell.");

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);

    setControl(container);
    setPageComplete(true);

  }

}
 