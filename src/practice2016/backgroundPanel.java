package practice2016;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class BackgroundPanel extends JPanel//класс необходим для отрисовки изображения на фрейме
{
	BufferedImage img = null;
    public BackgroundPanel(boolean b)
    {
    	String st;
        if(b) st = "img.jpg";
        else st = "img2.jpg";
    	try {
    	    img = ImageIO.read(new File(st));
    	} catch (IOException e) {
    	}
    }

    public BackgroundPanel(int n) {
    			if(n==1) {
	    			try {
	    				img = ImageIO.read(new File("pigeon.jpg"));
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
    			}
    			if(n==2) {
	    			try {
	    				img = ImageIO.read(new File("legend.jpg"));
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    			
    			}
	}

	public void paint(Graphics g)
    {
        
        g.drawImage(img, 0, 0, null);
    }
}
