package ReflectExercise;

//从始至终这里都没有出现 import ReflectExercise.ExampleClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ZAY
 * 反射机制的练习
 * 不直接import那个类，只是输入类路径和方法名就可以调用类中的方法
 */
public class ReflectExercise {
    public static void main(String[] args){
        try {
            //获取ExampleClass类的Class对象
            Class<?> targetClass = Class.forName("ReflectExercise.ExampleClass");
            //创建ExampleClass类的实例
            ExampleClass exampleClass = (ExampleClass) targetClass.newInstance();
            //获取ExampleClass类中的所有方法
            Method[] methods = targetClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            //获取指定的方法(public方法)
            Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
            //调用该public方法
            publicMethod.invoke(exampleClass,"ZAY");
            //获取类中的参数
            Field field = targetClass.getDeclaredField("value");
            //为了对private参数进行修改，取消安全检查
            field.setAccessible(true);
            //设置类中的private参数
            field.set(exampleClass,"ZAY");
            //调用getValue方法检查参数是否修改成功
            Method getValue = targetClass.getDeclaredMethod("getValue");
            System.out.println("value-"+getValue.invoke(exampleClass).toString());
            //获取指定的方法(private方法)
            Method privateMethod = targetClass.getDeclaredMethod("privateMethod", String.class);
            //为了调用private方法，取消安全检查
            privateMethod.setAccessible(true);
            //调用private方法
            privateMethod.invoke(exampleClass,"ZAY");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
