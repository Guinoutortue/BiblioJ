package biblioj

class RecuperationLivreService {

    def getByTitre(String titre) {
		def result = new ArrayList<Livre>()
		result = Livre.findAllByTitreLike(titre)
			return result

	}
	
	def getByNom(String nom) {
		def result = new ArrayList<Livre>()
		Auteur auteur = Auteur.findByNomLike(nom)

		for (int i=0; i<(auteur.livres.size()); i++) {
			Livre livre = auteur.livres.toList().get(i)
			result.add(livre)
		}
		return result
		
	}
	
	def getByType(String t) {
		def result = new ArrayList<Livre>()
		TypeDocument l = TypeDocument.findByIntitule(t)
		result = Livre.findAllByTypeDocument(l)
			return result

	}
}
