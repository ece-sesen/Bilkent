///Ece ŞEŞEN, CS201-03, ID: 22201637

#ifndef HW4_LISTQUEUE_H
#define HW4_LISTQUEUE_H

#include "Linkedlist.h"
#include <iostream>

using namespace std;
class Parsel;
template <class ItemType>
class ListQueue
{
private:
    LinkedList<ItemType>* listPtr;

public:
    ListQueue();
    ListQueue(const ListQueue& aQueue);
    ~ListQueue();

    bool isEmpty() const;
    bool enqueue(const ItemType& newEntry);
    bool dequeue();
    ItemType peekFront() const;

    Node<ItemType>* getStartPointer();
    LinkedList<ItemType>* getListPtr();

    void setListPtr(LinkedList<ItemType>* newPtr);

};

template<class ItemType>
ListQueue<ItemType>::ListQueue()
{
    listPtr = new LinkedList<Node<Parsel>>();
}

template<class ItemType>
ListQueue<ItemType>::ListQueue(const ListQueue<ItemType>& aQueue)
{
    listPtr = aQueue.listPtr;
}

template<class ItemType>
ListQueue<ItemType>::~ListQueue()
{
    if(listPtr != nullptr)
    {
        delete listPtr;
        listPtr = nullptr;
    }
}

template<class ItemType>
bool ListQueue<ItemType>::isEmpty() const
{
    return listPtr->isEmpty();
}

template<class ItemType>
bool ListQueue<ItemType>::enqueue(const ItemType& newEntry)
{
    return listPtr->insert(newEntry, listPtr->getLength() + 1);
}

template<class ItemType>
bool ListQueue<ItemType>::dequeue()
{
    return listPtr->remove(1);
}

template<class ItemType>
ItemType ListQueue<ItemType>::peekFront() const
{
    if(!isEmpty())
    {
        return  listPtr->getEntry(1);
    }
    return ItemType();
}

///Setter Methods
template<class ItemType>
void ListQueue<ItemType>::setListPtr( LinkedList<ItemType>* ptr)
{
    listPtr = ptr;
}

///Getter Methods
template<class ItemType>
Node<ItemType> *ListQueue<ItemType>::getStartPointer()
{
    return listPtr;
}

template<class ItemType>
LinkedList<ItemType>* ListQueue<ItemType>::getListPtr()
{
    return listPtr;
}

#endif //HW4_LISTQUEUE_H
