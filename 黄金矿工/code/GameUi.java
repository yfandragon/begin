package thread.yf.sumholiday21twelve;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameUi extends JFrame{
	List<Biggold> biggoldList=new ArrayList<>();
	List<Smallgold> smallgoldList=new ArrayList<>();
	List<GoldRock> goldList=new ArrayList<>();
	List<Rockrock> rockList=new ArrayList<>();
	Source sc=new Source();

	
	int count;
	int waternum=3;

	

	
	Line line=new Line(this,goldList,rockList,smallgoldList,biggoldList);
	

	BufferedImage buffimage=new BufferedImage(750, 950, BufferedImage.TYPE_INT_ARGB);
	public static void main(String[] args) {
		
		new GameUi().init();
	}
	public GameUi() {
		// TODO Auto-generated constructor stub
		//level+=1;
		//levelcount+=level;
		
	}
	
	//添加多个biggold
	{
	for(int i=0;i<1;i++){
		Biggold biggold=new Biggold();
		biggoldList.add(biggold);

	}
	}
	
	//添加多个smallgold
	{
	for(int i=0;i<3;i++){
		Smallgold smallgold=new Smallgold();
		smallgoldList.add(smallgold);
	}
	}
	
	//添加多个Goldrock
	{
		for(int i=0;i<3;i++) {
			GoldRock gold=new GoldRock();
			goldList.add(gold);
		}
	}
	//添加多个Rockrock
	{
		for(int i=0;i<3;i++) {
			Rockrock rock=new Rockrock();
			rockList.add(rock);
		}
	}
	public void init() {
		this.setSize(750,950);
		this.setTitle("黄金矿工");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		
		//FlowLayout flow=new FlowLayout();
		//this.setLayout(flow);
		GameUi ui=new GameUi();
		
		Listenener listen=new Listenener(line,ui);
		//waternum=listen.waternum;
		System.out.println(listen.waternum);
		this.addMouseListener(listen);





		
		this.setVisible(true);
		//Graphics g=this.getGraphics();
		while(true) {
			//line.piant(g);
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 count=line.count;
			 waternum=listen.waternum;
			  nextlevel();	
			  //if() {
				//	level+=1;
					//levelcount+=level;
			 // sc.level++;
			  





			 
			 //System.out.println(count);
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//super.paint(g);
		Graphics buffg=buffimage.getGraphics();
		//Bj bj=new Bj(count);
		
		Bj bj=new Bj(count,waternum);


		//waternum=bj.waternum;
		//System.out.println("waternum"+waternum);
		bj.Paint(buffg);
		//for(int i=0;i<200;i++) {

		line.piant(buffg);
		sc.paint(buffg);
		for(int i=0;i<goldList.size();i++) {
			goldList.get(i).paint(buffg);
		}
		//gold.paint(buffg);
		for(int i=0;i<rockList.size();i++) {
			rockList.get(i).paint(buffg);
		}
		for(int i=0;i<smallgoldList.size();i++) {
			smallgoldList.get(i).paint(buffg);
		}
		for(int i=0;i<biggoldList.size();i++) {
			biggoldList.get(i).paint(buffg);
		}
		g.drawImage(buffimage, 0, 0, null);
		

		
	}
	public void nextlevel() {
		 if(count>=sc.levelcount) {
			 System.out.println("过关");
			 sc.level++;
			 dispose();
			 
			 GameUi ui=new GameUi();
			 ui.init();

			 
			 
		 }
	}



}
