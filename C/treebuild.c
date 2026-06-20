/* 输入格式A(B(D,E(G,)),C(,F))建立二叉树*/

#include <iostream>
#include <string>
using namespace std;

class node
{
public:
 node(char c):data(c){leftchild=NULL;rightchild=NULL;}
 char data;
 node * leftchild;
 node * rightchild;
};

node * create_bt(string str)
{

 if (str.length()==0) return NULL;
 else 
 {
  node *p=new node(str[0]);
  if(str.length()==1) return p;
  int pair=0,i=0;
  for(;i<str.length();i++)
  {
   if (str[i]=='(') pair++;
   if (str[i]==')') pair--;
   if ((str[i]==',')&&(pair==1)) break;
  }
  
  if(1<=i-2)
  {
   string leftstr=str.substr(2,i-2);
   p->leftchild=create_bt(leftstr);
  }
  if(1<=str.length()-2-i)
  {
   string rightstr=str.substr(i+1,str.length()-2-i);
   p->rightchild=create_bt(rightstr);
  }
  return p;
 }
}

void destroy(node * root)
{
 if (root==NULL) return;
 destroy(root->leftchild);
 destroy(root->rightchild); 
 delete root;
}
void print(node * root, int t)
{
 if (root==NULL) return;
 print(root->rightchild,t+1);
 for(int i=0;i<t;i++) cout<<'\t';
 cout<<root->data<<endl;;
 print(root->leftchild,t+1);  
 

}
int main()
{
 string str="A(B(D(F,G),E(,H(,I))),C)";
 node *p= create_bt(str);
 print(p,0);
 destroy(p);
 return 0;
}
