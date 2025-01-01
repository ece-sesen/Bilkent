///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW3_USER_H
#define HW3_USER_H

#include <iostream>
using namespace std;

#include "Playlist.h"
#include "Linkedlist.h"

class User
{
private:
    LinkedList<Playlist>* playlists;
    int ID;
    string name;

public:
    User();
    User(int ID, string name);
    ~User();

    int getID() const;
    string getName();
    LinkedList<Playlist>* getPlaylists();
};


#endif //HW3_USER_H
