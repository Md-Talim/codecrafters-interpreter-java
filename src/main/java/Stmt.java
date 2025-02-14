abstract class Stmt {
    interface Visitor<R> {
        R visitPrintStmt(Print stmt);
    }

    static class Print extends Stmt {
        final Expr expression;

        Print(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }
    }

    abstract <R> R accept(Visitor<R> visitor);
}
