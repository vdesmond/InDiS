package com.desmond.indis.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.desmond.indis.R
import com.desmond.indis.databinding.FragmentQuestionnaireBinding

class QuestionnaireFragment : Fragment() {

    private var _binding: FragmentQuestionnaireBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val questionnaireViewModel =
            ViewModelProvider(this).get(QuestionnaireViewModel::class.java)

        _binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textQuestionnaire

        questionnaireViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val buttonStartQuiz : Button = binding.buttonStartQuiz
        buttonStartQuiz.setOnClickListener { view ->

            view.findNavController().navigate(R.id.action_navigation_home_to_surveyBasicFragment)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}