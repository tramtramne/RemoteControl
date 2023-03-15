package gui;

import Client.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ApplicationList extends JFrame {
    private JPanel AppList;
    private JTextField FieldText;
    private JTextArea FrameList;
    private JScrollPane vbar;
    private JButton KillAppButton;
    private JTextField FieldOpen;
    private JButton openApplicaionButton;
    private JButton cancelButton;
    ApplicationList __this = this;
    public ApplicationList(String titleAppList, Client sv, String listApp) //, String list, Server sv
    {
        super(titleAppList);
        this.setContentPane(AppList);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
       // System.out.println("app nha\n" + listApp);
        FrameList.setText(listApp);



        KillAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textPIDTurnOff = FieldText.getText();
                try {
                    sv.sendMg(textPIDTurnOff +" kill");
                }
                catch (IOException er)
                {
                    System.out.println("Error "+er);
                }
                finally {
                    FieldText.setText(null);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                __this.setVisible(false);
            }
        });

        openApplicaionButton.addActionListener(new ActionListener() {
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
                        FieldText.setText("");
                    }

            }
        });
    }

}
