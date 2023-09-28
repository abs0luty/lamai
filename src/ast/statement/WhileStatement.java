package ast.statement;

import ast.expression.Expression;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class WhileStatement implements Statement {

  private final Expression condition;
  private final Statement body;
  private final SegmentLocation location;

  public WhileStatement(Expression condition, Statement body, SegmentLocation location) {
    this.condition = condition;
    this.body = body;
    this.location = location;
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getBody() {
    return body;
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
