package Representation;

import TSP_EA.TSPData;

/**
 * 种群类
 */

public class Population {

    public Individual head;//头结点
    public int speciesNum;//物种数量

    public Population()
    {
        head=new Individual();
        speciesNum= TSPData.SPECIES_NUM;
    }

    //添加物种
    public void add(Individual species)
    {
        Individual point=head;//游标
        while(point.next != null)//寻找表尾结点
            point=point.next;
        point.next=species;
    }

    //获取第k个物种
    public Individual get(int k)
    {
        Individual point = head;    //游标
        for(int i = 0; i < k; i++){
            point = point.next;
        }
        Individual res = point.clone();
        //res.next = null;
        return res;
    }

    //遍历
    void traverse()
    {
        Individual point=head.next;//游标
        while(point != null)//寻找表尾结点
        {
            for(int i=0;i<TSPData.CITY_NUM;i++)
                System.out.print(point.genes[i]+" ");
            System.out.println(point.distance);
            point=point.next;
        }
        System.out.println("_______________________");
    }

}