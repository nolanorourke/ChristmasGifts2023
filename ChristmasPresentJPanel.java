import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ChristmasPresentJPanel extends JPanel
{
    private JLabel memories, pictures;
    private Icon memIconU, memIconC, picIconU, picIconC;
    private Color backgroundColor;
    private Entry[] entries = new Entry[50];
    private CenterPanel center;
    private BottomPanel bottom;
    private String name;
    private int height = getHeight(), width = getWidth();
    private JTextArea experience;
    ChristmasPresentJPanel()
    {
        setLayout(new BorderLayout());
        center = new CenterPanel();
        center.setBackground(Color.WHITE);
        bottom = new BottomPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setSize(new Dimension(getWidth(), getHeight()/5));

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
    ChristmasPresentJPanel(String filepath)
    {
        setLayout(new BorderLayout());
        center = new CenterPanel();
        center.setBackground(Color.WHITE);
        bottom = new BottomPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setSize(new Dimension(getWidth(), getHeight()/5));

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        try
        {
            readEntries(filepath);
        }
        catch(IOException e)
        {
            System.out.println("Wasnt able to get into the file...");
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }


    public void readEntries(String filepath) throws IOException
    {
        int i = 0;

        Scanner scanner = new Scanner(new File(filepath));
        name = scanner.nextLine();

        while(scanner.hasNextLine() )
        {
            String line = scanner.nextLine();

            Scanner lineScanner = new Scanner(line);

            entries[i] = new Entry(lineScanner.nextInt(), lineScanner.nextLine().trim());
        
            lineScanner.close();
            i++;
        }
        scanner.close();
    }

    public static class Entry
    {
        private int index;
        private String content;
        Entry()
        {
            index = -1;
            content = " ";
        }
        Entry(int i, String s)
        {
            index = i;
            content = s;
        }
        public int getIndex()
        {
            return index;
        }
        public String getContent()
        {
            return content;
        }
        public void setIndex(int i)
        {
            index = i;
        }
        public void setContent(String s)
        {
            content = s;
        }
    }

    public class CenterPanel extends JPanel
    {
        CenterPanel()
        {
            experience = new JTextArea(3,3);
            setLayout(new BorderLayout());
            Font openSans = new Font("Open Sans", Font.PLAIN, 22);
            experience.setFont(openSans);
            experience.setEditable(false);
            experience.setSize(new Dimension(getWidth()-10, getHeight()-10));
            experience.setLineWrap(true);
            experience.setWrapStyleWord(true);
            add(experience);
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }
        public int generateNumber()
        {
            Random random = new Random();
            return random.nextInt(50);
        }
        public void recall()
        {
            experience.setText(entries[generateNumber()].content);
        }
    }
    public class BottomPanel extends JPanel
    {
        BottomPanel() 
        {
            backgroundColor = Color.WHITE;
            setBackground(backgroundColor);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // picIconU = resizeImageIcon(new ImageIcon(getClass().getResource("photo-gallery.png")));
            // picIconC = resizeImageIcon(new ImageIcon(getClass().getResource("photo-gallery-2.gif")));
            // memIconU = resizeImageIcon(new ImageIcon(getClass().getResource("heart.png")));
            // memIconU = resizeImageIcon(new ImageIcon(getClass().getResource("heart-2.gif")));

            // picIconU = new ImageIcon(getClass().getResource("photo-gallery.png"));
            // picIconC = new ImageIcon(getClass().getResource("photo-gallery-2.gif"));
            memIconU = new ImageIcon(getClass().getResource("heart.png"));
            memIconC = new ImageIcon(getClass().getResource("heart-2.gif"));
            
            memories = new JLabel("My favorite memories of us", memIconU, SwingConstants.CENTER);
            memories.setMaximumSize(new Dimension(10,10));
            memories.setVerticalTextPosition(SwingConstants.BOTTOM);
            memories.setHorizontalTextPosition(SwingConstants.CENTER);
            memories.setToolTipText("Click to see some of my favorite memories of us");
            //memories.setPreferredSize(new Dimension(getWidth()/3, getHeight()/3));
            memories.setOpaque(true);
            memories.setVisible(true);
            
            // pictures = new JLabel("My Favorite pictures of us", picIconU, SwingConstants.RIGHT);
            // pictures.setVerticalTextPosition(SwingConstants.BOTTOM);
            // pictures.setHorizontalTextPosition(SwingConstants.CENTER);
            // pictures.setToolTipText("Click to see some of my favorite pictures of us");
            // // pictures.setPreferredSize(new Dimension(getWidth()/3, getHeight()/3));
            // pictures.setOpaque(true);

            MouseHandler mickey = new MouseHandler();
            memories.addMouseListener(mickey);
            // pictures.addMouseListener(mickey);

            add(memories);
            // add(pictures);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            //setSize(new Dimension(getWidth(), getHeight()/5));

        }
        private ImageIcon resizeImageIcon(ImageIcon icon)
        {
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(width/4, height/4, Image.SCALE_SMOOTH); // Adjust the size as needed
            return new ImageIcon(scaledImage);
        }
       
    }

    private class MouseHandler implements MouseListener
    {
        public void mouseEntered(MouseEvent e)
        {
            if(e.getSource() == memories)
            {
                memories.setIcon(memIconC);
                memories.setBackground(Color.LIGHT_GRAY);
            }
            else if(e.getSource() == pictures)
            {
                pictures.setIcon(picIconC);
                pictures.setBackground(Color.LIGHT_GRAY);
            }
        }
        public void mouseExited(MouseEvent e)
        {
            if(e.getSource() == memories)
            {
                memories.setIcon(memIconU);
                memories.setBackground(backgroundColor);
            }
            else if(e.getSource() == pictures)
            {
                pictures.setIcon(picIconU);
                pictures.setBackground(backgroundColor);
            }
        }
        public void mousePressed(MouseEvent e)
        {
            if(e.getSource() == memories)
            {
                memories.setBackground(Color.DARK_GRAY);
                
            }
            else if(e.getSource() == pictures)
            {
                pictures.setBackground(Color.DARK_GRAY);
            }
        }
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource() == memories)
            {
                memories.setBackground(Color.LIGHT_GRAY);
            }
            else if(e.getSource() == pictures)
            {
                pictures.setBackground(Color.LIGHT_GRAY);
            }
        }   
        public void mouseClicked(MouseEvent e)
        {
            if(e.getSource() == memories)
            {
                center.recall();
            }
            else if(e.getSource() == pictures)
            {

            }
        }             
    }
}