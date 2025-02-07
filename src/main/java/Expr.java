abstract class Expr {
    interface Visitor<R> {
        R visitLiteralExpr(Literal expr);

        R visitGroupingExpr(Grouping expr);
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

    static class Grouping extends Expr {
        final Expr expression;

        Grouping(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Expr.Visitor<R> visitor) {
            return visitor.visitGroupingExpr(this);
        }
    }

    abstract <R> R accept(Visitor<R> visitor);
}
