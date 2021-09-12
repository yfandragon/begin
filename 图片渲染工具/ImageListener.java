package com.yf.sumholiday21six;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class ImageListener extends ListenerUtils {
	public Graphics g;
	JPanel jp;
	String path="F:\\蓝杰\\作业\\1.png";
	String path1="F:\\蓝杰\\作业\\2.jpg";
	String path2="F:\\蓝杰\\作业\\3.jpg";
	public String btnstrs;
	BufferedImage [] buffimgs=new BufferedImage[100];//存返回值对象的数组
	Stack<BufferedImage> buffstack= new Stack<BufferedImage>(); 
	int index=0;//计数器
	int indexmsk=0;
	int x1,y1,x2,y2;
	int x3,y3;
	
	//  1）构造出来一个类的实例 （2）对构造出来个一个类的实例（对象）初始化
	//构造方法的名字必须与定义他的类名完全相同，没有返回类型，甚至连void也没有
	int[][] imgArr;
	int[][] imgArr1;
	int[][] imgArr2;
	ImageEFF imgeff;
	//类构造方法
	//就是类构造对象时调用的方法，主要用来实例化对象。
	ImageListener(){          
		imgeff = new ImageEFF();
		imgArr=imgeff.ImageToArray(path);
		imgArr1=imgeff.ImageToArray(path1);
		imgArr2=imgeff.ImageToArray(path2);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		btnstrs =e.getActionCommand();
		if(btnstrs.equals("原图")) {
			BufferedImage img=imgeff.drawImagelaozi(imgArr, g);//img 存返回值对象（内存缓冲池中已画好的内容）
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("马赛克")) {
			BufferedImage img=imgeff.drawImage5(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("马赛克瓷砖")) {
			BufferedImage img=imgeff.drawImage0(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			buffstack.push(img);
		}
		else if(btnstrs.equals("灰度")) {
			BufferedImage img=imgeff.drawImage1(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("轮廓检测")) {
			BufferedImage img=imgeff.drawImage3(imgArr, g);
			buffimgs[index++]=img;	
			indexmsk++;
			buffstack.push(img);
			}
		else if(btnstrs.equals("二值化")) {
			BufferedImage img=imgeff.drawImage2(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			buffstack.push(img);
		}
		else if(btnstrs.equals("缩小")) {
			//取当前图片
			BufferedImage buffimg=buffimgs[indexmsk-1];
			//创建一个新的空缓冲区
			BufferedImage buffimg1=new BufferedImage(buffimg.getWidth(), buffimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics buffg=buffimg1.getGraphics();
			for(int i=0;i<buffimg.getWidth();i++) {
				for(int j=0;j<buffimg.getHeight();j++) {
					int rgbvalue=buffimg.getRGB(i,j);
					Color color=new Color(rgbvalue);
					buffimg1.setRGB(i, j, rgbvalue);

				}
			}
			g.drawImage(buffimg, 0,0,200,200,null);
			
			buffimgs[index++]=buffimg1;
			indexmsk++;
		}
		else if(btnstrs.equals("撤回")) {

			if(index==0) 
			{
				 return;
			}
			else 
			{
			 // 删除数据 
			 buffimgs[index-1]=null;
			 index--;
			 
			}
		    
			 jp.repaint();//调用repaint 就相当于在调用jp的paint方法
		}
		else if(btnstrs.equals("油画融合")) {
			BufferedImage img=imgeff.drawImagenmd(imgArr1,imgArr2, g);
			buffimgs[index++]=img;
			indexmsk++;
		}

		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
		if(btnstrs.equals("选择截屏区域")) {
			x1=e.getX();
			y1=e.getY();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
		if(btnstrs.equals("选择截屏区域")) {
			x2=e.getX();
			y2=e.getY();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 鼠标拖动 局部马赛克
		System.out.println("拖动");
		int oldx=0;
		int oldy=0;
		if("局部马赛克".equals(btnstrs)) {
			
		
		//取当前图片
		BufferedImage buffimg=buffimgs[indexmsk-1];
		//创建一个新的空缓冲区
		BufferedImage buffimg1=new BufferedImage(buffimg.getWidth(), buffimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics buffg1=buffimg1.getGraphics();
		int x=e.getX();
		int y=e.getY();
		//获取像素点太快了
		if((Math.abs(x-oldx)*Math.abs(y-oldy))<100) {
			return;
		}
		else {
			oldx=x;
			oldy=y;
		}
		//根据坐标获取当前像素点的像素值
		int rgbvalue=buffimg.getRGB(x, y);
		
		//转成颜色 设置画笔 绘制方块
		Color color=new Color(rgbvalue);
		//g.setColor(color);
		//g.fillRect(x, y, 10, 10);
				//将处理后的 存储在buffimg中
		//buffg.setColor(color);
		buffg1.setColor(color);
		//System.out.println("color="+color);
		buffg1.fillRect(x, y, 10, 10);
		g.drawImage(buffimg1, 0, 0, null);
		//jp.repaint();//修改数据后使用paint刷新
	
		buffimgs[index++]=buffimg1;
		System.out.println("index="+index);
		//比如说我在原图画了一道局部马赛克 原图就变成带一道马赛克的了被覆盖了 局部马赛克与原图对应的是同一个对象
		//不断覆盖原来的 index(1-10++)全是一张图 需要使用新的bufferedimage
		
		
		}

		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 if(btnstrs.equals("截屏放置区域")) {
			x3=e.getX();
			y3=e.getY();
			//取当前图片
			BufferedImage buffimg=buffimgs[indexmsk-1];
			//创建一个新的空缓冲区
			BufferedImage buffimg1=new BufferedImage(buffimg.getWidth(), buffimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics buffg=buffimg1.getGraphics();
			for(int i=x1;i<=x2;i++) {
				for(int j=y1;j<=y2;j++) {
					int rgbvalue=buffimg.getRGB(i,j);
					Color color=new Color(rgbvalue);
					//buffimg1.setRGB(i, j, rgbvalue);	
					buffg.setColor(color);
					buffg.fillRect(i, j, 1, 1);

				}
			}
			g.drawImage(buffimg1,x3,y3,x2-x1,y2-y1,null);
			
			buffimgs[index++]=buffimg1;
			indexmsk++;
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyPressed(e);
	}

}
