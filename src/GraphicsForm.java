import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GraphicsForm {
  
	JLabel l_time = new JLabel();
	JFrame frame = new JFrame();
	int[][] historyData;
	MyGraphics graphics;
	int kol = 0;
	Materials materials;
	String[] timeGraphics;
	Mesage mes;
	JLabel l_mes2;
	public GraphicsForm(int[][] historyData, String[]timeGraphics, Materials materials, Mesage mes)
	{
		this.mes = mes;
		this.materials = materials;
		this.timeGraphics = timeGraphics;
		this.historyData = historyData;
		frame.setLocation(10,10);
    	frame.setSize(1330, 720);
    	frame.setLayout(null);
    	frame.setLayout(new GridBagLayout());
    	graphics = new MyGraphics(historyData, timeGraphics);
    	addElements(frame, graphics);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	Timer myTimer;
    	myTimer = new Timer();

    	myTimer.schedule(new TimerTask() {
    		public void run() {
    			String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
    			l_time.setText("");
    			l_time.setText(timeStamp);
    			//graphics.createTime(kol, new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())); 
    			//kol++;
    			//if(kol == 18) kol = 0;
    			graphics.repaint();
    			
    			String[] allMes = mes.getData();
    			String s="<html>";
    			for(int i =0; i < mes.getKol(); i ++)
    			{
    				s = s + "<br>" + allMes[i];
    			}
    			l_mes2.setText(s);
    			
    		    		}
    	}, 0, 1000); // каждые 5 секунд
	}
	
	public void closeForm()
	{
		frame.setVisible(false);
	}
	public void openForm()
	{
		frame.setVisible(true);
	}
		
	public void addElements(JFrame frame, MyGraphics graphics)
    {
        JMenuBar menuBar = new JMenuBar();
		
        JMenuItem schemeMenu = new JMenuItem("Схема управления");
		menuBar.add(schemeMenu);
		schemeMenu.addActionListener(new Listener1());
		
		JMenuItem graphMenu = new JMenuItem("График");
		graphMenu.setBackground(new Color(190, 189, 255));
		menuBar.add(graphMenu);
		//graphMenu.addActionListener(new Listener2());
		
		JMenuItem tableMenu = new JMenuItem("Таблицы");
		menuBar.add(tableMenu);
		tableMenu.addActionListener(new Listener3());
		
		frame.setJMenuBar(menuBar);
		
		JLabel l_mes = new JLabel("Message:");
		l_mes.setSize(70,30);
		l_mes.setLocation(10, 550);
	 	frame.add(l_mes);
 		
 		l_mes2 = new JLabel("");
		l_mes2.setSize(1300,60);
		l_mes2.setLocation(10, 580);
		l_mes2.setBackground(Color.WHITE);
		l_mes2.setOpaque(true);
 		frame.add(l_mes2);
		
		/*JLabel l_name = new JLabel("График");
		l_name.setSize(270,50);
		l_name.setLocation(580, 0);
		l_name.setFont(new Font("Serif", Font.PLAIN, 30));
	 	frame.add(l_name);*/
		
 		l_time.setSize(370,30);
		l_time.setLocation(580, 70);
		l_time.setFont(new Font("Serif", Font.PLAIN, 30));
		//frame.add(l_time);
		
		JScrollPane scroll = new JScrollPane(l_time);
		//scroll.setPreferredSize(new Dimension(1300,400));
		frame.add(scroll, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		 
		//////////////////////////////////////////////////////////////////////
		
		//MyGraphics graphics = new MyGraphics(historyData);
	
		
	    //frame.setContentPane(graphics);
		JScrollPane scroll2 = new JScrollPane(graphics);
		//scroll2.setPreferredSize(new Dimension(1300,400));
		frame.add(scroll2, new GridBagConstraints(0,1,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
		
		JScrollPane scroll3 = new JScrollPane(l_mes);
		scroll2.setPreferredSize(new Dimension(1300,400));
		frame.add(scroll3, new GridBagConstraints(0,2,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		
		JScrollPane scroll4 = new JScrollPane(l_mes2);
		scroll4.setPreferredSize(new Dimension(1300,150));
		frame.add(scroll4, new GridBagConstraints(0,3,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
		
		scroll4.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		

    }
	
	class Listener1 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    	    //new MainForm();
    		frame.dispose();
        }
    }
	/*public class Listener2 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
		  
		  //new GraphicsForm();
 		  frame.dispose();
		
	    }

    }*/
	public class Listener3 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			new TableForm(historyData, timeGraphics, materials, mes);
			frame.dispose();
		
	    }

    }
	
}
