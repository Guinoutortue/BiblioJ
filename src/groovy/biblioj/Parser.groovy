package biblioj


class Parser {
	public static void parserCSV() {
		def csv = new File("grails-app/conf/Les 1000 titres les plus recherches en 2012.csv")
		def livre
		def auteur
		def model=false;
		csv.splitEachLine(';') { row ->
			if(model){
				livre = Livre.findByTitre(row[3]) ?: new Livre(
				  titre: row[3],
				  nombreExemplaires: 3,
				  nombreExemplairesDisponibles: 3
			   ).save(failOnError: true, flush: true)	
			   
			   String rowAuteur = row[4]
			   println ""
			   println rowAuteur
			   if(rowAuteur!=null) {
				   String[] rowNom =  rowAuteur.split("[,]")
				   println rowNom
				   boolean nomEtPrenom = rowNom.size()==2
				   println rowNom.size()
				   
				   auteur = Auteur.findByNom(rowNom[0]).each{ a ->
					   println nomEtPrenom
					   if(nomEtPrenom) {
						   a.findByPrenom(rowNom[1]) ?: new Auteur(
							   nom: rowNom[0],
							   prenom: rowNom[1],
							   Livre.findByTitre(row[3])
						   	).save(failOnError: true, flush: true)
					   } else {
						   new Auteur(
							   nom: rowNom[0],
							   prenom: "",
							   livres: Livre.findByTitre(row[3])
							   ).save(failOnError: true, flush: true)
					   }
					}
			   }
			} else {
				model=true;
			}
		}
	}
}
