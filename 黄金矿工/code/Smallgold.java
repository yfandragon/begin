package thread.yf.sumholiday21twelve;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Smallgold extends Rock{
	Smallgold(){
		//int num=(int)(Math.random()*n);			//返回大于等于0小于n之间的随机数
		//int num0=m+(int)(Matn.randon()*n);		//返回大于等于m小于m+n（不包括m+n）之间的随机数
		////返回指定范围的随机数(m-n之间)的公式:     Math.random()*(n-m)+m；

		//this.x=(int) (Math.random()*750);
		//this.y=(int) (Math.random()*750+200);
		point=4;
		this.weight=36;
		this.height=36;
		this.image=new ImageIcon("image/gold0.gif");
	}
	//绘制
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image.getImage(),x,y,null);

	}
	

}
