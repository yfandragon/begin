package com.yf.sumholiday21six;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageEFF {
	
	public int[][] ImageToArray(String imgpath){
		File imgfile=new File(imgpath);
		BufferedImage img=null;
		try {
			img=ImageIO.read(imgfile);
		}catch(java.io.IOException e) {
			e.printStackTrace();
			System.err.println("ͼ���ȡʧ��");
		}
		int w=img.getWidth();
		int h=img.getHeight();
		int [][] imgArr=new int [w][h];
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				imgArr[i][j]=img.getRGB(i, j);
			}
			
		}
		
		return imgArr;
		
	}
	
	//�ͻ��ں�
	
	public BufferedImage drawImagenmd(int [][]imgArr1,int [][]imgArr2,Graphics g) {      //�÷���ֵ����
		BufferedImage buffimg=new BufferedImage(imgArr1.length,imgArr1[0].length,BufferedImage.TYPE_INT_ARGB);
		//TYPE_INT_ARGB������ɫΪint(4���ֽ�),��ɫͨ������16-23,��ɫ��8-15����ɫ��0-7
		
		// �����е�����ֵ���뻺��ͼƬ- ����- �ڴ����
		for(int i=0;i<imgArr1.length;i++) {
			for(int j=0;j<imgArr1[i].length;j++) {
				int value=imgArr1[i][j];
				//Color color=new Color(value);
				//g.setColor(color);//�൱�����ػ�ÿ�����ص� ����Ҫ������ÿ�����ص���ɫ�����
				//g.fillRect(50+i, 50+j, 1, 1);
				int value1=imgArr2[i][j];
				buffimg.setRGB(i, j, (value+value1)/2);//ȫ����ʼ���������ڴ��BufferedImage�� ��δ��
			}
		}
		//������ͼƬһ���Ի���
		g.drawImage(buffimg, 0,0,null);
		
		return buffimg;
	}
	
	//�����˴�ש
	
	public BufferedImage drawImage0(int [][]imgArr,Graphics g) {
		BufferedImage buffimg=new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for(int i=0;i<imgArr.length;i+=10) {
			for(int j=0;j<imgArr[i].length;j+=10) {
				int value=imgArr[i][j];
				Color color=new Color(value);
				buffg.setColor(color);//�൱�����ػ�ÿ�����ص� ����Ҫ������ÿ�����ص���ɫ�����
				buffg.fillRect(i, j, 8, 8);
			}
		}
		g.drawImage(buffimg, 0, 0, null);
		return buffimg;
	}
	
	
	  /**
     * �Ҷ�
     * @param path  ͼƬ·��
     * @param g ͼ���� ���� �������ص�
     */

	public BufferedImage drawImage1(int [][]imgArr,Graphics g) {
		BufferedImage buffimg=new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for (int i=0;i<imgArr.length;i++) {
			for(int j=0;j<imgArr[i].length;j++) {
				int value=imgArr[i][j];
				int red=(value>>16)&0xFF;
				int green=(value>>8)&0xFF;
				int blue=(value>>0)&0xFF;
				int gray=(int)(red*0.6+green*0.3+blue*0.1);
				Color color=new Color(gray,gray,gray);
				buffg.setColor(color);
				buffg.fillRect(i, j, 1, 1);
				
			}
		}
		g.drawImage(buffimg, 0, 0, null);
		return buffimg;
	}
	
	 /**
     * ��ֵ�����ֻ�Ч��
     * @param path  ͼƬ·��
     * @param g ͼ���� ���� �������ص�
     */

	public BufferedImage drawImage2(int [][]imgArr,Graphics g) {
		BufferedImage buffimg = new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for(int i=0;i<imgArr.length;i++) {
			for(int j=0;j<imgArr[i].length;j++) {
				int value=imgArr[i][j];
				int red=(value>>16)&0xFF;
				int green=(value>>8)&0xFF;
				int blue=(value>>0)&0xFF;
				int gray=(int)(red*0.4+green*0.3+blue*0.3);
				if(gray<127) {
				    buffg.setColor(Color.BLACK);
				}else {
					buffg.setColor(Color.white);
				}
				buffg.fillRect(i, j, 1, 1);
			}
		}
		g.drawImage(buffimg, 0, 0, null);
		return buffimg;
	}
	
	 /**
     * �������
     *  ����֮���������ɫ���бȽ�
     * @param path  ͼƬ·��
     * @param g ͼ���� ���� �������ص�
     */

	public BufferedImage drawImage3(int [][]imgArr,Graphics g) {
		BufferedImage buffimg = new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for(int i=0;i<imgArr.length-2;i++) {
			for(int j=0;j<imgArr[i].length-2;j++) {//ע���2
				int value=imgArr[i][j];
				int red=(value>>16)&0xFF;
				int green=(value>>8)&0xFF;
				int blue=(value>>0)&0xFF;
				int gray=(int)(red*0.4+green*0.3+blue*0.3);
				
				int valueN=imgArr[i+2][j+2];
				int redN=(valueN>>16)&0xFF;
				int greenN=(value>>8)&0xFF;
				int blueN=(value>>0)&0xFF;
				int grayN=(int)(redN*0.4+greenN*0.3+blueN*0.3);
				
				if(Math.abs(gray-grayN)>15) {//Math.abs����ֵ
					buffg.setColor(Color.red);
				}else {
					buffg.setColor(Color.white);
				}
				buffg.fillRect(i, j, 1, 1);
			}
		}
		g.drawImage(buffimg, 0, 0, null);
		return buffimg;
	}
	
	//������
	
		public BufferedImage drawImage5(int [][]imgArr,Graphics g) {
	
			BufferedImage buffimg=new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
			Graphics buffg=buffimg.getGraphics();   //ֱ�ӻ��ڻ����� ����GPU ���ڴ����
			for(int i=0;i<imgArr.length;i+=10) {
				for(int j=0;j<imgArr[i].length;j+=10) {
					int value=imgArr[i][j];
					Color color=new Color(value);
					buffg.setColor(color);//�൱�����ػ�ÿ�����ص� ����Ҫ������ÿ�����ص���ɫ�����
					buffg.fillRect(i, j, 10, 10);//�����Դ�50������ drawImage�����겻��Ϊ0�ͺ�
				}
			}
			g.drawImage(buffimg, 0, 0, null);//��buffimg�ڴ滺�����е�ȡ��һ���Ի���
			return buffimg;
		}
		//ԭͼ��С
		public void drawImage6(int [][]imgArr,Graphics g) {
			BufferedImage buffimg=new BufferedImage(imgArr.length,imgArr[0].length,BufferedImage.TYPE_INT_ARGB);
			//TYPE_INT_ARGB������ɫΪint(4���ֽ�),��ɫͨ������16-23,��ɫ��8-15����ɫ��0-7
			
			// �����е�����ֵ���뻺��ͼƬ- ����- �ڴ����
			for(int i=0;i<imgArr.length;i++) {
				for(int j=0;j<imgArr[i].length;j++) {
					int value=imgArr[i][j];
					//Color color=new Color(value);
					//g.setColor(color);//�൱�����ػ�ÿ�����ص� ����Ҫ������ÿ�����ص���ɫ�����
					//g.fillRect(50+i, 50+j, 1, 1);
					
					buffimg.setRGB(i, j, value);//ȫ����ʼ���������ڴ��BufferedImage�� ��δ��
				}
			}
			//������ͼƬһ���Ի���
			g.drawImage(buffimg, 50,50,200,200,null);//ͨ��200 200 ��С
		}
		
		
		//ԭͼ
		public BufferedImage drawImagelaozi(int [][]imgArr,Graphics g) {      //�÷���ֵ����
			BufferedImage buffimg=new BufferedImage(imgArr.length,imgArr[0].length,BufferedImage.TYPE_INT_ARGB);
			//TYPE_INT_ARGB������ɫΪint(4���ֽ�),��ɫͨ������16-23,��ɫ��8-15����ɫ��0-7
			
			// �����е�����ֵ���뻺��ͼƬ- ����- �ڴ����
			for(int i=0;i<imgArr.length;i++) {
				for(int j=0;j<imgArr[i].length;j++) {
					int value=imgArr[i][j];
					//Color color=new Color(value);
					//g.setColor(color);//�൱�����ػ�ÿ�����ص� ����Ҫ������ÿ�����ص���ɫ�����
					//g.fillRect(50+i, 50+j, 1, 1);
					
					buffimg.setRGB(i, j, value);//ȫ����ʼ���������ڴ��BufferedImage�� ��δ��
				}
			}
			//������ͼƬһ���Ի���
			g.drawImage(buffimg, 0,0,null);
			
			return buffimg;
		}

}
