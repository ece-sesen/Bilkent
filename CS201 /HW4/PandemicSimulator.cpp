///Ece ŞEŞEN, CS201-03, ID: 22201637

#include "PandemicSimulator.h"

PandemicSimulator::PandemicSimulator(const string cityGridFile)
{
    day = 0;
    currentInfected = nullptr;
    nextInfected = new ListQueue<Node<Parsel>>();
    city = nullptr;
    rowNum =  0;

    string cityGridData = "";

    ///Read txt file
    ifstream in;
    in.open(cityGridFile);
    in>>rowNum;
    in>>columnNum;

    string currentRow = "";
    for(int i = 0; i < rowNum; i++)
    {
        in >> currentRow;
        cityGridData += currentRow;
    }
    in.close();

    ///Construct city
    int index = 0;

    ///Assign houses to city (2D array)
    city = new Parsel*[rowNum];
    for(int row = 0; row < rowNum; row++)
    {
        city[row] = new Parsel[columnNum];
        for(int column = 0; column < columnNum; column++)
        {
            Parsel* newParsel = new Parsel(cityGridData.substr(index, 1), column, row);
            if(newParsel->getHealthStatus() == "2")
            {
                nextInfected->enqueue(*newParsel); ///Add infectious houses to queues to maintained spread of virus
            }
            index++;
            city[row][column] = *newParsel;
            delete newParsel;
        }
    }
    spreadVirus();
}

PandemicSimulator::~PandemicSimulator()
{
    for(int row = 0; row < rowNum; row++)
    {
        delete[] city[row];
    }
    delete[] city;

    delete currentInfected;
    delete nextInfected;
}

void PandemicSimulator::displayCityState(const int time)
{
    ///Print the city
    cout << "City state at day " << time << ":"<< endl;
    for(int row = 0; row< rowNum; row++)
    {
        for(int column = 0; column < columnNum; column++)
        {
            if( city[row][column].getHealthStatus() == "0") ///Parts that cannot be infected
            {
                cout << 0;
            }
            else
            {
                if( city[row][column].getInfectedDay() <= time && city[row][column].getInfectedDay() != -1) ///Already infected
                {
                    cout << 2;
                }
                else
                {
                    cout << 1; ///Not infected yet
                }
            }
        }
        cout << endl;
    }
}

void PandemicSimulator::simulateBlock(int y, int x)
{
    if( x < columnNum && y < rowNum)
    {
        cout << "Time for block (" << y << ", " << x << ") to be infected: " << city[y][x].getInfectedDay() << " days." << endl;
    }
}

void PandemicSimulator::simulatePandemic()
{
    spreadVirus();
    if(allBlocksInfected())
    {
        cout << "Minimum time for pandemic to spread to all blocks: " << day - 1 << " days." << endl;
    }
    else
    {
        cout << "Pandemic cannot spread to all blocks." << endl;
    }
}

bool PandemicSimulator::allBlocksInfected()
{
    for(int row = 0; row< rowNum; row++)
    {
        for(int column = 0; column < columnNum; column++)
        {
            if( city[row][column].getHealthStatus() == "1")
            {
               return false;
            }
        }
    }
    return true;
}

void PandemicSimulator::updateItsNeighbors(Parsel house)
{
    ///Order is in clockwise up, right, down, left
    int x = house.getX();
    int y = house.getY();


    if(y - 1 >= 0 && city[y - 1][x].getHealthStatus() == "1" && !city[y - 1][x].getIsAdded() ) ///up
    {
        nextInfected->enqueue(city[y - 1][x]);
        city[y - 1][x].setIsAdded(true);

    }
    if(x + 1 < columnNum && city[y][x + 1].getHealthStatus() == "1" && !city[y][x + 1].getIsAdded()) ///right
    {
        nextInfected->enqueue(city[y][x + 1]);
        city[y][x + 1].setIsAdded(true);
    }
    if(y + 1 < rowNum && city[y + 1][x].getHealthStatus() == "1" && !city[y + 1][x].getIsAdded()) ///down
    {
        nextInfected->enqueue(city[y + 1][x]);
        city[y + 1][x].setIsAdded(true);
    }
    if(x - 1 >= 0 && city[y][x - 1].getHealthStatus() == "1" && !city[y][x - 1].getIsAdded()) ///left
    {
        nextInfected->enqueue(city[y][x - 1]);
        city[y][x - 1].setIsAdded(true);
    }
}

void PandemicSimulator::spreadVirus()
{
    while(!nextInfected->isEmpty())
    {
        delete currentInfected; ///houses that infected that day
        currentInfected = nextInfected;
        nextInfected = new ListQueue<Node<Parsel>>(); ///neighors of infected today houses which will be infected tomottoz

        while(!currentInfected->isEmpty())
        {
            Parsel removedCity = currentInfected->peekFront().getItem();
            removedCity.setInfectedDay(day);
            removedCity.setHealthStatus("2");
            updateItsNeighbors(removedCity);
            currentInfected->dequeue(); ///remove from queue
            city[removedCity.getY()][removedCity.getX()] = removedCity;
        }
        day++;
    }
}




