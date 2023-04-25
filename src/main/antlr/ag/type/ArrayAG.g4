grammar ArrayAG;

@header {
package ag.type;
import java.util.*;
}

@parser::members {
private Map<String, String> typeMap = new HashMap<>();
}

prog : stat* EOF ;

stat : varDecl
     | arrDecl
     | lhs = expr '=' rhs = expr ';'
     ;

varDecl : basicType ID ';' ;
basicType : 'int' | 'float' ;

// OR: type ID ('[' INT ']')* ';'
arrDecl : basicType ID arrayType ';' ;
arrayType : '[' INT ']' arrayType
          |
          ;

// primary = expr '[' subscript = expr ']'
expr: ID ('[' subscript = expr ']')+
    | ID
    | INT
    ;

ID : [a-z]+ ;
INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;