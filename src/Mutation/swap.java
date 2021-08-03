package Mutation;

import java.util.Random;

import TSPMain.Individual;
import TSPMain.Population;
import TSPMain.TSPData;

/**
 * swap mutation
 */

public class swap implements MutateOperator{

	@Override
	public void mutate(Population list) {
		// TODO Auto-generated method stub
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
                String tmp;
                tmp=point.genes[left];
                point.genes[left] = point.genes[right];
                point.genes[right] = tmp;
            }
            point=point.next;
        }
	}
}
