package Exercicios;
	
import java.util.Scanner;

public class Ex3 {
	
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite a quantidade de termos: ");

		int num = ler.nextInt();
		int n1 = 1;
		int n2 = 1;

		System.out.print("1 ");
		System.out.print("1 ");

		num = num - 2;

		while (num > 0) {
			System.out.print((n1+n2) + " ");
			int n3 = n1+n2;
			n1 = n2;
			n2 = n3;
			num--;

		}

		System.out.println("Fim");
	}
}