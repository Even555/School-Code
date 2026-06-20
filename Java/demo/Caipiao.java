package demo;

import java.util.HashSet;
import java.util.Scanner;

public class Caipiao {
		public static HashSet<Integer> getLuck() {
			HashSet<Integer> s = new HashSet<Integer>();
			while (s.size() < 7) {
				int n = (int) (Math.random() * 30 + 1);
				s.add(n);
			}
			return s;
		}
	 
	 
	 
		public static void main(String[] args) {
			
			HashSet<Integer> s = new HashSet<Integer>();
			s = getLuck();
			
			int x;
			int bingo = 0;
			
			System.out.println("请输入7个数字，请勿重复");
			Scanner sc = new Scanner(System.in);
			
			for (int i = 0; i < 7; i++) {
				x = sc.nextInt();
				if (s.contains(x)) {
					bingo++;
				}
			}
			
			sc.close();
			System.out.println("中了"+bingo+"个号码");
			
			if (bingo == 7) {
				System.out.println("一等奖");
			} 
			
			else if (bingo == 6) {
			System.out.println("二等奖");
			} 
			
			else if (bingo == 5) {
			System.out.println("三等奖");
			} 
			
			else {
			System.out.println("没有中奖");
			}
		}
			
}
