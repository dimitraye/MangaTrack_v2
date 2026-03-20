package com.example.mangatrack_v2.util

import android.content.Context
import android.os.Environment
import com.example.mangatrack_v2.data.entity.MangaEntity
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.AreaBreak
import com.itextpdf.layout.properties.AreaBreakType
import java.io.File
import android.content.ContentValues
import android.provider.MediaStore
import android.os.Build
import android.net.Uri
import androidx.annotation.RequiresApi
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.layout.element.Image

object PdfExporter {

    fun exportMangas(context: Context, mangas: List<MangaEntity>): Uri? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            // ✅ Android 10+
            val resolver = context.contentResolver

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "manga_library.pdf")
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/")
            }

            val uri = resolver.insert(
                MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                contentValues
            )

            uri?.let {

                val outputStream = resolver.openOutputStream(it)

                if (outputStream == null) return null

                val writer = PdfWriter(outputStream)
                val pdf = PdfDocument(writer)
                val document = Document(pdf)

                writePdfContent(context, document, mangas)

                document.close()
                outputStream?.close()
            }

            uri

        } else {

            // ✅ Android < 10
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "manga_library.pdf"
            )

            val writer = PdfWriter(file)
            val pdf = PdfDocument(writer)
            val document = Document(pdf)

            writePdfContent(context, document, mangas)

            document.close()

            Uri.fromFile(file)
        }
    }

    private fun writePdfContent(
        context: Context,
        document: Document,
        mangas: List<MangaEntity>
    ) {

        document.add(Paragraph("MangaTrack"))
        document.add(Paragraph("My Manga List\n"))
        document.add(Paragraph("Total mangas: ${mangas.size}"))

        document.add(AreaBreak(AreaBreakType.NEXT_PAGE))

        mangas.forEachIndexed { index, manga ->

            // 🖼️ IMAGE (zone 1)
            manga.coverImagePath?.let { path ->

                try {
                    val uri = Uri.parse(path)
                    val inputStream = context.contentResolver.openInputStream(uri)

                    val bytes = inputStream?.readBytes()

                    if (bytes != null) {
                        val imageData = ImageDataFactory.create(bytes)
                        val image = Image(imageData)

                        image.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER)
                        image.scaleToFit(250f, 350f) // 🔥 contrôle taille

                        document.add(image)
                    }

                    inputStream?.close()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // 📄 INFOS (zone 2)
            document.add(Paragraph("\n${manga.title}"))
            document.add(Paragraph("Status: ${manga.status}"))
            document.add(
                Paragraph("Chapters: ${manga.chaptersRead} / ${manga.chaptersTotal ?: "?"}")
            )

            // ✍️ COMMENTAIRE (zone 3)
            manga.review?.let {

                val maxLength = 300 // 🔥 limite texte

                val truncated = if (it.length > maxLength) {
                    it.take(maxLength) + "..."
                } else it

                document.add(Paragraph("\nReview:"))
                document.add(Paragraph(truncated))
            }

            // ➜ page suivante
            if (index < mangas.lastIndex) {
                document.add(AreaBreak(AreaBreakType.NEXT_PAGE))
            }
        }
    }
}