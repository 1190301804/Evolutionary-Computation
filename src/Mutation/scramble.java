package Mutation;

import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSPMain.TSPData;

/**
 * scramble mutation
 */

public class scramble implements MutateOperator{

	@Override
	public void mutate(Population poplist) {
		//每一物种均有变异的机会,以概率pm进行
        Individual point=poplist.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            if(rate < TSPData.pm)
            {
                //寻找区域左右端点
                Random rand=new Random();
                int leftpos=rand.nextInt(TSPData.CITY_NUM);
                int rightpos=rand.nextInt(TSPData.CITY_NUM);
                if(leftpos > rightpos)//保证右边比左边大
                {
                    int tmp;
                    tmp=leftpos;
                    leftpos=rightpos;
                    rightpos=tmp;
                }

                //随机打乱left-right的元素
                for(int i = leftpos; i <= rightpos; i++) {
                	int num = i + rand.nextInt(rightpos+1-i);
                	String tmp = point.genes[i];
                	point.genes[i] = point.genes[num];
                	point.genes[num] = tmp;
                }
            }
            point=point.next;
        }
	}

}
