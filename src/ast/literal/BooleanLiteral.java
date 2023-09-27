package ast.literal;

import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public final class BooleanLiteral implements Literal {

  private final SegmentLocation location;
  private final boolean value;

  public BooleanLiteral(SegmentLocation location, boolean value) {
    this.location = location;
    this.value = value;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }

  public boolean getValue() {
    return value;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
