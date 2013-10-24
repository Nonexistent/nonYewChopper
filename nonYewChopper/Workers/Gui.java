package nonYewChopper.Workers;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import nonYewChopper.Box;
import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.PowerE;
import nonYewChopper.Utilites.TesterInter;
import nonYewChopper.Utilites.VarsE;
import org.powerbot.script.methods.MethodContext;

public class Gui {
	JFrame frame = new JFrame("NonYew Cutter by Nonexistent");
	JTabbedPane tab = new JTabbedPane();
	MethodContext ctx;
	EnumInter choice;
	private YewChop mainClass;
	Box container;
	TesterInter tester;

	public Gui(MethodContext ctx, YewChop c) {
		this.ctx = ctx;
		this.mainClass = c;
	}

	public void go() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		buttons();
		powerChop();
		frame.getContentPane().add(tab);
		frame.setSize(300, 290);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	public void buttons() {
		JPanel button = new JPanel(new GridLayout(0, 1));
		JPanel choicePanel = new JPanel(new FlowLayout());
		choicePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		button.add(new JButton(new AbstractAction("Draynor Village") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choice = VarsE.Draynor;
				tester = new YewTester(ctx, choice);
				new Box.Builder(2, ctx, choice).bankTileWalker().banker().treeTileWalker().chopper().login().yewSearcher(4).build();
				frame.dispose();
				completed();
			}
		}));
		button.add(new JButton(new AbstractAction("Varrock Palace") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choice = VarsE.VarP;
				tester = new YewTester(ctx, choice);
				new Box.Builder(2, ctx, choice).bankAreaWalker().banker().treeAreaWalker().chopper().login().yewSearcher(3).build();
				frame.dispose();
				completed();
			}
		}));
		button.add(new JButton(new AbstractAction("Edgeville") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choice = VarsE.Edge;
				tester = new YewTester(ctx, choice);
				new Box.Builder(2, ctx, choice).bankAreaWalker().banker().treeAreaWalker().chopper().login().yewSearcher(2).build();
				frame.dispose();
				completed();
			}
		}));
		button.add(new JButton(new AbstractAction("Falador South") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choice = VarsE.Fala;
				tester = new YewTester(ctx, choice);
				new Box.Builder(2, ctx, choice).bankTileWalker().banker().treeTileWalker().chopper().login().yewSearcher(3).build();
				frame.dispose();
				completed();
			}
		}));
		button.getComponent(0).setEnabled(false);
		choicePanel.add(button);
		choicePanel.add(infoSec());
		tab.addTab("Bank Yews", choicePanel);
	}

	@SuppressWarnings("serial")
	public void powerChop() {
		JPanel out = new JPanel(new FlowLayout());
		JPanel in = new JPanel(new GridLayout(0, 1));
		JPanel radiop = new JPanel(new FlowLayout()), 
				top = new JPanel(new FlowLayout()),
				bottom = new JPanel(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		final JRadioButton drop = new JRadioButton("Drop"), bonfire = new JRadioButton("Bonfire");
		in.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		final JComboBox<PowerE> box = new JComboBox<PowerE>(PowerE.values());
		group.add(drop); group.add(bonfire); group.setSelected(drop.getModel(), true);
		radiop.add(drop); radiop.add(bonfire);
		top.add(box);
		in.add(top);
		in.add(radiop);
		bottom.add(new JButton(new AbstractAction("Start!") {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = (PowerE) box.getSelectedItem();
				PowerE i = (PowerE) choice;
				tester = new PowerTester(ctx, choice);
				if(drop.isSelected()){
					new Box.Builder(1, ctx, i).dropper(i.getLogId()).chopper().yewSearcher(0).login().build();
				}else if(bonfire.isSelected()){
				    new Box.Builder(1, ctx, i).bonfire(i.getLogId()).chopper().yewSearcher(0).login().build();
				}
				frame.dispose();
				completed();
			}
		}));
		in.add(bottom);
		out.add(in);
		out.add(powerInfo());
		tab.addTab("PowerChop", out);
	}

	public Component infoSec() {
		JPanel infoPanel = new JPanel();
		JPanel sec = new JPanel();
		infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		TitledBorder border = BorderFactory
				.createTitledBorder("Announcements:");
		sec.setBorder(border);
		sec.add(new JLabel(
				"<html><b>Updated on: October 22nd 2013<br>Script Version: 2.84<br><br>Feel free to post in the script's<br>thread in the woodcutting section!</b></html>"));
		infoPanel.add(sec);
		return infoPanel;
	}

	public Component powerInfo() {
		JPanel infoPanel = new JPanel();
		JPanel sec = new JPanel();
		infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		TitledBorder border = BorderFactory.createTitledBorder("Note" + ":");
		sec.setBorder(border);
		sec.add(new JLabel(
				"<html><b>Maple is currently not usable.</b></html>"));
		infoPanel.add(sec);
		return infoPanel;
	}

	private void completed() {
		this.mainClass.deleteGui(choice.getExpAmount(), tester);
	}

}
