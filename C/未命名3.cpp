#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define OK 1
#define NO 0
#define DATA_MAX_SIZE 20
 
typedef int Status;
typedef char Excelelem;
typedef int Numelem;
typedef double db;
typedef struct Node
{
	db xishu;
	db mi;
	struct Node *next;
}LNode,*LinkList;
 
typedef struct
{
	Excelelem name[100];
	Numelem length;
	LinkList next;
}HeadList,*HeadLinkList;

LinkList Init(Numelem *n);
HeadLinkList HeadInit(char a[]);
LinkList DATA_UPPER_FIND(LinkList Head,db ans);
void DATA_ADD(HeadLinkList A,HeadLinkList B,HeadLinkList C);
void DATA_SUB(HeadLinkList A,HeadLinkList B,HeadLinkList C);
void DATA_Free(HeadLinkList Head);
void DATA_cout(HeadLinkList Head);
 
int main()
{
	HeadLinkList A,B,C;
	Numelem n;
	printf("**********start!**********\n");
	A=HeadInit("A多项式");
	printf("请输入%s:\n",A->name);
	A->next=Init(&A->length);
	B=HeadInit("B多项式");
	printf("请输入%s:\n",B->name);
	B->next=Init(&B->length);
	C=HeadInit("C多项式");
	C->next=NULL;
	printf("请输出%s的内容:\n",A->name);
	DATA_cout(A);
	printf("请输出%s的内容\n",B->name);
	DATA_cout(B);
	printf("%s+%s:\n",A->name,B->name);
	DATA_ADD(A,B,C);
	DATA_cout(C);
	printf("%s-%s:\n",A->name,B->name);
	DATA_SUB(A,B,C);
	DATA_cout(C);
	DATA_Free(A);DATA_Free(B);DATA_Free(C);
	free(A),free(B),free(C);
	printf("End\n");
	return 0;
}
 
LinkList DATA_UPPER_FIND(LinkList Head,db ans)
{
	if(Head->next == NULL || Head->next->mi > ans)
		return Head;
	return DATA_UPPER_FIND(Head->next,ans);
}
 
LinkList Init(Numelem *n)
{
	LinkList Head,p,q;
	Numelem i=0;
	db a,b;
	Head=NULL;
	while(~scanf("%lf",&a) && a!=0.0)
	{
		scanf("%lf",&b);
		q=(LinkList)malloc(sizeof(LNode));
		q->xishu=a;
		q->mi=b;
		if(*n==0 || Head->mi>b) q->next=Head,Head=q;
		else
		{
			p=DATA_UPPER_FIND(Head,b);
			q->next=p->next;
			p->next=q;
		}
		*n++;
	}
	return Head;
}
 
HeadLinkList HeadInit(char *a)
{
	HeadLinkList Head;
	Head=(HeadLinkList)malloc(sizeof(HeadList));
	strcpy(Head->name,a);
	Head->length=0;
	return Head;
}
 
void DATA_ADD(HeadLinkList A,HeadLinkList B,HeadLinkList C)
{
	LinkList qa=A->next,qb=B->next,p,q=NULL;
	DATA_Free(C);
	while(qa || qb)
	{
		p=(LinkList)malloc(sizeof(LNode));
		if(qb==NULL || qa && qa->mi<qb->mi)
		{
			*p=*qa;
			qa=qa->next;
		}
		else if(qa==NULL || qb && qa->mi>qb->mi)
		{
			*p=*qb;
			qb=qb->next;
		}
		else
		{
			p->xishu=qb->xishu+qa->xishu;
			p->mi=qb->mi;
			qa=qa->next;
			qb=qb->next;
		}
		if(q==NULL) p->next=q,C->next=q=p;
		else
			p->next=q->next,q->next=p,q=p;
		C->length++;
	}
}
 
void DATA_SUB(HeadLinkList A,HeadLinkList B,HeadLinkList C)
{
	LinkList qa=A->next,qb=B->next,p,q=NULL;
	DATA_Free(C);
	while(qa!=NULL || qb!=NULL)
	{
		p=(LinkList)malloc(sizeof(LNode));
		if(qb==NULL || qa && qa->mi<qb->mi)
		{
			*p=*qa;
			qa=qa->next;
		}
		else if(qa==NULL || qb && qa->mi>qb->mi)
		{
			*p=*qb;
			p->xishu*=-1;
			qb=qb->next;
		}
		else
		{
			*p=*qa;
			p->xishu-=qb->xishu;
			qa=qa->next;
			qb=qb->next;
			if(p->xishu==0)
			{
				free(p);
				continue;
			}
		}
		if(q==NULL) p->next=q,C->next=q=p;
		else
			q->next=p->next,q->next=p,q=p;
		C->length++;
	}
}
 
void DATA_Free(HeadLinkList Head)
{
	LinkList q=Head->next,p;
	while (q)
	{
		p=q;
		q=q->next;
		free(p);
	}
	Head->length=0;
	Head->next=NULL;
	return ;
}
 
void DATA_cout(HeadLinkList Head)
{
	LinkList q=Head->next;
	while(q)
	{
		if(q->xishu>0 && q!=Head->next)
			printf("+");
		printf("%.1lfx^(%.1lf)",q->xishu,q->mi);
		q=q->next;
	}
	printf("\n");
	return ;
}
