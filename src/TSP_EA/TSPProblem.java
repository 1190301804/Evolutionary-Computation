package TSP_EA;

import java.io.IOException;

import GeneticAlgorirhms.GAimplement1;
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


    /**
     * Experiment 1) Run your three algorithms with population sizes 10, 20, 50, 100
     * on the instances EIL51, EIL76, EIL101, ST70, KROA100, KROC100, KROD100,
     * LIN105, PCB442, PR2392 from TSPlib. Report the outcomes after 5000, 10000,
     * and 20000 generations. To clarify: these are 3*4*10=120 runs, i.e., 3 algorithms X
     * 4 population sizes X 10 instances.
     * @throws IOException
     */
    public void RunAll() throws IOException{
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
