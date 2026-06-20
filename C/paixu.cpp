#include <stdio.h>
using namespace std ; 
void shellsort(int arr[] , int len , int* compare , int* move ) {// 希尔排序
    int d = len/2 ;	// gap的值
    while ( d ){
       for (int x = 0 ; x < d ; x++) {//对于gap所分的每一个组
	        for (int i = x+d ; i < len ; i += d) {//进行插入排序
		    	int temp = arr[i];  int j ;
		        for (j = i-d ; j >= 0 && arr[j] > temp ; j = j - d){
		            arr[j+d] = arr[j] ;
		            (*move)++ ;
		            (*compare)++ ;
		        }
		        arr[j+d] = temp;
		        (*move)++ ; 
	        }
       }
       d = d / 2;//每次都将gap的值减半
    } 
}
void quicksort(int arr[] , int left , int right , int* compare , int* move ){ // 快速排序 
    int temp ;
    int i = left , j = right ;	// left 和 right 分别为要比较的区域边界 
    if( left < right ){
        temp = arr[left] ;
        while( i != j ){
            while( j > i && arr[j] > temp ) --j , (*compare)++ ;//从右边扫描找到一个小于temp元素
            if( i < j ){
                arr[i] = arr[j];
            	(*move)++ ;
			    ++i ;
            }
            while( i < j && arr[i] < temp) ++i , (*compare)++ ;//从左边扫描，找到一个大于temp元素
            if( i < j ){
                arr[j] = arr[i] ;
             	(*move)++ ; --j ;
            }
        }
        arr[i] = temp ;//temp放在最终位置
        (*move)++ ;
        quicksort( arr , left , i-1 , compare , move );  //对temp左边元素进行扫描
        quicksort( arr , i+1 , right , compare , move); //对temp右边元素进行扫描
    }
}
void heapadjust(int arr[], int parent, int len , int* compare , int* move ){//构成堆 (堆排序)
    int child ;
    int temp ;
    for (temp = arr[parent]; 2 * parent + 1 < len; parent = child ) {
        child = 2 * parent + 1;
        if (child < len - 1 && arr[child + 1] > arr[child])  child++;
        (*compare)++;
        if (temp < arr[child]){
            swap (arr[child] , arr[parent]);
            (*move) += 3 ;
        }
        else break;
    }
}
void heapsort(int arr[], int len , int* compare , int* move ){//堆排序
    int i;
    for (i = (len - 1) / 2; i >= 0; i--)//把初始堆变成最大堆
        heapadjust(arr, i, len , compare , move );
    for (i = len - 1; i > 0; i--){
        swap(arr[0] , arr[i] ) //每次将最大的数排在最后
        (*move) += 3;	//swap函数执行一次移动3次 
        heapadjust(arr, 0, i , compare , move ); //重新构成堆，将最大的数放在第一位
    }
}
int main(){
	int i , j , n , m , shellcompare = 0 , shellmove = 0 , quickcompare = 0 , quickmove = 0 , heapcompare = 0 , heapmove = 0 ;// 比较次数和移动次数 
	int shellnum[105] , quicknum[205] , heapnum[505] ; 
	srand((unsigned)time(NULL));
	for( i = 0 ; i < 100 ; i++ ){	//开始生成随机数 
		shellnum[i] = 200 + rand()%801 ;}
	for( i = 0 ; i < 200 ; i++ ){	/开始生成随机数
		quicknum[i] = 200 + rand()%9800 ;}  
	for( i = 0 ; i < 500 ; i++ ){	// 开始生成随机数
		heapnum[i] = 200 + rand()%9800 ;}
	cout << "希尔排序之前的排序为: " << endl ;
	for( i = 0 ; i < 100 ; i++ ){
    	printf("%4d ",shellnum[i]);
    	if( (i+1)%20 == 0 ) cout << endl ;
	}
	shellsort( shellnum , 100 , &shellcompare , &shellmove ) ; 
	cout << "希尔排序之后的排序为:" << endl ;
    for( int i = 0 ; i < 100 ; i++ ){
    	printf("%4d ", shellnum[i]) ;
    	if( (i+1)%20 == 0 )		cout << endl ;
	}
	cout << "此次希尔排序比较次数为:" << shellcompare << endl ;
	cout << "此次希尔排序移动次数为:" << shellmove << endl << endl ;
	cout << "快速排序之前的排序为: " << endl ;
	for( i = 0 ; i < 200 ; i++ ){
    	printf("%4d ",quicknum[i]);
    	if( (i+1)%20 == 0 )		cout << endl ;
	}
	quicksort( quicknum , 0 , 200-1 , &quickcompare , &quickmove ); 
	cout << "快速排序之后的排序为: " << endl ;
	for( int i = 0 ; i < 200 ; i++ ){
    	printf("%4d ", quicknum[i]) ;
    	if( (i+1)%20 == 0 )		cout << endl ;
	}
	cout << "此次快速排序比较次数为:" << quickcompare << endl ;
	cout << "此次快速排序移动次数为:" << quickmove << endl << endl ;
	
	cout << "堆排序之前的排序为: " << endl ;
	for( i = 0 ; i < 500 ; i++ ){
    	printf("%4d ", heapnum[i]);
    	if( (i+1)%20 == 0 )		cout << endl ;
	}
	
	heapsort( heapnum , 500 , &heapcompare , &heapmove ) ;
	
	cout << "堆排序之后的排序为: " << endl ;
	for( int i = 0 ; i < 500 ; i++ ){
    	printf("%4d ", heapnum[i]) ;
    	if( (i+1)%20 == 0 )		cout << endl ;
	}
	cout << "此次堆排序比较次数为:" << heapcompare << endl ;
	cout << "此次堆排序移动次数为:" << heapmove << endl << endl ;
	
	return 0 ;
}
