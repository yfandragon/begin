package thread.yf.sumholiday21twelve;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Bj {
	int count;
	int waternum=3;




	//public void setcount(int a) {
		//count=a;
	//}
	
	private ImageIcon image1=new ImageIcon("image/ren.png");
	public Bj(int count,int waternum) {
		super();
		this.count = count;
		this.waternum=waternum;

	}
	private ImageIcon image=new ImageIcon("image/bg.jpg");
	private ImageIcon image2=new ImageIcon("image/sky.jpg"); //750 200
	private ImageIcon image3=new ImageIcon("image/water.png");
	BufferedImage buffimg=new BufferedImage(750, 950, BufferedImage.TYPE_INT_ARGB);
	 void Paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(image.getImage(),0,200,null);//地	750 750
		g.drawImage(image2.getImage(),0,0,null);//天       750 200
	
		g.drawImage(image1.getImage(),325,100,null);//人     100 100
		g.drawImage(image3.getImage(), 450, 40, null);
		//System.out.println(count);
		g.setColor(Color.orange);
		g.setFont(new Font("仿宋",Font.BOLD,30));
		g.drawString("分数"+count, 30, 150);

		
		g.drawString("*"+waternum, 510, 70);

	}
	 
	 public int getwaternum() {
		 return waternum;
	 }

}
