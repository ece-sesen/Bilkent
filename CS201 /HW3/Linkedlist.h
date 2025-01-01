///Ece ŞEŞEN, CS201-03, ID: 22201637

/**
 * This is linkedlist class
 * It contains linkedlist methods including insert, remove, getNode, get length
 * @author ECE ŞEŞEN
 * @version 10.12.2024
 */
#ifndef HW3_LINKEDLIST_H
#define HW3_LINKEDLIST_H
#include "Node.h"

template<class ItemType>
class LinkedList
{
private:
    Node <ItemType>* headPtr;
    int itemCount;
    Node<ItemType>* getNodeAt(int position) const;

public:
    LinkedList();
    LinkedList(const LinkedList<ItemType>& aList);
    virtual ~LinkedList();

    bool isEmpty();
    int getLength();

    void insert(const ItemType& newEntry);
    bool insert(const ItemType& newEntry, int position);

    bool remove(int position);
    void clear();
    ItemType getEntry(int position) const;
    ItemType replace(int position, const ItemType& newEntry);

    int insertWhere(const ItemType& newEntry);
    Node<ItemType>* containsID(int searchID);
    Node<ItemType>* findNode(int id);
    int findIndex(int id);

    Node<ItemType>* getHeader();
};

template<class ItemType>
LinkedList<ItemType>::LinkedList()
{
    headPtr = nullptr;
    itemCount = 0;
}

template<class ItemType>
LinkedList<ItemType>::LinkedList(const LinkedList<ItemType> &aList)
{

}

#include "Linkedlist.h"
template<class ItemType>
bool LinkedList<ItemType>::isEmpty()
{
    return itemCount == 0;
}

template<class ItemType>
int LinkedList<ItemType>::getLength()
{
    return itemCount;
}

template<class ItemType>
ItemType LinkedList<ItemType>::getEntry(int position) const
{
    bool ableToGet = (position >= 1) && (position <= itemCount);
    if(ableToGet)
    {
        Node<ItemType>* nodePtr = getNodeAt(position);
        return nodePtr->getItem();
    }
    return ItemType(); ///exception handling
}

template<class ItemType>
Node<ItemType>* LinkedList<ItemType>::getNodeAt(int position) const
{
    bool isAble = (position >= 1) && (position <= itemCount);
    if(isAble)
    {
        Node <ItemType>* curPtr = headPtr;
        for(int skip  = 1; skip < position; skip++)
        {
            curPtr = curPtr-> getNext();
        }
        return curPtr;
    }
    return nullptr;
}

template<class ItemType>
bool LinkedList<ItemType>::insert(const ItemType &newEntry, int position)
{
    bool ableToInsert = (position >= 1) && (position <= itemCount + 1);
    if(ableToInsert)
    {
        Node<ItemType>* newNodePtr = new Node<ItemType>(newEntry);
        if(position == 1)
        {
            newNodePtr->setNext(headPtr);
            headPtr = newNodePtr;
        }
        else
        {
            Node<ItemType>* prevPtr = getNodeAt(position - 1);
            newNodePtr->setNext(prevPtr->getNext());
            prevPtr->setNext(newNodePtr);
        }
        itemCount++;
    }
    return ableToInsert;
}

template<class ItemType>
void LinkedList<ItemType>::insert(const ItemType& newEntry)
{
    int newPosition = insertWhere(newEntry);
    insert(newEntry, newPosition);

}

template<class ItemType>
bool LinkedList<ItemType>::remove(int position)
{
    bool ableToRemove = (position >= 1) && (position <= itemCount);
    if(ableToRemove)
    {
        Node<ItemType>* curPtr = nullptr;
        if(position == 1)
        {
            curPtr = headPtr;
            headPtr = headPtr->getNext();
        }
        else
        {
            Node<ItemType>* prevPtr = getNodeAt(position - 1);
            curPtr = prevPtr->getNext();
            prevPtr->setNext(curPtr->getNext());
        }
        curPtr->setNext(nullptr);
        delete curPtr;
        curPtr = nullptr;
        itemCount--;
    }
    return ableToRemove;
}

template<class ItemType>
void LinkedList<ItemType>::clear()
{
    while(itemCount != 0)
    {
        remove(1);
    }
}

template<class ItemType>
LinkedList<ItemType>::~LinkedList()
{
    clear();
}

template<class ItemType>
ItemType LinkedList<ItemType>::replace(int position, const ItemType &newEntry)
{

    Node <ItemType>* targetNode = getNodeAt(position);
    if(targetNode != nullptr)
    {
        ItemType oldValue = targetNode->getItem();
        targetNode->setItem(newEntry);
        return oldValue;
    }
    return ItemType();
}


template<class ItemType>
int LinkedList<ItemType>::insertWhere(const ItemType& newEntry)
{
    Node<ItemType>* temp = headPtr;
    for(int i = 1; i <= itemCount; i++)
    {
        if(temp->getItem().getID() >= newEntry.getID() )
        {
            return i;
        }
        temp = temp->getNext();
    }
    return itemCount + 1;

}


template<class ItemType>
Node<ItemType>* LinkedList<ItemType>::containsID(int searchID)
{
    for(Node<ItemType>* temp = headPtr; temp != nullptr; temp = temp->getNext())
    {
        if(temp->getItem().getID() == searchID )
        {
            return temp;
        }
    }
    return nullptr;
}

template<class ItemType>
Node<ItemType> *LinkedList<ItemType>::findNode(int id)
{
    for(Node<ItemType>* curPtr = headPtr; curPtr != nullptr; curPtr = curPtr->getNext() )
    {
        if(curPtr->getItem().getID() == id)
        {
            return curPtr;
        }
    }
    return nullptr;
}

template<class ItemType>
int LinkedList<ItemType>::findIndex(int id)
{
    Node<ItemType>* curPtr = headPtr;
    for(int i = 1; i <= itemCount; i++)
    {
        if(curPtr->getItem().getID() == id)
        {
            return i;
        }
        curPtr = curPtr->getNext();
    }
    return -1;
}

template<class ItemType>
Node<ItemType>* LinkedList<ItemType>::getHeader()
{
    return headPtr;
}

#endif //HW3_LINKEDLIST_H
