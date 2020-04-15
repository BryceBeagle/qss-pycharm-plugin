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

COLON=":"
SEMICOLON=";"
OPEN_BRACE="{"
CLOSE_BRACE="}"

%state DECLARATION
%state EXPRESSION

%%

<YYINITIAL> {
    {IDENTIFIER}                                                   {return QSSTypes.IDENTIFIER;}
    {OPEN_BRACE}                                                   {yybegin(DECLARATION); return QSSTypes.OPEN_BRACE;}
}

<DECLARATION> {
    {IDENTIFIER}                                                   {return QSSTypes.IDENTIFIER;}
    {COLON}                                                        {yybegin(EXPRESSION); return QSSTypes.COLON;}
    {CLOSE_BRACE}                                                  {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACE;}
}

<EXPRESSION> {
    {IDENTIFIER} / {WHITE_SPACE}? ({SEMICOLON} | {CLOSE_BRACE})    {return QSSTypes.EXPRESSION;}
    {SEMICOLON}                                                    {yybegin(DECLARATION); return QSSTypes.SEMICOLON;}
    {CLOSE_BRACE}                                                  {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACE;}
}

{WHITE_SPACE}                                                      { return TokenType.WHITE_SPACE; }

[^]                                                                { return TokenType.BAD_CHARACTER; }
