package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class kakaomoney {
	public static void main(String[] args) {
	int x=0,firstnumber=0,secondnumber=0,cnt=0,least =0;
	ArrayList<String> addMon = new ArrayList<String>();
	String temp="";
	boolean flag = false;
	Scanner in = new Scanner(System.in);
	cnt = in.nextInt();
	in.nextLine();
	while(cnt>0) {
		temp =in.nextLine();
		StringTokenizer st = new StringTokenizer(temp);
		firstnumber = Integer.parseInt(st.nextToken());
		secondnumber = Integer.parseInt(st.nextToken());
		if(firstnumber<0) { //ó������ ���� �� ����ϰ��
			if(x<Math.abs(firstnumber)) { //��ݽ� �����ؾߵɰ��
				addMon.add((Math.abs(firstnumber)+secondnumber-x)+""); //�����ݾ�
				if((secondnumber-x)>least) { 
					least = (secondnumber-x); //�ִ����� �ּ� ������ ������ �ִ������
				}
			}
		}
		x=secondnumber;
		cnt--;
	}
	if(addMon.size()>1) {
		int small=0;
		int r=1;
		int ans[] = new int[addMon.size()+1];
		
	for(int i=1;i<addMon.size();i++) {
		int number1 = Integer.parseInt(addMon.get(i-1));
		int number2 = Integer.parseInt(addMon.get(i));
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
		ans[i] = small;
	}

	for(int i=0;i<addMon.size();i++) {
		System.out.println("�����ݾ� : "+addMon.get(i)+"��");
		if(Integer.parseInt(addMon.get(i))<cnt) {
			System.out.println(-1);
			System.exit(0);
		}
	}
	int ans2[] = new int[ans.length];
	int num1=0,num2=0;
	if(ans.length>2) {
	for(int i=1;i<ans.length;i++) {
		num1=ans[i-1];
		num2=ans[i];
		while(r>0) {
			if(num1>num2) {
				r = num1%num2;
				num1 = r;
				small = num2;
			}else if(num1<num2) {
				r = num2%num1;
				num2 = r;
				small = num1;
			}
			}
			ans2[i] = small;
	}
	
	addMon = new ArrayList<String>();
for(int j=0;j<ans2.length;j++) {
	r = ans2[j];
	for(int i = 1; i <= r; i++) {
	    if (r % i == 0) {
	        addMon.add(i+"");
	    }
	}
	
}
cnt=Integer.parseInt(addMon.get(0));
for(int i=0;i<addMon.size();i++) {
	if(Integer.parseInt(addMon.get(i))>least) {
	if(Integer.parseInt(addMon.get(i))<cnt) {
		cnt= Integer.parseInt(addMon.get(i));
	}
	}
}
System.out.println(cnt);
System.exit(0);

	}
	
	
	
	}else if(addMon.size()==1){
		System.out.println(addMon.get(0));
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