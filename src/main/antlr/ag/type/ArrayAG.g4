grammar ArrayAG;

@header {
package ag.type;
import java.util.*;
}

@parser::members {
}

prog : stat* EOF ;

stat : varDecl
     | arrDecl
     | lhs = expr '=' rhs = expr ';'
     ;

varDecl : basicType ID ';' ;
basicType : 'int' | 'float' ;

// OR: type ID ('[' INT ']')* ';'
arrDecl : basicType ID arrayType[$basicType.text]
  { System.out.println($ID.text + " : " + $arrayType.array_type); } ';' ;

arrayType[String basic_type]
    returns [String array_type]
  : '[' INT ']' arrayType[$basic_type]
     { $array_type = "(" + $INT.int + ", " + $arrayType.array_type + ")"; }
  |  { $array_type = $basic_type; }
  ;

// primary = expr '[' subscript = expr ']'
expr: ID ('[' subscript = expr ']')+
    | ID
    | INT
    ;

ID : [a-z]+ ;
INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;