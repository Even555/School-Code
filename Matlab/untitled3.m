% 定义省份类别及其包含的省份列表
provinces = {'广东', '江苏', '山东', '浙江', '河南', '四川', '湖北', '福建', '湖南', '安徽', ...
             '河北', '陕西', '江西', '辽宁', '云南', '广西', '山西', '内蒙古', '贵州', ...
             '新疆', '黑龙江', '吉林', '甘肃', '海南', '宁夏', '青海', '西藏'};

% 定义每个类别中包含的省份列表
clusters = { {'广东', '江苏'}, {'山东', '浙江'}, {'河南', '四川', '湖北', '福建', '湖南', '安徽', '河北'}, ...
             {'陕西', '江西', '辽宁', '云南', '广西', '山西', '内蒙古'}, {'贵州', '新疆', '黑龙江', '吉林', '甘肃'}, ...
             {'海南', '宁夏', '青海', '西藏'} };

% 统计每个类别中的省份数量
province_counts = zeros(1, numel(clusters));
for i = 1:numel(clusters)
    province_counts(i) = numel(clusters{i});
end

% 创建一个新的图形
figure;

% 绘制直方图
bar(province_counts);
hold on;

% 绘制折线图
plot(province_counts, 'ro-');
hold off;

xticks(1:numel(clusters));%设置 x 轴刻度的位置，numel(clusters) 返回簇的数量，然后通过 1:numel(clusters) 生成一个从 1 到簇数量的序列，用于指定 x 轴刻度的位置。
xticklabels({'第一类', '第二类', '第三类', '第四类', '第五类', '第六类'});
ylabel('省份数量');
title('每个类别中省份数量的直方图和折线图');
legend('直方图', '折线图', 'Location', 'northwest');
grid on;
