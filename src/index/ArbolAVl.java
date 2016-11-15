
package index;

public class ArbolAVl<T extends Comparable<T>> {

private NodoAVl<T> raiz;

public ArbolAVl(){
raiz=null;	
}

public NodoAVl<T> getRaizAVL(){
return raiz;	
}

public void setRaiz(NodoAVl<T> p){
raiz=p;
}

public String buscar(NodoAVl<T> raiz, T dato){
String s = "";
if (raiz != null){
if (dato.compareTo(raiz.getDato()) < 0){ // Vamos por la izquierda
s = buscar(raiz.getIzq(), dato);
}else if (dato.compareTo(raiz.getDato()) > 0){ // Vamos por la derecha
s = buscar(raiz.getDer(), dato);
}else{
s = "El objeto se encontró :3";
}
}else{
s = "No se encontró el objeto :´V";
}
        
return s;
 }	


public void revisarI(NodoAVl<T> nodo, Integer direccion){
    NodoAVl<T> nodo1, nodo2, nodo3;
    if (direccion ==-1){
      nodo1=nodo.getIzq();
      nodo2=nodo1.getIzq();
    }else{
      nodo1=nodo.getDer();
      nodo2=nodo1.getIzq();    
    }
    if(nodo2.getFe() == -1){// Rotacion II
        nodo1.setIzq(nodo2.getDer());
        nodo2.setDer(nodo1);
        nodo1.setFe(0);
        if (direccion == -1){
            nodo.setIzq(nodo2);
        }else{
            nodo.setDer(nodo2); 
        }
    }else{ // Rotacion ID
        nodo3 = (NodoAVl<T>) nodo2.getDer();
        nodo1.setIzq(nodo3.getDer());
        nodo3.setDer(nodo1);
        nodo2.setDer(nodo3.getIzq());
        nodo3.setIzq(nodo2);
        if(nodo3.getFe() == -1){
            nodo1.setFe(1);
        }else{
            nodo1.setFe(0);
        }
        if (nodo3.getFe() == 1){
            nodo2.setFe(-1);
        }else{
            nodo2.setFe(0);
        }
        if (direccion == -1){
            nodo.setIzq(nodo3);
        }else{
            nodo.setDer(nodo3); 
        }
        
    }
    nodo1.setFe(0);
}

public void revisarD(NodoAVl<T> nodo, Integer direccion){
	NodoAVl<T> nodo1, nodo2, nodo3;
    if (direccion ==-1){
        nodo1=nodo.getIzq();
        nodo2=nodo1.getDer();
    }else{
        nodo1=nodo.getDer();
        nodo2=nodo1.getDer();
    }
    if(nodo2.getFe() == 1){// Rotacion DD
        nodo1.setDer(nodo2.getIzq());
        nodo2.setIzq(nodo1);
        nodo1.setFe(0);
        if (direccion == -1){
            nodo.setIzq(nodo2);
        }else{
            nodo.setDer(nodo2); 
        }
        
        
    }else{ // Rotacion DI
        nodo3 = (NodoAVl<T>) nodo2.getIzq();
        nodo1.setDer(nodo3.getIzq());
        nodo3.setIzq(nodo1);
        nodo2.setIzq(nodo3.getDer());
        nodo3.setDer(nodo2);
        if(nodo3.getFe() == 1){
            nodo1.setFe(-1);
        }else{
            nodo1.setFe(0);
        }
        if (nodo3.getFe() == -1){
            nodo2.setFe(1);
        }else{
            nodo2.setFe(0);
        }
        if (direccion == -1){
            nodo.setIzq(nodo3);
        }else{
            nodo.setDer(nodo3); 
        }
        
    }
    nodo1.setFe(0);
}

private Integer _insertar(NodoAVl<T> nodo, T dato){
    Integer resultado = 0;
    if (dato.compareTo(nodo.getDato()) < 0){
        if (nodo.getIzq() == null){
            nodo.setIzq(new NodoAVl<T>(dato));
            switch (nodo.getFe()){
            case 1: // El arbol se balanceó
                nodo.setFe(0);
                resultado = 0;
                break;
            case 0: // Se cargó del lado izquierdo
                nodo.setFe(-1);
                resultado = 1;
                break;              
            }
            //resultado = 1;
            
        }else{
            switch (_insertar(nodo.getIzq(),dato)){
            case 1: // Se insrtó un dato nuevo, revisar.
                switch (nodo.getFe()){
                case 1: // El arbol se balanceó
                    nodo.setFe(0);
                    resultado = 0;
                    break;
                case 0: // Se cargó del lado izquierdo
                    nodo.setFe(-1);
                    resultado = 1;
                    break;  
                case -1: // Reestructuracion del arbol
                    //if (nodo == raiz){
                        resultado = -1;
                    //}else{                        
                        
                        
                        
                    //}
                    break;
                }
                break;
            case -1:
                revisarI(nodo,-1);
                break;
            case -2:
                revisarD(nodo,-1);
                break;
                
        
                
            }
            
            
        }
    }else{
        if (dato.compareTo(nodo.getDato()) > 0){
            if (nodo.getDer() == null){
                nodo.setDer(new NodoAVl<T>(dato));
                switch (nodo.getFe()){
                case -1: // El arbol se balanceó
                    nodo.setFe(0);
                    resultado = 0;
                    break;
                case 0: // Se cargó del lado izquierdo
                    nodo.setFe(1);
                   resultado = 1;
                    break;              
                }
            }else{
                switch (_insertar(nodo.getDer(),dato)){
                case 1: // Se insrtó un dato nuevo, revisar.
                    switch (nodo.getFe()){
                    case -1: // El arbol se balanceó
                        nodo.setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se cargó del lado der
                        nodo.setFe(1);
                        resultado = 1;
                        break;  
                    case 1: // Reestructuracion del arbol
                            resultado = -2;
                        }
                        break;
                case -1:
                    revisarI (nodo,1);
                    break;
                case -2:
                    revisarD(nodo,1);
                break;
                
                    }                       
                    
                }
            }
        }
    
    
    return resultado;
 
}


public Integer insertar(T dato) {
    if (raiz == null){
        raiz = new NodoAVl<T>(dato);
    }else{
        Integer result = _insertar(raiz, dato);
        if (result == -1){
        
            // Reestructuración del arbol
                NodoAVl<T> nodo1, nodo2;
                nodo1= raiz.getIzq();
                
                if(nodo1.getFe() == -1){// Rotacion II
                    raiz.setIzq(nodo1.getDer());
                    nodo1.setDer(raiz);
                    raiz.setFe(0);
                    raiz = nodo1;
                }else{ // Rotacion ID
                    nodo2 =  nodo1.getDer();
                    raiz.setIzq(nodo2.getDer());
                    nodo2.setDer(raiz);
                    nodo1.setDer(nodo2.getIzq());
                    nodo2.setIzq(nodo1);
                    if(nodo2.getFe() == -1){
                        raiz.setFe(1);
                    }else{
                        raiz.setFe(0);
                    }
                    if (nodo2.getFe() == 1){
                        nodo1.setFe(-1);
                    }else{
                        nodo1.setFe(0);
                    }
                    raiz = nodo2;
                }
                raiz.setFe(0);
        
            
                     
        }else if (result == -2){
            // Reestructuración del arbol
            NodoAVl<T> nodo1, nodo2;
            nodo1=raiz.getDer();
            
            if(nodo1.getFe() == 1){// Rotacion DD
                raiz.setDer(nodo1.getIzq());
                nodo1.setIzq(raiz);
                raiz.setFe(0);
                raiz = nodo1;
            }else{ // Rotacion DI
                nodo2 = nodo1.getIzq();
                raiz.setDer(nodo2.getIzq());
                nodo2.setIzq(raiz);
                nodo1.setIzq(nodo2.getDer());
                nodo2.setDer(nodo1);
                if(nodo2.getFe() == 1){
                    raiz.setFe(-1);
                }else{
                   raiz.setFe(0);
                }
                if (nodo2.getFe() == -1){
                    nodo1.setFe(1);
                }else{
                    nodo1.setFe(0);
                }
                raiz = nodo2;
                
            }
            
            raiz.setFe(0);
    
        }
    }
    return 0;
}

}