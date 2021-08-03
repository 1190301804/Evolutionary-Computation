package Mutation;

import java.util.Random;

import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;



public class swap implements MutateOperator{

	@Override
	public void mutate(Population poplist) {
		//每一物种均有变异的机会,以概率pm进行
        Individual point=poplist.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            //变异
            if(rate < TSPData.pm) {
                Random rand=new Random();
                int leftpos=rand.nextInt(TSPData.CITY_NUM);
                int rightpos=rand.nextInt(TSPData.CITY_NUM);
                String tmp;
                tmp=point.genes[rightpos];
                point.genes[rightpos] = point.genes[leftpos];
                point.genes[leftpos] = tmp;
            }
            point=point.next;
        }
	}
}
