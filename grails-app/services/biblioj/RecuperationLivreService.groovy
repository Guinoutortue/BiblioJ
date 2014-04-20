package biblioj

class RecuperationLivreService {

    def getByTitre(String titre) {
		def result = new ArrayList<Livre>()
		result = Livre.findAllByTitreLike(titre)
			return result

	}
	
	def getByNom(String nom) {
		def result = new ArrayList<Livre>()
		def auteurs = Auteur.findAllByNomLike(nom)

		auteurs.each { auteur ->
			result = result + auteur.livres
		}
		
		
		return result
	}
	
	def getByType(String t) {
		def result = new ArrayList<Livre>()
		TypeDocument l = TypeDocument.findByIntituleLike(t)
		result = Livre.findAllByTypeDocument(l)
			return result

	}
}
