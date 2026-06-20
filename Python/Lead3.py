# -*- coding: UTF-8 -*-
# !/usr/bin/python
# @time     :2019/8/24 22:43
# @author   :Mo
# @function :text_summary with lead-3
import re
import os


def read_files(dir_path):
    files = os.listdir(dir_path)
    texts = set()  # 使用set数据结构保存文本内容，自动去重
    for file in files:
        if file.endswith('.txt'):
            file_path = os.path.join(dir_path, file)
            with open(file_path, 'r', encoding='utf-8') as f:
                text = f.read()
                texts.add(text)
    return list(texts)


def cut_sentence(sentence):
    re_sen = re.compile('[:;!?。：；？！\n\r]')  # .不加是因为不确定.是小数还是英文句号(中文省略号......)
    sentences = re_sen.split(sentence)
    sen_cuts = []
    for sen in sentences:
        if sen and str(sen).strip():
            sen_cuts.append(sen)
    return sen_cuts


class Lead3Sum:
    def __init__(self):
        self.algorithm = 'lead_3'

    def summarize(self, text, type='mix', num=3):
        sentences = cut_sentence(text)
        if len(sentences) < num:
            return sentences
        # 最小句子数
        num_min = min(num, len(sentences))
        if type == 'begin':
            summers = sentences[0:num]
        elif type == 'end':
            summers = sentences[-num:]
        else:
            summers = [sentences[0]] + [sentences[-1]] + sentences[1:num - 1]
        summers_s = {}
        for i in range(len(summers)):  # 得分计算
            if len(summers) - i == 1:
                summers_s[summers[i]] = (num - 0.75) / (num + 1)
            else:
                summers_s[summers[i]] = (num - i - 0.5) / (num + 1)
        score_sen = [(rc[1], rc[0]) for rc in sorted(summers_s.items(), key=lambda d: d[1], reverse=True)][0:num_min]
        return score_sen


if __name__ == '__main__':
    dir_path = 'D:\PyCharm\实验二-摘要算法代码及测试\测试文本'
    texts = read_files(dir_path)
    l3 = Lead3Sum()
    summaries = []
    for text in texts:
        for score_sen in l3.summarize(text, type='mix', num=3):
            print(score_sen)
# 优化器、损失函数、保存检查点、时间监视器等设置
opt = nn.Adam(filter(lambda x: x.requires_grad, net.get_parameters()),
              learning_rate=learning_rate, weight_decay=cfg.weight_decay)
loss = nn.SoftmaxCrossEntropyWithLogits(sparse=True)
model = Model(net, loss_fn=loss, optimizer=opt, metrics={'acc': Accuracy()})
config_ck = CheckpointConfig(save_checkpoint_steps=int(cfg.epoch_size*batch_num/2), keep_checkpoint_max=cfg.keep_checkpoint_max)
time_cb = TimeMonitor(data_size=batch_num)
ckpt_save_dir = "./ckpt"
ckpoint_cb = ModelCheckpoint(prefix="train_textcnn", directory=ckpt_save_dir, config=config_ck)
loss_cb = LossMonitor()

model.train(cfg.epoch_size, dataset, callbacks=[time_cb, ckpoint_cb, loss_cb])
print("train success")