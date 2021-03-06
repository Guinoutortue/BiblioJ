package biblioj


import java.util.List;
import java.util.regex.Pattern.Neg;

import org.springframework.dao.DataIntegrityViolationException

class LivreController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	RecuperationLivreService recupLivre


	def index() {
		recupLivre = new RecuperationLivreService()
		redirect(action: "list", params: params)
	}

	def list(Integer max) {


		params.max = Math.min(max ?: 5, 100)
		[livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
	}

	def livrelist(Integer max) {

		recupLivre = new RecuperationLivreService()
		def resultTitre = new ArrayList<Livre>()
		def resultNom = new ArrayList<Livre>()
		def resultType = new ArrayList<Livre>()
		def result = new ArrayList<Livre>()

		if(!params["titre"].equals("") && params["titre"] !=  null) {
			resultTitre = recupLivre.getByTitre("%"+ (params["titre"]) +"%")
		}

		if(!params["nom"].equals("") && params["nom"] !=  null) {
			resultNom = recupLivre.getByNom("%"+params["nom"]+"%")
		}

		if(!params["typedoc"].equals("") && params["typedoc"] !=  null) {
			resultType = recupLivre.getByType("%" + params["typedoc"] + "%")
		}


		if(resultNom.size()==0) {
			if(resultTitre.size()==0) {
				result=resultType
			} else if(resultType.size()==0) {
				result=resultTitre
			} else {
				result=resultTitre
				result.retainAll(resultType)
			}
		} else if(resultTitre.size()==0) {
			if(resultType.size()==0) {
				result=resultNom
			} else {
				result=resultNom
				result.retainAll(resultType)
			}
		} else if(resultType.size()==0) {
			result=resultNom
			result.retainAll(resultTitre)
		} else {
			result=resultNom
			result.retainAll(resultTitre)
			result.retainAll(resultType)
		}

		
		params.max = Math.min(max ?: 5, 100)
		[livreInstanceList: result.subList(params.offset==null ? 0 : Math.max(params.offset.toInteger()-1,0) , params.offset==null ? Math.min(result.size(),5) : Math.min(params.offset.toInteger()+params.max-1,result.size())), livreInstanceTotal: result.size()]
	}

	def create() {
		[livreInstance: new Livre(params)]
	}

	def save() {
		def livreInstance = new Livre(params)
		if (!livreInstance.save(flush: true)) {
			render(view: "create", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'livre.label', default: 'Livre'),
			livreInstance.id
		])
		redirect(action: "show", id: livreInstance.id)
	}

	def show(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def edit(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def update(Long id, Long version) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (livreInstance.version > version) {
				livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'livre.label', default: 'Livre')] as Object[],
						"Another user has updated this Livre while you were editing")
				render(view: "edit", model: [livreInstance: livreInstance])
				return
			}
		}

		livreInstance.properties = params

		if (!livreInstance.save(flush: true)) {
			render(view: "edit", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'livre.label', default: 'Livre'),
			livreInstance.id
		])
		redirect(action: "show", id: livreInstance.id)
	}

	def delete(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			livreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}
