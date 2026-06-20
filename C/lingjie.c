#include <stdio.h>
typedef struct

{   int  vexnum , arcnum ;   /*  图的当前顶点数和弧数  */

    VexType   vexs[MAX_VEX] ;  /*  顶点向量  */

    AdjType   adj[MAX_VEX][MAX_VEX];

}MGraph ;    

typedef struct LinkNode

{    int  adjvex ;   // 邻接点在头结点数组中的位置(下标)

     InfoType    info  ;       // 与边或弧相关的信息, 如权值

     struct LinkNode  *nextarc ;     // 指向下一个表结点

}LinkNode ;    /*  表结点类型定义   */

typedef struct VexNode

{  VexType  data;     // 顶点信息

   int  indegree ;   //  顶点的度, 有向图是入度或出度或没有

   LinkNode  *firstarc ;    // 指向第一个表结点

}VexNode ;     /*  顶点结点类型定义   */

typedef struct

{   int vexnum ;

   VexNode   AdjList[MAX_VEX] ;

}ALGraph ;     /*  图的结构定义   */
void mian()
{
	
}
