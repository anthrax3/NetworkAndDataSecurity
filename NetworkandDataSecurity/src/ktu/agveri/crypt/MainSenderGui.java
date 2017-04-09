/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktu.agveri.crypt;

import ktu.agveri.crypt.Encrypt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JFileChooser;
import ktu.agveri.crypt.Md5Encryption;
import ktu.agveri.socket.Client;
import ktu.agveri.socket.Server;

/**
 *
 * @author mcanv
 */
public class MainSenderGui extends javax.swing.JFrame {

        private String Inputfile;
        private String hashText;
        
    
    /**
     * Creates new form MainGui
     */
    public MainSenderGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        DosyalariGonder = new javax.swing.JButton();
        Hashing = new javax.swing.JButton();
        Encrypt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hashVar = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        encryptedLabel = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        openPath = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DosyalariGonder.setText("Dosyayi Gönder");
        DosyalariGonder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DosyalariGonderActionPerformed(evt);
            }
        });

        Hashing.setText("Hashle");
        Hashing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HashingMouseClicked(evt);
            }
        });
        Hashing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HashingActionPerformed(evt);
            }
        });

        Encrypt.setText("Sifrele");
        Encrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncryptActionPerformed(evt);
            }
        });

        hashVar.setColumns(20);
        hashVar.setRows(5);
        jScrollPane1.setViewportView(hashVar);

        encryptedLabel.setColumns(20);
        encryptedLabel.setRows(5);
        jScrollPane2.setViewportView(encryptedLabel);

        jLabel2.setText("Gonderilecek Dosya");

        openPath.setColumns(20);
        openPath.setRows(5);
        jScrollPane4.setViewportView(openPath);

        jMenu1.setText("File");

        Open.setText("Open File");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Encrypt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hashing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(DosyalariGonder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DosyalariGonder, Encrypt, Hashing});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hashing))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Encrypt)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(DosyalariGonder, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DosyalariGonderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DosyalariGonderActionPerformed
        // TODO add your handling code here:
                Client client = new Client();
		client.connect();
		client.sendFile();
    }//GEN-LAST:event_DosyalariGonderActionPerformed

    private void HashingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HashingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HashingActionPerformed

    private void EncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptActionPerformed
        // TODO add your handling code here:
        Encrypt en = new Encrypt();
        
                String inFile = "Encrypted.txt";
		String line = ""; 
		String text = "";
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
                                encryptedLabel.setText(text);
				//System.out.println("doya okundu!");
				bReader.close();
			} catch (IOException e) {
				//System.out.println("Dosya okunamadı!");
			}

		} catch (FileNotFoundException e) {
			//System.out.println("Dosya bulunamadı!");
		}

    }//GEN-LAST:event_EncryptActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    
    
    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
                openPath.read(new FileReader(file.getAbsoluteFile() ), null);
                openPath.setText(file.getAbsolutePath());
                Inputfile = file.getAbsolutePath();
            }catch(IOException ex){
               // System.out.println("problem accessing file"+file.getAbsolutePath());
            }
        }else {
          //  System.out.println("File access cancelled by user");
        }
    }//GEN-LAST:event_OpenActionPerformed

    private void HashingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HashingMouseClicked
        // TODO add your handling code here:
        Md5Encryption md5 = new Md5Encryption();
       // System.out.println(Inputfile);
                String inFile = Inputfile;
		String line = ""; 
		String text = "";
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
				//System.out.println("doya okundu!");
				bReader.close();
			} catch (IOException e) {
				//System.out.println("Dosya okunamadı!");
			}

		} catch (FileNotFoundException e) {
			//System.out.println("Dosya bulunamadı!");
		}
             
                hashText = md5.hash(text);
                hashVar.setText(md5.hash(text));
    }//GEN-LAST:event_HashingMouseClicked

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainSenderGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainSenderGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainSenderGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainSenderGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
            
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainSenderGui().setVisible(true);
        });
        
        Thread thread = new Thread(() -> {
            Server server  = new Server();
            server.doConnect();
            server.downloadFile();
        });
        
        thread.start();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DosyalariGonder;
    private javax.swing.JButton Encrypt;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JButton Hashing;
    private javax.swing.JMenuItem Open;
    private javax.swing.JTextArea encryptedLabel;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextArea hashVar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea openPath;
    // End of variables declaration//GEN-END:variables

}
