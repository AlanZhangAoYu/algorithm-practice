package MyHandwrittenSpring.spring;

import java.io.File;
import java.net.URL;

/**
 * @author ZAY
 * 这是一个spring容器类; spring就是将一些类放在一起统一管理，统一存储，要用哪个就根据注解或xml文件中配置的类名字拿出来用
 */
public class MyApplicationContext {
    private Class configClass;
    public MyApplicationContext(Class configClass) {
        /*
          在spring容器启动的过程中(加载MyApplicationContext时)要做的事情
          扫描传入的配置类中配置的包路径下是否有 有 @Component 注解的类
         */
        this.configClass = configClass;
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            //检查传入的配置类上是否有@ComponentScan注解, 有的话就提出来
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            //从@ComponentScan中拿到扫描包路径path，格式为: xxx.xxx.xxx
            String path = componentScanAnnotation.value();
            //以下要做的就是将path转换为操作系统可以识别的路径，让程序读取到编译好的对应包路径下的class文件 格式为: X:/XXxxx/xxxx/xxxx/...
            path = path.replace('.', '/');
            ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            //System.out.println(file);
            if(file.isDirectory()){
                //读取到包所对应的目录，将目录下每一个文件列一个表files
                File[] files=file.listFiles();
                for (File f : files) {
                    String fileAbsolutePath = f.getAbsolutePath();
                    if(fileAbsolutePath.endsWith(".class")){
                        //将对应目录下的.class文件筛选出来
                        //System.out.println(fileAbsolutePath);
                        String myHandwrittenSpring = fileAbsolutePath.substring(fileAbsolutePath.indexOf("MyHandwrittenSpring"), fileAbsolutePath.indexOf(".class"));
                        myHandwrittenSpring = myHandwrittenSpring.replace('\\', '.');
                        try {
                            //传入的参数格式必须为xxx.xxx.xxx,所以要经过一次转换
                            Class<?> aClass = classLoader.loadClass(myHandwrittenSpring);
                            if (aClass.isAnnotationPresent(Component.class)) {
                                //判断这个class文件(这个类)是不是Bean(是否被@Component注解)

                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {
                System.out.println(1);
            }
        }
    }
    public Object getBean(String beanName){
        return null;
    }
}
