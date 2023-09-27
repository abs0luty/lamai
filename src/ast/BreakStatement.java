package ast;

import ast.visitor.Visitor;

public record BreakStatement(
        /**
         * The location of the `break `keyword.
         */
        SegmentLocation location) implements Statement {

    @Override
    public SegmentLocation getLocation() {
        return location;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
