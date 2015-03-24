package dataSystem;

import java.util.Vector;

public class Parser {

	public enum Request {
		REGISTER, LOOKUP;
	}

	private Request operator;
	private Vector<String> operands;

	public Request getOperator() {
		return operator;
	}

	public void setOperator(Request operator) {
		this.operator = operator;
	}

	public Vector<String> getOperands() {
		return operands;
	}

	public void setOperands(Vector<String> operands) {
		this.operands = operands;
	}

	public Parser(String request) {
		
		operands=new Vector<String>();
		String[] tokens = request.split(" ");

		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);
		}

		switch (tokens[0]) {

		case "REGISTER":

			if (tokens.length == 3) {
				operator = Request.REGISTER;
				operands.add(tokens[1]);
				operands.add(tokens[2]);
			} else {
				// error msg, wrong number of arguments, send msg
			}
			break;
		case "LOOKUP":
			if (tokens.length == 2) {
				operator = Request.LOOKUP;
				operands.add(tokens[1]);
			} else {
				// error msg, wrong number of arguments, send msg
			}
			break;
		default:
			// error msg
		}

	}

	// for Server

	// anwser client about number of registers

	// anwser client about owner's plate

	// for client

	// asks server to regist

	// asks server to look up

}
