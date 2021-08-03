package TSPMain;

import java.io.IOException;

import GeneticAlgorirhms.GAimplement1;
import Representation.Individual;
import Representation.Population;

/**
 * 主函数运行类
 */

public class TSPProblem {

    public static void main(String[] args) throws IOException {
        //载入数据
    	TSPData.loaddata();
        //创建遗传算法驱动对象
        GAimplement1 GA=new GAimplement1();
        
        //创建初始种群
        Population speciesPopulation = new Population();
        
        
        //开始遗传算法（选择算子、交叉算子、变异算子）
        Individual bestRate=GA.start(speciesPopulation);

        //打印路径与最短距离
        bestRate.printRate();

    }
}
