package Quiz1;
public class separate {
	private String math="";
	int[] number = new int[2];	
	String c = "";
	separate(String c){
		this.c = c;
		try {
			sep(this.c);
			cal(number[0],math,number[1]);
		} catch (calE e) {
			e.prt();
		}finally {
			System.out.println("계산이 완료되었습니다.");
		}
	}
	private void cal(int a,String b,int c) throws calE {
		if(a<100&& -100<a && c<100 && -100<c) { //십의 자리 초과 안되게
			
		}else {
			throw new calE("ERR003");
		}
		if(b.length()!=1) { //연산자 오류 방지
				throw new calE("ERR002");
		}else {
			
			switch(b) {
			case "+" : System.out.println(a+c);break;
			case "-" : System.out.println(a-c);break;
			case "*" : System.out.println(a*c);break;
			case "/" : if(c==0) {
			throw new calE("ERR001");
			}else {
				System.out.println(a/c);
			}
			break;
			default : throw new calE("ERR002"); 
			}
		}
	}
	private void sep(String cal) {
		boolean flag = false;
		if(cal.charAt(0)=='-') {
			flag =true;
			cal = cal.substring(1);
		}else if(cal.charAt(0)=='+') {
			cal = cal.substring(1);
		}
		for(int i=0;i<cal.length();i++) {
			char a = cal.charAt(i);
			if(IsNumber(a)) {
				if(number[0]!=0) {
					number[1] = Integer.parseInt(cal.substring(i));
					break;
				}
			}else{
				if(number[0]==0) {
					number[0] = Integer.parseInt(cal.substring(0,i));
				}
				math+=a+"";
			}
		}
			if(math.length()>1) {
				if(math.charAt(math.length()-1)=='-') {
					number[1] *=-1;
					math = math.charAt(0)+"";
				}else if(math.charAt(math.length()-1)=='+') {
					math = math.charAt(0)+"";
				}
			}
			if(flag) {
				number[0]*=-1;
			}
	}
	private boolean IsNumber(char a) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String number = "0123456789";
		if(number.indexOf(a)!=-1) {
			flag = true;
		}
		
		return flag;
	}
	
	
}
