#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define A sizeof(struct student)

struct student
{
	long long num;
	char name[10];
	float yu;
	float shu;
	float ying;
	float average;
	struct student*next;
};

struct student *head = NULL;
struct student *head1 = NULL;

void add();

void get(struct student*a,int n);

void put(struct student*a);

struct student *create(struct student*p1);

struct student *create1(struct student*p1);

void watch(struct student*a);

struct student*findmax(struct student*p);

struct student*find(long long n);

void delenum(long long n);

void edit(long long n);

void sort();

void damage(struct student*p);

char x1[10]="学号";
char x2[10]="姓名";
char x3[10]="语文";
char x4[10]="数学";
char x5[10]="英语";

int main()
{
	while(1)
	{
		printf("==============学生成绩管理系统================\n");
		printf("\n");
		printf("\t【1】添加数据\n\n");
		printf("\t【2】修改数据\n\n");
		printf("\t【3】删除数据\n\n");
		printf("\t【4】浏览数据\n\n");
		printf("\t【5】排序数据\n\n");
		printf("\t【6】清空数据\n\n");
		printf("\t【7】查找数据\n\n");
		printf("\t【0】退出系统\n\n");
		printf("\n");
		printf("===================================================\n");
		printf("请输入待操作功能的数字：");
		int a;
		long long b;
		scanf("%d",&a);
		system("cls");
		switch(a)
		{
		case 1:
			add();
			system("cls");
			watch(head);
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 2:
			if(head==NULL)
				printf("没有数据可以编辑\n");
			else
			{
				watch(head);
				printf("请输入想修改的学生的学号");
				scanf("%lld",&b);
				system("cls");
				edit(b);
				watch(head);
			}
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 3:
			if(head==NULL)
				printf("没有数据可以编辑\n");
			else
			{
				watch(head);
				printf("请输入删除的学生的学号");
				scanf("%lld",&b);
				system("cls");
				delenum(b);
				watch(head);
			}
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 4:
			watch(head);
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 5:
			sort();
			system("cls");
			watch(head);
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 6:
			damage(head);
			//watch(head1);
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 7:
			if(head==NULL)
				printf("没有数据可以编辑\n");
			else
			{
				printf("请输入想查找的学生的学号");
				scanf("%lld",&b);
				put(find(b));
			}
			printf("输入任意键继续");
			getchar();
			getchar();
			system("cls");
			break;
		case 0:
			printf("感谢你的使用");
			exit(0);
			break;
		}
	}
}



void add()
{
	struct student*p1,*p2;
	static int n=1;
	while(1)
	{
		p1=(struct student*)malloc(A);
		get(p1,n);
		if(p1->num==0)
			break;
		head=create(p1);
		n++;
	}
}



void get(struct student*a,int n)
{
	printf("请输入第%d个同学的%s(输入0则停止输入):",n,x1);
	scanf("%lld",&a->num);
	if(a->num==0)
		return;
	printf("请输入第%d个同学的%s:",n,x2);
	scanf("%s",&a->name);
	printf("请输入第%d个同学的%s:",n,x3);
	scanf("%f",&a->yu);
	printf("请输入第%d个同学的%s:",n,x4);
	scanf("%f",&a->shu);
	printf("请输入第%d个同学的%s:",n,x5);
	scanf("%f",&a->ying);
	a->average=(a->yu+a->shu+a->ying)/3;
}



void put(struct student*a)
{
	if(a==NULL)
		return;
	printf("%s为%lld的同学\n",x1,a->num);
	printf("%s:%s ",x2,a->name);
	printf("%s:%.1f ",x3,a->yu);
	printf("%s:%.1f ",x4,a->shu);
	printf("%s:%.1f ",x5,a->ying);
	printf("平均分:%.1f\n",a->average);
}



void sort()
{
	struct student*a,*b,*c;
	if(head==NULL)
		return;
	while(head!=NULL)
	{
		a=findmax(head);
		b=(struct student*)malloc(A);
		*b=*a;
		create1(b);
		delenum(a->num);
	}
	head=head1;
	head1=NULL;
}



struct student *create(struct student*p1)
{
	struct student *p2;
	if (head == NULL)
	{
		head = p1;
		p1->next = NULL;
	}
	else
	{
		for(p2 = head; p2->next != NULL; p2 = p2->next);
		p2->next = p1;
		p1->next = NULL;
	}
	return head;
}



struct student *create1(struct student*p1)
{
	struct student *p2;
	if (head1 == NULL)
	{
		head1 = p1;
		p1->next = NULL;
	}
	else
	{
		for(p2 = head1; p2->next != NULL; p2 = p2->next);
		p2->next = p1;
		p1->next = NULL;
	}
	return head1;
}




void watch(struct student*a)
{
	if(a==NULL)
		printf("没有数据可以输出\n\n");
	while(a!=NULL)
	{
		printf("%s:%lld",x1,a->num);
		printf("%s:%s ",x2,a->name);
		printf("%s:%.1f ",x3,a->yu);
		printf("%s:%.1f ",x4,a->shu);
		printf("%s:%.1f ",x5,a->ying);
		printf("平均分:%.1f\n\n",a->average);
		a=a->next;
	}
	printf("\n\n");
}



struct student*findmax(struct student*p)
{
	struct student*a,*b;
	a=b=p;
	while(b!=NULL)
	{
		if(b->num>a->num)
			a=b;
		b=b->next;
	} 
	return a;
}



struct student*find(long long n)
{
	struct student*a;
	a=head;
	while(1)
	{
		if(a==NULL)
			break;
		if(a->num==n)
			break;
		a=a->next;
	}
	if(a==NULL)
	{
		printf("系统中不存在此学号\n");
		return;
	}
	return a;
}



void delenum(long long n)
{
	struct student*a,*b;
	a=b=head;
	while(1)
	{
		if(a->num==n||a==NULL)
			break;
		b=a;
		a=a->next;
	}
	if(a==NULL)
		printf("系统中不存在此学号\n");
	else
	{
		if(a==head)
		{
			head=a->next;
		}
		else if(a->next==NULL)
		{
			b->next=NULL;
		}
		else
		{
			b->next=a->next;
		}
		free(a);
	}
}



void edit(long long n)
{
	struct student*a;
	int b;
	a=find(n);
	if(a==NULL)
		return;
	while(1)
	{
		put(a);
		printf("==============想修改那个数据？===========\n");
		printf("\n");
		printf("【1】学号\n");
		printf("【2】姓名\n");
		printf("【3】语文\n");
		printf("【4】数学\n");
		printf("【5】英语\n");
		printf("【6】平均分\n");
		printf("【0】退出\n");
		printf("\n");
		printf("========================================\n");
		printf("请输入待操作功能的数字：");
		scanf("%d",&b);
		switch(b)
		{
			case 1:
				printf("修改后的数据是");
				scanf("%lld",&a->num);
				break;
			case 2:
				printf("修改后的数据是");
				scanf("%s",&a->name);
				break; 
			case 3:
				printf("修改后的数据是");
				scanf("%f",&a->yu);
				break;
			case 4:
				printf("修改后的数据是");
				scanf("%f",&a->shu);
				break;
			case 5:
				printf("修改后的数据是");
				scanf("%f",&a->ying);
				break;
			case 6:
				printf("修改后的数据是");
				scanf("%f",&a->average);
				break;
			case 0:
				system("cls");
				return;
				break;
		}
		system("cls");
		printf("修改后的数据为\n\n");
		put(a);
		printf("输入任意键继续");
		getchar();
		system("cls");
	}
}
void damage(struct student*p)
{
	struct student*a;
	if(head==NULL)
	{
		printf("没有数据可以删除\n\n");
		return;
	}
	while(head!=NULL)
	{
		a=findmax(head);
		delenum(a->num);
	}
	printf("清空完成\n\n");
}
