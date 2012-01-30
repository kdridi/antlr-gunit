package com.octo.mvnantlr;

import org.antlr.gunit.gUnitBaseTest;

public class TestDemo extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./com/octo/mvnantlr";
		this.lexerPath = "com.octo.mvnantlr.DemoLexer";
		this.parserPath = "com.octo.mvnantlr.DemoParser";
	}

	public void testExprPri11() throws Exception {
		// test input: "12"
		Object retval = execParser("exprPri1", "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 12;

		assertEquals("testing rule "+"exprPri1", expecting, actual);
	}

	public void testExprPri12() throws Exception {
		// test input: "(1+2)"
		Object retval = execParser("exprPri1", "(1+2)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 3;

		assertEquals("testing rule "+"exprPri1", expecting, actual);
	}

	public void testExprPri13() throws Exception {
		// test input: "-(1+2)"
		Object retval = execParser("exprPri1", "-(1+2)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = -3;

		assertEquals("testing rule "+"exprPri1", expecting, actual);
	}

	public void testExprPri21() throws Exception {
		// test input: "2*3"
		Object retval = execParser("exprPri2", "2*3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 6;

		assertEquals("testing rule "+"exprPri2", expecting, actual);
	}

	public void testExprPri22() throws Exception {
		// test input: "2*3*4"
		Object retval = execParser("exprPri2", "2*3*4", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 24;

		assertEquals("testing rule "+"exprPri2", expecting, actual);
	}

	public void testExprPri23() throws Exception {
		// test input: "24/3"
		Object retval = execParser("exprPri2", "24/3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 8;

		assertEquals("testing rule "+"exprPri2", expecting, actual);
	}

	public void testExprPri24() throws Exception {
		// test input: "24/3/2"
		Object retval = execParser("exprPri2", "24/3/2", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 4;

		assertEquals("testing rule "+"exprPri2", expecting, actual);
	}

	public void testExpr1() throws Exception {
		// test input: "1+2"
		Object retval = execParser("expr", "1+2", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 3;

		assertEquals("testing rule "+"expr", expecting, actual);
	}

	public void testExpr2() throws Exception {
		// test input: "1+2+3"
		Object retval = execParser("expr", "1+2+3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = 6;

		assertEquals("testing rule "+"expr", expecting, actual);
	}

	public void testExpr3() throws Exception {
		// test input: "1-2"
		Object retval = execParser("expr", "1-2", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = -1;

		assertEquals("testing rule "+"expr", expecting, actual);
	}

	public void testExpr4() throws Exception {
		// test input: "1-2-3"
		Object retval = execParser("expr", "1-2-3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.RETVAL, retval);
		Object expecting = -4;

		assertEquals("testing rule "+"expr", expecting, actual);
	}

	public void testStatement1() throws Exception {
		// test input: "print 12"
		Object retval = execParser("statement", "print 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"statement", expecting, actual);
	}

	public void testStatement2() throws Exception {
		// test input: "print 12;"
		Object retval = execParser("statement", "print 12;", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12\n";

		assertEquals("testing rule "+"statement", expecting, actual);
	}

	public void testStatement3() throws Exception {
		// test input: "a=5;"
		Object retval = execParser("statement", "a=5;", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"statement", expecting, actual);
	}

	public void testProgram1() throws Exception {
		// test input: "// blablabla"
		Object retval = execParser("program", "// blablabla", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"program", expecting, actual);
	}

	public void testProgram2() throws Exception {
		// test input: "print 12"
		Object retval = execParser("program", "print 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"program", expecting, actual);
	}

	public void testProgram3() throws Exception {
		// test input: "print 12;"
		Object retval = execParser("program", "print 12;", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12\n";

		assertEquals("testing rule "+"program", expecting, actual);
	}

	public void testProgram4() throws Exception {
		// test input: "a=5;"
		Object retval = execParser("program", "a=5;", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"program", expecting, actual);
	}

	public void testProgram5() throws Exception {
		// test input: "a=5; print a;"
		Object retval = execParser("program", "a=5; print a;", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "5\n";

		assertEquals("testing rule "+"program", expecting, actual);
	}



}