package thread.yf.sumholiday21twelve;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listenener implements MouseListener{
	private GameUi ui;
	private Line line;//传入line对象
	private Bj bj;
	int waternum;
	

	public Listenener(Line line,GameUi ui) {
		super();
		this.line = line;
		this.ui=ui;
		waternum=this.ui.waternum;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==1&&line.state==0){//左键是1 右键是3
			line.state=1;
		}
		if(e.getButton()==3&&(line.state==3||line.state==5||line.state==6)) {
			//waternum=this.ui.waternum;
			//System.out.println("waternum="+waternum);
			waternum--;
			//System.out.println("waternum="+waternum);
			line.h=10;
		}
		//System.out.println("waternum="+waternum);
		if(e.getButton()==3&&line.state==4) {
			waternum--;
			line.state=7;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
