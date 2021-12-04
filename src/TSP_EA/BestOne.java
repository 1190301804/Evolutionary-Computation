package TSP_EA;


import GeneticAlgorirhms.GAimplement3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择最优的实现【这里是GAimplement1】，在 50的种群规模和 10000代下，使用10个样例进行测试
 *  用单轮运行时间作为cost
 *  记录cost均值和方差
 *  结果保留在Experiment2_result.txt 中
 */
public class BestOne {
    public static void main(String[] args) throws IOException {

        //设置参数  10000代
        TSPData.DEVELOP_NUM=10000;

        //10个文件
        File Dir = new File("test");
        List<String> files = new ArrayList<>();
        if (Dir.isDirectory()) {
            for (File f : Dir.listFiles()) {
                System.out.println(f.getName());
                files.add(f.getCanonicalPath());
            }
        }

        //结果文件保存到 Experiment2_result.txt中
        File Experiment1_result = new File("Experiment2_result.txt");
        Experiment1_result.createNewFile();
        BufferedWriter output = new BufferedWriter(new FileWriter(Experiment1_result, true));

        StringBuilder resultLine = new StringBuilder();
        resultLine.append("FileName\t\tResult\t\tCost\n");
        List<Long> cost = new ArrayList<>();
        System.out.println("规模：50   迭代次数：10000代   GAimplement1 测试结果如下：");
        if(Dir.isDirectory()){
            for(File f: Dir.listFiles()){
                String filePath=f.getCanonicalPath();
                String fileName=f.getName();
                System.out.println(fileName);
                //记录运行时间作为cost
                long start = System.currentTimeMillis();
                float result= Main.SingleRun(filePath,new GAimplement3(),50);
                long end = System.currentTimeMillis();
                long timeElapsed = end - start;
                System.out.println("\n运行时间："+timeElapsed+"ms");
                cost.add(timeElapsed);

                //写入文件中
                resultLine.append(fileName+"\t\t"+result+"\t\t"+timeElapsed+"\n");


            }
        }
        //总数
        int num = cost.size();
        //求和
        double sum=0;
        for(Long it : cost){
            sum+=it;
        }
        //求均值
        double average= sum/num;
        //求标准差
        double dVar=0;
        for(Long it :cost){
            dVar+=(it-average)*(it-average);
        }
        double StandardDeviation= Math.sqrt(dVar/num);


        //结果写入文件
        resultLine.append("averageCost:"+average+"\t\tstandardDeviation"+StandardDeviation);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Experiment2_result.txt"));
            out.write(String.valueOf(resultLine));
            out.close();
            System.out.println("Experiment2_result.txt created ！！");
        } catch (IOException e) {
        }









    }
}
