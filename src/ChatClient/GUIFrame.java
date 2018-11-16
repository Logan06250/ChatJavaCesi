/*
 * Created by JFormDesigner on Thu Nov 15 11:22:43 CET 2018
 */

package ChatClient;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Lamouar
 */
public class GUIFrame extends JFrame {

    public ClientSocket client;

    public GUIFrame(ClientSocket client){
        this.client = client;
        initComponents();
        textArea1.setText("Bienvenue sur le chat !");
    }

    public void setTextArea(String text){
        textArea1.setText(textArea1.getText() + "\n" + text);
    }

    public void setTextArea2(String text){
        textArea2.setText(text);
    }

    private void button1MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        client.sendMessage(textField1.getText());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lamouar
        button1 = new JButton();
        textField1 = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("Envoyer");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    button1MouseClicked(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //---- textField1 ----
        textField1.setText("Entrez votre message");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textArea2);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(scrollPane1)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button1)))
                    .addGap(34, 34, 34))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(41, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lamouar
    private JButton button1;
    private JTextField textField1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
