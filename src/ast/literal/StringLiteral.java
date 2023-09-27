package ast.literal;

import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public final class StringLiteral implements Literal {

  private final SegmentLocation location;
  private final String value;

  public StringLiteral(SegmentLocation location, String value) {
    this.location = location;
    this.value = value;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }

  public String getValue() {
    return value;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
