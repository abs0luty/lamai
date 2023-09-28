package ast.statement;

import java.util.Optional;

import ast.expression.Expression;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class IfStatement implements Statement {

  private final Expression condition;
  private final Statement ifBody;
  private final Optional<Statement> elseBody;
  private final SegmentLocation location;

  public IfStatement(Expression condition,
      Statement ifBody, Optional<Statement> elseBody,
      SegmentLocation location) {
    this.condition = condition;
    this.ifBody = ifBody;
    this.elseBody = elseBody;
    this.location = location;
  }

  public IfStatement(Expression condition,
      Statement ifBody,
      SegmentLocation location) {
    this(condition, ifBody, Optional.empty(), location);
  }

  public IfStatement(Expression condition,
      Statement ifBody, Statement elseBody,
      SegmentLocation location) {
    this(condition, ifBody, Optional.of(elseBody), location);
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getIfBody() {
    return ifBody;
  }

  public Optional<Statement> getElseBody() {
    return elseBody;
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
