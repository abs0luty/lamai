package ast.statement;

import ast.expression.Expression;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class ForStatement implements Statement {

  private final Statement initializationStatement;
  private final Expression condition;
  private final Statement updateStatement, body;
  private final SegmentLocation location;

  public ForStatement(Statement initializationStatement,
      Expression condition,
      Statement updateStatement, Statement body,
      SegmentLocation location) {
    this.initializationStatement = initializationStatement;
    this.condition = condition;
    this.updateStatement = updateStatement;
    this.body = body;
    this.location = location;
  }

  public Statement getInitializationStatement() {
    return initializationStatement;
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getUpdateStatement() {
    return updateStatement;
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
