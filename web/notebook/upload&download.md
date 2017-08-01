### 文件上传下载

#### 1. 文件上传

上一次咱说到了Java的I/O流操作，那么为了加深印象，接下来我们就做个简易版的网盘系统。

用户界面基于jsp和servlet实现，不知道的同学可以来这看看咱之前的文章。

##### 1.1. 准备工作

As we know，在网页中上传文件需要用`<input type="fileDemo">`来实现，然后用form表单的enctype=multipart/form-data属性来告诉浏览器，咱上传的是个文件，你要帮我转成二进制上传到服务器，数据流中包含文件的属性和内容。

然而本文说的只是I/O流的小练习，具体过程就交给开源库处理，在此不再赘述。

java中常用的来自Apache的开源工具commons-fileupload实现上传功能，它依赖于commons-io，在开始之前我们先下载这两个jar包，导入项目：
- 下载上面两个jar包，可以用Google搜索Maven commons-fileupload下载最新版
- 打开File > Project Structure
- 在Modules中导入jar包，下方会有提示发布目录缺少jar包，fix一下或手动导入即可

##### 1.2. mariaDB数据库配置

虽然使用Java里的Map等集合也能当作文件目录，但程序结束后内存中的数据也会丢失，为了下次使用还能找回数据，我们可以将文件目录数据实时保存到一个本地文件或直接存到数据库中，这里用的是MariaDB。

MariaDB基于MySQL并遵循GPL v2授权使用的，mysql的完美替代品，有更多更好的新特性。

我们先来建个表：

```mysql
-- auto-generated definition
create table <数据库名>.file_catalog
(
	id int auto_increment primary key,
	name varchar(32) not null,
	date datetime not null,
	constraint file_catalog_id_uindex unique (id),
	constraint file_catalog_name_uindex unique (name)
);
```
其中id是主键，name是文件名。

##### 1.3. file类

为了方便起见，我们还要在java项目中写个文件类，用于临时存放文件属性，比如名称，大小，上传时间什么的。

代码如下：

```java
//    文件类的私有属性，与mariaDB相对应
    private int id;
    private String name;
    private Date date;
    
```
有了文件类，接下来还要想办法把里面的数据存入数据库。在MVC设计模式中，Dao层直接操作数据库，Service层处理数据并通过Dao层存取数据库

##### 1.4. HTML代码

用户交互界面咱用的是jsp，有上传、下载、删除、文件列表等功能，时间允许的话还会加上翻页。

简易的jsp代码如下，用于测试：

```html

```


#### 2. 文件下载

##### 2.1. 