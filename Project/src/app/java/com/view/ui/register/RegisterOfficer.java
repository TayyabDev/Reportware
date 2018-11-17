package app.java.com.view.ui.register;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;

import app.java.com.model.utilities.Account.Account;
import app.java.com.model.utilities.Account.AgencyAccount;
import app.java.com.presenter.interfaces.RegisterNewUserResultInterface;
import app.java.com.view.interfaces.RegisterNewUserView;
import app.java.com.view.ui.UIHelpers;

public class RegisterOfficer extends RegisterNewUser implements RegisterNewUserView {

	private JPanel panel;
	private JButton register;
	private JButton cancel;
	private static JFrame frame;
	private RegisterNewUserResultInterface resultInterface;
	private Account account;
	
	
	public RegisterOfficer(JFrame frame, AgencyAccount account) {
		this.frame = frame;
		this.account = account;
		
		
		panel = super.initPanel();
        
        JLabel lblEnterInfo = new JLabel("Please enter the following information");
        lblEnterInfo.setBounds(300,50, 400, 20);
        
        JLabel lblAgencyName = new JLabel("Agency Name");
        lblAgencyName.setBounds(300,100, 400, 20);
        JTextField agencyNameTxt = new JTextField();
        agencyNameTxt.setBounds(300,150, 400, 20);
        
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(300,200, 400, 20);
        JTextField firstNameTxt = new JTextField();
        firstNameTxt.setBounds(300,250, 400, 20);
        
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(300,300, 400, 20);
        JTextField lastNameTxt = new JTextField();
        lastNameTxt.setBounds(300,350, 400, 20);
        
        JLabel lblDOB = new JLabel("Date of Birth");
        lblDOB.setBounds(300,400, 400, 20);
        JDateChooser chooser = new JDateChooser();
        chooser.setBounds(300,450, 400, 20);
        
        register = UIHelpers.buttonGenerator("register");
        register.setBounds( 325, 500, 150,50);
		
        cancel = UIHelpers.buttonGenerator("Cancel");
		cancel.setBounds(500, 500, 150, 50);
        
		panel.add(lblEnterInfo);
		panel.add(lblAgencyName);
		panel.add(agencyNameTxt);
		panel.add(lblFirstName);
		panel.add(firstNameTxt);
		panel.add(lblLastName);
		panel.add(lastNameTxt);
		panel.add(lblDOB);
		panel.add(chooser);
		panel.add(register);
		panel.add(cancel);
		
		frame.add(panel);
        super.setFrame(frame);
        System.out.println("in view");
		
		register.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// get the info client input to presenter
        		String agencyName = agencyNameTxt.getText();
        		String firstName = firstNameTxt.getText();
        		String lastName = lastNameTxt.getText();
        		Date dob = chooser.getDate();
        		if (firstName == "" || firstName == null) {
        			showPopUpWithMessage("First Name can not be empty", "Error");
                    return;
        		} else if (lastName == "" || lastName == null) {
        			showPopUpWithMessage("Last Name can not be empty", "Error");
                    return;
        		} else if(dob == null) {
                    showPopUpWithMessage("No Date Selected", "Error");
                    return;
                } else if (agencyName == "" || agencyName == null) {
                	showPopUpWithMessage("agency Name can not be empty", "Error");
                	return;
                } else {
                	// construct user
                	// send information client input to presenter
                }
        		
        	}
        });
	}

	@Override
	public void onSuccessRegisterNewUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onErrorRegisterNewUser() {
		// TODO Auto-generated method stub
		
	}

}
