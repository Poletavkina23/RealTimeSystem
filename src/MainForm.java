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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainForm 
{
	JLabel l_time = new JLabel();
	JFrame frame = new JFrame();
	int CostOne,CostTwo,CostThree, CostFour,TotalCost = 0;
	Materials materials;
	ViewMaterials viewMaterials;
	int[][] historyData;
	JLabel l_mes2;
	JLabel labelBenefit, labelBenefitA, labelBenefitB, labelBenefitC;
    String[]timeGraphics;
	boolean avto = false;
	boolean fire = false;
	GraphicsForm graphicsForm;
	TableForm tableForm;
    int kol = 0;
    Mesage mes;
    JLabel labelCostOne;
    JLabel labelTotalCost;
    JLabel labelCostThree;
    JLabel labelCostFour;
    JCheckBox checkBox;
    JLabel labelCostTwo;
    Temperature temperature;
    int temp;
    int j = 0;
    int jj = 0;
    int jjj=0;
    int jjjj=0;
    int benefitA, benefitB, benefitC = 0;
    boolean fireHelp = false;
	public MainForm()
	{
		/*for(int i =0; i<18; i++)
		{
			timeGraphics[i] = "HH:mm:ss";
		}*/
		temperature = new Temperature();
		mes = new Mesage();
		materials = new Materials(mes);
		frame.setLocation(10,10);
    	frame.setSize(1330, 720);
    	frame.setLayout(null);
     	frame.setLayout(new GridBagLayout());
    	addElements(frame);
    	frame.setTitle("Полетавкина А.С. ПИ-424Б");
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	Timer myTimer;
    	myTimer = new Timer();

    	myTimer.schedule(new TimerTask() {
    		public void run() {
    			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    			
    			l_time.setText("");
    			l_time.setText(timeStamp);
    			if (checkBox.isSelected()) avto = true;
    			else avto = false;
    			if (avto) checkMaterials();
    			historyData = materials.getHistoryData();
    			timeGraphics = materials.getTime();
    			kol = materials.getKol();
    			CostTwo = materials.getShtraf();
    			CostFour = CostFour + 100;
    			benefitA = materials.getBenefitA();
    			benefitB = materials.getBenefitB();
    			benefitC = materials.getBenefitC();
    			labelBenefitA.setText(benefitA + " руб. - От реализации продукции А");
    			labelBenefitB.setText(benefitB + " руб. - От реализации продукции Б");
    			labelBenefitC.setText(benefitC + " руб. - От реализации продукции В");
    			labelCostTwo.setText((CostTwo + " руб. - Штрафы"));
    			TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
    			labelCostOne.setText(CostOne + " руб. - Закупка продукции");
    			labelTotalCost.setText("Итого: " + TotalCost + " руб.");
    			
    			labelCostFour.setText(CostFour + " руб. - Управленческие расходы");
    			checkTempetarure();
    			
    			viewMaterials.repaint();
    			
    			
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
	
	public void checkTempetarure()
	{
		//122-143 - красный
		//144-215 - желтый
		//216-400 -зеленый
		temp = temperature.getTemp();
		if (avto) 
		{
			if (temp < 220) 
			{
			  temperature.setTemp(330);
			  mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Температура понижена!");
			}
		}
		else
		{
			if(temp > 144)jjj = 0;
			if(temp > 144 && temp < 215)
			{
				
				materials.setCanShtraf(true);
				//mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Температура не соответствует нормам хранения!");
				int minus = materials.getA()*100+materials.getB()*150+materials.getC()*200;
				if(minus != 0)
				{
				mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Температура не соответствует нормам хранения!");
				mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Испорчено продукции на сумму " + minus + " руб.");
				materials.setCanShtraf(false);
				materials.setA(0);
				materials.setB(0);
				materials.setC(0);
				//materials.setShtraf(minus);
				CostThree = CostThree + minus;
				TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
				labelCostThree.setText(CostThree + " руб. - Устранение аварий");
				mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Необхоимо срочно пополнить запасы");
				}
				
				
    	    		j++;
    	    		
       				if (j==3)
       				{
       					materials.setCanShtraf(true);
       				}
    	    		if (j==4) j=0;	
    	    		 
    	    	
    			
			}
			
			if(/*temp > 122 &&*/temp < 143)
			{
				
				materials.setCanShtraf(false);
				jj++;
				if(jjj==0) mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Температура слишком высокая, пожар!");
				jjj++;
				fire = true;
				//mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " jj = " + jj);
				if(jj == 10 && fireHelp == false)
				{
					mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + "Cклад сгорел! Компания понесла большие убытки");
					jj = 0;
					CostThree = CostThree + 5000;
					TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
					labelCostThree.setText(CostThree + " руб. - Устранение аварий");
					labelTotalCost.setText("Итого: " + TotalCost + " руб.");
					//temperature.setTemp(200);
					fire = false;
					jjj=0;
					
				}
				
				// добавить штраф отложенный
			}
		}
		
	}
	
	public void checkMaterials()
	{
		if(materials.getA() < 3) addMaterials("A");
		
		if(materials.getB() < 3) addMaterials("B");
		
		if(materials.getC() < 3) addMaterials("C");
		
	}
	
	public void addMaterials(String x)
	{
	if(temp < 200 && avto == false)
	{
		mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Понизьте температуру во избежании порчи новой партии товара");
	}
	else {
		
		if(x.equals("A"))
		{
			materials.addMaterials("A");
			mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Закупили продукцию А");
			//String[] allMes = mes.getData();
			//String s="<html>";
			/*for(int i =0; i < mes.getKol(); i ++)
			{
				s = s + "<br>" + allMes[i];
			}
			l_mes2.setText(s);*/
			
			CostOne = CostOne + materials.getCost();
			TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
			labelCostOne.setText(CostOne + " руб. - Закупка продукции");
			labelTotalCost.setText("Итого: " + TotalCost + " руб.");
		}
		
		if(x.equals("B"))
		{
			materials.addMaterials("B");
			mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Закупили продукцию Б");
			//String[] allMes = mes.getData();
			//String s="<html>";
			/*for(int i =0; i < mes.getKol(); i ++)
			{
				s = s + "<br>" + allMes[i];
			}
			//l_mes2.setText("<html>" + s+ "<br>Вторая строка</html>");
			l_mes2.setText(s);
			*/
			CostOne = CostOne + materials.getCost();
			TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
			labelCostOne.setText(CostOne + " руб. - Закупка продукции");
			labelTotalCost.setText("Итого: " + TotalCost + " руб. ");
		}
		if(x.equals("C"))
		{
			materials.addMaterials("C");
			mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Закупили продукцию В");
			//String[] allMes = mes.getData();
			//String s="<html>";
			/*for(int i =0; i < mes.getKol(); i ++)
			{
				s = s + "<br>" + allMes[i];
			}
			//l_mes2.setText("<html>" + s+ "<br>Вторая строка</html>");
			l_mes2.setText(s);*/
			
			CostOne = CostOne + materials.getCost();
			TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
			labelCostOne.setText(CostOne + " руб. - Закупка продукции");
			labelTotalCost.setText("Итого: " + TotalCost + " руб.");
		}
	}
	}
		
	public void addElements(JFrame frame)
    {
		JMenuBar menuBar = new JMenuBar();
		
		JMenuItem schemeMenu = new JMenuItem("Система управления остатками на складе");
		schemeMenu.setBackground(new Color(190, 189, 255));
		menuBar.add(schemeMenu);
		//schemeMenu.addActionListener(new Listener1());
		
		JMenuItem graphMenu = new JMenuItem("График");
		menuBar.add(graphMenu);
		graphMenu.addActionListener(new Listener2());
		
		JMenuItem tableMenu = new JMenuItem("Таблицы");
		menuBar.add(tableMenu);
		tableMenu.addActionListener(new Listener3());
					
		frame.setJMenuBar(menuBar);
		
		l_time.setSize(370,30);
		l_time.setLocation(580, 70);
		l_time.setFont(new Font("Serif", Font.PLAIN, 30));
		
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
 		
 		JButton buttonA = new JButton("Закупить А");
		buttonA.setBounds(40,190,240,40);
		buttonA.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonA.addActionListener(new Listener4());
 			
		JButton buttonB = new JButton("Закупить Б");
		buttonB.setBounds(40,240,240,40);
		buttonB.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonB.addActionListener(new Listener5());
		
		JButton buttonC = new JButton("Закупить В");
		buttonC.setBounds(40,290,240,40);
		buttonC.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonC.addActionListener(new Listener6());
		
		JButton buttonT = new JButton("Понизить температуру");
		buttonT.setBounds(340,190,240,40);
		buttonT.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonT.addActionListener(new Listener7());
		
		JButton buttonFire = new JButton("Потушить пожар");
		buttonFire.setBounds(340,240,240,40);
		buttonFire.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonFire.addActionListener(new Listener8());
		
		checkBox = new JCheckBox();
		checkBox.setBounds(40, 350,20,20);
		
		JLabel labelControl = new JLabel("Автоматический контроль остатков продукции и температуры");
		labelControl.setBounds(70,340,550,40);
		labelControl.setFont(new Font("Serif", Font.PLAIN, 18));
		
		JLabel labelCost = new JLabel("Затраты (руб.):");
		labelCost.setBounds(15,00,300,40);
		labelCost.setFont(new Font("Serif", Font.BOLD, 25));
		
		labelBenefit = new JLabel("Прибыль (руб.): ");
		labelBenefit.setBounds(320,00,300,40);
		labelBenefit.setFont(new Font("Serif", Font.BOLD, 25));
		
		
		labelTotalCost = new JLabel("Итого: " + 0 + " руб.");
		labelTotalCost.setBounds(200,150,400,40);
		labelTotalCost.setFont(new Font("Serif", Font.BOLD, 27));
		
		labelCostOne = new JLabel(CostOne + " руб. - Закупка подукции");
		labelCostOne.setBounds(15,30,2020,40);
		labelCostOne.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelCostTwo = new JLabel(CostTwo + " руб. - Штрафы");
		labelCostTwo.setBounds(15,60,200,40);
		labelCostTwo.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelCostThree = new JLabel(CostThree + " руб. - Устранение аварий");
		labelCostThree.setBounds(15,90,250,40);
		labelCostThree.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelCostFour = new JLabel(CostThree + " руб. - Управленческие расходы");
		labelCostFour.setBounds(15,120,300,40);
		labelCostFour.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelBenefitA = new JLabel(benefitA + " руб. - От реализации продукции А");
		labelBenefitA.setBounds(320,30,300,40);
		labelBenefitA.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelBenefitB = new JLabel(benefitB + " руб. - От реализации продукции B");
		labelBenefitB.setBounds(320,60,300,40);
		labelBenefitB.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelBenefitC = new JLabel(benefitC + " руб. - От реализации продукции C");
		labelBenefitC.setBounds(320,90,300,40);
		labelBenefitC.setFont(new Font("Serif", Font.PLAIN, 16));
 		
 		/*ViewMaterials */viewMaterials = new ViewMaterials(materials, temperature);

 		JPanel controlPanel = new JPanel(null);
 		controlPanel.add(buttonA);
 		controlPanel.add(buttonB);
 		controlPanel.add(buttonC);
 		controlPanel.add(buttonT);
 		controlPanel.add(buttonFire);
 		controlPanel.add(checkBox);
 		controlPanel.add(labelControl);
 		controlPanel.add(labelCost);
 		controlPanel.add(labelCostOne);
 		controlPanel.add(labelCostTwo);
 		controlPanel.add(labelCostThree);
 		controlPanel.add(labelCostFour);
 		controlPanel.add(labelTotalCost);
 		controlPanel.add(labelBenefit);
 		controlPanel.add(labelBenefitA);
 		controlPanel.add(labelBenefitB);
 		controlPanel.add(labelBenefitC);
 		
 		JScrollPane scroll = new JScrollPane(l_mes);
		JScrollPane scroll2 = new JScrollPane(l_mes2);
		scroll2.setPreferredSize(new Dimension(1300,150));
		JScrollPane scroll3 = new JScrollPane(l_time);
		scroll3.setPreferredSize(new Dimension(300,45));
		JScrollPane scroll4 = new JScrollPane(viewMaterials);
		scroll4.setPreferredSize(new Dimension(250,400));
		JScrollPane scroll5 = new JScrollPane(controlPanel);
		scroll5.setPreferredSize(new Dimension(200,400));
		
		
		scroll2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		 frame.add(scroll, new GridBagConstraints(0,2,2,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		 frame.add(scroll2, new GridBagConstraints(0,3,2,1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
		 frame.add(scroll3, new GridBagConstraints(0,0,2,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
		 frame.add(scroll4, new GridBagConstraints(0,1,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
		 frame.add(scroll5, new GridBagConstraints(1,1,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0,0));
 				
    }

/*class Listener1 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    	    //new MainForm();
    		//frame.dispose();
        }
    }*/
	public class Listener2 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			   //graphicsForm = new GraphicsForm(historyData, timeGraphics);
			graphicsForm = new GraphicsForm(historyData, timeGraphics, materials, mes); 
	    }

    }
	public class Listener3 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			
				//tableForm = new TableForm(materials);
			tableForm = new TableForm(historyData, timeGraphics, materials, mes);
			
	    }

    }
	class Listener4 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		addMaterials("A");
        }
    }
	class Listener5 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		addMaterials("B");
        }
    }
	class Listener6 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		addMaterials("C");
        }
    }
	
	class Listener7 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		if(temp > 143)
    		{
    		temperature.setTemp(330);
			  mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Температура понижена!");
			  viewMaterials.repaint();
  			
  			
  			String[] allMes = mes.getData();
  			String s="<html>";
  			for(int i =0; i < mes.getKol(); i ++)
  			{
  				s = s + "<br>" + allMes[i];
  			}
  			l_mes2.setText(s);
    		}
        }
    }
	class Listener8 implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		if(fire) 
    		{
    			fireHelp = true;
    			jjjj=0;
    			Timer myTimer;
    	    	myTimer = new Timer();
    			myTimer.schedule(new TimerTask() {
    	    		public void run() {
    	    			
    	    		jjjj++;
    	    		if(jjjj==1)
       					 mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Бригада выехала");
       				if(jjjj==2)
       				{
      					 
      	    			  mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Тушение");
      	    			 
      				}
       				if (jjjj==3)
       				{
       					jj=0;
       					jjj=0;
       					temperature.setTemp(330);
       	    			  mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Огонь потушен!");
       	    			  viewMaterials.repaint();
       	    			fire = false;
       	    			fireHelp = false;
       	    			CostThree = CostThree + 1000;
    					TotalCost = -CostOne - CostTwo - CostThree - CostFour + benefitA + benefitB + benefitC;
    					labelCostThree.setText(CostThree + " руб. - Устранение аварий");
    					labelTotalCost.setText("Итого: " + TotalCost + " руб.");
       	    			
       				}
       				if(jjjj==6) materials.setCanShtraf(true);
    	    			String[] allMes = mes.getData();
    	      			String s="<html>";
    	      			for(int i =0; i < mes.getKol(); i ++)
    	      			{
    	      				s = s + "<br>" + allMes[i];
    	      			}
    	      			l_mes2.setText(s);
    	    		if (jjjj==7) myTimer.cancel();	
    	    		 }
    	    	}, 0, 1000); // каждые 5 секунд
    			
    			
    		
    		}
    		else
    		{
    			
  			   if(temp > 143)mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Штраф за ложный вызов!");
  			   temperature.setTemp(330);   			 
  			   if(temp > 143)materials.setShtraf(500);
    	
    			String[] allMes = mes.getData();
    			String s="<html>";
    			for(int i =0; i < mes.getKol(); i ++)
    			{
    				s = s + "<br>" + allMes[i];
    			}
    			l_mes2.setText(s);
    			viewMaterials.repaint();
    		}
    		fire = false;
        }
    }
	
}
