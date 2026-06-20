#include<stdio.h>
#include<stdlib.h>
struct node
{
	float num;
	int exp;
	struct node*next;
};
struct node*Init()
{
	node*head;
	head = (struct node*)malloc(sizeof(node));
	head->next=NULL;
	return head;
}
