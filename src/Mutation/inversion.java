package Mutation;

import java.util.Random;

import TSPMain.Individual;
import TSPMain.Population;
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
                int left=rand.nextInt(TSPData.CITY_NUM);
                int right=rand.nextInt(TSPData.CITY_NUM);
                if(left > right)
                {
                    int tmp;
                    tmp=left;
                    left=right;
                    right=tmp;
                }

                //逆转left-right下标元素
                while(left < right)
                {
                    String tmp;
                    tmp=point.genes[left];
                    point.genes[left]=point.genes[right];
                    point.genes[right]=tmp;

                    left++;
                    right--;
                }
            }
            point=point.next;
        }
	}

}
