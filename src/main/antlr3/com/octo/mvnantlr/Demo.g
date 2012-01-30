grammar Demo;


// Cette grammaire implémente le "hello world" du monde des parsers : la calculette
// La nôtre travaille sur des entiers, et est capable de stocker des variables.


// Les mots-clefs de notre langage
tokens {
	PRINT = 'print';
	DEBUG = 'debug';
}


// Code Java ajouté en tête du fichier source du lexer
@lexer::header
{
	package com.octo.mvnantlr;
}


// Code Java ajouté en tête du fichier source du parser
@parser::header
{
	package com.octo.mvnantlr;

	import java.util.HashMap;
}


// Membres de la classe du parser générée
@parser::members
{
	// Le tableau est statique car dans le prog appelant on recrée un parser à chaque entrée
	// On veut que les variables persistent tout au long de la "session"
	private static HashMap<String, Integer> variables = new HashMap<String, Integer>();

	private void showVariables() {
		for (String varName : variables.keySet())
			System.out.println(varName + " = " + variables.get(varName).intValue());
		System.out.println("   " + variables.size() + " variable(s)");
	}
}



// Règles du parser (les noms commencent par une minuscule)
program
	:	statement*
	;

// Instruction élémentaire
statement
	:	PRINT expr ';'						{ System.out.println($expr.value); }
	|	VARIABLE '=' expr ';'				{ variables.put($VARIABLE.text, new Integer($expr.value)); }
	|	DEBUG ';'							{ showVariables(); }
	;


// Parser d'expressions arithmétiques. Les calculs sont faits en même temps que le parsing.
// La priorité des opérateurs est "câblée" directement dans l'enchaînement des règles
expr returns [int value] options { backtrack=true; }
	:	e1=exprPri2 { $value = $e1.value; } (
			'+' e2=exprPri2 { $value += $e2.value; }
		|	'-' e2=exprPri2 { $value -= $e2.value; }
		)*
	;

exprPri2 returns [int value] options { backtrack=true; }
	:	e1=exprPri1 { $value = $e1.value; } (
			'*' e2=exprPri1 { $value *= $e2.value; }
		|	'/' e2=exprPri1 { $value /= $e2.value; }
		)*
	;

exprPri1 returns [int value]
	:	INTEGER								{ $value = new Integer($INTEGER.text); }
	|	VARIABLE							{ $value = variables.get($VARIABLE.text).intValue(); }
	|	'(' expr ')'						{ $value = $expr.value; }
	|	'-' expr							{ $value = -$expr.value; }
	;


// Règles du lexer (les noms commencent par une majuscule ; la convention est de tout mettre en majuscules)

// Nom de variable
VARIABLE
	:	LETTER (LETTER | DIGIT)*
	;

// Constante entière (positive ; la négation est traitée par le parser)
INTEGER
	:	DIGIT+;

// Commentaire -> ignoré
COMMENT
	:	'//' (~ NL)* NL? { skip(); };

// Sauts de ligne (ignorés via la règle WS)
NL
	: '\n' | '\r'
	;

// Espaces -> ignorés
WS
	:	(' ' | '\t' | NL) { skip(); };

fragment LETTER
	:	'A'..'Z' | 'a'..'z' | '_';

fragment DIGIT
	:	'0'..'9';
