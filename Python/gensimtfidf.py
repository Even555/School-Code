from gensim import corpora, models
import os

data_path = "./CSCMNews"
num_categories = 6

# 加载新闻语料数据集并创建词典
corpus = []
categories = []
for category_dir in os.listdir(data_path):
    category_path = os.path.join(data_path, category_dir)
    if os.path.isdir(category_path):
        for file_name in os.listdir(category_path)[:10]:
            file_path = os.path.join(category_path, file_name)
            with open(file_path, "r", encoding="utf-8") as f:
                content = f.read()
                corpus.append(content)
                categories.append(category_dir)

dictionary = corpora.Dictionary([doc.split() for doc in corpus])

# 构建文档-词频矩阵并计算 TF-IDF 模型
doc_term_matrix = [dictionary.doc2bow(doc.split()) for doc in corpus]
tfidf = models.TfidfModel(doc_term_matrix)

# 获取各个文档的 TF-IDF 向量表示
for i, doc in enumerate(doc_term_matrix):
    tfidf_vector = tfidf[doc]
    print("Document:", i)
    print("Category:", categories[i])
    print("TF-IDF Vector:", tfidf_vector)
    print()


