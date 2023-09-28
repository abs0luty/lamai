package ast.visitor;

import ast.expression.Expression;
import ast.expression.LiteralExpression;
import ast.statement.BlockStatement;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;
import ast.statement.DoWhileStatement;
import ast.statement.FunctionDefinitionStatement;
import ast.statement.Statement;

public abstract class AbstractVisitor implements Visitor {

  @Override
  public void visit(FunctionDefinitionStatement statement) {
    visit(statement.getBody());
  }

  @Override
  public void visit(BreakStatement statement) {
  }

  @Override
  public void visit(BlockStatement statement) {
    for (Statement s : statement.getStatements()) {
      s.accept(this);
    }
  }

  @Override
  public void visit(ContinueStatement statement) {
  }

  @Override
  public void visit(DoWhileStatement statement) {
    visit(statement.getCondition());
    visit(statement.getBody());
  }

  @Override
  public void visit(Statement statement) {
    if (statement instanceof FunctionDefinitionStatement) {
      visit((FunctionDefinitionStatement) statement);
    } else if (statement instanceof BreakStatement) {
      visit((BreakStatement) statement);
    } else if (statement instanceof BlockStatement) {
      visit((BlockStatement) statement);
    } else if (statement instanceof ContinueStatement) {
      visit((ContinueStatement) statement);
    }
  }

  @Override
  public void visit(LiteralExpression expression) {
  }

  @Override
  public void visit(Expression expression) {
    if (expression instanceof LiteralExpression) {
      visit((LiteralExpression) expression);
    }
  }
}
