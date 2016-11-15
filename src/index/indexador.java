package index;

import java.io.*;

public class indexador {

BTreePrinter j=new BTreePrinter();
BufferedReader buffer;
ArbolAVl<String> prin=new ArbolAVl();



public NodoAVl Arbol(){
return prin.getRaizAVL();
}

public void setArbol(NodoAVl a){
prin.setRaiz(a);
}

public String Datos(String c)throws IOException{
 String linea;
String result="";    

String [] datos;	


buffer= new BufferedReader(new FileReader(c));

linea =buffer.readLine();
datos=linea.split("\\|");


 for (String dato : datos) {
        result += dato + "|";
    }
return result;
}






public void LlenarString(int numero) throws IOException{
  
    String linea =buffer.readLine();	
    String[] l;
    
    
   
    
	while(linea != null){
        l=linea.split("\\|");   
	prin.insertar(l[numero-1]);
	linea=buffer.readLine();
        }
	
	
	
}


}




