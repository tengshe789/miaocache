# MiaoCache

为了毕业设计`-miaosha`商城平台准备的缓存组件，目的是为了让添加缓存更加快捷高效。
为了减少代码的强耦合。为了使用缓存更迅速。为了快捷观看缓存列表。
### 技术
保持与miaosha商城平台相同的技术栈
+ spring AOP
+ spring boot 2.0.7
+ hutools
+ fastjson
### 使用
#### 增
使用`@CreatCache`注解，标记在目标方法上
#### 删
使用`@DeleteCache`注解，标记在目标方法上
#### 改
使用`@PutCache`注解，标记在目标方法上
#### 查询
使用`@GetCache`注解，标记在目标方法上