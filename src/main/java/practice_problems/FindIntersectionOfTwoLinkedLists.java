public class FindIntersectionOfTwoLinkedLists{
static class Node{
int data;
Node next;
Node(int data){this.data=data;}
}
static Node findIntersection(Node a,Node b){
Node first=a;
Node second=b;
while(first!=second){
first=first==null?b:first.next;
second=second==null?a:second.next;
}
return first;
}
public static void main(String[] args){
Node common=new Node(8);
common.next=new Node(4);
common.next.next=new Node(5);
Node a=new Node(4);
a.next=new Node(1);
a.next.next=common;
Node b=new Node(5);
b.next=new Node(6);
b.next.next=new Node(1);
b.next.next.next=common;
Node result=findIntersection(a,b);
if(result==null) System.out.println("null");
else System.out.println("Node with value "+result.data);
}
}
