///Ece ŞEŞEN, CS201-03, ID: 22201637

#include "Parsel.h"
Parsel::Parsel() ///default constructor
{

}

Parsel::Parsel( string status, int x, int y)
{
    healthStatus = status;
    infectedDay = -1;
    isAdded = false;
    xCoor = x;
    yCoor = y;
}

Parsel::~Parsel() ///Destructor
{

}

///Setter methods
void Parsel::setInfectedDay( int day)
{
    infectedDay = day;
}

void Parsel::setHealthStatus(string newStatus)
{
    healthStatus = newStatus;
}

void Parsel::setIsAdded(bool isAddedResult)
{
    isAdded = isAddedResult;
}


///Getter Methods
int Parsel::getInfectedDay()
{
    return infectedDay;
}

string Parsel::getHealthStatus()
{
    return healthStatus;
}

int Parsel::getX()
{
    return xCoor;
}

int Parsel::getY()
{
    return yCoor;
}

bool Parsel::getIsAdded()
{
    return isAdded;
}


