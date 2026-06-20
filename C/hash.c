#include<stdio.h>
#include<malloc.h>

typedef struct MyHash {
	int key;
	int value;
	struct MyHash* next;
}MyHash;

MyHash hash[17];
//主要操作：
//初始化
void ini(MyHash hash[], int n) {
	for (int i = 0; i < n; i++) {
		hash[i].key = -1;
		hash[i].value = -1;
		hash[i].next = NULL;
	}
}

//添加。返回1为成功，-1为失败（重复）
int addData(int key, int value) {
	int t = key % 17;
	if (hash[t].key == -1) {
		hash[t].key = key;
		hash[t].value = value;
		return 1;
	}
	MyHash* p = (MyHash*)malloc(sizeof(MyHash));
	*p = hash[t];
	if (p->next == NULL) {
		MyHash* h = (MyHash*)malloc(sizeof(MyHash));
		h->key = key;
		h->value = value;
		h->next = NULL;
		hash[t].next = h;
		return 1;
	}
	while (p->next != NULL) {
		if (p->next->key == key) {
			return -1;
		}
		if (p->next->key ==-1) {
			p->next->key = key;
			p->next->value = value;
			return 1;
		}
		p = p->next;
	}

	MyHash* h = malloc(sizeof(MyHash));
	h->key = key;
	h->value = value;
	h->next = NULL;
	p->next = h;
	return 1;
}

//查找
int search(int key) {
	int t = key % 17;
	int n = 0;//查找次数

	n++;
	if (hash[t].key == key) {
		printf("查找成功，查找次数为%d次\n", n);
		return n;
	}

	MyHash* p = (MyHash*)malloc(sizeof(MyHash));
	*p = hash[t];
	n++;
	if (p == NULL) {
		printf("查找失败，查找次数为%d次\n", n);
		return n;
	}
	while (p != NULL) {
		n++;
		if (p->key == key) {
			printf("查找成功，查找次数为%d次\n", n);
			return n;
		}
		p = p->next;
	}
	printf("查找失败，查找次数为%d次\n", n);
	return n;
}

//删除
int delete(int key) {
	int t = key % 17;
	int n = 0;//操作次数
	n++;
	if (hash[t].key == key) {
		hash[t].key = -1;
		hash[t].value = -1;
		n += 2;
		printf("删除成功，操作次数为%d次\n", n);
		return n;
	}

	MyHash* p = (MyHash*)malloc(sizeof(MyHash));
	*p = hash[t];
	n++;
	if (p == NULL) {
		printf("查找失败，不存在此数，操作次数为%d次\n", n);
		return n;
	}
	while (p != NULL) {
		n++;
		if (p->key == key) {
			hash[t].key = -1;
			hash[t].value = -1;
			n += 2;
			printf("删除成功，操作次数为%d次\n", n);
			return n;
		}
		p = p->next;
		n++;
	}
	printf("查找失败，不存在此数，操作次数为%d次\n", n);
	return n;
}

//步骤与测试
main() {
	ini(&hash, 17);
	int count = 50;
	int i;
	while (count > 0) {
		i = rand();
		if (i > 199 && i < 1001) {
			if (addData(i, 0) > 0) {
				count--;
			}
		}
	}

	printf("查找500(500为自定义的数)\n");
	search(500);

	printf("\n");
	printf("查找%d(%d为生成的随机数)\n", i,i);
	search(i);

	printf("\n");
	printf("删除%d\n", i);
	delete(i);

}


