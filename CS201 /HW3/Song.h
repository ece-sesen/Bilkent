///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW3_SONG_H
#define HW3_SONG_H

#include <iostream>
using namespace std;

class Song
{private:
    int ID;
    string name;
    string artist;
public:
    Song();
    Song(int ID, string name, string artist);
    ~Song();

    int getID() const;
    string getName();
    string getArtist();
};


#endif //HW3_SONG_H
