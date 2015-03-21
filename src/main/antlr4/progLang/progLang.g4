grammar progLang;

progLang : (compilationUnit)*
    ;

typedId : ID '::' ID
    ;

compilationUnit : expr
    ;

expr :   arithExpr
    |    objLit
    ;

// Object Literals
objLit : '{' objLitMembers '}'
    ;

objLitMembers : objLitMember
    | objLitMember ',' objLitMembers
    ;

objLitMember : objLitId ':' expr
    ;

objLitId: ID | typedId;

arithExpr : arithExpr ('*'|'/') arithExpr
    |   arithExpr ('+'|'-') arithExpr
    |   INT
    |   '(' arithExpr ')'
    ;

ID  :    ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :    '0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        )+ -> skip
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

NEWLINE : [\n|\r\n]+ ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
