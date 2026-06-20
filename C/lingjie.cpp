#include <stdio.h>
#include <stdlib.h>
#define MaxV  20
#define Maxsize  8
#define MaxWeight  1000
#define DataType  char
//创建一个存储边的结构体
typedef struct {
	int row;
	int col;
	int weight;
}RowColWeight;

//创建一个顺序表存储顶点
typedef struct {
	DataType list[Maxsize];
	int size;
}SeqList;

//初始化顺序表函数
void ListIntinate(SeqList* L) {
	L->size = 0;
}

//顺序表插入元素的函数
int ListInsert(SeqList* L, int i, DataType x) {
	int j;
	if (L->size >= Maxsize) {
		printf("顺序表已满无法插入！\n");
		return 0;
	}
	else if (i<0 || i>L->size)
	{
		printf("参数i不合法！");
		return 0;
	}
	else {
		for (j = L->size; j > i; j--) L->list[j] = L->list[j - 1];
		L->list[i] = x;
		L->size++;
		return 1;
	}
}

//顺序表取元素函数。为prim算法做准备
int ListGet(SeqList L, int i, DataType* x) {
	if (i<0 || i>L.size - 1) {
		printf("参数不合法！\n");
		return 0;
	}
	else {
		*x = L.list[i];
		return 1;
	}
}

//创建一个邻接矩阵图表
typedef struct {
	SeqList Vertices;
	int edge[MaxV][MaxV];
	int numOfEdges;
}AdjMgraph;

//邻接矩阵图表的初始化函数
void Initiate(AdjMgraph* G, int n) {
	int i, j;
	for (i = 0;i < n;i++) {
		for (j = 0;j < n;j++) {
			if (i == j) {
				G->edge[i][j] = 0;
			}
			else {
				G->edge[i][j] = 1000;
			}
		}
		G->numOfEdges = 0;
		ListIntinate(&G->Vertices);
	}
}

//邻接矩阵图表的插入顶点函数
void InsertVertex(AdjMgraph* G, DataType vertex) {
	ListInsert(&G->Vertices, G->Vertices.size, vertex);
}

//邻接矩阵插入边函数
void InsertEdge(AdjMgraph* G, int v1, int v2, int weight) {
	if (v1 < 0 || v1 >= G->Vertices.size || v2 < 0 || v2 >= G->Vertices.size) {
		printf("参数出错");
		return;
	}
	G->edge[v1][v2] = weight;
	G->edge[v2][v1] = weight;
	G->numOfEdges = G->numOfEdges + 2;
}

//邻接矩阵的取第一个顶点的函数
//对于邻接矩阵来说默认第一个非零元素所对应的顶点就是第一个邻接顶点
int GetFristVex(AdjMgraph G, int v) {
	//在图中寻找序号为v的顶点的第一个邻接顶点
	//如果存在这样的邻接顶点则返回该邻接顶点的序号，否则返回-1
	int col;
	if (v < 0 || v >= G.Vertices.size) {
		printf("参数信息错误(1)");
		return -1;
	}
	for (col = 0;col < G.Vertices.size;col++) {
		if (G.edge[v][col] > 0 && G.edge[v][col] < MaxWeight) {
			return col;
		}
	}
	return -1;
}

//邻接矩阵取下一个邻接顶点
int GetNextVex(AdjMgraph G, int v1, int v2) {
	int col;
	if (v1 < 0 || v1 >= G.Vertices.size || v2 < 0 || v2 >= G.Vertices.size) {
		printf("参数信息错误");
		return -1;
	}
	for (col = v2 + 1;col < G.Vertices.size;col++) {
		if (G.edge[v1][col] > 0 && G.edge[v1][col] < MaxWeight) {
			return col;
		}
	}
	return -1;
}

//以邻接矩阵为基础创建一个图的函数
void CreatGraph(AdjMgraph* G, DataType V[], int n, RowColWeight E[], int e) {
	int i, k;
	Initiate(G, n);
	for (i = 0;i < n;i++) {
		InsertVertex(G, V[i]);
	}
	for (k = 0;k < e;k++) {
		InsertEdge(G, E[k].row, E[k].col, E[k].weight);
	}
}


//以邻接矩阵为基础实现图的深度优先遍历
void DepthSearch(AdjMgraph G, int v, int visited[]) {
	//连同图以v为初始顶点的深度优先遍历，数组visted用来记录相应的顶点是否已经访问过
	int w;
	printf("%c ", G.Vertices.list[v]);
	visited[v] = 1;
	w = GetFristVex(G, v);
	while (w != -1) {

		if (visited[w] != 1) {
			DepthSearch(G, w, visited);
		}
		w = GetNextVex(G, v, w);
	}
}
//创建顺序循环队列，用于广度优先遍历
typedef struct {
	int queue[Maxsize];
	int rear;//队尾指针
	int front;//队头指针
	int count;//计数器
}SeqCQueue;

//初始化循环队列
void QueueInitiate(SeqCQueue* Q) {
	Q->rear = 0;
	Q->front = 0;
	Q->count = 0;
}

//判断队列是否为空
int QueueNotEmpty(SeqCQueue Q) {
	if (Q.count != 0) return 1;
	else return 0;
}

//将元素入队列
int QueueAppend(SeqCQueue* Q, int x) {
	if (Q->count > 0 && Q->rear == Q->front) {
		printf("队列已满无法插入\n");
		return 0;
	}
	else {
		Q->queue[Q->rear] = x;
		Q->rear = (Q->rear + 1) % Maxsize;
		Q->count++;
		return 1;
	}
}

//将元素出队列
int QueueDelete(SeqCQueue* Q, int* d) {
	if (Q->count == 0) {
		printf("队列已空无数据元素出队列！\n");
		return 0;
	}
	else {
		*d = Q->queue[Q->front];
		Q->front = (Q->front + 1) % Maxsize;
		Q->count--;
		return 1;
	}
}

//以邻接矩阵为基础的广度优先遍历
void BroadFSearch(AdjMgraph G, int v, int visited[]) {
	int u, w;
	SeqCQueue queue;
	printf("%c ", G.Vertices.list[v]);
	visited[v] = 1;
	QueueInitiate(&queue);
	QueueAppend(&queue, v);
	while (QueueNotEmpty(queue)) {
		QueueDelete(&queue, &u);
		w = GetFristVex(G, u);
		while (w != -1) {
			if (visited[w] != 1) {
				printf("%c ", G.Vertices.list[w]);
				visited[w]=1;
				QueueAppend(&queue, w);
			}
			w = GetNextVex(G, u, w);
		}
	}
}

//建立最小生成树的结构体存储顶点数据和相应的边的权值
typedef struct {
	char vertex;
	int weight;
}MinSpanTree;

//以邻接矩阵为基础的Prim算法建立图的最小生成树
void Prim(AdjMgraph G, MinSpanTree closeVertex[]) {
	char x;
	int n = G.Vertices.size;
	int minCost;
	int* lowCost = (int*)malloc(sizeof(int)*n);
	int i, j, k;
	for (i = 1;i < n;i++) {
		lowCost[i] = G.edge[0][i];
	}

	ListGet(G.Vertices,0,&x);
	closeVertex[0].vertex=x;
	lowCost[0] = -1;
	for (i = 1;i < n;i++)
	{
		//寻找当前最小权值的边所对应的弧头顶点k
		minCost = MaxWeight;
		for (j = 1;j < n;j++) {
			if (lowCost[j] < minCost && lowCost[j]>0) {
				minCost = lowCost[j];
				k = j;
			}
		}
		ListGet(G.Vertices, k, &x);
		closeVertex[i].vertex = x;
		closeVertex[i].weight = minCost;
		lowCost[k] = -1;
		//根据加入集合U的顶点修改lowCost中的数值
		for (j = 1;j < n;j++) {
			if (G.edge[k][j] < lowCost[j]) {
				lowCost[j] = G.edge[k][j];
			}
		}
	}
}

int main() {
	AdjMgraph G;
	int visited[8];
	int visited1[8];
	int n = 8;
	int e = 10;
	int i, j;
	Initiate(&G, 8);
	DataType v[8] = { 'A','B', 'C', 'D', 'E', 'F', 'G', 'H' };
	RowColWeight E[10] = { {0,1,10}, {0,2,6}, {0,3,15}, {1,4,7}, {2,4,12}, {2,5,7}, {3,6,16}, {4,7,8}, {5,6,3}, {6,7,6} };
	CreatGraph(&G, v, n, E, e);
	printf("顶点的集合为：\n");
	for (i = 0;i < G.Vertices.size;i++) {
		printf("%c ", G.Vertices.list[i]);
	}
	printf("\n");
	printf("图的邻接矩阵图为：\n");
	for (i = 0;i < G.Vertices.size;i++) {
		for (j = 0;j < G.Vertices.size;j++)
		{
			printf("%4d ", G.edge[i][j]);
		}
		printf("\n");
	}
	printf("图的深度遍历为：\n");
	DepthSearch(G, 0, visited);
	printf("\n");
	printf("图的广度遍历为：\n");
	BroadFSearch(G, 0, visited1);
	MinSpanTree closeVertex[8];
	Prim(G, closeVertex);
	printf("\n");
	printf("Prim算法的最小生成树的顶点：");
	printf("初始顶点=%c\n", closeVertex[0].vertex);
	for (i = 1;i < 8;i++) {
		printf("顶点=%c 边的权值=%d \n", closeVertex[i].vertex, closeVertex[i].weight);
	}
}


