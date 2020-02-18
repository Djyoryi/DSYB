/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlpracticalibros;

import java.util.ArrayList;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author fecq8
 */
public class VerHandler extends DefaultHandler{
    private ArrayList<StarWars> universo = new ArrayList();
    private StarWars starWars;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<StarWars> getUniverso() {
        return universo;
    }
    
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atributos) throws SAXException {
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
    
}
