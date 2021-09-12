package com.yf.sumholiday21six;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class ImageUI extends JPanel{
	String [] btnstrs= {"原图","马赛克","灰度","二值化","马赛克瓷砖","轮廓检测","手绘风格","怀旧","油画融合","局部马赛克","缩小","截屏放置区域","选择截屏区域","撤回"};
	static final ImageListener imageListener=new ImageListener();
	//对于变量，表示一旦给值就不可修改，并且通过类名可以访问。
	//对于方法，表示不可覆盖，并且可以通过类名直接访问。
	public static void main(String[] args) {
		new ImageUI().initUI();
		
	}
	
	
	public void initUI() {
		JFrame jf=new  JFrame("图形编程");
		jf.setSize(800,600);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		
		// 按钮功能区面板 JPanel 
		JPanel jp1= new JPanel();
		jp1.setBackground(Color.DARK_GRAY);
		Dimension dim=new Dimension(120,0);
		jp1.setPreferredSize(dim);
		Dimension btndim=new Dimension(110,35);
		
		for(int i=0;i<btnstrs.length;i++) {
			JButton btn=new JButton(btnstrs[i]);
			btn.setBackground(Color.white);
			btn.setPreferredSize(btndim);
			btn.addActionListener(imageListener);
			jp1.add(btn);
			
			
		}
		
		//绘图区
		//JPanel jp2=new JPanel();
		this.setBackground(Color.BLACK);
		this.addMouseListener(imageListener);
		this.addMouseMotionListener(imageListener);
		this.addKeyListener(imageListener);
		
		
		jf.add(jp1,BorderLayout.EAST);
		jf.add(this);		
		jf.setVisible(true);
		Graphics g=this.getGraphics();
		imageListener.g=g;
		imageListener.jp=this;
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//paint(g)函数会重绘图像，要加上super.paint(g)，表示在原来图像的基础上，再画图。
		//如果不加super.paint(g)，重绘时，会将原有的绘制清空，再根据paing(g)函数绘制。
		
		super.paint(g);//你改变界面的状态它就要重新画一次  在JPanel重画一次
		BufferedImage []imgs=imageListener.buffimgs;
		for(int i=0;i<imgs.length;i++) {
			//g.drawImage(imgs[i], 0, 0, null);
			if(imgs[i]==null) {
				return;
			}
			
			
			//创建新空缓冲图片对象
			g.drawImage(imgs[i], 0, 0,this.getWidth(),this.getHeight(), null);
			BufferedImage buffimg1=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics buffg1=buffimg1.getGraphics();
			//将发大后的缓冲图片放入新缓冲图片对象
			buffg1.drawImage(imgs[imageListener.index-1], 0, 0,this.getWidth(),this.getHeight(), null);
			//用放大后的图片替换
			imgs[imageListener.index-1]=buffimg1;
			//绘制新缓冲图片
			g.drawImage(buffimg1, 0, 0, null);
			System.out.println("i="+i);
		}
		
	}
	

}
