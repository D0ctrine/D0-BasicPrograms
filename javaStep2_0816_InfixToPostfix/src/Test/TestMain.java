package Test;

public class TestMain {
	public static void main(String[] args) {
		String inFix = "1*(3+4/2)-5*(6-7*8)";	// [post] : 1342/+*5- <- [in] :  1*(3+4/2)-5
		InToPostFix intop = new InToPostFix(inFix);
		String postFix = intop.processPost();
		System.out.println("변화된 수식 : "+postFix);
		//	위 소스가 완성되면 postFix 결과값을 계산하는  Cal 코딩
		
	}
}
