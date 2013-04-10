import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		//citire cuvinte din dictionar
		String dictionar = null;
		try {
			dictionar = readFileAsString("dictionar.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] cuvinte = dictionar.split("\n");
		
		//System.out.println("introduceti sirul de caractere...");
		Scanner in = new Scanner(System.in);
		String sir_cuvinte = in.nextLine();
		//String sir_cuvinte = "varrry";
		
		//long time1 = System.currentTimeMillis();
		
		System.out.println(word(cuvinte,sir_cuvinte));
		//System.out.println("timpul: " + ((System.currentTimeMillis() - time1)/1000.));
		//System.out.println(minMultiplications("abcdef",cuvinte));
	}

	private static String readFileAsString(String filePath)
		    throws java.io.IOException{
		        StringBuffer fileData = new StringBuffer(1000);
		        BufferedReader reader = new BufferedReader(
		                new FileReader(filePath));
		        char[] buf = new char[1024];
		        int numRead=0;
		        while((numRead=reader.read(buf)) != -1){
		            String readData = String.valueOf(buf, 0, numRead);
		            fileData.append(readData);
		            buf = new char[1024];
		        }
		        reader.close();
		        return fileData.toString();
		    }
	
	
	
	private static int minMultiplications(String sir, String[] cuvinte) 
	  {
	    /* Tiparim efectiv lantul parantezat si intoarcem numarul minim de operatii de
	     * inmultire elementare. */
		Levenshtein lev = new Levenshtein();
		  int[][] m = new int[sir.length()][sir.length()];
		  int[][] s = new int[sir.length()][sir.length()];
		  int i;
		  for(i = 0 ; i < sir.length() ; i++) {
			  m[i][i] = 0;
		  }
			  for(int l = 2 ; l < sir.length() ; l++) {
				  for( i = 1 ; i < sir.length() - l + 1 ; i++) {
					  int j = i + l - 1 ;
					  m[i][j] = Integer.MAX_VALUE;
					  for( int k = i ; k < j ; k++) {
						  int q = m[i][k] + m[k+1][j] + lev.levenshteinDistance(sir.substring(i, k), word(cuvinte, sir.substring(i, k))) + lev.levenshteinDistance(sir.substring(k+1, j), word(cuvinte, sir.substring(k+1, j)));
						  if(q < m[i][j]) {
							  m[i][j] = q;
							  s[i][j] = k;
						  }
					  }
				  }
			  }
	    return m[1][sir.length() - 1];
	  }
	
	public static String word(String[] cuvinte, String cuv) {
		Levenshtein lev = new Levenshtein();
		String[] cuvantFrecv = cuvinte[0].split(" ");
		int distMinim = lev.levenshteinDistance(cuv, cuvantFrecv[0]);
		float frecventa = Float.parseFloat(cuvantFrecv[1]);
		int index = 0;
		for(int i = 1; i < cuvinte.length; i++) {
			cuvantFrecv = cuvinte[i].split(" ");
			float frecventa_curenta = Float.parseFloat(cuvantFrecv[1]);
			int dist = lev.levenshteinDistance(cuv, cuvantFrecv[0]);
			if(dist < distMinim) {
				distMinim = dist;
				frecventa = frecventa_curenta;
				index = i;
			}
			else if (dist == distMinim) {
				if(frecventa < frecventa_curenta) {
					frecventa = frecventa_curenta;
					index = i;
				}
				else if( frecventa == frecventa_curenta) {
					index = i;
				}
			}
		}
		return cuvinte[index].split(" ")[0];
	}
}
