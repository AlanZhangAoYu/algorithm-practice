import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author ZAY
 * Java比较器练习
 * 比较器: 对对象的集合进行总排序;
 * 为了对一个集合进行排序，我们将比较器(Comparator)实例传递给Stream.sorted、Collections.sort、List.sort和Arrays.sort方法。
 */
class Student{
    private int id;
    private String name;
    private int age;
    public Student(int id,String name,int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
/**
 * @author ZAY
 */
public class ComparatorPractice {
    public static class IDComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            //返回负数时, 第一个参数排在前面
            //返回正数时, 第二个参数排在前面
            //返回0时, 谁在前面无所谓
            return o1.getId() - o2.getId();
        }
    }
    public static void main(String[] args){
        Student student1=new Student(1,"ZAY",20);
        Student student2=new Student(4,"ZZZ",21);
        Student student3=new Student(2,"JIS",19);
        Student student4=new Student(3,"LKJ",20);
        ArrayList<Student> list=new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.sort(new IDComparator());
        for(Student student:list){
            System.out.println(student.toString());
        }
    }
}
