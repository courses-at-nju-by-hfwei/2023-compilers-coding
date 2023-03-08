package dragon;

public class DragonLexer extends Lexer {
  private final KeywordTable kwTable = new KeywordTable();

  public DragonLexer(String input) {
    super(input);
  }

  @Override
  public Token nextToken() {
    // add code below for WS, ID, INT (NUMBER)
    if (peek == EOF) {
      return Token.EOF;
    }

    if (Character.isWhitespace(peek)) {
      return WS();
    }

    if (Character.isLetter(peek)) {
      return ID();
    }

    if (Character.isDigit(peek)) {
      return INT();
    }

    // add code below for relop

    // unknown tokens (characters)
    Token unknown = new Token(TokenType.UNKNOWN, Character.toString(peek));
    advance();
    return unknown;
  }

  private Token WS() {
    // add code below
    while (Character.isWhitespace(peek)) {
      advance();
    }

    return Token.WS;
  }

  private Token ID() {
    // add code below
    StringBuilder sb = new StringBuilder();

    do {
      sb.append(peek);
      advance();
    } while (Character.isLetterOrDigit(peek));

    Token token = this.kwTable.getKeyword(sb.toString());
    if (token == null) {
        return new Token(TokenType.ID, sb.toString());
    }

    return token;
  }

  private Token INT() {
    // add code below

    return null;
  }

  private Token NUMBER() {
    StringBuilder intStr = new StringBuilder();
    intStr.append(peek);
    advance();

    // add code below for recording positions

    // add code below for storing scanned characters

    int state = 13;
    while (true) {
      switch (state) {
        case 13:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 13;
            break;
          } else if (peek == '.') {
            // add code below for recording scanned characters

            advance();
            state = 14;
            break;
          } else if (peek == 'E' || peek == 'e') {
            // add code below for recording scanned characters

            advance();
            state = 16;
            break;
          } else {
            // add code for recognizing and returning an INT
            return null;
          }
        case 14:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 15;
            break;
          } else {
            // add code below for resetting positions and recognizing an INT

            return null;
          }
        case 15:
          // add code below for recording positions

          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 15;
            break;
          } else if (peek == 'E') {
            // add code below for recording scanned characters

            advance();
            state = 16;
            break;
          } else {
            // add code for recognizing and returning a REAL
            return null;
          }
        case 16:
          if (peek == '+' || peek == '-') {
            // add code below for recording scanned characters

            advance();
            state = 17;
            break;
          } else if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 18;
            break;
          } else {
            // add code below for resetting positions and recognizing an INT

            return null;
          }
        case 17:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 18;
            break;
          } else {
            // add code below for resetting positions and recognizing an INT

            return null;
          }
        case 18:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters

            advance();
            state = 18;
            break;
          } else {
            // add code for recognizing and returning an SCI
            return null;
          }
        default:
          System.err.println("Unreachable");
      }
    }
  }
}