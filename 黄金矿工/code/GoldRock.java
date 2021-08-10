package thread.yf.sumholiday21twelve;

import java.awt.Graphics;

import javax.swing.ImageIcon;

//继承的为什么不能直接用需要放在构造方法里
public class GoldRock extends Rock{
	GoldRock(){
		//int num=(int)(Math.random()*n);			//返回大于等于0小于n之间的随机数
		//int num0=m+(int)(Matn.randon()*n);		//返回大于等于m小于m+n（不包括m+n）之间的随机数
		////返回指定范围的随机数(m-n之间)的公式:     Math.random()*(n-m)+m；

		//this.x=(int) (Math.random()*750);
		//this.y=(int) (Math.random()*750+200);
		point=4;
		this.weight=52;
		this.height=52;
		this.image=new ImageIcon("image/gold1.gif");
	}
	//绘制
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image.getImage(),x,y,null);

	}
	

			

}
