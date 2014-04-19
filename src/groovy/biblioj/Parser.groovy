package biblioj


class Parser {
	public static void parserCSV() {
		def csv = new File("grails-app/conf/Les 1000 titres les plus recherches en 2012.csv")
		def livre
		def auteur
		def typedoc
		def model=false; // pour ne pas traiter la première ligne
		csv.splitEachLine(';') { row ->
			
			if(model){
				//************
				 //	Livre
				 //************
				livre = Livre.findByTitre(row[3]) ?: new Livre(
				  titre: row[3],
				  nombreExemplaires: 3,
				  nombreExemplairesDisponibles: 3
			   ).save(failOnError: true, flush: true)	
			
			   
			   //************
				//	Auteur
				//************
			   String rowAuteur = row[4]
			   
			   if(rowAuteur!=null) {
				   String[] rowNom =  rowAuteur.split("[,]")
				   boolean nomEtPrenom = rowNom.size()==2 //pour savoir si il a un nom ET un prenom
				   
				   if(nomEtPrenom) {
					    auteur = Auteur.findByNomAndPrenom(rowNom[0],rowNom[1]) ?: new Auteur(
							   nom: rowNom[0],
							   prenom: rowNom[1]
							   ).save(failOnError: true, flush: true)
					   
				   } else {
					   auteur = Auteur.findByNomAndPrenom(rowNom[0],"") ?: new Auteur(
						   nom: rowNom[0],
						   prenom: ""
						   ).save(failOnError: true, flush: true)
				   }
				   
				   livre.addToAuteurs(auteur).save(flush: true)
			   }
			   
			   //************
				//	Type
				//************
			   typedoc = TypeDocument.findByIntitule(row[1]) ?: new TypeDocument(
				   intitule: row[1] 
				).save(failOnError: true, flush: true)
			   
				if(typedoc!=null) {
					livre.typeDocument=typedoc
				}
				
			} else {
				model=true;
			}
			
		}
	}
}
