# Develop Document

这是中国海洋大学 2021 年游戏开发课程实践项目的开发文档。

# 框架介绍

**开发过程中非必要不要修改框架代码，否则可能导致出现无法预知的错误！**

框架为GameFrame包中的代码：
> Position：坐标类

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | x     | float   | 坐标的X值  |
> | y      | float | 坐标的Y值 |

> | 成员函数 |说明     |
> | -------- | -------- |
> | Add        | 两个坐标相加 |
> | Minus | 两个坐标相减 |

> GameObject：未来开发中所有具有实例的物体的父类，创建对象必须继承该类

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

> CollisionBox：碰撞盒

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | downLeft     | Position   | 碰撞盒的左下点坐标 |
> | upRight      | Position   | 碰撞盒的右上点坐标 |

| 成员函数 |说明     |
> | -------- | -------- |
> | isOnCollision | 检测和另一碰撞盒是否存在碰撞 |

> ImageContainer：动画播放组件

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | imgList     | ArrayList<ImageSeries>   | 动画帧序列列表 |

| 成员函数 |说明     |
> | -------- | -------- |
> | addAnimImages | 添加动画至帧序列 |
> | getAnim | 根据动画序列标识符返回动画序列 |

> ImageSeries：动画序列

> | 成员参数 | 字段类型 | 说明     |
> | -------- | -------- | -------- |
> | curImgFrame | int | 当前播放动画序列的序列数 |
> | animName    | string | 隶属于同一动画组件的动画名必须唯一 |
> | imgList     | ArrayList<Image> | 动画播放所需的文件序列 |
> | playInterval     | int | 序列帧间播放间隔，不可为0 |
> | curFrame     | int | 距上次更新播放序列所经历的帧数 |

| 成员函数 |说明     |
> | -------- | -------- |
> | ImageSeries | 根据动画的文件序列生成动画序列 |
