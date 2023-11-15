import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class TableForm {

	JLabel l_time = new JLabel();
	JFrame frame = new JFrame();
	int[][] allHistoryData;
	String[] timeGraphics;
	String[] allTimeGraphics;
	Materials materials;
	int[][] historyData = new int[3][18];
	Table modelTable;
	int kol;
	Mesage mes;
	JLabel l_mes2;
	public TableForm(int[][] historyData, String[]timeGraphics,Materials materials, Mesage mes)
	{
		this.mes = mes;
		this.materials = materials;
		this.timeGraphics = timeGraphics;
		this.historyData = historyData;
		frame.setLocation(10,10);
    	frame.setSize(1340, 720);
    	frame.setLayout(null);
    	frame.setLayout(new GridBagLayout());
    	allHistoryData = materials.getAllHistoryData();;
		allTimeGraphics = materials.getAllTime();
		kol = materials.getKol();
    	addElements(frame);
    	
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    	Timer myTimer;
    	myTimer = new Timer();

    	myTimer.schedule(new TimerTask() {
    		public void run() {
    			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    			l_time.setText("");
    			l_time.setText(timeStamp);
    			allHistoryData = materials.getAllHistoryData();
    			allTimeGraphics = materials.getAllTime();
    			kol = materials.getKol();
    			
    			addNewData();
    			
    			
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
	
		
	public void addElements(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
		
        JMenuItem schemeMenu = new JMenuItem("Схема управления");
		menuBar.add(schemeMenu);
		schemeMenu.addActionListener(new Listener1());
		
		JMenuItem graphMenu = new JMenuItem("График");
		menuBar.add(graphMenu);
		graphMenu.addActionListener(new Listener2());
		
		JMenuItem tableMenu = new JMenuItem("Таблицы");
		tableMenu.setBackground(new Color(190, 189, 255));
		menuBar.add(tableMenu);
		//tableMenu.addActionListener(new Listener3());
		
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
			
		  JLabel l_name = new JLabel("                                                       Продукция А                                                 Продукция Б                                             Продукция С");
		  l_name.setSize(270,50);
		  l_name.setLocation(580, 0);
		  l_name.setBackground(new Color(213, 235, 232));
		  l_name.setOpaque(true);
		  l_name.setFont(new Font("Serif", Font.PLAIN, 18));
		  frame.add(l_name);
			
		  l_time.setSize(370,30);
		  l_time.setLocation(500, 50);
		  l_time.setFont(new Font("Serif", Font.PLAIN, 30));
		  frame.add(l_time);
		  
		  JScrollPane scroll = new JScrollPane(l_mes);
		  JScrollPane scroll3 = new JScrollPane(l_mes2);
		  JScrollPane scroll2 = new JScrollPane(l_time);
		  JScrollPane scroll4 = new JScrollPane(l_name);
		
		  
		  scroll3.setPreferredSize(new Dimension(1300,150));
		  scroll3.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		        public void adjustmentValueChanged(AdjustmentEvent e) {  
		            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		        }
		    });
		  frame.add(scroll2, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		  
		  createTable();
		  frame.add(scroll, new GridBagConstraints(0,3,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		  frame.add(scroll3, new GridBagConstraints(0,4,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		  frame.add(scroll4, new GridBagConstraints(0,1,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1,1,1,1), 0,0));
	
		    for(int i =0; i<kol; i++)
			{
			String []str = new String[8];
			str[0] = allTimeGraphics[i];
			str[1] = Integer.toString(allHistoryData[0][i]);
			str[2] = "100";
			str[3] = Integer.toString(allHistoryData[1][i]);
			str[4] = "150";
			str[5] = Integer.toString(allHistoryData[2][i]);
			str[6] = "200";
			str[7] = Integer.toString(allHistoryData[0][i]*100+allHistoryData[1][i]*150+allHistoryData[2][i]*200);
			 modelTable.addDate(str);
			 modelTable.fireTableDataChanged();
			
			}
    }
	
	public void closeForm()
	{
		frame.setVisible(false);
	}
	public void openForm()
	{
		frame.setVisible(true);
	}
	
	public void addNewData()
	{
						
		String []str = new String[8];
		str[0] = allTimeGraphics[kol];
		str[1] = Integer.toString(allHistoryData[0][kol]);
		str[2] = "100";
		str[3] = Integer.toString(allHistoryData[1][kol]);
		str[4] = "150";
		str[5] = Integer.toString(allHistoryData[2][kol]);
		str[6] = "200";
		str[7] = Integer.toString(allHistoryData[0][kol]*100+allHistoryData[1][kol]*150+allHistoryData[2][kol]*200);
		 modelTable.addDate(str);
		 modelTable.fireTableDataChanged();
					
	}
	
	public void  createTable()
	{
		
		modelTable = new Table();
		JTable table = new JTable(modelTable);
		JScrollPane scroll = new JScrollPane(table);
		
		scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		scroll.setPreferredSize(new Dimension(1300,400));
		
		scroll.setMinimumSize(new Dimension(1000,200));
		//scroll.getHorizontalScrollBar().setValue(100);
		
		
		frame.add(scroll, new GridBagConstraints(0,2,1,1,1,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
		  
		
		
		
		
		//frame.add(scroll);
		
		/*JLabel l_time = new JLabel("Время");
		l_time.setSize(163,35);
		l_time.setLocation(10, 115);
		l_time.setFont(new Font("Serif", Font.PLAIN, 18));
		l_time.setBackground(new Color(240, 252, 251)); 
		l_time.setOpaque(true); 
		l_time.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_time);
		
		JLabel l_kolA = new JLabel("Количество, шт");
		l_kolA.setSize(163,35);
		l_kolA.setLocation(172, 115);
		l_kolA.setFont(new Font("Serif", Font.PLAIN, 18));
		l_kolA.setBackground(new Color(240, 252, 251)); 
		l_kolA.setOpaque(true);
		l_kolA.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_kolA);
		
		JLabel l_ctA = new JLabel("Стоимость, руб.");
		l_ctA.setSize(164,35);
		l_ctA.setLocation(334, 115);
		l_ctA.setFont(new Font("Serif", Font.PLAIN, 18));
		l_ctA.setBackground(new Color(240, 252, 251)); 
		l_ctA.setOpaque(true);
		l_ctA.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_ctA);
		
		JLabel l_kolB = new JLabel("Количество, шт");
		l_kolB.setSize(163,35);
		l_kolB.setLocation(497, 115);
		l_kolB.setFont(new Font("Serif", Font.PLAIN, 18));
		l_kolB.setBackground(new Color(240, 252, 251)); 
		l_kolB.setOpaque(true);
		l_kolB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_kolB);
		
		JLabel l_ctB = new JLabel("Стоимость, руб.");
		l_ctB.setSize(164,35);
		l_ctB.setLocation(659, 115);
		l_ctB.setFont(new Font("Serif", Font.PLAIN, 18));
		l_ctB.setBackground(new Color(240, 252, 251)); 
		l_ctB.setOpaque(true);
		l_ctB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_ctB);
		
		JLabel l_kolC = new JLabel("Количество, шт");
		l_kolC.setSize(164,35);
		l_kolC.setLocation(821, 115);
		l_kolC.setFont(new Font("Serif", Font.PLAIN, 18));
		l_kolC.setBackground(new Color(240, 252, 251)); 
		l_kolC.setOpaque(true);
		l_kolC.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_kolC);
		
		JLabel l_ctC = new JLabel("Стоимость, руб.");
		l_ctC.setSize(164,35);
		l_ctC.setLocation(984, 115);
		l_ctC.setFont(new Font("Serif", Font.PLAIN, 18));
		l_ctC.setBackground(new Color(240, 252, 251)); 
		l_ctC.setOpaque(true);
		l_ctC.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_ctC);
		
		JLabel l_sum = new JLabel("Сумма");
		l_sum.setSize(163,35);
		l_sum.setLocation(1147, 115);
		l_sum.setFont(new Font("Serif", Font.PLAIN, 18));
		l_sum.setBackground(new Color(240, 252, 251)); 
		l_sum.setOpaque(true); 
		l_sum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_sum);
		
		JLabel l_A = new JLabel("                Продукция A");
		l_A.setSize(326,37);
		l_A.setLocation(172, 82);
		l_A.setFont(new Font("Serif", Font.PLAIN, 22));
		l_A.setBackground(new Color(213, 235, 232)); 
		l_A.setOpaque(true);
		l_A.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_A);
		
		JLabel l_B = new JLabel("                Продукция B");
		l_B.setSize(326,37);
		l_B.setLocation(497, 82);
		l_B.setFont(new Font("Serif", Font.PLAIN, 22));
		l_B.setBackground(new Color(213, 235, 232)); 
		l_B.setOpaque(true); 
		l_B.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_B);
		
		JLabel l_C = new JLabel("                Продукция C");
		l_C.setSize(326,37);
		l_C.setLocation(822, 82);
		l_C.setFont(new Font("Serif", Font.PLAIN, 22));
		l_C.setBackground(new Color(213, 235, 232)); 
		l_C.setOpaque(true);
		l_C.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		frame.add(l_C);
		  
	  JTable table = new JTable(10, 8);
     
      table.setLocation(10, 150); 
      table.setSize(1300,350);
      frame.add(table);*/
      
      
	}
	
	class Listener1 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    	    //new MainForm();
    		frame.dispose();
        }
    }
	public class Listener2 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
		  
		  new GraphicsForm(historyData, timeGraphics, materials, mes);
 		  frame.dispose();
		
	    }

    }
	/*public class Listener3 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			//new TableForm();
 		  frame.dispose();
		
	    }

    }*/
}
