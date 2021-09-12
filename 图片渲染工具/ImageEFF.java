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
			System.err.println("图像读取失败");
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
	
	//油画融合
	
	public BufferedImage drawImagenmd(int [][]imgArr1,int [][]imgArr2,Graphics g) {      //用返回值保存
		BufferedImage buffimg=new BufferedImage(imgArr1.length,imgArr1[0].length,BufferedImage.TYPE_INT_ARGB);
		//TYPE_INT_ARGB代表颜色为int(4个字节),红色通道道在16-23,绿色在8-15和蓝色在0-7
		
		// 将所有的像素值存入缓冲图片- 操作- 内存操作
		for(int i=0;i<imgArr1.length;i++) {
			for(int j=0;j<imgArr1[i].length;j++) {
				int value=imgArr1[i][j];
				//Color color=new Color(value);
				//g.setColor(color);//相当于在重绘每个像素点 所以要给画笔每个像素的颜色再填充
				//g.fillRect(50+i, 50+j, 1, 1);
				int value1=imgArr2[i][j];
				buffimg.setRGB(i, j, (value+value1)/2);//全部初始化缓冲在内存的BufferedImage中 还未画
			}
		}
		//将整张图片一次性绘制
		g.drawImage(buffimg, 0,0,null);
		
		return buffimg;
	}
	
	//马赛克瓷砖
	
	public BufferedImage drawImage0(int [][]imgArr,Graphics g) {
		BufferedImage buffimg=new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for(int i=0;i<imgArr.length;i+=10) {
			for(int j=0;j<imgArr[i].length;j+=10) {
				int value=imgArr[i][j];
				Color color=new Color(value);
				buffg.setColor(color);//相当于在重绘每个像素点 所以要给画笔每个像素的颜色再填充
				buffg.fillRect(i, j, 8, 8);
			}
		}
		g.drawImage(buffimg, 0, 0, null);
		return buffimg;
	}
	
	
	  /**
     * 灰度
     * @param path  图片路径
     * @param g 图形类 功能 绘制像素点
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
     * 二值化的手绘效果
     * @param path  图片路径
     * @param g 图形类 功能 绘制像素点
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
     * 轮廓检测
     *  相邻之间的像素颜色进行比较
     * @param path  图片路径
     * @param g 图形类 功能 绘制像素点
     */

	public BufferedImage drawImage3(int [][]imgArr,Graphics g) {
		BufferedImage buffimg = new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
		Graphics buffg=buffimg.getGraphics();
		for(int i=0;i<imgArr.length-2;i++) {
			for(int j=0;j<imgArr[i].length-2;j++) {//注意减2
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
				
				if(Math.abs(gray-grayN)>15) {//Math.abs绝对值
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
	
	//马赛克
	
		public BufferedImage drawImage5(int [][]imgArr,Graphics g) {
	
			BufferedImage buffimg=new BufferedImage(imgArr.length, imgArr[0].length, BufferedImage.TYPE_INT_ARGB);
			Graphics buffg=buffimg.getGraphics();   //直接画在缓冲区 不到GPU 是内存操作
			for(int i=0;i<imgArr.length;i+=10) {
				for(int j=0;j<imgArr[i].length;j+=10) {
					int value=imgArr[i][j];
					Color color=new Color(value);
					buffg.setColor(color);//相当于在重绘每个像素点 所以要给画笔每个像素的颜色再填充
					buffg.fillRect(i, j, 10, 10);//坐标以从50往后移 drawImage中坐标不用为0就好
				}
			}
			g.drawImage(buffimg, 0, 0, null);//将buffimg内存缓冲区中的取出一次性绘制
			return buffimg;
		}
		//原图缩小
		public void drawImage6(int [][]imgArr,Graphics g) {
			BufferedImage buffimg=new BufferedImage(imgArr.length,imgArr[0].length,BufferedImage.TYPE_INT_ARGB);
			//TYPE_INT_ARGB代表颜色为int(4个字节),红色通道道在16-23,绿色在8-15和蓝色在0-7
			
			// 将所有的像素值存入缓冲图片- 操作- 内存操作
			for(int i=0;i<imgArr.length;i++) {
				for(int j=0;j<imgArr[i].length;j++) {
					int value=imgArr[i][j];
					//Color color=new Color(value);
					//g.setColor(color);//相当于在重绘每个像素点 所以要给画笔每个像素的颜色再填充
					//g.fillRect(50+i, 50+j, 1, 1);
					
					buffimg.setRGB(i, j, value);//全部初始化缓冲在内存的BufferedImage中 还未画
				}
			}
			//将整张图片一次性绘制
			g.drawImage(buffimg, 50,50,200,200,null);//通过200 200 缩小
		}
		
		
		//原图
		public BufferedImage drawImagelaozi(int [][]imgArr,Graphics g) {      //用返回值保存
			BufferedImage buffimg=new BufferedImage(imgArr.length,imgArr[0].length,BufferedImage.TYPE_INT_ARGB);
			//TYPE_INT_ARGB代表颜色为int(4个字节),红色通道道在16-23,绿色在8-15和蓝色在0-7
			
			// 将所有的像素值存入缓冲图片- 操作- 内存操作
			for(int i=0;i<imgArr.length;i++) {
				for(int j=0;j<imgArr[i].length;j++) {
					int value=imgArr[i][j];
					//Color color=new Color(value);
					//g.setColor(color);//相当于在重绘每个像素点 所以要给画笔每个像素的颜色再填充
					//g.fillRect(50+i, 50+j, 1, 1);
					
					buffimg.setRGB(i, j, value);//全部初始化缓冲在内存的BufferedImage中 还未画
				}
			}
			//将整张图片一次性绘制
			g.drawImage(buffimg, 0,0,null);
			
			return buffimg;
		}

}
