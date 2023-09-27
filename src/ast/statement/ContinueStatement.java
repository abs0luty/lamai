package ast.statement;

import ast.literal.Statement;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class ContinueStatement implements Statement {

  /**
   * The location of the `continue `keyword.
   */
  private final SegmentLocation location;

  public ContinueStatement(SegmentLocation location) {
    this.location = location;
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
