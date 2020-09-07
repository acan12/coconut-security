package app.beelabs.com.codebase

import okhttp3.CertificatePinner
import okhttp3.Interceptor

class ConfigNetworkSecurity(
        var apiDomain: String,
        var allowUntrusted: Boolean,
        var clazz: Any,
        var timeout: Int,
        var enableLoggingHttp: Boolean,
        var interceptors: Array<Interceptor>?,
        var certifcatePinner: CertificatePinner
)