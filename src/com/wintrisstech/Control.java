package com.wintrisstech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.imageio.ImageIO.read;

/***********************************************************************************************
 * Main Contrtol Class
 * 6/5/19
 * Copyright David Frieder 2019
 * Changed the nature of thomas's speed control
 ***********************************************************************************************/

public class Control extends JComponent implements ActionListener, Runnable, KeyListener
{
    public Thomas thomas = new Thomas();
    int thomasSpeed = 7;
    Timer paintTicker = new Timer(20, this);
    int pseudoThomasTicker;
    int paintTickerCounter = 0;

    public static void main(String[] args)
    {
        System.out.println("ThomasFebruary" + "version 1.1.1, 5/11/19");
        SwingUtilities.invokeLater(new Control());
    }

    @Override
    public void run()
    {
        initializeGameWindow();
    }

    /***********************************************************************************************
     * Set up main JFrame
     ***********************************************************************************************/
    private void initializeGameWindow()    //Sets up the game window
    {
        JFrame gameWindow = new JFrame("Thomas the tank");
        gameWindow.setVisible(true);
        gameWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        gameWindow.add(this);// Adds the paint method to the JFrame
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(new Color(200, 235, 255));
        gameWindow.addKeyListener(this);
        paintTicker.start();
    }

    public void drawThomas()    //References the Thomas image arrays in the thomas class
    {
        thomas.getThomasSpriteImageArray();
        thomas.getReverseThomasImageArray();
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        if(thomasSpeed > 0){
            g2.drawImage(thomas.getThomasSpriteImageArray()[pseudoThomasTicker], 500, 500, 500, 300, this);
        }
        else{
            g2.drawImage(thomas.getReverseThomasImageArray()[pseudoThomasTicker], 500, 500, 500, 300, this);
        }
    }
    //TODO: MAKE THOMAS MOVE LEFT WHEN THE SPEED IS NEGATIVE
    @Override
    public void actionPerformed(ActionEvent e)
    {
        paintTickerCounter++;
        if(thomasSpeed != 0){
            if (paintTickerCounter % (100 / thomasSpeed) == 0)
            {
                pseudoThomasTicker++;
                pseudoThomasTicker %= 8;
            }
        }
        else{
            pseudoThomasTicker = 0;
        }
        System.out.println(thomasSpeed);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            thomasSpeed++;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            thomasSpeed--;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}

