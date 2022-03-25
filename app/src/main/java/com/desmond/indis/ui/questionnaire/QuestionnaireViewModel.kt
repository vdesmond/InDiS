package com.desmond.indis.ui.questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionnaireViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Questionnaire Fragment"
    }
    val text: LiveData<String> = _text
}