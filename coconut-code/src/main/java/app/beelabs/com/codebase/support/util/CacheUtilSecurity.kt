package app.beelabs.com.codebase.support.util

import android.content.Context
import android.content.SharedPreferences
import app.beelabs.com.codebase.R

class CacheUtilSecurity {
    companion object {
        private fun getSharedPreference(context: Context): SharedPreferences {
            return context.getSharedPreferences(context.resources.getString(R.string.app_preference), 0)
//            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
//            val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
//            return EncryptedSharedPreferences.create(
//                    context.resources.getString(R.string.app_name),
//                    masterKeyAlias,
//                    context,
//                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            )
        }

        fun putPreferenceString(key: String?, value: String?, context: Context) {
            val valueEncrypt: String = EncryptUtil.EncryptByteArray(value!!.toByteArray(), EncryptUtil.KEYENC)
            val editor = getSharedPreference(context).edit()
            editor.putString(key, valueEncrypt).commit()
        }

        fun putPreferenceInt(key: String?, value: Int, context: Context) {
//            var byte = ByteBuffer.allocate(8).putLong(value.toLong()).array()
//            val valueEncrypt: String = EncryptUtil.EncryptByteArray(byte, EncryptUtil.KEYENC)
            val editor = getSharedPreference(context).edit()
            editor.putInt(key, value).commit()
        }

        fun putPreferenceBoolean(key: String?, value: Boolean, context: Context) {
            val editor = getSharedPreference(context).edit()
            editor.putBoolean(key, value).commit()
        }

        fun getPreferenceString(key: String?, def: String?, context: Context): String? {
            val value: String = getSharedPreference(context).getString(key, def)
            return try {
                var decyp: ByteArray = EncryptUtil.decryptByteArray(value, EncryptUtil.KEYENC)
                String(decyp)
            } catch (e: Exception) {
                "-"
            }

        }

        fun getPreferenceInteger(key: String?, def: Int, context: Context): Int {
            return getSharedPreference(context).getInt(key, def)
        }

        fun getPreferenceBoolean(key: String?, def: Boolean, context: Context): Boolean? {
            return getSharedPreference(context).getBoolean(key, def)
        }

        fun clearPreference(context: Context) {
            getSharedPreference(context).edit().clear().commit()
        }

    }
}