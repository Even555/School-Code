import fnmatch
import os
import win32com.client as wc


def Word2Txt(filePath, savePath=''):
    # 1 切分文件上级目录和文件名
    dirs, filename = os.path.split(filePath)
    # print(dirs,'\n',filename)
    # 2 修改转化后的文件名
    new_name = ''
    if fnmatch.fnmatch(filename, '*doc'):
        new_name = filename[-4] + '.txt'
    elif fnmatch.fnmatch(filename, '*.docx'):
        new_name = filename[:-5] + '.txt'
    else:
        return
    print('->', new_name)
    # 3 文件转化后的保存路径
    if savePath == '':
        savePath = dirs
    else:
        savePath = savePath
    word_to_txt = os.path.join(savePath, new_name)
    print('->', word_to_txt)
    # 4 加载处理应用，word转化txt
    wordapp = wc.Dispatch('Word.Application')  # 打开word应用程序
    mytxt = wordapp.Documents.Open(filePath)
    mytxt.SaveAs(word_to_txt, FileFormat=4)  # 4表示提取文本
    mytxt.Close()


if __name__ == '__main__':
    # filepath = os.path.abspath(r'../Files/wordtotxt/Python数据预处理.docx')
    filepath = os.path.abspath('./第二次实验所需数据/往届大数据作业/大数据高级专题第一次课后作业.docx')
    # savepath = '' # 自定义保存路径
    Word2Txt(filepath)
