abstract class Expr {
    interface Visitor<R> {
        R visitLiteralExpr(Literal expr);
    }

    static class Literal extends Expr {
        final Object value;

        Literal(Object value) {
            this.value = value;
        }

        @Override
        <R> R accept(Expr.Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }

    abstract <R> R accept(Visitor<R> visitor);
}
