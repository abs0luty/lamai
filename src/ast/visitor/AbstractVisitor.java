package ast.visitor;

import ast.literal.Statement;
import ast.statement.BlockStatement;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;

public abstract class AbstractVisitor implements Visitor {

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
}
