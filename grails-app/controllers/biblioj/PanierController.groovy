package biblioj

import org.springframework.dao.DataIntegrityViolationException

class PanierController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        //redirect(action: "list", params: params)
    }

	def login() {
		if(params.username == "admin" &params.password == "pass") {
			flash.message ="login succeed"
			session.user = "admin"
		} else {
			flash.message = "login failed"
		}
		redirect(action: 'index')
	}
	
	def logout() {
		session.user =null
		redirect(action: 'index')
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

        flash.message = message(code: 'default.created.message', args: [message(code: 'panier.label', default: 'Panier'), panierInstance.id])
        redirect(action: "show", id: panierInstance.id)
    }

    def show(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        [panierInstance: panierInstance]
    }

    def edit(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        [panierInstance: panierInstance]
    }

    def update(Long id, Long version) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (panierInstance.version > version) {
                panierInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'panier.label', default: 'Panier')] as Object[],
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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'panier.label', default: 'Panier'), panierInstance.id])
        redirect(action: "show", id: panierInstance.id)
    }

    def delete(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        try {
            panierInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "show", id: id)
        }
    }
}
