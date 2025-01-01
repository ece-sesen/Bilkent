///Ece ŞEŞEN, CS201-03, ID: 22201637

/**
 * This is Bilkent Beats class
 * This class handles adding songs, playlists and users too the system.
 * Songs, users and playlists belongs to users are stored in linledlisyts.
 * This system also prints the user and song lists according to ascending IDs.
 *
 * @author ECE ŞEŞEN
 * @version 10.12.2024
 */

#ifndef HW3_BILKENTBEATS_H
#define HW3_BILKENTBEATS_H
#include <iostream>
using namespace std;

#include "Linkedlist.h"
#include "Node.h"
#include "User.h"
#include "Song.h"

class BilkentBeats
{
private:
    LinkedList<User>* users;
    LinkedList<Song>* library;
    ///LinkedList<Playlist>* playlists;
public:
    BilkentBeats();
    ~BilkentBeats();

    void addUser( const int userId, const string userName );
    void removeUser( const int userId );
    void printUsers() const;

    void addSong( const int songID, const string songName, const string artists );
    void removeSong( const int songID );
    void printSongs() const;

    void addPlaylist( const int userId, const int playlistId );
    void removePlaylist( const int userId, const int playlistId );
    void addSongToPlaylist( const int playlistId, const int songId );
    void removeSongFromPlaylist( const int playlistId, const int songId);
    void printSongsInPlaylist( const int playlistId ) const;

    Node<Playlist>* playlistExist(int playlistID) const;
    bool songExistInPlaylist(int songID);
    string printPlaylistID(User user) const;
};

#endif //HW3_BILKENTBEATS_H
