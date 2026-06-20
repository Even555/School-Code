clc;
clear;
close all;
%% 导入数据
digitDatasetPath = fullfile(matlabroot,'toolbox','nnet','nndemos', ...
    'nndatasets','DigitDataset');
imds = imageDatastore(digitDatasetPath, ...
    'IncludeSubfolders',true,'LabelSource','foldernames');
% 图像展示
figure;
perm = randperm(10000,20);
for i = 1:20
    subplot(4,5,i);
    imshow(imds.Files{perm(i)});
end
%% 数据整理与归一化
labelCount = countEachLabel(imds);% 查看各类图片的数量及对应的分类标签
img = readimage(imds,1); % 设置输入图像的大小
fprintf('输入图像的大小为：');
disp(size(img));
% 指定训练集和测试集合
numTrainFiles = 750; % 指定训练集总共包含750个图像
[imdsTrain,imdsValidation] = splitEachLabel(imds,numTrainFiles,'randomize'); % 将图片与对应的标签分开，即分成输入与输出
%% 网络定义以及训练
[layers,options] = Net_Built(imdsValidation);
analyzeNetwork(layers);
net = trainNetwork(imdsTrain,layers,options);
%% 网络分类预测
YPred = classify(net,imdsValidation);
YValidation = imdsValidation.Labels;
accuracy = sum(YPred == YValidation)/numel(YValidation);
fprintf('分类测试的正确率为：');
disp(accuracy);
plot(YPred);
hold on
plot(YValidation);
hold off
legend('预测分类','实际分类');
title('CNN实际测试情况');
xlabel('样本');
ylabel('分类数值');
