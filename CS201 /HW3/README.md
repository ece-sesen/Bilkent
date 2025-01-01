# CS201 Homework 3

### Focused topic of the course: "Linkedlist" and "Node" implementations 

  In this homework, you will create a music streaming service called BilkentBeats, implemented
using "linked lists". 

  The music streaming service consists of users, songs, and playlists, each
identified by unique IDs across the system. Note that these ID types are independent of each
other. For instance, there cannot be more than one user with ID 6, but there can be a playlist
with ID 6 and a song with ID 6. They would be diﬀerent entities.

  In this system, you will store a linked list of users and a linked list of songs. For each user,
you will store a linked list of playlists. For each playlist, you will store a linked list of songs IDs.

  In your implementation, you MUST use sorted linked lists for storing the users, songs, and
playlists, and MUST use unsorted linked lists for storing the songs in playlists. 
Program should not have any memory leaks at the end.



