///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW3_PLAYLIST_H
#define HW3_PLAYLIST_H

#include "Song.h"
#include "Linkedlist.h"

class Playlist
{
private:
    int ID;
    LinkedList<Song>* songs;

public:
    Playlist();
    Playlist(int playlistID);
    ~Playlist();
    int getID() const;

    LinkedList<Song>* getSongs();
    bool songExist(int songID);



};


#endif //HW3_PLAYLIST_H
