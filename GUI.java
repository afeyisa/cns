import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class GUI {
    GUI(){

        JFrame frame = new JFrame();
        JPanel panel =new JPanel();
        JPanel panel1 =new JPanel();
        JPanel panel2 =new JPanel();
        JPanel panel3=new JPanel(new BorderLayout());
        JPanel panel4 =new JPanel(new BorderLayout());
        JPanel panel5=new JPanel(new BorderLayout());
        JPanel panel6 =new JPanel(new BorderLayout());
        JPanel panel3_1 = new JPanel(new BorderLayout());
        JPanel panel5_1 = new JPanel(new BorderLayout());


        JTextField textField1 = new JTextField(2);
        JTextField textField2 = new JTextField(2);
        JTextField textField3 = new JTextField(2);
        JTextField textField4 = new JTextField(2);
        JTextField textField5 = new JTextField(2);
        JTextField textField6 = new JTextField(2);



        //Encryption part
        JButton Encypt = new JButton("Encrypt");
        Encypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setMessage(textField1.getText());
                String s= Main.getAlgorithm();
                if ( s== "3DES") {
                    textField5.setText(DES.desEncrypt());
                    textField3.setText(DES.getKey());

                }
                else if (s=="OTP"){
                    String message= textField1.getText();
                    byte[] enMessage = message.getBytes();
                    byte[] myaESkey = OTP.generateKey(enMessage.length);
                    textField5.setText(Base64.getEncoder().encodeToString(OTP.encrypt(enMessage,myaESkey)));
                    textField3.setText(OTP.getKey());


                }


                else if (s=="AES") {
                    textField5.setText(AES.aesEncrypt());
                    textField3.setText(AES.getKey());
                }




            }
        });


// Decryption part
        JButton Decrypt = new JButton("Decrypt");
        Decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s= Main.getAlgorithm();
                if ( s== "3DES") {
                    //new DES();
                    byte[] enmessage = Base64.getDecoder().decode(textField2.getText());
                    byte[] myDESkey= Base64.getDecoder().decode(textField4.getText());
                    SecretKey secretKey = new SecretKeySpec(myDESkey, 0, myDESkey.length, "DES");
                    textField6.setText(DES.desDycrypt(enmessage,secretKey));

                }
                else if (s=="OTP"){

                    byte[] decodedKey = Base64.getDecoder().decode(textField4.getText());
                    byte[] decodedMessage = OTP.decrypt(Base64.getDecoder().decode(textField2.getText()), decodedKey);
                    String decryptedMessage = new String(decodedMessage);
                    textField6.setText(decryptedMessage);


                }
                else if (s=="AES") {
                    byte[] enmessage = Base64.getDecoder().decode(textField2.getText());
                    byte[] myaESkey = Base64.getDecoder().decode(textField4.getText());
                    SecretKey secretKey = new SecretKeySpec(myaESkey, 0, myaESkey.length, "AES");
                    textField6.setText(AES.aesdecryption(enmessage,secretKey));

                }
            }
        });


        // algorithm selection part
        String[] options = {"3DES","AES","OTP"};
        JComboBox<String> AlgorithmComboBox = new JComboBox<>(options);
        AlgorithmComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.setA((String) AlgorithmComboBox.getSelectedItem());

            }
        });




        // label one which is on panel 3
        JLabel label1 = new JLabel("Message to Encrypt");
        Font boldFont = new Font(label1.getFont().getFontName(),Font.BOLD,label1.getFont().getSize());
        label1.setFont(boldFont);
        Font currentFont = label1.getFont();
        Font largerFont = currentFont.deriveFont(currentFont.getSize() + 10f);
        label1.setFont(largerFont);
        label1.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));

        // label two which is on panel 5
        JLabel label2 = new JLabel("Message to Decrypt");
        label2.setFont(boldFont);
        label2.setFont(largerFont);
        label2.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));

        // encryption label
        JLabel label3 = new JLabel("Encryption key");
        label3.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        JLabel label4 = new JLabel("Decryption key");
        label4.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        JLabel label5= new JLabel("Choose Algorithm");
        label5.setFont(boldFont);
        label5.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));



        // panel5_1
        panel5_1.setLayout(new GridLayout(1,2));
        panel5_1.add(label4);
        panel5_1.add(textField4);


        // panel inside panel3

        panel3_1.setLayout(new GridLayout(1,2));
        panel3_1.add(label3);
        panel3_1.add(textField3);



        // panel3 part
        panel3.add(label1);
        panel3.setLayout(new GridLayout(3,1));
        panel3.add(textField1);
        panel3.add(panel3_1);



        //panel4 part
        panel4.setBackground(Color.lightGray);
        panel4.setLayout(new GridLayout(3,1));
        panel4.add(Encypt);
        panel4.add(textField5);
        panel4.add(label5);



        // panel5 part

        panel5.setBorder(BorderFactory.createEmptyBorder());
        panel5.add(label2);
        panel5.setBackground(Color.red);
        panel5.setLayout(new GridLayout(3,1));
        panel5.add(textField2);
        panel5.add(panel5_1);




        //panel6 part
        panel6.setBackground(Color.yellow);
        panel6.setLayout(new GridLayout(3,1));
        panel6.add(Decrypt);
        panel6.add(textField6);
        panel6.add(AlgorithmComboBox);
        //panel1 part
        panel1.setLayout(new GridLayout(2,1));
        panel1.setBorder(BorderFactory.createEmptyBorder());
        panel1.add(panel3,BorderLayout.CENTER);
        panel1.add(panel4, BorderLayout.CENTER);

        // panel2 part
        panel2.setBorder(BorderFactory.createEmptyBorder());
        panel2.setLayout(new GridLayout(2,1));
        panel2.add(panel5,BorderLayout.CENTER);
        panel2.add(panel6,BorderLayout.CENTER);




        //container panel part
        panel.setLayout(new GridLayout(1,2));
        panel.add(panel1,BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.CENTER);

        // frame part
        frame.setTitle("Encryption and Decryption Algorithms");
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2,screenSize.height);

        frame.setVisible(true);

    }
}
