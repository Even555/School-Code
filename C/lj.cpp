#include<bits/stdc++.h>
using namespace std ;

#define MAXN 1000
#define INF 99999

int G[MAXN][MAXN] , bian , dian , v1[MAXN] , v2[MAXN] , weigh[MAXN] ;
int visited[MAXN];	//访问标志的数组,为1表示访问过，为0表示未被访问

struct node{
	int w ;
	int adj ;
	node * next ;
};
typedef node * tree ;
tree a[MAXN] ;


// 用数组模拟队列对图进行广度搜索 
typedef int DataType;
typedef struct
{
	DataType myqueue[MAXN];
	int rear;
	int front;
	int count;
}Squeue;

void QueueInit(Squeue *Q )
{
	Q ->front = 0 ;
	Q ->rear = 0 ;
	Q ->count = 0 ;
}

/*判断队列Q是否为空*/
bool QueueEmpty(Squeue Q )
{
	return Q.count==0 ;
}

/*入队列*/
void QueuePush(Squeue *Q, DataType elem)
{
	if ((Q->rear + 1) % MAXN == Q->front)//少用一个存储单元判断队满
	{
		printf("此队列满载了！你想来也来不了\n");
	}
	else
	{
		Q->myqueue[Q->rear] = elem;
		Q->rear = (Q->rear + 1) % MAXN;
		Q->count++;
	}
}

/*出队列*/
void QueuePop(Squeue *Q, DataType *elem)
{
	if (Q->rear == Q->front)
	{
		printf("队列已经啥都没了~\n");
	}
	else
	{
		*elem = Q->myqueue[Q->front];
		Q->front = (Q->front + 1) % MAXN;
		Q->count--;
	}
}

// 创建邻接链表 
void createlist()
{
	for(int i = 0 ; i < dian ; i++){
	 	a[i] = new node ;
	 	a[i]->adj = i;
	 	a[i]->next = NULL;
	}
	tree temp ;
	for(int i = 0 ; i < bian ; i++){
         tree temp , temp1 ;
         temp = new node ;
         temp->w = weigh[i] ;
         temp->adj = v2[i] ;
         temp->next = a[v1[i]]->next;
         a[v1[i]]->next = temp;
         temp1 = new node;
         temp1->w = weigh[i] ;
         temp1->adj = v1[i] ;
         temp1->next = a[v2[i]]->next;
         a[v2[i]]->next = temp1;
	}
}

// 创建邻接矩阵 
void buildjuzhen()
{
	for( int i = 0 ; i < dian ; i++) 
		for( int j = 0 ; j < dian ; j++)
			if( i == j )	G[i][j] = 0 ;
				else 		G[i][j] = INF; 	// 初始化图 , 权值99999表示无穷大（无连接） 
	// 插入边 
	for( int i = 0 ; i < bian ; i++){
		G[v1[i]][v2[i]] = weigh[i];
		G[v2[i]][v1[i]] = weigh[i];
	}
}

// 遍历并输出邻接表 
void printlist()
{
	tree temp;
	for(int i = 0 ; i < dian ; i++){
		temp = a[i];
		while( temp ){
			printf("%5d ", temp->adj ) ;
			temp = temp->next;
		}
		cout << endl;
	}
	cout << endl << endl ;
}

// 遍历并输出邻接矩阵 
void printjuzhen()
{
	int i , j ;
	for( i = 0 ; i < dian ; i++){
		for( j = 0 ; j < dian ; j++)
			printf("%5d " , G[i][j] ) ;
		cout << endl ;
	}
	cout << endl << endl ;
}

// 图的深度优先搜索 
void dfs( int pos )
{
	cout << pos << "-->" ;
	visited[pos] = 1 ;
	for( int w = 0 ; w < dian ; w++ ){
		if( !visited[w] && G[w][pos] != 0 && G[w][pos] != INF )
			dfs( w ) ;
	}
}
void traverseGdfs()
{
	memset( visited , 0 , sizeof(visited) );	// 把 visited 数组的值全部设置为 0  
	for( int i = 0 ; i < dian ; i++ ){
		if( !visited[i] )	dfs( i ) ;
	}
	cout << "over!" << endl << endl ;
 } 
 
// 图的广度优先搜索 
void bfs( int pos )
{
	Squeue q ;
	QueueInit( &q ) ;
	cout << pos << "-->" ; 
	visited[pos] = 1 ;
	QueuePush( &q , pos ) ;	// 访问到的新元素入队 
	while( !QueueEmpty(q) ){
		int v ;
		QueuePop( &q , &v ) ;			// 访问完毕，出队 , v 为出队的那个元素 
		for( int w = 0 ; w < dian ; w++ ){
			if( !visited[w] && G[w][v] != 0 && G[w][v] != INF ){
				cout << w << "-->" ;
				visited[w] = 1 ;
				QueuePush( &q , w ) ;	// 访问到的新元素入队
			}
		} 
	}
} 
void traverseGbfs()
{
	memset( visited , 0 , sizeof(visited) );	// 把 visited 数组的值全部设置为 0  
	for( int i = 0 ; i < dian ; i++ ){
		if( !visited[i] )	bfs( i ) ;
	}
	cout << "over!" << endl << endl ;
 } 
 
// 普里姆算法
void prim( int map[][MAXN] , int v)//（参数：邻接矩阵，根节点（可任意取））
{
	int lowcost[MAXN], closest[MAXN], i, min, j, k , sum = 0 ;	// sum 记录最小生成树的权值之和 
 
	/***初始化lowcost数组，closest数组(即从起点开始设置lowcost数组，closest数组相应的值，以便后续生成使用)***/
	for (i = 0 ; i < dian ; i++)//赋初值，即将closest数组都赋为第一个节点v，lowcost数组赋为第一个节点v到各节点的权重
	{
		closest[i] = v;
		lowcost[i] = map[v][i];//map[v][i]的值指的是节点v到i节点的权重
	}
 
	/**********************************开始生成其他的节点*********************************/
	for (i = 1; i < dian ; i++)//接下来找剩下的n-1个节点（dian是图的节点个数）
	{
 
		/*****找到一个节点，该节点到已选节点中的某一个节点的权值是当前最小的*****/
		min = INF ;//INF表示正无穷（每查找一个节点，min都会重新更新为INF，以便获取当前最小权重的节点）
		for (j = 0; j < dian ; j++)//遍历所有节点
		{
			if (lowcost[j] != 0 && lowcost[j] < min)//若该节点还未被选且权值小于之前遍历所得到的最小值
			{
				min = lowcost[j];//更新min的值
				k = j;//记录当前最小权重的节点的编号
			}
		}
 
		/****************输出被连接节点与连接节点，以及它们的权值***************/
		printf("当前连接的边为(%d,%d),边权为:%d\n", closest[k], k, min);
		
		sum += min ;	// 加上新增的边的权值 
 
		/***********更新lowcost数组，closest数组，以便生成下一个节点************/
		lowcost[k] = 0;//表明k节点已被选了(作标记)
		//选中一个节点完成连接之后，作数组相应的调整
		for (j = 0; j < dian ; j++)//遍历所有节点
		{
			/* if语句条件的说明：
			 * （1）map[k][j] != 0是指k！=j，即跳过自身的节点
			 * （2）map[k][j]是指刚被选的节点k到节点j的权重，lowcost[j]是指之前遍历的所有节点与j节点的最小权重。
			 * （3）若map[k][j] < lowcost[j],则说明当前刚被选的节点k与节点j之间存在更小的权重，则需要更新
			 */
			if (map[k][j] != 0 && map[k][j] < lowcost[j])
			{
				//更新lowcost数组，closest数组
				lowcost[j] = map[k][j];//更新权重，使其当前最小
				closest[j] = k;//进入到该if语句里，说明刚选的节点k与当前节点j有更小的权重，则closest[j]的被连接节点需作修改为k
			}
		}
	}
	cout << "所求最小生成树的权值之和为:" << sum << endl ; 
} 
  
// 主函数 
int main()
{
	int i , j , k , n , m ;
	
	cout << "请输入要创建的图需要多少个顶点(顶点从0开始)：" ; 
	cin >> dian ;
	cout << "请输入要创建多少条边:" ;
	cin >> bian ;
	for( i = 0 ; i < bian ; i++ ){
		printf("输入第%2d条边的两个顶点和权值:" , i+1 ) ;
		cin >> v1[i] >> v2[i] >> weigh[i] ;
	}
	
	buildjuzhen() ;	// 创建邻接矩阵 
	createlist() ;	// 创建邻接表 
	
	cout << "邻接矩阵输出如下:" << endl ;
	printjuzhen() ;
	cout << "邻接表输出如下:" << endl ;
	printlist() ;
	cout << "图的深度遍历结果为:" ;
	traverseGdfs() ;
	cout << "图的广度遍历结果为:" ;
	traverseGbfs() ;
	
	// 生成最小生成树 
	prim( G , 0 ) ;
	 
	return 0;
}


