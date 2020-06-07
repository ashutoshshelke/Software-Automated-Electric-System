package seconddemo;

import java.io.*;
import gnu.io.*;
import java.util.Enumeration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;

public class SerialTest implements SerialPortEventListener {
	
SerialPort serialPort;
    /** The port we're normally going to use. */
private static final String PORT_NAMES[] = {"/dev/tty.usbserial-A9007UX1", // Mac OS X
        									"/dev/ttyUSB0", // Linux
        									"COM3", // Windows
        									};

private BufferedReader input;
private static OutputStream output;
private static final int TIME_OUT = 2000;
private static final int DATA_RATE = 9600;
public static JLabel l;
public static JFrame f;
public static JButton b1;
public static JButton b2;
public static TextArea ta;
public static JRadioButton option1;
public static JRadioButton option2;

public void initialize() {
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    //First, Find an instance of serial port as set in PORT_NAMES.
    while (portEnum.hasMoreElements()) {
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
    }
    if (portId == null) {
        System.out.println("Could not find COM port.");
        return;
    }

    try {
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
    } catch (PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException e) {
        System.err.println(e.toString());
    }
}


public synchronized void close() {
    if (serialPort != null) {
        serialPort.removeEventListener();
        serialPort.close();
    }
}

@Override
public synchronized void serialEvent(SerialPortEvent oEvent) {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();
            String str1="\nSwitch turned on at " +(df.format(dateobj));
            String str2="\nSwitch turned off at " +(df.format(dateobj));
            String inputLine;
            if (input.ready()) {
                inputLine = input.readLine();
                l.setFont(new Font("Serif", Font.PLAIN, 20));
		l.setOpaque(true);
		if("Led on".equals(inputLine)){
                    l.setText("SWITCH ON");
                    l.setBackground(Color.GREEN);
                    ta.append(str1);
                    
                }
                        		
		else if("Led off".equals(inputLine))
		{
			l.setText("SWITCH OFF");
			l.setBackground(Color.RED);
                        ta.append(str2);
			
		}
	}

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
   }


public static class gui
{
	public gui()
	{
		f=new JFrame();
		f.setLayout(null);
                f.setTitle("Software Automated Electric System");
                option1 = new JRadioButton("Automatic Mode");
                option2 = new JRadioButton("Manual Mode");
                ButtonGroup group= new ButtonGroup();
                group.add(option1);
                group.add(option2);
                option1.setBounds(650,50,150,150);
                option2.setBounds(950,50,150,150);
                f.add(option1);
                f.add(option2);
                option1.addActionListener(new RadioListener());
                option2.addActionListener(new RadioListener());
		l=new JLabel();
                //l.setText("SWITCH OFF");
		//l.setBackground(Color.RED);
                l.setBounds(800,200,150,100);
		f.add(l);                            
                b1=new JButton("Turn off");
                b2=new JButton("Turn on");
                ta=new TextArea();
                b1.addActionListener(new ButtonListener());
                b2.addActionListener(new ButtonListener());
                b1.setBounds(950,350,150,50);
                b2.setBounds(650,350,150,50);
                ta.setBounds(650,500,470,250);
                f.add(b1);
                f.add(b2);
                f.add(ta);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(5000,5000);
                //f.pack();
                f.setVisible(true);
	}
}

public static class ButtonListener implements ActionListener
{
    public ButtonListener(){
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(b1))
        {
            try {
                output.write('0');
            } 
            catch (IOException ex) {
                Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource().equals(b2))
        {
           
            try {
                output.write('1');
                
            } 
            catch (IOException ex) {
                Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    }

public static class RadioListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        JRadioButton jrb = (JRadioButton) ae.getSource();
        if(jrb == option1)
        {
            try
            {
                output.write('2');
                ta.append("\nAutomatic Mode Selected");
                b1.setVisible(false);
                b2.setVisible(false);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if(jrb == option2)
        {
            try
            {
                output.write('3');
                ta.append("\nManual Mode Selected");
                b1.setVisible(true);
                b2.setVisible(true);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}


public SerialTest() throws Exception  {
    Thread t=new Thread() {
        @Override
        public void run() {
            //the following line will keep this app alive for 1000 seconds,
            //waiting for events to occur and responding to them    (printing incoming messages to console).
            try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
        }
    };
    t.start();
    System.out.println("Started");
}
}

