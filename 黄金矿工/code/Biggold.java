package thread.yf.sumholiday21twelve;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Biggold extends Rock{

	Biggold(){
		//int num=(int)(Math.random()*n);			//���ش��ڵ���0С��n֮��������
		//int num0=m+(int)(Matn.randon()*n);		//���ش��ڵ���mС��m+n��������m+n��֮��������
		////����ָ����Χ�������(m-n֮��)�Ĺ�ʽ:     Math.random()*(n-m)+m��

		//this.x=(int) (Math.random()*750);
		//this.y=(int) (Math.random()*750+200);
		point=4;
		this.weight=105;
		this.height=105;
		this.image=new ImageIcon("image/gold2.gif");
	}
	//����
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image.getImage(),x,y,null);

	}
}
