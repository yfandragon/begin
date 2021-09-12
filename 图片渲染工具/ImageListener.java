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
	String path="F:\\����\\��ҵ\\1.png";
	String path1="F:\\����\\��ҵ\\2.jpg";
	String path2="F:\\����\\��ҵ\\3.jpg";
	public String btnstrs;
	BufferedImage [] buffimgs=new BufferedImage[100];//�淵��ֵ���������
	Stack<BufferedImage> buffstack= new Stack<BufferedImage>(); 
	int index=0;//������
	int indexmsk=0;
	int x1,y1,x2,y2;
	int x3,y3;
	
	//  1���������һ�����ʵ�� ��2���Թ��������һ�����ʵ�������󣩳�ʼ��
	//���췽�������ֱ����붨������������ȫ��ͬ��û�з������ͣ�������voidҲû��
	int[][] imgArr;
	int[][] imgArr1;
	int[][] imgArr2;
	ImageEFF imgeff;
	//�๹�췽��
	//�����๹�����ʱ���õķ�������Ҫ����ʵ��������
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
		if(btnstrs.equals("ԭͼ")) {
			BufferedImage img=imgeff.drawImagelaozi(imgArr, g);//img �淵��ֵ�����ڴ滺������ѻ��õ����ݣ�
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("������")) {
			BufferedImage img=imgeff.drawImage5(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("�����˴�ש")) {
			BufferedImage img=imgeff.drawImage0(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			buffstack.push(img);
		}
		else if(btnstrs.equals("�Ҷ�")) {
			BufferedImage img=imgeff.drawImage1(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			System.out.println("index"+index);
		}
		else if(btnstrs.equals("�������")) {
			BufferedImage img=imgeff.drawImage3(imgArr, g);
			buffimgs[index++]=img;	
			indexmsk++;
			buffstack.push(img);
			}
		else if(btnstrs.equals("��ֵ��")) {
			BufferedImage img=imgeff.drawImage2(imgArr, g);
			buffimgs[index++]=img;
			indexmsk++;
			buffstack.push(img);
		}
		else if(btnstrs.equals("��С")) {
			//ȡ��ǰͼƬ
			BufferedImage buffimg=buffimgs[indexmsk-1];
			//����һ���µĿջ�����
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
		else if(btnstrs.equals("����")) {

			if(index==0) 
			{
				 return;
			}
			else 
			{
			 // ɾ������ 
			 buffimgs[index-1]=null;
			 index--;
			 
			}
		    
			 jp.repaint();//����repaint ���൱���ڵ���jp��paint����
		}
		else if(btnstrs.equals("�ͻ��ں�")) {
			BufferedImage img=imgeff.drawImagenmd(imgArr1,imgArr2, g);
			buffimgs[index++]=img;
			indexmsk++;
		}

		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
		if(btnstrs.equals("ѡ���������")) {
			x1=e.getX();
			y1=e.getY();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
		if(btnstrs.equals("ѡ���������")) {
			x2=e.getX();
			y2=e.getY();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// ����϶� �ֲ�������
		System.out.println("�϶�");
		int oldx=0;
		int oldy=0;
		if("�ֲ�������".equals(btnstrs)) {
			
		
		//ȡ��ǰͼƬ
		BufferedImage buffimg=buffimgs[indexmsk-1];
		//����һ���µĿջ�����
		BufferedImage buffimg1=new BufferedImage(buffimg.getWidth(), buffimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics buffg1=buffimg1.getGraphics();
		int x=e.getX();
		int y=e.getY();
		//��ȡ���ص�̫����
		if((Math.abs(x-oldx)*Math.abs(y-oldy))<100) {
			return;
		}
		else {
			oldx=x;
			oldy=y;
		}
		//���������ȡ��ǰ���ص������ֵ
		int rgbvalue=buffimg.getRGB(x, y);
		
		//ת����ɫ ���û��� ���Ʒ���
		Color color=new Color(rgbvalue);
		//g.setColor(color);
		//g.fillRect(x, y, 10, 10);
				//�������� �洢��buffimg��
		//buffg.setColor(color);
		buffg1.setColor(color);
		//System.out.println("color="+color);
		buffg1.fillRect(x, y, 10, 10);
		g.drawImage(buffimg1, 0, 0, null);
		//jp.repaint();//�޸����ݺ�ʹ��paintˢ��
	
		buffimgs[index++]=buffimg1;
		System.out.println("index="+index);
		//����˵����ԭͼ����һ���ֲ������� ԭͼ�ͱ�ɴ�һ�������˵��˱������� �ֲ���������ԭͼ��Ӧ����ͬһ������
		//���ϸ���ԭ���� index(1-10++)ȫ��һ��ͼ ��Ҫʹ���µ�bufferedimage
		
		
		}

		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 if(btnstrs.equals("������������")) {
			x3=e.getX();
			y3=e.getY();
			//ȡ��ǰͼƬ
			BufferedImage buffimg=buffimgs[indexmsk-1];
			//����һ���µĿջ�����
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
