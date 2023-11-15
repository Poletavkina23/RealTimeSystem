import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ViewMaterials extends JPanel

{
	Materials materials;
	int A,B,C;
	int Y;
	Temperature temperature;
	public ViewMaterials(Materials materials, Temperature temperature)
    {
    	this.materials= materials;
    	this.temperature = temperature;
    }
    
    @Override
	public void paintComponent (Graphics g)
	{
		Graphics2D g2 =  (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,1300,700);
		g2.setColor(Color.BLACK);
		g2.drawRect(70,80,50,290);
		g2.drawRect(180,80,50,290);
		g2.drawRect(290,80,50,290);
		
		g2.drawRect(530,110,30,240); // градусник
		
		
		g2.setFont(new Font("Serif", Font.BOLD, 18));
		g2.drawString("Количество коробок: ", 55, 48);
		
		g2.setFont(new Font("Serif", Font.BOLD, 14));
		g2.drawString("Продукция А", 55, 65);
		g2.drawString("Продукция Б", 165, 65);
		g2.drawString("Продукция B", 275, 65);
		g2.drawString("Температура", 500, 90);
		g2.drawString("на складе, °C", 510, 105);
		
		g2.setFont(new Font("Serif", Font.BOLD, 30));
		g2.drawString("Остатки на складе", 100, 25);
		
		
		g2.setColor(new Color(93,176,255));
		
		A = materials.getA();
		B = materials.getB();
		C = materials.getC();
		for(int i =0; i<A; i++) // A
		{
		   g2.fillRect(72, 353-i*16, 47, 15);
		}
		
		for(int i =0; i<B; i++) // B
		{
		   g2.fillRect(182, 353-i*16, 47, 15);
		}
		
		for(int i =0; i<C; i++) // C
		{
		   g2.fillRect(292, 353-i*16, 47, 15);
		}
		
		g2.setColor(Color.red);
		g2.fillRect(532, 112, 27, 30);
		g2.setColor(Color.yellow);
		g2.fillRect(532, 144, 27, 70);
		g2.setColor(Color.green);
		g2.fillRect(532, 216, 27, 133);
		
		g2.setFont(new Font("Serif", Font.PLAIN, 12));
		g2.setColor(Color.black);
		g2.drawString("Высока", 570,120);
		g2.drawString("вероятность", 570,130);
		g2.drawString("пожара", 570,140);
		g2.drawString("Неблагоприятные", 570,170);
		g2.drawString("условия", 570,180);
		g2.drawString("хранения", 570,190);
		g2.drawString("(брак)", 570,200);
		g2.drawString("Оптимальные", 570,240);
		g2.drawString("условия", 570,250);
		g2.drawString("хранения", 570,260);
		
		g2.setColor(Color.gray);
		Y = temperature.getTemp(); // высота треугольника 300
		int[]x = new int[3];
		x[0] = 500;
		x[1] = 480;
		x[2] = 480;
		int[]y = new int[3];
		y[0] = Y;
		y[1] = Y-15;
		y[2] = Y+15;
		g2.fillPolygon(x, y, 3);
		
		
		g2.setColor(Color.black);
		int t=0;
		for(int i =335; i>110; i = i - 15) // A
		{
		   g2.drawLine(525,i,535,i);
		   g2.drawString(Integer.toString(15+t), 510,i+5);
		   t++;
		}
		
		g2.setFont(new Font("Serif", Font.BOLD, 16));
		double temperatura = 14;
		
		for(double myY=350; myY >= Y; myY = myY - 7.5)
		{
			temperatura=temperatura+0.5;
		}
		g2.drawString("Температура = " +  (temperatura) +  "  °C", 450, 380);
	}
    
}
