///Ece ŞEŞEN, CS201-03, ID: 22201637
#include "Playlist.h"
Playlist::Playlist(int aID)
{
    ID = aID;
    songs = new LinkedList<Song>();
}
Playlist::Playlist()
{

}

Playlist::~Playlist()
{

}

int Playlist::getID() const
{
    return ID;
}

LinkedList<Song>* Playlist::getSongs()
{
    return songs;
}

bool Playlist::songExist(int id)
{
    Node<Song>* curPtr = songs->getHeader();
    for(int i = 1; i < songs->getLength(); i++)
    {
        if(curPtr->getItem().getID() == id)
        {
            return true;
        }
    }
    return false;
}