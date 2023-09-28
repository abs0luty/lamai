package ast.visitor;

import ast.expression.Expression;
import ast.expression.LiteralExpression;
import ast.literal.BooleanLiteral;
import ast.literal.IntegerLiteral;
import ast.literal.StringLiteral;
import ast.statement.BlockStatement;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;
import ast.statement.DoWhileStatement;
import ast.statement.FunctionDefinitionStatement;
import ast.statement.Statement;

public interface Visitor {

  void visit(FunctionDefinitionStatement statement);

  void visit(BlockStatement statement);

  void visit(BreakStatement statement);

  void visit(ContinueStatement statement);

  void visit(DoWhileStatement statement);

  void visit(Statement statement);

  void visit(StringLiteral literal);

  void visit(IntegerLiteral literal);

  void visit(BooleanLiteral literal);

  void visit(LiteralExpression expression);

  void visit(Expression expression);
}
