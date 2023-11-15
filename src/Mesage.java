
public class Mesage
{
    String[] mes;
	int kolMes=0;
   
	public Mesage()
   {
		mes = new String[1000];
   }
	
	public void addData(String data)
	{
		mes[kolMes] = data;
		kolMes++;
		//System.out.println(data);
	}
	
	public String[] getData()
	{
		return mes;
	}
	public int getKol()
	{
		return kolMes;
	}
}
