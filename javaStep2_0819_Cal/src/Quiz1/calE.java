package Quiz1;

public class calE extends Exception{
	private String errMemo = "";
	private String ecode="";
	
	calE(String errcode){
		this.ecode = errcode;
		switch(errcode) {
		case "ERR001" :
			err001();
			break;
		case "ERR002" :
			err002();
			break;
		case "ERR003" :
			err003();
			break;
		}
	}
	
	private void err003() {
		errMemo="code : "+ecode+" / 숫자가 십의 자리를 초과하였습니다.";
	}

	private void err002() {
		errMemo = "code : "+ecode+" / 연산자에 문제가 발생하였습니다.";
	}

	private void err001() {
		errMemo = "code : "+ecode+" / 0으로 나눌 수 없습니다.";
	}
	
	public void prt() {
		System.out.println(errMemo);
	}
}
