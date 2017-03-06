# ShiroDemo

## 授权(主体与角色、角色与权限(资源：操作)之间是多对多的关系。)
+ 主体
 + 主体，即访问应用的用户，在Shiro中使用Subject代表该用户。用户只有授权后才允许访问相应的资源。
+ 资源
 + 在应用中用户可以访问的任何东西，比如访问JSP页面、查看/编辑某些数据、访问某个业务方法、打印文本等等都是资源。用户只要授权后才能访问。
+ 权限
 + 安全策略中的原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权力。即权限表示在应用中用户能不能访问某个资源，
+ 角色(隐身角色、显示角色)
 + 角色代表了操作集合，可以理解为权限的集合，一般情况下我们会赋予用户角色而不是权限，即这样用户可以拥有一组权限，赋予权限时比较方便。


###　授权的方式
Shiro支持三种方式的授权：

+ 编程式：通过写if/else授权代码块完成
+ 注解式：通过在执行的Java方法上放置相应的注解完成
+ JSP/GSP标签：在JSP/GSP页面通过相应的标签来完成


## Authenticator及AuthenticationStrategy
Authenticator的职责是验证用户账号，是Shiro API中身份验证核心的入口点
```
public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
```
如果验证成功，将返回AuthenticationInfo验证信息；此信息中包含了身份及凭证；如果验证失败将抛出相应的AuthenticationException实现

SecurityManage接口继承了Authenticator，另外还有一个ModularRealmAuthenticator实现，其委托给多个Realm进行验证，
验证规则通过AuthenticationStrategy接口指定，默认提供的实现：

+ FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
+ AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
+ AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。