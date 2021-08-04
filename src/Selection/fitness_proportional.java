package Selection;

import GeneticAlgorirhms.GAimplement1;
import Representation.Individual;
import Representation.Population;

/**
 * 选择优秀物种（轮盘赌）
 */

public class fitness_proportional implements selectOperator{

	@Override
	public Individual select(Population list) {
      //计算适应度
      GAimplement1.calRate(list);

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
      int talentNum=(int)(list.speciesNum/4);
      for(int i=1;i<=talentNum;i++)
      {
          //复制物种至新表
          Individual newSpecies=talentSpecies.clone();
          newSpeciesPopulation.add(newSpecies);
      }

      //轮盘赌list.speciesNum-talentNum次
      int roundNum=list.speciesNum-talentNum;
      for(int i=1;i<=roundNum;i++)
      {
          //产生0-1的概率
          float rate=(float)Math.random();

          Individual oldPoint=list.head.next;//游标
          while(oldPoint != null && oldPoint != talentSpecies)//寻找表尾结点
          {
              if(rate <= oldPoint.rate)
              {
                  Individual newSpecies=oldPoint.clone();
                  newSpeciesPopulation.add(newSpecies);

                  break;
              }
              else
              {
                  rate=rate-oldPoint.rate;
              }
              oldPoint=oldPoint.next;
          }
          if(oldPoint == null || oldPoint == talentSpecies)
          {
              //复制最后一个
              point=list.head;//游标
              while(point.next != null)//寻找表尾结点
                  point=point.next;
              Individual newSpecies=point.clone();
              newSpeciesPopulation.add(newSpecies);
          }

      }
      list.head=newSpeciesPopulation.head;
      return talentSpecies;
  }
	

}
