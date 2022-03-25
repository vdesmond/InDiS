package com.desmond.indis.ui.questionnaire

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.desmond.indis.R
import com.desmond.indis.databinding.FragmentSurveyBasicBinding
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView


/**
 * A simple [Fragment] subclass.
 * Use the [SurveyBasicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyBasicFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentSurveyBasicBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var survey: SurveyView
    private lateinit var surveyContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        survey = findViewById(R.id.survey_view)
//        container = findViewById(R.id.surveyContainer)
//        setupSurvey(survey)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSurveyBasicBinding.inflate(inflater, container, false)

        survey = binding.surveyView
        surveyContainer =  binding.surveyContainer
        setupSurvey(survey)

        return binding.root
    }

    private fun setupSurvey(surveyView: SurveyView) {
        val steps = listOf(
            InstructionStep(
                title = this.resources.getString(R.string.intro_title),
                text = this.resources.getString(R.string.intro_text),
                buttonText = this.resources.getString(R.string.intro_start)
            ),
            QuestionStep(
                title = this.resources.getString(R.string.about_you_question_title),
                text = this.resources.getString(R.string.about_you_question_text),
                answerFormat = AnswerFormat.TextAnswerFormat(maxLines = 5)
            ),
            QuestionStep(
                title = this.resources.getString(R.string.how_old_title),
                text = this.resources.getString(R.string.how_old_text),
                answerFormat = AnswerFormat.IntegerAnswerFormat(
                    defaultValue = 25,
                    hint = this.resources.getString(R.string.how_old_hint)
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.how_fat_question_title),
                text = this.resources.getString(R.string.how_fat_question_text),
                answerFormat = AnswerFormat.ScaleAnswerFormat(
                    minimumValue = 1,
                    maximumValue = 5,
                    minimumValueDescription = this.resources.getString(R.string.how_fat_min),
                    maximumValueDescription = this.resources.getString(R.string.how_fat_max),
                    step = 1f,
                    defaultValue = 3
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.allergies_question_title),
                text = this.resources.getString(R.string.allergies_question_text),
                answerFormat = AnswerFormat.MultipleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.allergies_back_penicillin)),
                        TextChoice(this.resources.getString(R.string.allergies_latex)),
                        TextChoice(this.resources.getString(R.string.allergies_pet)),
                        TextChoice(this.resources.getString(R.string.allergies_pollen))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_title),
                text = this.resources.getString(R.string.quit_or_continue_question_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.boolean_example_title),
                text = this.resources.getString(R.string.boolean_example_text),
                answerFormat = AnswerFormat.BooleanAnswerFormat(
                    positiveAnswerText = this.resources.getString(R.string.how_fat_min),
                    negativeAnswerText = this.resources.getString(R.string.how_fat_max),
                    defaultValue = AnswerFormat.BooleanAnswerFormat.Result.NegativeAnswer
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.value_picker_example_title),
                text = this.resources.getString(R.string.value_picker_example_text),
                answerFormat = AnswerFormat.ValuePickerAnswerFormat(
                    choices = (0..10).toList().map { it.toString() },
                    defaultValue = 5.toString()
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.date_picker_title),
                text = this.resources.getString(R.string.date_picker_text),
                answerFormat = AnswerFormat.DateAnswerFormat()
            ),
            QuestionStep(
                title = this.resources.getString(R.string.time_picker_title),
                text = this.resources.getString(R.string.time_picker_text),
                answerFormat = AnswerFormat.TimeAnswerFormat()
            ),
            QuestionStep(
                title = this.resources.getString(R.string.email_question_title),
                text = this.resources.getString(R.string.email_question_text),
                answerFormat = AnswerFormat.EmailAnswerFormat()
            ),
            QuestionStep(
                title = this.resources.getString(R.string.image_selector_question_title),
                text = this.resources.getString(R.string.image_selector_question_text),
                answerFormat = AnswerFormat.ImageSelectorFormat(
                    numberOfColumns = 5,
                    defaultSelectedImagesIndices = listOf(1, 3),
                    imageChoiceList = listOf(
                        ImageChoice(R.drawable.color1),
                        ImageChoice(R.drawable.color2),
                        ImageChoice(R.drawable.color3),
                        ImageChoice(R.drawable.color4),
                        ImageChoice(R.drawable.color5),
                        ImageChoice(R.drawable.color6)
                    )
                )
            ),
//            QuestionStep(
//                title = getString(R.string.date_time_question_title),
//                text = getString(R.string.date_time_question_text),
//                answerFormat = AnswerFormat.DateTimeAnswerFormat()
//
//            ),
//            QuestionStep(
//                title = this.resources.getString(R.string.location_select_title),
//                text = this.resources.getString(R.string.location_question_text),
//                answerFormat = AnswerFormat.LocationAnswerFormat(lifecycle)
//            ),
            CompletionStep(
                title = this.resources.getString(R.string.finish_question_title),
                text = this.resources.getString(R.string.finish_question_text),
                buttonText = this.resources.getString(R.string.finish_question_submit)
            )
        )

        val task = NavigableOrderedTask(steps = steps)

        task.setNavigationRule(
            steps[5].id,
            NavigationRule.DirectStepNavigationRule(
                destinationStepStepIdentifier = steps[6].id
            )
        )

        task.setNavigationRule(
            steps[7].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "Ja" -> steps[7].id
                        "Nein" -> steps[0].id
                        else -> null
                    }
                }
            )
        )

        surveyView.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            if (reason == FinishReason.Completed) {
                taskResult.results.forEach { stepResult ->
                    Log.e("ASDF", "answer ${stepResult.results.firstOrNull()}")
                    surveyContainer.removeAllViews()
                    findNavController().popBackStack()

                }
            }
        }

        val configuration = SurveyTheme(
            themeColorDark = ContextCompat.getColor(this.requireContext(), R.color.survey_text),
            themeColor = ContextCompat.getColor(this.requireContext(), R.color.survey_text),
            textColor = ContextCompat.getColor(this.requireContext(), R.color.survey_text),
//            abortDialogConfiguration = AbortDialogConfiguration(
//                title = R.string.title,
//                message = R.string.message,
//                neutralMessage = R.string.no,
//                negativeMessage = R.string.yes
//            )
        )

        surveyView.start(task, configuration)
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        return if (keyCode == KeyEvent.KEYCODE_BACK) {
//            survey.backPressed()
//            true
//        } else false
//    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SurveyBasicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SurveyBasicFragment()
    }
}