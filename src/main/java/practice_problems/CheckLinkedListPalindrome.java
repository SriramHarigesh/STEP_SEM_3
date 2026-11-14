public class CheckLinkedListPalindrome{
static class Node{
int data;
Node next;
Node(int data){this.data=data;}
}
static boolean isPalindrome(Node head){
if(head==null||head.next==null) return true;
Node slow=head;
Node fast=head;
while(fast.next!=null&&fast.next.next!=null){
slow=slow.next;
fast=fast.next.next;
}
Node current=slow.next;
Node previous=null;
while(current!=null){
Node next=current.next;
current.next=previous;
previous=current;
current=next;
}
Node first=head;
Node second=previous;
while(second!=null){
if(first.data!=second.data) return false;
first=first.next;
second=second.next;
}
return true;
}
public static void main(String[] args){
Node head=new Node(1);
head.next=new Node(2);
head.next.next=new Node(2);
head.next.next.next=new Node(1);
System.out.println(isPalindrome(head));
}
}
