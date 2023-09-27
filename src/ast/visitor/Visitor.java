package ast.visitor;

import ast.BreakStatement;

public interface Visitor {
  void visit(BreakStatement statement);
}
