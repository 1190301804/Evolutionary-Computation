package CrossoverOperate;

import java.util.HashSet;
import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSPMain.TSPData;

/**
 * Cycle Crossover
 */

public class CycleCrossover implements CrossoverOperator{

	@Override
	public void crossover(Population individualSet) {
		//pcl~pch的概率
        float rate=(float)Math.random();
        if(rate > TSPData.pcl && rate < TSPData.pch)
        {           
            Individual point=individualSet.head.next;//游标
            Random rand=new Random();
            int find=rand.nextInt(individualSet.speciesNum);
            while(point != null && find != 0)//寻找表尾结点
            {
                point=point.next;
                find--;
            }

            if(point.next != null)
            {
            	int i;
            	int flag = 0;
            	Individual p1 = point;
            	Individual p2 = point.next;
                int begin=rand.nextInt(TSPData.CITY_NUM);
                HashSet<Integer> set = new HashSet<>();
                set.add(begin);
                String bg = p2.genes[begin];
                
                //获取所有循环圈上字符index
                while(true) {
                	//查询p1上字符
                	for(i = 0; i < TSPData.CITY_NUM; i++) {
                		if(p1.genes[i].equals(bg)) {
                			//循环了就退出
                			if(i == begin) {
                				flag = 1;
                			}
                			set.add(i);
                			break;
                		}
                	}
                	
                	//循环了就退出
                	if(flag == 1) break;
                	
                	bg = p2.genes[i];
                }
                
                //crossover
                for(int idx:set) {
                	String tmp = p1.genes[idx];
                	p1.genes[idx] = p2.genes[idx];
                	p2.genes[idx] = tmp;
                }
            }
        }
	}


}
