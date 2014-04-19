import biblioj.Livre
import biblioj.Auteur
import biblioj.TypeDocument

class RecuperationLivre {
	
	def getByTitre(String titre) {
		def result = Livre.findAllByTitreLike(titre)
			return result

	}
	
	def getByNom(String nom) {
		def result = Auteur.findAllByNomLike(nom)
			return result

	}
	
	def getByType(String type) {
		def result = TypeDocument.findAllByIntitule(type)
			return result

	}
	
	

}
