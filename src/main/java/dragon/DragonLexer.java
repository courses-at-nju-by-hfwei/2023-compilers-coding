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
      return NUMBER();
    }

    if (peek == '=') {
      advance();
      return Token.EQ;
    }

    if (peek == '<') {
      advance();
      if (peek == '=') {
        advance();
        return Token.LE;
      } else if (peek == '>') {
        advance();
        return Token.NE;
      } else {
        return Token.LT;
      }
    }

    if (peek == '>') {
      advance();
      if (peek == '=') {
        advance();
        return Token.GE;
      } else {
        return Token.GT;
      }
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
    StringBuilder sb = new StringBuilder();

    do {
      sb.append(peek);
      advance();
    } while (Character.isDigit(peek));

    return new Token(TokenType.INT, sb.toString());
  }

  private Token NUMBER() {
    StringBuilder intStr = new StringBuilder();
    intStr.append(peek);
    advance();

    // add code below for recording positions
    int longestPrefixPos = -1;
    TokenType type = null;

    // add code below for storing scanned characters
    StringBuilder realStr = new StringBuilder();
    StringBuilder sciStr = new StringBuilder();

    int state = 13;
    while (true) {
      switch (state) {
        case 13:
          longestPrefixPos = pos;
          type = TokenType.INT;

          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            intStr.append(peek);

            advance();
            state = 13;
            break;
          } else if (peek == '.') {
            // add code below for recording scanned characters
            realStr.append(peek);

            advance();
            state = 14;
            break;
          } else if (peek == 'E' || peek == 'e') {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 16;
            break;
          } else {
            // add code for recognizing and returning an INT
            return new Token(TokenType.INT, intStr.toString());
          }
        case 14:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            realStr.append(peek);

            advance();
            state = 15;
            break;
          } else {
            // add code below for resetting positions and recognizing an INT
            this.reset(longestPrefixPos);
            return new Token(type, intStr.toString());
          }
        case 15:
          // add code below for recording positions
          longestPrefixPos = pos;
          type = TokenType.REAL;

          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            realStr.append(peek);

            advance();
            state = 15;
            break;
          } else if (peek == 'E' || peek == 'e') {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 16;
            break;
          } else {
            // add code for recognizing and returning a REAL
            return new Token(TokenType.REAL, intStr.append(realStr).toString());
          }
        case 16:
          if (peek == '+' || peek == '-') {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 17;
            break;
          } else if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 18;
            break;
          } else {
            // add code below for resetting positions and recognizing an INT
            this.reset(longestPrefixPos);
            return new Token(type, intStr.append(realStr).toString());
          }
        case 17:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 18;
            break;
          } else {
            // add code below for resetting positions and recognizing an REAL
            this.reset(longestPrefixPos);
            return new Token(TokenType.REAL, intStr.append(realStr).toString());
          }
        case 18:
          if (Character.isDigit(peek)) {
            // add code below for recording scanned characters
            sciStr.append(peek);

            advance();
            state = 18;
            break;
          } else {
            // add code for recognizing and returning an SCI
            return new Token(TokenType.REAL,
                intStr.append(realStr).append(sciStr).toString());
          }
        default:
          System.err.println("Unreachable");
      }
    }
  }
}