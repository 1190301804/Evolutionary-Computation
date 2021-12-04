package Selection;

import java.util.ArrayList;
import java.util.Random;

import GeneticAlgorirhms.GAimplement3;
import Representation.Individual;
import Representation.Population;

/**
 * 选择优秀物种（锦标赛）
 */

public class tournament_elitism implements selectOperator{

	ArrayList Position(int range, int num){    //随机选取num个不同的下标进行n元竞赛
        ArrayList<Integer> pos = new ArrayList<>();
        Random r = new Random();
        do{    //随机放回选取elementNum个元素进行锦标赛选取
            int temp = r.nextInt(range) + 1;
            if(!pos.contains(temp))
                pos.add(temp);
        }while(pos.size() != num);
        return pos;
    }
	
	@Override
    public Individual select(Population list){
        //计算适应度
        GAimplement3.calRate(list);
        //找出最大适应度物种
        float talentDis=Float.MAX_VALUE;
        Individual talentSpecies=null;
        Individual point=list.head.next;//游标
        while(point!=null)
        {
            if(talentDis > point.distance)
            {
                talentDis=point.distance;
                talentSpecies=point;
            }
            point=point.next;
        }
        //将最大适应度物种复制talentNum个
        Population newSpeciesPopulation=new Population();
        //剩余3/4n个元素通过锦标赛选取
        int elementNum = 3;   //设置为三元竞赛选择
        for(int i = 0;i < list.speciesNum; i ++){
            ArrayList<Integer> pos = new ArrayList<>();
            pos = Position(list.speciesNum,elementNum);
            //ArrayList<SpeciesIndividual> temp = new ArrayList<>();
            Individual temp = new Individual();
            temp = list.get(pos.get(0));
            for(int j = 1; j < elementNum; j++){
                //temp.add(list.get(pos.get(j)));
                if(list.get(pos.get(j)).distance<temp.distance)
                    temp = list.get(pos.get(j));
            }
            newSpeciesPopulation.add(temp);
        }
        return talentSpecies.clone();
    }

}
