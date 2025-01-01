///Ece ŞEŞEN, CS201-03, ID: 22201637

/**
 * Bilkent Beats pp file
 */

#include "BilkentBeats.h"

///Constructor
BilkentBeats::BilkentBeats()
{
    users = new LinkedList<User>();
    library = new LinkedList<Song>();

}

///Destructor
BilkentBeats::~BilkentBeats()
{
    for(Node<User>* tempU = users->getHeader(); tempU != nullptr; tempU = tempU->getNext() )
    {
        for(Node<Playlist>* tempP = tempU->getItem().getPlaylists()->getHeader(); tempP != nullptr; tempP = tempP->getNext())
        {
            delete tempP->getItem().getSongs();
        }
        delete tempU->getItem().getPlaylists();
    }
    delete users;
    delete library;
}

/**
 * Add user to the system if it has unique ıd.
 * Start its playlist as empty and print thse result.
 * @param userId unique user ID
 * @param userName user name
 */
void BilkentBeats::addUser(const int userId, const string userName)
{
    if(users->containsID(userId))
    {
        cout << "Cannot add user. There is already a user with ID " << userId << "." << endl;
    }
    else
    {
        User* newUser = new User(userId, userName);
        users->insert(*newUser);
        cout << "Added user " << userId << "." << endl;
        delete newUser;
    }
}

/**
 * Remove the user who has given user ID from the system. ALso remove its playlists with stored songs in it.
 * Print the result.
 * @param userId unique ID
 */
void BilkentBeats::removeUser(const int userId)
{
    if(!users->containsID(userId))
    {
        cout << "Cannot remove user. There is no user with ID " << userId << "." << endl;
    }
    else
    {
        Node<User>* removed = users->findNode(userId);
        int index = users->findIndex(userId);

        for(Node<Playlist>* curPtr = removed->getItem().getPlaylists()->getHeader(); curPtr != nullptr; curPtr = curPtr->getNext())
        {
            delete curPtr->getItem().getSongs();
        }
        delete removed->getItem().getPlaylists();
        users->remove(index);
        cout << "Removed user " << userId << "." << endl;
    }
}

/**
 * Print all users with their playlist IDs in ascending order.
 */
void BilkentBeats::printUsers() const
{
    if(users->getLength() == 0)
    {
        cout << "There are no users to show." << endl;
    }
    else
    {
        cout << "Users in the system:" << endl;
        Node<User>* curPtr = users->getHeader();
        for(int i = 1; i <= users->getLength(); i++)
        {
            cout << "User ID : " << curPtr->getItem().getID() << ", Name : " << curPtr->getItem().getName() << ", Playlist IDs : ";
            cout << printPlaylistID(curPtr->getItem())  << endl;
            curPtr = curPtr->getNext();
        }
    }
}

/**
 * Create the song and add to the system if its ID is unique
 * Print the result
 * @param songID unique song ID
 * @param songName song name
 * @param artists artists of the somg
 */
void BilkentBeats::addSong(const int songID, const string songName, const string artists)
{
    if(library->containsID(songID))
    {
        cout << "Cannot add song. BilkentBeats already contains song " << songID << "." << endl;
    }
    else
    {
        Song* newSong = new Song(songID, songName, artists);
        library->insert(*newSong);
        cout << "Added song " << songID << "." << endl;
        delete newSong;
    }
}

/**
 * remove the song which has given ID from the library
 * Print the result.
 * @param songID song ID
 */
void BilkentBeats::removeSong(const int songID)
{
    if(!library->containsID(songID))
    {
        cout << "Cannot remove song. There is no song with ID " << songID << "." << endl;
    }
    else
    {
        for(Node<User>* tempU = users->getHeader(); tempU != nullptr; tempU = tempU->getNext())
        {
            for(Node<Playlist>* tempP = tempU->getItem().getPlaylists()->getHeader(); tempP != nullptr; tempP = tempP->getNext())
            {
                if(tempP->getItem().getSongs()->containsID(songID) != nullptr)
                {
                    int index = tempP ->getItem().getSongs()->findIndex(songID);
                    tempP->getItem().getSongs()->remove(index);
                }
            }
        }
        int pos = library->findIndex(songID);
        library->remove(pos);
        cout << "Removed song " << songID << "." << endl;
    }
}

/**
 * Print the song with its all information incıding ID, name and artists
 */
void BilkentBeats::printSongs() const
{
    if(library->getLength() == 0)
    {
        cout << "Cannot print songs. There is no song in the music library." << endl;
    }
    else
    {
        cout << "Music Library:" << endl;
        Node<Song>* curPtr = library->getHeader();
        for(int i = 1; i <= library->getLength(); i++)
        {
            cout << "Song " << curPtr->getItem().getID() << " : " << curPtr->getItem().getName() << " - " <<  curPtr->getItem().getArtist() <<  endl;
            curPtr = curPtr->getNext();
        }
    }
}

/**
 * Add playlist to the specific user.
 * If the user has already posses to that platylist ID, warn and do not do any operation.
 * Print the result
 * @param userId user ID
 * @param playlistId playlist ID
 */
void BilkentBeats::addPlaylist(const int userId, const int playlistId)
{
    if(!users->containsID(userId))
    {
        cout << "Cannot add playlist. There is no user with ID " << userId << "." << endl;
    }
    else if(playlistExist(playlistId))
    {
        cout << "Cannot add playlist. There is a user having a playlist with ID "<< playlistId << "." << endl;
    }
    else
    {
        Playlist* newPlaylist = new Playlist(playlistId);
        Node<User>* targetUser = users->findNode( userId);
        targetUser->getItem().getPlaylists()->insert(*newPlaylist);
        cout << "Added playlist " + to_string(playlistId) << " to user "<< to_string(userId) << "." << endl;
        delete newPlaylist;
    }
}

/**
 * remove given playlist ID from the user
 * If the user does not exist or the plşaylist ID is not exist on the users's all playlists warn
 * Print the result
 * @param userId unique user ID
 * @param playlistId  unique playlist ID
 */
void BilkentBeats::removePlaylist(const int userId, const int playlistId)
{
    Node<User>* targetUser = users->findNode(userId);
    if(targetUser == nullptr)
    {
        cout << "Cannot remove playlist. There is no user with ID " << userId << "." << endl;
    }
    else if(targetUser->getItem().getPlaylists()->containsID(playlistId) == nullptr)
    {
        cout << "Cannot remove playlist. User " << userId << " does not have a playlist with ID " << playlistId << "." << endl;
    }
    else
    {
        Node<Playlist>* targetPlaylist = targetUser->getItem().getPlaylists()->findNode(playlistId);
        int index = targetUser->getItem().getPlaylists()->findIndex(playlistId);
        targetPlaylist->getItem().getSongs()->clear();
        delete targetPlaylist->getItem().getSongs();
        targetUser->getItem().getPlaylists()->remove(index);
        cout << "Removed playlist " << playlistId << " from user " << userId << "." << endl;
    }
}

/**
 * Add given song to the given playlist
 * If the playlist has already this song warn and do not do any operation
 * Print the result
 * @param playlistId unique playlist ID
 * @param songId unique song ID
 */
void BilkentBeats::addSongToPlaylist(const int playlistId, const int songId)
{
    Node<Playlist>* targetPlaylist = playlistExist(playlistId);
    if(targetPlaylist == nullptr)
    {
        cout << "Cannot add song. There is no playlist with ID " << playlistId << "." << endl;
    }
    else if(targetPlaylist->getItem().getSongs()->containsID(songId) != nullptr)
    {
        cout << "Cannot add song. The playlist already contains song " << songId << "." << endl;
    }
    else if(library->containsID(songId) == nullptr)
    {
        cout << "Cannot add song. There is no song with ID " << songId << "." << endl;
    }
    else
    {
        Node<Song>* targetSong = library->findNode(songId);
        targetPlaylist->getItem().getSongs()->insert(targetSong->getItem(), targetPlaylist->getItem().getSongs()->getLength() + 1);
        cout << "Added song " << songId << " to playlist " << playlistId << "." << endl;
    }
}

/**
 * remove given song form the given playlist
 * ID the playlist does not exist or the song does not located in the playlist warn and do not do any operation.
 * Print the result
 * @param playlistId unique playlist ID
 * @param songId unique song ID
 */
void BilkentBeats::removeSongFromPlaylist(const int playlistId, const int songId)
{
    Node<Playlist>* target = playlistExist(playlistId);
    if(target == nullptr)
    {
        cout << "Cannot remove song. There is no playlist with ID " << playlistId << "." << endl;
    }
    else
    {
        if(target->getItem().getSongs()->containsID(songId) == nullptr)
        {
            cout << "Cannot remove song. There is no song " << songId << " in playlist " << playlistId<< "." << endl;
        }
        else
        {
            int index = target->getItem().getSongs()->findIndex(songId);
            target->getItem().getSongs()->remove(index);
            cout << "Removed song "<< songId << " from playlist " << playlistId << "." << endl;
        }
    }
}

/**
 * print all sonf locates in the playlist.
 * @param playlistId unique playlist ID
 */
void BilkentBeats::printSongsInPlaylist(const int playlistId) const
{

    Node<Playlist>* target = playlistExist(playlistId);
    if(target == nullptr)
    {
        cout << "Cannot print songs. There is no playlist with ID " << playlistId << "." << endl;
    }
    else
    {

        if(target->getItem().getSongs()->getLength() == 0)
        {
            cout << "There are no songs to show in playlist " << playlistId << "." << endl;
        }
        else
        {
            Node<Song>* curPtr = target->getItem().getSongs()->getHeader();
            cout << "Songs in playlist " << playlistId << ":" << endl;

            for(int i = 1; i <= target->getItem().getSongs()->getLength(); i++)
            {
                cout << "Song " << curPtr->getItem().getID() << " : " << curPtr->getItem().getName() << " - " << curPtr->getItem().getArtist() << endl;
                curPtr = curPtr->getNext();
            }
        }
    }
}

/**
 * Check whether the playlist ID is recorded any of the user's list
 * @param playlistID unique playlist ID
 * @return  playlist node
 */
Node<Playlist>* BilkentBeats::playlistExist(int playlistID) const
{
    if(users->getLength() == 0)
    {
        return nullptr;
    }
    else
    {
        Node<User>* curPtr = users->getHeader();
        for(int i  = 1; i <= users->getLength(); i++)
        {
            Node<Playlist>* target = curPtr->getItem().getPlaylists()->containsID(playlistID);
            if(target != nullptr)
            {
                return target;
            }
            curPtr = curPtr->getNext();
        }
    }
    return nullptr;
}

/**
 * gives the user's  playlist IDs
 * @param user user object
 * @return playlist ID
 */
string BilkentBeats::printPlaylistID(User user) const
{
    if(user.getPlaylists()->getLength() == 0)
    {
        return "None";
    }

    Node<Playlist>* curPtr = user.getPlaylists()->getHeader();
    string result = "[" + to_string(curPtr->getItem().getID()) ;
    curPtr = curPtr->getNext();
    for(int i = 2; i <= user.getPlaylists()->getLength(); i++)
    {
        result += "," + to_string(curPtr->getItem().getID());
        curPtr = curPtr->getNext();
    }
    return result + "]";
}

/**
 * I think I do not need this method!!!!
 * @param songID unique song ID
 * @return true if song stored in any of the playlist
 */
bool BilkentBeats::songExistInPlaylist(int songID)
{
    for(Node<User>* tempU = users->getHeader(); tempU != nullptr; tempU = tempU->getNext())
    {
        for(Node<Playlist>* tempP = tempU->getItem().getPlaylists()->getHeader(); tempP != nullptr; tempP = tempP->getNext() )
        {
            if(tempP->getItem().getSongs()->containsID(songID))
            {
                return true;
            }
        }
    }
    return false;
}