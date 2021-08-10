package thread.yf.sumholiday21twelve;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public class Line {

	int x=375,y=175;
	int endx;//endx=x+线长*cos
	int endy;//endy=x+线长*sin
	int a;
	int h;
	
	double length=100;//线长
	double minlength=100;
	double maxlength=750;
	double n=0.1;

	//角度  double jiaodu=1;  此时使用的是弧度制   垂直状态是二分之π=1.57   1是在右边 2是在左边
	//设一个 0-1的n 由此n*π便可控制角度
	int dir;
	//状态  0是摇摆 1是伸长 2是返回 3抓取返回金 4抓取返回石头
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
		 //抓大金子
		 for(int i=0;i<biggoldList.size();i++) {
			  if(endx>biggoldList.get(i).x&&endx<biggoldList.get(i).x+biggoldList.get(i).weight
					 &&endy>biggoldList.get(i).y&&endy<biggoldList.get(i).y+biggoldList.get(i).height) {
				 System.out.println(1);
				 state=6;
				 a=i;
				 
			  }
			 }
		 
		 //抓小金子
		 for(int i=0;i<smallgoldList.size();i++) {
			  if(endx>smallgoldList.get(i).x&&endx<smallgoldList.get(i).x+smallgoldList.get(i).weight
					 &&endy>smallgoldList.get(i).y&&endy<smallgoldList.get(i).y+smallgoldList.get(i).height) {
				 System.out.println(1);
				 state=5;
				 a=i;
				 
			  }
			 }
		 
		 
		 //抓金子
		 for(int i=0;i<goldList.size();i++) {
		  if(endx>goldList.get(i).x&&endx<goldList.get(i).x+goldList.get(i).weight
				 &&endy>goldList.get(i).y&&endy<goldList.get(i).y+goldList.get(i).height) {
			 System.out.println(1);
			 state=3;
			 a=i;
			 
		  }
		 }
		 
		 //抓石头
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
			 endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
			 endy=(int) (y+length*Math.sin(n*Math.PI));
			 g.drawLine(x, y, endx, endy);
			 g.drawImage(image.getImage(), endx-36, endy-2, null);
			
			break;

		case 1:
			if(length<maxlength) {
			length+=10;
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
				endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
			endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
				endx=(int) (x+length*Math.cos(n*Math.PI));  //cos计算出得是一个double类型
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
