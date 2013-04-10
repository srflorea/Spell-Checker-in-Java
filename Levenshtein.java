
public class Levenshtein {

	public int levenshteinDistance(String s,String t) {
		
		int m = s.length(), n = t.length();
		char[] sCH = s.toCharArray(), tCH = t.toCharArray();
		
		/*pentru oricare i si j, d[i][j] retine distanta Levenshtein 
		 * dintre primele i caractere ale primului sir si primele j caractere celui de-al doilea sir
		 */
		int[][] d = new int [m+1][n+1];
		
		//distamta dintre oricare string la un al doilea string gol
		for(int i = 0; i <= m; i++) {
			d[i][0] = i;
		}
		//distanta dintre oricare al doilea string si primul gol
		for(int j = 0; j <= n; j++) {
			d[0][j] = j;
		}
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(sCH[i - 1] == tCH[j - 1]) {
					d[i][j] = d[i-1][j-1];
				}
				else {
					//o stergere, o insertie, o substitutie
					d[i][j] = minim(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1] + 1);
				}
			}
		}
		return d[m][n];
	}
	
	public int minim(int a, int b, int c) {
		
		return (a < b) ? ((a < c) ? a : b) : ((b < c) ? b : c );
	}
}
