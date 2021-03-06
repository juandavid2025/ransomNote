package ransomNote;

public class RansomNote { 
	
	public void checkMagazine(String[]magazine,String[]note) {
		int tableSize=magazine.length*2;
		String [] magazineTable = new String[tableSize];
		int place;
		
		// pasar el magazine a una tabla hash
		for(int i =0;i<magazine.length;i++) {
			place=hashValue(magazine[i],tableSize);
			// si el lugar esta vacio----
			if(magazineTable[place]==null) {
				magazineTable[place]=magazine[i];
			}
			//si no lo esta COLISION----
			else {
				
				//metodo de Quadratic Hashing
				//quadraticHashing(place+1,magazine[i],tableSize,magazineTable);
				linealHashing(place+1,magazine[i],tableSize,magazineTable);
			}
			
		}
		// fin de pasar el magazine a tabla hash
		boolean completa=true;
		for(int i=0;i<note.length && completa;i++) {
			
			if(!buscarValorLineal(note[i],magazineTable,tableSize)) {
				completa=false;
			}
			
		}
		// valor si esta o no completa la note en las palabras del magazine
		
		System.out.println(completa);
	}
	
	public int hashValue(String toHash,int tableSize) {
		int hash=0;
		
		hash = StringToCharSum(toHash)%tableSize;
		
		return hash;
	}
	
	public int StringToCharSum(String cadena) {
		int value=0;
		
		for(int i=0;i<cadena.length();i++) {
			value+=cadena.charAt(i);
		}
		
		return value;
	}
	
	public void quadraticHashing(int place,String value,int tableSize,String[] magazineTable) {
		
		int realPlace=realPlace(place,tableSize);
		boolean insertado=false;
		
		do {
			
			if(magazineTable[realPlace(realPlace,tableSize)]==null) {
				magazineTable[realPlace]=value;
				insertado=true;
			}else {
				
				realPlace=realPlace(realPlace*realPlace,tableSize);
			}
			
		}while(!insertado);
	
		
	}
	
	public int realPlace(int place,int tableSize) {
		int realPlace=place;
		
		
		if(place>=tableSize) {
			
			do {
			realPlace=realPlace-tableSize;
			
			}
			while(realPlace>=tableSize);	
		}
		
		
		return realPlace;
	}
	
	public boolean buscarValorLineal(String valorNote,String[]magazine,int tableSize) {
		boolean esta=false;
		if(magazine[hashValue(valorNote,tableSize)]!=null) {
			if(magazine[hashValue(valorNote,tableSize)].equals(valorNote)) {
			esta=true;
		 }
			else {
				buscarValorLinealR(valorNote,magazine,tableSize,hashValue(valorNote,tableSize));
		 }
		}
		return esta;
	}
	
	public boolean buscarValorLinealR(String valorNote,String[]magazine,int tableSize,int posicion) {
		
		if(magazine[posicion]!=null) {
			
		     if(magazine[posicion].equals(valorNote)) {
				return true;
			}else {
				return buscarValorLinealR(valorNote,magazine,tableSize,realPlace(posicion+1,tableSize));
			}
		}else {
			return false;
		}
		
	}
	
	public boolean buscarValor(String valorNote,String[]magazine,int tableSize) {
		boolean esta=false;
		
		if(magazine[hashValue(valorNote,tableSize)].equals(valorNote)) {
			esta=true;
		}
		else {
			esta=buscarValorR(valorNote,magazine,tableSize,realPlace(hashValue(valorNote,tableSize)+1,tableSize));
		}
		
		return esta;
	}
	
	// forma recursiva
	public boolean buscarValorR(String valorNote,String[]magazine,int tableSize,int posicion) {
	
		if(magazine[posicion]!=null) {
	
	     if(magazine[posicion].equals(valorNote)) {
			return true;
		}else {
			return buscarValorR(valorNote,magazine,tableSize,realPlace(posicion*posicion,tableSize));
		}
	}else {
		return false;
	}
  }
	
	public void linealHashing(int place,String value,int tableSize,String[]magazine) {
		
		int realPlace=realPlace(place,tableSize);
		
		if(magazine[realPlace]==null) {
			magazine[realPlace]=value;
		}else {
			linealHashing(place+1,value,tableSize,magazine);
		}
		
	}
}
