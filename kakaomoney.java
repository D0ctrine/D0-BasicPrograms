package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class kakaomoney {

	
	
	
	public static void main(String[] args) {
	int x=0,firstnumber=0,secondnumber=0,cnt=0,least =0;
	ArrayList<String> addMon = new ArrayList<String>();
	String temp="";
	Scanner in = new Scanner(System.in);
	cnt = in.nextInt();
	in.nextLine();
	while(cnt>0) {
		temp =in.nextLine();
		StringTokenizer st = new StringTokenizer(temp);
		firstnumber = Integer.parseInt(st.nextToken());
		secondnumber = Integer.parseInt(st.nextToken());
		if(firstnumber<0) {
			if(x<Math.abs(firstnumber)) {
				addMon.add((Math.abs(firstnumber)+secondnumber-x)+"");
				if((secondnumber-x)>least) {
					least = (secondnumber-x); //최대공약수 최소 범위
				}
			}
		}
		x=secondnumber;
		cnt--;
	}
	if(addMon.size()>1) {
		int small=0;
	for(int i=1;i<addMon.size();i++) {
		int r=1;
		int number1 = Integer.parseInt(addMon.get(i-1));
		int number2 = Integer.parseInt(addMon.get(i));
//		System.out.println(number1+"/"+number2);
		while(r>0) {
		if(number1>number2) {
			r = number1%number2;
			number1 = r;
			small = number2;
		}else if(number1<number2) {
			r = number2%number1;
			number2 = r;
			small = number1;
		}
		}
		
	}
	if(small>least) {
		System.out.println(small);
	}else {
		System.out.println(-1);
	}
	}else {
		System.out.println(-1);
	}
	

	
}

}
/*
int a=13,b=29;
int small=0;
int r=1;

while(r>0) {
	
if(a>b) {
	r = a%b;
	a=r;
	small = b;
}else if(a<b) {
	r= b%a;
	b=r;
	small = a;
}
System.out.println(r+"/"+small);
}
*/