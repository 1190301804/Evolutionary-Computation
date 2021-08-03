package Mutation;

import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSPMain.TSPData;

/**
 * inversion mutation
 */

public class inversion implements MutateOperator{

	@Override
	public void mutate(Population list) {
		//每一物种均有变异的机会,以概率pm进行
        Individual point=list.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            if(rate < TSPData.pm)
            {
                //寻找逆转左右端点
                Random rand=new Random();
                int leftpos=rand.nextInt(TSPData.CITY_NUM);
                int rightpos=rand.nextInt(TSPData.CITY_NUM);
                if(leftpos > rightpos)
                {
                    int tmp;
                    tmp=leftpos;
                    leftpos=rightpos;
                    rightpos=tmp;
                }

                //逆转left-right下标元素
                while(leftpos < rightpos)
                {
                    String tmp;
                    tmp=point.genes[leftpos];
                    point.genes[leftpos]=point.genes[rightpos];
                    point.genes[rightpos]=tmp;

                    leftpos++;
                    rightpos--;
                }
            }
            point=point.next;
        }
	}

}
