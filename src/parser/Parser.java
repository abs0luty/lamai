package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ast.Identifier;
import ast.expression.Expression;
import ast.location.ByteLocation;
import ast.location.SegmentLocation;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;
import ast.statement.DoWhileStatement;
import ast.statement.ForStatement;
import ast.statement.FunctionDefinitionStatement;
import ast.statement.IfStatement;
import ast.statement.Statement;
import ast.statement.WhileStatement;
import ast.token.Token;
import ast.token.TokenKind;
import parser.exceptions.ParseException;
import parser.exceptions.ExpectedNodeException;
import parser.exceptions.ExpectedTokenException;

public final class Parser {

  private Lexer lexer;
  private Token currentToken, nextToken;

  public Parser(Lexer lexer) throws ParseException {
    this.lexer = lexer;
    this.currentToken = lexer.nextTokenExceptComment();
    this.nextToken = lexer.nextTokenExceptComment();
  }

  /**
   * Advances the lexer to the next token.
   * 
   * @throws ParseException if an error occured during processing
   *                        the next token
   */
  private void advance() throws ParseException {
    currentToken = nextToken;
    nextToken = lexer.nextTokenExceptComment();
  }

  /**
   * Parses the Lama program's source code.
   * 
   * @return a list of statements representing the program's AST
   * @throws ParseException if an error occured during parsing the
   *                        source code
   */
  public List<Statement> parse() throws ParseException {
    List<Statement> statements = new ArrayList<>();

    while (currentToken.getKind() != TokenKind.EOF) {
      statements.add(parseStatement());
    }

    return statements;
  }

  private Statement parseStatement() throws ParseException {
    switch (nextToken.getKind()) {
      case BREAK:
        return parseBreakStatement();
      case CONTINUE:
        return parseContinueStatement();
      case FN:
        return parseFunctionDefinitionStatement();
      case DO:
        return parseDoWhileStatement();
      case FOR:
        return parseForStatement();
      case WHILE:
        return parseWhileStatement();
      case IF:
        return parseIfStatement();
      default:
        throw new ExpectedNodeException("statement", nextToken);
    }
  }

  private FunctionDefinitionStatement parseFunctionDefinitionStatement() throws ParseException {
    advance();

    consume(TokenKind.OPEN_PARENTHESIS);

    final Identifier name = consumeIdentifier();
    final List<Identifier> parameters = new ArrayList<>();

    while (!currentToken.getKind().equals(TokenKind.CLOSE_PARENTHESIS)) {
      parameters.add(consumeIdentifier());
    }

    final Statement body = parseStatement();

    return new FunctionDefinitionStatement(
        name,
        parameters,
        body);
  }

  private DoWhileStatement parseDoWhileStatement() throws ParseException {
    final ByteLocation startLocation = nextToken.getLocation().getFirstByteLocation();
    advance();

    final Statement statement = parseStatement();

    consume(TokenKind.WHILE);

    final Expression condition = parseExpression();

    final SegmentLocation location = new SegmentLocation(startLocation,
        currentToken.getLocation().getLastByteLocation());

    return new DoWhileStatement(condition, statement, location);
  }

  private ForStatement parseForStatement() throws ParseException {
    final ByteLocation startLocation = nextToken.getLocation().getFirstByteLocation();
    advance();

    consume(TokenKind.OPEN_PARENTHESIS);

    final Statement initializationStatement = parseStatement();
    consume(TokenKind.COMMA);

    final Expression condition = parseExpression();
    consume(TokenKind.COMMA);

    final Statement updateStatement = parseStatement();
    consume(TokenKind.CLOSE_PARENTHESIS);

    final Statement body = parseStatement();
    final SegmentLocation location = new SegmentLocation(
        startLocation,
        currentToken.getLocation().getLastByteLocation());

    return new ForStatement(
        initializationStatement,
        condition,
        updateStatement,
        body,
        location);
  }

  private WhileStatement parseWhileStatement() throws ParseException {
    final ByteLocation startLocation = nextToken.getLocation().getFirstByteLocation();
    advance();

    consume(TokenKind.OPEN_PARENTHESIS);

    final Expression condition = parseExpression();
    consume(TokenKind.CLOSE_PARENTHESIS);

    final Statement body = parseStatement();
    final SegmentLocation location = new SegmentLocation(
        startLocation,
        currentToken.getLocation().getLastByteLocation());

    return new WhileStatement(condition, body, location);
  }

  private IfStatement parseIfStatement() throws ParseException {
    final ByteLocation startLocation = nextToken.getLocation().getFirstByteLocation();
    advance();

    final Expression condition = parseExpression();
    final Statement ifStatement = parseStatement();
    final Optional<Statement> elseStatement;

    if (nextToken.getKind().equals(TokenKind.ELSE)) {
      advance();

      elseStatement = Optional.of(parseStatement());
    } else {
      elseStatement = Optional.empty();
    }

    final SegmentLocation location = new SegmentLocation(
        startLocation,
        currentToken.getLocation().getLastByteLocation());

    return new IfStatement(condition, ifStatement, elseStatement, location);
  }

  private BreakStatement parseBreakStatement() throws ParseException {
    advance();

    return new BreakStatement(currentToken.getLocation());
  }

  private ContinueStatement parseContinueStatement() throws ParseException {
    advance();

    return new ContinueStatement(currentToken.getLocation());
  }

  private Expression parseExpression() throws ParseException {
    switch (currentToken.getKind()) {
      // TODO: add more cases
      default:
        throw new ExpectedNodeException("expression", currentToken);
    }
  }

  /**
   * Throws exception if the next token kind is not an identifier.
   * If the next token kind is an identifier, advances the lexer to
   * the next token and returns a new {@link Identifier} AST Node.
   * 
   * @return a new {@link Identifier} AST Node based on the next token
   * @throws ParseException if the condition was not met or an error
   *                        occured during processing the next token
   */
  private Identifier consumeIdentifier() throws ParseException {
    expect(TokenKind.IDENTIFIER);

    return new Identifier(
        resolveLocation(currentToken.getLocation()),
        currentToken.getLocation());
  }

  /**
   * Resolves the location of the code segment.
   * 
   * @param location the location of the code segment
   * @return the resolved location - a substring of the source code
   */
  private String resolveLocation(SegmentLocation location) {
    return lexer.getContents().substring(
        location.getFirstByteLocation().getOffset(),
        location.getLastByteLocation().getOffset());
  }

  /**
   * Throws exception if the next token kind is not an expected one.
   * If the next token kind is an expected one, advances the lexer to
   * the next token.
   * 
   * @param kind an expected token's kind
   * @throws ParseException if the condition was not met or an error
   *                        occured during processing the next token
   */
  private void consume(TokenKind kind) throws ParseException {
    expect(kind);
    advance();
  }

  /**
   * Throws exception if the next token kind is not an expected one
   * 
   * @param expected an expected token's kind
   * @throws ExpectedTokenException if the condition was not met
   */
  private void expect(TokenKind expected) throws ExpectedTokenException {
    if (nextToken.getKind() != expected) {
      throw new ExpectedTokenException(expected, nextToken);
    }
  }
}
