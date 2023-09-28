package ast.statement;

import ast.expression.Expression;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class ReturnStatement implements Statement {

  private final Expression expression;
  private final SegmentLocation location;

  public ReturnStatement(Expression expression, SegmentLocation location) {
    this.expression = expression;
    this.location = location;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
