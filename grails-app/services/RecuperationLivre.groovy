import biblioj.Livre


class RecuperationLivre {
	
	def getByTitre(String t) {
		def result = Livre.findAllByTitreLike(t)
			return result

	}

}
