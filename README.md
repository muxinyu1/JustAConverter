# JustAConverter

BIT安卓开发大作业

## 使用方法

在`Converter`页面中选择要转换的文件大致类型，进入转换界面再选择具体的转换格式：

<img src="C:\Users\MXY\AppData\Roaming\Typora\typora-user-images\image-20220510185105161.png" alt="image-20220510185105161" style="zoom:50%;" />

<img src="C:\Users\MXY\AppData\Roaming\Typora\typora-user-images\image-20220510185130451.png" alt="image-20220510185130451" style="zoom:50%;" />

<img src="C:\Users\MXY\AppData\Roaming\Typora\typora-user-images\image-20220510185205447.png" alt="image-20220510185205447" style="zoom:50%;" />

点击“Convert”开始转换，转换完成后会使用默认浏览器打开转换后文件的下载链接：

![image-20220510190111850](C:\Users\MXY\AppData\Roaming\Typora\typora-user-images\image-20220510190111850.png)

## 实现

使用`CloudConvert`和`AnonFiles`提供的API，作为事务后端处理。

使用`Jetpack Compose`完成UI开发。

`AnonFiles`API负责通过本地文件获得其`URL`直链(中间需要经过爬虫处理才能获取真正的链接)

`CloudConvert`API负责完成具体的文件转换过程，获得转换后文件的`URL`，再使用默认浏览器打开即可。

使用一个`ConverterScreen ViewModel`来储存转换文件的`Uri`、`from format`、`to format`，这里使用的是`by lazy`创建的单例，具体执行转换操作时，只需传入次单例即可。

## TODO

`CloudConvert`API需要认证，每天25分钟免费转换时间，这里的`app-key`用的是我自己的，但实际上应该让用户自己输入他的`api-key`，这显然需要数据库存取的功能，这里偷懒了，没做。

原本计划获得转换后的文件下载链接后，使用一些下载框架下载到本地的，但是一想还是需要数据库，所以也没做。

`Settings`功能里面原本计划有多语言和黑暗模式，但是多语言。。。懒得做了，因为有些`kt`代码里面偷懒使用了`Raw String`，找的话不太容易。黑暗模式应该不难，但是。。。也懒得做了

## 使用的API

[CloudConvert](https://cloudconvert.com/api/v2)

[AnonFiles](https://anonfiles.com/docs/api)

