class Interpreter implements Expr.Visitor<Object> {
    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    private String stringify(Object object) {
        if (object == null)
            return "nil";
        return object.toString();
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitGroupingExpr'");
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitUnaryExpr'");
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitBinaryExpr'");
    }

    void interpret(Expr expression) {
        Object object = evaluate(expression);
        System.out.println(stringify(object));
    }
}
