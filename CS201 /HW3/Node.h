///Ece ŞEŞEN, CS201-03, ID: 22201637
#ifndef HW3_NODE_H
#define HW3_NODE_H

template<class ItemType>
class Node
{
private:
    ItemType item;
    Node <ItemType>* next;
public:
    Node();
    Node(const ItemType& anItem);
    Node(const ItemType& anItem, Node<ItemType>* nextNode );
    ~Node();

    void setItem(const ItemType anItem);
    void setNext(Node <ItemType>* nextNodePtr);

    ItemType getItem() const;
    Node<ItemType>* getNext() const;
};
template<class ItemType>
Node<ItemType>::Node()
{
    next = nullptr;
}

template<class ItemType>
Node<ItemType> ::Node(const ItemType& anItem)
{
    item = anItem;
    next = nullptr;
}

template<class ItemType>
Node<ItemType>::Node(const ItemType &anItem, Node<ItemType> *nextNode)
{
    item = anItem;
    next = nextNode;
}

template<class ItemType>
Node<ItemType>::~Node()
{
}

template<class ItemType>
void Node<ItemType>::setItem(const ItemType anItem)
{
    item = anItem;
}

template<class ItemType>
void Node<ItemType>::setNext(Node<ItemType> *nextNodePtr)
{
    next = nextNodePtr;
}

template<class ItemType>
ItemType Node<ItemType>::getItem() const
{
    return item;
}

template<class ItemType>
Node<ItemType>* Node<ItemType>::getNext() const
{
    return next;
}
#endif //HW3_NODE_H
