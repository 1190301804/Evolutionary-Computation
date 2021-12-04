package GeneticAlgorirhms;

import CrossoverOperate.*;
import Mutation.*;
import Selection.fitness_proportional;
import Selection.selectOperator;
import Representation.Individual;
import Representation.Population;
import TSP_EA.TSPData;

/**
 * 遗传算法类
 */

public class GAimplement3 implements GA{
	   @Override
	   public Individual start(Population citylist)
        {
            //创建初始种群
            createBeginningSpecies(citylist);

            for(int i=1;i<=TSPData.DEVELOP_NUM;i++)
            {
                /*选择
            	 * 选择不同的select算子
            	 * fitness-proportional算子：fitness_proportional类
            	 * tournament selection和elitism算子： tournament_elitism类
            	*/
            	
            	selectOperator se = new fitness_proportional(); 
            	//selectOperator se = new tournament_elitism();
                Individual temp = se.select(citylist);

                
                /*交叉
                 * 选择不同的crossover算子
                 * Order Crossover算子：Order类
                 * PMX Crossover算子    ：PMX类
                 * Cycle Crossover算子：Cycle类
                 * Edge Recombination算子：EdgeRecombination类
                */
                
                CrossoverOperator co = new CycleCrossover();
                //CrossoverOperator co = new PMX();
                //CrossoverOperator co = new Order();
                //CrossoverOperator co = new EdgeRecombination();
                co.crossover(citylist);

                
                /*变异
                 * 选择不同的mutation算子
                 * insert算子：insert类
                 * swap算子    ：swap类
                 * inversion算子：inversion类
                 * scramble算子  ：scramble类
                */
                
                //MutateOperator mu = new insert(); 
                //MutateOperator mu = new swap(); 
                MutateOperator mu = new inversion(); 
                //MutateOperator mu = new scramble(); 
                mu.mutate(citylist);

                replace(citylist, temp);
                if(i ==5000||i== 10000||i==20000)
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