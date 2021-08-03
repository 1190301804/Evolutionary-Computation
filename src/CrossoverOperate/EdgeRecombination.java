package CrossoverOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;

/**
 * Edge Recombination
 */
 
public class EdgeRecombination implements CrossoverOperator{

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
            	String[] o = new String[TSPData.CITY_NUM];
            	
            	List<List<String>> elementList = new ArrayList<>();
            	
            	List<String> temp = new ArrayList<String>();
            	temp.add(point.genes[0]);
            	temp.add(point.genes[1]);
            	temp.add(point.genes[TSPData.CITY_NUM-1]);
            	elementList.add(temp);
            	
            	for(int i = 1; i < TSPData.CITY_NUM-1; i++) {
            		List<String> temp2 = new ArrayList<String>();
                	temp2.add(point.genes[i]);
                	temp2.add(point.genes[i-1]);
                	temp2.add(point.genes[i+1]);
                	elementList.add(temp2);
            	}
            	
            	List<String> temp3 = new ArrayList<String>();
            	temp3.add(point.genes[TSPData.CITY_NUM-1]);
            	temp3.add(point.genes[TSPData.CITY_NUM-2]);
            	temp3.add(point.genes[0]);
            	elementList.add(temp3);
            	
            	String pre = point.next.genes[TSPData.CITY_NUM-1];
        		String post = point.next.genes[1];
        		int index;
        		for(index = 0; index < TSPData.CITY_NUM; index++) {
        			if(elementList.get(index).get(0).equals(point.next.genes[0])) {
        				break;
        			}
        		}
        		if(!elementList.get(index).contains(pre)) {
        			elementList.get(index).add(pre);
        		}
        		if(!elementList.get(index).contains(post)) {
        			elementList.get(index).add(post);
        		}
            	
            	for(int i = 1; i < TSPData.CITY_NUM-1; i++) {
            		pre = point.next.genes[i-1];
            		post = point.next.genes[i+1];
            		for(index = 0; index < TSPData.CITY_NUM; index++) {
            			if(elementList.get(index).get(0).equals(point.next.genes[i])) {
            				break;
            			}
            		}
            		if(!elementList.get(index).contains(pre)) {
            			elementList.get(index).add(pre);
            		}
            		if(!elementList.get(index).contains(post)) {
            			elementList.get(index).add(post);
            		}
            	}
            	
            	pre = point.next.genes[TSPData.CITY_NUM-2];
        		post = point.next.genes[0];
        		for(index = 0; index < TSPData.CITY_NUM; index++) {
        			if(elementList.get(index).get(0).equals(point.next.genes[TSPData.CITY_NUM-1])) {
        				break;
        			}
        		}
        		if(!elementList.get(index).contains(pre)) {
        			elementList.get(index).add(pre);
        		}
        		if(!elementList.get(index).contains(post)) {
        			elementList.get(index).add(post);
        		}
        		
        		o[0] = point.genes[0];
        		int lastIndex = 0;
        		for(int j = 0; j < TSPData.CITY_NUM; j++) {
        			for(int l = 1; l < elementList.get(j).size(); l++) {
        				if(elementList.get(j).get(l).equals(o[0])) {
        					elementList.get(j).remove(l);
        					break;
        				}
        			}
        		}
        		for(int i = 1; i < TSPData.CITY_NUM; i++) {
        			int count = 10;
        			index = 0;
        			for(int j = 0; j < TSPData.CITY_NUM; j++) {
        				if(elementList.get(lastIndex).contains(elementList.get(j).get(0)) && !elementList.get(j).get(0).equals(elementList.get(lastIndex).get(0)) && elementList.get(j).size() < count) {
        					count = elementList.get(j).size();
        					index = j;
        				}
        			}
        			o[i] = elementList.get(index).get(0);
        			lastIndex = index;
        			for(int j = 0; j < TSPData.CITY_NUM; j++) {
        				for(int l = 1; l < elementList.get(j).size(); l++) {
        					if(elementList.get(j).get(l).equals(o[i])) {
        						elementList.get(j).remove(l);
        						break;
        					}
        				}
        			}
        		}
        		
            	point.genes = o;
            }
        	
        }
    }        

}
