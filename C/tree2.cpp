#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define OVERFLOW -1
#define num 100
#define OK 1
typedef int Status;
typedef char ElemType;

typedef struct node{
	ElemType data;
	struct node *lchild,*rchild;
}BinTNode,*BinTree;

//求叶子结点函数
int leaf_node_function(BinTree p)
{
    if(!p)
    {
        return 0;           //空二叉树的情况
    }
    else if(p->lchild == NULL && p->rchild == NULL)
    {
        return  + 1;
    }
    else
    {
        return leaf_node_function(p->lchild) + leaf_node_function(p->rchild);
    }
}  //因为我们用的递归是根结点开始的，所以必须把递归的函数放在else（非叶子结点这里）

//求非叶子结点函数
int Non_leaf_node_function(BinTree p)
{
    if(!p)
    {
        return 0;   //空二叉树
    }
    else if(p->lchild == NULL && p->rchild == NULL)
    {
        return 0;       //结点为叶子结点 = 左子树 + 右子树 = NULL
    }
    else
    {
        return Non_leaf_node_function(p->lchild) + Non_leaf_node_function(p->rchild) + 1;
    }
}

//打印非叶子结点
void Non_leaf_print(BinTree p)
{
    if(p != NULL)
    {
        if(!(p->lchild == NULL && p->rchild == NULL))     //为叶子结点的
        {
            printf("%c  ", p->data);
        }
        Non_leaf_print(p->lchild);
        Non_leaf_print(p->rchild);
    }
}

//打印叶子结点
void leaf_print(BinTree p)
{
    if(p != NULL)
    {
        if(p->lchild == NULL && p->rchild == NULL)
        {
            printf("%c  ", p->data);
        }
        leaf_print(p->lchild);
        leaf_print(p->rchild);
    }
}


Status CreateTree(BinTree &bt)
{//按照先序遍历次序地柜建立二叉树，
	//ABC##DE#G##F###
	char s;
	scanf("%c",&s);
	//如果想要依次输入，就要加getchar();
	if(s=='#')  bt=NULL;
	else
	{
		if(!(bt=(BinTree )malloc(sizeof(BinTNode))))  exit(OVERFLOW);
		bt->data=s;
		CreateTree(bt->lchild);
		CreateTree(bt->rchild);
	}	
	return OK;

}//CreateTree

Status PreOrder(BinTree bt)
{//前序遍历非递归算法
	BinTree ptr[20];int top=-1;
	while(bt||top!=-1){
		while(bt){
			printf("%c",bt->data);
			ptr[++top]=bt;
			bt=bt->lchild;
		
		}
		if(top!=-1){
			bt=ptr[top--];
			bt=bt->rchild;
		
		}
	
	}
	
return OK;
}

Status InOrder(BinTree bt)
{
	//二叉树中序遍历递归算法
/*	if(bt){                      
	InOrder(bt->lchild);         
	printf("%c",bt->data);		 
	InOrder(bt->rchild);         
	
	}
	return OK;
*/

//二叉树中序遍历非递归算法(利用堆栈实现)
/*
	Stack s;
	InitStack(s); BinTree p=bt;
	SElemType temp;
	while(p||!StackEmpty(s)){
		if(p){ 
			temp.ptr=p;
			Push(s,temp);p=p->lchild;
		}
		else
			{  if(!StackEmpty(s))
				{	
				Pop(s,temp);
				p=temp.ptr;
				printf("%c",p->data);
				p=p->rchild;
				}
			}	
		}
	return OK;
*/

//二叉树中序遍历非递归算法(利用数组辅助实现)
	BinTree ptr[20];int top=-1;
	while(bt||top!=-1){
		while(bt){
			ptr[++top]=bt;
			bt=bt->lchild;
		
		}
		if(top!=-1){
			bt=ptr[top--];
				printf("%c",bt->data);
			bt=bt->rchild;
		
		}
	
	}
	
	return OK;

}//InOrder

Status PostOrder(BinTree bt)
	{//二叉树后序遍历递归算法
	/*	if(bt){
		PostOrder(bt->lchild);
		PostOrder(bt->rchild);
		printf("%c",bt->data);
		}
		
		return OK;
	*/

//二叉树后序遍历非递归算法（利用数组模拟栈辅助实现）
	if(!bt)
	return OK;
	BinTree s[num];
	BinTree pre;
	int top=-1;
	while(bt||top!=-1){
		if(bt){//先将左边元素全部入栈
		s[++top]=bt;
		bt=bt->lchild;
		}
	else
		{
			bt=s[top];//取出栈顶元素
			if(bt->rchild==NULL||bt->rchild==pre)
			{
				printf("%c",bt->data);
				top--;
				pre=bt;
				bt=NULL;//当有一个元素被访问时，栈顶应该为他的双亲结点，当bt为NULL时下一次被取出来的结点恰好就是双亲
			}
			else
			{
				bt=bt->rchild;
			}
		}
	}
	return OK;
}//PostOrder

Status LevelOrder(BinTree bt)
{//二叉树层序遍历非递归算法（利用数组模拟队列辅助实现）
    if (bt== NULL)      return OK;
    BinTree s[num];
    int front, rear;
    front = rear = 0; 
    s[rear++] = bt; // 根结点入队
    while (front != rear) // 当队列非空时
    {    
		printf("%c",s[front]->data); // 输出队头元素结点信息 
        if (s[front]->lchild)
        {       
            s[rear++] = s[front]->lchild; // 若结点q存在左孩子，则将左孩子入队；
        }
        if (s[front]->rchild)
        {         
            s[rear++] = s[front]->rchild; // 若结点q存在右孩子，则将右孩子入队；
        }
		front++;
    }
	return OK;
}//LevelOrder

int size(BinTree bt)
{//统计二叉树中所有结点个数

	if(bt==NULL)
	{
		return 0;
	}
	return size(bt->lchild)+size(bt->rchild)+1;
}//size


int LeafCount(BinTree BT)
{//统计二叉树中叶子结点个数
	if(BT==NULL)return 0;
	else if(!BT->lchild&&!BT->rchild)
	{
	   return 1;
	}
	   return LeafCount(BT->lchild)+LeafCount(BT->rchild);
}//LeafCount

int Depth(BinTree bt)
{//求二叉树深度
	int right,left,h;
	if(!bt)
	return 0;
	else {
	left=Depth(bt->lchild);
	right=Depth(bt->rchild);
	if(left>=right)	h=left+1;
	else h=right+1;
	}
	return h;
}// Depth


Status Exchange(BinTree bt)
{//交换二叉树左右子树

	if(!bt||(!(bt->rchild)&&!(bt->lchild))) return OK;
	else
	{
		BinTree temp=bt->lchild;
		bt->lchild=bt->rchild;
		bt->rchild=temp;
		Exchange(bt->lchild);
		Exchange(bt->rchild);
	}
	return OK;
}// Exchange

/*图示：
        A					    A
	   /						 \
	  B			  Exchange		  B
	 / \          ------->		 / \
	c   D						D   C
	   / \					   / \
	  E   F					  F	  E
	   \					     /
	    G						G
*/

int main()
{		BinTree bt;int xz=1; int yz,sd;
		printf("二叉树的建立及其基本操作  \n");
		printf("==================================\n");
		printf("1.建立二叉树的存储结构    \n");
		printf("2.二叉树的基本操作     \n");
		//printf("3.交换二叉树的左右     \n");
		printf("0.退出系统    \n");
		printf("============================\n");
		while(xz)
		{	
			printf("请选择: (0-2)  \n");
			scanf("%d",&xz);  getchar();
			switch(xz)
			{//输入：ABC##DE#G##F###  输出： CBEGDFA
			case 1:printf("输入二叉树的先序遍历序列节点值：  \n");
				CreateTree(bt);
				printf("二叉树的链式存储结构建立完成！\n");
				printf("\n");break;
			case 2:
				//printf("该二叉树的前序序列是：");
				//PreOrder(bt);printf("\n");
				//printf("该二叉树的中序遍历序列是：");
				//InOrder(bt);printf("\n");
				printf("该二叉树的后序遍历序列是：");
				PostOrder(bt);printf("\n");	
				printf("该二叉树的层序遍历序列是：");
				LevelOrder(bt);
				printf("\n");
				//printf("该二叉树的结点的个数是: %4d\n",size(bt));
				//yz=LeafCount(bt);printf("叶子结点个数是:%4d\n",yz);
				sd=Depth(bt);printf("该二叉树的深度是:%4d\n",sd);
				printf("叶子节点为\n");
				leaf_print(bt);
				printf("\n");
				printf("非叶子节点为\n");
				Non_leaf_print(bt);
				printf("\n");
				printf("\n");break;
			/*case 3: 
				Exchange(bt);
				printf("该二叉树已交换左右子树！\n");
				printf("交换后该二叉树的前序序列是：");
				PreOrder(bt);printf("\n");
				printf("交换后该二叉树的中序遍历序列是：");
				InOrder(bt);printf("\n");
				printf("交换后该二叉树的后序遍历序列是：");
				PostOrder(bt);printf("\n");
				printf("交换后该二叉树的层序遍历序列是：");
				LevelOrder(bt);	printf("\n");
				printf("交换后该二叉树的结点的个数是: %4d\n",size(bt));
				yz=LeafCount(bt);printf("交换后叶子结点个数是:%4d\n",yz);
				sd=Depth(bt);printf("交换后该二叉树的深度是:%4d\n",sd);
				printf("\n");break;*/
			case 0:
				break;
			default:printf("输入错误！不在(0-2)范围内\n");break;
			}
		}
}
