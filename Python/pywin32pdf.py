import fnmatch
import os
import win32com.client as wc


def Pdf2Txt(filePath, savePath=''):
    # 1 切分文件上级目录和文件名
    dirs, filename = os.path.split(filePath)
    # print(f'目录:{dirs},文件名:{filename}')
    # 2 修改转化后的文件名
    new_name = ""
    if fnmatch.fnmatch(filename, '*pdf') or fnmatch.fnmatch(filename, '*PDF'):
        new_name = filename[:-4] + '.txt'  # 截取".pdf"之前的文件名
    else:
        return
    print('新的文件名：', new_name)
    # 3 文件转化后的保存路径
    if savePath == "": savePath = dirs
    else: savePath = savePath
    pdf_to_txt = os.path.join(savePath, new_name)
    print('保存路径：', pdf_to_txt)
    # 4 加载处理应用，word转化txt
    wordapp = wc.Dispatch('Word.Application')  # 打开word应用程序
    mytxt = wordapp.Documents.Open(filePath)
    mytxt.SaveAs(pdf_to_txt, 4)  # 4表示提取文本
    mytxt.Close()


if __name__ == '__main__':
    # filepath = os.path.abspath(r'../Files/pdftotxt/Python数据预处理.docx')
    filepath = os.path.abspath('./第二次实验所需数据/历史/12.pdf')
    # savepath = '' # 自定义保存路径
    Pdf2Txt(filepath)
