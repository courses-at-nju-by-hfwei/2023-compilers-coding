grammar PostfixExprAG;

@header {
package ag;
}

stat : expr ;

expr
  : l = expr op = '*' r = expr
  | l = expr op = '+' r = expr
  | '(' expr ')'
  | INT
  ;

INT : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;