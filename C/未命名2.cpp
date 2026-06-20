#include<stdio.h>
#include<stdlib.h>
#define MaxSize 50
typedef struct node* SeQuence;
struct node {
    char data[MaxSize];
    int Front, Rear; //Front队头，出队用，Rear队尾，入队用
};
//初始化
void InitQuence(SeQuence *L) {
    (*L) = (SeQuence)malloc(sizeof(struct node));
    (*L)->Front = 0;
    (*L)->Rear = 0;
}
//入队
void AadQuence(SeQuence L, char x) {
    
    if ((L->Rear + 1) % MaxSize == L->Front) { //Front和Rear相遇队列满
        printf("满队列");
    }
    else {
        L->Rear = (L->Rear + 1) % MaxSize;  //队尾加一
        L->data[L->Rear] = x;
    }
}
//出队
void DeQuence(SeQuence L, char *x) {
    if (L->Front ==L->Rear) { //相等说明空队列
        printf("空队列\n");
    }
    else {
        L->Front = (L->Front + 1) % MaxSize; //队头加一
        *x = L->data[L->Front];
    }
}
void PrintQuence(SeQuence L) {
    int i,a=0;
    i = (L->Front + 1) % MaxSize; //队头加一开始遍历
    while (i != L->Rear) {
        printf("%c", L->data[i]);
        i = (i + 1) % MaxSize;
        a++;
    }
    printf("%c,共%d个数", L->data[i],a-9); //队尾
    printf("\n");
}
int main() {
    SeQuence q;
    char* y;
    char x;
    y = &x;
    InitQuence(&q);
    DeQuence(q, y);
    printf("输入数据:\n");
    scanf("%c", &x);
    while (x != '\n') {
        AadQuence(q, x);
        scanf("%c", &x);
    }
    PrintQuence(q);
    DeQuence(q, y);
    printf("出队数据是:%c\n",*y);
    printf("剩余:\n");
    PrintQuence(q);
    printf("输入数据:\n");
    scanf("%c", &x);
    while (x != '\n') {
        AadQuence(q, x);
        scanf("%c", &x);
    }
    PrintQuence(q);
    
    
}
