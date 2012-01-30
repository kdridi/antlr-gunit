package com.octo.mvnantlr;


import java.io.*;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

import com.octo.mvnantlr.DemoLexer;
import com.octo.mvnantlr.DemoParser;


public class Main {
	private static DemoParser createStringParser(String input) {
		// Création du lexer
		DemoLexer lexer = new DemoLexer(new ANTLRStringStream(input));

		// Création du flux de tokens. Les tokens sont alimentés par le lexer sous-jacent
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		
		// Création du parser opérant sur le flux de tokens défini ci-dessus
		return new DemoParser(tokens);
	}

	public static void main(String[] args) {
		try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
			System.out.println("Entrez 'quit' pour sortir");
			while(true) {
				System.out.print(">>> ");
				System.out.flush();

				String input = console.readLine();
				if ("quit".equals(input))
					break;
				
				// Appel du parser - le nom de la méthode (program) est celui de la règle principale de Demo.g
				createStringParser(input).program();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
