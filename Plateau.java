package demineur;

import java.util.Random;

public class Plateau {
	private int nbrMines;
	private char tab[][];
	private char tabMines[][];
	
	//Constructeurs
	public Plateau() {}
	
	public Plateau(int nbrMines) {
		this.nbrMines = nbrMines;
		tab = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tab[i][j] = '-';
			}
		}
		this.initialiserTabMines(nbrMines);
	}
	
	//Accesseurs
	public int getNbrMines() {
		return this.nbrMines;
	}
	
	//Mutateurs
	public void setNbrMines(int nbrMines) {
		this.nbrMines = nbrMines;
	}
	
	/**
	 * Méthode qui initialise le tableau de mines
	 * @param nbrMines que l'utilisateur a entré
	 */
	public void initialiserTabMines(int nbrMines) {
		Random r = new Random();
		int c = 1, i, j;
		tabMines = new char[9][9];
		while (c <= nbrMines) {
			i = r.nextInt(9);
			j = r.nextInt(9);
			if (tabMines[i][j] != 'X') {
				tabMines[i][j] = 'X';
				c++;
			}
		}
		for (int f = 0; f < 9; f++) {
			for (int k = 0; k < 9; k++) {
				if (tabMines[f][k] != 'X') tabMines[f][k] = '-';
			}
		}
	}

	/**
	 * Méthode qui permet d'afficher la grille de jeu
	 */
	public void afficherTab() {
		System.out.println("\n\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tab[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}

	/*public void afficherTabM() {
		System.out.println("\n\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tabMines[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}*/
	
	/**
	 * Méthode qui permet d'afficher le tableau avec les emplacements de mines
	 */
	public void afficherTabMines() {
		System.out.println("\n\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tabMines[i][j] == 'X') tab[i][j] = 'X';
			}
		}
		
		this.afficherTab();
	}
	
	/**
	 * Méthode pour creuser une case
	 * @param x ligne
	 * @param y colonne
	 * @return false si explosion et true sinon
	 */
	public boolean creuser(int x, int y) {
		if (tabMines[x][y] == 'X') return false;
		else {
			int count = 0;
			for (int i = x-1; i <= x+1; i++) {
				for (int j = y-1; j <= y+1; j++) {
					if (0 <= i && i <= 8 && j <= 8 && 0 <= j) {
						if (tabMines[i][j] == 'X') count++;
					}
				}
			}
			tab[x][y] = Character.forDigit(count, 10);
		}
		return true;
	}
	
	/**
	 * Méthode qui creuse les cases adjacentes si elles n'ont pas de mines
	 * @param x ligne
	 * @param y colonne
	 * @return false si explosion et true sinon
	 */
	public boolean creuserCasesAdjacentes(int x, int y) {
		x--; y--;
		if (this.creuser(x, y)) {
			if (tab[x][y] == '0') {
				for (int i = x-1; i <= x+1; i++) {
					for (int j = y-1; j <= y+1; j++) {
						if (0 <= i && i <= 8 && j <= 8 && 0 <= j) {
							this.creuser(i, j);
						}
					}
				}
			}
			return true;
		} else return false;
	}
	
	/**
	 * Méthode qui permet de détecter les drapeaux sur cases vides
	 * @return true si il y a des drapeaux sur des cases vides et false sinon
	 */
	public boolean caseVide() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tabMines[i][j] != 'X' && tab[i][j] == '@') return true;
			}
		}
		return false;
	}
	
	/**
	 * Méthode pour poser un drapeau
	 * @param x
	 * @param y
	 * return true si mine et non sinon
	 */
	public boolean poserDrapeau(int x, int y) {
		x--; y--;
		tab[x][y] = '@';
		if (tabMines[x][y] == 'X') return true;
		else return false;
	}
	
	/**
	 * Méthode retirer un drapeau
	 * @param x
	 * @param y
	 * @return
	 */
	public String retirerDrapeau(int x, int y) {
		x--; y--;
		if (tab[x][y] == '@') {
			tab[x][y] = '-';
			return "";
		} else return "Il n'existe pas de drapeau à cette position";
	}
}
