///Ece ŞEŞEN, CS201-03, ID: 22201637
#include "User.h"

User::User()
{

}

User::User(int aID, string aName)
{
    ID = aID;
    name = aName;
    playlists = new LinkedList<Playlist>();
}
User::~User()
{

}

int User::getID() const
{
    return ID;
}

string User::getName()
{
    return name;
}

LinkedList<Playlist>* User::getPlaylists()
{
    return playlists;
}