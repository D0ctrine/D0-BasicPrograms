package Test;

public class InToPostFix {

	private String inF = "";
	private String postF = "";
	private MyStack ms = new MyStack();

	InToPostFix(String inf) {
		this.inF = inf;
	}

	public String processPost() {
		System.out.println("현재 infix : " + inF);
		for (int i = 0; i < inF.length(); i++) {
			char c = inF.charAt(i);
			if (IsthisNumber(c)) {  //숫자 일경우 
				postF += c;
			} else if (c == '(') {  //'(' 괄호 시작 점 스택 저장 
				ms.push(c + "");
			} else if (c == ')') { 	//')' 괄호 끝날 시 스택 계산
				while (ms.pointerIndex() != -1 && !ms.getData().equals("(")) { //스택에 '(' 나올때 까지 끄집어 내서 계산
					postF += ms.pop();
				}
				if (ms.pointerIndex() != -1 && !ms.getData().equals("(")) {  //스택에 '(' 또 있다면 잘못된 수식이다.
					return "Invalid Expression";
				} else {	//정상적인 수식이라면 '('를 버린다 이유는 기존의 순서를 구분하려고 스택에 저장되었기 때문에 또 쓸 이유가 없다.
					ms.pop();
				}
			} else { //연산자일경우
				while (ms.pointerIndex() != -1 && MathPriority(c + "") < MathPriority(ms.getData())) { 
					//지금 연산자와 스택 최신 연산자 (전 연산자)우선순위 비교
					//각자 연산자의 우선순위를 확인해서 '+,-'가 'x,/'보다 먼저 실행되지 않게 한다.
					if (ms.getData() == "(") {
						return "Invalid Expression";
					}
					postF += ms.pop();
				}
				ms.push(c + "");
			}
		}
		while (ms.pointerIndex() != -1) { // 마지막 하나 배출
			if (ms.getData() == "(")
				return "Invalid Expression";
			postF += ms.pop();
		}
		return postF;
	}
	
	//숫자인지 아닌지 계산해주기
	private boolean IsthisNumber(char c) { 
		boolean flag = true;
		String numb = "0123456789";
		if (numb.indexOf(c) != -1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	//연산자 간의 우선순위 비교해주기
	private int MathPriority(String ch) {
		switch (ch) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		}
		return -1;
	}
}
