package ast.visitor;

import ast.expression.LiteralExpression;
import ast.literal.BooleanLiteral;
import ast.literal.IntegerLiteral;
import ast.literal.StringLiteral;
import ast.statement.BlockStatement;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;

public interface Visitor {

  void visit(BlockStatement statement);

  void visit(BreakStatement statement);

  void visit(ContinueStatement statement);

  void visit(StringLiteral literal);

  void visit(IntegerLiteral literal);

  void visit(BooleanLiteral literal);

  void visit(LiteralExpression expression);
}
