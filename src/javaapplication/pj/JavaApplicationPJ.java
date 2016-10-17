package javaapplication.pj;

import java.awt.Frame;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

       

public class JavaApplicationPJ {



    public static void main(String[] args) {
        
      /*  JFrame frame = new JFrame("Samochody");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 320, 240);
        frame.setVisible(true);
        
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton Polisa = new JButton("Polisa");
        JButton Drukuj = new JButton("Drukuj");
        JButton Zapisz = new JButton("Zapisz");
        
        buttonPanel.add(Polisa, );
       */ 
        JFrame frame = new JFrame("Samochody");
        frame.setTitle("Samochody");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

                
        JMenuBar pasekMenu = new JMenuBar();
        JMenu menuPolisa = new JMenu("Polisa");
        
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Imię :");
        label.setDisplayedMnemonic(KeyEvent.VK_N);
        
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.NORTH_EAST);
        textField.addActionListener(new java.awt.event.ActionListener() {        
        public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Witam "+textField.getText() + "!");
         }});
        
        label.setLabelFor(textField);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        pasekMenu.add(textField);
        
        pasekMenu.add(menuPolisa);
        

         
        JMenuItem opcjaDrukuj = new JMenuItem("Drukuj");
        opcjaDrukuj.addActionListener(new ramka());
        menuPolisa.add(opcjaDrukuj);
        
        
        
        JMenuItem opcjaZapisz = new JMenuItem("Zapisz");
        opcjaZapisz.addActionListener(new ramka());
        menuPolisa.add(opcjaZapisz);
        
        
        JMenu menuWyjscie = new JMenu("Wyjście");
        JMenuItem opcjaZamknij = new JMenuItem("Zamknij Program");
        opcjaZamknij.addActionListener(new ramka());
        menuWyjscie.add(opcjaZamknij);
        pasekMenu.add(menuWyjscie);
        
        frame.add(pasekMenu, BorderLayout.NORTH);
        
        frame.setSize(300, 200);
        frame.setVisible(true);
        
        
        
    }
   
}
