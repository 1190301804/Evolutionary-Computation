package Mutation;

import java.util.Random;

import TSPMain.Individual;
import TSPMain.Population;
import TSPMain.TSPData;

/**
 * scramble mutation
 */

public class scramble implements MutateOperator{

	@Override
	public void mutate(Population list) {
		//每一物种均有变异的机会,以概率pm进行
        Individual point=list.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            if(rate < TSPData.pm)
            {
                //寻找区域左右端点
                Random rand=new Random();
                int left=rand.nextInt(TSPData.CITY_NUM);
                int right=rand.nextInt(TSPData.CITY_NUM);
                if(left > right)
                {
                    int tmp;
                    tmp=left;
                    left=right;
                    right=tmp;
                }

                //随机打乱left-right的元素
                for(int i = left; i <= right; i++) {
                	int num = i + rand.nextInt(right+1-i);
                	String tmp = point.genes[i];
                	point.genes[i] = point.genes[num];
                	point.genes[num] = tmp;
                }
            }
            point=point.next;
        }
	}

}
