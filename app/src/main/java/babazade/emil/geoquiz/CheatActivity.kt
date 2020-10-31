package babazade.emil.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val EXTRA_ANSWER_IS_TRUE = "babazade.emil.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "babazade.emil.geoquiz.answer_shown"

class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = false

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var apiVersionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        apiVersionTextView = findViewById(R.id.api_version_text)
        apiVersionTextView.text = "Api Level ${Build.VERSION.SDK_INT}"

        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContect: Context, answerIsTrue: Boolean): Intent =
            Intent(packageContect, CheatActivity::class.java).putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
    }
}