
public class MuRelOp1 {
	
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int gcd = gcd(a,b);
		System.out.println("GCD of 10 and 20 is: " + gcd);
	}
	
		
	public static int gcd(int a, int b) {
		if((a<0) &&
 (b>0)) {
			while(a!=b) {
				if(a>b) {
					a = a - b;
				}else {
					b = b -a;
				}				
			}
			return a;
		}
		return -1;
	}
	
	
}


	
	


