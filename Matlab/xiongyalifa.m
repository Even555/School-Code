function [z,xy]=xiongyalifa(a)
b=a;
sh=length(a);
ml=min(a');
for i=1:sh
  a(i,:)=a(i,:)-ml(i);
end

mr=min(a);
for j=1:sh
  a(:,j)=a(:,j)-mr(j);
end

nums=0;
while(nums~=sh)

  index=ones(sh);
  index=a&index;
  index=~index;
  flag = zeros(sh);
  xy = zeros(sh);

  while(sum(sum(index)))

    for i=1:sh
      t=0;
      l=0;
      for j=1:sh
        if(flag(i,j)==0&&index(i,j)==1)
          l=l+1;
          t=j;
        end
      end
      if(l==1)
        flag(:,t)=flag(:,t)+1;
        index(:,t)=0;
        xy(i,t)=1;
      end
    end

    for j=1:sh
      t=0;
      r=0;
      for i=1:sh
        if(flag(i,j)==0&&index(i,j)==1)
          r=r+1;
          t=i;
        end
      end
      if(r==1)
        flag(t,:)=flag(t,:)+1;
        index(t,:)=0;
        xy(t,j)=1;
      end
    end
  end 

  nums=sum(sum(xy));
  if(sh==nums)
    break;
  	end

  m=max(max(a));
  for i=1:sh
    for j=1:sh
      if(flag(i,j)==0)
        if(a(i,j)<m)
          m=a(i,j);
        end
      end
    end
  end

  for i=1:sh
    for j=1:sh
      if(flag(i,j)==0)
        a(i,j)=a(i,j)-m;
      end
      if(flag(i,j)==2)
          a(i,j)=a(i,j)+m;
      end
    end
  end
end

zm=xy.*b;
z=0;
z=sum(sum(zm));