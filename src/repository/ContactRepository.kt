package repository

import entity.ContactEntity

class ContactRepository {

    companion object{
        private val contactList = mutableListOf<ContactEntity>()


        fun save(contactEntity: ContactEntity){
            this.contactList.add(contactEntity)
        }

        fun delete(contactEntity: ContactEntity){
            var index = 0
            for (item in contactList.withIndex()) {
                val value = item.value
                if (value.name == contactEntity.name && value.phone == contactEntity.phone){
                    index = item.index
                    break
                }
            }

            contactList.removeAt(index)
        }

        fun getList() : List<ContactEntity>{
            return this.contactList
        }
    }




}
