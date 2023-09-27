package ast.literal;

import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public final class IntegerLiteral implements Literal {
  private final SegmentLocation location;
  private final String value;

  public IntegerLiteral(SegmentLocation location, String value) {
    this.location = location;
    this.value = value;
  }

  public String getValue() {
    return value;
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
