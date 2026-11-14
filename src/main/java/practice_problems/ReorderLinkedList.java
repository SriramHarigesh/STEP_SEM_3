public class ReorderLinkedList{
static class Node{
int data;
Node next;
Node(int data){this.data=data;}
}
static void reorderList(Node head){
if(head==null||head.next==null) return;
Node slow=head;
Node fast=head;
while(fast.next!=null&&fast.next.next!=null){
slow=slow.next;
fast=fast.next.next;
}
Node second=slow.next;
slow.next=null;
Node previous=null;
while(second!=null){
Node next=second.next;
second.next=previous;
previous=second;
second=next;
}
Node first=head;
second=previous;
while(second!=null){
Node firstNext=first.next;
Node secondNext=second.next;
first.next=second;
second.next=firstNext;
first=firstNext;
second=secondNext;
}
}
static void display(Node head){
while(head!=null){
System.out.print(head.data);
if(head.next!=null) System.out.print(" -> ");
head=head.next;
}
System.out.println();
}
public static void main(String[] args){
Node head=new Node(1);
head.next=new Node(2);
head.next.next=new Node(3);
head.next.next.next=new Node(4);
head.next.next.next.next=new Node(5);
reorderList(head);
display(head);
}
}
