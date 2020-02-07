package view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileChooser extends JFileChooser {
	private static final long serialVersionUID = 1L;
	private String path;
    

    public FileChooser() {

		FileNameExtensionFilter extensionXML = new FileNameExtensionFilter("*.xml", "xml");
		setFileFilter(extensionXML);
        int returnVal = showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().getAbsolutePath();
        }
    }

    public String getPath() {
        return path;
    }
}