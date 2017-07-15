### 文件上传下载

#### 1. 文件上传

上一次咱说到了Java的I/O流操作，那么为了加深印象，接下来我们就做个简易版的网盘系统。

用户界面基于jsp和servlet实现，不知道的同学可以来这看看咱之前的文章。

##### 1.1. 准备工作

As we know，在网页中上传文件需要在HTML中插入`<input type="file">`来实现，之后通过设置form表单的enctype=multipart/form-data属性来告诉浏览器，咱上传的是个文件，你要转化成二进制流来处理，数据流中包含文件的属性和内容。然而本文说的只是I/O流的小练习，具体过程交给开源库处理，再此不再赘述。

java中常用的来自Apache的开源工具commons-fileupload实现上传功能，它依赖于commons-io，在开始之前我们先下载这两个jar包，导入项目：
- 下载上面两个jar包，可以用Google搜索Maven commons-fileupload下载最新版
- 打开File > Project Structure
- 在Modules中导入jar包，之后下方会有提示发布目录缺少jar包，fix一下或手动加入都行

##### 1.2. HTML代码

```html

```


#### 2. 文件下载

##### 2.1. 