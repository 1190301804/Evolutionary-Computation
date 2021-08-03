package TSPMain;

import java.io.IOException;

/**
 * 主函数运行类
 */

public class TSPProblem {

    public static void main(String[] args) throws IOException {
        //载入数据
    	TSPData.loaddata();
        //创建遗传算法驱动对象
        GeneticAlgorithm GA=new GeneticAlgorithm();
        
        //创建初始种群
        Population speciesPopulation = new Population();
        
        
        //开始遗传算法（选择算子、交叉算子、变异算子）
        Individual bestRate=GA.run(speciesPopulation);

        //打印路径与最短距离
        bestRate.printRate();

    }
}
