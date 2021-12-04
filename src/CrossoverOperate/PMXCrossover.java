package CrossoverOperate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;

/**
 * PMX Crossover
 */

public class PMXCrossover implements CrossoverOperator{

	@Override
	public void crossover(Population list) {
    	//pcl~pch的概率
        float rate=(float)Math.random();
        if(rate > TSPData.pcl && rate < TSPData.pch)
        {
			Random rand=new Random();
        	Individual point=list.head.next;//游标
            int find=rand.nextInt(list.speciesNum);
            while(point != null && find != 0)//寻找表尾结点
            {
                point=point.next;
                find--;
            }

            if(point.next != null)
            {
            	int index2 = rand.nextInt(TSPData.CITY_NUM-1)+1;
            	int index1 = rand.nextInt(index2);
            	String[] o1 = new String[TSPData.CITY_NUM];
            	String[] o2 = new String[TSPData.CITY_NUM];
            	Map<String, String> map1 = new HashMap<String, String>();
            	Map<String, String> map2 = new HashMap<String, String>();
            	for(int i = index1; i <= index2; i++) {
            		o1[i] = point.next.genes[i];
            		o2[i] = point.genes[i];
            		map1.put(point.next.genes[i], point.genes[i]);
            		map2.put(point.genes[i], point.next.genes[i]);
            	}
            	
            	for(int i = 0; i < TSPData.CITY_NUM; i++) {
            		if(i < index1 || i > index2) {
            			if(map1.containsKey(point.genes[i])) {
            				String temp = map1.get(point.genes[i]);
            				while(map1.containsKey(temp)) {
            					temp = map1.get(temp);
            				}
                			o1[i] = temp;
            			} else {
            				o1[i] = point.genes[i];
            			}
            		}
            	}
            	
            	for(int i = 0; i < TSPData.CITY_NUM; i++) {
            		if(i < index1 || i > index2) {
            			if(map2.containsKey(point.next.genes[i])) {
            				String temp = map2.get(point.next.genes[i]);
            				while(map2.containsKey(temp)) {
            					temp = map2.get(temp);
            				}
                			o2[i] = temp;
            			} else {
            				o2[i] = point.next.genes[i];
            			}
            		}
            	}
            	
            	point.genes = o1;
            	point.next.genes = o2;
            }
        }
    }

}
