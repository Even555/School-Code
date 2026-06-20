function [answer,best]=DE(fun)
global popnum ubound lbound dim F cost times
best=inf;
answer=zeros(times,cost/popnum);
for i=1:times
    X=initpop();
    thebest=inf;
    for j=1:cost/popnum
        for k=1:popnum
            [BV,DV]=PS(X);
            TV=variant(X(k,:),BV,DV);
            X(k,:)=selecsur(X(k,:),TV);
        end
        answer(i,j)=thebest;
        if thebest<best
            best=thebest;
        end
    end
end

function X=initpop()
X=unifrnd(lbound,ubound,popnum,dim);
end

function [BV,DV]=PS(X) %父代选择
BV=X(unidrnd(popnum),:);  %随机选取一个个体作为基向量
DV=diff(X(randperm(popnum,3),:)); %随机生成两个差向量
end  

function TV=variant(X,BV,DV) %变异
MV=BV+F*mean(DV);
TV=zeros(1,dim);
flag=0;
for i1=1:dim
    if rand()<0.8
        TV(i1)=MV(i1);
        flag=1;
    else
        TV(i1)=X(i1);
    end
    if flag==0
        n=unidrnd(dim);
        TV(n)=MV(n);
    end
end
end

function f=fit(X) %适应值函数
    f=fun(X);
end 

function luck=selecsur(X,newX) %生存选择
new=fit(newX);
old=fit(X);
if new<old
    good=new;
    luck=newX;
else
    good=old;
    luck=X;
end
if good<thebest
    thebest=good;
end
end
end
