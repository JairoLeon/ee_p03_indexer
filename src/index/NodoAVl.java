
package index;

public class NodoAVl <T extends Comparable<T>> {
private T dato;
private NodoAVl<T> izq;
private NodoAVl<T> der;
private int Fe;

public int getFe() {
	return Fe;
}


public void setFe(int Fe) {
	this.Fe = Fe;
}


public NodoAVl(T dato){
this.dato=dato;
izq=null;
der=null;
Fe=0;
}


public T getDato() {
	return dato;
}
public void setDato(T dato) {
	this.dato = dato;
}
public NodoAVl<T> getIzq() {
	return izq;
}
public void setIzq(NodoAVl<T> izq) {
	this.izq = izq;
}
public NodoAVl<T> getDer() {
	return der;
}
public void setDer(NodoAVl<T> der) {
	this.der = der;
}



}