package MyHandwrittenSpring.spring;

/**
 * @author ZAY
 * 这是一个spring容器类; spring就是将一些类放在一起统一管理，统一存储，要用哪个就根据注解或xml文件中配置的类名字拿出来用
 */
public class MyApplicationContext {
    private Class configClass;
    public MyApplicationContext(Class configClass) {
        /*
          在spring容器启动的过程中(加载此类时)要做的事情
          扫描传入的配置类中配置的包路径下是否有 有 @Component 注解的类
         */
        this.configClass = configClass;
    }
    public Object getBean(String beanName){
        return null;
    }
}
