/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Will
 */
public class Time extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    private JButton button = new JButton("Get Time");
    private JLabel label = new JLabel("Button last pressed at ..:..:..");

    public void main(String[] args) {
        JFrame frame = new JFrame("Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(button);
        contentPane.add(label);
        
        pack();
        setVisible(true);
    }
    
}
