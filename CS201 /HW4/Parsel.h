///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW4_PARSEL_H
#define HW4_PARSEL_H
#include <iostream>
using namespace std;

class Parsel
{
private:
    int infectedDay;
    string healthStatus;
    bool isAdded;
    int xCoor;
    int yCoor;

public:
    Parsel();
    Parsel(string initialStatus, int xCoor, int yCoor);
    ~Parsel();

    int getInfectedDay();
    string getHealthStatus();
    int getX();
    int getY();
    bool getIsAdded();

    void setInfectedDay(int updatedDay);
    void setHealthStatus(string newHealthStatus);
    void setIsAdded(bool isAddedResult);
};


#endif //HW4_PARSEL_H
