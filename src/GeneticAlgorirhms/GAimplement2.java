package GeneticAlgorirhms;

import CrossoverOperate.CrossoverOperator;

import CrossoverOperate.PMXCrossover;
import Mutation.MutateOperator;
import Mutation.insert;
import Select.selectOperator;
import Select.tournament_elitism;
import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;

public class GAimplement2 implements GA {
	public Individual start(Population citylist)
    {
        //创建初始种群
        createBeginningSpecies(citylist);

        for(int i=1;i<=TSPData.DEVELOP_NUM;i++)
        {
            
        	selectOperator se = new tournament_elitism();
            Individual temp = se.select(citylist);
            CrossoverOperator co = new PMXCrossover();
            co.crossover(citylist);
            MutateOperator mu = new insert();  
            mu.mutate(citylist);
            replace(citylist, temp);
            if(i % 200 == 0)
            	System.out.println("第"+i+"次迭代结果为："+getBest(citylist).distance);

        }   

        return getBest(citylist);
    }

    //创建初始种群
    public void createBeginningSpecies(Population list)
    {
        //100%随机
        int randomNum=(int)(TSPData.SPECIES_NUM);
        for(int i=1;i<=randomNum;i++)
        {
            Individual species=new Individual();//创建结点
            species.createByRandomGenes();//初始种群基因

            list.add(species);//添加物种
        }
    }

    //计算每一物种被选中的概率
    public static void calRate(Population list)
    {
        //计算总适应度
        float totalFitness=0.0f;
        list.speciesNum=0;
        Individual point=list.head.next;//游标
        while(point != null)//寻找表尾结点
        {
            point.calFitness();//计算适应度

            totalFitness += point.fitness;
            list.speciesNum++;

            point=point.next;
        }

        //计算选中概率
        point=list.head.next;//游标
        while(point != null)//寻找表尾结点
        {
            point.rate=point.fitness/totalFitness;
            point=point.next;
        }
    }

    //删除list中的最小项，用当前迭代下的最优路径进行替换
    void replace(Population list, Individual best){
        float maxDis = 0;
        Individual point=list.head.next;//游标
        Individual point2=list.head.next;//游标

        while(point!=null)
        {
            if(maxDis < point.distance)
            {
                maxDis=point.distance;
            }
            point=point.next;
        }
        while(point2 != null){
            if(point2.distance == maxDis){
                point2.distance = best.distance;
                point2.fitness = best.fitness;
                point2.genes = best.genes.clone();
                point2.rate = best.rate;
                break;
            }
            point2 = point2.next;
        }

    }

    //获得适应度最大的物种
    Individual getBest(Population list)
    {
        float distance=Float.MAX_VALUE;
        Individual bestSpecies=null;
        Individual point=list.head.next;//游标
        while(point != null)//寻找表尾结点
        {
            if(distance > point.distance)
            {
                bestSpecies=point;
                distance=point.distance;
            }

            point=point.next;
        }

        return bestSpecies;
    }
}
