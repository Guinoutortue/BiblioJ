package biblioj

class RecuperationLivreService {

    def getByTitre(String titre) {
		def result = Livre.findAllByTitreLike(titre)
			return result

	}
	
	def getByNom(String nom) {
		def result = Auteur.findAllByNomLike(nom)
			return result

	}
	
	def getByType(String t) {
		def l = TypeDocument.findByIntitule(t)
		def result = Livre.findAllByTypeDocument(l)
			return result

	}
}
