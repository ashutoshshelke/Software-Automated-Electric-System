
package seconddemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SecondDemo extends JFrame{
    public static void main(String[] args) {
        SecondDemo c = new SecondDemo();
    }
    
    JButton login =  new JButton("Login");
    JLabel username = new JLabel("Username");
    JLabel password =  new JLabel("Password");
    JTextField uname = new JTextField(15); 
    JPasswordField pass =  new JPasswordField(15);
    JPanel panel = new JPanel();	
    Font font = new Font("Times New Roman", Font.BOLD,13);
    JLabel pls = new JLabel("Please Login to use the System");
    
    SecondDemo(){
        super("Login");
        setSize(300,200);
        setLocation(500,280);
        setResizable(false);
        panel.setLayout(null);
                
        pls.setBounds(60,10,180,20);
        pls.setFont(font);
        username.setBounds(35,43,75,10);
        username.setFont(font);
        uname.setBounds(120,40,150,20);
        password.setBounds(35,70,75,10);
        password.setFont(font);
        pass.setBounds(120,65,150,20);
        login.setBounds(110,100,80,20);
        

        panel.add(pls);
        panel.add(username);
        panel.add(uname);
        panel.add(password);
        panel.add(pass);
        panel.add(login);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionLogin();
        
    }
        
    public void actionLogin(){
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               
                try{
                    String u = uname.getText();
                    String p = new String(pass.getPassword());
                    
                    if(u.equals("ashutosh")&& p.equals("12345")){
                        JOptionPane.showMessageDialog(null, "Logged In");
                        uname.setText("");
                        pass.setText("");
                        uname.requestFocus();
                        SerialTest.gui gui;
                        gui = new SerialTest.gui();
                        SerialTest main;
                        try {
                            main = new SerialTest();
                            main.initialize();
                        } catch (Exception ex) {
                        }
                        //main.initialize();
                        Thread t=new Thread();
                        t.start();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid Username/Password");
                        uname.setText("");
                        pass.setText("");
                }
                }   
                catch(HeadlessException e){
                }
                
            }
        });
    }    
}
