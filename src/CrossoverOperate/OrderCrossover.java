package CrossoverOperate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;

/**
 * Order Crossover
 */

public class OrderCrossover implements CrossoverOperator{

	@Override
	public void crossover(Population list) {
    	//pcl~pch的概率
        float rate=(float)Math.random();
        if(rate > TSPData.pcl && rate < TSPData.pch)
        {           
        	Individual point=list.head.next;//游标
            Random rand=new Random();
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
            	Set<String> temp1 = new HashSet<String>();
            	Set<String> temp2 = new HashSet<String>();
            	for(int i = index1; i <= index2; i++) {
            		o1[i] = point.genes[i];
            		o2[i] = point.next.genes[i];
            		temp1.add(point.genes[i]);
            		temp2.add(point.next.genes[i]);
            	}
            	
            	int j = (index2 + 1) % TSPData.CITY_NUM;
            	for(int i = 0; i < TSPData.CITY_NUM; i++) {
            		int index = (i + index2 + 1) % TSPData.CITY_NUM;
            		if(!temp1.contains(point.next.genes[index])) {
            			o1[j] = point.next.genes[index];
            			j++;
            			j = j % TSPData.CITY_NUM;
            		}
            	}
            	
            	j = (index2 + 1) % TSPData.CITY_NUM;
            	for(int i = 0; i < TSPData.CITY_NUM; i++) {
            		int index = (i + index2 + 1) % TSPData.CITY_NUM;
            		if(!temp2.contains(point.genes[index])) {
            			o2[j] = point.genes[index];
            			j++;
            			j = j % TSPData.CITY_NUM;
            		}
            	}

				point.next.genes = o2;
            	point.genes = o1;

            }
        	
        }
    }      

}
