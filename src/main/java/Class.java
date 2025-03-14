import java.util.List;
import java.util.Map;

class Class implements Callable {
    final String name;
    private final Map<String, Function> methods;

    Class(String name, Map<String, Function> methods) {
        this.name = name;
        this.methods = methods;
    }

    public Function findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }
        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Instance instance = new Instance(this);
        return instance;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
