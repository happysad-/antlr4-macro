grammar Hello;
// parser rules
r: 'hello' ID;
// lexer rules
ID: [a-z]+;
WS: [ \t\r\n]+ -> skip;
