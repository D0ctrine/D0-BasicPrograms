package Quiz1;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	while(true) {
	try {
		Scanner in = new Scanner(System.in);
		System.out.println("계산식을 입력하세요>");
		new separate(in.nextLine());
		break;
	} catch (Exception e) {
		System.out.println("알수 없는 오류가 발생하였습니다.");
	}
	}
	}

}
