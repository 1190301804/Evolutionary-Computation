package TSP_EA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * TSP数据类
 */

public class TSPData {

    public static int CITY_NUM;                 //城市数
    public static  int SPECIES_NUM=10;     //种群数
    public static  int DEVELOP_NUM=20000;  //进化代数
    public static final float pcl=0.6f;         //交叉概率
	public static final float pch=0.95f;
    public static final float pm=0.4f;          //变异概率
    public static float[][] disMap;             //地图数据
    public static int[][] cityPosition;


    /**
     * 读取文件载入数据到cityPosition
     *
     * @throws IOException
     */
    static void loadData() throws IOException
	{
		loadData("test//eil51.tsp");
	}

	/**
	 * 根据文件名读取文件内容，载入cityPosition中
	 * @param fileName  文件路径
	 * @throws IOException	文件不存在，抛出异常
	 */
    static void loadData(String fileName) throws IOException  {
		//读取不同文件
//		File file = new File("test//eil51.tsp");
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader bfr = new BufferedReader(fr);
		String line = null;
		int cnt = 0;
		while((line = bfr.readLine()) != null) {
			cnt++;
			//System.out.println(line);
		}
		//System.out.println(cnt);
		cityPosition = new int[cnt-7][2];
		//System.out.println(cityPosition.length);
		bfr.close();
		fr.close();

		FileReader fr1 = new FileReader(file);
		BufferedReader bfr1 = new BufferedReader(fr1);
		String line1 = null;
		int cnt1 = 0;
		int idx = 0;
		while((line1 = bfr1.readLine()) != null) {
			cnt1++;
			if(cnt1 > 6 && cnt1 < cnt) {
				String[] lines = line1.split(" ");
				if(lines[1].indexOf("e") != -1) {
					BigDecimal bd = new BigDecimal(lines[1]);
					String str = bd.toPlainString();
					cityPosition[idx][0] = (int)Float.parseFloat(str);
				}else
					cityPosition[idx][0] = Integer.valueOf(lines[1]);
				if(lines[2].indexOf("e") != -1) {
					BigDecimal bd = new BigDecimal(lines[2]);
					String str = bd.toPlainString();
					cityPosition[idx][1] = (int)Float.parseFloat(str);
				}else
					cityPosition[idx][1] = Integer.valueOf(lines[2]);
				idx++;
			}
			//System.out.println(line1);
		}

//		for(int[] tmp:cityPosition) {
//			System.out.print(tmp[0]+" ");
//			System.out.println(tmp[1]);
//		}
		bfr1.close();
		fr1.close();

		//路径集合
		CITY_NUM=cityPosition.length;
		disMap=new float[CITY_NUM][CITY_NUM];
		for(int i=0;i<CITY_NUM;i++)
		{
			for(int j=i;j<CITY_NUM;j++)
			{
				float dis=(float)Math.sqrt(Math.pow((cityPosition[i][0] - cityPosition[j][0]),2) + Math.pow((cityPosition[i][1] - cityPosition[j][1]),2));

				disMap[i][j]=dis;
				disMap[j][i]=disMap[i][j];
			}
		}
	}

}