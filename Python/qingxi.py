# 读取文本信息
import os
import re
import time
from nltk import *
import jieba


def readFile(path):
    str_doc = ""
    with open(path, 'r', encoding='utf-8') as f:
        str_doc = f.read()
    return str_doc


# 正则对字符串清洗
def textParse(str_doc):
    # 正则过滤掉特殊符号、标点、英文、数字等。
    r1 = '[a-zA-Z0-9’!"#$%&\'()*+,-./:：;；|<=>?@，—。?★、…【】《》？“”‘’！[\\]^_`{|}~]+'
    # 去除空格
    r2 = '\s+'
    # 去除换行符
    str_doc = re.sub(r1, ' ', str_doc)
    # 多个空格成1个
    str_doc = re.sub(r2, '', str_doc)
    return str_doc


# # 1 读取文本
# path = r'./CSCMNews/财经/798977.txt'
# str_doc = readFile(path)
# #print(str_doc)
# # 2 文本清洗
# word_list = textParse(str_doc)
# #print(word_list)
# # 3 分词
# word_cut = jieba.lcut(word_list)
# #print(word_cut)
# # 4 词频统计
# word_count = FreqDist(word_cut)
# print(word_count.keys())
# print(word_count.values())


# 加载目录文件
class loadFiles(object):
    def __init__(self, par_path):
        self.par_path = par_path

    def __iter__(self):
        folders = loadFolders(self.par_path)
        for folder in folders:  # level directory
            catg = folder.split(os.sep)[-1]
            for file in os.listdir(folder):  # secondary directory
                file_path = os.path.join(folder, file)
                if os.path.isfile(file_path):
                    this_file = open(file_path, 'rb')  # rb读取方式更快
                    content = this_file.read().decode('utf8')
                    yield catg, content
                    this_file.close()


# 加载目录下的子文件
class loadFolders(object):  # 迭代器
    def __init__(self, par_path):
        self.par_path = par_path

    def __iter__(self):
        for file in os.listdir(self.par_path):
            file_abspath = os.path.join(self.par_path, file)
            # if file is a folder
            if os.path.isdir(file_abspath):
                yield file_abspath

if __name__ == '__main__':
    start = time.time()
    filepath = os.path.abspath(r'./CSCMNews')
    files = loadFiles(filepath)
    n = 1  # n 表示抽样率， n抽1
    for i, msg in enumerate(files):
        if i % n == 0:
            catg = msg[0]  # 文章类别
            content = msg[1]  # 文章内容
            content = textParse(content)  # 正则清洗
            #print(content)
            # 3 分词
            word_cut = jieba.lcut(content)
            #print(word_cut)
            # 4 词频统计
            word_count = FreqDist(word_cut)
            #print(word_count.keys())
            #print(word_count.values())
            if int(i / n) % 1000 == 0:
                print('{t} *** {i} \t docs has been dealed'
                      .format(i=i, t=time.strftime('%Y-%m-%d %H:%M:%S', time.localtime())), '\n', catg, ':\t',
                      content[:20])
    end = time.time()
    print('total spent times:%.2f' % (end - start) + ' s')
