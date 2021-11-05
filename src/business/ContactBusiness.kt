package business

import entity.ContactEntity
import repository.ContactRepository

class ContactBusiness {

    fun getContactsCountDescription(): String{
        val list = getList()

        return when {
            list.isEmpty() ->  "0 contatos"
            list.size == 1 -> "1 contato"
            else -> "${list.size} contatos"
        }
    }

    fun save(name: String, phone: String){
        validate(name, phone)
        val contactEntity = ContactEntity(name, phone)
        ContactRepository.save(contactEntity)
    }

    fun delete(name: String, phone: String){
        valideteDelete(name, phone)

        val contactEntity = ContactEntity(name, phone)
        ContactRepository.delete(contactEntity)
    }

    fun getList(): List<ContactEntity>{
        return ContactRepository.getList()
    }

    private fun valideteDelete(name: String, phone: String){
        if (name == "" || phone == ""){
            throw Exception("É necessário selecionar um contato antes de remover.")
        }
    }


    private fun validate(name: String, phone: String) {
        if (name == ""){
            throw Exception("Nome é obrigatório!")
        }

        if (phone == ""){
            throw Exception("Telefone é obrigatório!")
        }
    }
}
