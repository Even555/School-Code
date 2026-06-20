 

clear all; 
close all; 
clc;m=50;Alpha=1;Beta=5;Rho=0.1; NC_max=200; Q=100;
 C=[1301 2352;3639 1315;417 2244;3712 1399;3488 1535;3326 1556;
   3238 1279;4196 1004;4312 790;4386 570;3007 190; 2562 1756;
  2788 1478;2781 1976;1332 6959;375 1678;3918 2179;4091 2370;
  3780 2212;3676 2078;4029 283;4263 2931;3429 1908;3557 2376;
  3394 2643;339 3701;2995 3240;3140 3550;2545 2357;5578 2826;
  2370 2975;5555 3487;3476 1234;4639 7384;2748 1938;1999 4086];               

n=size(C,1);
D=zeros(n,n);
for i=1:n
    for j=1:n
        if i~=j
            D(i,j)=((C(i,1)-C(j,1))^2+(C(i,2)-C(j,2))^2)^0.5;
        else
            D(i,j)=eps;      
        end
        D(j,i)=D(i,j);   
    end
end
Eta=1./D;          
Tau=ones(n,n);    
TB=zeros(m,n);
NC=1;           
R_best=zeros(NC_max,n);    
L_best=inf.*ones(NC_max,1);  
L_ave=zeros(NC_max,1);      
while NC<=NC_max       

    RD=[];   
    for i=1:(ceil(m/n))
        RD=[RD,randperm(n)];
    end
    TB(:,1)=(RD(1,1:m))';   

    for j=2:n     
        for i=1:m
            visited=TB(i,1:(j-1)); 
            J=zeros(1,(n-j+1));  
            P=J;             
            Jc=1;
            for k=1:n
                if length(find(visited==k))==0 
                    J(Jc)=k;
                    Jc=Jc+1;                       
                end
            end
           
            for k=1:length(J)
                P(k)=(Tau(visited(end),J(k))^Alpha)*(Eta(visited(end),J(k))^Beta);
            end
            P=P/(sum(P));
         
            Pcum=cumsum(P);   
            Select=find(Pcum>=rand);
            to_visit=J(Select(1));
            TB(i,j)=to_visit;
        end
    end
    
    if NC>=2
        TB(1,:)=R_best(NC-1,:); 
    end

    L=zeros(m,1);    
    for i=1:m
        R=TB(i,:);
        for j=1:(n-1)
            L(i)=L(i)+D(R(j),R(j+1));   
        end
        L(i)=L(i)+D(R(1),R(n));  
    end
    L_best(NC)=min(L);          
    pos=find(L==L_best(NC));
    R_best(NC,:)=TB(pos(1),:); 
    L_ave(NC)=mean(L);          
    NC=NC+1;                      
    DTA=zeros(n,n);       
    for i=1:m
        for j=1:(n-1)
            DTA(TB(i,j),TB(i,j+1))=DTA(TB(i,j),TB(i,j+1))+Q/L(i);
        end
        DTA(TB(i,n),TB(i,1))=DTA(TB(i,n),TB(i,1))+Q/L(i);
    end
    Tau=(1-Rho).*Tau+DTA; 
    TB=zeros(m,n);          
end
Pos=find(L_best==min(L_best));
Shortest_Route=R_best(Pos(1),:) 
SL=L_best(Pos(1)) 
 
figure(1) 
plot(L_best)
xlabel('迭代次数')
ylabel('最短路径长度')
title('每代最短路径长度进化曲线')
 
figure(2)

subplot(1,2,1)                 
N=length(Shortest_Route);
scatter(C(:,1),C(:,2)); 
 hold on
 plot([C(Shortest_Route(1),1),C(Shortest_Route(N),1)],[C(Shortest_Route(1),2),C(Shortest_Route(N),2)],'g')
 hold on
for ii=2:N
    plot([C(Shortest_Route(ii-1),1),C(Shortest_Route(ii),1)],[C(Shortest_Route(ii-1),2),C(Shortest_Route(ii),2)],'g')
     hold on
end
title('旅行商最短路线结果图 ')
 

subplot(1,2,2)                  
plot(L_best)
hold on                       
plot(L_ave,'r')
legend('最短距离','平均距离')
title('平均距离和最短距离')  
 