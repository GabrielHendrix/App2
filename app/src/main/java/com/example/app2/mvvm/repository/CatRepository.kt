package com.example.mvvm.repository

import android.content.Context


class CatRepository {
    /*
            Essa classe seria a interface com um banco de dados local ou remoto via API.
            Por enquanto, vamos apenas retornar que o registro foi feito com sucesso.
            No futuro, a ideia é implementar as funções de registro aqui dentro.
         */
    private var phraseList: List<String> = listOf("frase1", "frase2", "frase3", "frase4", "frase5", "frase6", "frase7", "frase8")

    fun generatePhrase(): String {
        return phraseList[(0 until 7).random()]
    }
}
