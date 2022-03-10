package demineur;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Nouvelle partie");
		int nbrMines = 0;
		
		/**
		 * Vérifier si la valeur que l'utilisateur entre est correcte
		 */
		do {
			try {
				System.out.println("Entrez le nombre de mines:");
				nbrMines = Integer.parseInt(in.readLine());
				if (nbrMines <= 0 || 81 < nbrMines) System.out.println("\nEntrez un entier entre 1 et 81 inclus");
			} catch (NumberFormatException e) {}
		} while(nbrMines <= 0 || 81 < nbrMines);

		Plateau p = new Plateau(nbrMines);
		p.afficherTab();
		
		int operation = 0, ligne = 0, colonne = 0;
		
		/**
		 * Vérifie si la valeur que l'utilisateur entre est correcte
		 * Creuse, pose un drapeau et retire un drapeau
		 * Répéter jusqu'à ce que le joueur trouve toutes les mines et ne place pas de drapeau sur une case vide
		 */
		do {
			do {
				try {
					System.out.println("Entrez 1 pour creuser, 2 pour poser un drapeau et 3 pour retirer un drapeau:");
					operation = Integer.parseInt(in.readLine());
					if (operation < 0 || 3 < operation) System.out.println("\nEntrez un entier entre 1 et 3 inclus");
				} catch (NumberFormatException e) {}
			} while (operation < 1 || 3 < operation);
			
			do {
				try {
					System.out.println("Entrez la ligne:");
					ligne = Integer.parseInt(in.readLine());
					
					System.out.println("Entrez la colonne:");
					colonne = Integer.parseInt(in.readLine());
					
					if (ligne < 1 || 9 < ligne || colonne < 1 || 9 < colonne)
						{ System.out.println("Entrez des coordonnées contenues entre 1 et 9 inclus"); }
				} catch (NumberFormatException e) {}
			} while (ligne < 1 || 9 < ligne || colonne < 1 || 9 < colonne);
			
			if (operation == 1) {
				if (!p.creuserCasesAdjacentes(ligne, colonne)) {
					p.afficherTabMines();
					System.out.println("Explosion !\nVous avez perdu la partie!");
					break;
				}
			} else if (operation == 2) {
				if (p.poserDrapeau(ligne, colonne)) nbrMines--;
			} else if (operation == 3) {
				System.out.println(p.retirerDrapeau(ligne, colonne));
			}
			p.afficherTab();
			
		} while (nbrMines != 0 || p.caseVide());
		
		
		//Si toutes les mines ont été trouvées et s'il n'y a pas de drapeau sur case vide
		if (nbrMines == 0 && !p.caseVide()) {
			System.out.println("Bravo! Vous avez gagné la partie!");
		}
	
	}

}
