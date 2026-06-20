#include<stdio.h>
#include<stdlib.h>
#define DataType int
typedef struct node {
	DataType data;
	struct node *left;
	struct node *right;
}BiTreeNode;
int Insert(BiTreeNode **root,DataType item)
{
	BiTreeNode *p,*parent =NULL,*current;
	current=*root;
	while(current!=NULL)
	{
		if(current->data==item)
			return 0;
		parent=current;
		if(current->data<item)
			current=current->right;
		else
			current=current->left;
	}
	p=(BiTreeNode*)malloc(sizeof(BiTreeNode));
	p->data=item;
	p->left=NULL;
	p->right=NULL;
	if(parent==NULL)
		*root=p;
	else if(item<parent->data)
		parent->left=p;
	else
		parent->right=p;
	return 1;
	}

void creatTREE(BiTreeNode **tree)
{
	int a[50],i=1,k;
	a[0]=rand()%601+200;
	k=Insert(tree,a[0]);
	while(i<50){
		a[i]=rand()%601+200;
		 if(Insert(tree,a[i])==0)
			 continue;
		 else
			 i++;
	}
}

void Midprint(BiTreeNode *tree)
{
	if(tree!=NULL)
	{
	Midprint(tree->left);
	printf("%d ",tree->data);
	Midprint(tree->right);
	}
	
}

BiTreeNode *Search(BiTreeNode *root ,DataType item,int *pos,int *times)
{
	BiTreeNode *p,*proot;
	p=root;
	proot=root;
	*pos=0;
	*times=1;
	if(root!=NULL)
	{
	while(p!=NULL)
	{
		if(p->data==item)
			return proot;
		proot=p;
		if(item>p->data)
		{
			p=p->right;
			*pos=1;
		}
		else
		{
			p=p->left;
			*pos=-1;
		}
		(*times)++;
	}
	*pos=-2;
	}
}

void Delete(BiTreeNode* tree ,DataType num)
{
	int times,pos;
	BiTreeNode *preNode,*preselect,*select,*p;
	preNode=Search(tree,num,&pos,&times);
	if(pos!=-2)
	{
		if(pos==-1)
		p=preNode->left;
		else if(pos==1)
		p=preNode->right;
		else
		p=preNode;
		if(p->left==NULL&&p->right==NULL)
		{
			free(p);
			if(pos==-1)
			preNode->left=NULL;
			else
			preNode->right=NULL;
		}
		else if(p->left==NULL)
		{
			if(pos==-1)
			preNode->left=p->right;
			else
			preNode->right=p->right;
			free(p);
		}
		else if(p->right==NULL)
		{
			if(pos==-1)
			preNode->left=p->left;
			else
			preNode->right=p->left;
			free(p);
		}
		else
		{
			select=preselect=p->right;
			while(select->left!=NULL)
			{
				preselect=select;
				select=select->left;
			}
			if(pos==0)
			{
				if(p->right==select)
					p->right=select->right;
				else{
				p->data=select->data;
				preselect->left=select->right;
				}
				free(select);
			}
			else
			{
			if(p->right==select)
			{
				select->left=p->left;
			}
			else
			{
			preselect->left=select->right;
			select->left=p->left;
			select->right=p->right;
			}
			if(pos==1)
				preNode->right=select;
			else if(pos==-1)
				preNode->left=select;
			free(p);
			}
		}
	}
	else if(pos==-2)
	{
		printf("树中未找到该结点\n");
	}
}

int main()
{
	BiTreeNode *tree=NULL,*p;
	int k,num,index,times;
	creatTREE(&tree);
	printf("中序排序结构如下：\n");
	Midprint(tree);
	printf("\n");
	while(1){
	printf("1、查找整数 \n2、删除整数 \n3、遍历显示\n");
	scanf("%d",&k);
	switch(k)
	{
	case 1:
		printf("输入要查找的整数：\n");
		scanf("%d",&num);
		p=Search(tree ,num,&index,&times);
		if(index==1)
		printf("经过%d次查找找到了%d\n",times,p->right->data);
		else if(index==-1)
		printf("经过%d次查找找到了%d\n",times,p->left->data);
		else if(index==0)
		printf("经过%d次查找找到了%d\n",times,p->data);
		else
		printf("不存在此结点!\n");
		break;
	case 2:
		printf("输入要删除的整数：\n");
		scanf("%d",&num);
		Delete(tree ,num);
		break;
	case 3:
		Midprint(tree);
		printf("\n");
		break;
	default :
		printf("输入有误！");
		break;
	}
	}
return 0;
}
