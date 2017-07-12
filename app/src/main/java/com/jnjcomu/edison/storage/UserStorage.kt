package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences

import com.jnjcomu.edison.model.Ticket
import com.jnjcomu.edison.model.User

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-07
 */

class UserStorage(context: Context) {
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    var name: String? = null
        private set

    var grade: Int? = null
        private set

    var clazz: Int? = null
        private set

    var number: Int? = null
        private set

    var ticket: Ticket? = null
        private set

    init {
        mPref = context.getSharedPreferences(STORAGE_NAME, SHARED_PREFERENCE_MODE)
        mEditor = mPref.edit()

        name = mPref.getString(NAME_PREF, "")
        grade = mPref.getInt(GRADE_PREF, 0)
        clazz = mPref.getInt(CLASS_PREF, 0)
        number = mPref.getInt(NUMBER_PREF, 0)
        ticket = Ticket(mPref.getString(TICKET_CD_PREF, ""))
    }

    val user: User
        get() {
            return User(0, name!!, grade!!, clazz!!, number!!)
        }

    fun saveUser(user: User): UserStorage {
        saveUserName(user.name)
        saveUserGrade(user.grade)
        saveUserClazz(user.clazz)
        saveUserNumber(user.number)

        return this
    }

    fun saveUserName(userName: String): UserStorage {
        this.name = userName

        mEditor.putString(NAME_PREF, userName)
                .commit()

        return this
    }

    fun saveUserGrade(userGrade: Int?): UserStorage {
        this.grade = userGrade

        mEditor.putInt(GRADE_PREF, userGrade!!)
                .commit()

        return this
    }

    fun saveUserClazz(userClazz: Int?): UserStorage {
        this.clazz = userClazz

        mEditor.putInt(CLASS_PREF, userClazz!!)
                .commit()

        return this
    }

    fun saveUserNumber(userNumber: Int?): UserStorage {
        this.number = userNumber

        mEditor.putInt(NUMBER_PREF, userNumber!!)
                .commit()

        return this
    }

    fun saveUserTicket(userTicket: Ticket): UserStorage {
        this.ticket = userTicket

        mEditor
                .putString(TICKET_CD_PREF, userTicket.ticketCode)
                .commit()

        return this
    }

    /**
     * NOTE : Please! this method need some time.
     */
    fun syncUserData() {
        name = mPref.getString(NAME_PREF, "")
        grade = mPref.getInt(GRADE_PREF, 0)
        clazz = mPref.getInt(CLASS_PREF, 0)
        number = mPref.getInt(NUMBER_PREF, 0)
        ticket!!.ticketCode = mPref.getString(TICKET_CD_PREF, "")
    }

    /**
     * NOTE : WARNING! This method execute dangerous task. Please used careful this method.
     */
    fun resetUserStorage() {
        name = ""
        grade = 0
        clazz = 0
        number = 0

        ticket!!.ticketCode = ""

        mEditor.clear().commit()
    }

    companion object {
        private val STORAGE_NAME = "user_storage"
        private val SHARED_PREFERENCE_MODE = Context.MODE_PRIVATE

        private val NAME_PREF = "name"
        private val GRADE_PREF = "grade"
        private val CLASS_PREF = "class"
        private val NUMBER_PREF = "number"
        private val TICKET_CD_PREF = "ticket_cd"
    }
}