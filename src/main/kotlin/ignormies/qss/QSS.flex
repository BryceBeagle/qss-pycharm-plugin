package ignormies.qss;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ignormies.qss.psi.QSSTypes;
import com.intellij.psi.TokenType;

%%

%class QSSLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITE_SPACE=\s+

IDENTIFIER=[a-zA-Z_]\w*
NUMBER=[-+]?[0-9]*\.?[0-9]*
PERCENTAGE={NUMBER}%
ANGLE={NUMBER}(deg|rad|grad)

COLON=":"
SEMICOLON=";"
OPEN_BRACE="{"
CLOSE_BRACE="}"
OPEN_BRACKET="["
CLOSE_BRACKET="]"
EQ_SIGN="="
PLUS_SIGN="+"
GT_SIGN=">"

%state ATTRIBUTE
%state DECLARATION
%state EXPRESSION

%%

<YYINITIAL> {
    {IDENTIFIER}                                 {return QSSTypes.IDENTIFIER;}
    {OPEN_BRACE}                                 {yybegin(DECLARATION); return QSSTypes.OPEN_BRACE;}
    {PLUS_SIGN}                                  {return QSSTypes.PLUS_SIGN;}
    {GT_SIGN}                                    {return QSSTypes.GT_SIGN;}
    {OPEN_BRACKET}                               {yybegin(ATTRIBUTE); return QSSTypes.OPEN_BRACKET;}
}

<ATTRIBUTE> {
    {IDENTIFIER}                                 {return QSSTypes.IDENTIFIER;}
    {EQ_SIGN}                                    {return QSSTypes.EQ_SIGN;}
    {CLOSE_BRACKET}                              {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACKET;}
}

<DECLARATION> {
    {IDENTIFIER}                                 {return QSSTypes.IDENTIFIER;}
    {COLON}                                      {yybegin(EXPRESSION); return QSSTypes.COLON;}
    {CLOSE_BRACE}                                {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACE;}
}

<EXPRESSION> {
    {NUMBER}                                     {return QSSTypes.NUMBER;}
    {PERCENTAGE}                                 {return QSSTypes.PERCENTAGE;}
    {ANGLE}                                      {return QSSTypes.ANGLE;}
    {IDENTIFIER}                                 {return QSSTypes.IDENTIFIER;}
    {SEMICOLON}                                  {yybegin(DECLARATION); return QSSTypes.SEMICOLON;}
    {CLOSE_BRACE}                                {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACE;}
}

{WHITE_SPACE}                                    { return TokenType.WHITE_SPACE; }

[^]                                              { return TokenType.BAD_CHARACTER; }
