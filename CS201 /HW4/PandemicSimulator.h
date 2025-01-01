///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW4_PANDEMICSIMULATOR_H
#define HW4_PANDEMICSIMULATOR_H

#include <iostream>
#include <fstream>
#include "ListQueue.h"
#include "Parsel.h"
using namespace std;

class PandemicSimulator
{
private:
    int day;
    ListQueue<Node<Parsel>>* currentInfected;
    ListQueue<Node<Parsel>>* nextInfected;
    Parsel** city;
    int columnNum;
    int rowNum;

public:
    PandemicSimulator(const string cityGridFile);
    ~PandemicSimulator();

    void displayCityState(const int time);
    void simulateBlock(int rowNum, int columnNum);
    void simulatePandemic();

    void updateItsNeighbors(Parsel city);
    void spreadVirus();
    bool allBlocksInfected();
};
#endif //HW4_PANDEMICSIMULATOR_H
