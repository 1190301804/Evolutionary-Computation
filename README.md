# Evolutionary-Computation
Evolutionary Computation Group Assignment: The Traveling Salesperson Problem

## 代码介绍

#### CrossoverOperator:

包括 Order Crossover, PMX Crossover, Cycle Crossover, Edge Recombination 四种交叉算子

#### Mutation:

分别实现了insert, swap, inversion, scramble 四种变异算子

#### Select:

分别实现了fitness-proportional, tournament selection,elitism 选择算子

#### Representation 
实现了Individual（个体类） 与 Population（种群类）

#### GeneticAlgorithm
算法主要实现类，**在run函数的for循环中可选择使用何种select算子，mutation算子和crossover算子**，每种类型的算子一次只能选择一个，因此需要将其他的都注释掉。
#### TSP_EA:

    TSPProblem：主程序，在此处运行整个函数
    
    GeneticAlgorithm：算法主要实现类，**在run函数的for循环中可选择使用何种select算子，mutation算子和crossover算子**，每种类型的算子一次只能选择一个，因此需要将其他的都注释掉。

    
    TSPData：TSP数据类，存放TSP种各个地点的位置，里面的loaddata函数用于从文件中读取数据，修改文件名为想要读取的文件即可除此以外，该类中还有种群数，进化代数以及交叉和变异概率等字段，根据需要修改即可
    


## 如何使用

**1.修改TSPData文件中loaddata函数中的file为想要读取的文件，并在TSPData中设置合适的种群数，进化代数和交叉、变异概率**

**2.在GeneticAlgorithm的run函数中，有一个for循环用于迭代，包含选择，交叉，变异等过程，为三个过程选择合适的算子，并将不想使用的算子注释掉**

**3.运行TSPProblem程序即可**



