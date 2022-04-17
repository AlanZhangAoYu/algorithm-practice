package ReflectExercise;

/**
 * @author ZAY
 */
public class ExampleClass {
    private final String value;
    public ExampleClass(){
        this.value = "";
    }
    public ExampleClass(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }
    public void publicMethod(String s){
        System.out.println("public-"+s);
    }
    private void privateMethod(String s){
        System.out.println("private-"+s);
    }
}
