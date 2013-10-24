package nonYewChopper.Workers;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nonYewChopper.YewChop;

public class StatGui {
	private double EXP;
	long timePast;
	private int goalExp;
	Boolean lstate;
	JFrame frame;
	private static StatGui instance;
	JLabel label = new JLabel("", JLabel.LEFT);
	private int startExp;
	private int yewPrice;
	Runnable r = new Runnable(){
		@Override
		public void run() {
			while(lstate){
				int perHour = (int) ((YewChop.logsCut * 3600000d) / getElapsed());
				double gained = YewChop.logsCut * EXP;
				int until = (int)(goalExp - (gained + startExp));
				double expPerHr = perHour * EXP;
				if((int)EXP == 175){
					yewLabel(perHour, gained, until, expPerHr);
				}else{
					powerLabel(perHour, gained, until, expPerHr);
				}
				
			}
		}
		
	};
	
	private StatGui(long t, double exp, int startExp, int yewPrice) {
		this.timePast = t;
		this.EXP = exp;
		this.startExp = startExp;
		this.yewPrice = yewPrice;
	}
	
	public static StatGui getInstance(long t, double exp, int startExp, int yewPrice){
		if(instance == null){
			instance = new StatGui(t, exp, startExp, yewPrice);
		}
		return instance;
	}
	
	private long getElapsed(){
		return System.currentTimeMillis() - timePast;
	}
	
	  public String toElapsedString() {
          return format(getElapsed());
  }
	
	public void go(int goalExp){
		this.goalExp = goalExp;
		frame = new JFrame("NonYew Chopper - Stats");
		lstate = true;
		(new Thread(r)).start();
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				frame.dispose();
				lstate = false;
				frame = null;
			}
		});
		frame.setSize(300, 290);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);
		frame.add(disp());
		frame.setVisible(true);
	}
	
	private Component disp(){
		JPanel outter = new JPanel();
		JPanel panel = new JPanel();
		outter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBorder(BorderFactory.createTitledBorder("Run Stats: "));
		panel.add(label);
		outter.add(panel);
		return outter;
	}
	
	public void yewLabel(int perHour, double gained, int until, double expPerHr){
	label.setText("<html><div style='text-align: left;'>Current Price: " + Integer.toString(yewPrice)
			+ "<br>Time Ran: " + toElapsedString()
			+ "<br>Logs Cut: " + Integer.toString(YewChop.logsCut)
			+ "<br>Logs per Hour: "+ Integer.toString(perHour)
			+ "<br>Geepees per Hour: " + Integer.toString(perHour * yewPrice)
			+ "<br>Geepees Total: " + Integer.toString(YewChop.logsCut * yewPrice)
			+ "<br>"
			+ "<br>Experience Gained: " + Double.toString(gained)
			+ "<br>Experience per Hour: " + Double.toString(expPerHr)
			+ "<br>Current Experience: " + Integer.toString((int)gained + startExp)
			+ "<br>Experience until Level: " + Integer.toString(until)
			+ "<br>Time until Level: " + format((long)((until * 3600000d)/ expPerHr)) 
			+ "</div></html>"
			);
	}
	
	public void powerLabel(int perHour, double gained, int until, double expPerHr){
		label.setText("<html><div style='text-align: left;'>Time Ran: " + toElapsedString()
				+ "<br>Logs Cut: " + Integer.toString(YewChop.logsCut)
				+ "<br>Logs per Hour: "+ Integer.toString(perHour)
				+ "<br>"
				+ "<br>Experience Gained: " + Double.toString(gained)
				+ "<br>Experience per Hour: " + Double.toString(expPerHr)
				+ "<br>Current Experience: " + Integer.toString((int)gained + startExp)
				+ "<br>Experience until Level: " + Integer.toString(until)
				+ "<br>Time until Level: " + format((long)((until * 3600000d)/ expPerHr)) 
				+ "</div></html>"
				);
	}
	
	//FORMAT METHOD AUTHORED BY TIMER
	private String format(final long time) {
		final StringBuilder t = new StringBuilder();
		final long total_secs = time / 1000;
		final long total_mins = total_secs / 60;
		final long total_hrs = total_mins / 60;
		final long total_days = total_hrs / 24;
		final int secs = (int) total_secs % 60;
		final int mins = (int) total_mins % 60;
		final int hrs = (int) total_hrs % 24;
		final int days = (int) total_days;
		if (days > 0) {
			if (days < 10) {
				t.append("0");
			}
			t.append(days);
			t.append(":");
		}
		if (hrs < 10) {
			t.append("0");
		}
		t.append(hrs);
		t.append(":");
		if (mins < 10) {
			t.append("0");
		}
		t.append(mins);
		t.append(":");
		if (secs < 10) {
			t.append("0");
		}
		t.append(secs);
		return t.toString();
	}
}
