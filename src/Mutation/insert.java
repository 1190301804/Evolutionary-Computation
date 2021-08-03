package Mutation;

import java.util.Random;
import TSPMain.Individual;
import TSPMain.Population;
import TSPMain.TSPData;

/**
 * insert mutation
 */

public class insert implements MutateOperator{

	@Override
	public void mutate(Population list) {
		//每一物种均有变异的机会,以概率pm进行
		Individual point=list.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            //变异
            if(rate < TSPData.pm) {
                Random rand=new Random();
                int left=rand.nextInt(TSPData.CITY_NUM);
                int right=rand.nextInt(TSPData.CITY_NUM);
                if(left == right) continue;
                //保持left小于right
                if(left > right) {
                	int tmp = left;
                	left = right;
                	right = tmp;
                }
                //insert
                String tmp = point.genes[right];
                for(int i = right; i > left+1; i--) {
                	point.genes[i] = point.genes[i-1];
                }
                point.genes[left+1] = tmp;
            }
            point=point.next;
        }
	}

}
