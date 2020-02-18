/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlpracticalibros;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import static javafx.beans.binding.Bindings.length;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import static jdk.nashorn.internal.objects.NativeArray.length;
import static jdk.nashorn.internal.objects.NativeArray.length;
import static jdk.nashorn.internal.objects.NativeString.length;
import static oracle.jrockit.jfr.events.Bits.length;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author fecq8
 */
public class XmlPracticaLibros {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String flechas = ANSI_BLUE+" --> "+ANSI_RESET;
    public static final String asteriscos = ANSI_BLUE+"  ***  "+ANSI_RESET;
    public static ArrayList<StarWars> universo = new ArrayList();
    public static StarWars starWars;
    public static StringBuilder buffer = new StringBuilder();
          
    public static void main(String[] args) {
       
        menu();
    }
    
    public static void menu(){
       
        System.out.println("\n¿Bienvenido que quieres hacer?\n1. Modo DOM y SAX\n2. Aceso a BBDD");
        
        Scanner sc1 = new Scanner(System.in);
        String respuesta1 = sc1.nextLine();
        if(respuesta1.equals("1")){
            menu1();
            
        }else if(respuesta1.equals("2")){
            menu2();
     }else{
            opcionInvalida();
        }
     }
     
    public static void menu1(){
        File file = new File("miLibreria.xml");
        System.out.println("\n¿Bienvenido al UNIVERSO STARWARS que quieres hacer?\n1. Mostrar toda la información\n2. Buscar por codigo Personaje(modo DOM)\n3. Buscar por Lado de la Fuerza\n4. Buscar por Raza(modo SAX)");
        Scanner sc1 = new Scanner(System.in);
        String respuesta1 = sc1.nextLine();
        if(respuesta1.equals("1")){
            leerDoc();
            
        }else if(respuesta1.equals("2")){
            try{
               
            buscaCodPer(file,sc1);
            
            }catch (Exception e){
                
            }
        }else if(respuesta1.equals("3")){
            buscaLadoFuerza();
        }else if(respuesta1.equals("4")){
            buscaPorEtiquetaSAX();
        }else{
            opcionInvalida();
        }
     }
     
    public static void menu2() {
           
        System.out.println("\n¿Bienvenido que quieres hacer?\n1. Ver todos los personajes\n2. Insertar personaje\n3. Filtrar por Lado de la Fuerza \n4. Filtrar por Precio");
        Scanner sc1 = new Scanner(System.in);
        String respuesta1 = sc1.nextLine();
        if(respuesta1.equals("1")){
            try{
            verBBDD();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(respuesta1.equals("2")){
            introducirNuevoMuñeco();
        }else if(respuesta1.equals("3")){
            buscarPorLadoDeLaFuerza();
        }else if(respuesta1.equals("4")){
            filtrarPorPrecio();
        }else{
            opcionInvalida();
        }
    }
       
    public static void menuEligeEtiqueta() {
           System.out.println("\nIntroduce la etiqueta por la que filtrar el contenido(igual que aparece en las opciones):\nnombre\napellido\nlado\nraza");
            Scanner sc2 = new Scanner(System.in);
            String respuesta = sc2.nextLine();
                
       }

    public static void volverMenu(){
        System.out.println("\n¿quieres hacer otra consulta?\n1. MENU PRINCIPAL\n2. DOM/SAX\n3. BASE DE DATOS\n4. NO");
        Scanner sc1 = new Scanner(System.in);
        String respuesta1 = sc1.nextLine();
        if(respuesta1.equals("1")){
            menu();
        }else if(respuesta1.equals("2")){
            menu1();
        }else if(respuesta1.equals("3")){
            menu2();
        }else if(respuesta1.equals("4")){
            System.exit(0);
        }else{
            opcionInvalida2();
        }
     }
    
    public static void opcionInvalida(){
        System.out.println("\nNICO PULSA UN NUMERO VALIDO ANDA!!!!!!!!!");
        menu();
     }
    
    public static void opcionInvalida2(){
        System.out.println("\nNICO PULSA UN NUMERO VALIDO ANDA!!!!!!!!!");
        volverMenu();
     }
    
    public static void leerDoc(){
     
        try { 
            //Creamos el manejador SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            File file = new File("miLibreria.xml");
            DefaultHandler handler = new DefaultHandler() {
                //cuando se llega al principio de un elemento imprimimos su nombre por pantalla
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    switch(qName){
            
            case "starwars":
                starWars = new StarWars();
                universo.add(starWars);
                break;
            case "cod_personaje":
                break;
            case "nombre":
                break;
            case "lado":
                break;
            case "raza":
                break;
            case "pri_aparicion":
                break;
            case "ult_aparicion":
                buffer.delete(0, buffer.length());
                break;
        }
                }
                //cuando se llega al final del elemento lo indicamos por pantalla

                public void endElement(String uri, String localName,String qName)
                        throws SAXException {
                    switch(qName){    
            case "ult_aparicion":
                starWars.setUlt_aparicion(buffer.toString());
                break;
            case "pri_aparicion":
                starWars.setPri_aparicion(buffer.toString());
                break;
            case "raza":
                starWars.setRaza(buffer.toString());
                break;   
            case "lado":
                starWars.setLado(buffer.toString());
                break;
            case "nombre":
                starWars.setNombre(buffer.toString());
                break;    
            case "cod_personaje":
                starWars.setCod_personaje(buffer.toString());
                break;
            case "starwars":
                break;
            
        }
                }
                //cuando leemos el contenido de un elemento lo mostramos por pantalla

                public void characters(char ch[], int start, int length)
                        throws SAXException {
                    buffer.append(ch,start,length);
                }
            };
            //Encapsulamos el fichero xml a leer indicando que tiene formato utf-8
            
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            saxParser.parse(is, handler);
            volverMenu();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    public ArrayList<StarWars> getUniverso() {
        return universo;
    }
    
    public static void recorrerRamaDom(Node nodo) {
        
        if (nodo != null) {
            System.out.println(nodo.getNodeName() + ": " + nodo.getNodeValue());
            NodeList hijos = nodo.getChildNodes();
            
            for (int i = 0; i < hijos.getLength(); i++) {
                Node nodoNieto = hijos.item(i);
                recorrerRamaDom(nodoNieto);
            }
        }
    
    }

    private static void buscaCodPer(File file, Scanner sc) {
        try {
            File f = new File("miLibreria.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document documento = builder.parse(file);
            System.out.println("\nIntroduce el cod de personje que quieres buscar:");
            Scanner sc2 = new Scanner(System.in);
            String respuesta2 = sc2.nextLine();
            boolean encontrado = false;
            //Generamos una lista que contenga todos los nodos cuyo nombre es DNI
            NodeList list = documento.getElementsByTagName("cod_personaje");
            int i = 0;
            //recorremos la lista para buscar el DNI
            while (i < list.getLength()) {
                Node n = list.item(i);
                //evaluamos si el nodo actual de la lista su valor es el dni
                if (n.getFirstChild().getNodeValue().equals(respuesta2)) {
                    //nos posicionamos en el nodo padre, que es el nodo alumno
                    Node Padre = n.getParentNode();
                    /*llamamos al procedimiento que muestra por pantalla todos los
                    nodos hijos junto a sus valores*/
                    recorrerRamaDom(Padre);
                    //ponemos la variable de control a verdadero
                    encontrado = true;
                    break;
                }
                i++;
            }
            if (encontrado == false) {
                System.out.println("El codigo del personaje no corresponde con ningun personaje");
            }
            volverMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static void buscaLadoFuerza() {
         try {
            File file = new File("miLibreria.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document documento = builder.parse(file);
            System.out.println("\nIntroduce el lado de la fuerza(Luminoso , Oscuro):");
            Scanner sc2 = new Scanner(System.in);
            String respuesta2 = sc2.nextLine();
            boolean encontrado = false;
            //Generamos una lista que contenga todos los nodos cuyo nombre es lado
            NodeList list = documento.getElementsByTagName("lado");
            int i = 0;
            //recorremos la lista para buscar el lado
            while (i < list.getLength()) {
                Node n = list.item(i);
                //evaluamos si el nodo actual de la lista su valor es el lado
                if (n.getFirstChild().getNodeValue().equals(respuesta2)) {
                    //nos posicionamos en el nodo padre, que es el nodo alumno
                    Node Padre = n.getParentNode();
                    /*llamamos al procedimiento que muestra por pantalla todos los
                    nodos hijos junto a sus valores*/
                    recorrerRamaDom(Padre);
                    //ponemos la variable de control a verdadero
                    encontrado = true;
                    
                }
                i++;
            }
            if (encontrado == false) {
                System.out.println("el dato introducido no corresponde con ningún lado de la fuerza");
            }
            volverMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static void buscaPorEtiquetaSAX() {
        try {
            //Creamos el manejador SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            System.out.println("\nIntroduce la etiqueta por la que filtrar el contenido(igual que aparece en las opciones):\nnombre\napellido\nlado\nraza");
            Scanner sc2 = new Scanner(System.in);
            String respuesta = sc2.nextLine();
            String cadena = "";
                if(respuesta.equals("nombre") || respuesta.equals("apellido") ||respuesta.equals("lado") ||respuesta.equals("raza")){
           
            DefaultHandler handler = new DefaultHandler() {
                 
                //cuando se llega al principio de un elemento imprimimos su nombre por pantalla
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {
                    if(qName.equals(respuesta)){
                        
                        System.out.println("<" + qName + ">");
                        
                    }
                }
                //cuando se llega al final del elemento lo indicamos por pantalla
                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {
                    if(qName.equals(respuesta)){
                        
                    System.out.println("</" + qName + ">");
                    }
                }
               
                public void characters(char ch[], int start, int length)
                        throws SAXException {
                    
                    System.out.println(new String(ch, start, length));
                 }
            };
            File file = new File("miLibreria.xml");
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            saxParser.parse(is, handler);
            volverMenu();
                }else{
                     System.out.println("\nLA HAS LIADO!! VUELVE A ELEGIR QUE QUIERES HACER");
                     menu1();
                     
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public static void modificarDNI(Document miDocumento, String datoCambio, String NuevoDato) {
        try {
            System.out.println("\nIntrocude");
            Scanner sc1 = new Scanner(System.in);
            String respuesta1 = sc1.nextLine();
            boolean encontrado = false;
            //Generamos una lista que contenga todos los nodos cuyo nombre es DNI
            NodeList list = miDocumento.getElementsByTagName("dni");
            int i = 0;
            //recorremos la lista para buscar el DNI
            while (i < list.getLength()) {
                Node n = list.item(i);
                //evaluamos si el nodo actual de la lista su valor es el dni
                if (n.getFirstChild().getNodeValue().equals(datoCambio)) {
                    //modificamos el valor del primer hijo, que es el tecto donde se almacena el DNI, por el nuevo DNI
                    n.getFirstChild().setNodeValue(NuevoDato);

                    //nos posicionamos en el nodo padre, que es el nodo alumno
                    Node Padre = n.getParentNode();
                    /*llamamos al procedimiento que muestra por pantalla todos los
                    nodos hijos junto a sus valores para comprobar que se ha cambiado el DNI*/
                    recorrerRamaDom(Padre);
                    //ponemos la variable de control a verdadero
                    encontrado = true;
                    guardarDOMcomoFILE(miDocumento);
                    break;

                }
                i++;
            }
            if (encontrado == false) {
                System.out.println("DNI no se encuentra como alumno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void guardarDOMcomoFILE(Document doc) {
        try {

            // Crea un fichero
            File archivo_xml = new File("miLibreria.xml");
            // Especifica el formato de salida
            OutputFormat format = new OutputFormat(doc);

            // Especifica que la salida esté indentada
            format.setIndenting(true);

            // Escribe el contenido en el FILE
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);

            serializer.serialize(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void verBBDD(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/starwars","root","");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personaje");
            //con next nos vamos posicionando en cada registro
            while(rs.next()){
                System.out.println("Nombre"+flechas+ANSI_RED + rs.getObject("nombre")+ANSI_RESET+asteriscos+"Lado de la fuerza"+flechas
                    +ANSI_RED+rs.getObject("lado")+ANSI_RESET+asteriscos+"Raza"+flechas+ANSI_RED +rs.getObject("raza")+ANSI_RESET
                    +asteriscos+"Pri_aparicion"+flechas+ANSI_RED +rs.getObject("pri_aparicion")+ANSI_RESET+asteriscos+"Ult_aparicion"+flechas
                    +ANSI_RED+rs.getObject("ult_aparicion")+ANSI_RESET);
            }
            rs.close();
            volverMenu();
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    private static void introducirNuevoMuñeco(){
        try{
            System.out.println("\nPOR FAVOR LA PRIMERA EN MAYUSCULAS");
            System.out.println("\nIntrocude nombre");
            Scanner sc1 = new Scanner(System.in);
            String respuesta1 = sc1.nextLine();
            System.out.println("\nIntrocude lado de la fuerza (Luminoso/Oscuro)");
            Scanner sc2 = new Scanner(System.in);
            String respuesta2 = sc2.nextLine();
            System.out.println("\nIntroduce la raza");
            Scanner sc3 = new Scanner(System.in);
            String respuesta3 = sc3.nextLine();
            System.out.println("\nIntroduce la Primera aparición del personaje en la pelicula");
            Scanner sc4 = new Scanner(System.in);
            String respuesta4 = sc4.nextLine();
            System.out.println("\nIntroduce la Ultima aparición del personaje en la pelicula");
            Scanner sc5 = new Scanner(System.in);
            String respuesta5 = sc5.nextLine();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/starwars","root","");
            Statement st = connection.createStatement();
            st.executeUpdate("insert into personaje values(NULL,'"+respuesta1+"','"+respuesta2+"','"+respuesta3+"','"+respuesta4+"','"+respuesta5+"')");
            st.executeQuery("SELECT @last := LAST_INSERT_ID();");
            System.out.println("\nNECESITO MAS DATOS PARA LA BASE DE DATOS");
            System.out.println("\nIntroduce el stock de este muñeco");
            Scanner sc6 = new Scanner(System.in);
            String respuesta6 = sc6.nextLine();
            System.out.println("\nIntroduce el precio de este muñeco con dos decimales utilizando este caracter(.)entre en entero y el decimal");
            Scanner sc7 = new Scanner(System.in);
            String respuesta7 = sc7.nextLine();
            st.executeUpdate("insert into productos values(@last,'"+respuesta1+"','"+respuesta6+"','"+respuesta7+"')");
            System.out.println("DATOS GUARDADOS CORRECTAMENTE, QUE BUENO EH NICO!!!");
            volverMenu();
        }catch(Exception e){
        e.printStackTrace();
    }
    }
    
     private static void buscarPorLadoDeLaFuerza(){
        try{
            System.out.println("\nPOR FAVOR LA PRIMERA EN MAYUSCULAS");
            System.out.println("\nIntrocude lado de la fuerza(Luminoso/Oscuro)");
            Scanner sc1 = new Scanner(System.in);
            String respuesta1 = sc1.nextLine();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/starwars","root","");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personaje where lado ='"+respuesta1+"'");
            //con next nos vamos posicionando en cada registro
            
            if(rs.next() == false){
                System.out.println("NO HAY NINGUNO DEL LADO " + respuesta1);
                
            }else{
                rs.beforeFirst();
                while(rs.next() == true){
                System.out.println("Nombre"+flechas+ANSI_RED + rs.getObject("nombre")+ANSI_RESET+asteriscos+"Lado de la fuerza"+flechas
                    +ANSI_RED+rs.getObject("lado")+ANSI_RESET+asteriscos+"Raza"+flechas+ANSI_RED +rs.getObject("raza")+ANSI_RESET
                    +asteriscos+"Pri_aparicion"+flechas+ANSI_RED +rs.getObject("pri_aparicion")+ANSI_RESET+asteriscos+"Ult_aparicion"
                    +flechas+ANSI_RED+rs.getObject("ult_aparicion")+ANSI_RESET);
                }
            }
            rs.close();
            volverMenu();
        }catch(Exception e){
        e.printStackTrace();
        }
}
     
     private static void filtrarPorPrecio(){
         try{
            System.out.println("\nIntrocude lo maximo que quieres gastarte");
            Scanner sc1 = new Scanner(System.in);
            String respuesta1 = sc1.nextLine();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/starwars","root","");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personaje,productos where precio <'"+respuesta1
                    +"' and productos.id_per = personaje.id_per");
            if(rs.next() == false){
                System.out.println("NO HAY NINGUN PRODUCTO POR MENOS DE "+respuesta1+"€. SUBE EL PRECIO RATA!!!!!! ");
                
            }else{
                rs.beforeFirst();
                while(rs.next() == true){
                System.out.println("Nombre"+flechas+ANSI_RED + rs.getObject("nombre")+ANSI_RESET+asteriscos+"Lado de la fuerza"
                    +flechas+ANSI_RED+rs.getObject("lado")+ANSI_RESET+asteriscos+"Raza"+flechas+ANSI_RED +rs.getObject("raza")
                    +ANSI_RESET+asteriscos+"Pri_aparicion"+flechas+ANSI_RED +rs.getObject("pri_aparicion")+ANSI_RESET+asteriscos
                    +"Ult_aparicion"+flechas+ANSI_RED+rs.getObject("ult_aparicion")+ANSI_RESET+asteriscos+"Precio"+flechas
                    +ANSI_RED +rs.getObject("precio")+"€"+ANSI_RESET+asteriscos+"Stock"+flechas+ANSI_RED +rs.getObject("stock")
                    +" Und"+ANSI_RESET);
                }
            }
            rs.close();
            volverMenu();
     }catch(Exception e){
        e.printStackTrace();
     }
   }
     

}


    

