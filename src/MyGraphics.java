import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JPanel;

public class MyGraphics extends JPanel
{
	Line line;
	Line line2;
	Line line3;
	Line line4;
	Line line5;
	Line line6;
	Line line7;
	Line line8;
	Line line9;
	
	String[] time = new String[20];
	Line[] lines = new Line[20];
	Line[] lines2 = new Line[20];
	
	int[] dataA = new int[18];
	int[] dataB = new int[18];
	int[] dataC = new int[18];
	
	int[][]historyData;
	String[] timeGraphics; 
	
	public MyGraphics(int[][] historyData, String[]timeGraphics)
    {
		this.timeGraphics = timeGraphics;
		
		this.historyData = historyData;
		
		// ось Y
		line = new Line(35,5,20,20);
		line2 = new Line(35,5,50,20);
		line3 = new Line(35,5,35,385);
		// ось X
		line4 = new Line(35,385,1150,385);
		line5 = new Line(1130,370,1150,385);
		line6 = new Line(1130,400,1150,385);
    	// промежуточные деления
			
		for(int i = 0; i<20; i++)
		{
			lines2[i] = new Line(30,385-i*20,40,385-i*20);
		}
		
		for(int i = 0; i<17; i++)
		{
			lines[i] = new Line(95+60*i,380,95+60*i,390);
		}
		    	
		// легенд
		line7 = new Line(1280,115,1230,115);
		line8 = new Line(1280,135,1230,135);
		line9 = new Line(1280,155,1230,155);
		
		createDate();
    }
	
	
	
	@Override
	public void paintComponent (Graphics g)
	{
		Graphics2D g2 =  (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,1300,700);
		g2.setColor(Color.BLACK);
		g2.draw(line);
		g2.draw(line2);
		g2.draw(line3);
		g2.draw(line4);
		g2.draw(line5);
		g2.draw(line6);
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.red);
		g2.draw(line7);
		g2.setColor(Color.blue);
		g2.draw(line8);
		g2.setColor(Color.green);
		g2.draw(line9);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(1));
		
		for(int i = 0; i<19; i++) 
		{
			
			g2.draw(lines2[i]);
			g2.drawString(Integer.toString(i), 10,389-i*20);
			//g2.draw(dataA[i]);
			//g2.draw(dataB[i]);
			//g2.draw(dataC[i]);
		}
		
		//g2.drawString("14:20:12", 70,400);
		//g2.drawString("14:20:13", 130,400);
		
		for(int i = 0; i<17; i++) 
		{
			g2.draw(lines[i]);
			g2.drawString(timeGraphics[i], 70+i*60,400);
		}
		
		g2.setFont(new Font("Serif", Font.BOLD, 14));
		g2.drawString("Х - кол-во коробок, шт", 55,20);
		g2.drawString("Y - время", 1150,370);
		g2.drawString("Продукция А", 1140,120);
		g2.drawString("Продукция Б", 1140,140);
		g2.drawString("Продукция С", 1140,160);
		
				
		for(int i =0; i< 17; i++) // отрисовка графика
		{
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.red);
			Line line_A = new Line(35+i*60, 385 - historyData[0][i]*20 ,95+i*60, 385 - historyData[0][i+1]*20);
			g2.draw(line_A);
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.blue);
			Line line_B = new Line(35+i*60, 385 - historyData[1][i]*20 ,95+i*60, 385 - historyData[1][i+1]*20);
			g2.draw(line_B);
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.green);
			Line line_C = new Line(35+i*60, 385 - historyData[2][i]*20 ,95+i*60, 385 - historyData[2][i+1]*20);
			g2.draw(line_C);
		}
	}
	
	public void createDate()
	{
		Random rnd = new Random();
		
		for(int i =0; i< dataA.length; i++)
		{
			dataA[i] = rnd.nextInt(19);
			dataB[i] = rnd.nextInt(19);
			dataC[i] = rnd.nextInt(19);
		}
	}
	
	
	
}
