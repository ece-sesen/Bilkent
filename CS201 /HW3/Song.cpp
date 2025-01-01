//
// Created by Ece SESEN on 6.12.2024.
//

#include "Song.h"
Song::Song()
{

}

Song::Song(int aID, string aName, string anArtist)
{
    ID = aID;
    name = aName;
    artist = anArtist;
}

Song::~Song()
{

}

int Song::getID() const
{
    return ID;
}
string Song::getName()
{
    return name;
}

string Song::getArtist()
{
    return artist;
}