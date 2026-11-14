public class BusBayRing{
int[] buses;
int capacity;
int front;
int size;
BusBayRing(int capacity){
this.capacity=capacity;
buses=new int[capacity];
front=0;
size=0;
}
boolean isEmpty(){
return size==0;
}
boolean isFull(){
return size==capacity;
}
boolean insertFront(int bus){
if(isFull()) return false;
front=(front-1+capacity)%capacity;
buses[front]=bus;
size++;
return true;
}
boolean insertLast(int bus){
if(isFull()) return false;
int rear=(front+size)%capacity;
buses[rear]=bus;
size++;
return true;
}
boolean deleteFront(){
if(isEmpty()) return false;
front=(front+1)%capacity;
size--;
return true;
}
boolean deleteLast(){
if(isEmpty()) return false;
size--;
return true;
}
int getFront(){
if(isEmpty()) return -1;
return buses[front];
}
int getRear(){
if(isEmpty()) return -1;
int rear=(front+size-1)%capacity;
return buses[rear];
}
public static void main(String[] args){
BusBayRing b=new BusBayRing(3);
System.out.println(b.insertLast(101));
System.out.println(b.insertLast(102));
System.out.println(b.insertFront(100));
System.out.println(b.insertFront(99));
System.out.println(b.getRear());
System.out.println(b.isFull());
System.out.println(b.deleteLast());
System.out.println(b.insertFront(98));
System.out.println(b.getFront());
}
}
