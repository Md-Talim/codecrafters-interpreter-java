class Instance {
    private Class klass;

    Instance(Class klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
