package ast.visitor;

import ast.BreakStatement;

public abstract class AbstractVisitor implements Visitor {

  @Override
  public void visit(BreakStatement statement) {
  }
}
