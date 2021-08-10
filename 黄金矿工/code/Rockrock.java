package thread.yf.sumholiday21twelve;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Rockrock extends Rock{
	Rockrock(){
		this.weight=52;
		this.height=52;
		point=1;
		this.image=new ImageIcon("image/rock1.png");
		
	}
	//»æÖÆ
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image.getImage(),x,y,null);

	}

}
