package app.beelabs.com.codebase.support.util

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class EncryptUtil {
    companion object {
        val strNamaPref = "id.ottodigital.jtp.pref"

        val KEYENC = "OTTODIGITALKEY04".toByteArray()

        //-------------------------------------------------------------------------------------------->>> Fungsi enkripsi
        fun ProsesEnkrip(RawData: String): String? {
            return wrap(RawData, keygen())
        }
        //--------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------->>> Keygen
        fun keygen(): String {
            var gkey = ""
            val lCode: String
            var rHead = ""
            val charArray: String
            var i = 0
            val length: Int
            val rand: Random
            charArray = "zyxwvutsrqponmlkjihgfedcba0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()"
            rand = Random()
            length = random_int(10, 15)
            lCode = Integer.toHexString(length)
            i = 0
            while (i <= 4) {
                rHead = rHead + charArray[random_int(0, 71)]
                i++
            }
            i = 0
            while (i <= length - 1) {
                gkey = gkey + charArray[random_int(0, 71)]
                i++
            }
            gkey = lCode + rHead + gkey
            return gkey
        }

        fun random_int(Min: Int, Max: Int): Int {
            return (Math.random() * (Max - Min)).toInt() + Min
        }
        //------------------------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------->>>>	AES, EncryptByteArray
        @Throws(Exception::class)
        fun EncryptByteArray(array: ByteArray?, key: ByteArray?): String {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val secretKey = SecretKeySpec(key, "AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return String(Base64.encode(cipher.doFinal(array), Base64.DEFAULT))
        }
        //--------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------->>>>>> AES, decryptByteArray
        @Throws(Exception::class)
        fun decryptByteArray(strToDecrypt: String, key: ByteArray?): ByteArray {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKey = SecretKeySpec(key, "AES")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return cipher.doFinal(Base64.decode(strToDecrypt.toByteArray(), Base64.DEFAULT))
        }
        //---------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------->>>>
        fun wrap(data: String, vKey: String): String? {
            //---------------------Wrap Declare-------------------------
            var vKey = vKey
            val intLen: Int
            val strTrueKey: String
            val strEncrypted: String
            var strWrapped = ""
            val strvKey2: String
            val s: String
            var strWrapped64 = ""
            val bytesOfMessage: ByteArray
            val thedigest: ByteArray
            val mdMD5: MessageDigest
            val c: Char
            //----------------------------------------------------------
            strvKey2 = vKey
            c = vKey[0]
            s = Character.toString(c)
            vKey = s
            try {
                intLen = vKey.toInt(16)
                strTrueKey = strvKey2.substring(6, 6 + intLen)
                mdMD5 = MessageDigest.getInstance("MD5")
                bytesOfMessage = strTrueKey.toByteArray(StandardCharsets.UTF_8)
                thedigest = mdMD5.digest(bytesOfMessage)
                strEncrypted = EncryptByteArray(data.toByteArray(StandardCharsets.UTF_8), thedigest)
                strWrapped = strvKey2 + strEncrypted
                strWrapped64 = String(Base64.encode(strWrapped.toByteArray(), Base64.DEFAULT))
            } catch (e: Exception) {
            }
            return strWrapped64
        }

        //---------------------------------------------------------------------------------------------------------
        fun unwrap(data: String): String? {
            //---------------------Wrap Declare-------------------------
            val intLen: Int
            val strTrueKey: String
            val s: String
            val data2: String
            var strUnWrapped64 = ""
            val bytesOfMessage: ByteArray
            val thedigest: ByteArray
            val mdMD5: MessageDigest
            val c: Char
            val strBase64 = String(Base64.decode(data.toByteArray(), Base64.DEFAULT))
            data2 = strBase64
            val bytesOfDecrypted: ByteArray
            c = strBase64[0]
            s = Character.toString(c)
            try {
                intLen = s.toInt(16)
                strTrueKey = strBase64.substring(6, 6 + intLen)
                mdMD5 = MessageDigest.getInstance("MD5")
                bytesOfMessage = strTrueKey.toByteArray(StandardCharsets.UTF_8)
                thedigest = mdMD5.digest(bytesOfMessage)
                val datanya = data2.substring(6 + intLen)
                bytesOfDecrypted = decryptByteArray(datanya, thedigest)
                strUnWrapped64 = String(bytesOfDecrypted, StandardCharsets.UTF_8)
            } catch (e: Exception) {
            }
            return strUnWrapped64
        }
    }
}