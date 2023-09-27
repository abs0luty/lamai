package ast.expression;

import ast.literal.Literal;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class LiteralExpression implements Expression {

  private final Literal literal;

  public LiteralExpression(Literal literal) {
    this.literal = literal;
  }

  public Literal getLiteral() {
    return literal;
  }

  @Override
  public SegmentLocation getLocation() {
    return literal.getLocation();
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
