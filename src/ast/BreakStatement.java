package ast;

import ast.visitor.Visitor;

public final class BreakStatement implements Statement {

    /**
     * The location of the `break `keyword.
     */
    private final SegmentLocation location;

    public BreakStatement(SegmentLocation location) {
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
