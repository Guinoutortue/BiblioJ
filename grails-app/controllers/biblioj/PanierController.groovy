package biblioj

import org.springframework.dao.DataIntegrityViolationException

class PanierController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def connexion() {
	}

	def login() {

		flash.message ="login succeed"
		session.user = params.username
		println session.user
		HashSet<Livre> hs = new HashSet<Livre>()
		def panierres = Panier.findByUsername(session.user) ?: new Panier(
				username: session.user
				).save(flush: true)
		println panierres
		redirect(uri: '/')
	}

	def logout() {
		session.user =null
		redirect(uri: '/')
	}

	def ajouter() {
		println params
		def l = Livre.findByTitre(params.cache)
		println l
		Panier.findByUsername(session.user).addToMeslivres(l)
		redirect(action: 'list', controller: 'Livre')
	}

	def retirer(){
		println params.cache
		def l = Livre.findByTitre(params.cache)
		println l
		Panier.findByUsername(session.user).removeFromMeslivres(l)
		redirect(action: 'list', controller: 'Livre')
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[panierInstanceList: Panier.list(params), panierInstanceTotal: Panier.count()]
	}

	def create() {
		def user = session["user"]
		session["user"] = "John"
		[panierInstance: new Panier(params)]
	}

	def save() {
		def panierInstance = new Panier(params)
		if (!panierInstance.save(flush: true)) {
			render(view: "create", model: [panierInstance: panierInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'panier.label', default: 'Panier'),
			panierInstance.id
		])
		redirect(action: "show", id: panierInstance.id)
	}

	def show(Long id) {
		def panierInstance = Panier.get(id)
		if (!panierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "list")
			return
		}

		[panierInstance: panierInstance]
	}

	def showLivres() {
		def listeLivres
		if(session.user!=null) {
			listeLivres += "<div>Vous-êtes connecté en temps que : "+session.user+"</div><a href='/BiblioJ/panier/connexion'>Deconnexion</a>"
			listeLivres += "<div>"
			Panier.findByUsername(session.user).getMeslivres().each { l ->
				listeLivres += l.getTitre()
				listeLivres += "<td><form action=\"/BiblioJ/panier/retirer\" method=\"post\">"
				listeLivres += "<input type=\"hidden\" name=\"cache\" value=\""
				listeLivres += l.getTitre()
				listeLivres += "\" id=\"cache\"/>"
				listeLivres += "<input type=\"submit\" name=\"list\" id=\""
				listeLivres += l.getTitre()
				listeLivres += " class=\"list\" value=\"Retirer au panier\"/>"
				listeLivres += "</form></td>"

			}

			listeLivres += "</div>"
		} else {
			listeLivres = "<div><p>Connectez-vous pour voir votre panier :)<p><br/><a href='/BiblioJ/panier/connexion'>Connexion</a></div>"
		}

		[message: listeLivres]
	}

	def edit(Long id) {
		def panierInstance = Panier.get(id)
		if (!panierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "list")
			return
		}

		[panierInstance: panierInstance]
	}

	def update(Long id, Long version) {
		def panierInstance = Panier.get(id)
		if (!panierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (panierInstance.version > version) {
				panierInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'panier.label', default: 'Panier')] as Object[],
						"Another user has updated this Panier while you were editing")
				render(view: "edit", model: [panierInstance: panierInstance])
				return
			}
		}

		panierInstance.properties = params

		if (!panierInstance.save(flush: true)) {
			render(view: "edit", model: [panierInstance: panierInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'panier.label', default: 'Panier'),
			panierInstance.id
		])
		redirect(action: "show", id: panierInstance.id)
	}

	def delete(Long id) {
		def panierInstance = Panier.get(id)
		if (!panierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			panierInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'panier.label', default: 'Panier'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}
