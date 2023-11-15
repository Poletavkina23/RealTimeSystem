import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Materials
{
   int material_A=18, material_B=18, material_C = 18;
   int cost = 0;
   int[][] historyData = new int[3][18];
   int[][] allHistoryData = new int[3][1000];
   int kol = 0;
   String[] timeGraphics = new String[18];
   String[] allTimeGraphics = new String[1000];
   int shtraf = 0;
   boolean canShtraf = true;
   boolean canShtrafA = true;
   boolean canShtrafB = true;
   boolean canShtrafC = true;
   int benefitA, benefitB, benefitC = 0;
   Mesage mes;
   public Materials(Mesage mes)
   { 
	   this.mes = mes;
	   for(int i =0; i<18; i++)
		{
			timeGraphics[i] = "HH:mm:ss";
		}
	  
	 
	   Random rnd = new Random();  
	 Timer myTimer;
   	 myTimer = new Timer();
   	timeGraphics[0]= new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
   	allTimeGraphics[0] = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
   	historyData[0][0] = material_A;
   	historyData[1][0] = material_B;
   	historyData[2][0] = material_C;
   	allHistoryData[0][0] = material_A;
   	allHistoryData[1][0] = material_B;
   	allHistoryData[2][0] = material_C;
   	   	myTimer.schedule(new TimerTask()
   	{
   		public void run()
   		{
   			int rashod = rnd.nextInt(3);
   			if (material_A >= rashod) 
   			{
   				material_A = material_A - rashod;
   				benefitA = benefitA + rashod*200;
   			}
   			else 
   			{
   				if(canShtraf)
   				{
   					if (canShtrafA)
   						{
   						   shtraf = shtraf + 500;
   						   mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Начислен штраф за отсутствие материала А на складе");
   						  canShtrafA = false;
   						}
   	   				
   				}
   				
   			}
   			rashod = rnd.nextInt(3);
   			if (material_B >= rashod) 
   			{
   				material_B = material_B - rashod;
   				benefitB = benefitB + rashod*300;
   			}
   			else 
   			{
   				if(canShtraf)
   				{
   					if (canShtrafB)
					{
   					shtraf = shtraf + 500;
   	   				mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Начислен штраф за отсутствие материала Б на складе");
   	   			    canShtrafB = false;
					}
				}
   				
   			}
   			rashod = rnd.nextInt(3);
   			if (material_C >= rashod)
   			{
   				material_C = material_C - rashod;
   				benefitC = benefitC + rashod*400;
   			}
   			else 
   			{
   				if(canShtraf)
   				{
   					if (canShtrafC)
					{
   					   shtraf = shtraf + 500;
   	   				   mes.addData(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " " + " Начислен штраф за отсутствие материала В на складе");
   	   				   canShtrafC = false;
					}
				}
   				
   			}
   			kol ++;
   			allHistoryData[0][kol] = material_A;
   		   	allHistoryData[1][kol] = material_B;
   		   	allHistoryData[2][kol] = material_C;
   		    allTimeGraphics[kol] = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
   			if(kol < 18) 
   			{
   				historyData[0][kol] = material_A;
   			   	historyData[1][kol] = material_B;
   			   	historyData[2][kol] = material_C;
   			    timeGraphics[kol] = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
   			    
   			}
   			if(kol >= 18) 
   			{
   				
   				for (int i = 0; i <17; i++)
   				{
   					historyData[0][i] = historyData[0][i+1];
   	   			   	historyData[1][i] = historyData[1][i+1];
   	   			   	historyData[2][i] = historyData[2][i+1];
   	   			    timeGraphics[i] = timeGraphics[i+1]; 
   	   			   	
   	   			}
   				
   				historyData[0][17] = material_A;
   			   	historyData[1][17] = material_B;
   			   	historyData[2][17] = material_C;
   			    timeGraphics[17] = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
   			}
   			
   		}
   	}, 0, 1000); // каждые 5 секунд
	  
   }
   
   public void setShtraf(int s)
   {
	   shtraf = shtraf + s;
   }
   
   public void addMaterials(String material)
   {
	   if (material.equals("A"))
	   {
		   cost = (18-material_A)*100;  // 100 - условная цена материала А
		   material_A=18;
		   canShtrafA = true;
	   }
	   if (material.equals("B"))
	   {
		   cost = (18-material_B)*150;  // 150 - условная цена материала B
		   material_B=18;
		   canShtrafB = true;
	   }
	   if (material.equals("C"))
	   {
		   cost = (18-material_C)*200;  // 200 - условная цена материала C
		   material_C=18;
		   canShtrafC = true;
	   }

   }
   public int getBenefitA()
   {
	   return benefitA;
   }
   public int getBenefitB()
   {
	   return benefitB;
   }
   public int getBenefitC()
   {
	   return benefitC;
   }
   public void setCanShtraf(boolean can)
   {
	   canShtraf = can;
   }
   public int[][] getAllHistoryData()
   {
	   return allHistoryData;
   }
   public int[][] getHistoryData()
   {
	   return historyData;
   }
   
   public String[] getTime()
   {
	   return timeGraphics;
   }
   
   public String[] getAllTime()
   {
	   return allTimeGraphics;
   }
   
   public int getShtraf()
   {
	   return shtraf;
   }
   public int getA()
   {
	   return material_A;
   }
   
   public int getB()
   {
	   return material_B;
   }
   
   public int getC()
   {
	   return material_C;
   }
   
   public int getCost()
   {
	   return cost;
   }
   
   public int getKol()
   {
	   return kol;
   }
   public void setA(int a)
   {
	   material_A = a;
   }
   public void setB(int b)
   {
	   material_B = b;
   }
   public void setC(int c)
   {
	   material_C = c;
   }
}
