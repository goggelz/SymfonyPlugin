package de.fhflensburg.pd.group002.wizard;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.*;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
public class ProjectWizard extends Wizard implements INewWizard{

	private FirstPage first;
	
    public ProjectWizard(){
		super();
	    setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
    	first = new FirstPage();
        addPage(first);
    }

    @Override
    public boolean performFinish() {
    	try {
    		 String fileName = "SymfonyStartProject.zip"; //The file that will be saved on your computer
    		 URL link = new URL("http://symfony.com/download?v=Symfony_Standard_Vendors_2.3.10.zip"); //The file that you want to download
    		
    		 //Code to download
    		 InputStream in = new BufferedInputStream(link.openStream());
    		 ByteArrayOutputStream out = new ByteArrayOutputStream();
    		 byte[] buf = new byte[1024];
    		 int n = 0;
    		 while (-1!=(n=in.read(buf)))
    		 {
    		    out.write(buf, 0, n);
    		 }
    		 out.close();
    		 in.close();
    		 byte[] response = out.toByteArray();
    		 String dir = "C:\\xampp\\htdocs\\";
    		 
    		 File folder = new File(dir);
		  	 if(!folder.exists()){
		  		 folder.mkdir();
		  	 }
    		 FileOutputStream fos = new FileOutputStream(dir+fileName);
    		 fos.write(response);
    		 fos.close();
    		 
    		 unzip(dir+fileName, dir);;
    		 
    		 System.out.println("Finished");
    		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
    }

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}
	
    public void unzip(String zipFilePath, String destPath) throws IOException {
        try{
    	// Open the zip file
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<?> enu = zipFile.entries();
        while (enu.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) enu.nextElement();

            String name = zipEntry.getName();
            long size = zipEntry.getSize();
            long compressedSize = zipEntry.getCompressedSize();
            System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
                    name, size, compressedSize);

            // Do we need to create a directory ?
            File file = new File(destPath+name);
            if (name.endsWith("/")) {
                file.mkdirs();
                continue;
            }

            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            // Extract the file
            InputStream is = zipFile.getInputStream(zipEntry);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) >= 0) {
                fos.write(bytes, 0, length);
            }
            is.close();
            fos.close();

        }
        zipFile.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   
}

