package gui;
import Client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame{
    JPanel mainPanel ;
    Client sv;
    private JTextField textIP;
    private JButton ProcessButton;
    private JButton connectButton;
    private JButton listApplicationButton;
    private JLabel enterIP;
    private JButton ScreenShot;
    private JButton shutDownButton;
    private JButton keyLoggerButton;
    private JButton Exit;
    GUI _this = this ;
    public GUI (String title)
    {

        super(title);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(600,400);
//        ProcessButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFrame frame2 = new JFrame("frame2");
//                frame2.setSize(300,400);
//                frame2.setVisible(true);
//            }
//        });
       // ProcessButton.setPreferredSize(new Dimension(40, 40));
        ProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    System.out.println("Process");
                    sv.sendMg("Process");
                    String ann = sv.receiveMg();
                    String p =  sv.printAllProcess();
                    ProcessList processList = new ProcessList("Process List",p,sv);
                   // processList.setSize(500,400);
                    processList.setVisible(true);
//                    System.out.println("Open");
//                    String check = sv.receiveMg();
//                    System.out.println(check);
//                    if (check.equals("successfull") == true)
//                        JOptionPane.showMessageDialog(null, "KILL PROCESS SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
//                    else {
//                        JOptionPane.showMessageDialog(null, "KILL PROCESS FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
//                    }
                }

                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }
            }
        });
        listApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("App");
                    sv.sendMg("App");
                    String ann = sv.receiveMg();
                    String a = sv.printAllApp();


                    ApplicationList app = new ApplicationList("Application List",sv,a);
                    app.setSize(700,600);
                    app.setVisible(true);

                   // SwingUtilities.invokeLater(new ApplicationList("Application List",sv,a));

                    System.out.println("Open List Application");
//                    String checkKillA = sv.receiveMg();
//                    if (checkKillA.equals("successfull") == true) {
//                        JOptionPane.showMessageDialog(null, "KILL APPLICATION SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(null, "KILL APPLICATION OFF FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
//                    }
                }

                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }
            }
        });
        ScreenShot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sv.sendMg("ScreenShoot");
                    String check = sv.ScreenShoot();
                    System.out.println(check);

                    String checkScreenShoot = sv.receiveMg();
                    System.out.println(checkScreenShoot);
                    String currentDirectory = System.getProperty("user.dir");
                    String path = currentDirectory + "/" + sv.filepath;
                    File f = new File(path);
                    String cmd = "open " + path ;
                    System.out.println(cmd);
                    if(f.exists() && !f.isDirectory()) {
                        try {

                            Process open = Runtime.getRuntime().exec(cmd);
                        } catch (IOException r) {
                            System.out.println("Error " + r);
                        }
                    }
                    else {
                        System.out.println("File is not exist");
                    }


                }
                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }

            }
        });
        shutDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sv.sendMg("ShutDown");
                    String checkShutDown = sv.receiveMg();
                    if (checkShutDown.equals("successfull") == true) {
                        JOptionPane.showMessageDialog(null, "SHUT DOWN SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "SHUT DOWN FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }

            }
        });

        keyLoggerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sv.sendMg("KeyLogger");
                    System.out.println("keylogger");// mung ua ba con oi
                    //sv.ReceiveKeyLogger();
                    CatchKeyLogger logger = new CatchKeyLogger(sv);
                    //logger.setSize(700,600);
                    logger.setVisible(true);
                }
                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }
            }
        });
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = textIP.getText();
                JOptionPane.showMessageDialog(null, "Connect Successfully", "Annoucement", JOptionPane.PLAIN_MESSAGE);
                _this.sv = new Client(ip);
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sv.sendMg("null");
                    _this.setVisible(false);
                    System.exit(0);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
//    public static void main(String[] args)
//    {
//        GUI gui = new GUI("Remote Control");
//        gui.setSize(300,400);
//        gui.setVisible(true);
//     //   gui.text1.setText("Ban Tram cute hihi \n alo alo alo");
//    }
}
