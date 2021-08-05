package TSP_EA;

import java.io.IOException;

import GeneticAlgorirhms.GAimplement3;
import Representation.Individual;
import Representation.Population;

/**
 * 主函数运行类
 */

public class TSPProblem {

    /**
     * 打印台输出示例：指定 输入文件 EA的实现方式 运行
     * @param args
     * @throws IOException 如果文件不存在，抛出异常
     */
    public static void main(String[] args) throws IOException {
        //载入数据
    	TSPData.loadData();
        //创建遗传算法驱动对象
        GAimplement3 GA=new GAimplement3();
        
        //创建初始种群
        Population speciesPopulation = new Population();
        
        
        //开始遗传算法（选择算子、交叉算子、变异算子）
        Individual bestRate=GA.start(speciesPopulation);

        //打印路径与最短距离
        bestRate.printRate();

    }




}
