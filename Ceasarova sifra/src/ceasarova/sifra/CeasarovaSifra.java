/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceasarova.sifra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Blizius
 */
public class CeasarovaSifra {

    /**
     * @param args the command line arguments
     * Uvedení pravidel psaní textu, postupné volání funkcí na potřebné vstupy,
     * podmínka pro šifrování, dešifrování, tisk výsledku
     */
    public static void main(String[] args) {
        System.out.println("V tomto programu při psaní TEXTU používejte jen základní latinskou abecedu"
                + " tj. 26 písmen bez diakritiky, interpunkce a jiných speciálních znaků a čísel. Mezery požívat můžete.");
        
        int dir = readDir();        
        int shift = readShift();        
        System.out.println("Napiště text.");
        char []text = readText();
        int []letters = charToInt(text);
        
        if (dir == 0){
            encode(letters, shift);
        }
        else{
            decode(letters, shift);
        }        
        
        for (int i = 0; i < text.length; i++){
            System.out.print((char)letters [i]);
        }   
    }
    /**
     * Fce pro napsání textu uživatelem
     * @return pole charů (jednotlivých písmen a mezer)
     */
    public static char [] readText() {         
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = new String(reader.readLine()).toLowerCase();                                                       
        }
        catch (IOException ex){
            System.err.println("Chyba při načítání vstupu, zkuste to prosím znovu.");
            System.exit(1);
        }            
        return  str.toCharArray();
    }
    /**
     * Fce pro napsání celých čísel uživatelem se zachycením chyb
     * @return integer (celé číslo)
     */
    public static int readInt() {
        BufferedReader reader;
        int i = 0;
        try {
        reader = new BufferedReader(new InputStreamReader(System.in));
        i = Integer.parseInt(reader.readLine());
        }
        catch (IOException ex){
            System.err.println("Chyba při načítání vstupu, zkuste to prosím znovu.");
            System.exit(1);
        }
        catch (NumberFormatException ex){
            System.err.println("Špatný formát vstupu, nezadali jste celé číslo.");
            System.exit(1);
        }
        return i;
    }
    /**
     * Fce pro zjištění, zda uživatel chce šifrovad nebo dešifrovat
     * @return 0 pro šifrování nebo 1 pro dešifrování
     */
    public static int readDir (){
        System.out.println("Napište 0 pro šifrování nebo 1 pro dešifrování textu.");
        int dir = readInt();
        if (dir != 0 && dir != 1){
            System.err.println("Zadal jste něco jiného než 1 nebo 0.");
            System.exit(1);
        }
        return dir;
    }
    /**
     * Konverze znaků abecedy na čísla dle ASCII a upravení na hodnoty 1 - 26
     * @param text pole charů (znaků abecedy)
     * @return pole intů (1 - 26 a -64 pro mezeru)
     */
    public static int[] charToInt (char []text){
        int []letters = new int [text.length];
        for (int i = 0; i < text.length; i++){
            letters [i] = (int)text[i] - 96;
        }        
        for (int i =0; i < letters.length; i++){
            if (letters [i] == -64 || (letters [i] > 0 && letters [i] < 26)){
                continue;
            }
            else{
                System.err.println("V zadaném textu jsou i jiné znaky než základní "
                        + "latinská abeceda a mezery.");
                System.exit(1);
            }
        }
        return letters;
    }
    /**
     * Fce pro šifrování textu (změnění hodnot intů v poli o zadaný posun doprava 
     * a převedení na číselnou hodnotu znaků abecedy dle ASCII)
     * @param letters upravené číselné hodnoty znaků abecedy (výstup fce charToInt)
     * @param shift celočíselná hodnota posunu písmen v Ceasarově šifře
     */
    public static void encode (int [] letters, int shift){
        for (int i = 0; i < letters.length; i++){
            if (letters [i] != -64){
                letters [i] += shift;
                if (letters [i] > 26){
                    letters [i] -= 26;
                }                
            }
            letters [i] += 96;
        }
    }
    /**
     * Fce pro zadání kontrole správného zadání posunu v Ceasarově šifře
     * @return int hodnota posunu (1 - 25)
     */
    public static int readShift (){
        System.out.println("Napište celým číslem od 1 do 25 posunutí písmen v Ceasarově šifře.");
        int shift = readInt();
        if (shift < 1 || shift > 25){
            System.err.println("Posunutí nebylo zadáno v rozmezí 1 až 25.");
            System.exit(1);
        }
        return shift;
    }
    /**
     * Fce pro dešifrování textu (změnění hodnot intů v poli o zadaný posun doleva 
     * a převedení na číselnou hodnotu znaků abecedy dle ASCII)
     * @param letters upravené číselné hodnoty znaků abecedy (výstup fce charToInt)
     * @param shift celočíselná hodnota posunu písmen v Ceasarově šifře
     */
    public static void decode (int []letters, int shift){
        for (int i = 0; i < letters.length; i++){
            if (letters [i] != -64){
                letters [i] -= shift;
                if (letters [i] < 1){
                    letters [i] += 26;
                }                
            }
            letters [i] += 96;
        }
    }
}
