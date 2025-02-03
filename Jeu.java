import java.util.Scanner;
import java.util.Random;

class Pokemon {
    String nom;
    int PV;
    int degats;
    int soin;
    String[] attaques;
    String[] soins;

    public Pokemon(String nom, int PV, int degats, int soin, String[] attaques, String[] soins) {
        this.nom = nom;
        this.PV = PV;
        this.degats = degats;
        this.soin = soin;
        this.attaques = attaques;
        this.soins = soins;
    }

    public String choisirAttaque(int choix) {
        return attaques[choix - 1]; 
    }

    public String actionAleatoire() {
        Random rand = new Random();
        int choix = rand.nextInt(2); 
        if (choix == 0) {
            return attaques[rand.nextInt(attaques.length)]; 
        } else {
            return soins[rand.nextInt(soins.length)]; 
        }
    }
}

public class Jeu {
    private static Pokemon joueur1;
    private static Pokemon joueur2;
    private static Scanner scanner;
    private static Random random;

    public static void main(String[] args) {
        random = new Random();

        System.out.println("------------------------------------");
        System.out.println("* Bienvenue pour le Combat ultime !*");
        System.out.println("*        Asterion VS Pokemon       *");
        System.err.println("------------------------------------");

        scanner = new Scanner(System.in);
        System.out.println("Choisissez votre personnage :");
        System.out.println("1. Dracofeu");
        System.out.println("2. Asterion");
        System.out.println("3. Pikachu");
        System.out.println("4. Bulbizarre");
        int choix = scanner.nextInt();

        if (choix == 1) {
            String[] attaques = {"Tempête Ecarate", "Flammes Dévastatrices"};
            String[] soins = {"Bain de Magma"};
            joueur1 = new Pokemon("Dracofeu", 250, 35, 10, attaques, soins);
            joueur2 = choisirPokemonAdverse();
        } 
        else if (choix == 2) {
            String[] attaques = {"Touche pas à mon bios"};
            String[] soins = {"Esquive sur Twitter"};
            joueur1 = new Pokemon("Asterion", 180, 12, 5, attaques, soins);
            joueur2 = choisirPokemonAdverse();
        } 
        else if (choix == 3) {
            String[] attaques = {"Queue de Fer", "Tonnerre"};
            String[] soins = {"Surcharge"};
            joueur1 = new Pokemon("Pikachu", 100, 22, 15, attaques, soins);
            joueur2 = choisirPokemonAdverse();
        }
        else if (choix == 4) {
            String[] attaques = {"Vampire Végétal", "Tornade Végétale"};
            String[] soins = {"Photosynthèse"};
            joueur1 = new Pokemon("Bulbizarre", 350, 15, 20, attaques, soins);
            joueur2 = choisirPokemonAdverse();
        }
        else {
            System.out.println("Choix invalide !");
            scanner.close();
            return;
        }

        System.out.println("Vous avez choisi " + joueur1.nom + " !");
        System.out.println(joueur2.nom + " est votre adversaire !");
        combat();
    }

    public static Pokemon choisirPokemonAdverse() {
        int choixPokemon = random.nextInt(4) + 1;
        switch (choixPokemon) {
            case 1:
                String[] attaques1 = {"Tempête Ecarate", "Flammes Dévastatrices"};
                String[] soins1 = {"Bain de Magma"};
                return new Pokemon("Dracofeu", 250, 35, 10, attaques1, soins1);
            case 2:
                String[] attaques2 = {"Touche pas à mon bios"};
                String[] soins2 = {"Esquive sur Twitter"};
                return new Pokemon("Asterion", 80, 50, 2, attaques2, soins2);
            case 3:
                String[] attaques3 = {"Queue de Fer", "Tonnerre"};
                String[] soins3 = {"Surcharge"};
                return new Pokemon("Pikachu", 100, 22, 15, attaques3, soins3);
            case 4:
                String[] attaques4 = {"Vampire Végétal", "Tornade Végétale"};
                String[] soins4 = {"Photosynthèse"};
                return new Pokemon("Bulbizarre", 150, 15, 20, attaques4, soins4);
            default:
                return null;
        }
    }

    public static void combat() {
        while (joueur1.PV > 0 && joueur2.PV > 0) {

            System.out.println("|-------------------------------|");
            System.out.println("|      Que voulez-vous faire ?  |");
            System.out.println("|      1. Attaquer              |");
            System.out.println("|      2. Se soigner            |");
            System.out.println("|-------------------------------|");
            int choix = scanner.nextInt();

            if (choix == 1) {
                System.out.println("Choisissez votre attaque :");
                for (int i = 0; i < joueur1.attaques.length; i++) {
                    System.out.println((i + 1) + ". " + joueur1.attaques[i]);
                }
                int choixAttaque = scanner.nextInt();
                String attaqueChoisie = joueur1.choisirAttaque(choixAttaque);
                joueur2.PV -= joueur1.degats;
                System.out.println(joueur1.nom + " attaque " + joueur2.nom + " avec " + attaqueChoisie + " ! " + joueur2.nom + " perd " + joueur1.degats + " points de vie.");
                System.out.println(joueur2.nom + " a " + joueur2.PV + " points de vie.");
            } else if (choix == 2) {
                System.out.println("Choisissez votre soin :");
                for (int i = 0; i < joueur1.soins.length; i++) {
                    System.out.println((i + 1) + ". " + joueur1.soins[i]);
                }
                int choixSoin = scanner.nextInt();
                String soinChoisi = joueur1.soins[choixSoin - 1];
                joueur1.PV += joueur1.soin;
                System.out.println(joueur1.nom + " utilise " + soinChoisi + " et récupère " + joueur1.soin + " points de vie.");
                System.out.println(joueur1.nom + " a " + joueur1.PV + " points de vie.");
            } else {
                System.out.println("Choix invalide !");
                continue;
            }

            if (joueur2.PV <= 0) {
                System.out.println(joueur2.nom + " est vaincu !");
                break;
            }

            actionAleatoireJoueur2();

            if (joueur1.PV <= 0) {
                System.out.println(joueur1.nom + " est vaincu !");
                break;
            }
        }
    }

    public static void actionAleatoireJoueur2() {
        String action = joueur2.actionAleatoire();
        if (action.equals("Surcharge")) {
            joueur2.PV += joueur2.soin;
            System.out.println(joueur2.nom + " utilise " + action + " et se soigne en récupérant " + joueur2.soin + " points de vie.");
        } else {
            joueur1.PV -= joueur2.degats;
            System.out.println(joueur2.nom + " attaque " + joueur1.nom + " avec " + action + " ! " + joueur1.nom + " perd " + joueur2.degats + " points de vie.");
        }
        System.out.println(joueur1.nom + " a " + joueur1.PV + " points de vie.");
    }
}
