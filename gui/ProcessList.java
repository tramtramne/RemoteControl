package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Client.Client;
public class ProcessList extends JFrame{
    private JPanel ProcessList;
    private JLabel List;
    private JTextArea framePrintList;
    private JLabel turnOffPID;
    private JTextField FieldTurnOff;
    private JButton ButtonTurnOff;
    private JTextField FieldOpen;
    private JButton openProcessButton;
    private JButton exitButton;
    ProcessList _this = this;
    public ProcessList(String titleProcessList, String list, Client sv)
    {
        super(titleProcessList);
        this.setContentPane(ProcessList);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 600);
        System.out.println("Process");
        List.setText("Process List");
        //System.out.println(list);
        framePrintList.setText(list);

        ButtonTurnOff.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e)  {
                String textPIDTurnOff = FieldTurnOff.getText();
                try {
                    sv.sendMg(textPIDTurnOff +" kill");
                    JOptionPane.showMessageDialog(null, "Open Successfully", "Annoucement", JOptionPane.PLAIN_MESSAGE);
                }
                catch (IOException er)
                {
                    JOptionPane.showMessageDialog(null, "Open failed", "Annoucement", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error "+er);
                }
                finally {

                    FieldTurnOff.setText(null);
                }
            }

        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
            }
        });
        openProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textPIDOpen = FieldOpen.getText();
                try {
                    if (textPIDOpen.equals("") != true) {
                        sv.sendMg(textPIDOpen + " open");
                        JOptionPane.showMessageDialog(null, "Open Successfully", "Annoucement", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Open failed", "Annoucement", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }
                finally {
                    FieldOpen.setText("");
                }
            }
        });
    }


}
