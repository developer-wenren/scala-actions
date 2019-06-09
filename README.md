#  Java 开发看的 Scala 入门

[TOC]

![每篇一句](http://ww1.sinaimg.cn/large/006tNc79ly1g3uqfddf10j30p00dwtat.jpg)

## 前言

对于 Scala 语言其实很早有所耳闻，但没有真正进一步了解，只知道这门语言在大数据领域很火。正如前几年大数据开发的兴起，也着实让这门基于 JVM 的语言火了一把。由于近期开始参与公司的大数据项目，面对大数据量计算处理需求，基于目前自己 Java 的技术栈远远不够，不得不引入 Spark 之类的大数据框架，而 Spark 是由 Scala 编写的，虽说 Spark 提供 Java API，但为了能更好了使用 Spark 和及时排查可能遇到问题，还是有必要全面学习下 Scala 这门语言。

本文主要内容涉及如下：

- Scala 基本介绍
- Scala 语法特性介绍
- Scala 与 SpringBoot 实战

> 示例项目：https://github.com/wrcj12138aaa/scala-actions
>
> - scala-actions：
>
> 环境支持：
>
> - JDK 8
> - SpringBoot 2.1.5
> - Maven 3.6.0
> - Scala 2.12.8

## 认识 Scala

在认识 Scala 之前，我们先来弄清楚 Scala 的读法和来历。尤其是作为中国程序员，头疼的就是面对新技术出现的单词，自己却读不出来或者读得很变扭，这里我在[百度翻译](https://fanyi.baidu.com/?aldtype=16047#en/zh/Scala)中查到了 Scala 的音标 `[ˈskɑlə]` 和单词发音，可以供大家参考。

接着再简单看下 Scala 的历史：Scala 是由洛桑联邦理工学院的 Martin Odersky 教授主导设计的一门编程语言，面对 Java 严格的语言规范限制，Martin 教授基于 JVM 重新设计了一门更加现代化，可扩展的语言， 并且将这门 Scalable Language 的缩写 Scala 作为命名，这就是 Scala 的来由。从 2003 年底基于 Java 平台的 Scala 发布，到现在稳定版本已经到了 2.12.8，可以说 Scala 的迭代速度还是很快的

好了说到正题，要学习 Scala 这门编程语言，首先来解读下官方对 Scala 的描述：

> Scala combines object-oriented and functional programming in one concise, high-level language. Scala's static types help avoid bugs in complex applications, and its JVM and JavaScript runtimes let you build high-performance systems with easy access to huge ecosystems of libraries.

从上面这段话里，我们可以提取到以下信息：

1. Scala 是一门静态类型的高级编程语言，结合了面对对象和面向函数编程的特性。
2. Scala 运行在 JVM 以及 JavaScript 运行时环境上编写程序。
3. Scala 常用于构建高性能程序和框架。

关于 Scala 语言的丰富特性我们将在下文的 Scala 语法 一节去学习和感受；而针对构建高性能程序和框架这一点，目前大数据库领域的项目框架 Spark，Flink，Kafka 等都是基于 Scala 开发的，说明了使用 Scala 对高性能场景下的帮助是毫无疑问的。

### Scala 的语言特性

- 即是属于面向对象的语言，也是函数式语言
- 静态类型语言，这一点跟 Java 一样，但是还支持类型推断，也就是说不用指定数据类型信息。
- 与 Java 生态无缝接合，可以调用 Java 库以及与 Java 混合开发
- 可扩展性，可以根据自身需要定制扩展语言的功能

整体了解 Scala 之后，接下来我们就开始快速搭建 Scala 环境，然后写我们的第一行 Scala 代码吧。

## Scala 环境搭建

搭建 Scala 环境其实很简单，类比配置 Java 语言环境的经历，总结下来就两步骤：

> 注意：下载 Scala 安装包之前，需要本机环境有 JDK 8 以上 才行。

1. 从[Scala 官网](https://www.scala-lang.org/download/)下载 2.12.8 版本的语言压缩包，并解压到本地磁盘。

   ![image-20190608011135438](http://ww1.sinaimg.cn/large/006tNc79ly1g3t3e2xzjtj30u00uxadp.jpg)

2. 添加 Scala 环境变量，指定为所解压的 Scala 文件夹，由于我本机为 Mac 环境，所以只需在 Shell 配置文件里操作即可，修改 Shell 配置文件 `~/.bash_profile` 文件底部添加下面内容, 使用命令`source ~/.bash_profile` 使配置生效。

   ![carbon](http://ww1.sinaimg.cn/large/006tNc79ly1g3tjwzlrcwj311s08caam.jpg)

完成上述步骤后，命令行输入 `scala -version` 得到跟下图内容一样时就表示 Scala 语言环境搞定了。

![查看 Scala 版本](http://ww3.sinaimg.cn/large/006tNc79ly1g3t48j43tkj316a06eq3h.jpg)



## Scala 命令行交互

#### Scala REPL

不同于 Java，Scala 还提供了命令行模式下的交互，专业说法为 REPL(Read-eval-print-loop)，详细介绍可以参见[Scala REPL OVERVIEW][scala repl overview]；基于此我们可以直接在命令行上使用 Scala 语言，如果有过 Python 或者 Node.js 的同学应该会熟悉这个编程交互方式。

首先在命令行里输入 `scala` 进去交互模式，然后输入 `println("Hello,Scala")` 回车，这样一个最简单的 Scala 版 HelloWorld 就是完成了。

![REPL](http://ww1.sinaimg.cn/large/006tNc79ly1g3t4h6wz94j311s0i2q40.jpg)

这里的使用到的 `println`就是 Scala 中打印方法，类似 Java 中的`System.out.println`，是不是很简洁呢。

#### 编译 Scala 类

除了使用命令行交互方式之外，我们再试着实现一个 Scala 类的 HelloWorld。

首先新建一个 `HelloWorld.scala` 文件, 写一个 `main` 方法。

![HelloWord.scala](http://ww1.sinaimg.cn/large/006tNc79ly1g3upe2vzdaj311s09i0tl.jpg)

看起来，Scala 的写法跟 Java 的 `main` 方法是不是很不一样呢，先试着跑起来，我们后面再对语法进一步学习。

接下来就需要对源代码进行编译允许了，类似 Java ，Scala 库提供了 Scala 编译器工具 scalac，可以对 Scala 文件编译成字节码；还有解释器工具 `scala` 用于运行字节码。

使用 scalac 进行编译生成 `HelloWorld.class` 文件；

```scala
scalac HelloWorld.scala
```

最后若要运行，执行 `scala HelloWorld` ,控制台输入 `HelloWorld` 就说明运行成功了。

![HelloWorld](http://ww4.sinaimg.cn/large/006tNc79ly1g3upkz8bm3j311s04uq31.jpg)

看过最简易的两种 Scala 版 HelloWorld 之后，我们开始学习一下 Scala 基础语法和特性吧。

## Scala 语法

### 变量

Scala 声明变量的关键字有两个：`var` & `val`。 `var` 用于可变变量的声明，是 `variable` 的简写；var 用于常量的声明，是 `value`的简写。下面是一般的写法，定义的变量名与类型之间，需要用一个冒号`:` 隔开。

```sql
val x: Int = 1+1
var y: String = "abc"
```

> Scala 中允许不使用分号表示语句结束，换行即认为新语句的开始

但与 Java 声明变量时必须要指定变量类型不同， Scala 具有类型推断的特性，就需要显示地声明类型,因此我们可以写成下面形式：

```scala
val x = 1+1
var y = "abc"
```

现在再来说下 `var` 与 `val` 的区别：`val` 用于声明的常量，当赋值之后就不能再改，尝试修改值时会编译错误。

![carbon](http://ww4.sinaimg.cn/large/006tNc79ly1g3tm3vlukwj311s09i74v.jpg)

另外，`val` 变量赋值为一个引用对象时，可以改变对象的属性，但不能再指向其他对象，所以 `val`可以理解为 Java 中用 `final` 修饰的变量。

### 数据类型

在 Scala 中一切都是对象，首先看下官方提供的 Scala 类型层次图，有跟 Java 相同的数据类型，也有 Scala 特有的类型。

![Scala 类型层次图](http://ww1.sinaimg.cn/large/006tNc79ly1g3tmg50zavj314r0d3gnc.jpg)

Java 中有八大基础数据，Scala 则有九个基础数据类型， 除了一样有 `Double`，`Float`，`Long`，`Int`，`Short`，`Byte`，`Boolean`，还额外多一个 `Unit` 类型，它表示不带任何意义的类型，且仅仅有一种值：`()` ，可以理解为 Java 中的 `void`，在函数定义时使用表示该函数无返回值。

这些基础类型都有一个父类 `AnyVal`，它表示通用的值类型，与 `AnyVal`相对的就是引用类型 `AnyRef`, Scala 中的集合对象（`Set`，`Map`），字符串类(`String`) ，以及自定义的类则都属于 `AnyRef`，相当于 Java 中的 `java.lang.Object`。

在 `AnyVal` 和 `AnyRef` 之上的就是 Scala 最顶级的类型 ，它定义了一些最通用的方法如 `equals`，`hashCode` 和 `toString`。

#### Nothing 和 Null

Scala 类型层次图底部还有两个类型 `Nothing` 和 `Null`，`Nothing`是所有类型的子类型，该类型下没有任何一个值，主要用于程序的异常和中断的非正常返回；而 `Null` 为引用类型的子类型，只有一个值：`null` ，主要是在与 Java 交互时使用。

#### 值类型转换

不同的值类型，Scala 也支持类型转换，并且是单向的,下面是值转换的方向图。

![值类型转换](http://ww2.sinaimg.cn/large/006tNc79ly1g3tng1yu8vj30j604tglq.jpg)

而如果尝试非指定方向的类型转换，就不会通过编译，直接提示错误： `type mismatch`，如下示例：

![mismatch](http://ww2.sinaimg.cn/large/006tNc79ly1g3tnn55trcj311s0ni0ue.jpg)

### 方法/函数

在 Scala 中方法和函数通常情况下可以认为是一个东西，只是函数可以作为变量单独使用，可以理解为 Java8 的 Lambda 表达式，首先来看下他们如何定义，这一点跟 Java 还是有很大不同的。

![方法/函数](http://ww3.sinaimg.cn/large/006tNc79ly1g3tpb6vhkbj311s0fcjsl.jpg)

上面是函数/方法最简化的定义方式，针对单行的表达式，Scala 是允许省略大括号和 `return` 关键字的。

首先看下函数的定义，`=>` 左边为参数列表(这里的类型不能省略)，右边的为表达式内容，从上面的 `add` 函数变量类型输出可以看出 `add` 函数变量类型为 `(Int, Int) => Int`，底层是一个 Lambda 相关的对象。

> 匿名函数,也叫做闭包：表示没有名字的函数，将定义的函数没有指定变量名称直接使用，如 `(x: Int) => x + 1`

Scala 定义方法需要使用 def 关键字，这是 Java 所没有的，并且 `def` 后面紧跟方法名称，参数列表，返回类型和方法体，下面给出常见的方法定义语法

![定义方法](http://ww1.sinaimg.cn/large/006tNc79ly1g3tpurahhtj311s076q3e.jpg)

需要注意的是，**在 Scala 方法中默认将最后一行的语句结果作为返回值** 。（跟 Java 一样，Scala 也有 `return` 关键字，但为了简洁很少使用）。而如果方法定义返回值类型为 `Unit`时，则表示该方法没有返回值。

![return](http://ww2.sinaimg.cn/large/006tNc79ly1g3tq03pc1kj311s060wep.jpg)

除此之外，如果调用的函数或者方法没有入参，可以把括号省略。

接下 Scala 函数/方法中所支持的一些特性：可变参数，默认参数，命名参数:

1.可变参数

Scala 允许指明函数的最后一个参数是可以重复的，在参数类型后面放星号 `*`，表示可重复的参数，Java 中使用 `...`：

![可变参数](http://ww2.sinaimg.cn/large/006tNc79ly1g3tqcy6z0rj311s08c0tj.jpg)

同样函数内部用一个数组接收可变参数。

2.默认参数

Scala 允许为函数参数指定默认值，这样即使调用函数过程中不传递参数，函数就是采用它的默认参数值，如果传了参数，默认值就会被忽略，这一特性是 Java 所没有的,但使用起来也很方便简单。

![默认参数](http://ww3.sinaimg.cn/large/006tNc79ly1g3tqizldp7j311s08c74k.jpg)

3.命名参数

尽管函数定义时指定了参数列表顺序，但是 Scala 支持不按照定义顺序，按照指定参数名字进行参数传递，实例如下：

![命名参数](http://ww4.sinaimg.cn/large/006tNc79ly1g3tqq3ljmoj311s0budgl.jpg)

### 流程控制

程序中通常涉及的流程控制无法两种：条件判断和循环处理。

#### 条件判断

条件判断上，Scala 有与 Java 一样的 `if-else` 语法，除此之外，Scala 提供了**模式匹配**的机制进行条件判断。

它是 Java 的 `switch` 语句的升级版，首先看下语法：

![模式匹配](http://ww3.sinaimg.cn/large/006tNc79ly1g3ttacxntgj311s0aoglz.jpg)

这是使用新关键字 `match` , 左侧为被匹配的值，而右侧包含了四个 `case` 表达式，每个表示一种条件，最后的 `case _` 表示匹配其余所有情况，类似 `switch-case` 语法的 `default` 作用。

`match` 表达式允许接受返回值，正如上方式示例会返回 `String` 类型，所以模式匹配通常定义在函数中，如下：

![模式匹配2](http://ww1.sinaimg.cn/large/006tNc79ly1g3ttfm3of9j311s0aoq3j.jpg)

如果匹配的条件里要执行多个语句，则就需要使用大括号 `{}`包含了，需要注意的是模式匹配一旦某个条件匹配成功，就不会执行其他条件的语句，自带了 `break` 的效果。

Scala 的模式匹配在本文仅进行简单的用法演示，还有丰富的用法详细可见[官方文档-模式匹配一节](https://docs.scala-lang.org/zh-cn/tour/pattern-matching.html)

#### 循环处理

Scala 循环语法比较特别，使用 `for` + `<-` 来进行遍历元素，并提供了便捷的 `until`方法，`to`方法来实现遍历, 需要注意的就是 `until`方法采用半闭区间，不包含索引最后一位。而 `until`方法，`to`方法底层都是构建 Range 对象然后进行遍历。

![Scala  循环](http://ww4.sinaimg.cn/large/006tNc79ly1g3tu3yt8lej311s0pu0ug.jpg)

除了上面简单用法之外，在 `for` 循环中还可以配合 `if` 条件进行遍历过滤，如下面简单的示例，是不是很灵活呢。

![for](http://ww4.sinaimg.cn/large/006tNc79ly1g3tudmenlvj311s076wep.jpg)

### 类

Scala 中类的概念与 Java 的基本一致，而语法形式和用法上更加丰富灵活。

先看下类的定义，没看错下面是 Scala 最简单的类的定义和使用了，创建了一个 `User` 对象，赋值给了 `user1` 变量。

![类的定义和使用](http://ww1.sinaimg.cn/large/006tNc79ly1g3tv91crvej311s0600sr.jpg)

`new` 关键字用于创建实例，由于当前 `User` 没有定义任何构造器，因此只有一个默认的无参构造器，可以直接省略括号`()`。

#### 构造器

再看下一个带有自定义构造器的类如何定义，看起来是不是很奇怪，紧跟类名括号后面的就是构造器所需的参数。

![构造器](http://ww1.sinaimg.cn/large/006tNc79ly1g3tvij2axfj311s08cwf8.jpg)

这里会有个疑问：如果有多个构造器函数，那这个类都该如何定义呢？来看下示例写法，基于原有构造器，用 `def this(...)`定义了一个新的方法，内部执行 `this(name，age)` 实际调用了原来的构造器。Scala 将这个定义方法成为辅助构造器，紧跟类名后的为主构造器。

![多个构造器](http://ww4.sinaimg.cn/large/006tNc79ly1g3tvsr2vloj319k0ho0ul.jpg)

Scala 允许一个类有多个辅助构造器，但只能有一个主构造器，并且辅助构造器内部第一行必须调用主构造器，这里是可以通过多个辅助构造器间接地调用主构造器。

如果辅助构造器有额外的构造参数，则需要用添加字段关联，不会像主构造器一样自动生成字段如上面 `Person` 类的 `name`和 `age`。

#### 成员变量和 Getter/Setter 语法

在 Java 开发中，通常我们会将 Java Bean 的成员变量私有化，然后提供 Getter/Setter 方法供外部操作该变量，那边在 Scala 类中有该如何操作呢，其实也很简单，首先看下示例。成员变量的成员默认是`public`的，可以使用`private`访问修饰符可以在函数外部隐藏它们。

![成员变量](http://ww2.sinaimg.cn/large/006tNc79ly1g3u18kjoc1j311s0iudht.jpg)

注意这边字段声明时使用了 `_` 关键字,这个在 Scala 中表示特定类型的默认值，用于占位的作用，如果对于为 String 类型，则该值就是空字符串。

#### 继承重写

继承是面向对象语言的重要的特性之一，Scala 在继承方面也跟 Java 一样，使用 `extends` 关键字指向父类。继承情况有两种，一种为父类采用了默认构造器，另外一种就是父类有自定义的构造器，子类定义时必须满足父类构造器参数的要求，先会执行父类的主构造器，在执行子类自己的构造器。

![继承](http://ww3.sinaimg.cn/large/006tNc79ly1g3u1igr9krj314e0gitbq.jpg)

当子类继承父类后，想要重写父类方法也十分简单，使用 `override` 关键字修饰需要重写的访问，子类默认执行父类方法例如这样的形式 `super.foo()`,我们只需调整为自定义的实现即可。

#### Trait

Trait 翻译过来就是特征，特性的意思，看似比较新颖，其实它就类似于 Java 的接口，用于扩展我们的类。跟 Java 的 Interface 一样，Trait 也无法被实例化，通常提供若干个抽象方法和字段，由具体的类使用 `extends` 关键字实现。

有一点不同的是，当类需要实现多个 Trait 时，`extends` 只能指向一个 Trait，其余的 Trait 都需要使用 `with` 关键字 关联，如下方给出的示例。

![Trait](http://ww2.sinaimg.cn/large/006tNc79ly1g3u1uo3daij311s076mxz.jpg)

#### Case Class

Case Class 是 Scala 特有的一种特殊的类，定义方式虽然跟普通类一样，但需要使用 `case class` 组合关键字修饰。主要用于不可变的数据和模式匹配中。

![Case Class](http://ww2.sinaimg.cn/large/006tNc79ly1g3u28gbcdej311s060jrw.jpg)

实例化 Case Class 类时不需要使用 `new` 关键字，而是有一个默认的`apply`方法来负责对象的创建。并且赋值字段都是默认用`public val`修饰，不可更改。

额外注意的是当两个 Case Class 对象进行 `==` 比较的时候，Scala 是按照值比较，而不是引用比较。

#### 单例对象

Scala 将单例对象单独用 object 关键字单独声明出来，定义方式用类一样, 如 `object Box`。但单例对象也是属于一种特殊的类，有且只有一个实例，并且当它第一次被使用时才会创建。

伴生对象与伴生类
Scala 允许一个单例对象与某个类共用一个名称，而这样两者形成了伴生的关系，单例对象成为该类的伴生对象，而类成为单例对象的伴生类。伴生的关系主要体现在于，伴生对象里定义的成员，可以在伴生类上使用，这一点就是好比在 Java 中 `static` 成员。

![carbon](http://ww2.sinaimg.cn/large/006tNc79ly1g3u55bv6ryj311s0iuwg5.jpg)

#### Tuple 元组

元组也属于 Scala 中一个特殊的类，它允许包含不同类型的元素，并且为不可变。常见的用法就是当我们需要函数返回多个值时，这点就可强大了。

首先看下元组的创建和使用

![Tuple](http://ww4.sinaimg.cn/large/006tNc79ly1g3u5fmjg7cj311s060wey.jpg)

Tuple2 类属于 Scala 定义的元素类，从 `Tuple2`...`Tuple22` 一共有 21 个这样类，每个类表示当前所对应的参数个数，也就是元组中最多包含 22 个参数。

元组访问起来也很方便，用`tuple._n`方式去取第 n 个元素。

### 集合

除了兼容 Java 的集合框架，Scala 还提供了丰富强大的集合类，我们主要来学习下 Scala 中最常用的几种集合。

#### Set

一种不包含重复元素的容器对象，分为不可变 Set 和可变 Set 两种，默认为不可变。

![Set](http://ww1.sinaimg.cn/large/006tNc79ly1g3u4uod1vuj311s0e6765.jpg)

不可变 Set 常用的操作方法有

- `xs.contains(x)`，`xs(x)` 判断集合是否存在该元素
- `xs intersect ys`，`xs & ys` 两个集合取交集
- `xs union ys`，`xs | ys` 两个集合取并集
- `xs diff ys`，`xs &~ ys` 两个集合取差集

可变 Set 常用的操作方法有

- `xs add x` 添加到集合
- `xs remove x` 从集合中移除
- `xs.clear()` 清空集合
- `xs(x) = b` ，`xs.update(x, b)` 将集合更新

#### Map

Map 集合特点就是以键值对结构存储。使用语法上有两种：

- `key -> value` 方式添加元素
- `(key, value)` 方式添加元素

Map 集合与 Set 集合类似，也有不可变和可变之分，默认为不可变。

![Map](http://ww4.sinaimg.cn/large/006tNc79ly1g3u59dhi2cj315s0iudij.jpg)

常用的 Map 操作有

- `map.keys` 返回含有所有 key 的可迭代对象
- `map.keySet`返回一个含所有 key 的集合
- `map.values` 返回一个含所有 value 的可迭代对象
- `map(k) = v` 更新元素
- `map.put(k, v)` 新增元素
- `map.remove(k)` 移除元素
- `map.clear()`清空 Map 集合

#### 数组

Scala 数组与 Java 数组是一一对应的。同样把 Array 作为数组的标识符，下面先看下如何声明一个定长数组和简单使用。

![定长数组](http://ww3.sinaimg.cn/large/006tNc79ly1g3tsd0dd6fj311s0budgz.jpg)

访问数组指定元素时采用括号+索引的形式，当数组元素没有值时，用 `null` 表示。

Scala 除了定长数组之外，还提供了可变数组的实现 `ArrayBuffers`，这个 Scala 提供的可变容器的一种数组实现，能直接访问和修改底层数组，具体使用方式如下：

![Array Buffers](http://ww4.sinaimg.cn/large/006tNc79ly1g3tsp6nu42j311s0iu0v2.jpg)

> Scala 提供了许多可变容器集合，如 ArrayBuffer，ListBuffer，链表，队列等等，都封装在 `scala.collection.mutable.*` 包下。

使用数组就会有越界访问的情况，当 Scala 数组出现越界访问时，抛出 `java.lang.IndexOutOfBoundsException`异常。

![越界访问](http://ww3.sinaimg.cn/large/006tNc79ly1g3tt3jmy9lj311s09i75x.jpg)

## Scala 实战：Scala + Spring Boot

接触了那么多 Scala 语法和特性之后，我们通过用 Scala 语言来编写简单的 Spring Boot Web 应用访问 hello 请求，来看下它是如何跟 Java 混合开发的。

我们使用 IDEA 作为开发工具，要使用 Scala 语言，为了让 IDE 提供更好的 Scala 语言支持，可以先安装 Scala 插件

![Scala 插件](http://ww1.sinaimg.cn/large/006tNc79ly1g3u5uhfsy2j31h20a8757.jpg)

我们可以通过 https://start.spring.io/ 创建一个普通的 SpringBoot 项目，下载到本地后解压用 IDEA 打开。

基于原来的目录接口，新建一个 scala 源代码目录。

![ scala 源代码目录](http://ww4.sinaimg.cn/large/006tNc79ly1g3u66jym8dj30c80860t0.jpg)

Scala 的源代码目录创建后，转到 Pom 文件，修改 XML 配置：

1. 首先引入 Scala 语言库, 这里版本为 2.12.8![Scala 语言库](http://ww3.sinaimg.cn/large/006tNc79ly1g3u60fb7cbj311s08c0tg.jpg)

2. 加入 Scala 依赖包之后，我们还需要添加一个用于编译 Scala 的 Maven Plguin。![Maven Plguin](http://ww2.sinaimg.cn/large/006tNc79ly1g3u64hijmyj30u00yftc9.jpg)

好了，到此 Scala 的项目配置已经完成，接下来就可以编写 Scala 代码了。

> 为了接受 hello 请求，项目 POM 先别忘了依赖 `spring-boot-starter-web`

新建一个 Scala 类 `HelloContrroller.scala`，实现一个接受 `/hello` 请求的方法，如下![HelloContrroller.scala](http://ww4.sinaimg.cn/large/006tNc79ly1g3u69ipzypj311s0iu75t.jpg)

然后直接启动项目引导类 `com.blog4one.learn.ScalaActionsApplication`，启动日志如下则表示启动成功，默认服务监听 8080 端口。

![启动日志](http://ww2.sinaimg.cn/large/006tNc79ly1g3u6c4q5k7j319i0u0ag8.jpg)

打开浏览器，输入 `http://localhost:8080/hello`，回车即可看到结果。

![image-20190608234156015](http://ww3.sinaimg.cn/large/006tNc79ly1g3u6eeaaq1j30fc062t8s.jpg)

浏览器成功的响应，也说明了 Scala 编写的 `HelloContrroller.scala` 能接受并处理响应，而底层仍然是基于 Java 框架 Spring Boot 构建。

## 结语

好了，基本的 Scala 语法和特性先介绍到这里，通过这次学习想必也看到了 Scala 虽然是基于 JVM 上运行，但是语法和功能都极其灵活，想要掌握还需要花一定精力时间， 后续继续对 Scala 高级特性和用法进行介绍，感兴趣的小伙伴可以关注我的微信公众号（头图），会在第一时间更新哈。

## 参考

- Scala 之百度翻译： https://fanyi.baidu.com/?aldtype=16047#en/zh/Scala
- Introduction to Scala：https://www.baeldung.com/scala-intro
- 从 Java 到 Scala，再到 Kotlin：https://juejin.im/post/5cf091e9f265da1bbb03c0c7
- Scala - 维基百科，自由的百科全书：https://zh.wikipedia.org/wiki/Scala
- Learn Scala in Y Minutes：https://learnxinyminutes.com/docs/zh-cn/scala-cn/
- GETTING STARTED WITH SCALA IN INTELLIJ：https://docs.scala-lang.org/getting-started-intellij-track/getting-started-with-scala-in-intellij.html
- TOUR OF SCALA：https://docs.scala-lang.org/tour/tour-of-scala.html
- Scala 课堂：https://twitter.github.io/scala_school/zh_cn/index.html
- Scala 开发者的 SpringBoot 快速入门指南：https://afoo.me/posts/2015-07-21-scala-developers-springboot-guide.html
