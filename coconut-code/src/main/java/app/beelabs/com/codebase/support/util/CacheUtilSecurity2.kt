//package app.beelabs.com.codebase.support.util
//
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.security.crypto.EncryptedSharedPreferences
//import androidx.security.crypto.MasterKeys
//import app.beelabs.com.codebase.R
//
//class CacheUtilSecurity2 {
//    companion object {
//        private fun getSharedPreference(context: Context): SharedPreferences {
//            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
//            val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
//            return EncryptedSharedPreferences.create(
//                    context.resources.getString(R.string.app_name),
//                    masterKeyAlias,
//                    context,
//                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            )
//        }
//
//        fun putPreferenceString(key: String?, value: String?, context: Context) {
//            val editor = getSharedPreference(context).edit()
//            editor.putString(key, value).commit()
//        }
//
//        fun putPreferenceInteger(key: String?, value: Int, context: Context) {
//            val editor = getSharedPreference(context).edit()
//            editor.putInt(key, value).commit()
//        }
//
//        fun putPreferenceBoolean(key: String?, value: Boolean, context: Context) {
//            val editor = getSharedPreference(context).edit()
//            editor.putBoolean(key, value).commit()
//        }
//
//        fun getPreferenceString(key: String?, context: Context): String? {
//            return getSharedPreference(context).getString(key, "")
//        }
//
//        fun getPreferenceInteger(key: String?, context: Context): Int {
//            return getSharedPreference(context).getInt(key, 0)
//        }
//
//        fun getPreferenceBoolean(key: String?, context: Context): Boolean? {
//            return getSharedPreference(context).getBoolean(key, false)
//        }
//
//        fun clearPreference(context: Context) {
//            getSharedPreference(context).edit().clear().commit()
//        }
//    }
//}