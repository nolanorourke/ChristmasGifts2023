import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class ChristmasPresent
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Merry Christmas :)");
        ChristmasPresentJPanel present = new ChristmasPresentJPanel("./favmem.txt");
        present.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 750));
        frame.setSize(650, 950);
        frame.setBackground(Color.WHITE);
        frame.add(present);
        frame.setVisible(true);
    }
    
}

