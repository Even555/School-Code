import os
import random
import numpy as np
from gensim import corpora, models
from sklearn.metrics.pairwise import pairwise_distances
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

data_path = "./CSCMNews"
num_categories = 6
test_size = 0.3
n_components = [100]

# 加载新闻语料数据集并创建词典
corpus = []
categories = []
cate_dir = []
for category_dir in os.listdir(data_path):
    category_path = os.path.join(data_path, category_dir)
    cate_dir.append(category_dir)
    if os.path.isdir(category_path):
        for file_name in os.listdir(category_path)[:1000]:
            file_path = os.path.join(category_path, file_name)
            with open(file_path, "r", encoding="utf-8") as f:
                content = f.read()
                corpus.append(content)
                categories.append(category_dir)

# 创建训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(corpus, categories, test_size=test_size, random_state=42)

# 创建词典并构建文档-词频矩阵
dictionary = corpora.Dictionary([doc.split() for doc in X_train])
doc_term_matrix_train = [dictionary.doc2bow(doc.split()) for doc in X_train]
doc_term_matrix_test = [dictionary.doc2bow(doc.split()) for doc in X_test]

# 计算 TF-IDF 模型并进行特征转换
tfidf = models.TfidfModel(doc_term_matrix_train)
tfidf_matrix_train = []
tfidf_matrix_test = []
for doc in doc_term_matrix_train:
    tfidf_vec = tfidf[doc]
    # 创建一个全0向量并将TF-IDF值赋值给对应的位置
    vec = [0] * len(dictionary)
    for i, j in tfidf_vec:
        vec[i] = j
    tfidf_matrix_train.append(vec)
for doc in doc_term_matrix_test:
    tfidf_vec = tfidf[doc]
    # 创建一个全0向量并将TF-IDF值赋值给对应的位置
    vec = [0] * len(dictionary)
    for i, j in tfidf_vec:
        vec[i] = j
    tfidf_matrix_test.append(vec)

# 不进行 PCA 降维
lr = LogisticRegression()
lr.fit(tfidf_matrix_train, y_train)
y_pred_lr = lr.predict(tfidf_matrix_test)
acc_lr = accuracy_score(y_test, y_pred_lr)
print("Logistic Regression accuracy without PCA:", acc_lr)

rf = RandomForestClassifier()
rf.fit(tfidf_matrix_train, y_train)
y_pred_rf = rf.predict(tfidf_matrix_test)
acc_rf = accuracy_score(y_test, y_pred_rf)
print("Random Forest accuracy without PCA:", acc_rf)

# 进行 PCA 降维
for n in n_components:
    pca = PCA(n_components=n)
    pca.fit(tfidf_matrix_train)
    tfidf_matrix_train_pca = pca.transform(tfidf_matrix_train)
    tfidf_matrix_test_pca = pca.transform(tfidf_matrix_test)

    # 训练逻辑回归分类器并进行测试
    lr = LogisticRegression()
    lr.fit(tfidf_matrix_train_pca, y_train)
    y_pred_lr = lr.predict(tfidf_matrix_test_pca)
    acc_lr_pca = accuracy_score(y_test, y_pred_lr)
    print(f"Logistic Regression accuracy with PCA(n={n}):", acc_lr_pca)

    # 训练随机森林分类器并进行测试
    rf = RandomForestClassifier()
    rf.fit(tfidf_matrix_train_pca, y_train)
    y_pred_rf = rf.predict(tfidf_matrix_test_pca)
    acc_rf_pca = accuracy_score(y_test, y_pred_rf)
    print(f"Random Forest accuracy with PCA(n={n}):", acc_rf_pca)
