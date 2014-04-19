import biblioj.Livre
import biblioj.Auteur


class RecuperationLivre {
	
	def getByTitre(String t) {
		def result = Livre.findAllByTitreLike(t)
			return result

	}
	
	def getByNom(String t) {
		def result = Auteur.findAllByNomLike(t)
			return result

	}

}
