package rsa;

import java.util.Random;
import java.util.Scanner;

public class RSA {

	public static void main(String[] args) {
		int p, q, fi, n, e, d;
		int[] key;
		p = randomPrimeNumber();
		q = randomPrimeNumber();
		System.out.println("p = " + p + ", q = "+q);

		fi = (p - 1) * (q - 1);
		n = p * q;
		System.out.println("n = " + n);

		for (e = 3; gcd(e, fi) != 1; e += 2);
		d = euklides(e, fi);
		System.out.println("e = " + e + ", d = "+d);

		System.out.println("Wpisz tekst: ");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();

		key = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			key[i] = ((int) Math.pow((byte) c, e)) % n;
		}
		System.out.println("Wiadomoœæ zakodowana: ");
		for (int i = 0; i < str.length(); i++) {
			System.out.print(key[i] + " ");
		}

		int t;
		String message = "";
		for (int i = 0; i < key.length; i++) {
			t = ((int) Math.pow(key[i], d)) % n;

			char ch = (char) t;
			message = message + ch;
		}
		System.out.println("\nWiadomoœæ odkodowana: ");
		System.out.println(message);
	}

	static int randomPrimeNumber() {
		Random rand = new Random();
		int x = 0;
		boolean first;
		x = rand.nextInt(100) + 1;
		do {
			first = true;
			for (int i = 2; i * i <= x; i++) {
				if (x % i == 0) {
					first = false;
				}
			}
			if (x == 1) {
				first = false;
			}
			if (first == false) {
				x = x + 1;
			}
		} while (!first);
		return x;
	}

	static int gcd(int a, int b) {
		int t;
		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		;
		return a;
	}

	static int euklides(int a, int n) {
		int p0, p1, n0, a0, q, r, t;
		p0 = 0;
		p1 = 1;
		n0 = n;
		a0 = a;
		q = n0 / a0;
		r = n0 % a0;
		while (r > 0) {
			t = p0 - q * p1;
			if (t >= 0) {
				t = t % n;
			} else {
				t = n - ((-t) % n);
			}
			p0 = p1;
			p1 = t;
			n0 = a0;
			a0 = r;
			q = n0 / a0;
			r = n0 % a0;
		}
		;
		return p1;
	}
}
