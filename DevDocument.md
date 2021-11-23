# Develop Document

这是中国海洋大学 2021 年游戏开发课程实践项目的开发文档。

# 框架介绍

**开发过程中非必要不要修改框架代码，否则可能导致出现无法预知的错误！**

框架为GameFrame包中的代码：
### Position：坐标类

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | x     | float   | 坐标的X值  |
> | y      | float | 坐标的Y值 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | Add        | 两个坐标相加 |
> | Minus | 两个坐标相减 |

### GameObject：未来开发中所有具有实例的物体的父类，创建对象必须继承该类

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | name     | string   | 标识对象的**唯一**标识符   |
> | pos      | Position | 对象的坐标，坐标数值：像素=1：1 |
> | type     | Type     | 对象的类型 |
> | isCollision     | boolean     | 是否参与碰撞检测 |
> | layerLevel     | int     | 渲染优先级，高覆盖低 |
> | staticImg     | Image     | 静态贴图 |
> | imgWidth     | int     | 渲染长度(单位：像素) |
> | imgHeight     | int     | 渲染高度(单位：像素) |
> | curAnim     | string     | 当前播放动画 |
> | ic     | ImageContainer     | 动画播放组件 |
> | cb     | CollisionBox     | 碰撞盒，物理相关 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | GameObject        | 标识符、坐标、类型是必须的，其他则有默认参数 |
> | setStaticImage | 设置静态贴图，同时将当前播放动画改成静态贴图|
> | playAnimation   | 设置当前播放的动画 |
> | isOnCollision   | 基于碰撞盒检测物体间是否发生碰撞 |

### CollisionBox：碰撞盒

> | 成员参数 | 字段类型 | 说明 |
> | -------- | -------- | -------- |
> | downLeft | Position   | 碰撞盒的左下点坐标 |
> | upRight  | Position   | 碰撞盒的右上点坐标 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | isOnCollision | 检测和另一碰撞盒是否存在碰撞 |

### ImageContainer：动画播放组件

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | imgList     | ArrayList<ImageSeries>   | 动画帧序列列表 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | addAnimImages | 添加动画至帧序列 |
> | getAnim | 根据动画序列标识符返回动画序列 |

### ImageSeries：动画序列

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | curImgFrame | int | 当前播放动画序列的序列数 |
> | animName    | string | 隶属于同一动画组件的动画名必须唯一 |
> | imgList     | ArrayList<Image> | 动画播放所需的文件序列 |
> | playInterval     | int | 序列帧间播放间隔，不可为0 |
> | curFrame     | int | 距上次更新播放序列所经历的帧数 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | ImageSeries | 根据动画的文件序列生成动画序列 |

  # 程序运行框架
  
### StartGame:负责创建游戏窗口
  
### GamePanel:游戏主窗口，游戏主程序
  
 > | 成员参数 | 字段类型 | 说明     |
 > | -------- | -------- | -------- |
 > | nColObj | ArrayList<GameObject> | 非碰撞体队列，不参与碰撞检测 |
 > | colObj    | ArrayList<GameObject> | 碰撞体队列，参与碰撞检测 |
 > | layer_x     | ArrayList<GameObject> | 渲染图层，层数越高优先级越高 |
 > | camPos     | Position | 摄像机坐标，左下为原点，默认渲染1280*720坐标范围内的对象 |

 > | 成员函数 |说明     |
 > | -------- | -------- |
 > | init | 只会执行一次，数据初始化及对象创建等在该函数执行 |
 > | paint | 绘图主循环，每秒执行50次，**不要在绘图循环中做数据处理** |
 > | actionPerformed | 事件处理循环，在该函数进行数据更新，每秒执行50次 |
 > | moveCamera | 基于摄像机当前位置进行位移 |
 > | setCamera | 设置摄像机到指定位置 |
 > | addObjToList | 将对象添加至世界队列，所有对象都必须使用该函数加入队列 |
 > | destroy | 摧毁对象 |
  
### KeyEvents:键盘监听组件，负责响应键盘输入
  > | 成员参数 | 字段类型 | 说明     |
  > | -------- | -------- | -------- |
  > | gp | GamePanel | 主程序实例，不可更改 |
  
  > | 成员函数 |说明     |
 > | -------- | -------- |
 > | keyPressed | 按键按下后调用该事件，按住则会持续调用 |
 > | keyReleased | 按键松开后调用该事件 |
