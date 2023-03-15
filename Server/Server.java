package Test;



//import Server.ReceiveKeyLogger;
import com.github.kwhat.jnativehook.GlobalScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import java.awt.Dimension;

public class Server {

    private static ObjectOutputStream objectOutputStream;
    public static Socket serverSocket;
    public static ServerSocket welcomeSocket;
    private BufferedReader inFromServer;
    public PrintWriter outToServer;
//    private JPanel cPanel = null;
//    String width = "", height = "";
//    double w;
//    double h;


    //
    public Server() {
//        try {
//
//            serverSocket = new Socket("127.0.0.1", 5050);
//            System.out.println("connected");
//
//            inFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
//            outToServer = new PrintWriter(serverSocket.getOutputStream());
//
//
//        } catch (IOException e) {
//            System.out.println("Error " + e);
//        }
        try {

            welcomeSocket = new ServerSocket(5050);
            System.out.println("Hello World...");
            serverSocket = welcomeSocket.accept();


            System.out.println("Connection successfully");


            inFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            outToServer = new PrintWriter(serverSocket.getOutputStream());
//                n = n-1;
//            }
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
    }

    private void sendMg(String MgToSend) {


        outToServer.write(MgToSend);
//        System.out.println("troi" + MgToSend);
        outToServer.write('\n');
        outToServer.write("done");
        outToServer.write('\n');
        outToServer.flush();

    }

    private String receiveMg() throws IOException {
        String mg = "";
        String l = inFromServer.readLine();
        mg += l;
        while ((l = inFromServer.readLine()) != null) {
            if (l.compareTo("done") == 0) {
                System.out.println(l + "hihi");
                break;
            }
            mg += '\n' + l;
        }
        // System.out.println("server received: "+ mg);
        return mg;
    }

    private void closeSendReceive() throws IOException {
        inFromServer.close();
        outToServer.close();
        serverSocket.close();
    }

    public String printAllProcesses() throws Exception {
        // -e - show all processes including processes of other users
        // -o command - restrict the output to just the process name
        //String cmd = "ps aux";
         String cmd = "powershell.exe gps | select ProcessName,Id,Description";
        Process process = Runtime.getRuntime().exec(cmd); //("ps -e -o command");

        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";

        while ((line = r.readLine()) != null) {
//            System.out.println(line);
            sendMg(line);
        }
        sendMg("Done");
        r.close();
        return "Done";
    }

    public String KillProcess(String PID) throws IOException {
////        cmd = "open powershell.exe "+ "/c" + cmd.toString();
        String cmd = "powershell.exe gps | taskkill /F /PID " + PID;
        //String cmd = "kill -9 " +PID;
        String check = "";
        try {
            System.out.println(PID);
            Process turnoff = Runtime.getRuntime().exec(cmd);
            check = "successfull";
            //JOptionPane.showMessageDialog(null, "TURN OFF SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error" + e);
            check = "failed";
            //JOptionPane.showMessageDialog(null, "TURN OFF FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }

    public String printAllApp() throws Exception {
        String cmd = "powershell.exe gps | where {$_.MainWindowTitle } | select ProcessName,Id,Description";
       // String cmd = "ps aux";
        Process app = Runtime.getRuntime().exec(cmd); //("ps -e -o command");
        BufferedReader r = new BufferedReader(new InputStreamReader(app.getInputStream()));
        String line = "";

        while ((line = r.readLine()) != null) {
//            System.out.println(line);
            sendMg(line);
        }
        sendMg("Done");
        r.close();
        return "Done";
    }

    public String KillApp(String PID) throws IOException {
        String cmd = "powershell.exe gps | taskkill /F /PID " + PID;
//        cmd = "open powershell.exe "+ "/c" + cmd.toString();
      //  String cmd = "Killall " +PID;
        String check = "";
        try {
            System.out.println(PID);
            Process turnoff = Runtime.getRuntime().exec(cmd);
            check = "successfull";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error" + e);
            check = "failed";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
    public String OpenApp(String name) throws IOException{
       // String cmd = "powershell.exe "+ "& '"+ name + "'";
        //String cmd = "open -a " + name ;
        String openPowerShell = "powershell.exe";
        String check;
        try {
            System.out.println(name);
            Process open = Runtime.getRuntime().exec(openPowerShell);
            String cmd =name;
            Process openA = Runtime.getRuntime().exec(cmd);
            check = "successfull";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error" + e);
            check = "failed";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
    public String OpenProcess(String name) throws IOException{
        String cmd ="powershell.exe Start-Process "+ name;
        //  String cmd = "open -a " + name ;
        String check;
        try {
            System.out.println(name);
            Process open = Runtime.getRuntime().exec(cmd);
            check = "successfull";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION SUCCESSFULLY", "Annoucement", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error" + e);
            check = "failed";
            //JOptionPane.showMessageDialog(null, "KILL APPLICATION FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
    public String ShutDown() throws IOException {
        String check = "";
        try {
            String command = "shutdown -s -t 15";
            Runtime.getRuntime().exec(command);
//            ProcessBuilder pb = new ProcessBuilder("cmd.exe", command);
//            pb.start();
//            pb.wait();
            check = "successfull";
        } catch (IOException e) {
            System.out.println("Error" + e);
            check = "failed";
        }
        //JOptionPane.showMessageDialog(null, "KILL APPLICATION FAILED", "Annoucement", JOptionPane.ERROR_MESSAGE);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return check;
    }

    public String ScreenShot() throws IOException, AWTException {
        String check = "";
        try {
//            System.out.println("chup di");
            Toolkit toolkit = Toolkit.getDefaultToolkit(); // lấy width và heigh của màn hình bằng toolkit class
            Dimension dimensions = toolkit.getScreenSize(); // chụp toàn màn hình
            Robot robot = new Robot();  // Robot class
//            System.out.println("chup di2");
            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(dimensions));
//            System.out.println("chup di3");
            ImageIO.write(screenshot, "png", serverSocket.getOutputStream());
//            System.out.println("chup thanh cong");
            check = "successfull";


//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Screenshoot");
            check = "failed";

        }
        return check;
    }


    public static void main(String[] args) throws Exception {
        Server cl = new Server();
        String menu = "";
        do {
            menu = cl.receiveMg();
            System.out.println(menu);
            if (menu == null){
                System.out.println("Exit");
                break;
                // System.exit(0);
            }
            if (menu.equals("null") == true){
                System.out.println("Exit");
                System.exit(0);
            }
            if (menu.isEmpty()){
                System.out.println("Exit");
                System.exit(0);
            }
            switch (menu) {
                case "Process":
                    System.out.println(menu);
                    String checkSendProcess = cl.printAllProcesses();
                    String nameProcess = cl.receiveMg();
                    if (nameProcess.equals(null)== true) continue;;
                    int posKillP = nameProcess.indexOf("kill");
                    int posOpenP = nameProcess.indexOf("open");
                    if (posKillP != -1){
                        nameProcess =nameProcess.substring(0,posKillP -1);
                        String checkKillA = cl.KillProcess(nameProcess);
                        //cl.sendMg(checkKillA);
                        System.out.println(checkKillA);
                    }
                    else if (posOpenP != -1)
                    {
                        nameProcess =nameProcess.substring(0,posOpenP -1);
                        String checkOpenA = cl.OpenProcess(nameProcess);
                        // cl.sendMg(checkKillA);
                        System.out.println(checkOpenA);
                    }

                    break;
                case "App":
                    System.out.println(menu);
                    String checkSendApp = cl.printAllApp();

                    String nameApp = cl.receiveMg();
                    if (nameApp== null) continue;;
                    int posKill = nameApp.indexOf("kill");
                    int posOpen = nameApp.indexOf("open");
                    if (posKill != -1){
                        nameApp =nameApp.substring(0,posKill -1);
                        String checkKillA = cl.KillApp(nameApp);
                        //cl.sendMg(checkKillA);
                        System.out.println(checkKillA);
                    }
                    else if (posOpen != -1)
                    {
                        nameApp =nameApp.substring(0,posOpen -1);
                        String checkOpenA = cl.OpenApp(nameApp);
                        // cl.sendMg(checkKillA);
                        System.out.println(checkOpenA);
                    }

                    break;
                case "ShutDown":
                    String checkShutDown = cl.ShutDown();
                    cl.sendMg(checkShutDown);
                    System.out.println(checkShutDown);
                    break;
                case "ScreenShoot":
                    String checkScreenShoot = cl.ScreenShot();
                    cl.sendMg(checkScreenShoot);
                    System.out.println(checkScreenShoot);
                    break;
                case "KeyLogger":
                    System.out.println("Stop1");
                    Keylogger Logger = new Keylogger(cl);
                    GlobalScreen.registerNativeHook();
                    GlobalScreen.addNativeKeyListener(Logger);
                    String  stopButton = cl.receiveMg();
                    if (stopButton.equals("Stop Send keylogger") == true) {
                        //System.out.println("Stop Send keylogger");
                        System.out.println("Hi");
                        GlobalScreen.unregisterNativeHook();

                        cl.sendMg(Logger.key);
                        System.out.println("He");
                        System.out.println(Logger.key);
                        String exitButton = cl.receiveMg();
                    }
                    else if (stopButton.equals("exit") == true)
                    {
                        System.out.println("Hi");
                        GlobalScreen.unregisterNativeHook();
                    }


                    System.out.println("duockhongvay 123");

                    //objectOutputStream = new ObjectOutputStream(cl.serverSocket.getOutputStream());
                    System.out.println("duockhongvay 456");


                    break;

            }
        }
        while (menu.equals("") == false);

        System.out.println("Stop2");
        cl.closeSendReceive();
    }

}


