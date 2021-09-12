package com.yf.sumholiday21six;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class ImageUI extends JPanel{
	String [] btnstrs= {"ԭͼ","������","�Ҷ�","��ֵ��","�����˴�ש","�������","�ֻ���","����","�ͻ��ں�","�ֲ�������","��С","������������","ѡ���������","����"};
	static final ImageListener imageListener=new ImageListener();
	//���ڱ�������ʾһ����ֵ�Ͳ����޸ģ�����ͨ���������Է��ʡ�
	//���ڷ�������ʾ���ɸ��ǣ����ҿ���ͨ������ֱ�ӷ��ʡ�
	public static void main(String[] args) {
		new ImageUI().initUI();
		
	}
	
	
	public void initUI() {
		JFrame jf=new  JFrame("ͼ�α��");
		jf.setSize(800,600);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		
		// ��ť��������� JPanel 
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
		
		//��ͼ��
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
		//paint(g)�������ػ�ͼ��Ҫ����super.paint(g)����ʾ��ԭ��ͼ��Ļ����ϣ��ٻ�ͼ��
		//�������super.paint(g)���ػ�ʱ���Ὣԭ�еĻ�����գ��ٸ���paing(g)�������ơ�
		
		super.paint(g);//��ı�����״̬����Ҫ���»�һ��  ��JPanel�ػ�һ��
		BufferedImage []imgs=imageListener.buffimgs;
		for(int i=0;i<imgs.length;i++) {
			//g.drawImage(imgs[i], 0, 0, null);
			if(imgs[i]==null) {
				return;
			}
			
			
			//�����¿ջ���ͼƬ����
			g.drawImage(imgs[i], 0, 0,this.getWidth(),this.getHeight(), null);
			BufferedImage buffimg1=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics buffg1=buffimg1.getGraphics();
			//�������Ļ���ͼƬ�����»���ͼƬ����
			buffg1.drawImage(imgs[imageListener.index-1], 0, 0,this.getWidth(),this.getHeight(), null);
			//�÷Ŵ���ͼƬ�滻
			imgs[imageListener.index-1]=buffimg1;
			//�����»���ͼƬ
			g.drawImage(buffimg1, 0, 0, null);
			System.out.println("i="+i);
		}
		
	}
	

}
