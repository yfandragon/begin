package thread.yf.sumholiday21twelve;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public class Line {

	int x=375,y=175;
	int endx;//endx=x+�߳�*cos
	int endy;//endy=x+�߳�*sin
	int a;
	int h;
	
	double length=100;//�߳�
	double minlength=100;
	double maxlength=750;
	double n=0.1;

	//�Ƕ�  double jiaodu=1;  ��ʱʹ�õ��ǻ�����   ��ֱ״̬�Ƕ���֮��=1.57   1�����ұ� 2�������
	//��һ�� 0-1��n �ɴ�n*�б�ɿ��ƽǶ�
	int dir;
	//״̬  0��ҡ�� 1���쳤 2�Ƿ��� 3ץȡ���ؽ� 4ץȡ����ʯͷ
	int state;
	GameUi ui;
	private List<GoldRock> goldList;
	private List<Rockrock> rockList;
	private List<Smallgold> smallgoldList;
	private List<Biggold> biggoldList;
	int count;
	ImageIcon image=new ImageIcon("image/catch.png");

	
	
	
	 public Line(GameUi ui,List<GoldRock> goldList,List<Rockrock> rockList,List<Smallgold> smallgoldList,List<Biggold> biggoldList) {
		super();
		this.ui = ui;
		this.goldList=goldList;
		this.rockList=rockList;
		this.smallgoldList=smallgoldList;
		this.biggoldList=biggoldList;
		//bj.setcount(count);

	}

	 public void Catch() {
		 //ץ�����
		 for(int i=0;i<biggoldList.size();i++) {
			  if(endx>biggoldList.get(i).x&&endx<biggoldList.get(i).x+biggoldList.get(i).weight
					 &&endy>biggoldList.get(i).y&&endy<biggoldList.get(i).y+biggoldList.get(i).height) {
				 System.out.println(1);
				 state=6;
				 a=i;
				 
			  }
			 }
		 
		 //ץС����
		 for(int i=0;i<smallgoldList.size();i++) {
			  if(endx>smallgoldList.get(i).x&&endx<smallgoldList.get(i).x+smallgoldList.get(i).weight
					 &&endy>smallgoldList.get(i).y&&endy<smallgoldList.get(i).y+smallgoldList.get(i).height) {
				 System.out.println(1);
				 state=5;
				 a=i;
				 
			  }
			 }
		 
		 
		 //ץ����
		 for(int i=0;i<goldList.size();i++) {
		  if(endx>goldList.get(i).x&&endx<goldList.get(i).x+goldList.get(i).weight
				 &&endy>goldList.get(i).y&&endy<goldList.get(i).y+goldList.get(i).height) {
			 System.out.println(1);
			 state=3;
			 a=i;
			 
		  }
		 }
		 
		 //ץʯͷ
		 for(int i=0;i<rockList.size();i++) {
			  if(endx>rockList.get(i).x&&endx<rockList.get(i).x+rockList.get(i).weight
					 &&endy>rockList.get(i).y&&endy<rockList.get(i).y+rockList.get(i).height) {
				 System.out.println(1);
				 state=4;
				 a=i;
				 
			  }
			 }
		 
	 }



	public void piant(Graphics g) {
		// TODO Auto-generated method stub
		//System.out.println(count);
		 Catch();
		 switch (state) {
		case 0:
			 if(n>=0.9) {
				 dir=-1;
			 }else if(n<=0.1) {
				 dir=1;
			 }
			 n+=0.005*dir;
			 n=n%1;
			 endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
			 endy=(int) (y+length*Math.sin(n*Math.PI));
			 g.drawLine(x, y, endx, endy);
			 g.drawImage(image.getImage(), endx-36, endy-2, null);
			
			break;

		case 1:
			if(length<maxlength) {
			length+=10;
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
			endy=(int) (y+length*Math.sin(n*Math.PI));
			g.drawLine(x, y, endx, endy);
			g.drawImage(image.getImage(), endx-36, endy-2, null);
			}else {
				state=2;
			}
			
			break;
		case 2:
			if(length>minlength) {
				length-=15;
				endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
				endy=(int) (y+length*Math.sin(n*Math.PI));
				g.drawLine(x, y, endx, endy);
				g.drawImage(image.getImage(), endx-36, endy-2, null);
				
			}else {
				state=0;
			}
			break;
		case 3:
			if(length>minlength) {
			length-=5;
			length-=h;
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
			endy=(int) (y+length*Math.sin(n*Math.PI));
			g.drawLine(x, y, endx, endy);
			g.drawImage(image.getImage(), endx-36, endy-2, null);
			//for(int i=0;i<goldList.size();i++) {
			  goldList.get(a).x=endx;
			  goldList.get(a).y=endy;
			  
			}else {
				state=0;
				//for(int i=0;i<goldList.size();i++) {
				goldList.get(a).x=-140;
				goldList.get(a).x=-149;
				count+=4;
			}
			
			break;
			
		case 4:
			if(length>minlength) {
			length-=2;
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
			endy=(int) (y+length*Math.sin(n*Math.PI));
			g.drawLine(x, y, endx, endy);
			g.drawImage(image.getImage(), endx-36, endy-2, null);
			//for(int i=0;i<goldList.size();i++) {
			  rockList.get(a).x=endx;
			  rockList.get(a).y=endy;
			  
			}else {
				state=0;
				//for(int i=0;i<goldList.size();i++) {
				rockList.get(a).x=-140;
				rockList.get(a).x=-149;
				count+=1;
				
			}
			
			break;
		case 5:
			if(length>minlength) {
			length-=10;
			length-=h;
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
			endy=(int) (y+length*Math.sin(n*Math.PI));
			g.drawLine(x, y, endx, endy);
			g.drawImage(image.getImage(), endx-36, endy-2, null);
			//for(int i=0;i<goldList.size();i++) {
			  smallgoldList.get(a).x=endx;
			  smallgoldList.get(a).y=endy;
			  
			}else {
				state=0;
				//for(int i=0;i<goldList.size();i++) {
				smallgoldList.get(a).x=-140;
				smallgoldList.get(a).x=-149;
				count+=2;
				
			}
			break;
		case 6:
			if(length>minlength) {
				length-=2;
				length-=h;
				endx=(int) (x+length*Math.cos(n*Math.PI));  //cos���������һ��double����
				endy=(int) (y+length*Math.sin(n*Math.PI));
				g.drawLine(x, y, endx, endy);
				g.drawImage(image.getImage(), endx-36, endy-2, null);
				//for(int i=0;i<goldList.size();i++) {
				  biggoldList.get(a).x=endx;
				  biggoldList.get(a).y=endy;
				  
				}else {
					state=0;
					//for(int i=0;i<goldList.size();i++) {
					biggoldList.get(a).x=-140;
					biggoldList.get(a).x=-149;
					count+=8;
					
				}
				break;
		case 7:
			rockList.get(a).x=-140;
			rockList.get(a).x=-149;
			count+=1;
			state=2;
			
			
			
			
		default:
		}
		

	}

}
