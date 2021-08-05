package TSP_EA;
import GeneticAlgorirhms.*;
import Representation.Individual;
import Representation.Population;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {

        //种群数量 10 20 50 100
        List<Integer> SpecieNumList = new ArrayList<>(Arrays.asList(10, 20, 50, 100));

        //三种实现方式
        GA GAimplement1 = new GAimplement3();
        GA GAimplement2 = new GAimplement2();
        GA GAimplement3 = new GAimplement1();
        List<GA> ImplementList = new ArrayList<>(Arrays.asList(GAimplement1, GAimplement2, GAimplement3));

        //10个文件
        File Dir = new File("test");
        List<File> files = new ArrayList<>();
        if (Dir.isDirectory()) {
            for (File f : Dir.listFiles()) {
                System.out.println(f.getCanonicalPath());
                files.add(f);
            }
        }



        //10*4*3 轮
        //相同数据集，相同规模，选出最优的算法并计数。
//        List<Integer> BestAlgorithmCount= new ArrayList<>(Arrays.asList(0,0,0));
        int BestAlgorithmCount[] = {0, 0, 0};
        int count = 0;
        for (File file : files) {
            for (Integer specieNum : SpecieNumList) {
                float min = Float.MAX_VALUE;
                int minIndex = 0;
                for (GA GaImplement : ImplementList) {
                    System.out.println("\n\n " + count++ + "\t\t"
                            + file.getName() + "\t\t"
                            + "specieNum: " + specieNum + "\t\t"
                            + "Implementation" + (ImplementList.indexOf(GaImplement) + 1));

                    //找出最优的算法
                    float result = SingleRun(file.getCanonicalPath(), GaImplement, specieNum);
                    if (min > result) {
                        min =   result;
                        minIndex = ImplementList.indexOf(GaImplement);
                    }
                }
                BestAlgorithmCount[minIndex]++;
            }
        }


        //算法评分
        for (int i=0;i<3;i++) {
            System.out.println("120轮运行完成，三个算法的得分如下：");
            System.out.println("第"+i+"种算法分数："+
                    BestAlgorithmCount[i]+"\n");
        }


    }


    /**
     * Experiment 1) Run your three algorithms with population sizes 10, 20, 50, 100
     * on the instances EIL51, EIL76, EIL101, ST70, KROA100, KROC100, KROD100,
     * LIN105, PCB442, PR2392 from TSPlib. Report the outcomes after 5000, 10000,
     * and 20000 generations. To clarify: these are 3*4*10=120 runs, i.e., 3 algorithms X
     * 4 population sizes X 10 instances.
     *
     * @param DateFile    文件名
     * @param GaImplement 实现方式
     * @param SpecieNum   种群规模
     * @return 最短路径的长度
     * @throws IOException 如果文件不存在，就抛出异常
     */
    public static float SingleRun(String DateFile, GA GaImplement, int SpecieNum) throws IOException {

        //设置种群数量
        TSPData.SPECIES_NUM = SpecieNum;

        //载入数据
        TSPData.loadData(DateFile);

        //创建遗传算法驱动对象
        GA thisGA = GaImplement;

        //创建初始种群
        Population speciesPopulation = new Population();


        //开始遗传算法（选择算子、交叉算子、变异算子）
        Individual bestRate = thisGA.start(speciesPopulation);

//        //打印路径与最短距离
        bestRate.printRate();


        return  bestRate.distance;


    }


/*
格式
.
algorithm1  EIL51   5000   455.276
algorithm1  EIL51   10000  455.276
algorithm1  EIL51   20000   455.276
最短路线：3->36->35->20->29->2->16->9->50->21->34->30->39->10->49->38->5->37->15->33->45->44->42->19->40->41->13->4->17->12->47->18->25->14->24->43->7->23->48->6->51->46->11->32->27->1->22->8->26->31->28->3
最短长度：455.27557
 */
}