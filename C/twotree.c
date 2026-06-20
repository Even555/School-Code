/*利用队列实现二叉树的层次遍历*/
#include<stdio.h>
#define maxsize 100
#define NULLData 0
typedef char datatype;
/*二叉链表类型定义*/
typedef struct Binnode
{
    datatype data;                   /*数据域*/
    struct BinNode* lchild,*rchild;  /*指向左、右孩子的指针*/
}BinNode,*Bintree;
/*循环队列类型定义*/
typedef struct seqqueue
{
    Bintree data[maxsize];          /*二叉链表类型的指针数组*/
    int front,rear;                 /*队首指针、队尾指针*/
}SeqQue,*SeQue;
/*按先序创建二叉树*/
Bintree CreateTree(Bintree T)
{
    char ch;
    scanf("%c",&ch);
    if(ch=='#')
        return 0;
    else
    {
        T=(Bintree)malloc(sizeof(BinNode));
        T->data=ch;
        T->lchild=CreateTree(T->lchild);/*创建左子树*/
        T->rchild=CreateTree(T->rchild);/*创建右子树*/

    }
    return T;
}
/*初始化循环队列*/
void InitQue(SeQue Q)
{
    Q->front=Q->rear=0;
}
/*判队列空*/
int EmptyQue(SeQue Q)
{
    if(Q->front==Q->rear)
        return 1;
    else
        return 0;
}
/*入队列*/
int EnQue(SeQue Q,Bintree T)
{
    if((Q->rear+1)%maxsize==Q->front)
    {
        printf("队列已满！\n");      /*队列满，入队失败。*/
        return 0;
    }
    else
    {
        Q->rear=(Q->rear+1)%maxsize;
        Q->data[Q->rear]=T;
        return 1;                   /*入队列成功*/
    }
}
/*出队列*/
int OutQue(SeQue Q)
{
    if(EmptyQue(Q))                 /*判断队列是否为空*/
    {
        printf("队列空！\n");
        return 0;
    }
    else
    {
        Q->front=(Q->front+1)%maxsize;/*不为空，出队列。*/
        return 1;
    }
}
/*取队列首元素*/
Bintree Gethead(SeQue Q)
{
    if(EmptyQue(Q))                  /*判断队列是否为空*/
        return NULLData;
    else
        return Q->data[(Q->front+1)%maxsize];
}
/*层次遍历二叉树*/
void Levelorder(Bintree T)
{
    Bintree p;
    SeqQue Q;
    InitQue(&Q);                    /*初始化队列*/
    if(T!=NULL)
    {
        EnQue(&Q,T);                /*根结点入队列*/
        while(!EmptyQue(&Q))
        {
            p=Gethead(&Q);
            OutQue(&Q);             /*结点出队列*/
            printf("%c ",p->data);  /*被访问结点*/
            if(p->lchild!=NULL)
                EnQue(&Q,p->lchild);/*左孩子结点入队列*/
            if(p->rchild!=NULL)
                EnQue(&Q,p->rchild);/*右孩子结点入队列*/
        }
    }
}
main()
{
    Bintree t;
    printf("请按先序的方式输入二叉树的结点元素（注：#表示节点为空）:");
    t=CreateTree(t);
    printf("按层次遍历输出:");
    Levelorder(t);
}
