package thread.yf.sumholiday21twelve;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Source {
	
	static int level=1;
	int levelcount=level*2;
	public void paint(Graphics g) {
		g.setColor(Color.orange);
		g.setFont(new Font("����",Font.BOLD,30));
		g.drawString("�ؿ�"+level,30, 90);
		g.drawString("���ط���"+levelcount,30, 120);
		
		
	}

}
