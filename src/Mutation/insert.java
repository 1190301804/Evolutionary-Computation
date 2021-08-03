package Mutation;

import java.util.Random;
import Representation.Individual;
import Representation.Population;
import TSPMain.TSPData;

public class insert implements MutateOperator{

	@Override
	public void mutate(Population poplist) {
		//每一物种均有变异的机会,以概率pm进行
		Individual point=poplist.head.next;
        while(point != null)
        {
            float rate=(float)Math.random();
            //变异
            if(rate < TSPData.pm) {
                Random pickpos=new Random();
                int leftpos=pickpos.nextInt(TSPData.CITY_NUM);//随机选取的第一个数
                int rightpos=pickpos.nextInt(TSPData.CITY_NUM);//随机选取的第二个数
                if(leftpos == rightpos)
                {
                     continue;
                }
            
                //保持left小于right,left小于right时互换即可
                if(leftpos > rightpos) {
                	int tmp = leftpos;
                	leftpos = rightpos;
                	rightpos = tmp;
                }
                //insert操作
                String temp = point.genes[rightpos];
                for(int i = rightpos; i > leftpos+1; i--) {
                	point.genes[i] = point.genes[i-1];
                }
                point.genes[leftpos+1] = temp;
            }
            point=point.next;
        }
	}

}
