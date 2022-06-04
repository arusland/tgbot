package com.github.arusland.tgbot

import picocli.CommandLine.IVersionProvider
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.jar.Attributes
import java.util.jar.Manifest

/**
 * Extract version from "META-INF/MANIFEST.MF"
 */
internal class ManifestVersionProvider : IVersionProvider {
    override fun getVersion(): Array<String> {
        val resources: Enumeration<URL> = javaClass.classLoader.getResources("META-INF/MANIFEST.MF")

        while (resources.hasMoreElements()) {
            val url: URL = resources.nextElement()

            try {
                val attributes = Manifest(url.openStream()).mainAttributes

                if (attributes.isApplicableManifest()) {
                    return arrayOf(
                        attributes.attr("Specification-Title") + " " + attributes.attr("Specification-Version")
                    )
                }
            } catch (ex: IOException) {
                // skip
            }
        }

        return emptyArray()
    }

    private fun Attributes.isApplicableManifest(): Boolean = "tgbot" == this.attr("Specification-Title")

    private fun Attributes.attr(key: String): String = this.getValue(key) ?: ""
}
