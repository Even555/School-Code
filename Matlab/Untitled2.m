global popnum ubound lbound vs dim cost times F
popnum=50; %种群数量
ubound=15; %个体维度大小上限
lbound=-5; %个体维度大小下限
F=0.7  %统一的步长
vs=0.3;  %变异强度临界值
dim=2;   %个体维度大小
cost=20000;  %计算代价
times=30;   %重复计算次数
fun=@(x)(x(2)-(5.1/(4*pi^2))*x(1)^2+5*x(1)/pi-6)^2+10*(1-1/(8*pi))*cos(x(1))+10;
[answer,best]=DE(fun) %best为DE找到的最优值，answer为每次计算得到的最好结果变化矩阵
plt(answer);
function plt(answer)  %绘制L曲线图
hisbest=reshape(answer',1,size(answer,1)*size(answer,2));
hisbest(1)=max(hisbest);
for k=2:length(hisbest)
    if hisbest(k)>hisbest(k-1)
        hisbest(k)=hisbest(k-1);
    end
end
for t=2:length(hisbest)
    hisbest(t)=(hisbest(t)+hisbest(t-1)*(t-1))/t;
end
plot(log(hisbest));
hold on
end
