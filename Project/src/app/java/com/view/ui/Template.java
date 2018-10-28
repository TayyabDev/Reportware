package app.java.com.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


public class Template {
	 private JPanel panel;
	 private JButton create;
	 private JButton view;
	 private JButton select ;
	 
	
     public Template() {
    	 GridBagLayout gb = new GridBagLayout();
 		GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;



    	 create = UIHelpers.buttonGenerator("Create a new template");
		 create.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				 jfc.setDialogTitle("Select your file");
				 int returnValue = jfc.showSaveDialog(null);
				 if (returnValue == JFileChooser.APPROVE_OPTION) {
					 if (jfc.getSelectedFile().isDirectory()) {
						 System.out.println("You selected the directory: " + jfc.getSelectedFile());
					 }
				 }

			 }
		 });


    	 view = UIHelpers.buttonGenerator("View the existing templates");
    	 select = UIHelpers.buttonGenerator("Select a template");
    	
    	 gb.setConstraints(create, c);
    	 gb.setConstraints(view, c);
    	 gb.setConstraints(select, c);
    	 
    	 panel.add(create);
    	 panel.add(view);
    	 panel.add(select);
    	 
    	 
    	 
     }
	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Template Settings");
	        frame.add(new Template().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
	    }

}


