/*
 * first-release
 *    
 */
package descubrir_mensaje;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author gesifred
 * 
 */
public class Descubrir_mensaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        File archivo = new File("datos.txt");
        Scanner entrada = new Scanner(archivo);
        char m[][];
        
        //contar filas del archivo
        int filas = 0;
        while(entrada.hasNext()){
            String linea = entrada.nextLine();
            filas++;
        }
        m = new char[filas][]; // Definicion de la matriz que contendra valores del teclado
             
        //reset del proceso de lectura
        entrada = new Scanner( archivo);
        
        //creación de matriz con nro de filas conocida, nro de columnas diferente
        for (int i = 0; i < filas; i++) {
            m[i] = entrada.nextLine().toCharArray();
        }
        entrada.close(); // cierre del archivo
        
        //lectura de mensaje oculto
        String mensaje_oculto = JOptionPane.showInputDialog("Mensaje codificado:");
        
        
        // Ciclo que evita java.lang.NullPointerException hasta que el usuario ingrese un valor
        while (mensaje_oculto==null){ 
            mensaje_oculto = JOptionPane.showInputDialog("Mensaje codificado:");
        }
        
        //cálculo para descubrir mensaje
        char codificado[];
        char space = " ".charAt(0); //caracter espacio
        String toShow; // string a mostrar
        mensaje_oculto = mensaje_oculto.toUpperCase(); //garantia de mayusculas
        codificado = mensaje_oculto.toCharArray(); //conversion a array de caracteres
        toShow = decodificar(m,codificado,space); // llamada al metodo decodificar que regresa el mensaje
        
        //impresion de resultados
        System.out.println("entrada: "+mensaje_oculto); //impresion de entrada por consola
        System.out.println("salida: "+toShow); // impresion de salida por consola
        JOptionPane.showMessageDialog(null, mensaje_oculto); // muestra mensaje codificado en GUI
        JOptionPane.showMessageDialog(null, toShow); // muestra de la salida en GUI
}
    
    /**
     * Metodo de clase para realizar proceso de busqueda secuencial en vector[]
     * de elementos tipo char del elemento buscado valor tipo char
     * @param vector Arreglo donde se realiza la busqueda
     * @param valor  Valor buscado, tipo char
     * @return 
     */
    public static int busqueda(char vector[], char valor){
        int resp = -1; //no encontrado
        int i = 0;
        while (i < vector.length && resp == -1){
            if (valor == vector[i]){
                resp = i;
            }
            i++;
        }
        return resp;
    }
    
    /**
     * Metodo que se encarga de decodificar los valores del mensaje enviado por el usuario
     * @param vector arreglo donde se obtiene los caracteres resultantes
     * @param mensajeCodificado mensaje a decodificar
     * @param espacio caracter de espacio a evaluar
     * @return 
     */
    public static String decodificar(char vector[][], char mensajeCodificado[], char espacio){
        int posRes; // posicion de los caracteres en el mensaje
        int sizeInput; //longitud de mensaje codificado
        String toShow= ""; // cadena de caracteres vacia que contendra la respuesta del metodo
        sizeInput = mensajeCodificado.length;
        
        for (int i = 0; i < sizeInput; i++) {
            for (int j = 0; j < vector.length; j++) {
                posRes = busqueda(vector[j],mensajeCodificado[i]); // busqueda secuencial en matriz de teclado
                if (posRes!=-1) {
                        if(posRes!=0 && posRes!=-1){
                            toShow +=  vector[j][posRes-1]; //agrega los caracteres que va encontrando
                        }
                } 
            }
            if (mensajeCodificado[i]==espacio) {
                                toShow +=  espacio;  //condicion para barra espaciadora
                }
        }
        return toShow;
    } 

}
